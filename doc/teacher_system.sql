/*
Navicat MySQL Data Transfer

Source Server         : native_mysql
Source Server Version : 50636
Source Host           : 172.16.23.52:3306
Source Database       : teacher_system

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2017-06-30 19:04:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for teacher_system_paper
-- ----------------------------
DROP TABLE IF EXISTS `teacher_system_paper`;
CREATE TABLE `teacher_system_paper` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `classify` char(1) DEFAULT NULL COMMENT '分类：语文、数学、英语',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户ID 外键',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for teacher_system_store
-- ----------------------------
DROP TABLE IF EXISTS `teacher_system_store`;
CREATE TABLE `teacher_system_store` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '图片名称',
  `url` varchar(255) DEFAULT NULL COMMENT '图片保存路径',
  `subject_id` int(11) DEFAULT NULL COMMENT '主体（存放图片的主体，如试卷）ID 外键',
  `store_type` int(11) DEFAULT NULL COMMENT '存储系统 0:本地文件系统  1:ftp  2:fastdfs',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
