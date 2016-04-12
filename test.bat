rem Authentification in system as admin
curl http://localhost:8080/login -d logonName=admin -d password=123 -d remember-me=on -c coks.txt
rem GET all restaraunnts
curl http://localhost:8080/rest/restaurant/ -G -b coks.txt 
rem Create Dishe with id 28 PUT restaurantId workdateId
curl http://localhost:8080/dishe/1/1/ -H "Content-Type: application/json" -X PUT -d {\"name\":\"juice\",\"price\":11.4} -b coks.txt 
rem GET all restaurants menu. It's all Dishes.
curl http://localhost:8080/dishe/ -G -b coks.txt 
rem Get Diche by Id
curl http://localhost:8080/dishe/28/ -G -b coks.txt
rem Update Dishe POST 
curl http://localhost:8080/dishe/28/ -H "Content-Type: application/json" -X POST -d {\"name\":\"COKE\",\"price\":2.4} -b coks.txt 
rem Check updated value Diche by Id
curl http://localhost:8080/dishe/28/ -G -b coks.txt
rem Get menu of dishe GET by restaurantId and workdateId
curl http://localhost:8080/dishe/1/1/ -G -b coks.txt
rem Delete Dishe DELETE
curl http://localhost:8080/dishe/28/ -H "Content-Type: application/json" -X  DELETE -b coks.txt
rem Get menu of dishe GET by restaurantId and workdateId
curl http://localhost:8080/dishe/1/1/ -G -b coks.txt
rem logout
curl http://localhost:8080/logout -b coks.txt
rem POST Voting process from annonym user with post authentification
curl http://localhost:8080/rest/voting/1/1/ -H "Content-Type: application/json" -X POST -d {\"logon\":\"admin\",\"password\":\"123\"} 
rem POST Voting process from annonym user with post authentification
curl http://localhost:8080/rest/voting/2/2/ -H "Content-Type: application/json" -X POST -d {\"logon\":\"app1\",\"password\":\"123\"} 
rem POST Voting process from annonym user with post authentification
curl http://localhost:8080/rest/voting/3/3/ -H "Content-Type: application/json" -X POST -d {\"logon\":\"app2\",\"password\":\"123\"} 
rem Authentification in system as admin
curl http://localhost:8080/login -d logonName=admin -d password=123 -d remember-me=on -c coks.txt
rem Check votes
curl http://localhost:8080/rest/votes/ -G -b coks.txt 
