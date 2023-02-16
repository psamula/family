@echo off
call cd FamilyApp
call mvn clean package -DskipTests=true
call cd ../FamilyMemberApp
call mvn clean package -DskipTests=true