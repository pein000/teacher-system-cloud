/*
Navicat MySQL Data Transfer

Source Server         : native_mysql
Source Server Version : 50636
Source Host           : 172.16.23.52:3306
Source Database       : teacher_system

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2017-09-13 19:50:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for teacher_ocr_identify
-- ----------------------------
DROP TABLE IF EXISTS `teacher_ocr_identify`;
CREATE TABLE `teacher_ocr_identify` (
  `id` int(11) NOT NULL,
  `content` blob COMMENT '识别的内容',
  `store_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for teacher_ocr_paper
-- ----------------------------
DROP TABLE IF EXISTS `teacher_ocr_paper`;
CREATE TABLE `teacher_ocr_paper` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `classify` char(1) DEFAULT NULL COMMENT '分类：语文、数学、英语',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `status` char(1) DEFAULT NULL COMMENT '状态： 0：初始化  1：识别中  2：识别成功  3：识别失败',
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户ID 外键',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for teacher_ocr_store
-- ----------------------------
DROP TABLE IF EXISTS `teacher_ocr_store`;
CREATE TABLE `teacher_ocr_store` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '图片名称',
  `url` varchar(255) DEFAULT NULL COMMENT '图片保存路径',
  `subject_id` int(11) DEFAULT NULL COMMENT '主体（存放图片的主体，如试卷）ID 外键',
  `store_type` int(11) DEFAULT NULL COMMENT '存储系统 0:本地文件系统  1:ftp  2:fastdfs',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for teacher_user_info
-- ----------------------------
DROP TABLE IF EXISTS `teacher_user_info`;
CREATE TABLE `teacher_user_info` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
