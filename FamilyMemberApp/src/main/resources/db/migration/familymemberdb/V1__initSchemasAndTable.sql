CREATE SCHEMA IF NOT EXISTS FamilyMemberDB;
CREATE TABLE IF NOT EXISTS FamilyMemberDB.FamilyMember (
id serial primary key,
familyid int,   /* familyId as introduction of relationship between family and familymember tables.
                    However, it cannot be set as a foreign key due to distributed transactions
                    microservices' problem */
firstname varchar,
lastname varchar,
age int,
socialnumber int unique
);
CREATE INDEX idx_familyid ON FamilyMemberDB.FamilyMember(familyid);