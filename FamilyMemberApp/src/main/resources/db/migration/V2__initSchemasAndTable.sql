CREATE SCHEMA IF NOT EXISTS FamilyMemberDB;
CREATE TABLE IF NOT EXISTS FamilyMemberDB.FamilyMember (
id serial primary key,
family_id int references FamilyDB.Family(id),
firstName varchar,
lastName varchar,
socialNumber int unique
);