CREATE DATABASE  IF NOT EXISTS `vissa` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `vissa`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: vissa
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.30-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bank`
--

DROP TABLE IF EXISTS `bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bank` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `archived` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank`
--

LOCK TABLES `bank` WRITE;
/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
INSERT INTO `bank` VALUES (1,'Bancolombia','2019-01-08 21:44:45','2019-01-08 21:44:45','\0'),(2,'Banco de Bogotá','2019-01-08 21:44:56','2019-01-08 21:44:56','\0'),(3,'Av Villas','2019-01-08 21:45:12','2019-01-08 21:45:14','\0'),(4,'Banco de Occidente','2019-01-08 21:45:32','2019-01-08 21:45:32','\0'),(5,'Banco Popular','2019-01-08 21:45:47','2019-01-08 21:45:47','\0'),(6,'Davivienda','2019-01-08 21:46:00','2019-01-08 21:46:00','\0');
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_account`
--

DROP TABLE IF EXISTS `bank_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bank_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_number` varchar(50) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `bank_id` bigint(20) DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `archived` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `bank_account_bank_idx` (`bank_id`),
  CONSTRAINT `bank_account_bank` FOREIGN KEY (`bank_id`) REFERENCES `bank` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_account`
--

LOCK TABLES `bank_account` WRITE;
/*!40000 ALTER TABLE `bank_account` DISABLE KEYS */;
INSERT INTO `bank_account` VALUES (1,'1234-5678-9012','SAVING',1,'','2018-11-28 16:00:56','2018-11-28 16:00:56','\0'),(2,'220','SAVING',1,'ACIVE','2019-01-13 23:08:39','2019-01-13 23:08:39','\0');
/*!40000 ALTER TABLE `bank_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `state_id` bigint(20) NOT NULL,
  `name` varchar(250) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `archived` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `state_fk_idx` (`state_id`),
  CONSTRAINT `city_state_fk` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,1,'Barranquilla','2018-12-04 21:22:09','2018-12-04 21:22:09','\0'),(2,1,'Soledad','2019-01-08 22:05:40','2019-01-08 22:05:47','\0'),(3,2,'Santa Marta','2019-01-08 22:06:01','2019-01-08 22:06:01','\0'),(4,2,'Ciénaga','2019-01-08 22:06:13','2019-01-08 22:06:13','\0');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `city_id` bigint(20) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `web_site` varchar(250) DEFAULT NULL,
  `type` char(1) NOT NULL,
  `person_id` bigint(20) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `archived` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `contact_person_fk_idx` (`person_id`),
  KEY `contact_city_idx` (`city_id`),
  CONSTRAINT `contact_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
  CONSTRAINT `contact_person_fk` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `archived` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Colombia','2018-12-04 21:19:23','2018-12-04 21:19:23','\0'),(2,'Venezuela','2019-01-08 22:04:49','2019-01-08 22:04:49','\0');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency`
--

DROP TABLE IF EXISTS `currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currency` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `archived` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency`
--

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` VALUES (1,'COP','Pesos',NULL,'2018-12-11 22:45:49','\0');
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `reference` varchar(45) DEFAULT NULL,
  `type_id` bigint(20) NOT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  `document_date` timestamp NULL DEFAULT NULL,
  `payment_type_id` bigint(20) DEFAULT NULL,
  `payment_method_id` bigint(20) DEFAULT NULL,
  `payment_term` varchar(45) DEFAULT NULL,
  `expiration_date` timestamp NULL DEFAULT NULL,
  `currency_id` bigint(20) DEFAULT NULL,
  `total_value_no_tax` decimal(16,2) DEFAULT NULL,
  `total_value` decimal(16,2) DEFAULT NULL,
  `status_id` bigint(20) NOT NULL DEFAULT '1',
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `archived` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `document_number_UQ` (`code`),
  KEY `document_document_type_idx` (`type_id`),
  KEY `document_person_idx` (`person_id`),
  KEY `document_payment_type_idx` (`payment_type_id`),
  KEY `document_payment_method_idx` (`payment_method_id`),
  KEY `document_currency_idx` (`currency_id`),
  KEY `document_status` (`status_id`),
  CONSTRAINT `document_currency` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`id`),
  CONSTRAINT `document_document_type` FOREIGN KEY (`type_id`) REFERENCES `document_type` (`id`),
  CONSTRAINT `document_payment_method` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_method` (`id`),
  CONSTRAINT `document_payment_type` FOREIGN KEY (`payment_type_id`) REFERENCES `payment_type` (`id`),
  CONSTRAINT `document_person` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `document_status` FOREIGN KEY (`status_id`) REFERENCES `document_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
INSERT INTO `document` VALUES (39,'CO-39','56485',1,6,'2019-01-04 05:00:00',1,1,'10',NULL,NULL,0.00,0.00,1,'2019-01-04 08:41:08','2019-01-04 08:41:08','\0'),(40,'CO-40','7878',1,6,'2019-01-04 05:00:00',1,1,'10',NULL,NULL,0.00,0.00,2,'2019-01-04 08:41:14','2019-01-04 08:41:14','\0'),(41,'VE-41','24234',2,6,'2019-01-04 05:00:00',1,1,'10','2019-01-14 05:00:00',NULL,0.00,0.00,2,'2019-01-04 09:27:32','2019-01-04 09:27:32','\0'),(42,'CO-42','4454',1,6,'2019-01-04 05:00:00',1,1,'8','2019-01-12 05:00:00',NULL,0.00,0.00,2,'2019-01-04 10:46:23','2019-01-04 10:46:23','\0'),(43,'VE-43','',2,7,'2019-01-04 05:00:00',1,1,'10','2019-01-14 05:00:00',NULL,0.00,0.00,2,'2019-01-04 10:52:10','2019-01-04 10:52:10','\0'),(44,'CO-44','4654',1,6,'2019-01-10 10:00:00',1,1,'10','2019-01-20 10:00:00',NULL,0.00,0.00,2,'2019-01-11 03:20:50','2019-01-11 03:20:50','\0'),(45,'CO-45','65415',1,6,'2019-01-10 10:00:00',1,1,'10','2019-01-20 10:00:00',NULL,0.00,0.00,2,'2019-01-11 03:24:46','2019-01-11 03:24:46','\0'),(46,'CO-46','uiu',1,6,'2019-01-25 22:32:03',1,1,'10','2019-02-04 22:32:03',NULL,0.00,0.00,2,'2019-01-14 00:33:28','2019-01-14 00:33:28','\0');
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document_detail`
--

DROP TABLE IF EXISTS `document_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `document_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `sub_total` decimal(16,2) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `stock_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `archived` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `document_detail_document_idx` (`document_id`),
  KEY `document_product_idx` (`product_id`),
  CONSTRAINT `document_detail_document` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`),
  CONSTRAINT `document_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_detail`
--

LOCK TABLES `document_detail` WRITE;
/*!40000 ALTER TABLE `document_detail` DISABLE KEYS */;
INSERT INTO `document_detail` VALUES (2,39,3,NULL,1,5000.00,NULL,'2019-01-04 02:32:03','2019-01-04 02:32:03','2019-01-04 02:32:03','\0'),(3,40,10,NULL,1,1000.00,NULL,'2019-01-04 08:40:03','2019-01-04 08:40:03','2019-01-04 08:40:03','\0'),(4,41,8,NULL,1,7500.00,NULL,'2019-01-04 09:27:32','2019-01-04 09:27:32','2019-01-04 09:27:32','\0'),(5,42,4,NULL,1,4700.00,NULL,'2019-01-04 10:46:23','2019-01-04 10:46:23','2019-01-04 10:46:23','\0'),(6,43,3,NULL,2,10000.00,NULL,'2019-01-04 10:52:10','2019-01-04 10:52:10','2019-01-04 10:52:10','\0'),(7,44,3,NULL,2,10000.00,NULL,'2019-01-11 03:20:50','2019-01-11 03:20:50','2019-01-11 03:20:50','\0'),(8,45,3,NULL,2,10000.00,NULL,'2019-01-11 03:24:46','2019-01-11 03:24:46','2019-01-11 03:24:46','\0'),(9,46,3,NULL,1,5000.00,NULL,'2019-01-14 00:33:28','2019-01-14 00:33:28','2019-01-14 00:33:28','\0');
/*!40000 ALTER TABLE `document_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document_status`
--

DROP TABLE IF EXISTS `document_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `document_type_id` bigint(20) DEFAULT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `archived` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `status_document_type` (`document_type_id`),
  CONSTRAINT `status_document_type` FOREIGN KEY (`document_type_id`) REFERENCES `document_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_status`
--

LOCK TABLES `document_status` WRITE;
/*!40000 ALTER TABLE `document_status` DISABLE KEYS */;
INSERT INTO `document_status` VALUES (1,'Nueva',1,'2019-01-04 00:45:23','2019-01-04 00:45:23','\0'),(2,'Registrada',1,'2019-01-04 08:38:19','2019-01-04 08:38:19','\0'),(3,'Cancelada',1,'2019-01-04 00:45:29','2019-01-04 00:45:29','\0'),(4,'Vencida',1,'2019-01-04 00:45:32','2019-01-04 00:45:32','\0'),(5,'Rechazada',1,'2019-01-04 00:45:56','2019-01-04 00:45:56','\0');
/*!40000 ALTER TABLE `document_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document_type`
--

DROP TABLE IF EXISTS `document_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `transaction_type` varchar(45) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `archived` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_type`
--

LOCK TABLES `document_type` WRITE;
/*!40000 ALTER TABLE `document_type` DISABLE KEYS */;
INSERT INTO `document_type` VALUES (1,'CO','Factura de compra','ENTRADA','2019-01-02 23:39:46','2019-01-02 23:39:46','\0'),(2,'VE','Factura de venta','SALIDA','2019-01-02 23:39:52','2019-01-02 23:39:52','\0'),(3,'RE','Remision','ENTRADA','2019-01-02 23:39:58','2019-01-02 23:39:58','\0'),(4,'FV','Factura de venta','SALIDA','2019-01-02 23:40:14','2019-01-02 23:40:14','');
/*!40000 ALTER TABLE `document_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory_transaction`
--

DROP TABLE IF EXISTS `inventory_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory_transaction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL,
  `transaction_type` varchar(45) NOT NULL,
  `document_id` bigint(20) DEFAULT NULL,
  `initial_stock` int(10) DEFAULT NULL,
  `final_stock` int(10) DEFAULT NULL,
  `quantity` int(10) DEFAULT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `archived` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `product_fk_idx` (`product_id`),
  KEY `document_fk_idx` (`document_id`),
  CONSTRAINT `inventory_document` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`),
  CONSTRAINT `inventory_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory_transaction`
--

LOCK TABLES `inventory_transaction` WRITE;
/*!40000 ALTER TABLE `inventory_transaction` DISABLE KEYS */;
INSERT INTO `inventory_transaction` VALUES (1,3,'ENTRADA',39,525,524,1,'2019-01-04 02:32:03','2019-01-04 02:32:03','\0'),(2,10,'ENTRADA',40,222,221,1,'2019-01-04 08:40:03','2019-01-04 08:40:03','\0'),(3,8,'SALIDA',41,220,219,1,'2019-01-04 09:27:32','2019-01-04 09:27:32','\0'),(4,4,'ENTRADA',42,291,290,1,'2019-01-04 10:46:23','2019-01-04 10:46:23','\0'),(5,3,'SALIDA',43,524,522,2,'2019-01-04 10:52:10','2019-01-04 10:52:10','\0'),(6,3,'ENTRADA',45,522,520,2,'2019-01-11 03:24:46','2019-01-11 03:24:46','\0'),(7,3,'ENTRADA',46,520,519,1,'2019-01-14 00:33:28','2019-01-14 00:33:28','\0');
/*!40000 ALTER TABLE `inventory_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lot`
--

DROP TABLE IF EXISTS `lot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lot` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `lot_date` timestamp NULL DEFAULT NULL,
  `expiration_date` timestamp NULL DEFAULT NULL,
  `product_id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `archived` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `lot_product_idx` (`product_id`),
  CONSTRAINT `lot_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lot`
--

LOCK TABLES `lot` WRITE;
/*!40000 ALTER TABLE `lot` DISABLE KEYS */;
INSERT INTO `lot` VALUES (1,'TEST',NULL,NULL,NULL,1,1,'2018-12-21 11:57:33','2018-12-21 11:57:33','\0'),(2,'LOT1','Lote1','2019-01-04 05:00:00','2019-01-31 05:00:00',8,1,'2019-01-04 09:28:09','2019-01-04 09:28:09','\0');
/*!40000 ALTER TABLE `lot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `measurement_unit`
--

DROP TABLE IF EXISTS `measurement_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `measurement_unit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `archived` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `measurement_unit`
--

LOCK TABLES `measurement_unit` WRITE;
/*!40000 ALTER TABLE `measurement_unit` DISABLE KEYS */;
INSERT INTO `measurement_unit` VALUES (1,'Kg','2019-01-03 13:11:08','2019-01-03 13:11:08','\0'),(2,'Libra','2019-01-03 13:11:29','2019-01-03 13:11:29','\0'),(3,'Bolsa','2019-01-03 13:12:03','2019-01-03 13:12:05','\0');
/*!40000 ALTER TABLE `measurement_unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_method`
--

DROP TABLE IF EXISTS `payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_method` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `archived` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_method`
--

LOCK TABLES `payment_method` WRITE;
/*!40000 ALTER TABLE `payment_method` DISABLE KEYS */;
INSERT INTO `payment_method` VALUES (1,'CASH','Efectivo','2018-12-12 10:37:15','2018-12-12 10:37:15','\0'),(2,'BANK_TRANSFER','Transferencia bancaria','2018-12-12 10:37:16','2018-12-12 10:37:16','\0'),(3,'BANK_DEPOSIT','Consignación bancaria','2018-12-12 10:37:16','2018-12-12 10:37:16','\0'),(4,'ONLINE','Pago en línea','2018-12-12 10:37:16','2018-12-12 10:37:16','\0');
/*!40000 ALTER TABLE `payment_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_type`
--

DROP TABLE IF EXISTS `payment_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `archived` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_type`
--

LOCK TABLES `payment_type` WRITE;
/*!40000 ALTER TABLE `payment_type` DISABLE KEYS */;
INSERT INTO `payment_type` VALUES (1,'1\r\n','Pago inmediato','2018-12-31 17:25:38','2018-12-31 17:25:38','\0');
/*!40000 ALTER TABLE `payment_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `document_type` varchar(45) NOT NULL,
  `document_number` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `type` varchar(45) NOT NULL,
  `contact_name` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT '',
  `city_id` bigint(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT '',
  `phone` varchar(20) DEFAULT '',
  `email` varchar(50) DEFAULT '',
  `web_site` varchar(250) DEFAULT '',
  `bank_account_id` bigint(20) DEFAULT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `archived` bit(1) NOT NULL,
  PRIMARY KEY (`id`,`archived`),
  UNIQUE KEY `identification_number_UNIQUE` (`document_number`),
  KEY `person_city` (`city_id`),
  KEY `person_bank_account_idx` (`bank_account_id`),
  CONSTRAINT `person_bank_account` FOREIGN KEY (`bank_account_id`) REFERENCES `bank_account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `person_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='Tabla para el almacenamiento de clientes, proveedores, terceros o cualquier tipo de persona';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (6,'CC','09876','Lina Maria','Florez Mejia','SUPPLIER','Lina Maria Flórez Mejia','Calle 75C # 114 -18',1,'3002007694','','','ab',2,'2019-01-13 23:13:41','2019-01-13 23:13:41',''),(7,'CC','104567608','LINAf','F','SUPPLIER','TEST','TEST',2,'3000','21','55','www.ee',NULL,'2019-01-13 23:13:52','2019-01-13 23:13:52','\0'),(14,'CC','14579','Other','Test','USER',NULL,'',1,'','','','',NULL,'2019-01-08 21:06:29','2019-01-08 21:06:29',''),(20,'CC','145795','Alex','Quiroz','USER',NULL,'',1,'','','','',NULL,'2019-01-08 21:06:29','2019-01-08 21:06:29','\0'),(21,'CC','123','Eider','Samia','USER',NULL,'',1,'','','','',NULL,'2019-01-08 21:06:29','2019-01-08 21:06:29','\0'),(22,'CC','1234','Diana','Carbo','USER',NULL,'',1,'','','','',NULL,'2019-01-08 21:06:29','2019-01-08 21:06:29','\0'),(23,'CC','1234567','Jhon','Castellanos','USER',NULL,'',1,'','','','',NULL,'2019-01-08 21:06:29','2019-01-08 21:06:29','\0'),(24,'CC','104567','dummy','dummy','SUPPLIER','','',1,'','','','www.misitio',NULL,'2019-01-13 23:43:09','2019-01-13 23:43:09','\0');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  `measurement_unit_id` bigint(20) DEFAULT NULL,
  `ean_code` varchar(45) DEFAULT NULL,
  `sale_price` decimal(16,2) DEFAULT NULL,
  `purchase_price` decimal(16,2) DEFAULT NULL,
  `sale_tax` decimal(16,2) DEFAULT NULL,
  `purchase_tax` decimal(16,2) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `stock_date` timestamp NULL DEFAULT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `archived` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `category_id_idx` (`category_id`),
  KEY `product_type_fk_idx` (`type_id`),
  KEY `measurement_unit_fk_idx` (`measurement_unit_id`),
  CONSTRAINT `category_fk` FOREIGN KEY (`category_id`) REFERENCES `product_category` (`id`),
  CONSTRAINT `measurement_unit_fk` FOREIGN KEY (`measurement_unit_id`) REFERENCES `measurement_unit` (`id`),
  CONSTRAINT `product_type_fk` FOREIGN KEY (`type_id`) REFERENCES `product_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'1','ACEITE CELEC','ACEITE CELEC',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,470,'2019-01-04 04:46:47','2019-01-03 23:46:57','2019-01-03 23:46:57','\0'),(2,'2','ACEITE VEGETAL','ACEITE VEGETAL',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,463,'2019-01-04 04:48:22','2019-01-03 23:48:27','2019-01-03 23:48:27','\0'),(3,'3','ADORNO FUTBOL','ADORNO FUTBOL',NULL,NULL,NULL,'',5000.00,NULL,NULL,NULL,519,'2019-01-14 05:33:28','2019-01-14 00:33:28','2019-01-14 00:33:28','\0'),(4,'4','AGOGO CAJAS','AGOGO CAJAS',NULL,NULL,NULL,'',4700.00,NULL,NULL,NULL,290,'2019-01-04 15:46:23','2019-01-04 10:46:23','2019-01-04 10:46:23','\0'),(5,'5','AGOGO PAQ','AGOGO PAQ',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,296,'2019-01-04 04:49:31','2019-01-03 23:49:32','2019-01-03 23:49:32','\0'),(7,'6','AGOGO UND','AGOGO UND',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,297,'2019-01-04 04:49:52','2019-01-03 23:49:54','2019-01-03 23:49:54','\0'),(8,'8','AJI PICANTE JR 2000 GR','AJI PICANTE JR 2000 GR',NULL,NULL,NULL,'',7500.00,NULL,NULL,NULL,219,'2019-01-04 14:27:32','2019-01-04 09:27:32','2019-01-04 09:27:32','\0'),(9,'9','AJI PICANTE JR 370','AJI PICANTE JR 370',NULL,NULL,NULL,'',1800.00,NULL,NULL,NULL,221,'2019-01-04 04:50:30','2019-01-03 23:50:32','2019-01-03 23:50:32','\0'),(10,'10','AJI PICANTE JR PEQUEÑO 180','AJI PICANTE JR PEQUEÑO 180',NULL,NULL,NULL,'',1000.00,NULL,NULL,NULL,221,'2019-01-04 13:40:03','2019-01-04 08:40:03','2019-01-04 08:40:03','\0');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `archived` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (1,'Lácteos','Lácteos','2019-01-03 12:27:04','2019-01-03 12:27:04','\0'),(2,'Embutidos','Embutidos','2019-01-03 12:26:43','2019-01-03 12:26:43','\0'),(3,'Aceites','Aceites','2019-01-03 12:27:26','2019-01-03 12:27:27','\0');
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_type`
--

DROP TABLE IF EXISTS `product_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `archived` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_type`
--

LOCK TABLES `product_type` WRITE;
/*!40000 ALTER TABLE `product_type` DISABLE KEYS */;
INSERT INTO `product_type` VALUES (1,'Type Test','2018-12-05 20:26:54','0000-00-00 00:00:00','\0');
/*!40000 ALTER TABLE `product_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `state` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `country_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `archived` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `country_state_fk_idx` (`country_id`),
  CONSTRAINT `country_state_fk` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state`
--

LOCK TABLES `state` WRITE;
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
INSERT INTO `state` VALUES (1,1,'Atlantico','2018-12-04 21:19:24','2018-12-04 21:19:24','\0'),(2,1,'Magdalena','2019-01-08 22:05:18','2019-01-08 22:05:20','\0');
/*!40000 ALTER TABLE `state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `person_id` bigint(20) NOT NULL,
  `payment_type_id` bigint(20) DEFAULT NULL,
  `payment_term` varchar(45) DEFAULT NULL,
  `payment_method_id` bigint(20) DEFAULT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `archived` bit(1) NOT NULL DEFAULT b'0',
  `account_bank` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `supplier_person_fk_idx` (`person_id`),
  KEY `supplier_payment_type_fk_idx` (`payment_type_id`),
  KEY `supplier_payment_method_idx` (`payment_method_id`),
  CONSTRAINT `supplier_payment_method` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_method` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `supplier_payment_type` FOREIGN KEY (`payment_type_id`) REFERENCES `payment_type` (`id`),
  CONSTRAINT `supplier_person` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (2,6,1,'61',1,'2019-01-13 23:42:41','2019-01-13 23:42:41','\0',NULL),(3,7,NULL,'',NULL,'2019-01-13 23:13:52','2019-01-13 23:13:52','\0',NULL),(4,24,1,'',2,'2019-01-13 23:43:09','2019-01-13 23:43:09','\0',NULL);
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(60) NOT NULL,
  `pass` varchar(60) NOT NULL,
  `person_id` bigint(20) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `archived` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `fk_user_person_idx` (`person_id`),
  CONSTRAINT `fk_user_person` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,'test','pnbrmR5/2yU1paLqlMjzRg==',14,'2018-12-26 22:09:16','2018-12-26 22:09:16','\0'),(5,'eider.samia','XPpIEcZPlvcB+yP+qHNC9w==',21,'2019-01-03 00:48:27','2019-01-03 00:48:27','\0'),(6,'alex.quiroz','XPpIEcZPlvcB+yP+qHNC9w==',20,'2019-01-03 00:49:58','2019-01-03 00:49:58','\0'),(8,'diana.carbo','XPpIEcZPlvcB+yP+qHNC9w==',22,'2019-01-03 00:49:19','2019-01-03 00:49:19','\0'),(9,'jhon.castellanos','XPpIEcZPlvcB+yP+qHNC9w==',23,'2019-01-03 00:50:46','2019-01-03 00:50:46','\0');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'vissa'
--

--
-- Dumping routines for database 'vissa'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-13 21:34:35
