

-- Dumping database structure for authentication
DROP DATABASE IF EXISTS `authentication`;
CREATE DATABASE IF NOT EXISTS `authentication` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `authentication`;

DROP TABLE IF EXISTS `UserInfo`;
CREATE TABLE IF NOT EXISTS `UserInfo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

DELETE FROM `userinfo` WHERE `id`=1;
INSERT INTO `userinfo` (`id`, `email`, `name`, `password`, `roles`) VALUES (1, 'a@gmail.com', 'sagar', '$2a$10$zan24hGdotQG9VEOKNkQoO01Owv9GF5CIihhOGHgv5zNbF2HJrmBy', 'ROLE_ADMIN');
DELETE FROM `userinfo` WHERE `id`=2;
INSERT INTO `userinfo` (`id`, `email`, `name`, `password`, `roles`) VALUES (2, 'z@gmail.com', 'mahesh', '$2a$10$YyGGgc2V6u0CBoe1ir51T.bOBca57yOMSDGWZ8NAM0oBPJUQlTMr.', 'ROLE_USER');
DELETE FROM `userinfo` WHERE `id`=3;
INSERT INTO `userinfo` (`id`, `email`, `name`, `password`, `roles`) VALUES (3, 'ramesh@gmail.com', 'ramesh', '$2a$10$YiLkZTb6H8GyBRqWxtAIbu9aLIIIlWXK4XXlQJYF291LUfVDrhRPK', 'ROLE_ADMIN');
DELETE FROM `userinfo` WHERE `id`=4;
INSERT INTO `userinfo` (`id`, `email`, `name`, `password`, `roles`) VALUES (4, 'ramesh@gmail.com', 'ramesh', '$2a$10$L8FVP7BqQVKf/pYC3iVbgeD4enOUbc11xzlQtpunGaCakcHpRaT/O', 'ROLE_READ_ONLY');
