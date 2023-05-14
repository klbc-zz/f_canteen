/*
 Navicat Premium Data Transfer

 Source Server         : mysql80
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : f_canteen

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 14/05/2023 15:27:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `food_id` int NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `disabled` int NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for f_order
-- ----------------------------
DROP TABLE IF EXISTS `f_order`;
CREATE TABLE `f_order`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `total_price` decimal(20, 2) NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `order_time` datetime(0) NULL DEFAULT NULL,
  `pay_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `disabled` int NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of f_order
-- ----------------------------
INSERT INTO `f_order` VALUES (6, 'KLBC-202304091242582', 10.00, 2, '2023-04-09 00:00:00', '2023-04-09 00:00:00', '2023-04-09 13:57:04', 0, 2);
INSERT INTO `f_order` VALUES (7, 'KLBC-202304091247082', 10.00, 1, '2023-04-09 00:00:00', '2023-04-09 00:00:00', '2023-04-09 00:00:00', 0, 2);
INSERT INTO `f_order` VALUES (8, 'KLBC-202304091255532', 3.00, 1, '2023-04-09 00:00:00', '2023-04-09 00:00:00', '2023-04-09 00:00:00', 0, 2);
INSERT INTO `f_order` VALUES (9, 'KLBC-202304091329342', 13.00, 1, '2023-04-09 00:00:00', '2023-04-09 00:00:00', '2023-04-09 00:00:00', 0, 2);
INSERT INTO `f_order` VALUES (10, 'KLBC-202304091337083', 10.00, 1, '2023-04-09 00:00:00', '2023-04-09 00:00:00', '2023-04-09 00:00:00', 0, 3);
INSERT INTO `f_order` VALUES (11, 'KLBC-202304091600012', 13.70, 0, '2023-04-09 16:00:01', NULL, NULL, 0, 2);

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `disabled` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `creation_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES (1, 1, 'klbc', '这是一条投诉', '未解决', NULL, '2023-04-04 00:10:02');
INSERT INTO `feedback` VALUES (2, 2, 'jjl', NULL, NULL, '0', '2023-04-09 16:39:24');
INSERT INTO `feedback` VALUES (3, 2, 'jjl', NULL, NULL, '0', '2023-04-09 16:42:26');
INSERT INTO `feedback` VALUES (4, 1, 'klbc', 'è¿æ¯ä¸æ¡æè¯2', NULL, '0', '2023-04-09 16:46:25');

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `food_name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `food_type_id` int NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `remark` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_by` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `creation_date` datetime(0) NULL DEFAULT NULL,
  `modify_by` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `modify_date` datetime(0) NULL DEFAULT NULL,
  `disabled` int NULL DEFAULT NULL,
  `buy_num` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of food
-- ----------------------------
INSERT INTO `food` VALUES (1, '红烧排骨', 1, 10.00, '', 'f0cbb324-6b1c-482e-aa31-00501a9ca243.jpg', 'k', '2023-03-15 21:16:30', NULL, '2023-04-09 15:36:56', 0, NULL);
INSERT INTO `food` VALUES (2, '凉拌青瓜', 2, 3.00, '', '18a06732-6c92-4f69-939a-2113e74ea2f4.jpg', NULL, '2023-03-26 11:36:25', NULL, '2023-04-09 15:40:07', 0, NULL);
INSERT INTO `food` VALUES (3, '干锅牛肉', 1, 12.00, '', '0b18bc8c-33e5-4913-9141-45a5bd3ca52e.jpg', NULL, '2023-04-09 15:12:37', NULL, '2023-04-09 15:39:44', 0, NULL);
INSERT INTO `food` VALUES (4, '炒豆芽', 2, 1.50, '', '6a8e98ab-034d-466a-838a-d5dfc29ae67f.jpg', NULL, '2023-04-09 15:20:01', NULL, '2023-04-09 15:46:06', 0, NULL);
INSERT INTO `food` VALUES (5, '米饭', 3, 0.70, '', 'ba6626a5-6419-4488-bdcc-e366146582dd.jpg', NULL, '2023-04-09 15:43:41', NULL, '2023-04-09 15:45:07', 0, NULL);

-- ----------------------------
-- Table structure for food_type
-- ----------------------------
DROP TABLE IF EXISTS `food_type`;
CREATE TABLE `food_type`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `food_type_name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_by` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `creation_date` datetime(0) NULL DEFAULT NULL,
  `modify_by` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `modify_date` datetime(0) NULL DEFAULT NULL,
  `disabled` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of food_type
-- ----------------------------
INSERT INTO `food_type` VALUES (1, '肉类', 'k', '2023-03-24 21:08:18', NULL, NULL, 0);
INSERT INTO `food_type` VALUES (2, '素类', NULL, '2023-03-26 00:58:24', NULL, '2023-03-26 01:02:11', 0);
INSERT INTO `food_type` VALUES (3, '主食', NULL, '2023-04-09 15:40:21', NULL, NULL, 0);

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NULL DEFAULT NULL,
  `food_id` int NULL DEFAULT NULL,
  `buy_num` int NULL DEFAULT NULL,
  `disabled` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES (1, 6, 1, 1, NULL);
INSERT INTO `order_detail` VALUES (2, 7, 1, 1, NULL);
INSERT INTO `order_detail` VALUES (3, 8, 2, 1, NULL);
INSERT INTO `order_detail` VALUES (4, 9, 1, 1, NULL);
INSERT INTO `order_detail` VALUES (5, 9, 2, 1, NULL);
INSERT INTO `order_detail` VALUES (6, 10, 1, 1, NULL);
INSERT INTO `order_detail` VALUES (7, 11, 1, 1, NULL);
INSERT INTO `order_detail` VALUES (8, 11, 2, 1, NULL);
INSERT INTO `order_detail` VALUES (9, 11, 5, 1, NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_by` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `creation_date` datetime(0) NULL DEFAULT NULL,
  `modify_by` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `modify_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` int NULL DEFAULT NULL,
  `birthday` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_role` int NULL DEFAULT NULL,
  `created_by` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `creation_date` datetime(0) NULL DEFAULT NULL,
  `modify_by` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `modify_date` datetime(0) NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `user_role_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '1', 'klbc', 'E10ADC3949BA59ABBE56E057F20F883E', 0, NULL, NULL, NULL, 0, 'k', '2023-03-15 21:47:14', NULL, NULL, 10, '管理员');
INSERT INTO `user` VALUES (2, '2', 'jjl', '96E79218965EB72C92A549DD5A330112', 1, NULL, '12132132', NULL, 1, NULL, '2023-04-01 23:51:59', NULL, NULL, 20, '员工');
INSERT INTO `user` VALUES (3, '3', 'yhx', '123123', 1, NULL, '1212313', NULL, 2, 'f', '2023-04-01 11:43:58', NULL, NULL, 29, '顾客');
INSERT INTO `user` VALUES (21, NULL, 'zhd', '123456', 1, '2023-05-14 15:06:27.105', '123456', 'null', 2, '0', NULL, NULL, '2023-05-14 15:06:27', NULL, NULL);
INSERT INTO `user` VALUES (22, NULL, 'aaaa', '4297F44B13955235245B2497399D7A93', NULL, NULL, '11111', NULL, 2, '0', NULL, NULL, NULL, NULL, '顾客');
INSERT INTO `user` VALUES (23, NULL, 'qwe', 'B0BAEE9D279D34FA1DFD71AADB908C3F', NULL, NULL, '11111', NULL, 2, '0', NULL, NULL, NULL, NULL, '顾客');
INSERT INTO `user` VALUES (24, NULL, 'zxc', '202CB962AC59075B964B07152D234B70', NULL, NULL, '123', NULL, 2, '0', NULL, NULL, NULL, NULL, '顾客');
INSERT INTO `user` VALUES (25, 'null', 'byhkk', '202CB962AC59075B964B07152D234B70', 0, '2023-05-14 14:51:20.398', '1231231234', 'null', 2, NULL, '2023-05-14 14:51:20', NULL, NULL, NULL, '顾客');
INSERT INTO `user` VALUES (26, 'null', 'dsa', '202CB962AC59075B964B07152D234B70', 0, '2023-05-14 14:52:00.891', '123', 'null', 2, NULL, '2023-05-14 14:52:01', NULL, NULL, NULL, '顾客');
INSERT INTO `user` VALUES (27, 'null', 'zzz', 'F3ABB86BD34CF4D52698F14C0DA1DC60', 1, '2023-05-14 15:03:16.15', 'zzz', 'null', 1, NULL, '2023-05-14 15:03:16', NULL, NULL, NULL, '员工');

SET FOREIGN_KEY_CHECKS = 1;
