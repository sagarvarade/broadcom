# BraodCom App
BraodCom App, Multi Module Spring Boot Project for learning purpose


# Server Ports : 

1. Authentication       : 7200
2. Naming Server        : 7100
3. API Gateway Server   : 7000  
4. Broadcom application : 7210
5. BroadCom UI          : c

<pre>
Add below to your Java application run configuration to avoid certificate issue, In VM arguments :

-Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true

To change the port of application, In VM arguments :
-Dserver.port=8021


spring.profiles.active=dev  : Add to run config to run specific profile



Docker Build Commands :


1. Build Docker images :

docker build -t broadcom-namingserver:1.1.1 -f dockerfileNamingsever .

docker build -t broadcom-apigateway:2.1.1 -f dockerfileApigateway .

docker build -t broadcom-authentication:3.1.1 -f dockerfileAuthentication .

docker build -t broadcom-broadcomapp:4.1.1 -f dockerfileBroadcomapp .


To run images on docker :

Remove dangling images

docker image prune

Run by compose

docker-compose down
docker-compose up --build -d


docker run -d --restart=always -p 7100:7100 broadcom-namingserver:1.1.1

docker run -d --restart=always -p 7000:7000 broadcom-apigateway:2.1.1

docker run -d --restart=always -p 7200:7200 broadcom-authentication:3.1.1

docker run -d --restart=always -p 7210:7210 broadcom-broadcomapp:4.1.1


Docker stop all containers :

docker stop $(docker ps -q)

Remove all container :

docker rm $(docker ps -a -q)

Stop and remove all containers :
docker stop $(docker ps -q) && docker rm $(docker ps -a -q)

docker-compose down
docker-compose up --build -d

docker-compose logs apigateway
docker-compose up -d
--

docker stop $(docker ps -a -q --filter="ancestor=broadcom-namingserver:1.1.1") -f
docker stop $(docker ps -a -q --filter="ancestor=broadcom-apigateway:2.1.1") -f
docker stop $(docker ps -a -q --filter="ancestor=broadcom-authentication:3.1.1") -f
docker stop $(docker ps -a -q --filter="ancestor=broadcom-broadcomapp:4.1.1") -f

docker rm $(docker ps -a -q --filter="ancestor=broadcom-namingserver:1.1.1") -f
docker rm $(docker ps -a -q --filter="ancestor=broadcom-apigateway:2.1.1") -f
docker rm $(docker ps -a -q --filter="ancestor=broadcom-authentication:3.1.1") -f
docker rm $(docker ps -a -q --filter="ancestor=broadcom-broadcomapp:4.1.1") -f

docker rmi -f broadcom-namingserver:1.1.1
docker rmi -f broadcom-apigateway:2.1.1
docker rmi -f broadcom-authentication:3.1.1
docker rmi -f broadcom-broadcomapp:4.1.1
</pre>


docker-compose stop $(docker-compose ps --services | Select-String "broadcom" | ForEach-Object { $_.ToString() })

-- Remove docker images
docker rmi 467b1d4b07ae

-- Powershell
docker ps --filter "name=broadcom" --format "{{.ID}}" | ForEach-Object { docker stop $_ }

docker ps -a --filter "name=broadcom" --format "{{.ID}}" | ForEach-Object { docker rm $_ }

# Jenkins
http://localhost:8011/login?from=%2F
--

<pre>

DB insert for Auth DB:
DROP DATABASE IF EXISTS `authentication`;
CREATE DATABASE IF NOT EXISTS `authentication` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `authentication`;

-- Dumping structure for table authentication.userinfo
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE IF NOT EXISTS `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table authentication.userinfo: 0 rows
DELETE FROM `userinfo`;
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` (`id`, `email`, `name`, `password`, `roles`) VALUES
	(1, 'a@gmail.com', 'sagar', '$2a$10$zan24hGdotQG9VEOKNkQoO01Owv9GF5CIihhOGHgv5zNbF2HJrmBy', 'ROLE_ADMIN');
<pre>

Username : sagar , password : sagar