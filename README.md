# privaterestaurant

Spring MVC + Web + JSON API.
    
Demo vote system.
========================

Requirements
------------
Install Java
http://www.oracle.com/ 

Install Apache Maven 
https://maven.apache.org

Install CURL
https://curl.haxx.se/

 
How run a project?
--------------------------

```
mvn spring-boot:run 
```
Project supported some spring profiles. As example "dev" profile for work with MSSQL DB.

```
mvn clean spring-boot:run -Pdev
```

If you have additional requirements for detail set jvm Arguments, you have possibility to edit 
pom.xml. If you use approach where you try to transmit parameters in command line that 
spring-boot forced to erase those parameters.

You can set application in src/main/resources/application.yml
 
How work with application?
------------------------------
You can open application in browser: http://localhost:8080
 
Test users
-----------------------------
* admin passw 123
* app1  passw 123
* app2  passw 123


How run a CURL file for testing application?
--------------------------

The test.bat batch file contains CURL executive commands for Windows.
This commands fills datas to database. See "test.bat" file in view mode for getting detail information.








