#!/usr/bin/env python
# -*- coding: utf-8 -*-

import sys
from lxml import html
import requests
import mysql.connector

try:
	dbCon = mysql.connector.connect(user='root', password='', host='127.0.0.1', database='plazamayor')
	dbCur = dbCon.cursor()
except mysql.connector.Error as err:
	if err.errno == errorcode.ER_ACCESS_DENIED_ERROR:
		print("Something is wrong with your user name or password")
	elif err.errno == errorcode.ER_BAD_DB_ERROR:
		print("Database does not exist")
	else:
		print(err)
	sys.exit()


dbCur.execute("DROP TABLE IF EXISTS towns")
dbCur.execute("DROP TABLE IF EXISTS regions")

dbCur.execute("CREATE TABLE IF NOT EXISTS towns (" +
			"  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY," +
			"  region_id INT(4) UNSIGNED NOT NULL," +
			"  name VARCHAR(50) NOT NULL," +
			"  wiki_url VARCHAR(100),"
			"  lat FLOAT," +
			"  lon FLOAT," +
			"  INDEX (name)" +
			") engine=InnoDB;")
dbCur.execute("CREATE TABLE IF NOT EXISTS regions (" +
			"  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY," +
			"  name VARCHAR(50) NOT NULL UNIQUE," +
			"  INDEX (name)" +
			") engine=InnoDB;")


baseWikipediaUrl = 'https://es.wikipedia.org'
page = requests.get(baseWikipediaUrl + '/wiki/Anexo:Municipios_de_España')
tree = html.fromstring(page.content)
elements = tree.xpath('//*[@id="mw-content-text"]/ul/li/a')

regions = []

regionCount = 0
totalTowns = 0

for ele in elements:
	title = ele.get('title')
	if title is None:
		continue # the elements we're looking for all have a 'title' defined
	href = ele.get('href')
	if (href is None):
		continue # the elements we're looking for all have a 'href' defined
	if ("Anexo:Municipios" not in href) and ("Anexo:Concejos" not in href): 
		continue # the elements we're looking for all contain string 'Anexo' in their 'href'
	# print "."

	regionName = ele.text.replace("Municipios de ", "").replace("la provincia de ", "").replace("Concejos de ", "").replace("las Islas ", "").replace("la Comunidad de ", "").replace("la Región de ".decode('utf-8'), "")

	subpage = requests.get(baseWikipediaUrl + href)
	subtree = html.fromstring(subpage.content)
	columns = subtree.xpath('//table[contains(@class,"sortable")]/tr/th')

	i = 1
	for col in columns:
		# Find column of table which contains the data we need
		coltext = col.text
		if coltext is None:
			for child in col.getchildren():
				coltext = child.text
		# print coltext
		colnames = [
			"Nombre",
			"Municipio",
			"Castellano",
			"Español",
			"Topónimo en Español",
			"Topónimo en español",
			"Nombre en Español",
			"Nombre en español"
		]
		if any(name.decode('utf-8') in coltext for name in colnames):
			break;
		i = i + 1

	subelements = subtree.xpath('//table[contains(@class,"sortable")]/tr/td[' + str(i) + ']/a')
	if not subelements:
		subelements = subtree.xpath('//table[contains(@class,"sortable")]/tr/td[' + str(i) + ']/b/a') #they appear in Bold
	if not subelements:
		print "NOT FOUND"

	townCount = 0
	for subele in subelements:
		townName = subele.text
		if townName is None:
			continue # the elements we're looking for all have text defined
		subhref = subele.get('href')
		if (subhref is None):
			print "CRITICAL!"
			break
		# print  ' - ' + townName + ' - ' + subhref
		
		try:
			regions[regionCount]
		except IndexError:
			regionInDB = False
			dbCur.execute("SELECT id FROM regions WHERE name LIKE '" + regionName + "'")
			for (id) in dbCur:
				regionInDB = True
				regionId = id

			if not regionInDB:
				dbCur.execute("INSERT INTO regions (name) VALUES ('" + regionName + "')")
				regionId = dbCur.lastrowid
				print str(regionId)
			regions.append( {'regionId': regionId, 'name': regionName, 'count': 0} )

		escapedTownName = townName.replace("'", "''")
		townInDB = False
		dbCur.execute("SELECT id FROM towns WHERE name LIKE '" + escapedTownName + "' AND region_id = " + str(regionId))
		for (id) in dbCur:
			townInDB = True
			townId = id
			print "TOWN ID: " + str(townId)

		if not townInDB:
			query = "INSERT INTO towns (name, region_id, wiki_url) VALUES ('" + escapedTownName + "', " + str(regionId) + ", '" + subhref + "')"
			print query
			dbCur.execute(query)

		regions[regionCount]['count'] = regions[regionCount]['count'] + 1		
		townCount = townCount + 1
		totalTowns = totalTowns + 1

	regionCount = regionCount + 1

for region in regions:
	print region['name'] + ': ' + str(region['count'])

dbCon.commit()

dbCur.close()
dbCon.close()

print '------------------------------'
print 'TOTAL REGIONS: ' + str(regionCount)
print 'TOTAL TOWNS: ' + str(totalTowns) 
