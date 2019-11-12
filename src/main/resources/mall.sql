/*
 Navicat MySQL Data Transfer

 Source Server         : 本地连接
 Source Server Type    : MySQL
 Source Server Version : 50553
 Source Host           : localhost:3306
 Source Schema         : miaosha

 Target Server Type    : MySQL
 Target Server Version : 50553
 File Encoding         : 65001

 Date: 12/11/2019 10:14:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_buyer
-- ----------------------------
DROP TABLE IF EXISTS `t_buyer`;
CREATE TABLE `t_buyer`  (
  `buyer_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '买家Id',
  `buyer_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家姓名',
  `buyer_login_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家登录账号',
  `buyer_password` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家登录密码',
  PRIMARY KEY (`buyer_id`) USING BTREE,
  UNIQUE INDEX `login_name`(`buyer_login_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_item
-- ----------------------------
DROP TABLE IF EXISTS `t_item`;
CREATE TABLE `t_item`  (
  `item_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品Id',
  `item_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名',
  `item_count` int(11) UNSIGNED NOT NULL COMMENT '库存',
  `seller_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '卖家id',
  PRIMARY KEY (`item_id`) USING BTREE,
  INDEX `Ref_01`(`seller_id`) USING BTREE,
  CONSTRAINT `Ref_01` FOREIGN KEY (`seller_id`) REFERENCES `t_seller` (`seller_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `order_id` int(11) NOT NULL COMMENT '订单id',
  `order_time` datetime NOT NULL COMMENT '下订单时间',
  `item_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品id',
  `buyer_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '买家id',
  `order_status` tinyint(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单状态\r\n（0：创建订单）\r\n（1：处理订单）\r\n（2：处理完成）',
  `order_number` int(19) NOT NULL COMMENT '订单号，根据雪花算法生成，备用主键',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `Ref_02`(`item_id`) USING BTREE,
  INDEX `Ref_03`(`buyer_id`) USING BTREE,
  INDEX `orderNumber`(`order_number`) USING BTREE,
  CONSTRAINT `Ref_02` FOREIGN KEY (`item_id`) REFERENCES `t_item` (`item_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Ref_03` FOREIGN KEY (`buyer_id`) REFERENCES `t_buyer` (`buyer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_seller
-- ----------------------------
DROP TABLE IF EXISTS `t_seller`;
CREATE TABLE `t_seller`  (
  `seller_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '买家',
  `seller_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家名',
  `seller_login_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '卖家登录名',
  `seller_password` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '卖家登录密码',
  PRIMARY KEY (`seller_id`) USING BTREE,
  UNIQUE INDEX `login_username`(`seller_login_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
