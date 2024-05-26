@echo off
cd namingserver

docker build -t broadcom-namingserver:1.1.1 -f dockerfileNamingsever .

cd ..

cd apigateway

docker build -t broadcom-apigateway:2.1.1 -f dockerfileApigateway .

cd ..

cd authentication

docker build -t broadcom-authentication:3.1.1 -f dockerfileAuthentication .

cd ..

cd broadcomapp

docker build -t broadcom-broadcomapp:4.1.1 -f dockerfileBroadcomapp .

pause
