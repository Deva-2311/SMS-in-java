@echo off
cd src
javac -cp "..\lib\mysql-connector-j-9.5.0.jar" TestDB.java
java -cp ".;..\lib\mysql-connector-j-9.5.0.jar" TestDB
pause

