-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: Efake
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `ADMINISTRADOR`
--

DROP TABLE IF EXISTS `ADMINISTRADOR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ADMINISTRADOR` (
  `Nombre` varchar(45) NOT NULL,
  `Apellidos` varchar(100) NOT NULL,
  `Correo` varchar(100) NOT NULL,
  `Password` varchar(45) NOT NULL,
  PRIMARY KEY (`Correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ADMINISTRADOR`
--

LOCK TABLES `ADMINISTRADOR` WRITE;
/*!40000 ALTER TABLE `ADMINISTRADOR` DISABLE KEYS */;
/*!40000 ALTER TABLE `ADMINISTRADOR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USUARIO`
--

DROP TABLE IF EXISTS `USUARIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `USUARIO` (
  `Correo` varchar(100) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Apellidos` varchar(100) NOT NULL,
  `Edad` int(11) NOT NULL,
  `Telefono` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`Correo`),
  UNIQUE KEY `Correo_UNIQUE` (`Correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USUARIO`
--

LOCK TABLES `USUARIO` WRITE;
/*!40000 ALTER TABLE `USUARIO` DISABLE KEYS */;
/*!40000 ALTER TABLE `USUARIO` ENABLE KEYS */;
UNLOCK TABLES;
--
-- Table structure for table `CATEGORIA`
--

DROP TABLE IF EXISTS `CATEGORIA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CATEGORIA` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CATEGORIA`
--

LOCK TABLES `CATEGORIA` WRITE;
/*!40000 ALTER TABLE `CATEGORIA` DISABLE KEYS */;
/*!40000 ALTER TABLE `CATEGORIA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRODUCTO`
--

DROP TABLE IF EXISTS `PRODUCTO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PRODUCTO` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Descripcion` varchar(255) DEFAULT NULL,
  `Precio` double NOT NULL,
  `Foto` varchar(255) DEFAULT NULL,
  `PalabraClave` varchar(45) DEFAULT NULL,
  `Fecha` date NOT NULL,
  `Hora` time NOT NULL,
  `CategoriaDelProducto` int(11) NOT NULL,
  `Categoria` int(11) NOT NULL,
  `Owner` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `categoria_idx` (`Categoria`),
  KEY `categoriaProducto_idx` (`CategoriaDelProducto`),
  KEY `owner_idx` (`Owner`),
  CONSTRAINT `categoriaProducto` FOREIGN KEY (`CategoriaDelProducto`) REFERENCES `CATEGORIA` (`ID`),
  CONSTRAINT `owner` FOREIGN KEY (`Owner`) REFERENCES `USUARIO` (`Correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRODUCTO`
--

LOCK TABLES `PRODUCTO` WRITE;
/*!40000 ALTER TABLE `PRODUCTO` DISABLE KEYS */;
/*!40000 ALTER TABLE `PRODUCTO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SUBCATEGORIA`
--

DROP TABLE IF EXISTS `SUBCATEGORIA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SUBCATEGORIA` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Descripcion` varchar(255) DEFAULT NULL,
  `Categoria` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `categoria_idx` (`Categoria`),
  CONSTRAINT `categoria` FOREIGN KEY (`Categoria`) REFERENCES `CATEGORIA` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SUBCATEGORIA`
--

LOCK TABLES `SUBCATEGORIA` WRITE;
/*!40000 ALTER TABLE `SUBCATEGORIA` DISABLE KEYS */;
/*!40000 ALTER TABLE `SUBCATEGORIA` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `valoracion`
--

DROP TABLE IF EXISTS `CALORACION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `VALORACION` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Puntuacion` int(11) NOT NULL,
  `Comentario` varchar(255) DEFAULT NULL,
  `Fecha` date NOT NULL,
  `Hora` time NOT NULL,
  `Cliente` varchar(100) DEFAULT NULL,
  `ProductoValorado` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `cliente_idx` (`Cliente`),
  KEY `producto_idx` (`ProductoValorado`),
  CONSTRAINT `cliente` FOREIGN KEY (`Cliente`) REFERENCES `USUARIO` (`Correo`),
  CONSTRAINT `producto` FOREIGN KEY (`ProductoValorado`) REFERENCES `PRODUCTO` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valoracion`
--

LOCK TABLES `VALORACION` WRITE;
/*!40000 ALTER TABLE `valoracion` DISABLE KEYS */;
/*!40000 ALTER TABLE `valoracion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-19 20:07:29
