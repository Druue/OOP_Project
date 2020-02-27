-- MySQL dump 10.13  Distrib 8.0.15, for macos10.14 (x86_64)
--
-- Host: localhost    Database: database_OOPP
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `AvailableTimeSlots`
--

DROP TABLE IF EXISTS `AvailableTimeSlots`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `AvailableTimeSlots` (
  `reservableID` int NOT NULL,
  `ID` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `time` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`reservableID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AvailableTimeSlots`
--

LOCK TABLES `AvailableTimeSlots` WRITE;
/*!40000 ALTER TABLE `AvailableTimeSlots` DISABLE KEYS */;
/*!40000 ALTER TABLE `AvailableTimeSlots` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Bike`
--

DROP TABLE IF EXISTS `Bike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Bike` (
  `reservableID` int NOT NULL,
  `isAvailable` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `buildingID` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`reservableID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bike`
--

LOCK TABLES `Bike` WRITE;
/*!40000 ALTER TABLE `Bike` DISABLE KEYS */;
/*!40000 ALTER TABLE `Bike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Building`
--

DROP TABLE IF EXISTS `Building`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Building` (
  `buildingID` int NOT NULL,
  `name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `opening_hours` datetime DEFAULT NULL,
  `hasFoodCourt` boolean DEFAULT FALSE,
  `available_time_slots` datetime DEFAULT NULL,
  PRIMARY KEY (`buildingID`),
  CONSTRAINT `buildingID` FOREIGN KEY (`buildingID`) REFERENCES `Reservables` (`reservableID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Building`
--

LOCK TABLES `Building` WRITE;
/*!40000 ALTER TABLE `Building` DISABLE KEYS */;
/*!40000 ALTER TABLE `Building` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Foodcourt`
--

DROP TABLE IF EXISTS `Foodcourt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Foodcourt` (
  `Foodcourt_ID` int DEFAULT NULL,
  `food_list` int NOT NULL,
  `building_number` int DEFAULT NULL,
  PRIMARY KEY (`FoodcourtID`),
  KEY `building_number_idx` (`building_number`),
  CONSTRAINT `building_number` FOREIGN KEY (`building_number`) REFERENCES `Building` (`buildingID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Foodcourt`
--

LOCK TABLES `Foodcourt` WRITE;
/*!40000 ALTER TABLE `Foodcourt` DISABLE KEYS */;
/*!40000 ALTER TABLE `Foodcourt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FoodReservation`
--

DROP TABLE IF EXISTS `FoodReservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `FoodReservation` (
  `FoodReservationID` int NOT NULL,
  `userID` int NOT NULL,
  `reservationID` int DEFAULT NULL,
  PRIMARY KEY (`FoodReservationID`),
  KEY `reservationID_idx` (`reservationID`),
  CONSTRAINT `reservationID` FOREIGN KEY (`reservationID`) REFERENCES `Reservation` (`reservationID`),
  CONSTRAINT `userID` FOREIGN KEY (`userID`) REFERENCES `User` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FoodReservation`
--

LOCK TABLES `FoodReservation` WRITE;
/*!40000 ALTER TABLE `FoodReservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `FoodReservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reservable`
--

DROP TABLE IF EXISTS `Reservable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Reservable` (
  `idReservable` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `isAvailable` boolean DEFAULT TRUE,
  `buildingId` int DEFAULT NULL,
  PRIMARY KEY (`idReservable`),
  UNIQUE KEY `idReservable_UNIQUE` (`idReservable`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reservable`
--

LOCK TABLES `Reservable` WRITE;
/*!40000 ALTER TABLE `Reservable` DISABLE KEYS */;
/*!40000 ALTER TABLE `Reservable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reservables`
--

DROP TABLE IF EXISTS `Reservables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Reservables` (
  `reservableID` int NOT NULL,
  `buildingID` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`reservableID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reservables`
--

LOCK TABLES `Reservables` WRITE;
/*!40000 ALTER TABLE `Reservables` DISABLE KEYS */;
/*!40000 ALTER TABLE `Reservables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reservation`
--

DROP TABLE IF EXISTS `Reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Reservation` (
  `reservationID` int NOT NULL,
  `timeslotID` int DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `reservableID` int DEFAULT NULL,
  PRIMARY KEY (`reservationID`),
  KEY `reservableID_idx` (`reservableID`),
  CONSTRAINT `reservableID` FOREIGN KEY (`reservableID`) REFERENCES `Reservable` (`idReservable`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reservation`
--

LOCK TABLES `Reservation` WRITE;
/*!40000 ALTER TABLE `Reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `Reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Room`
--

DROP TABLE IF EXISTS `Room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Room` (
  `reservableID` int NOT NULL,
  `isAvailable` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `buildingID` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `isForEmployee` int NOT NULL,
  PRIMARY KEY (`reservableID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Room`
--

LOCK TABLES `Room` WRITE;
/*!40000 ALTER TABLE `Room` DISABLE KEYS */;
/*!40000 ALTER TABLE `Room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `User` (
  `userID` varchar(45) COLLATE utf8_bin NOT NULL,
  `name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserReservations`
--

DROP TABLE IF EXISTS `UserReservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `UserReservations` (
  `reservationID` int NOT NULL,
  `userID` int DEFAULT NULL,
  PRIMARY KEY (`reservationID`),
  KEY `userID_idx` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserReservations`
--

LOCK TABLES `UserReservations` WRITE;
/*!40000 ALTER TABLE `UserReservations` DISABLE KEYS */;
/*!40000 ALTER TABLE `UserReservations` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-27 21:11:50
