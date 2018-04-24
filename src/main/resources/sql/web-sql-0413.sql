-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.21-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2018-04-13 15:10:24
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for web1
CREATE DATABASE IF NOT EXISTS `web1` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `web1`;


-- Dumping structure for table web1.sys_menu
CREATE TABLE IF NOT EXISTS `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_id` tinyint(4) NOT NULL COMMENT '菜单ID',
  `parent_id` tinyint(4) NOT NULL DEFAULT '0' COMMENT '父级的菜单ID，一级菜单为0',
  `name` varchar(50) NOT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- Dumping data for table web1.sys_menu: ~0 rows (approximately)
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;


-- Dumping structure for table web1.sys_user
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID（用户ID）',
  `nick_name` varchar(100) DEFAULT '淘气的匿名用户' COMMENT '平台昵称',
  `user_name` varchar(100) NOT NULL COMMENT '用户名',
  `pass_word` varchar(100) NOT NULL COMMENT '加密后的密码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `create_time` datetime NOT NULL DEFAULT '2018-04-01 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_name` (`user_name`),
  KEY `k_user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- Dumping data for table web1.sys_user: ~2 rows (approximately)
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
REPLACE INTO `sys_user` (`id`, `nick_name`, `user_name`, `pass_word`, `email`, `mobile`, `create_time`, `update_time`) VALUES
	(1, 'eric_h', 'admin', '3131e32fbdf926399e432fada3cab0d4', '549987523@qq.com', '15502108775', '2018-04-01 00:00:00', '2018-04-13 15:03:09'),
	(2, '淘气的匿名用户', 'role_1', '3131e32fbdf926399e432fada3cab0d4', '549987523@qq.com', '15502108775', '2018-04-01 00:00:00', '2018-04-12 17:30:02');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
