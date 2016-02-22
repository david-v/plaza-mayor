DROP TABLE towns IF EXISTS;

CREATE TABLE towns (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(100),
  lat  FLOAT,
  lon  FLOAT
);

CREATE INDEX towns_name ON towns (name);
