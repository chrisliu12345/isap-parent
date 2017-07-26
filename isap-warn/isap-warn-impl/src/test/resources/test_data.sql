-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: isap
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `t_alert_log_template`
--

DROP TABLE IF EXISTS `t_alert_log_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_alert_log_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `log_type` tinyint(4) NOT NULL,
  `content` varchar(100) NOT NULL,
  `params_count` tinyint(4) NOT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `status_UNIQUE` (`log_type`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_alert_log_template`
--

LOCK TABLES `t_alert_log_template` WRITE;
/*!40000 ALTER TABLE `t_alert_log_template` DISABLE KEYS */;
INSERT INTO `t_alert_log_template` VALUES (1,0,'%2$s 将警情标记为 %3$s，%1$s',3,'2017-05-09 12:03:29'),(2,1,'%2$s 联系保安 %3$s 出警，%1$s',3,'2017-05-09 12:03:29'),(3,2,'%2$s 联系保安 %3$s 失败，原因：%4$s，%1$s',4,'2017-05-09 14:12:46'),(4,20,'%2$s 联系不到保安，警情处理失败，%1$s',2,'2017-05-09 17:32:50'),(5,3,'%2$s 确认保安 %3$s 到达现场，%1$s',3,'2017-05-09 17:32:50'),(6,21,'%2$s 确认保安 %3$s 未到达现场，原因：%4$s，警情处理失败，%1$s',4,'2017-05-09 17:32:50'),(7,10,'%2$s 收到保安 %3$s 二次回电，内容：%4$s，%1$s',4,'2017-05-09 17:32:50'),(8,11,'%2$s 记录保安 %3$s 找到可疑人员，警情处理结束，%1$s',4,'2017-05-09 17:32:50'),(9,22,'%2$s 30 分钟内未收到保安回电，警情处理失败，%1$s',2,'2017-05-09 17:32:50'),(10,12,'%2$s 30 分钟内未收到保安二次回电，警情处理结束，%1$s',2,'2017-05-09 17:32:50');
/*!40000 ALTER TABLE `t_alert_log_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_department`
--

DROP TABLE IF EXISTS `t_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_department` (
  `id` char(36) NOT NULL,
  `name` varchar(128) NOT NULL,
  `parent` char(36) NOT NULL,
  `community_flag` tinyint(4) NOT NULL DEFAULT '0',
  `parent_ids` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `parent_ids` (`parent_ids`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_department`
--

LOCK TABLES `t_department` WRITE;
/*!40000 ALTER TABLE `t_department` DISABLE KEYS */;
INSERT INTO `t_department` VALUES ('480421ca-3d22-11e7-b6d1-4ccc6aa6e080','江西省','eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080',0,'eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080'),('4805686f-3d22-11e7-b6d1-4ccc6aa6e080','南昌市','480421ca-3d22-11e7-b6d1-4ccc6aa6e080',0,'eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080,480421ca-3d22-11e7-b6d1-4ccc6aa6e080'),('4805694d-3d22-11e7-b6d1-4ccc6aa6e080','高新区','4805686f-3d22-11e7-b6d1-4ccc6aa6e080',0,'eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080,480421ca-3d22-11e7-b6d1-4ccc6aa6e080,4805686f-3d22-11e7-b6d1-4ccc6aa6e080'),('48056976-3d22-11e7-b6d1-4ccc6aa6e080','艾溪湖北路','4805694d-3d22-11e7-b6d1-4ccc6aa6e080',0,'eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080,480421ca-3d22-11e7-b6d1-4ccc6aa6e080,4805686f-3d22-11e7-b6d1-4ccc6aa6e080,4805694d-3d22-11e7-b6d1-4ccc6aa6e080'),('ad87499e-4d87-11e7-9107-02004c4f4f50','万科海上传奇','f86a0c7d-4d86-11e7-9107-02004c4f4f50',0,'eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080,480421ca-3d22-11e7-b6d1-4ccc6aa6e080,4805686f-3d22-11e7-b6d1-4ccc6aa6e080,4805694d-3d22-11e7-b6d1-4ccc6aa6e080,f86a0c7d-4d86-11e7-9107-02004c4f4f50'),('ad88fc6c-4d87-11e7-9107-02004c4f4f50','南昌三中','f86a0c7d-4d86-11e7-9107-02004c4f4f50',0,'eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080,480421ca-3d22-11e7-b6d1-4ccc6aa6e080,4805686f-3d22-11e7-b6d1-4ccc6aa6e080,4805694d-3d22-11e7-b6d1-4ccc6aa6e080,f86a0c7d-4d86-11e7-9107-02004c4f4f50'),('ad88fd53-4d87-11e7-9107-02004c4f4f50','芳沁园','f86a0c7d-4d86-11e7-9107-02004c4f4f50',0,'eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080,480421ca-3d22-11e7-b6d1-4ccc6aa6e080,4805686f-3d22-11e7-b6d1-4ccc6aa6e080,4805694d-3d22-11e7-b6d1-4ccc6aa6e080,f86a0c7d-4d86-11e7-9107-02004c4f4f50'),('ad88fda6-4d87-11e7-9107-02004c4f4f50','樱桃苑','f86a0c7d-4d86-11e7-9107-02004c4f4f50',0,'eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080,480421ca-3d22-11e7-b6d1-4ccc6aa6e080,4805686f-3d22-11e7-b6d1-4ccc6aa6e080,4805694d-3d22-11e7-b6d1-4ccc6aa6e080,f86a0c7d-4d86-11e7-9107-02004c4f4f50'),('ad88fe67-4d87-11e7-9107-02004c4f4f50','铃兰苑','f86a0c7d-4d86-11e7-9107-02004c4f4f50',0,'eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080,480421ca-3d22-11e7-b6d1-4ccc6aa6e080,4805686f-3d22-11e7-b6d1-4ccc6aa6e080,4805694d-3d22-11e7-b6d1-4ccc6aa6e080,f86a0c7d-4d86-11e7-9107-02004c4f4f50'),('ad88fea7-4d87-11e7-9107-02004c4f4f50','青竹苑','f86a0c7d-4d86-11e7-9107-02004c4f4f50',0,'eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080,480421ca-3d22-11e7-b6d1-4ccc6aa6e080,4805686f-3d22-11e7-b6d1-4ccc6aa6e080,4805694d-3d22-11e7-b6d1-4ccc6aa6e080,f86a0c7d-4d86-11e7-9107-02004c4f4f50'),('ad88ff71-4d87-11e7-9107-02004c4f4f50','山茶苑','f86a0c7d-4d86-11e7-9107-02004c4f4f50',0,'eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080,480421ca-3d22-11e7-b6d1-4ccc6aa6e080,4805686f-3d22-11e7-b6d1-4ccc6aa6e080,4805694d-3d22-11e7-b6d1-4ccc6aa6e080,f86a0c7d-4d86-11e7-9107-02004c4f4f50'),('cd5b5553-4d86-11e7-9107-02004c4f4f50','绿地铂骊酒店','48056976-3d22-11e7-b6d1-4ccc6aa6e080',0,'eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080,480421ca-3d22-11e7-b6d1-4ccc6aa6e080,4805686f-3d22-11e7-b6d1-4ccc6aa6e080,4805694d-3d22-11e7-b6d1-4ccc6aa6e080,48056976-3d22-11e7-b6d1-4ccc6aa6e080'),('cd60e35a-4d86-11e7-9107-02004c4f4f50','艾溪康桥','48056976-3d22-11e7-b6d1-4ccc6aa6e080',0,'eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080,480421ca-3d22-11e7-b6d1-4ccc6aa6e080,4805686f-3d22-11e7-b6d1-4ccc6aa6e080,4805694d-3d22-11e7-b6d1-4ccc6aa6e080,48056976-3d22-11e7-b6d1-4ccc6aa6e080'),('cd60e42e-4d86-11e7-9107-02004c4f4f50','101 智慧大厦','48056976-3d22-11e7-b6d1-4ccc6aa6e080',0,'eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080,480421ca-3d22-11e7-b6d1-4ccc6aa6e080,4805686f-3d22-11e7-b6d1-4ccc6aa6e080,4805694d-3d22-11e7-b6d1-4ccc6aa6e080,48056976-3d22-11e7-b6d1-4ccc6aa6e080'),('cd60e49b-4d86-11e7-9107-02004c4f4f50','创业梦想街区','48056976-3d22-11e7-b6d1-4ccc6aa6e080',0,'eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080,480421ca-3d22-11e7-b6d1-4ccc6aa6e080,4805686f-3d22-11e7-b6d1-4ccc6aa6e080,4805694d-3d22-11e7-b6d1-4ccc6aa6e080,48056976-3d22-11e7-b6d1-4ccc6aa6e080'),('eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080','中国','eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080',0,'eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080'),('eb6edb4a-3d21-11e7-b6d1-4ccc6aa6e080','浙江省','eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080',0,'eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080'),('eb6edcce-3d21-11e7-b6d1-4ccc6aa6e080','杭州市','eb6edb4a-3d21-11e7-b6d1-4ccc6aa6e080',0,'eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080,eb6edb4a-3d21-11e7-b6d1-4ccc6aa6e080'),('eb6edcfe-3d21-11e7-b6d1-4ccc6aa6e080','下沙区','eb6edcce-3d21-11e7-b6d1-4ccc6aa6e080',0,'eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080,eb6edb4a-3d21-11e7-b6d1-4ccc6aa6e080,eb6edcce-3d21-11e7-b6d1-4ccc6aa6e080'),('eb6eddbc-3d21-11e7-b6d1-4ccc6aa6e080','2号大街','eb6edcfe-3d21-11e7-b6d1-4ccc6aa6e080',0,'eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080,eb6edb4a-3d21-11e7-b6d1-4ccc6aa6e080,eb6edcce-3d21-11e7-b6d1-4ccc6aa6e080,eb6edcfe-3d21-11e7-b6d1-4ccc6aa6e080'),('eb6ede89-3d21-11e7-b6d1-4ccc6aa6e080','智慧谷产业园','eb6eddbc-3d21-11e7-b6d1-4ccc6aa6e080',1,'eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080,eb6edb4a-3d21-11e7-b6d1-4ccc6aa6e080,eb6edcce-3d21-11e7-b6d1-4ccc6aa6e080,eb6edcfe-3d21-11e7-b6d1-4ccc6aa6e080,eb6eddbc-3d21-11e7-b6d1-4ccc6aa6e080'),('f86a0c7d-4d86-11e7-9107-02004c4f4f50','艾溪湖西路','4805694d-3d22-11e7-b6d1-4ccc6aa6e080',0,'eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080,480421ca-3d22-11e7-b6d1-4ccc6aa6e080,4805686f-3d22-11e7-b6d1-4ccc6aa6e080,4805694d-3d22-11e7-b6d1-4ccc6aa6e080'),('fccd3ca0-31fd-11e7-be49-4ccc6aa6e080','绿地玫瑰城','48056976-3d22-11e7-b6d1-4ccc6aa6e080',1,'eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080,480421ca-3d22-11e7-b6d1-4ccc6aa6e080,4805686f-3d22-11e7-b6d1-4ccc6aa6e080,4805694d-3d22-11e7-b6d1-4ccc6aa6e080,48056976-3d22-11e7-b6d1-4ccc6aa6e080');
/*!40000 ALTER TABLE `t_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_plat`
--

DROP TABLE IF EXISTS `t_plat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_plat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT '平台名称',
  `vendor_type` tinyint(4) unsigned NOT NULL COMMENT '厂家类型',
  `access_type` tinyint(4) unsigned NOT NULL COMMENT '接入类型',
  `access_ip_address` varchar(36) NOT NULL COMMENT '接入IP地址',
  `access_port` int(10) unsigned NOT NULL COMMENT '接入端口',
  `login_user` varchar(128) NOT NULL COMMENT '用户名',
  `login_passwd` varchar(64) NOT NULL COMMENT '用户密码',
  `status` tinyint(4) unsigned NOT NULL COMMENT '接入状态',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '平台描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_t_plat` (`access_ip_address`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8 COMMENT='平台管理表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_plat`
--

LOCK TABLES `t_plat` WRITE;
/*!40000 ALTER TABLE `t_plat` DISABLE KEYS */;
INSERT INTO `t_plat` VALUES (1,'大华',1,1,'192.168.1.1',80,'dahua','123456',1,'大华');
/*!40000 ALTER TABLE `t_plat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_device`
--

DROP TABLE IF EXISTS `t_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_device` (
  `id` char(36) NOT NULL,
  `plat_id` bigint(20) NOT NULL COMMENT '平台id',
  `code` varchar(128) NOT NULL COMMENT '设备编码',
  `name` varchar(128) NOT NULL COMMENT '设备名称',
  `dev_type` varchar(36) NOT NULL COMMENT '设备类型',
  `status` tinyint(4) unsigned NOT NULL COMMENT '设备状态',
  `net_status` tinyint(4) unsigned NOT NULL COMMENT '入网状态',
  `parent` char(36) DEFAULT NULL COMMENT '父设备id',
  `department_id` char(36) NOT NULL COMMENT '部门id',
  `location_x` varchar(36) DEFAULT NULL COMMENT '经纬度X',
  `location_y` varchar(36) DEFAULT NULL COMMENT '经纬度Y',
  `brief_name` varchar(36) DEFAULT NULL COMMENT '简名',
  `name_spell` char(10) DEFAULT NULL COMMENT '名称拼写',
  `description` varchar(255) DEFAULT NULL COMMENT '设备描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_t_device` (`plat_id`,`code`) USING BTREE,
  KEY `fk_t_device_plat_id` (`plat_id`) USING BTREE,
  KEY `fk_t_device_parent` (`parent`) USING BTREE,
  KEY `fk_t_device_department_id` (`department_id`) USING BTREE,
  CONSTRAINT `t_device_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `t_department` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_device_ibfk_2` FOREIGN KEY (`plat_id`) REFERENCES `t_plat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_device`
--

LOCK TABLES `t_device` WRITE;
/*!40000 ALTER TABLE `t_device` DISABLE KEYS */;
INSERT INTO `t_device` VALUES ('02b4d08b-bfc9-4a9d-9d2a-11232eefc55f',1,'02b4d08b-bfc9-4a9d-9d2a-11232eefc55f','山茶苑-南','dd',1,1,'','ad88ff71-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'02b4d08b-',NULL),('0413d268-d540-419a-b97d-5bb4cb424efe',1,'0413d268-d540-419a-b97d-5bb4cb424efe','铃兰苑-东','dd',1,1,'','ad88fe67-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'0413d268-',NULL),('0d041a1d-1f39-49b7-8f13-1b9317969bb4',1,'0d041a1d-1f39-49b7-8f13-1b9317969bb4','芳沁园-北','dd',1,1,'','ad88fd53-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'0d041a1d-',NULL),('14000b8c-d748-48ca-b05e-3dab028ed2ae',1,'14000b8c-d748-48ca-b05e-3dab028ed2ae','万科海上传奇-东','dd',1,1,'','ad87499e-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'14000b8c-',NULL),('1b1f91a4-f5a3-4d8e-8167-348f97312c51',1,'1b1f91a4-f5a3-4d8e-8167-348f97312c51','樱桃苑-南','dd',1,1,'','ad88fda6-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'1b1f91a4-',NULL),('211be4dc-eea2-4ab0-89dc-ef2fb06d5b64',1,'211be4dc-eea2-4ab0-89dc-ef2fb06d5b64','创业梦想街区-南','dd',1,1,'','cd60e49b-4d86-11e7-9107-02004c4f4f50','102','103',NULL,'211be4dc-',NULL),('232aa099-ac9f-43cc-8bf6-dc44d6b2ba3c',1,'232aa099-ac9f-43cc-8bf6-dc44d6b2ba3c','101 智慧大厦-北','dd',1,1,'','cd60e42e-4d86-11e7-9107-02004c4f4f50','102','103',NULL,'232aa099-',NULL),('2426e187-724c-4d5c-ac2b-8707c67b48b2',1,'2426e187-724c-4d5c-ac2b-8707c67b48b2','芳沁园-西','dd',1,1,'','ad88fd53-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'2426e187-',NULL),('3ab5c794-7321-4cb3-bbcb-74dda3bc741c',1,'3ab5c794-7321-4cb3-bbcb-74dda3bc741c','绿地玫瑰城-南','dd',1,1,'','fccd3ca0-31fd-11e7-be49-4ccc6aa6e080','102','103',NULL,'3ab5c794-',NULL),('3b9f84d2-5806-4bd7-8b60-e00a0d2e2a9d',1,'3b9f84d2-5806-4bd7-8b60-e00a0d2e2a9d','绿地玫瑰城-西','dd',1,1,'','fccd3ca0-31fd-11e7-be49-4ccc6aa6e080','102','103',NULL,'3b9f84d2-',NULL),('40c3340c-21bc-4c76-8658-60315668494c',1,'40c3340c-21bc-4c76-8658-60315668494c','绿地铂骊酒店-北','dd',1,1,'','cd5b5553-4d86-11e7-9107-02004c4f4f50','102','103',NULL,'40c3340c-',NULL),('482521a7-137e-4372-9985-2e828fc09c7d',1,'482521a7-137e-4372-9985-2e828fc09c7d','101 智慧大厦-南','dd',1,1,'','cd60e42e-4d86-11e7-9107-02004c4f4f50','102','103',NULL,'482521a7-',NULL),('4b1ea4e4-f2bf-4763-a533-bfc0f93834a0',1,'4b1ea4e4-f2bf-4763-a533-bfc0f93834a0','南昌三中-南','dd',1,1,'','ad88fc6c-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'4b1ea4e4-',NULL),('54e2d390-ba9c-40db-951d-3cfe5297a7c1',1,'54e2d390-ba9c-40db-951d-3cfe5297a7c1','芳沁园-东','dd',1,1,'','ad88fd53-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'54e2d390-',NULL),('5a847525-8af5-4656-800b-fb9ee1a369f6',1,'5a847525-8af5-4656-800b-fb9ee1a369f6','万科海上传奇-西','dd',1,1,'','ad87499e-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'5a847525-',NULL),('5b060511-0a40-48d9-bfbf-807e097791ca',1,'5b060511-0a40-48d9-bfbf-807e097791ca','青竹苑-北','dd',1,1,'','ad88fea7-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'5b060511-',NULL),('5fac33a9-ee15-464f-a6b5-aae528bd332f',1,'5fac33a9-ee15-464f-a6b5-aae528bd332f','山茶苑-西','dd',1,1,'','ad88ff71-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'5fac33a9-',NULL),('63107695-0bfa-4411-a027-f99ba7708eed',1,'63107695-0bfa-4411-a027-f99ba7708eed','南昌三中-东','dd',1,1,'','ad88fc6c-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'63107695-',NULL),('6572fadf-8c0a-4fb0-8243-69e754b2bc14',1,'6572fadf-8c0a-4fb0-8243-69e754b2bc14','青竹苑-西','dd',1,1,'','ad88fea7-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'6572fadf-',NULL),('6678bbbd-c237-4e76-8b45-54211c437e5d',1,'6678bbbd-c237-4e76-8b45-54211c437e5d','万科海上传奇-北','dd',1,1,'','ad87499e-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'6678bbbd-',NULL),('69c278ab-3439-48a1-a61d-2977ed50f171',1,'69c278ab-3439-48a1-a61d-2977ed50f171','万科海上传奇-南','dd',1,1,'','ad87499e-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'69c278ab-',NULL),('70492a37-21d1-4cd5-b38a-49674c0bc03e',1,'70492a37-21d1-4cd5-b38a-49674c0bc03e','绿地铂骊酒店-东','dd',1,1,'','cd5b5553-4d86-11e7-9107-02004c4f4f50','102','103',NULL,'70492a37-',NULL),('75a26baa-5c1e-40df-8678-6c568a0230b0',1,'75a26baa-5c1e-40df-8678-6c568a0230b0','樱桃苑-西','dd',1,1,'','ad88fda6-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'75a26baa-',NULL),('767b0827-450f-42f4-9bb0-01d984254b67',1,'767b0827-450f-42f4-9bb0-01d984254b67','创业梦想街区-东','dd',1,1,'','cd60e49b-4d86-11e7-9107-02004c4f4f50','102','103',NULL,'767b0827-',NULL),('76b3b679-7c0f-4d7e-94fb-265585b0d2de',1,'76b3b679-7c0f-4d7e-94fb-265585b0d2de','艾溪康桥-北','dd',1,1,'','cd60e35a-4d86-11e7-9107-02004c4f4f50','102','103',NULL,'76b3b679-',NULL),('774eeb4d-6cb1-432c-b7b7-eb70c6f11ffd',1,'774eeb4d-6cb1-432c-b7b7-eb70c6f11ffd','艾溪康桥-南','dd',1,1,'','cd60e35a-4d86-11e7-9107-02004c4f4f50','102','103',NULL,'774eeb4d-',NULL),('8f808427-35fc-46e0-aa6c-4d860185fd01',1,'8f808427-35fc-46e0-aa6c-4d860185fd01','樱桃苑-东','dd',1,1,'','ad88fda6-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'8f808427-',NULL),('92d900cc-1e23-4405-960c-ed9183d3ea1e',1,'92d900cc-1e23-4405-960c-ed9183d3ea1e','山茶苑-北','dd',1,1,'','ad88ff71-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'92d900cc-',NULL),('969952ce-0cfa-4455-8b54-f7ed3b45faef',1,'969952ce-0cfa-4455-8b54-f7ed3b45faef','101 智慧大厦-东','dd',1,1,'','cd60e42e-4d86-11e7-9107-02004c4f4f50','102','103',NULL,'969952ce-',NULL),('a0828033-a620-4a06-803e-7e5ab1a7a174',1,'a0828033-a620-4a06-803e-7e5ab1a7a174','南昌三中-西','dd',1,1,'','ad88fc6c-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'a0828033-',NULL),('a2ccc72a-ec61-4aa6-ac9e-b09b6204db97',1,'a2ccc72a-ec61-4aa6-ac9e-b09b6204db97','芳沁园-南','dd',1,1,'','ad88fd53-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'a2ccc72a-',NULL),('a3b2ae16-ac31-433d-9587-a5b6075678ef',1,'a3b2ae16-ac31-433d-9587-a5b6075678ef','创业梦想街区-北','dd',1,1,'','cd60e49b-4d86-11e7-9107-02004c4f4f50','102','103',NULL,'a3b2ae16-',NULL),('ac6727a5-b70b-4cad-baa1-bae0a12932f3',1,'ac6727a5-b70b-4cad-baa1-bae0a12932f3','青竹苑-南','dd',1,1,'','ad88fea7-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'ac6727a5-',NULL),('afcc22ed-c0ad-4e74-ac4b-dab105cec1e6',1,'afcc22ed-c0ad-4e74-ac4b-dab105cec1e6','艾溪康桥-西','dd',1,1,'','cd60e35a-4d86-11e7-9107-02004c4f4f50','102','103',NULL,'afcc22ed-',NULL),('b3847e73-295b-44f2-b6d4-8157d2932f47',1,'b3847e73-295b-44f2-b6d4-8157d2932f47','101 智慧大厦-西','dd',1,1,'','cd60e42e-4d86-11e7-9107-02004c4f4f50','102','103',NULL,'b3847e73-',NULL),('b92263d8-d1fa-47a0-8fa1-9c38dd18fec6',1,'b92263d8-d1fa-47a0-8fa1-9c38dd18fec6','绿地玫瑰城-东','dd',1,1,'','fccd3ca0-31fd-11e7-be49-4ccc6aa6e080','102','103',NULL,'b92263d8-',NULL),('c11a2744-13c0-43f5-831f-d9bf3e3b6ae7',1,'c11a2744-13c0-43f5-831f-d9bf3e3b6ae7','南昌三中-北','dd',1,1,'','ad88fc6c-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'c11a2744-',NULL),('cabef01c-7f53-4657-bc83-c01bc416b772',1,'cabef01c-7f53-4657-bc83-c01bc416b772','创业梦想街区-西','dd',1,1,'','cd60e49b-4d86-11e7-9107-02004c4f4f50','102','103',NULL,'cabef01c-',NULL),('d6b81a8a-064a-4f0d-85a9-cffc3dc1c6c1',1,'d6b81a8a-064a-4f0d-85a9-cffc3dc1c6c1','绿地铂骊酒店-西','dd',1,1,'','cd5b5553-4d86-11e7-9107-02004c4f4f50','102','103',NULL,'d6b81a8a-',NULL),('d9f17e58-e0cb-415c-bcb8-669289fea87d',1,'d9f17e58-e0cb-415c-bcb8-669289fea87d','绿地铂骊酒店-南','dd',1,1,'','cd5b5553-4d86-11e7-9107-02004c4f4f50','102','103',NULL,'d9f17e58-',NULL),('dcf612ba-6eb1-470f-b1ed-f3d0fe474322',1,'dcf612ba-6eb1-470f-b1ed-f3d0fe474322','铃兰苑-西','dd',1,1,'','ad88fe67-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'dcf612ba-',NULL),('e359c1f7-69b7-472c-b270-8ce7c8b73c70',1,'e359c1f7-69b7-472c-b270-8ce7c8b73c70','铃兰苑-南','dd',1,1,'','ad88fe67-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'e359c1f7-',NULL),('e96047b7-39ca-4390-9f2d-d9e8be007b93',1,'e96047b7-39ca-4390-9f2d-d9e8be007b93','艾溪康桥-东','dd',1,1,'','cd60e35a-4d86-11e7-9107-02004c4f4f50','102','103',NULL,'e96047b7-',NULL),('ea573053-47bb-4ebf-9b0a-c38220416cee',1,'ea573053-47bb-4ebf-9b0a-c38220416cee','铃兰苑-北','dd',1,1,'','ad88fe67-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'ea573053-',NULL),('ec6ebad3-ad96-46fb-a71f-0225bc233653',1,'ec6ebad3-ad96-46fb-a71f-0225bc233653','青竹苑-东','dd',1,1,'','ad88fea7-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'ec6ebad3-',NULL),('ecb29aa0-2af2-4890-a8cd-b1f4f3841b96',1,'ecb29aa0-2af2-4890-a8cd-b1f4f3841b96','绿地玫瑰城-北','dd',1,1,'','fccd3ca0-31fd-11e7-be49-4ccc6aa6e080','102','103',NULL,'ecb29aa0-',NULL),('ed1a123c-6764-40cb-923a-3440ab2934b2',1,'ed1a123c-6764-40cb-923a-3440ab2934b2','山茶苑-东','dd',1,1,'','ad88ff71-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'ed1a123c-',NULL),('fe25a8f8-04fb-4025-8016-41c7ec69c41f',1,'fe25a8f8-04fb-4025-8016-41c7ec69c41f','樱桃苑-北','dd',1,1,'','ad88fda6-4d87-11e7-9107-02004c4f4f50','102','103',NULL,'fe25a8f8-',NULL);
/*!40000 ALTER TABLE `t_device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT '用户名称',
  `login_name` varchar(36) NOT NULL COMMENT '登录名',
  `passwd` varchar(64) NOT NULL COMMENT '用户密码',
  `mobile` char(11) DEFAULT NULL COMMENT '移动电话',
  `email` varchar(36) DEFAULT NULL COMMENT '邮箱',
  `priority` tinyint(4) unsigned NOT NULL COMMENT '用户级别',
  `status` tinyint(4) unsigned NOT NULL COMMENT '用户状态',
  `lock_start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '锁定开始时间',
  `lock_end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '锁定结束时间',
  `login_ip` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_t_user` (`login_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1099 DEFAULT CHARSET=utf8 COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'admin','admin',' ','',' ',1,1,'2017-06-10 15:14:07','2017-06-10 15:14:07',NULL),(10,'user-0-0','user-0-0','','','',1,1,'2017-06-10 14:30:17','2017-06-10 14:30:17',NULL),(11,'user-0-1','user-0-1','','','',1,1,'2017-06-10 14:30:18','2017-06-10 14:30:18',NULL),(12,'user-0-2','user-0-2','','','',1,1,'2017-06-10 14:30:18','2017-06-10 14:30:18',NULL),(13,'user-0-3','user-0-3','','','',1,1,'2017-06-10 14:30:18','2017-06-10 14:30:18',NULL),(14,'user-0-4','user-0-4','','','',1,1,'2017-06-10 14:30:18','2017-06-10 14:30:18',NULL),(20,'user-1-0','user-1-0','','','',1,1,'2017-06-10 14:30:18','2017-06-10 14:30:18',NULL),(21,'user-1-1','user-1-1','','','',1,1,'2017-06-10 14:30:18','2017-06-10 14:30:18',NULL),(22,'user-1-2','user-1-2','','','',1,1,'2017-06-10 14:30:18','2017-06-10 14:30:18',NULL),(23,'user-1-3','user-1-3','','','',1,1,'2017-06-10 14:30:18','2017-06-10 14:30:18',NULL),(24,'user-1-4','user-1-4','','','',1,1,'2017-06-10 14:30:18','2017-06-10 14:30:18',NULL),(30,'user-2-0','user-2-0','','','',1,1,'2017-06-10 14:30:18','2017-06-10 14:30:18',NULL),(31,'user-2-1','user-2-1','','','',1,1,'2017-06-10 14:30:18','2017-06-10 14:30:18',NULL),(32,'user-2-2','user-2-2','','','',1,1,'2017-06-10 14:30:18','2017-06-10 14:30:18',NULL),(33,'user-2-3','user-2-3','','','',1,1,'2017-06-10 14:30:18','2017-06-10 14:30:18',NULL),(34,'user-2-4','user-2-4','','','',1,1,'2017-06-10 14:30:18','2017-06-10 14:30:18',NULL),(40,'user-3-0','user-3-0','','','',1,1,'2017-06-10 14:30:18','2017-06-10 14:30:18',NULL),(41,'user-3-1','user-3-1','','','',1,1,'2017-06-10 14:30:18','2017-06-10 14:30:18',NULL),(42,'user-3-2','user-3-2','','','',1,1,'2017-06-10 14:30:19','2017-06-10 14:30:19',NULL),(43,'user-3-3','user-3-3','','','',1,1,'2017-06-10 14:30:19','2017-06-10 14:30:19',NULL),(44,'user-3-4','user-3-4','','','',1,1,'2017-06-10 14:30:19','2017-06-10 14:30:19',NULL);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8 COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES (10,'role-0-0'),(11,'role-0-1'),(12,'role-0-2'),(13,'role-0-3'),(14,'role-0-4'),(20,'role-1-0'),(21,'role-1-1'),(22,'role-1-2'),(23,'role-1-3'),(24,'role-1-4'),(30,'role-2-0'),(31,'role-2-1'),(32,'role-2-2'),(33,'role-2-3'),(34,'role-2-4');
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_belong`
--

DROP TABLE IF EXISTS `t_user_belong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user_belong` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  KEY `fk_t_user_belong_user_id` (`user_id`) USING BTREE,
  KEY `fk_t_user_belong_role_id` (`role_id`) USING BTREE,
  CONSTRAINT `t_user_belong_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_user_belong_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT='用户所属信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_belong`
--

LOCK TABLES `t_user_belong` WRITE;
/*!40000 ALTER TABLE `t_user_belong` DISABLE KEYS */;
INSERT INTO `t_user_belong` VALUES (15,10,10),(16,11,11),(17,12,12),(18,13,13),(19,14,14),(20,20,20),(21,21,21),(22,22,22),(23,23,23),(24,24,24),(25,30,30),(26,31,31),(27,32,32),(28,33,33),(29,34,34),(30,40,30),(31,40,20),(32,40,10),(33,41,31),(34,41,21),(35,41,11),(36,42,32),(37,42,22),(38,42,12),(39,43,33),(40,43,23),(41,43,13),(42,44,34),(43,44,24),(44,44,14);
/*!40000 ALTER TABLE `t_user_belong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_menu_authdef`
--

DROP TABLE IF EXISTS `t_menu_authdef`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_menu_authdef` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT '菜单权限名称',
  `parent` bigint(20) NOT NULL DEFAULT '0' COMMENT '父权限',
  `permission` varchar(50) NOT NULL COMMENT '权限标识',
  PRIMARY KEY (`id`),
  KEY `fk_t_menu_authdef_parent` (`parent`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8 COMMENT='菜单权限信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_menu_authdef`
--

LOCK TABLES `t_menu_authdef` WRITE;
/*!40000 ALTER TABLE `t_menu_authdef` DISABLE KEYS */;
INSERT INTO `t_menu_authdef` VALUES (1,'实况',0,'01'),(2,'告警',0,'02'),(3,'系统资源',0,'03'),(4,'资源管理',0,'04'),(5,'人脸布控',0,'05'),(6,'警情',0,'06'),(7,'实时警告',2,'0201'),(8,'历史告警',2,'0202'),(9,'告警联动',2,'0203'),(10,'布防计划',2,'0204'),(11,'时间模板',2,'0205'),(12,'基础平台',3,'0301'),(13,'配置管理',3,'0302'),(14,'系统状态',3,'0303'),(15,'日志管理',3,'0304'),(16,'用户管理',4,'0401'),(17,'角色管理',4,'0402'),(18,'组织管理',4,'0403'),(19,'设备管理',4,'0404'),(20,'设备划归',4,'0405'),(21,'监控预警',5,'0501'),(22,'名单管理',5,'0502'),(23,'人脸查询',5,'0503'),(24,'数据统计',5,'0504'),(25,'警情列表',6,'0601'),(26,'警情处理',6,'0602'),(27,'保安管理',6,'0603');
/*!40000 ALTER TABLE `t_menu_authdef` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_menu_auth`
--

DROP TABLE IF EXISTS `t_role_menu_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role_menu_auth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `auth_id` bigint(20) NOT NULL COMMENT '菜单权限id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_t_role_menu_auth` (`role_id`,`auth_id`) USING BTREE,
  KEY `fk_t_role_menu_auth_role_id` (`role_id`) USING BTREE,
  KEY `fk_t_role_menu_auth_auth_id` (`auth_id`) USING BTREE,
  CONSTRAINT `t_role_menu_auth_ibfk_1` FOREIGN KEY (`auth_id`) REFERENCES `t_menu_authdef` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_role_menu_auth_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COMMENT='角色菜单权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_menu_auth`
--

LOCK TABLES `t_role_menu_auth` WRITE;
/*!40000 ALTER TABLE `t_role_menu_auth` DISABLE KEYS */;
INSERT INTO `t_role_menu_auth` VALUES (69,10,25),(70,11,25),(71,12,25),(72,13,25),(73,14,25),(74,20,26),(75,21,26),(76,22,26),(77,23,26),(78,24,26),(79,30,27),(80,31,27),(81,32,27),(82,33,27),(83,34,27);
/*!40000 ALTER TABLE `t_role_menu_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_dep`
--

DROP TABLE IF EXISTS `t_role_dep`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role_dep` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `department_id` char(36) NOT NULL COMMENT '部门id',
  PRIMARY KEY (`id`),
  KEY `fk_t_role_dep_role_id` (`role_id`) USING BTREE,
  KEY `fk_t_role_dep_department_id` (`department_id`) USING BTREE,
  CONSTRAINT `t_role_dep_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `t_department` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_role_dep_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8 COMMENT='角色部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_dep`
--

LOCK TABLES `t_role_dep` WRITE;
/*!40000 ALTER TABLE `t_role_dep` DISABLE KEYS */;
INSERT INTO `t_role_dep` VALUES (1,10,'4805686f-3d22-11e7-b6d1-4ccc6aa6e080'),(2,11,'48056976-3d22-11e7-b6d1-4ccc6aa6e080'),(3,11,'f86a0c7d-4d86-11e7-9107-02004c4f4f50'),(4,12,'48056976-3d22-11e7-b6d1-4ccc6aa6e080'),(5,12,'ad87499e-4d87-11e7-9107-02004c4f4f50'),(6,12,'ad88fc6c-4d87-11e7-9107-02004c4f4f50'),(7,12,'ad88fd53-4d87-11e7-9107-02004c4f4f50'),(8,12,'ad88fda6-4d87-11e7-9107-02004c4f4f50'),(9,12,'ad88fe67-4d87-11e7-9107-02004c4f4f50'),(10,12,'ad88fea7-4d87-11e7-9107-02004c4f4f50'),(11,12,'ad88ff71-4d87-11e7-9107-02004c4f4f50'),(12,13,'cd5b5553-4d86-11e7-9107-02004c4f4f50'),(13,13,'cd60e35a-4d86-11e7-9107-02004c4f4f50'),(14,13,'cd60e42e-4d86-11e7-9107-02004c4f4f50'),(15,13,'cd60e49b-4d86-11e7-9107-02004c4f4f50'),(16,13,'fccd3ca0-31fd-11e7-be49-4ccc6aa6e080'),(17,13,'ad87499e-4d87-11e7-9107-02004c4f4f50'),(18,13,'ad88fc6c-4d87-11e7-9107-02004c4f4f50'),(19,13,'ad88fd53-4d87-11e7-9107-02004c4f4f50'),(20,13,'ad88fda6-4d87-11e7-9107-02004c4f4f50'),(21,13,'ad88fe67-4d87-11e7-9107-02004c4f4f50'),(22,13,'ad88fea7-4d87-11e7-9107-02004c4f4f50'),(23,13,'ad88ff71-4d87-11e7-9107-02004c4f4f50'),(24,14,'48056976-3d22-11e7-b6d1-4ccc6aa6e080'),(25,14,'f86a0c7d-4d86-11e7-9107-02004c4f4f50'),(26,14,'ad87499e-4d87-11e7-9107-02004c4f4f50'),(27,14,'ad88fc6c-4d87-11e7-9107-02004c4f4f50'),(28,14,'ad88fd53-4d87-11e7-9107-02004c4f4f50'),(29,14,'ad88fda6-4d87-11e7-9107-02004c4f4f50'),(30,14,'ad88fe67-4d87-11e7-9107-02004c4f4f50'),(31,14,'ad88fea7-4d87-11e7-9107-02004c4f4f50'),(32,14,'ad88ff71-4d87-11e7-9107-02004c4f4f50'),(33,20,'4805686f-3d22-11e7-b6d1-4ccc6aa6e080'),(34,21,'48056976-3d22-11e7-b6d1-4ccc6aa6e080'),(35,21,'f86a0c7d-4d86-11e7-9107-02004c4f4f50'),(36,22,'48056976-3d22-11e7-b6d1-4ccc6aa6e080'),(37,22,'ad87499e-4d87-11e7-9107-02004c4f4f50'),(38,22,'ad88fc6c-4d87-11e7-9107-02004c4f4f50'),(39,22,'ad88fd53-4d87-11e7-9107-02004c4f4f50'),(40,22,'ad88fda6-4d87-11e7-9107-02004c4f4f50'),(41,22,'ad88fe67-4d87-11e7-9107-02004c4f4f50'),(42,22,'ad88fea7-4d87-11e7-9107-02004c4f4f50'),(43,22,'ad88ff71-4d87-11e7-9107-02004c4f4f50'),(44,23,'cd5b5553-4d86-11e7-9107-02004c4f4f50'),(45,23,'cd60e35a-4d86-11e7-9107-02004c4f4f50'),(46,23,'cd60e42e-4d86-11e7-9107-02004c4f4f50'),(47,23,'cd60e49b-4d86-11e7-9107-02004c4f4f50'),(48,23,'fccd3ca0-31fd-11e7-be49-4ccc6aa6e080'),(49,23,'ad87499e-4d87-11e7-9107-02004c4f4f50'),(50,23,'ad88fc6c-4d87-11e7-9107-02004c4f4f50'),(51,23,'ad88fd53-4d87-11e7-9107-02004c4f4f50'),(52,23,'ad88fda6-4d87-11e7-9107-02004c4f4f50'),(53,23,'ad88fe67-4d87-11e7-9107-02004c4f4f50'),(54,23,'ad88fea7-4d87-11e7-9107-02004c4f4f50'),(55,23,'ad88ff71-4d87-11e7-9107-02004c4f4f50'),(56,24,'48056976-3d22-11e7-b6d1-4ccc6aa6e080'),(57,24,'f86a0c7d-4d86-11e7-9107-02004c4f4f50'),(58,24,'ad87499e-4d87-11e7-9107-02004c4f4f50'),(59,24,'ad88fc6c-4d87-11e7-9107-02004c4f4f50'),(60,24,'ad88fd53-4d87-11e7-9107-02004c4f4f50'),(61,24,'ad88fda6-4d87-11e7-9107-02004c4f4f50'),(62,24,'ad88fe67-4d87-11e7-9107-02004c4f4f50'),(63,24,'ad88fea7-4d87-11e7-9107-02004c4f4f50'),(64,24,'ad88ff71-4d87-11e7-9107-02004c4f4f50'),(65,30,'4805686f-3d22-11e7-b6d1-4ccc6aa6e080'),(66,31,'48056976-3d22-11e7-b6d1-4ccc6aa6e080'),(67,31,'f86a0c7d-4d86-11e7-9107-02004c4f4f50'),(68,32,'48056976-3d22-11e7-b6d1-4ccc6aa6e080'),(69,32,'ad87499e-4d87-11e7-9107-02004c4f4f50'),(70,32,'ad88fc6c-4d87-11e7-9107-02004c4f4f50'),(71,32,'ad88fd53-4d87-11e7-9107-02004c4f4f50'),(72,32,'ad88fda6-4d87-11e7-9107-02004c4f4f50'),(73,32,'ad88fe67-4d87-11e7-9107-02004c4f4f50'),(74,32,'ad88fea7-4d87-11e7-9107-02004c4f4f50'),(75,32,'ad88ff71-4d87-11e7-9107-02004c4f4f50'),(76,33,'cd5b5553-4d86-11e7-9107-02004c4f4f50'),(77,33,'cd60e35a-4d86-11e7-9107-02004c4f4f50'),(78,33,'cd60e42e-4d86-11e7-9107-02004c4f4f50'),(79,33,'cd60e49b-4d86-11e7-9107-02004c4f4f50'),(80,33,'fccd3ca0-31fd-11e7-be49-4ccc6aa6e080'),(81,33,'ad87499e-4d87-11e7-9107-02004c4f4f50'),(82,33,'ad88fc6c-4d87-11e7-9107-02004c4f4f50'),(83,33,'ad88fd53-4d87-11e7-9107-02004c4f4f50'),(84,33,'ad88fda6-4d87-11e7-9107-02004c4f4f50'),(85,33,'ad88fe67-4d87-11e7-9107-02004c4f4f50'),(86,33,'ad88fea7-4d87-11e7-9107-02004c4f4f50'),(87,33,'ad88ff71-4d87-11e7-9107-02004c4f4f50'),(88,34,'48056976-3d22-11e7-b6d1-4ccc6aa6e080'),(89,34,'f86a0c7d-4d86-11e7-9107-02004c4f4f50'),(90,34,'ad87499e-4d87-11e7-9107-02004c4f4f50'),(91,34,'ad88fc6c-4d87-11e7-9107-02004c4f4f50'),(92,34,'ad88fd53-4d87-11e7-9107-02004c4f4f50'),(93,34,'ad88fda6-4d87-11e7-9107-02004c4f4f50'),(94,34,'ad88fe67-4d87-11e7-9107-02004c4f4f50'),(95,34,'ad88fea7-4d87-11e7-9107-02004c4f4f50'),(96,34,'ad88ff71-4d87-11e7-9107-02004c4f4f50');
/*!40000 ALTER TABLE `t_role_dep` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_guard_group`
--

DROP TABLE IF EXISTS `t_guard_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_guard_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT '保安组名字',
  `description` varchar(255) DEFAULT NULL COMMENT '保安组描述',
  `user_id` bigint(20) NOT NULL DEFAULT '1',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='保安组信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_guard_group`
--

LOCK TABLES `t_guard_group` WRITE;
/*!40000 ALTER TABLE `t_guard_group` DISABLE KEYS */;
INSERT INTO `t_guard_group` VALUES (1,'绿地铂骊酒店',NULL,1,'2017-06-10 06:50:01','2017-06-10 06:50:01'),(2,'艾溪康桥',NULL,1,'2017-06-10 06:50:02','2017-06-10 06:50:02'),(3,'101 智慧大厦',NULL,1,'2017-06-10 06:50:02','2017-06-10 06:50:02'),(4,'创业梦想街区',NULL,1,'2017-06-10 06:50:02','2017-06-10 06:50:02'),(5,'绿地玫瑰城',NULL,1,'2017-06-10 06:50:02','2017-06-10 06:50:02'),(6,'万科海上传奇',NULL,1,'2017-06-10 06:50:02','2017-06-10 06:50:02'),(7,'芳沁园',NULL,1,'2017-06-10 06:50:02','2017-06-10 06:50:02'),(8,'铃兰苑',NULL,1,'2017-06-10 06:50:02','2017-06-10 06:50:02'),(9,'山茶苑',NULL,1,'2017-06-10 06:50:02','2017-06-10 06:50:02');
/*!40000 ALTER TABLE `t_guard_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_guard_group_belong`
--

DROP TABLE IF EXISTS `t_guard_group_belong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_guard_group_belong` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) NOT NULL COMMENT '小组 id',
  `department_id` char(36) NOT NULL COMMENT '社区 id',
  `belong_desc` varchar(255) DEFAULT NULL COMMENT '描述',
  `user_id` bigint(20) NOT NULL DEFAULT '1',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_t_guard_group_belong` (`group_id`,`department_id`),
  KEY `fk_t_guard_group_belong_department_id` (`department_id`),
  CONSTRAINT `fk_t_guard_group_belong_department_id` FOREIGN KEY (`department_id`) REFERENCES `t_department` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_t_guard_group_belong_group_id` FOREIGN KEY (`group_id`) REFERENCES `t_guard_group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='保安小组社区绑定表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_guard_group_belong`
--

LOCK TABLES `t_guard_group_belong` WRITE;
/*!40000 ALTER TABLE `t_guard_group_belong` DISABLE KEYS */;
INSERT INTO `t_guard_group_belong` VALUES (1,1,'cd5b5553-4d86-11e7-9107-02004c4f4f50',NULL,1,'2017-06-10 06:50:01','2017-06-10 06:50:01'),(2,2,'cd60e35a-4d86-11e7-9107-02004c4f4f50',NULL,1,'2017-06-10 06:50:02','2017-06-10 06:50:02'),(3,3,'cd60e42e-4d86-11e7-9107-02004c4f4f50',NULL,1,'2017-06-10 06:50:02','2017-06-10 06:50:02'),(4,4,'cd60e49b-4d86-11e7-9107-02004c4f4f50',NULL,1,'2017-06-10 06:50:02','2017-06-10 06:50:02'),(5,5,'fccd3ca0-31fd-11e7-be49-4ccc6aa6e080',NULL,1,'2017-06-10 06:50:02','2017-06-10 06:50:02'),(6,6,'ad87499e-4d87-11e7-9107-02004c4f4f50',NULL,1,'2017-06-10 06:50:02','2017-06-10 06:50:02'),(7,6,'ad88fc6c-4d87-11e7-9107-02004c4f4f50',NULL,1,'2017-06-10 06:50:02','2017-06-10 06:50:02'),(8,7,'ad88fd53-4d87-11e7-9107-02004c4f4f50',NULL,1,'2017-06-10 06:50:02','2017-06-10 06:50:02'),(9,7,'ad88fda6-4d87-11e7-9107-02004c4f4f50',NULL,1,'2017-06-10 06:50:02','2017-06-10 06:50:02'),(10,8,'ad88fe67-4d87-11e7-9107-02004c4f4f50',NULL,1,'2017-06-10 06:50:02','2017-06-10 06:50:02'),(11,8,'ad88fea7-4d87-11e7-9107-02004c4f4f50',NULL,1,'2017-06-10 06:50:02','2017-06-10 06:50:02'),(12,9,'ad88ff71-4d87-11e7-9107-02004c4f4f50',NULL,1,'2017-06-10 06:50:03','2017-06-10 06:50:03');
/*!40000 ALTER TABLE `t_guard_group_belong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_guard`
--

DROP TABLE IF EXISTS `t_guard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_guard` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL,
  `phone` char(11) NOT NULL,
  `guard_desc` varchar(255) DEFAULT NULL COMMENT '保安描述',
  `group_id` bigint(20) NOT NULL DEFAULT '0',
  `cid` char(18) DEFAULT NULL COMMENT '身份证',
  `home_address` varchar(128) DEFAULT NULL COMMENT '家庭住址',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `user_id` bigint(20) NOT NULL DEFAULT '1',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_UNIQUE` (`phone`),
  KEY `fk_t_guard_group_id` (`group_id`),
  CONSTRAINT `fk_t_guard_group_id` FOREIGN KEY (`group_id`) REFERENCES `t_guard_group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='保安信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_guard`
--

LOCK TABLES `t_guard` WRITE;
/*!40000 ALTER TABLE `t_guard` DISABLE KEYS */;
INSERT INTO `t_guard` VALUES (1,'保安-00','17012340000',NULL,1,NULL,NULL,NULL,1,'2017-06-10 07:00:10','2017-06-10 07:00:10'),(2,'保安-01','17012340001',NULL,1,NULL,NULL,NULL,1,'2017-06-10 07:00:10','2017-06-10 07:00:10'),(3,'保安-02','17012340002',NULL,1,NULL,NULL,NULL,1,'2017-06-10 07:00:10','2017-06-10 07:00:10'),(4,'保安-03','17012340003',NULL,1,NULL,NULL,NULL,1,'2017-06-10 07:00:10','2017-06-10 07:00:10'),(5,'保安-04','17012340004',NULL,1,NULL,NULL,NULL,1,'2017-06-10 07:00:10','2017-06-10 07:00:10'),(6,'保安-50','17012340005',NULL,2,NULL,NULL,NULL,1,'2017-06-10 07:00:10','2017-06-10 07:00:10'),(7,'保安-51','17012340006',NULL,2,NULL,NULL,NULL,1,'2017-06-10 07:00:10','2017-06-10 07:00:10'),(8,'保安-52','17012340007',NULL,2,NULL,NULL,NULL,1,'2017-06-10 07:00:10','2017-06-10 07:00:10'),(9,'保安-53','17012340008',NULL,2,NULL,NULL,NULL,1,'2017-06-10 07:00:10','2017-06-10 07:00:10'),(10,'保安-54','17012340009',NULL,2,NULL,NULL,NULL,1,'2017-06-10 07:00:10','2017-06-10 07:00:10'),(11,'保安-100','17012340010',NULL,3,NULL,NULL,NULL,1,'2017-06-10 07:00:10','2017-06-10 07:00:10'),(12,'保安-101','17012340011',NULL,3,NULL,NULL,NULL,1,'2017-06-10 07:00:10','2017-06-10 07:00:10'),(13,'保安-102','17012340012',NULL,3,NULL,NULL,NULL,1,'2017-06-10 07:00:10','2017-06-10 07:00:10'),(14,'保安-103','17012340013',NULL,3,NULL,NULL,NULL,1,'2017-06-10 07:00:10','2017-06-10 07:00:10'),(15,'保安-104','17012340014',NULL,3,NULL,NULL,NULL,1,'2017-06-10 07:00:10','2017-06-10 07:00:10'),(16,'保安-150','17012340015',NULL,4,NULL,NULL,NULL,1,'2017-06-10 07:00:10','2017-06-10 07:00:10'),(17,'保安-151','17012340016',NULL,4,NULL,NULL,NULL,1,'2017-06-10 07:00:10','2017-06-10 07:00:10'),(18,'保安-152','17012340017',NULL,4,NULL,NULL,NULL,1,'2017-06-10 07:00:10','2017-06-10 07:00:10'),(19,'保安-153','17012340018',NULL,4,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(20,'保安-154','17012340019',NULL,4,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(21,'保安-200','17012340020',NULL,5,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(22,'保安-201','17012340021',NULL,5,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(23,'保安-202','17012340022',NULL,5,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(24,'保安-203','17012340023',NULL,5,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(25,'保安-204','17012340024',NULL,5,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(26,'保安-250','17012340025',NULL,6,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(27,'保安-251','17012340026',NULL,6,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(28,'保安-252','17012340027',NULL,6,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(29,'保安-253','17012340028',NULL,6,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(30,'保安-254','17012340029',NULL,6,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(31,'保安-300','17012340030',NULL,7,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(32,'保安-301','17012340031',NULL,7,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(33,'保安-302','17012340032',NULL,7,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(34,'保安-303','17012340033',NULL,7,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(35,'保安-304','17012340034',NULL,7,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(36,'保安-350','17012340035',NULL,8,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(37,'保安-351','17012340036',NULL,8,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(38,'保安-352','17012340037',NULL,8,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(39,'保安-353','17012340038',NULL,8,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(40,'保安-354','17012340039',NULL,8,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(41,'保安-400','17012340040',NULL,9,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(42,'保安-401','17012340041',NULL,9,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(43,'保安-402','17012340042',NULL,9,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(44,'保安-403','17012340043',NULL,9,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11'),(45,'保安-404','17012340044',NULL,9,NULL,NULL,NULL,1,'2017-06-10 07:00:11','2017-06-10 07:00:11');
/*!40000 ALTER TABLE `t_guard` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-10 15:33:46
