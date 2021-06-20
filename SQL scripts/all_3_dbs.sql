-- MySQL dump 10.16  Distrib 10.1.38-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	5.7.31

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
-- Table structure for table `address`
--
DROP DATABASE IF EXISTS `customer`;
CREATE SCHEMA `customer` ;
USE `customer` ;

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `addressId` varchar(20) NOT NULL,
  `houseNumber` varchar(45) NOT NULL,
  `addressLine1` varchar(45) NOT NULL,
  `addressLine2` varchar(45) DEFAULT NULL,
  `city` varchar(45) NOT NULL,
  `customerId` varchar(20) NOT NULL,
  PRIMARY KEY (`addressId`),
  KEY `customerId_idx` (`customerId`),
  CONSTRAINT `customerId` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contactnumber`
--

DROP TABLE IF EXISTS `contactnumber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contactnumber` (
  `phoneId` varchar(20) NOT NULL,
  `customerId` varchar(45) NOT NULL,
  `countryCode` int(2) NOT NULL,
  `phoneNumber` int(12) NOT NULL,
  PRIMARY KEY (`phoneId`),
  KEY `cstomerId_idx` (`customerId`),
  CONSTRAINT `cstomerId` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customerId` varchar(20) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `addressId` varchar(20) NOT NULL,
  `email` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `contactNumber` varchar(20) NOT NULL,
  PRIMARY KEY (`customerId`),
  UNIQUE KEY `customerId_UNIQUE` (`customerId`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP DATABASE IF EXISTS `shopping_cart`;
CREATE SCHEMA `shopping_cart` ;
USE `shopping_cart` ;

DROP TABLE IF EXISTS `orderline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderline` (
  `orderLineId` varchar(20) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `discount` decimal(10,2) DEFAULT NULL,
  `shoppingCartId` varchar(20) NOT NULL,
  `productId` varchar(20) NOT NULL,
  `quantity` decimal(10,3) NOT NULL,
  PRIMARY KEY (`orderLineId`),
  KEY `fk_OrderLine_ShoppingCart1_idx` (`shoppingCartId`),
  KEY `fk_OrderLine_Product1_idx` (`productId`),
  CONSTRAINT `fk_OrderLine_Product1` FOREIGN KEY (`productId`) REFERENCES `product` (`productId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_OrderLine_ShoppingCart1` FOREIGN KEY (`shoppingCartId`) REFERENCES `shoppingcart` (`shoppingCartId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `productId` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `unitPrice` decimal(10,2) NOT NULL,
  `available` decimal(15,2) NOT NULL DEFAULT '0.00',
  `sold` decimal(15,2) NOT NULL DEFAULT '0.00',
  `reserved` decimal(15,2) NOT NULL DEFAULT '0.00',
  `isUnit` tinyint(1) NOT NULL DEFAULT '1',
  `tax` decimal(4,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`productId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('1234','Shampoo',200.00,100.00,1000.00,10.00,1,2.00),('152632','Soap',50.00,1000.00,100000.00,5.00,1,3.00),('FISH11134','Sudaya',160.00,100.00,7695.00,0.00,0,4.00),('FISH12345','Tuna',1300.00,4.00,510.00,0.00,0,3.00),('FRU12345','Mango',90.00,70.00,560.00,0.00,0,1.00),('VEG12344','Ccumber',48.00,45.00,1000.00,1.00,0,0.00);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoppingcart`
--

DROP TABLE IF EXISTS `shoppingcart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shoppingcart` (
  `shoppingCartId` varchar(20) NOT NULL,
  `customerId` varchar(20) NOT NULL,
  `isPrimary` int(1) NOT NULL,
  `numberOfItems` int(11) NOT NULL,
  `cartValue` decimal(10,2) NOT NULL,
  PRIMARY KEY (`shoppingCartId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



DROP DATABASE IF EXISTS `payments`;
CREATE SCHEMA `payments` ;
USE `payments` ;

DROP TABLE IF EXISTS `orderline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderline` (
  `orderLineId` varchar(20) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `discount` decimal(10,2) DEFAULT NULL,
  `quantity` decimal(10,3) NOT NULL,
  `productDescription` varchar(255) NOT NULL,
  `orderId` varchar(20) NOT NULL,
  PRIMARY KEY (`orderLineId`),
  KEY `Order_idx` (`orderId`),
  CONSTRAINT `Order` FOREIGN KEY (`orderId`) REFERENCES `orders` (`orderId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `orderId` varchar(20) NOT NULL,
  `orderDate` date NOT NULL,
  `deliverDate` date DEFAULT NULL,
  `orderValue` decimal(15,2) NOT NULL,
  `discountTotal` decimal(15,2) DEFAULT NULL,
  `orderStatus` int(11) NOT NULL,
  `customerId` varchar(20) NOT NULL,
  `totalTax` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `paymentId` varchar(20) NOT NULL,
  `paymentDate` date NOT NULL,
  `paymentAmount` decimal(15,2) NOT NULL,
  `details` varchar(255) DEFAULT NULL,
  `paymentType` int(11) NOT NULL,
  `orderId` varchar(20) NOT NULL,
  `customerId` varchar(20) NOT NULL,
  PRIMARY KEY (`paymentId`),
  KEY `fk_payment_Order1_idx` (`orderId`),
  CONSTRAINT `fk_payment_Order1` FOREIGN KEY (`orderId`) REFERENCES `orders` (`orderId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-20 23:50:02
