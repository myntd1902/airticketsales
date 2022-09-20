-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: localhost    Database: flight
-- ------------------------------------------------------
-- Server version	8.0.24
CREATE DATABASE flight /*!40100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE flight;

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
-- Table structure for table `chitiethoadon`
--

DROP TABLE IF EXISTS `chitiethoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitiethoadon` (
  `maHD` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `maVe` int NOT NULL,
  `giaVe` decimal(10,0) NOT NULL,
  PRIMARY KEY (`maHD`,`maVe`),
  KEY `FK_chitiethoadon_maHD_idx` (`maHD`),
  KEY `FK_chitiethoadon_maVe_idx` (`maVe`),
  KEY `FK_chitiethoadon_giaVe_idx` (`giaVe`),
  CONSTRAINT `FK_chitiethoadon_giaVe` FOREIGN KEY (`giaVe`) REFERENCES `giave` (`giaVe`),
  CONSTRAINT `FK_chitiethoadon_idKH` FOREIGN KEY (`maVe`) REFERENCES `vemaybay` (`maVe`),
  CONSTRAINT `FK_chitiethoadon_idNguoiTT` FOREIGN KEY (`maHD`) REFERENCES `hoadonthanhtoan` (`maHD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitiethoadon`
--

LOCK TABLES `chitiethoadon` WRITE;
/*!40000 ALTER TABLE `chitiethoadon` DISABLE KEYS */;
INSERT INTO `chitiethoadon` VALUES ('1eead382-18bd-4319-9251-a913a2d77771',14,90000),('40e3ebdf-0939-47b3-9861-1499aa3471c4',14,90000),('ad735366-5de8-4b6f-9070-18c13bbe65dd',4,90000),('bf468fe9-6006-4445-a2ce-7fa15dafc7d7',13,90000),('c8e4a187-7bb9-4ceb-8760-c1738ca03e71',16,90000),('0349681f-228a-4311-9eb5-d2a6b723acb9',5,100000),('0f91a17a-5dd4-4398-a0fe-1c5782a52dff',15,100000),('386a92eb-a82a-46c9-812e-b4cc32085a8c',10,100000),('3baa0f10-2006-418f-b0d7-0f55a3b62022',8,100000),('bcd6e38d-8970-4820-ade4-dc2b0e6ef989',9,100000),('c89e9cb9-64c8-45d5-83f6-ff20d11fa7d7',2,100000),('ed5baeed-e0cc-4cc7-9058-5800d83414f8',11,100000),('2029c49d-8a42-4389-aed1-f2c30b9b9061',12,150000),('54b872bb-1b1f-4170-b300-9574c3273c3e',19,150000),('77459a52-4d63-4cd7-8216-3eb0b31de227',18,150000),('8cc4dc5d-0556-4a01-b285-b113661c6e46',3,150000),('5f4b505a-c6e1-44e7-b4d9-2d6babcf7783',17,200000),('8b3079d0-26b3-4480-8299-19be8943a643',1,200000),('e179995b-4bf8-43bb-8dd2-f31ae7cfc6ae',20,200000);
/*!40000 ALTER TABLE `chitiethoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chuyenbay`
--

DROP TABLE IF EXISTS `chuyenbay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chuyenbay` (
  `maChuyenBay` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ngayGioKhoiHanh` varchar(19) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ngayGioDen` varchar(19) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenSanBayDi` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenSanBayDen` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `soHieuMayBay` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `sanBayTrungGian` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`maChuyenBay`),
  KEY `FK_chuyenbay_sanBayTrungGian_idx` (`sanBayTrungGian`),
  KEY `FK_chuyenbay_tenSanBayDi_idx` (`tenSanBayDi`),
  KEY `FK_chuyenbay_tenSanBayDen_idx` (`tenSanBayDen`),
  KEY `FK_chuyenbay_soHieuMayBay_idx` (`soHieuMayBay`),
  KEY `FK_chuyenbay_maChuyenBay_idx` (`maChuyenBay`),
  CONSTRAINT `FK_chuyenbay_sanBayTrungGian` FOREIGN KEY (`sanBayTrungGian`) REFERENCES `sanbay` (`maSanBay`),
  CONSTRAINT `FK_chuyenbay_soHieuMayBay` FOREIGN KEY (`soHieuMayBay`) REFERENCES `maybay` (`soHieuMayBay`),
  CONSTRAINT `FK_chuyenbay_tenSanBayDen` FOREIGN KEY (`tenSanBayDen`) REFERENCES `sanbay` (`tenSanBay`),
  CONSTRAINT `FK_chuyenbay_tenSanBayDi` FOREIGN KEY (`tenSanBayDi`) REFERENCES `sanbay` (`tenSanBay`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chuyenbay`
--

LOCK TABLES `chuyenbay` WRITE;
/*!40000 ALTER TABLE `chuyenbay` DISABLE KEYS */;
INSERT INTO `chuyenbay` VALUES ('1','07:00:00 20-05-2020','12:00:00 20-05-2020','Mộc Bài','Tân Sơn Nhất','VNA01',NULL),('2','17:00:00 26-06-2020','20:00:00 26-06-2020','Tân Sơn Nhất','Mộc Bài','BA01',NULL);
/*!40000 ALTER TABLE `chuyenbay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ghe`
--

DROP TABLE IF EXISTS `ghe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ghe` (
  `maGhe` varchar(5) COLLATE utf8mb4_unicode_ci NOT NULL,
  `hangVe` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`maGhe`),
  KEY `FK_ghe_hangVe_idx` (`hangVe`),
  CONSTRAINT `FK_ghe_hangVe` FOREIGN KEY (`hangVe`) REFERENCES `hangve` (`hangVe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ghe`
--

LOCK TABLES `ghe` WRITE;
/*!40000 ALTER TABLE `ghe` DISABLE KEYS */;
INSERT INTO `ghe` VALUES ('A01','Phổ thông'),('A02','Phổ thông'),('A03','Phổ thông'),('A04','Phổ thông'),('A05','Phổ thông'),('A06','Phổ thông'),('A07','Phổ thông'),('A08','Phổ thông'),('A09','Phổ thông'),('A10','Phổ thông'),('A11','Phổ thông'),('A12','Phổ thông'),('A13','Phổ thông'),('A14','Phổ thông'),('A15','Phổ thông'),('A16','Phổ thông'),('A17','Phổ thông'),('A18','Phổ thông'),('A19','Phổ thông'),('A20','Phổ thông'),('B01','Phổ thông'),('B02','Phổ thông'),('B03','Phổ thông'),('B04','Phổ thông'),('B05','Phổ thông'),('B06','Phổ thông'),('B07','Phổ thông'),('B08','Phổ thông'),('B09','Phổ thông'),('B10','Phổ thông'),('B11','Phổ thông'),('B12','Phổ thông'),('B13','Phổ thông'),('B14','Phổ thông'),('B15','Phổ thông'),('B16','Phổ thông'),('B17','Phổ thông'),('B18','Phổ thông'),('B19','Phổ thông'),('B20','Phổ thông'),('C01','Phổ Thông'),('C02','Phổ Thông'),('C03','Phổ Thông'),('C04','Phổ Thông'),('C05','Phổ Thông'),('C06','Phổ Thông'),('C07','Phổ Thông'),('C08','Phổ Thông'),('C09','Phổ Thông'),('C10','Phổ Thông'),('C11','Phổ Thông'),('C12','Phổ Thông'),('C13','Phổ Thông'),('C14','Phổ Thông'),('C15','Phổ Thông'),('C16','Phổ Thông'),('C17','Phổ Thông'),('C18','Phổ Thông'),('C19','Phổ Thông'),('C20','Phổ Thông'),('D01','Phổ Thông'),('D02','Phổ Thông'),('D03','Phổ Thông'),('D04','Phổ Thông'),('D05','Phổ Thông'),('D06','Phổ Thông'),('D07','Phổ Thông'),('D08','Phổ Thông'),('D09','Phổ Thông'),('D10','Phổ Thông'),('D11','Phổ Thông'),('D12','Phổ Thông'),('D13','Phổ Thông'),('D14','Phổ Thông'),('D15','Phổ Thông'),('D16','Phổ Thông'),('D17','Phổ Thông'),('D18','Phổ Thông'),('D19','Phổ Thông'),('D20','Phổ Thông'),('S01','Thương gia'),('S02','Thương gia'),('S03','Thương gia'),('S04','Thương gia'),('S05','Thương gia'),('S06','Thương gia'),('S07','Thương gia'),('S08','Thương gia'),('S09','Thương gia'),('S10','Thương gia'),('S11','Thương gia'),('S12','Thương gia'),('S13','Thương gia'),('S14','Thương gia'),('S15','Thương gia'),('S16','Thương gia'),('S17','Thương gia'),('S18','Thương gia'),('S19','Thương gia'),('S20','Thương gia'),('S21','Thương gia'),('S22','Thương gia'),('S23','Thương gia'),('S24','Thương gia'),('S25','Thương gia'),('S26','Thương gia'),('S27','Thương gia'),('S28','Thương gia'),('S29','Thương gia'),('S30','Thương gia');
/*!40000 ALTER TABLE `ghe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giave`
--

DROP TABLE IF EXISTS `giave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giave` (
  `tenSanBayDi` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenSanBayDen` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `hangBay` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `hangVe` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `giaVe` decimal(10,0) NOT NULL,
  PRIMARY KEY (`tenSanBayDi`,`tenSanBayDen`,`hangBay`,`hangVe`),
  KEY `FK_giave_chuyenbay_tenSanBayDi_idx` (`tenSanBayDi`),
  KEY `FK_giave_chuyenbay_tenSanBayDen_idx` (`tenSanBayDen`),
  KEY `FK_giave_maybay_hangBay_idx` (`hangBay`),
  KEY `FK_giave_hangve_hangVe_idx` (`hangVe`),
  KEY `FK_giave_vemaybay_idx` (`giaVe`),
  CONSTRAINT `FK_giave_chuyenbay_tenSanBayDen` FOREIGN KEY (`tenSanBayDen`) REFERENCES `chuyenbay` (`tenSanBayDen`),
  CONSTRAINT `FK_giave_chuyenbay_tenSanBayDi` FOREIGN KEY (`tenSanBayDi`) REFERENCES `chuyenbay` (`tenSanBayDi`),
  CONSTRAINT `FK_giave_hangve_hangVe` FOREIGN KEY (`hangVe`) REFERENCES `hangve` (`hangVe`),
  CONSTRAINT `FK_giave_maybay_hangBay` FOREIGN KEY (`hangBay`) REFERENCES `maybay` (`hangBay`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giave`
--

LOCK TABLES `giave` WRITE;
/*!40000 ALTER TABLE `giave` DISABLE KEYS */;
INSERT INTO `giave` VALUES ('Mộc Bài','Tân Sơn Nhất','Bamboo Airways','Phổ thông',90000),('Tân Sơn Nhất','Mộc Bài','Bamboo Airways','Phổ thông',90000),('Mộc Bài','Tân Sơn Nhất','Vietnam Airlines','Phổ thông',100000),('Tân Sơn Nhất','Mộc Bài','Vietnam Airlines','Phổ thông',100000),('Mộc Bài','Tân Sơn Nhất','Bamboo Airways','Thương gia',150000),('Tân Sơn Nhất','Mộc Bài','Bamboo Airways','Thương gia',150000),('Mộc Bài','Tân Sơn Nhất','Vietnam Airlines','Thương gia',200000),('Tân Sơn Nhất','Mộc Bài','Vietnam Airlines','Thương gia',200000);
/*!40000 ALTER TABLE `giave` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hangve`
--

DROP TABLE IF EXISTS `hangve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hangve` (
  `id` int NOT NULL,
  `hangVe` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_id_idx` (`id`),
  KEY `FK_hangVe_idx` (`hangVe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hangve`
--

LOCK TABLES `hangve` WRITE;
/*!40000 ALTER TABLE `hangve` DISABLE KEYS */;
INSERT INTO `hangve` VALUES (2,'Phổ thông'),(1,'Thương gia');
/*!40000 ALTER TABLE `hangve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadonthanhtoan`
--

DROP TABLE IF EXISTS `hoadonthanhtoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadonthanhtoan` (
  `maHD` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenNguoiTT` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenKH` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ngayTT` varchar(19) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`maHD`,`tenKH`,`tenNguoiTT`,`ngayTT`),
  KEY `FK_hoadonthanhtoan_tenNguoiTT_idx` (`tenNguoiTT`),
  KEY `FK_hoadonthanhtoan_tenKH_idx` (`tenKH`),
  CONSTRAINT `FK_hoadonthanhtoan_tenKH` FOREIGN KEY (`tenKH`) REFERENCES `khachhang` (`tenKH`),
  CONSTRAINT `FK_hoadonthanhtoan_tenNguoiTT` FOREIGN KEY (`tenNguoiTT`) REFERENCES `users` (`hoTen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadonthanhtoan`
--

LOCK TABLES `hoadonthanhtoan` WRITE;
/*!40000 ALTER TABLE `hoadonthanhtoan` DISABLE KEYS */;
INSERT INTO `hoadonthanhtoan` VALUES ('0f91a17a-5dd4-4398-a0fe-1c5782a52dff','Nhân Viên 01','qưqwqwqw','04:16:55 10-05-2021'),('1eead382-18bd-4319-9251-a913a2d77771','Nhân Viên 01','e3432','04:16:55 10-05-2021'),('2029c49d-8a42-4389-aed1-f2c30b9b9061','Nhân Viên 01','sadds','04:01:21 10-05-2021'),('386a92eb-a82a-46c9-812e-b4cc32085a8c','Nhân Viên 01','dưqeeqw','03:59:50 10-05-2021'),('40e3ebdf-0939-47b3-9861-1499aa3471c4','Nhân Viên 01','e3432','04:16:54 10-05-2021'),('54b872bb-1b1f-4170-b300-9574c3273c3e','Nhân Viên 01','121212','04:33:08 10-05-2021'),('5f4b505a-c6e1-44e7-b4d9-2d6babcf7783','Nhân Viên 01','ewqqw','04:16:56 10-05-2021'),('77459a52-4d63-4cd7-8216-3eb0b31de227','Nhân Viên 01','dssdas','04:33:08 10-05-2021'),('8b3079d0-26b3-4480-8299-19be8943a643','Nhân Viên 01','Lê Thị B','04:00:00 20-01-2021'),('8cc4dc5d-0556-4a01-b285-b113661c6e46','Nhân Viên 01','Trương G','06:00:00 27-02-2021'),('bf468fe9-6006-4445-a2ce-7fa15dafc7d7','Nhân Viên 01','23','04:01:21 10-05-2021'),('c8e4a187-7bb9-4ceb-8760-c1738ca03e71','Nhân Viên 01','sadsadsad','04:16:55 10-05-2021'),('e179995b-4bf8-43bb-8dd2-f31ae7cfc6ae','Nhân Viên 01','âsasasas','04:33:09 10-05-2021'),('ed5baeed-e0cc-4cc7-9058-5800d83414f8','Nhân Viên 01','dfdfds','04:01:21 10-05-2021'),('3baa0f10-2006-418f-b0d7-0f55a3b62022','Nhân Viên 02','rưerwe','03:46:04 10-05-2021'),('ad735366-5de8-4b6f-9070-18c13bbe65dd','Nhân Viên 02','Huỳnh N','07:00:00 02-03-2021'),('bcd6e38d-8970-4820-ade4-dc2b0e6ef989','Nhân Viên 02','ửewrew','03:53:48 10-05-2021'),('c89e9cb9-64c8-45d5-83f6-ff20d11fa7d7','Nhân Viên 02','Hoành Văn E','05:00:00 15-02-2021'),('0349681f-228a-4311-9eb5-d2a6b723acb9','Phạm Anh D','Phạm Anh D','05:50:00 10-03-2021');
/*!40000 ALTER TABLE `hoadonthanhtoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khachhang` (
  `maKH` int NOT NULL AUTO_INCREMENT,
  `tenKH` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `idCard` varchar(12) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sdt` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`tenKH`),
  KEY `FK_khachhang_maKH_idx` (`maKH`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES (24,'12','121212121212','12@','1212212121'),(23,'1212','121212121212','@','1212121212'),(20,'121212','121212211212','@','1212121212'),(25,'213','121212121212','@','1212121212'),(14,'23','323232323222','@','2323232323'),(21,'âsasasas','121212121212','@','1212121212'),(12,'dfdfds','324234343434','@','3434343434'),(19,'dssdas','121212121221','@','1212121212'),(11,'dưqeeqw','121212121212','@','1212121212'),(15,'e3432','121212121212','@','1212121212'),(18,'ewqqw','121212121212','@','1212121212'),(2,'Hoành Văn E','012345678666','hoangvane@gmail.com','0988775444'),(4,'Huỳnh N','012345678444','huynhn@gmail.com','0988775222'),(1,'Lê Thị B','012345678777','lethib@gmail.com','0988775555'),(6,'Nguyễn Thị Diễm M','012345678911','nguyenthidiemm@gmail.com','0345678922'),(7,'Nguyễn Văn A','012345678999','nguyenvana@gmail.com','0988775544'),(5,'Phạm Anh D','012345678910','phamanhdg@gmail.com','0345678921'),(16,'qưqwqwqw','121212121212','@','1212121212'),(9,'rưerwe','121212121','sads@','1212121212'),(13,'sadds','342434343434','@','3434343434'),(17,'sadsadsad','030303030301','@','1010101010'),(22,'sdsad','232423433434','@','1212121212'),(8,'Trần Thị C','012345678888','tranthic@gmail.com','0988775511'),(3,'Trương G','012345678555','truongg@gmail.com','0988775333'),(10,'ửewrew','324323232323','đá@','2323232323'),(26,'ưqe','121212121212','@','1212121212'),(27,'ưqewqe','121212121212','@','1212121212');
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaitk`
--

DROP TABLE IF EXISTS `loaitk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loaitk` (
  `id` int NOT NULL,
  `tk` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_id_idx` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaitk`
--

LOCK TABLES `loaitk` WRITE;
/*!40000 ALTER TABLE `loaitk` DISABLE KEYS */;
INSERT INTO `loaitk` VALUES (1,'Nhân Viên'),(2,'Khách Hàng'),(3,'Admin');
/*!40000 ALTER TABLE `loaitk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maybay`
--

DROP TABLE IF EXISTS `maybay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `maybay` (
  `soHieuMayBay` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `hangBay` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`soHieuMayBay`),
  KEY `FK_maybay_giave_hangBay_idx` (`hangBay`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maybay`
--

LOCK TABLES `maybay` WRITE;
/*!40000 ALTER TABLE `maybay` DISABLE KEYS */;
INSERT INTO `maybay` VALUES ('BA01','Bamboo Airways'),('VNA01','Vietnam Airlines');
/*!40000 ALTER TABLE `maybay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maybay_ghe`
--

DROP TABLE IF EXISTS `maybay_ghe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `maybay_ghe` (
  `soHieuMayBay` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `maGhe` varchar(5) COLLATE utf8mb4_unicode_ci NOT NULL,
  `trangThai` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`soHieuMayBay`,`maGhe`),
  KEY `FK_maybay_ghe_soHieuMayBay_idx` (`soHieuMayBay`),
  KEY `FK_maybay_ghe_maGhe_idx` (`maGhe`),
  CONSTRAINT `FK_maybay_ghe_maGhe` FOREIGN KEY (`maGhe`) REFERENCES `ghe` (`maGhe`),
  CONSTRAINT `FK_maybay_ghe_soHieuMayBay` FOREIGN KEY (`soHieuMayBay`) REFERENCES `maybay` (`soHieuMayBay`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maybay_ghe`
--

LOCK TABLES `maybay_ghe` WRITE;
/*!40000 ALTER TABLE `maybay_ghe` DISABLE KEYS */;
INSERT INTO `maybay_ghe` VALUES ('BA01','A01',1),('BA01','A02',1),('BA01','A03',1),('BA01','A04',1),('BA01','A05',1),('BA01','A06',0),('BA01','A07',0),('BA01','A08',0),('BA01','A09',0),('BA01','A10',0),('BA01','A11',0),('BA01','A12',0),('BA01','A13',0),('BA01','A14',0),('BA01','A15',0),('BA01','A16',0),('BA01','A17',0),('BA01','A18',0),('BA01','A19',0),('BA01','A20',0),('BA01','B01',1),('BA01','B02',0),('BA01','B03',0),('BA01','B04',0),('BA01','B05',0),('BA01','B06',0),('BA01','B07',0),('BA01','B08',0),('BA01','B09',0),('BA01','B10',0),('BA01','B11',0),('BA01','B12',0),('BA01','B13',0),('BA01','B14',0),('BA01','B15',0),('BA01','B16',0),('BA01','B17',0),('BA01','B18',0),('BA01','B19',0),('BA01','C01',0),('BA01','C02',0),('BA01','C03',0),('BA01','C04',0),('BA01','C05',0),('BA01','C06',0),('BA01','C07',0),('BA01','C08',0),('BA01','C09',0),('BA01','C10',0),('BA01','C11',0),('BA01','C12',0),('BA01','C13',0),('BA01','C14',0),('BA01','C15',0),('BA01','C16',0),('BA01','C17',0),('BA01','C18',0),('BA01','C19',0),('BA01','C20',0),('BA01','D01',0),('BA01','D02',0),('BA01','D03',0),('BA01','D04',0),('BA01','D05',0),('BA01','D06',0),('BA01','D07',0),('BA01','D08',0),('BA01','D09',0),('BA01','D10',0),('BA01','D11',0),('BA01','D12',0),('BA01','D13',0),('BA01','D14',0),('BA01','D15',0),('BA01','D16',0),('BA01','D17',0),('BA01','D18',0),('BA01','D19',0),('BA01','S01',1),('BA01','S02',1),('BA01','S03',0),('BA01','S04',1),('BA01','S05',1),('BA01','S06',0),('BA01','S07',1),('BA01','S08',1),('BA01','S09',0),('BA01','S10',0),('BA01','S11',0),('BA01','S12',0),('BA01','S13',0),('BA01','S14',0),('BA01','S15',0),('BA01','S16',0),('BA01','S17',0),('BA01','S18',0),('BA01','S19',0),('BA01','S20',0),('BA01','S21',0),('BA01','S22',0),('BA01','S23',0),('BA01','S24',0),('BA01','S25',0),('BA01','S26',0),('BA01','S27',0),('BA01','S28',0),('BA01','S29',0),('BA01','S30',0),('VNA01','A01',1),('VNA01','A02',1),('VNA01','A03',1),('VNA01','A04',1),('VNA01','A05',0),('VNA01','A06',1),('VNA01','A07',1),('VNA01','A08',1),('VNA01','A09',1),('VNA01','A10',0),('VNA01','A11',0),('VNA01','A12',0),('VNA01','A13',0),('VNA01','A14',0),('VNA01','A15',0),('VNA01','A16',0),('VNA01','A17',0),('VNA01','A18',0),('VNA01','A19',0),('VNA01','A20',0),('VNA01','B01',0),('VNA01','B02',0),('VNA01','B03',0),('VNA01','B04',0),('VNA01','B05',0),('VNA01','B06',0),('VNA01','B07',0),('VNA01','B08',0),('VNA01','B09',0),('VNA01','B10',0),('VNA01','B11',0),('VNA01','B12',0),('VNA01','B13',0),('VNA01','B14',0),('VNA01','B15',0),('VNA01','B16',0),('VNA01','B17',0),('VNA01','B18',0),('VNA01','B19',0),('VNA01','B20',0),('VNA01','C01',1),('VNA01','C02',0),('VNA01','C03',0),('VNA01','C04',0),('VNA01','C05',0),('VNA01','C06',0),('VNA01','C07',0),('VNA01','C08',0),('VNA01','C09',0),('VNA01','C10',0),('VNA01','C11',0),('VNA01','C12',0),('VNA01','C13',0),('VNA01','C14',0),('VNA01','C15',0),('VNA01','C16',0),('VNA01','C17',0),('VNA01','C18',0),('VNA01','C19',0),('VNA01','C20',0),('VNA01','D01',1),('VNA01','D02',0),('VNA01','D03',0),('VNA01','D04',0),('VNA01','D05',0),('VNA01','D06',0),('VNA01','D07',0),('VNA01','D08',0),('VNA01','D09',0),('VNA01','D10',0),('VNA01','D11',0),('VNA01','D12',0),('VNA01','D13',0),('VNA01','D14',0),('VNA01','D15',0),('VNA01','D16',0),('VNA01','D17',0),('VNA01','D18',0),('VNA01','D19',0),('VNA01','D20',0),('VNA01','S01',0),('VNA01','S02',1),('VNA01','S03',1),('VNA01','S04',1),('VNA01','S05',1),('VNA01','S06',1),('VNA01','S07',1),('VNA01','S08',0),('VNA01','S09',0),('VNA01','S10',0),('VNA01','S11',0),('VNA01','S12',0),('VNA01','S13',0),('VNA01','S14',0),('VNA01','S15',0),('VNA01','S16',0),('VNA01','S17',0),('VNA01','S18',0),('VNA01','S19',0),('VNA01','S20',0),('VNA01','S21',0),('VNA01','S22',0),('VNA01','S23',0),('VNA01','S24',0),('VNA01','S25',0),('VNA01','S26',0),('VNA01','S27',0),('VNA01','S28',0),('VNA01','S29',0),('VNA01','S30',0);
/*!40000 ALTER TABLE `maybay_ghe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieudatcho`
--

DROP TABLE IF EXISTS `phieudatcho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieudatcho` (
  `maPhieu` int NOT NULL AUTO_INCREMENT,
  `maVe` int NOT NULL,
  `tenKH` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ngayDatVe` varchar(19) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`maPhieu`),
  KEY `FK_phieudatcho_maVe_idx` (`maVe`),
  KEY `FK_phieudatcho_tenKH_idx` (`tenKH`),
  CONSTRAINT `FK_phieudatcho_maVe` FOREIGN KEY (`maVe`) REFERENCES `vemaybay` (`maVe`),
  CONSTRAINT `FK_phieudatcho_tenKH` FOREIGN KEY (`tenKH`) REFERENCES `khachhang` (`tenKH`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieudatcho`
--

LOCK TABLES `phieudatcho` WRITE;
/*!40000 ALTER TABLE `phieudatcho` DISABLE KEYS */;
INSERT INTO `phieudatcho` VALUES (1,1,'Lê Thị B','04:00:00 20-01-2021'),(2,2,'Hoành Văn E','05:00:00 15-02-2021'),(3,3,'Trương G','06:00:00 27-02-2021'),(4,4,'Huỳnh N','07:00:00 02-03-2021'),(5,8,'rưerwe','03:41:26 10-05-2021'),(6,9,'ửewrew','03:41:57 10-05-2021'),(7,10,'dưqeeqw','03:42:59 10-05-2021'),(8,11,'dfdfds','04:00:37 10-05-2021'),(9,12,'sadds','04:00:55 10-05-2021'),(10,13,'23','04:01:15 10-05-2021'),(11,14,'e3432','04:01:57 10-05-2021'),(12,15,'qưqwqwqw','04:02:31 10-05-2021'),(13,16,'sadsadsad','04:02:57 10-05-2021'),(14,17,'ewqqw','04:03:12 10-05-2021'),(15,18,'dssdas','04:03:27 10-05-2021'),(16,19,'121212','04:03:47 10-05-2021'),(17,20,'âsasasas','04:04:23 10-05-2021'),(18,21,'sdsad','04:33:29 10-05-2021'),(19,22,'1212','04:33:44 10-05-2021'),(20,23,'12','04:34:08 10-05-2021'),(21,24,'213','04:34:23 10-05-2021'),(22,25,'ưqe','04:35:16 10-05-2021'),(23,26,'23','04:35:29 10-05-2021'),(24,27,'ưqewqe','04:35:50 10-05-2021');
/*!40000 ALTER TABLE `phieudatcho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanbay`
--

DROP TABLE IF EXISTS `sanbay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanbay` (
  `maSanBay` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenSanBay` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `diaDiem` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `quocGia` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `trangThai` tinyint(1) NOT NULL,
  PRIMARY KEY (`maSanBay`),
  KEY `FK_sanbay_maSanBay_idx` (`maSanBay`),
  KEY `FK_sanbay_tenSanBay_idx` (`tenSanBay`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanbay`
--

LOCK TABLES `sanbay` WRITE;
/*!40000 ALTER TABLE `sanbay` DISABLE KEYS */;
INSERT INTO `sanbay` VALUES ('1','Tân Sơn Nhất','TP.Hồ Chí Minh','Việt Nam',1),('2','Mộc Bài','Hà Nội','Việt Nam',1);
/*!40000 ALTER TABLE `sanbay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanbay_maybay`
--

DROP TABLE IF EXISTS `sanbay_maybay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanbay_maybay` (
  `maSanBay` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `soHieuMayBay` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ngayDauTaiSanBay` varchar(19) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`maSanBay`,`soHieuMayBay`),
  KEY `FK_sanbay_maybay_maSanBay_idx` (`maSanBay`),
  KEY `FK_sanbay_maybay_soHieuMayBay_idx` (`soHieuMayBay`),
  CONSTRAINT `FK_sanbay_maybay_maSanBay` FOREIGN KEY (`maSanBay`) REFERENCES `sanbay` (`maSanBay`),
  CONSTRAINT `FK_sanbay_maybay_soHieuMayBay` FOREIGN KEY (`soHieuMayBay`) REFERENCES `maybay` (`soHieuMayBay`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanbay_maybay`
--

LOCK TABLES `sanbay_maybay` WRITE;
/*!40000 ALTER TABLE `sanbay_maybay` DISABLE KEYS */;
INSERT INTO `sanbay_maybay` VALUES ('1','VNA01','05:50:00 20-03-2021'),('2','BA01','20:30:00 01-01-2021');
/*!40000 ALTER TABLE `sanbay_maybay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trangthaive`
--

DROP TABLE IF EXISTS `trangthaive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trangthaive` (
  `loaiTrangThai` tinyint(1) NOT NULL DEFAULT '0',
  `trangThai` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`loaiTrangThai`),
  KEY `FK_trangThai_idx` (`trangThai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trangthaive`
--

LOCK TABLES `trangthaive` WRITE;
/*!40000 ALTER TABLE `trangthaive` DISABLE KEYS */;
INSERT INTO `trangthaive` VALUES (0,'Chưa thanh toán'),(1,'Đã thanh toán');
/*!40000 ALTER TABLE `trangthaive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL,
  `hoTen` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenTK` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `matKhau` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `trangThai` tinyint(1) NOT NULL DEFAULT '1',
  `idLoaiTK` int NOT NULL,
  `idCard` varchar(12) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sdt` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_users_id_idx` (`id`),
  KEY `FK_users_hoTen_idx` (`hoTen`),
  KEY `FK_users_idLoaiTK_idx` (`idLoaiTK`),
  CONSTRAINT `FK_users_loaitk` FOREIGN KEY (`idLoaiTK`) REFERENCES `loaitk` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (0,'ADMIN','Admin','123456',1,3,'000000000000','admin@gmail.com','1111111111'),(1,'Nhân Viên 01','NV01','123456',1,1,'023456789','nhanvien01@gmail.com','0987654321'),(2,'Nhân Viên 02','NV02','123456',1,1,'023456799','nhanvien02@gmail.com','0987654322'),(3,'Phạm Anh D','DPham','123456',1,2,'012345678910','phamanhdg@gmail.com','0345678921'),(4,'Nguyễn Thị Diễm M','MNguyen','123456',1,2,'012345678911','nguyenthidiemm@gmail.com','0345678922');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vemaybay`
--

DROP TABLE IF EXISTS `vemaybay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vemaybay` (
  `maVe` int NOT NULL AUTO_INCREMENT,
  `hangVe` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `giaVe` decimal(10,0) NOT NULL,
  `maGhe` varchar(5) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ngayXuatVe` varchar(19) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenNguoiDat` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenKH` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `maChuyenBay` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `trangThai` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`maVe`),
  KEY `FK_vemaybay_maGhe_idx` (`maGhe`),
  KEY `FK_vemaybay_maChuyenBay_idx` (`maChuyenBay`),
  KEY `FK_vemaybay_khachhang_tenKH_idx` (`tenKH`),
  KEY `FK_vemaybay_users_tenNguoiDat_idx` (`tenNguoiDat`),
  KEY `FK_vemaybay_maVe_idx` (`maVe`),
  KEY `FK_vemaybay_hangVe_idx` (`hangVe`),
  KEY `FK_vemaybay_giaVe_idx` (`giaVe`),
  KEY `FK_vemaybay_trangThai_idx` (`trangThai`),
  CONSTRAINT `FK_vemaybay_giaVe` FOREIGN KEY (`giaVe`) REFERENCES `giave` (`giaVe`),
  CONSTRAINT `FK_vemaybay_hangVe` FOREIGN KEY (`hangVe`) REFERENCES `hangve` (`hangVe`),
  CONSTRAINT `FK_vemaybay_khachhang_tenKH` FOREIGN KEY (`tenKH`) REFERENCES `khachhang` (`tenKH`),
  CONSTRAINT `FK_vemaybay_maChuyenBay` FOREIGN KEY (`maChuyenBay`) REFERENCES `chuyenbay` (`maChuyenBay`),
  CONSTRAINT `FK_vemaybay_maGhe` FOREIGN KEY (`maGhe`) REFERENCES `ghe` (`maGhe`),
  CONSTRAINT `FK_vemaybay_trangThai` FOREIGN KEY (`trangThai`) REFERENCES `trangthaive` (`trangThai`),
  CONSTRAINT `FK_vemaybay_users_tenNguoiDat` FOREIGN KEY (`tenNguoiDat`) REFERENCES `users` (`hoTen`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vemaybay`
--

LOCK TABLES `vemaybay` WRITE;
/*!40000 ALTER TABLE `vemaybay` DISABLE KEYS */;
INSERT INTO `vemaybay` VALUES (1,'Thương gia',200000,'A01','04:00:00 20-01-2021','Nhân Viên 01','Lê Thị B','1','Đã thanh toán'),(2,'Phổ thông',100000,'C01','05:00:00 15-02-2021','Nhân Viên 02','Hoành Văn E','1','Đã thanh toán'),(3,'Thương gia',150000,'S01','06:00:00 27-02-2021','Nhân Viên 01','Trương G','2','Đã thanh toán'),(4,'Phổ thông',90000,'B01','07:00:00 02-03-2021','Nhân Viên 02','Huỳnh N','2','Đã thanh toán'),(5,'Phổ thông',100000,'D01','05:50:00 10-03-2021','Phạm Anh D','Phạm Anh D','1','Đã thanh toán'),(6,'Thương gia',150000,'S02','22:20:21 15-03-2021','Nguyễn Thị Diễm M','Nguyễn Thị Diễm M','2','Chưa thanh toán'),(7,'Phổ thông',90000,'S03','18:22:31 20-04-2021','Nguyễn Thị Diễm M','Trần Thị C','1','Chưa thanh toán'),(8,'Phổ thông',100000,'A03','03:41:26 10-05-2021','Nhân Viên 01','rưerwe','1','Đã thanh toán'),(9,'Phổ thông',100000,'A04','03:41:57 10-05-2021','Nhân Viên 01','ửewrew','1','Đã thanh toán'),(10,'Phổ thông',100000,'A02','03:42:59 10-05-2021','Nhân Viên 02','dưqeeqw','1','Đã thanh toán'),(11,'Phổ thông',100000,'A08','04:00:37 10-05-2021','Nhân Viên 01','dfdfds','1','Đã thanh toán'),(12,'Thương gia',150000,'S05','04:00:55 10-05-2021','Nhân Viên 01','sadds','2','Đã thanh toán'),(13,'Phổ thông',90000,'A03','04:01:15 10-05-2021','Nhân Viên 01','23','2','Đã thanh toán'),(14,'Phổ thông',90000,'A01','04:01:57 10-05-2021','Nhân Viên 01','e3432','2','Đã thanh toán'),(15,'Phổ thông',100000,'A06','04:02:31 10-05-2021','Nhân Viên 02','qưqwqwqw','1','Đã thanh toán'),(16,'Phổ thông',90000,'A04','04:02:57 10-05-2021','Nhân Viên 02','sadsadsad','2','Đã thanh toán'),(17,'Thương gia',200000,'S04','04:03:12 10-05-2021','Nhân Viên 02','ewqqw','1','Đã thanh toán'),(18,'Thương gia',150000,'S04','04:03:27 10-05-2021','Nhân Viên 02','dssdas','2','Đã thanh toán'),(19,'Thương gia',150000,'S08','04:03:47 10-05-2021','Nhân Viên 02','121212','2','Đã thanh toán'),(20,'Thương gia',200000,'S02','04:04:23 10-05-2021','Nhân Viên 02','âsasasas','1','Đã thanh toán'),(21,'Thương gia',200000,'S06','04:33:29 10-05-2021','Nhân Viên 01','sdsad','1','Chưa thanh toán'),(22,'Phổ thông',100000,'A07','04:33:44 10-05-2021','Nhân Viên 01','1212','1','Chưa thanh toán'),(23,'Thương gia',200000,'S05','04:34:08 10-05-2021','Nhân Viên 01','12','1','Chưa thanh toán'),(24,'Phổ thông',90000,'A05','04:34:23 10-05-2021','Nhân Viên 01','213','2','Chưa thanh toán'),(25,'Phổ thông',100000,'A09','04:35:16 10-05-2021','Nhân Viên 02','ưqe','1','Chưa thanh toán'),(26,'Thương gia',150000,'S07','04:35:29 10-05-2021','Nhân Viên 02','23','2','Chưa thanh toán'),(27,'Thương gia',200000,'S07','04:35:50 10-05-2021','Nhân Viên 02','ưqewqe','1','Chưa thanh toán');
/*!40000 ALTER TABLE `vemaybay` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-10 10:16:01
