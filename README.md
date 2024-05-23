# Broad Com Application
Broad Com Application, Multi Module Spring Boot Project for learning purpose


# Server Ports : 

1. Authentication       : 7200
2. Naming Server        : 7100
3. API Gateway Server   : 7000  


<br/>
Add below to your Java application run configuration to avoid certificate issue, In VM arguments :

-Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true

To change the port of application, In VM arguments :
-Dserver.port=8021

<br/>
<p>
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
</p>

Username : sagar , password : sagar

Need to create broad com schema as well.

