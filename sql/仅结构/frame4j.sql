/*
Navicat MySQL Data Transfer

Source Server         : 118.24.244.187
Source Server Version : 50717
Source Host           : 118.24.244.187:3306
Source Database       : frame4j

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-07-08 11:14:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_area
-- ----------------------------
DROP TABLE IF EXISTS `sys_area`;
CREATE TABLE `sys_area` (
  `id` varchar(64) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `parent` tinyblob,
  `parent_ids` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `data1` datetime DEFAULT NULL,
  `data2` datetime DEFAULT NULL,
  `data3` datetime DEFAULT NULL,
  `data4` datetime DEFAULT NULL,
  `data5` datetime DEFAULT NULL,
  `integer1` int(11) DEFAULT NULL,
  `integer2` int(11) DEFAULT NULL,
  `integer3` int(11) DEFAULT NULL,
  `integer4` int(11) DEFAULT NULL,
  `integer5` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `string1` varchar(255) DEFAULT NULL,
  `string2` varchar(255) DEFAULT NULL,
  `string3` varchar(255) DEFAULT NULL,
  `string4` varchar(255) DEFAULT NULL,
  `string5` varchar(255) DEFAULT NULL,
  `types` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sys_datapermission
-- ----------------------------
DROP TABLE IF EXISTS `sys_datapermission`;
CREATE TABLE `sys_datapermission` (
  `id` varchar(64) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `parent` tinyblob,
  `parent_ids` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `data1` datetime DEFAULT NULL,
  `data2` datetime DEFAULT NULL,
  `data3` datetime DEFAULT NULL,
  `data4` datetime DEFAULT NULL,
  `data5` datetime DEFAULT NULL,
  `data_code` varchar(255) DEFAULT NULL,
  `integer1` int(11) DEFAULT NULL,
  `integer2` int(11) DEFAULT NULL,
  `integer3` int(11) DEFAULT NULL,
  `integer4` int(11) DEFAULT NULL,
  `integer5` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `string1` varchar(255) DEFAULT NULL,
  `string2` varchar(255) DEFAULT NULL,
  `string3` varchar(255) DEFAULT NULL,
  `string4` varchar(255) DEFAULT NULL,
  `string5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(64) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `data1` datetime DEFAULT NULL,
  `data2` datetime DEFAULT NULL,
  `data3` datetime DEFAULT NULL,
  `data4` datetime DEFAULT NULL,
  `data5` datetime DEFAULT NULL,
  `integer1` int(11) DEFAULT NULL,
  `integer2` int(11) DEFAULT NULL,
  `integer3` int(11) DEFAULT NULL,
  `integer4` int(11) DEFAULT NULL,
  `integer5` int(11) DEFAULT NULL,
  `skey` varchar(255) DEFAULT NULL,
  `string1` varchar(255) DEFAULT NULL,
  `string2` varchar(255) DEFAULT NULL,
  `string3` varchar(255) DEFAULT NULL,
  `string4` varchar(255) DEFAULT NULL,
  `string5` varchar(255) DEFAULT NULL,
  `svalue` varchar(255) DEFAULT NULL,
  `dictcategory_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbqh8w99r7tqydmscxbs7hodq9` (`dictcategory_id`),
  CONSTRAINT `FKbqh8w99r7tqydmscxbs7hodq9` FOREIGN KEY (`dictcategory_id`) REFERENCES `sys_dictcategory` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sys_dictcategory
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictcategory`;
CREATE TABLE `sys_dictcategory` (
  `id` varchar(64) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `data1` datetime DEFAULT NULL,
  `data2` datetime DEFAULT NULL,
  `data3` datetime DEFAULT NULL,
  `data4` datetime DEFAULT NULL,
  `data5` datetime DEFAULT NULL,
  `integer1` int(11) DEFAULT NULL,
  `integer2` int(11) DEFAULT NULL,
  `integer3` int(11) DEFAULT NULL,
  `integer4` int(11) DEFAULT NULL,
  `integer5` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `string1` varchar(255) DEFAULT NULL,
  `string2` varchar(255) DEFAULT NULL,
  `string3` varchar(255) DEFAULT NULL,
  `string4` varchar(255) DEFAULT NULL,
  `string5` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sys_kafka_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_kafka_data`;
CREATE TABLE `sys_kafka_data` (
  `id` varchar(64) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `data1` datetime DEFAULT NULL,
  `data2` datetime DEFAULT NULL,
  `data3` datetime DEFAULT NULL,
  `data4` datetime DEFAULT NULL,
  `data5` datetime DEFAULT NULL,
  `integer1` int(11) DEFAULT NULL,
  `integer2` int(11) DEFAULT NULL,
  `integer3` int(11) DEFAULT NULL,
  `integer4` int(11) DEFAULT NULL,
  `integer5` int(11) DEFAULT NULL,
  `skey` varchar(255) DEFAULT NULL,
  `string1` varchar(255) DEFAULT NULL,
  `string2` varchar(255) DEFAULT NULL,
  `string3` varchar(255) DEFAULT NULL,
  `string4` varchar(255) DEFAULT NULL,
  `string5` varchar(255) DEFAULT NULL,
  `svalue` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(64) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `parent` tinyblob,
  `parent_ids` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `data1` datetime DEFAULT NULL,
  `data2` datetime DEFAULT NULL,
  `data3` datetime DEFAULT NULL,
  `data4` datetime DEFAULT NULL,
  `data5` datetime DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `integer1` int(11) DEFAULT NULL,
  `integer2` int(11) DEFAULT NULL,
  `integer3` int(11) DEFAULT NULL,
  `integer4` int(11) DEFAULT NULL,
  `integer5` int(11) DEFAULT NULL,
  `is_show` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `string1` varchar(255) DEFAULT NULL,
  `string2` varchar(255) DEFAULT NULL,
  `string3` varchar(255) DEFAULT NULL,
  `string4` varchar(255) DEFAULT NULL,
  `string5` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(64) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `data1` datetime DEFAULT NULL,
  `data2` datetime DEFAULT NULL,
  `data3` datetime DEFAULT NULL,
  `data4` datetime DEFAULT NULL,
  `data5` datetime DEFAULT NULL,
  `enname` varchar(255) DEFAULT NULL,
  `integer1` int(11) DEFAULT NULL,
  `integer2` int(11) DEFAULT NULL,
  `integer3` int(11) DEFAULT NULL,
  `integer4` int(11) DEFAULT NULL,
  `integer5` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `string1` varchar(255) DEFAULT NULL,
  `string2` varchar(255) DEFAULT NULL,
  `string3` varchar(255) DEFAULT NULL,
  `string4` varchar(255) DEFAULT NULL,
  `string5` varchar(255) DEFAULT NULL,
  `datapermission_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpcw4dmoe3jqun91fsqu7tgvo9` (`datapermission_id`),
  CONSTRAINT `FKpcw4dmoe3jqun91fsqu7tgvo9` FOREIGN KEY (`datapermission_id`) REFERENCES `sys_datapermission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` varchar(64) NOT NULL,
  `menu_id` varchar(64) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FKf3mud4qoc7ayew8nml4plkevo` (`menu_id`),
  CONSTRAINT `FKf3mud4qoc7ayew8nml4plkevo` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`id`),
  CONSTRAINT `FKkeitxsgxwayackgqllio4ohn5` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(64) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `data1` datetime DEFAULT NULL,
  `data2` datetime DEFAULT NULL,
  `data3` datetime DEFAULT NULL,
  `data4` datetime DEFAULT NULL,
  `data5` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `integer1` int(11) DEFAULT NULL,
  `integer2` int(11) DEFAULT NULL,
  `integer3` int(11) DEFAULT NULL,
  `integer4` int(11) DEFAULT NULL,
  `integer5` int(11) DEFAULT NULL,
  `login_date` datetime DEFAULT NULL,
  `login_exception_count` varchar(255) DEFAULT NULL,
  `login_flag` varchar(255) DEFAULT NULL,
  `login_ip` varchar(255) DEFAULT NULL,
  `login_name` varchar(255) NOT NULL,
  `nick` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `password_expired_date` datetime DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `string1` varchar(255) DEFAULT NULL,
  `string2` varchar(255) DEFAULT NULL,
  `string3` varchar(255) DEFAULT NULL,
  `string4` varchar(255) DEFAULT NULL,
  `string5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(64) NOT NULL,
  `role_id` varchar(64) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`),
  CONSTRAINT `FKb40xxfch70f5qnyfw8yme1n1s` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKhh52n8vd4ny9ff4x9fb8v65qx` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
