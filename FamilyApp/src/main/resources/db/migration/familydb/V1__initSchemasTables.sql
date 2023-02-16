CREATE SCHEMA IF NOT EXISTS FamilyDB;
CREATE TABLE IF NOT EXISTS FamilyDB.Family (
id serial primary key,
name varchar default '<unassigned>',
nrOfAdults int default 0,
nrOfChildren int default 0,
nrOfInfants int default 0
);