-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: bootcamp15db
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_of_birth` date DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `nationality` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'1890-09-15','Agatha','Christie','British'),(2,'1859-05-22','Arthur','Conan Doyle','British'),(3,'1899-07-21','Ernest','Hemingway','American'),(4,'1913-11-07','Albert','Camus','French'),(5,'1883-07-03','Franz','Kafka','Austrian'),(6,'1882-01-25','Virginia','Woolf','British'),(7,'1976-02-24','Yuval Noah','Harari','Israeli');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` text,
  `image_url` varchar(255) DEFAULT NULL,
  `stocks` int NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `year_puplished` date DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  `author_id` bigint DEFAULT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKam9riv8y6rjwkua1gapdfew4j` (`category_id`),
  KEY `FKklnrv3weler2ftkweewlky958` (`author_id`),
  CONSTRAINT `FKam9riv8y6rjwkua1gapdfew4j` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKklnrv3weler2ftkweewlky958` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'A Hercule Poirot mystery where a killer strikes in alphabetical order across England.','abcmurders.jpg',9,'The A.B.C. Murders','1936-01-01',1,1,15),(2,'A Hercule Poirot mystery set aboard a Nile steamer, where murder disrupts a honeymoon and secrets unfold.','deathonnile.jpg',11,'Death on the Nile','1937-01-01',1,1,20),(3,'Sherlock Holmes investigates the eerie legend of a ghostly hound terrorizing the Baskerville family.','houndbaskervilles.jpg',19,'The Hound of the Baskervilles','1902-01-01',1,2,12),(4,'A collection of twelve classic Sherlock Holmes stories, first published in 1892.','adventuressherlock.jpg',15,'The Adventures of Sherlock Holmes','1892-01-01',1,2,11),(5,'Hemingway’s non-fiction exploration of Spanish bullfighting, published in 1932.','deathintheafternoon.jpg',8,'Death in the Afternoon','1932-01-01',2,3,6),(6,'Hemingway’s World War I novel about love and loss, first published in 1929.','farewelltoarms.jpg',14,'A Farewell to Arms','1929-01-01',2,3,10),(7,'Camus’s existentialist novel about Meursault and the absurdity of life, first published in 1942.','thestranger.jpg',11,'The Stranger','1942-01-01',2,4,12),(8,'Camus’s allegorical novel about a plague in Oran, exploring human resilience and the absurd.','plague.jpg',7,'The Plague','1947-01-01',2,4,30),(9,'Kafka’s unfinished novel about K.’s futile attempts to gain access to the mysterious Castle authorities.','thecastle.jpg',8,'The Castle','1926-01-01',2,5,20),(10,'Kafka’s novella about Gregor Samsa’s transformation and the themes of alienation and identity.','metamorphosis.jpg',9,'The Metamorphosis','1915-01-01',2,5,25),(11,'Virginia Woolf’s modernist novel told through soliloquies, exploring identity, time, and consciousness.','thewaves.jpg',5,'The Waves','1931-01-01',2,6,12),(12,'Virginia Woolf’s 1925 essay collection exploring literature and the perspective of the everyday reader.','commonreader.jpg',5,'The Common Reader','1925-01-01',2,6,11),(13,'Harari’s sweeping history of humankind, from the Stone Age to the modern era.','sapiens.jpg',18,'Sapiens: A Brief History of Humankind','2011-01-01',3,7,15);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Detective'),(2,'Novel'),(3,'History');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-29 10:29:44
