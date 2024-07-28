# BraodCom App
BraodCom App, Multi Module Spring Boot Project for learning purpose

<h3> This is multi module project to send sms/email to group of users </h3>

# Server Ports : 

1. Naming Server        : 7100
2. API Gateway Server   : 7000  
3. Authentication       : 7200
4. Broadcom application : 7210
5. BroadCom UI          : 7214
6. Email sender         : 7230
7. Sms sender           : 7231



# Necessary commands 

1.> mvn clean install

	will install all apps and create jar files in target folder.

2. buildDockerImages.bat will pick above jar files from target folder and will create a docker images for them
  > buildDockerImages.bat

3. Run docker compose file to run docker images
  > docker compose up -d
  
This docker file has all the dependecis requird like ,zookeeper , kafka,mysql , and all required docker images config.


This will make your app ready.

Then you can use the postman attached to this repo.
BroadCom.postman_collection.json

Kindly use BroadRunnerGateway folder to go with Gateway.

You will need to add one user before sending a request to app , this sql can be found in TextFile.txt.
All necessary docker commands found in TextFile.txt


Username : sagar , password : sagar

-------
