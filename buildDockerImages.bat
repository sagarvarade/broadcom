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

cd ..

cd broadcomUI

docker build -t broadcom-broadcomaui:7.1.1 -f dockerfileBroadcomUI .

cd ..

cd emailsender

docker build -t broadcom-emailsender:8.1.1 -f dockerfileEmailSender .

cd ..

cd smssender

docker build -t broadcom-smssender:9.1.1 -f dockerfileSmsSender .

cd ..

pause
