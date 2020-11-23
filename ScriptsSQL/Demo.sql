-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: escalade
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `area`
--
USE ESCALADE;

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `area` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `routes_number` int NOT NULL,
  `site_id` int NOT NULL,
  `cotations` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqi8fhnmfptwx8k68xxmw2wx8a` (`site_id`),
  CONSTRAINT `FKqi8fhnmfptwx8k68xxmw2wx8a` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `sites_areas_fk` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=195 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` VALUES (161,'Secteur d\'Appy',26,160,'4c au 7b'),(163,'Secteur Droite',19,162,'4c au 7b+'),(164,'Secteur Gauche',30,162,'4c au 8a'),(166,'Le secteur principal',39,165,'5c+ à 7b+'),(167,' Le secteur Pastoufly',10,165,'6a à 8a'),(168,' Le secteur Les vires ',2,165,'6a-7b'),(169,' Le secteur Gare à vous',33,165,'5b à 8b'),(170,' Le secteur Elysée',16,165,'5c à 8a'),(173,'Franchard Isatis',500,172,'3a au 8b'),(174,'Cul de Chien',200,172,'3a au 8b'),(175,'Rocher Canon',500,172,'4b au 8b'),(176,'Bas Cuvier',500,172,'3a au 8c'),(178,'Secteur du 95.2',250,177,'3c à 7a'),(180,'Secteur Les Gros-Sablons',500,179,'3a à 7a'),(182,'Secteur Dame-Jouanne',400,181,'3b à 7a'),(184,'Secteur du Mérantais',20,183,'2b à 6a'),(194,'Secteur du Lac Bleu',11,193,'4 à 6b+');
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(600) NOT NULL,
  `user_id` int NOT NULL,
  `site_id` int NOT NULL,
  `time_stamp` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `users_comments_fk` (`user_id`),
  KEY `sites_comments_fk` (`site_id`),
  CONSTRAINT `sites_comments_fk` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `users_comments_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (186,'je suis originaire du coin et je connais tres bien cette foret, comme ma poche. En tous cas merci encore de faire découvrir notre beau coin.',158,172,'2020-11-19 01:20:22'),(187,'Je compte y aller ce week-end avec un groupe d\'amis ! Merci pour ces infos, je ferai un feedback la semaine prochaine!',158,162,'2020-11-19 01:21:40'),(188,'Mais de rien! C\'est un bel endroit qui mérite d\'être connu!',159,162,'2020-11-19 01:22:35'),(191,'J\'aime bien ce site!',190,181,'2020-11-20 16:20:56');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site`
--

DROP TABLE IF EXISTS `site`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `region` varchar(50) NOT NULL,
  `department` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `is_equipped` tinyint(1) NOT NULL,
  `is_tagged` tinyint(1) NOT NULL,
  `areas_number` int NOT NULL,
  `creator_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `users_sites_fk` (`creator_id`),
  CONSTRAINT `users_sites_fk` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=194 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site`
--

LOCK TABLES `site` WRITE;
/*!40000 ALTER TABLE `site` DISABLE KEYS */;
INSERT INTO `site` VALUES (160,'Falaise d\'APPY','Occitanie','09','Appy',1,0,1,159),(162,'Carol','Occitanie','09','Roquefort les Cascades',1,0,2,159),(165,'Site du Lordat','Occitanie','09','Lordat',1,0,5,159),(172,'Site de Fontainebleau','Ile-de-france','77','Fontainebleau',0,0,4,159),(177,'Le 95.2 (Forêt des Trois-Pignons)','Ile-de-france','77','Fontainebleau',0,0,1,159),(179,'Les Gros-Sablons (Fôret des Trois-Pignons)','Ile-de-france','77','Fontainebleau',1,0,1,159),(181,'Dame-Jouanne','Ile-de-france','77','Larchant',1,0,1,159),(183,'Mérantais','Ile-de-france','78','Magny-les-Hameaux',1,0,1,158),(193,'Lac bleu erquy','Bretagne','22','Erquy',0,0,1,189);
/*!40000 ALTER TABLE `site` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topo`
--

DROP TABLE IF EXISTS `topo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `localization` varchar(50) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `date` date NOT NULL,
  `user_id` int NOT NULL,
  `site_id` int NOT NULL,
  `reservation` enum('A','R','U','I') NOT NULL,
  PRIMARY KEY (`id`),
  KEY `users_topos_fk` (`user_id`),
  KEY `sites_topos_fk` (`site_id`),
  CONSTRAINT `sites_topos_fk` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `users_topos_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=196 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topo`
--

LOCK TABLES `topo` WRITE;
/*!40000 ALTER TABLE `topo` DISABLE KEYS */;
INSERT INTO `topo` VALUES (171,'Le Plantaurel','Roquefort les Cascades','Cette double crête calcaire marque la limite entre plaines et montagnes d\'Ariège. Au coeur de ces petites Pyrénées, pays de l\'hérésie cathare, il s\'est développé une grimpe variée et ensoleillée. Vous grimperez sur les blocs d\'Arabaux, les couennes du Carol, les dalles à l\'assaut du château de Roquefixade ou sur l\'étrave d\'une nef gigantesque venue s\'échouer dans les gorges de Péreille. Ici, escalade rime avec plaisir et paysage et ce, quel que soit votre niveau. L\'escalade ariégeoise est multiple. A proximité du Plantaurel, 1300 autres voies vous attendent. Quatre topos « Escalades en Ariège » décrivent les sites de Sinsat, d\'Auzat, de Calamès, de Génat, de Sibada... A bientôt sur les rochers ! ','2014-04-17',159,162,'A'),(185,'La falaise d’Appy – Ariège – Occitanie','Appy','La falaise d’Appy est située sur les contreforts de la vallée de la Haute-Ariège contre les pentes du massif de Tabe. À 1700 m d’altitude, elle est au soleil toute la journée et elle est fréquemment balayée dans l’après-midi par un petit courant d’air bien appréciable en été. Côté décor, le cadre est grandiose! Nous sommes plongés en pleine carte postale: estives verdoyantes, fleurs multicolores, merisiers en fleurs au printemps, sommets enneigés en fond de tableau, petit ruisseau et étang au-dessus!','2019-05-31',158,160,'A'),(195,'Erquy-Fréhel Escalade La côte de grès rose','Erquy','Erquy-Fréhel Escalade La côte de grès rose est un topo édité par CTFFME22 en 2017\r\nCe topo comprend des informations concernant 3 sites d\'Oblyk:\r\n- Lac bleu erquy\r\n- La Fosse Eyrand\r\n- Le Routin','2016-12-31',189,193,'A');
/*!40000 ALTER TABLE `topo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `last_name` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `roles` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=191 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (158,'Lisbonne','Marion','mlisbonne@gmail.com',_binary '','USER','$2a$10$V06sj42INRQ.Ny5mcB.9uOSFT88AhncMpaQkq1rhH84TjsZnEjtO6'),(159,'Marie-Sainte','Martine','mmariesainte@gmail.com',_binary '','ADMIN','$2a$10$KIykY32JRm/KmkSOm1DHIuglQfjqUN2ZZSRottJB4DpHH8GGC/KFy'),(189,'Peireira','Victor','vpeireira@gmail.com',_binary '','USER','$2a$10$FS4CWoY.AORF2l77iEH1Eeiepg37D3dbsiG/HXylSBpnAyNIJoxea'),(190,'Mouhamadouvahap','Minna','mmouhamadou@gmail.com',_binary '','USER','$2a$10$PEV3CCIPiTZeSblTemmZ/ezTmoizFB6dOornlT2YZ7Llf.OJIHGn6');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-23 22:02:31
