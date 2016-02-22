#!/usr/bin/env python
# -*- coding: utf-8 -*-

from lxml import html
import requests

baseWikipediaUrl = 'https://es.wikipedia.org'
page = requests.get(baseWikipediaUrl + '/wiki/Anexo:Municipios_de_España')
tree = html.fromstring(page.content)
elements = tree.xpath('//*[@id="mw-content-text"]/ul/li/a')

regions = []
towns = []

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
		subtext = subele.text
		if subtext is None:
			continue # the elements we're looking for all have text defined
		subhref = subele.get('href')
		if (subhref is None):
			print "CRITICAL!"
			break
		# print  ' - ' + subtext + ' - ' + subhref
		
		town = {'region': regionName, 'name': subtext, 'href': subhref}
		towns.append(town)

		try:
			regions[regionCount]
		except IndexError:
			regions.append( {'name': regionName, 'count': 0} )

		regions[regionCount]['count'] = regions[regionCount]['count'] + 1
		
		townCount = townCount + 1
		totalTowns = totalTowns + 1

	regionCount = regionCount + 1

for region in regions:
	print region['name'] + ': ' + str(region['count'])

print '------------------------------'
print 'TOTAL REGIONS: ' + str(regionCount)
print 'TOTAL TOWNS: ' + str(totalTowns) 