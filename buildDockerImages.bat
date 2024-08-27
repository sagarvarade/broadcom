@echo off
cd naming-server

docker build -t broadcom-namingserver:1.1.1 -f dockerfileNamingsever .

cd ..

cd api-gateway

docker build -t broadcom-apigateway:2.1.1 -f dockerfileApigateway .

cd ..

cd authentication

docker build -t broadcom-authentication:3.1.1 -f dockerfileAuthentication .

cd ..

cd broad-com-app

docker build -t broadcom-broadcomapp:4.1.1 -f dockerfileBroadcomapp .

cd ..

cd broadcomUI

docker build -t broadcom-broadcomaui:7.1.1 -f dockerfileBroadcomUI .

cd ..

cd email-sender

docker build -t broadcom-emailsender:8.1.1 -f dockerfileEmailSender .

cd ..

cd sms-sender

docker build -t broadcom-smssender:9.1.1 -f dockerfileSmsSender .

cd ..

pause
