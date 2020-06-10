-- MySQL dump 10.17  Distrib 10.3.22-MariaDB, for debian-linux-gnueabihf (armv8l)
--
-- Host: localhost    Database: Efake
-- ------------------------------------------------------
-- Server version	10.3.22-MariaDB-0+deb10u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `CATEGORIA`
--

DROP TABLE IF EXISTS `CATEGORIA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CATEGORIA` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CATEGORIA`
--

LOCK TABLES `CATEGORIA` WRITE;
/*!40000 ALTER TABLE `CATEGORIA` DISABLE KEYS */;
INSERT INTO `CATEGORIA` VALUES (1,'Electronics'),(2,'House'),(3,'Fashion'),(4,'Sports'),(5,'Motors'),(7,'Collectibles'),(8,'Toys'),(9,'Entertainment'),(10,'Books');
/*!40000 ALTER TABLE `CATEGORIA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `KEYWORDS`
--

DROP TABLE IF EXISTS `KEYWORDS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `KEYWORDS` (
  `Palabra` varchar(45) NOT NULL,
  PRIMARY KEY (`Palabra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `KEYWORDS`
--

LOCK TABLES `KEYWORDS` WRITE;
/*!40000 ALTER TABLE `KEYWORDS` DISABLE KEYS */;
/*!40000 ALTER TABLE `KEYWORDS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRODUCTO`
--

DROP TABLE IF EXISTS `PRODUCTO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRODUCTO` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Descripcion` longtext DEFAULT NULL,
  `Precio` double NOT NULL,
  `Imagen` longtext DEFAULT 'http://localhost:8080/efake/img/favicon.png',
  `Fecha` date NOT NULL,
  `Categoria` int(11) NOT NULL,
  `Subcategoria` int(11) DEFAULT NULL,
  `Owner` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `categoria_producto_idx` (`Categoria`),
  KEY `owner_idx` (`Owner`),
  KEY `subcategoria_idx` (`Subcategoria`),
  CONSTRAINT `categoria_producto` FOREIGN KEY (`Categoria`) REFERENCES `CATEGORIA` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `owner` FOREIGN KEY (`Owner`) REFERENCES `USUARIO` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `subcategoria` FOREIGN KEY (`Subcategoria`) REFERENCES `SUBCATEGORIA` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=434 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRODUCTO`
--

LOCK TABLES `PRODUCTO` WRITE;
/*!40000 ALTER TABLE `PRODUCTO` DISABLE KEYS */;
INSERT INTO `PRODUCTO` VALUES (364,'Incredible Rubber Towels','Awesome Plastic',0.9,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-11',3,NULL,157),(365,'Awesome Concrete Fish','Awesome Rubber',0.88,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-03-27',2,NULL,154),(366,'Fantastic Concrete Car','Awesome Rubber',0.56,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-03-31',3,NULL,140),(367,'Incredible Fresh Towels','Generic Rubber',0.07,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-20',9,NULL,143),(368,'Unbranded Wooden Car','Gorgeous Cotton',0.16,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-14',3,NULL,176),(369,'Handcrafted Rubber Computer','Handcrafted Fresh',0.06,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-19',4,NULL,138),(370,'Small Wooden Gloves','Refined Frozen',0.63,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-21',8,NULL,138),(371,'Tasty Concrete Ball','Sleek Soft',0.91,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-18',1,NULL,173),(372,'Generic Granite Soap','Incredible Concrete',0.6,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-16',3,NULL,138),(373,'Generic Concrete Towels','Intelligent Wooden',0.96,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-03-29',5,NULL,154),(374,'Refined Fresh Tuna','Tasty Cotton',0.1,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-17',9,NULL,135),(375,'Refined Fresh Salad','Gorgeous Fresh',0.44,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-15',1,NULL,158),(376,'Unbranded Frozen Mouse','Small Frozen',0.81,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-19',10,NULL,162),(377,'Practical Soft Chicken','Rustic Soft',0.75,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-03',4,NULL,159),(378,'Fantastic Steel Cheese','Refined Frozen',0,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-12',5,NULL,132),(379,'Intelligent Metal Computer','Small Granite',0.28,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-03-25',8,NULL,138),(380,'Tasty Rubber Towels','Gorgeous Soft',0.54,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-11',1,NULL,147),(381,'Licensed Frozen Gloves','Handmade Frozen',0.59,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-08',3,NULL,171),(382,'Ergonomic Frozen Tuna','Rustic Steel',0.37,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-03-31',4,NULL,152),(383,'Handcrafted Plastic Hat','Gorgeous Fresh',0.43,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-14',8,NULL,169),(385,'Licensed Wooden Keyboard','Intelligent Soft',0.02,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-03-27',9,NULL,165),(386,'Awesome Granite Mouse','Sleek Soft',0.95,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-03-31',9,NULL,172),(387,'Practical Metal Pants','Practical Metal',0.13,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-07',5,NULL,179),(388,'Incredible Metal Bacon','Licensed Concrete',0.45,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-11',2,NULL,178),(389,'Sleek Wooden Tuna','Intelligent Steel',0.56,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-14',1,NULL,173),(390,'Rustic Granite Shoes','Sleek Soft',0.97,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-03-27',3,NULL,147),(391,'Ergonomic Concrete Cheese','Generic Wooden',0.55,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-03-31',1,NULL,177),(392,'Gorgeous Wooden Tuna','Handcrafted Cotton',0.6,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-04',2,NULL,168),(393,'Small Rubber Chips','Licensed Wooden',0.11,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-05',2,NULL,140),(394,'Licensed Cotton Pizza','Handcrafted Cotton',0.17,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-03-27',7,NULL,162),(395,'Ergonomic Soft Tuna','Licensed Granite',0.62,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-04',10,NULL,145),(396,'Intelligent Plastic Car','Generic Steel',0.1,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-09',8,NULL,142),(397,'Fantastic Wooden Mouse','Practical Frozen',0.92,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-11',7,NULL,163),(398,'Gorgeous Steel Towels','Refined Rubber',0.98,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-21',9,NULL,142),(399,'Generic Soft Chips','Practical Wooden',0.23,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-10',8,NULL,153),(400,'Intelligent Steel Gloves','Handmade Metal',0.98,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-03-26',5,NULL,168),(401,'Awesome Fresh Computer','Handcrafted Steel',0.27,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-03-25',5,NULL,179),(402,'Handcrafted Wooden Ball','Sleek Rubber',0.4,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-03-28',10,NULL,153),(403,'Generic Metal Chair','Handcrafted Steel',0.58,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-07',7,NULL,145),(404,'Awesome Rubber Cheese','Handcrafted Plastic',0.92,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-15',9,NULL,170),(405,'Fantastic Plastic Gloves','Unbranded Soft',0.99,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-10',4,NULL,158),(406,'Unbranded Wooden Sausages','Generic Concrete',0.94,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-02',9,NULL,132),(407,'Handmade Rubber Computer','Unbranded Concrete',0.63,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-18',9,NULL,165),(408,'Handcrafted Steel Soap','Intelligent Soft',0.76,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-19',4,NULL,135),(409,'Tasty Fresh Shoes','Unbranded Concrete',0.6,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-16',7,NULL,146),(410,'Rustic Cotton Bacon','Incredible Concrete',0.83,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-11',1,NULL,158),(411,'Incredible Concrete Computer','Handmade Wooden',0.77,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-02',2,NULL,151),(412,'Practical Wooden Fish','Small Metal',0.38,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-10',7,NULL,145),(413,'Gorgeous Granite Gloves','Ergonomic Fresh',0.62,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-04',4,NULL,161),(414,'Awesome Cotton Shirt','Practical Rubber',0.21,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-09',4,NULL,149),(415,'Handcrafted Metal Chair','Practical Frozen',0.15,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-21',9,NULL,178),(416,'Generic Metal Chips','Unbranded Wooden',0.33,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-17',9,NULL,134),(417,'Unbranded Steel Shoes','Awesome Steel',0.32,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-02',7,NULL,130),(418,'Refined Frozen Keyboard','Intelligent Rubber',0.11,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-17',7,NULL,136),(419,'Handcrafted Granite Sausages','Handmade Soft',0.32,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-12',3,NULL,169),(420,'Fantastic Cotton Table','Rustic Rubber',0.73,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-21',1,NULL,175),(421,'Handcrafted Soft Chips','Handmade Rubber',0.06,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-01',9,NULL,139),(422,'Awesome Fresh Cheese','Tasty Rubber',0.33,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-03-30',5,NULL,159),(423,'Handmade Frozen Shoes','Practical Steel',0.21,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-16',9,NULL,136),(424,'Licensed Plastic Ball','Small Rubber',0.01,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-03-29',5,NULL,172),(425,'Small Steel Computer','Ergonomic Plastic',0.86,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-14',4,NULL,137),(426,'Unbranded Wooden Chicken','Licensed Granite',0.63,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-03-31',8,NULL,164),(427,'Refined Rubber Table','Refined Soft',0.51,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-14',2,NULL,145),(428,'Incredible Wooden Ball','Tasty Rubber',0.64,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-18',3,NULL,135),(429,'Incredible Fresh Chair','Generic Frozen',0.96,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-03-25',9,NULL,179),(430,'Awesome Wooden Shirt','Awesome Granite',0.8,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-15',9,NULL,171),(431,'Ergonomic Plastic Mouse','Sleek Rubber',0.87,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-03',8,NULL,156),(432,'Licensed Cotton Cheese','Tasty Concrete',0.02,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-04-16',1,NULL,169),(433,'Ergonomic Rubber Gloves','Ergonomic Metal',0.42,'https://b1.pngbarn.com/png/1015/575/simply-styled-icon-set-731-icons-free-ebay-alt-multicolored-striped-bag-icon-png-clip-art-thumbnail.png','2020-03-27',9,NULL,141);
/*!40000 ALTER TABLE `PRODUCTO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRODUCTO_KEYWORDS`
--

DROP TABLE IF EXISTS `PRODUCTO_KEYWORDS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRODUCTO_KEYWORDS` (
  `Producto` int(11) NOT NULL,
  `Palabra` varchar(45) NOT NULL,
  PRIMARY KEY (`Producto`,`Palabra`),
  KEY `fk_PRODUCTO_has_Keywords_Keywords1_idx` (`Palabra`),
  KEY `fk_PRODUCTO_has_Keywords_PRODUCTO1_idx` (`Producto`),
  CONSTRAINT `fk_PRODUCTO_has_Keywords_Keywords1` FOREIGN KEY (`Palabra`) REFERENCES `KEYWORDS` (`Palabra`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_PRODUCTO_has_Keywords_PRODUCTO1` FOREIGN KEY (`Producto`) REFERENCES `PRODUCTO` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRODUCTO_KEYWORDS`
--

LOCK TABLES `PRODUCTO_KEYWORDS` WRITE;
/*!40000 ALTER TABLE `PRODUCTO_KEYWORDS` DISABLE KEYS */;
/*!40000 ALTER TABLE `PRODUCTO_KEYWORDS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SUBCATEGORIA`
--

DROP TABLE IF EXISTS `SUBCATEGORIA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SUBCATEGORIA` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Categoria` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `categoria_idx` (`Categoria`),
  CONSTRAINT `categoria` FOREIGN KEY (`Categoria`) REFERENCES `CATEGORIA` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SUBCATEGORIA`
--

LOCK TABLES `SUBCATEGORIA` WRITE;
/*!40000 ALTER TABLE `SUBCATEGORIA` DISABLE KEYS */;
INSERT INTO `SUBCATEGORIA` VALUES (1,'Computers',1),(3,'Cameras',1),(6,'Tablets',1),(7,'Tv',1),(8,'Accessories',1),(9,'Yard',2),(10,'Crafts',2),(11,'Pet Supplies',2),(12,'Women',3),(13,'Men',3),(14,'Watches',3),(15,'Shoes',3),(16,'Outdoor',4),(17,'Team Sports',4),(18,'Golf',4),(19,'Parts',5),(20,'Accessories',5),(21,'Cars',5),(22,'Trucks',5),(23,'Motorcycles',5),(24,'Coins',7),(25,'Paper Money',7),(26,'Antiques',7),(27,'Sports Memorabilia',7),(28,'Vintage & Antique toys',8),(29,'Kids Toys',8),(30,'Action figures',8),(31,'Dolls & Bears',8),(32,'Videogames',9),(33,'Music',9),(34,'DVDâ€™s',9),(35,'Comic',10),(36,'Adult Coloring Books',10),(37,'Bibles',10);
/*!40000 ALTER TABLE `SUBCATEGORIA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USUARIO`
--

DROP TABLE IF EXISTS `USUARIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USUARIO` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Correo` varchar(100) NOT NULL,
  `Password` binary(32) NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Apellidos` varchar(100) NOT NULL,
  `Edad` int(11) NOT NULL,
  `Telefono` varchar(13) DEFAULT '-',
  `esAdmin` tinyint(4) NOT NULL DEFAULT 0,
  `UltimaEntrada` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Correo_UNIQUE` (`Correo`)
) ENGINE=InnoDB AUTO_INCREMENT=180 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USUARIO`
--

LOCK TABLES `USUARIO` WRITE;
/*!40000 ALTER TABLE `USUARIO` DISABLE KEYS */;
INSERT INTO `USUARIO` VALUES (129,'admin@efake.com','Œiv\åµA½\é½M\îß±g©\ÈsüK¸¨o*´H©','Admin','Efake',909,'111-111-1111',1,'2020-04-21'),(130,'Freddie72@hotmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Liam','Flatley',7,'090-586-627',0,'2020-04-03'),(131,'Laurel_Swaniawski44@hotmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Raoul','Feil',13,'526-916-502',0,'2020-04-17'),(132,'Cali63@yahoo.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Margie','Huel',4,'052-184-223',0,'2020-04-21'),(133,'Eleanore.Rohan6@hotmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Ernestine','Schaefer',51,'124-474-068',0,'2020-04-02'),(134,'Roosevelt40@hotmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Brady','Toy',31,'494-131-117',0,'2020-04-03'),(135,'Mariane_Hayes47@gmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Tremayne','Kihn',53,'534-340-019',0,'2020-04-08'),(136,'Kayla.Corkery@gmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Gennaro','Ferry',24,'486-854-415',0,'2020-04-19'),(137,'Nickolas26@gmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Ansel','Ebert',35,'634-663-074',0,'2020-04-19'),(138,'Jacey.McKenzie19@hotmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Alyson','Stamm',19,'780-796-127',0,'2020-04-13'),(139,'Owen.Morissette80@hotmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Brielle','Botsford',27,'606-845-599',0,'2020-04-01'),(140,'Ahmed_Kris@gmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Hermann','Harvey',18,'937-095-873',0,'2020-03-31'),(141,'Nigel.Nicolas@hotmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Jolie','Daniel',14,'338-461-155',0,'2020-03-25'),(142,'Elissa.Marvin57@yahoo.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Guillermo','Weissnat',39,'327-722-610',0,'2020-03-28'),(143,'Jake.Cummerata@gmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Rosemarie','Grimes',29,'960-980-575',0,'2020-04-20'),(144,'Eino_Yost91@gmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Celia','Rogahn',32,'243-924-977',0,'2020-04-06'),(145,'Marcellus.Rowe@hotmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Kailey','Douglas',14,'205-326-602',0,'2020-04-04'),(146,'Elyssa96@hotmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Jarred','Heller',46,'823-717-596',0,'2020-04-02'),(147,'Lacey.Stiedemann@yahoo.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Scottie','Stroman',57,'187-249-337',0,'2020-04-05'),(148,'Branson21@yahoo.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Titus','Green',10,'473-629-261',0,'2020-04-16'),(149,'Janet_Collins42@gmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Marc','Marks',52,'873-384-002',0,'2020-04-12'),(150,'Winnifred_Heathcote@gmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Joyce','Daniel',30,'080-565-860',0,'2020-04-15'),(151,'Johnson_Purdy44@gmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Carlotta','Metz',47,'487-829-606',0,'2020-04-11'),(152,'Rafaela.Howe@hotmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Emie','Tromp',24,'691-311-065',0,'2020-04-14'),(153,'Henry83@hotmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Deborah','Cassin',12,'309-288-870',0,'2020-04-01'),(154,'Barton_Harvey10@gmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Katheryn','Ziemann',45,'411-998-014',0,'2020-04-04'),(155,'Bobbie.Brakus41@hotmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Laurine','Fahey',59,'692-717-320',0,'2020-04-17'),(156,'Gladys_Bailey72@yahoo.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Aliyah','Jakubowski',23,'742-392-926',0,'2020-03-25'),(157,'Raleigh67@hotmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Gunnar','Green',2,'457-743-079',0,'2020-04-03'),(158,'Elenora.Rutherford@gmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Darrion','Cummings',57,'987-574-816',0,'2020-04-03'),(159,'Rocio.OConnell98@yahoo.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Pat','Gottlieb',35,'696-627-325',0,'2020-04-21'),(160,'Alivia.Goldner@yahoo.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Maxie','Pfannerstill',47,'525-168-559',0,'2020-04-13'),(161,'Roberto_Dickens50@hotmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Rodolfo','Bashirian',16,'795-263-414',0,'2020-04-21'),(162,'Astrid.Braun57@yahoo.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Amya','Larson',58,'906-615-907',0,'2020-04-18'),(163,'Lavada_Prohaska@hotmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Randall','Herzog',10,'620-201-582',0,'2020-04-15'),(164,'Hellen.Jerde93@gmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Bret','Pagac',34,'921-904-324',0,'2020-03-31'),(165,'Cynthia23@yahoo.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Wilfrid','Luettgen',28,'246-846-777',0,'2020-03-23'),(166,'Malcolm_Luettgen95@gmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Ashton','Halvorson',53,'074-691-488',0,'2020-04-06'),(167,'Paul_Donnelly@hotmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Linwood','Little',15,'683-705-850',0,'2020-04-12'),(168,'Haylie_Nicolas40@hotmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Prince','Kling',12,'109-025-286',0,'2020-04-02'),(169,'Heaven_Zulauf82@hotmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Watson','Funk',48,'289-661-404',0,'2020-03-28'),(170,'Mohammad_Robel56@gmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Khalil','Goodwin',17,'976-950-097',0,'2020-04-09'),(171,'Deja.Kiehn25@yahoo.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Keyshawn','Watsica',14,'544-479-714',0,'2020-04-20'),(172,'Alison_Gutmann67@gmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Alva','Huel',1,'105-735-455',0,'2020-04-06'),(173,'Carroll.Pacocha55@yahoo.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Alvah','Toy',25,'257-587-639',0,'2020-04-17'),(174,'Celine58@yahoo.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Otha','Parker',12,'551-003-409',0,'2020-04-21'),(175,'Lonie97@gmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Kameron','Sporer',31,'011-596-952',0,'2020-04-17'),(176,'Tyson19@hotmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Kiera','Windler',26,'052-596-889',0,'2020-04-08'),(177,'King.Trantow30@gmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Monique','Kris',24,'002-277-246',0,'2020-04-03'),(178,'Derek77@gmail.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Olen','Ritchie',30,'110-192-471',0,'2020-03-27'),(179,'Lonny_Gottlieb96@yahoo.com','’P\â\"\Ä\ÇX\Ô\ÅKP¨€£\éùþ\Õ]\\: °\è`\ÞÙ‘e','Robin','Kreiger',10,'731-098-000',0,'2020-04-18');
/*!40000 ALTER TABLE `USUARIO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VALORACION`
--

DROP TABLE IF EXISTS `VALORACION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `VALORACION` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Puntuacion` int(11) NOT NULL,
  `Comentario` longtext DEFAULT NULL,
  `Fecha` datetime NOT NULL,
  `Cliente` int(11) DEFAULT NULL,
  `ProductoValorado` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `cliente_idx` (`Cliente`),
  KEY `producto_idx` (`ProductoValorado`),
  CONSTRAINT `cliente` FOREIGN KEY (`Cliente`) REFERENCES `USUARIO` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `producto` FOREIGN KEY (`ProductoValorado`) REFERENCES `PRODUCTO` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VALORACION`
--

LOCK TABLES `VALORACION` WRITE;
/*!40000 ALTER TABLE `VALORACION` DISABLE KEYS */;
INSERT INTO `VALORACION` VALUES (47,4,'Nice!','2020-04-21 23:25:16',132,371),(48,3,'Delicious!','2020-04-21 23:25:28',132,365),(49,5,'Beautiful Shirt','2020-04-21 23:25:42',132,364),(50,4,'Soft ball','2020-04-21 23:25:55',132,377),(51,2,'Nice Car!','2020-04-21 23:26:08',132,373);
/*!40000 ALTER TABLE `VALORACION` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-21 22:32:24
