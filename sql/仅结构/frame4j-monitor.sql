/*
Navicat MySQL Data Transfer

Source Server         : 118.24.244.187
Source Server Version : 50717
Source Host           : 118.24.244.187:3306
Source Database       : frame4j-monitor

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-07-08 11:14:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for metrics_info
-- ----------------------------
DROP TABLE IF EXISTS `metrics_info`;
CREATE TABLE `metrics_info` (
  `id` varchar(64) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `http_server_requests` varchar(255) DEFAULT NULL,
  `jvm_gc_pause` varchar(255) DEFAULT NULL,
  `jvm_memory_committed` varchar(255) DEFAULT NULL,
  `jvm_memory_max` varchar(255) DEFAULT NULL,
  `jvm_memory_used` varchar(255) DEFAULT NULL,
  `jvm_threads_daemon` varchar(255) DEFAULT NULL,
  `jvm_threads_live` varchar(255) DEFAULT NULL,
  `jvm_threads_peak` varchar(255) DEFAULT NULL,
  `process_cpu_usage` varchar(255) DEFAULT NULL,
  `process_files_max` varchar(255) DEFAULT NULL,
  `process_files_open` varchar(255) DEFAULT NULL,
  `process_start_time` varchar(255) DEFAULT NULL,
  `process_uptime` varchar(255) DEFAULT NULL,
  `system_cpu_count` varchar(255) DEFAULT NULL,
  `system_cpu_usage` varchar(255) DEFAULT NULL,
  `system_load_average1m` varchar(255) DEFAULT NULL,
  `tomcat_global_error` varchar(255) DEFAULT NULL,
  `tomcat_sessions_active_max` varchar(255) DEFAULT NULL,
  `tomcat_sessions_rejected` varchar(255) DEFAULT NULL,
  `tomcat_threads_busy` varchar(255) DEFAULT NULL,
  `tomcat_threads_config_max` varchar(255) DEFAULT NULL,
  `tomcat_threads_current` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
