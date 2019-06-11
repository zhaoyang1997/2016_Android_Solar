/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : solardb

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-06-11 15:25:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `daka`
-- ----------------------------
DROP TABLE IF EXISTS `daka`;
CREATE TABLE `daka` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `daka_date` varchar(255) DEFAULT NULL,
  `daka_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of daka
-- ----------------------------
INSERT INTO `daka` VALUES ('1', '1', '2019年06月04日', '河北师范大学');
INSERT INTO `daka` VALUES ('2', '1', '2019年06月05日', '河北师范大学');
INSERT INTO `daka` VALUES ('10', '1', '2019年06月10日', '河北师范大学(新校区)');
INSERT INTO `daka` VALUES ('11', '1', '2019年06月11日', '河北师范大学(新校区)');

-- ----------------------------
-- Table structure for `matter`
-- ----------------------------
DROP TABLE IF EXISTS `matter`;
CREATE TABLE `matter` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `matter_name` varchar(255) DEFAULT NULL,
  `matter_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of matter
-- ----------------------------
INSERT INTO `matter` VALUES ('1', '1', '期末考试', '2019年07月10日');
INSERT INTO `matter` VALUES ('2', '1', '六级考试', '2019年07月01日');
INSERT INTO `matter` VALUES ('3', '1', '四级考试', '2019年6月21日');

-- ----------------------------
-- Table structure for `ring`
-- ----------------------------
DROP TABLE IF EXISTS `ring`;
CREATE TABLE `ring` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ring_name` varchar(255) DEFAULT NULL,
  `ring_file` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ring
-- ----------------------------
INSERT INTO `ring` VALUES ('1', '铃声', 'http://10.7.89.189:8080/SolorService/ring/a946.mp3');

-- ----------------------------
-- Table structure for `score`
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `score_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('1', '1', '30');

-- ----------------------------
-- Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `task_name` varchar(255) DEFAULT NULL,
  `task_state` varchar(255) DEFAULT NULL,
  `task_time` int(11) DEFAULT NULL,
  `task_year` int(255) DEFAULT NULL,
  `task_month` int(255) DEFAULT NULL,
  `task_day` int(255) DEFAULT NULL,
  `task_show` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', '1', 'Spring作业', '1', '8', '2019', '6', '3', '0');
INSERT INTO `task` VALUES ('2', '1', 'Spring大作业', '1', '8', '2019', '6', '2', '1');
INSERT INTO `task` VALUES ('3', '1', '六级考试', '0', '25', '2019', '6', '5', '0');
INSERT INTO `task` VALUES ('4', '1', '期末作业', '0', '30', '2019', '6', '10', '1');
INSERT INTO `task` VALUES ('5', '1', '高数第一章复习', '1', '60', '2019', '6', '11', '1');
INSERT INTO `task` VALUES ('6', '1', '英语单词Unit6', '1', '20', '2019', '6', '8', '1');
INSERT INTO `task` VALUES ('7', '1', '英语单词Unit7', '1', '20', '2019', '6', '9', '1');
INSERT INTO `task` VALUES ('8', '1', '222', '0', '2', '2019', '6', '11', '0');
INSERT INTO `task` VALUES ('9', '1', '3333', '0', '3', '2019', '6', '11', '1');

-- ----------------------------
-- Table structure for `time`
-- ----------------------------
DROP TABLE IF EXISTS `time`;
CREATE TABLE `time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `timedate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `time_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of time
-- ----------------------------
INSERT INTO `time` VALUES ('1', '1', '50', '20190610');
INSERT INTO `time` VALUES ('2', '2', '60', '20190610');
INSERT INTO `time` VALUES ('3', '3', '20', '20190610');
INSERT INTO `time` VALUES ('4', '4', '30', '20190611');
INSERT INTO `time` VALUES ('5', '5', '45', '20190611');
INSERT INTO `time` VALUES ('6', '6', '30', '20190611');
INSERT INTO `time` VALUES ('7', null, null, null);

-- ----------------------------
-- Table structure for `tomato`
-- ----------------------------
DROP TABLE IF EXISTS `tomato`;
CREATE TABLE `tomato` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `tomato_count` int(11) DEFAULT NULL,
  `tomato_year` int(11) DEFAULT NULL,
  `tomato_month` int(11) DEFAULT NULL,
  `tomato_day` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tomato
-- ----------------------------
INSERT INTO `tomato` VALUES ('1', '1', '2', '2019', '6', '1');
INSERT INTO `tomato` VALUES ('2', '1', '3', '2019', '6', '2');
INSERT INTO `tomato` VALUES ('3', '1', '1', '2019', '6', '3');
INSERT INTO `tomato` VALUES ('6', '2', '3', '2019', '6', '10');
INSERT INTO `tomato` VALUES ('7', '3', '2', '2019', '6', '10');
INSERT INTO `tomato` VALUES ('8', '1', '1', '2019', '6', '11');
INSERT INTO `tomato` VALUES ('9', '1', '2', '2019', '6', '10');
INSERT INTO `tomato` VALUES ('10', '1', '4', '2019', '6', '9');
INSERT INTO `tomato` VALUES ('11', '1', '3', '2019', '6', '8');
INSERT INTO `tomato` VALUES ('12', '1', '3', '2019', '6', '7');
INSERT INTO `tomato` VALUES ('13', '1', '5', '2019', '6', '6');
INSERT INTO `tomato` VALUES ('14', '1', '2', '2019', '6', '5');
INSERT INTO `tomato` VALUES ('15', '1', '3', '2019', '6', '4');
INSERT INTO `tomato` VALUES ('16', '1', '4', '2019', '5', '31');
INSERT INTO `tomato` VALUES ('17', '1', '2', '2019', '5', '30');
INSERT INTO `tomato` VALUES ('18', '1', '3', '2019', '5', '29');
INSERT INTO `tomato` VALUES ('19', '1', '5', '2019', '5', '28');
INSERT INTO `tomato` VALUES ('20', '1', '6', '2019', '5', '27');
INSERT INTO `tomato` VALUES ('21', '1', '4', '2019', '5', '26');
INSERT INTO `tomato` VALUES ('22', '1', '3', '2019', '5', '25');
INSERT INTO `tomato` VALUES ('23', '1', '2', '2019', '5', '24');
INSERT INTO `tomato` VALUES ('24', '1', '2', '2019', '5', '22');
INSERT INTO `tomato` VALUES ('25', '1', '5', '2019', '5', '21');
INSERT INTO `tomato` VALUES ('26', '1', '4', '2019', '5', '20');
INSERT INTO `tomato` VALUES ('27', '1', '3', '2019', '5', '19');
INSERT INTO `tomato` VALUES ('28', '1', '6', '2019', '5', '18');
INSERT INTO `tomato` VALUES ('29', '1', '3', '2019', '5', '17');
INSERT INTO `tomato` VALUES ('30', '1', '2', '2019', '5', '15');
INSERT INTO `tomato` VALUES ('31', '1', '3', '2019', '5', '14');
INSERT INTO `tomato` VALUES ('32', '1', '4', '2019', '5', '12');
INSERT INTO `tomato` VALUES ('33', null, null, null, null, null);

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `verify_code` varchar(255) DEFAULT NULL,
  `user_head` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '123@qq.com', '张三', '123456', null, 'astronaut.jpg');
INSERT INTO `user` VALUES ('2', '345@qq.com', '李四', '123456', null, 'astronaut.jpg');
INSERT INTO `user` VALUES ('3', '134@qq.com', '王五', '123456', null, 'astronaut.jpg');
INSERT INTO `user` VALUES ('4', 'fyl@qq.com', '范艺琳', '111111', null, 'astronaut.jpg');
INSERT INTO `user` VALUES ('5', 'znn@qq.com', '郑宁宁', '111111', null, 'astronaut.jpg');
INSERT INTO `user` VALUES ('6', 'liyabing@qq.com', '李亚冰', '111111', null, 'astronaut.jpg');
INSERT INTO `user` VALUES ('7', 'xxx@qq.com', 'xxx', '111111', null, 'astronaut.jpg');
INSERT INTO `user` VALUES ('8', 'aaa@qq.com', 'aaa', '111111', null, 'astronaut.jpg');
INSERT INTO `user` VALUES ('9', 'llll@qq.com', '六六', '111111', null, 'astronaut.jpg');

-- ----------------------------
-- Table structure for `userring`
-- ----------------------------
DROP TABLE IF EXISTS `userring`;
CREATE TABLE `userring` (
  `ring_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ring_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userring
-- ----------------------------
