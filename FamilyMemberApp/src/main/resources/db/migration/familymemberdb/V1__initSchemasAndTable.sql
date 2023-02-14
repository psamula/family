CREATE SCHEMA IF NOT EXISTS FamilyMemberDB;
CREATE TABLE IF NOT EXISTS FamilyMemberDB.FamilyMember (
id serial primary key,
familyid int,
firstname varchar,
lastname varchar,
socialnumber int unique
);
CREATE INDEX idx_familyid ON FamilyMemberDB.FamilyMember(familyid);