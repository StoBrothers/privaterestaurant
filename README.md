# privaterestaurant

Spring MVC + Web + JSON API.
    
Demo vote system.
========================

Requirements
------------
Install Java 8.65 version
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


Introduction
--------------------------

This is voting system for restaurants. Application provides the following functions:

1. Authentication and authorisation users and session support.
2. Work with entities: Restaurants, WorkDate, User, Dishe, Vote and CRUD operations to them.
3. Users can vote for any restaurant. Only one vote counts per user per day.
4. User can vote for restaurant only in defined time duration. This period can set in application.yml file. 
5. Please use plushour and minushour parameters for defining time of voting in during day.
6. Application allow make vote as anonymous user with post authentication process and don't use session.
 
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

Command file make next instructions and steps with HTTP requests:

1. Authentification in system as Admin user with save cookie information in file coks.txt
curl http://localhost:8080/login -d logonName=admin -d password=123 -d remember-me=on -c coks.txt

2. GET. It's getting information about all restourants in system. Response in JSON format.
curl http://localhost:8080/rest/restaurant/ -G -b coks.txt 

3. PUT. Create Dishe with id=28 and restaurantId=1 and workdateId=1 and parameters name = "juice" and "price"=11.4
curl http://localhost:8080/dishe/1/1/ -H "Content-Type: application/json" -X PUT -d {\"name\":\"juice\",\"price\":11.4} -b coks.txt 

4. GET. Get information about all restaurants menu. It's all Dishes.
curl http://localhost:8080/dishe/ -G -b coks.txt 

5. GET. Get information about Diche with id=28 (Created in step 3).
curl http://localhost:8080/dishe/28/ -G -b coks.txt

6. POST. Change dishe name and price for Dishe with id=28.
curl http://localhost:8080/dishe/28/ -H "Content-Type: application/json" -X POST -d {\"name\":\"COKE\",\"price\":2.4} -b coks.txt 

7. GET. Get information for Dishe with id=28 and check that changes from step 6 updated.
curl http://localhost:8080/dishe/28/ -G -b coks.txt

8. GET. Get updated menu of dishes by restaurantId=1 and workdateId=1 after step 6.
curl http://localhost:8080/dishe/1/1/ -G -b coks.txt

9. DELETE. Delete Dishe with id=28.
curl http://localhost:8080/dishe/28/ -H "Content-Type: application/json" -X  DELETE -b coks.txt

10. GET. Get updated menu of dishe by restaurantId=1 and workdateId=1 after step 9.
curl http://localhost:8080/dishe/1/1/ -G -b coks.txt

11. GET. Make logout request.
curl http://localhost:8080/logout -b coks.txt

12. POST. Voting use post process of authentication. Body request contains user authentication parameters.
curl http://localhost:8080/rest/voting/1/1/ -H "Content-Type: application/json" -X POST -d {\"logon\":\"admin\",\"password\":\"123\"}

13. POST. Voting use post process of authentication. Body request contains user authentication parameters.
curl http://localhost:8080/rest/voting/2/2/ -H "Content-Type: application/json" -X POST -d {\"logon\":\"app1\",\"password\":\"123\"}

14. POST. Voting use post process of authentication. Body request contains user authentication parameters.
curl http://localhost:8080/rest/voting/3/3/ -H "Content-Type: application/json" -X POST -d {\"logon\":\"app2\",\"password\":\"123\"} 

15. GET. Authentification in system as App1 user.
curl http://localhost:8080/login -d logonName=app1 -d password=123 -d remember-me=on -c coks.txt

16. GET. Get votes information.
curl http://localhost:8080/rest/votes/ -G -b coks.txt


File example_out.txt contains result of execution test.bat.


Entity uml diagramm in file entity_uml.jpg


Codacy project metrics
--------------------------

https://api.codacy.com/project/badge/grade/313d3ab611d149d892036adefdaf2a93)](https://www.codacy.com/app/DjSmile/privaterestaurant
[![Codacy Badge](https://api.codacy.com/project/badge/grade/313d3ab611d149d892036adefdaf2a93)](https://www.codacy.com/app/DjSmile/privaterestaurant)


<a href="https://www.codacy.com/app/DjSmile/privaterestaurant"><img src="https://api.codacy.com/project/badge/grade/313d3ab611d149d892036adefdaf2a93"/></a>
!https://api.codacy.com/project/badge/grade/313d3ab611d149d892036adefdaf2a93!:https://www.codacy.com/app/DjSmile/privaterestaurant   text
{<img src="https://api.codacy.com/project/badge/grade/313d3ab611d149d892036adefdaf2a93" alt="Codacy code quality" />}[https://www.codacy.com/app/DjSmile/privaterestaurant]
image:https://api.codacy.com/project/badge/grade/313d3ab611d149d892036adefdaf2a93["Codacy code quality", link="https://www.codacy.com/app/DjSmile/privaterestaurant"] ascii



