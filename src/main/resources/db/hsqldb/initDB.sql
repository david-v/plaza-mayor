DROP TABLE towns IF EXISTS;

CREATE TABLE towns (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(100),
  lat  FLOAT,
  lon  FLOAT
);

CREATE TABLE `regions` (
  `id` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `name_2` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE INDEX towns_name ON towns (name);
