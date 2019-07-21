
DROP TABLE IF EXISTS `process`;
CREATE TABLE `process`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `user_type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '用户类型：0机器人; 1用户',
  `question_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '问题类型：0问答；1选择',
  `process_id` int(11) DEFAULT NULL COMMENT '上一个流程',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `process_id`(`process_id`) USING BTREE,
  CONSTRAINT `process_ibfk_1` FOREIGN KEY (`process_id`) REFERENCES `process` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

INSERT INTO `process` VALUES (1, '遇到哪方面的问题？', 0, 0, NULL, '我的流程1');
INSERT INTO `process` VALUES (2, 'FPGA加载不成功', 1, 0, 1, NULL);
INSERT INTO `process` VALUES (3, '请选择加载模式：\r\n		a.SPI模拟JATG\r\n		b.CPLD加载', 0, 1, 2, NULL);
INSERT INTO `process` VALUES (4, 'a.SPI模拟JATG', 1, 0, 3, NULL);
INSERT INTO `process` VALUES (5, 'b.CPLD加载', 1, 0, 3, NULL);
INSERT INTO `process` VALUES (6, '请选择GPU类型：\r\n	a.Hi1212\r\n	b.Hi1213\r\n	c.Hi1215', 0, 1, 4, NULL);
INSERT INTO `process` VALUES (7, 'a.Hi1212', 1, 0, 6, NULL);
INSERT INTO `process` VALUES (8, 'b.Hi1213', 1, 0, 6, NULL);
INSERT INTO `process` VALUES (9, 'c.Hi1215', 1, 0, 6, NULL);
INSERT INTO `process` VALUES (10, '加载完成后，FPGA指示灯状态：\r\n	a.亮\r\n	b.灭', 0, 1, 5, NULL);
INSERT INTO `process` VALUES (11, 'a.亮', 1, 0, 10, NULL);
INSERT INTO `process` VALUES (12, 'b.灭', 1, 0, 10, NULL);
INSERT INTO `process` VALUES (13, '请输入关键现象', 0, 0, 7, NULL);
INSERT INTO `process` VALUES (14, '网口ping不通', 1, 0, 13, NULL);
INSERT INTO `process` VALUES (15, '关键现象2', 0, 0, 13, NULL);
INSERT INTO `process` VALUES (16, '请输入关键现象', 0, 0, 8, NULL);
INSERT INTO `process` VALUES (17, '关键详细3', 0, 0, 16, NULL);
