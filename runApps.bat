@echo off
start /b "1" java -jar deployDirectory/apigateway-1.1.1.jar
timeout 5 > NUL 
start /b "2" java -jar deployDirectory/namingserver-2.1.1.jar
timeout 5 > NUL 
start /b "3" java -jar deployDirectory/authentication-3.1.1.jar
timeout 5 > NUL 
pause
