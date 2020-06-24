-- ----------------------------
-- Table structure for SYS_APPLY_BILLS
-- ----------------------------
DROP TABLE IF EXISTS `SYS_APPLY_BILLS`;
CREATE TABLE `SYS_APPLY_BILLS` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bills_type` tinyint(3) NOT NULL,
  `bills_code` varchar(30) NOT NULL,
  `bills_status` tinyint(3) NOT NULL,
  `process_definition_id` varchar(64) NOT NULL,
  `deployment_id` varchar(64) NOT NULL,
  `process_instance_id` varchar(64) DEFAULT NULL,
  `created_by` int(11) NOT NULL,
  `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYS_APPLY_BILLS
-- ----------------------------

-- ----------------------------
-- Table structure for SYS_ACTION_HISTORY
-- ----------------------------
DROP TABLE IF EXISTS `SYS_ACTION_HISTORY`;
CREATE TABLE `SYS_ACTION_HISTORY` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bills_id` int(11) NOT NULL,
  `task_id` varchar(64),
  `task_name` varchar(255),
  `task_definition_key` varchar(255),
  `action_type` tinyint(3) NOT NULL,
  `action_comment` varchar(500),
  `action_by` int(11) NOT NULL,
  `action_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYS_ACTION_HISTORY
-- ----------------------------

-- ----------------------------
-- Table structure for SYS_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `SYS_ROLE`;
CREATE TABLE `SYS_ROLE` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(30) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYS_ROLE
-- ----------------------------
INSERT INTO `SYS_ROLE` VALUES ('1', 'CommonUser', '普通用户');
INSERT INTO `SYS_ROLE` VALUES ('2', 'AccountManager', '客户经理');
INSERT INTO `SYS_ROLE` VALUES ('3', 'DepartmentManager', '部门经理');
INSERT INTO `SYS_ROLE` VALUES ('4', 'LegalAffairs', '法务');
INSERT INTO `SYS_ROLE` VALUES ('5', 'RiskControlOfficer', '风控专员');
INSERT INTO `SYS_ROLE` VALUES ('6', 'RiskControlManager', '风控经理');
INSERT INTO `SYS_ROLE` VALUES ('7', 'Director', '总监');
INSERT INTO `SYS_ROLE` VALUES ('8', 'BranchLeader', '分管领导');
INSERT INTO `SYS_ROLE` VALUES ('9', 'GeneralManager', '总经理');

-- ----------------------------
-- Table structure for SYS_USER
-- ----------------------------
DROP TABLE IF EXISTS `SYS_USER`;
CREATE TABLE `SYS_USER` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(30) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYS_USER
-- ----------------------------
INSERT INTO `SYS_USER` VALUES ('1', 'A001', '客户A');
INSERT INTO `SYS_USER` VALUES ('2', 'A002', '客户B');
INSERT INTO `SYS_USER` VALUES ('3', 'A003', '客户C');
INSERT INTO `SYS_USER` VALUES ('4', 'A004', '客户D');
INSERT INTO `SYS_USER` VALUES ('5', 'A005', '客户E');
INSERT INTO `SYS_USER` VALUES ('6', 'A006', '客户经理A');
INSERT INTO `SYS_USER` VALUES ('7', 'A007', '客户经理B');
INSERT INTO `SYS_USER` VALUES ('8', 'A008', '客户经理C');
INSERT INTO `SYS_USER` VALUES ('9', 'A009', '部门经理A');
INSERT INTO `SYS_USER` VALUES ('10', 'A010', '部门经理B');
INSERT INTO `SYS_USER` VALUES ('11', 'A011', '法务A');
INSERT INTO `SYS_USER` VALUES ('12', 'A012', '法务B');
INSERT INTO `SYS_USER` VALUES ('13', 'A013', '风控专员A');
INSERT INTO `SYS_USER` VALUES ('14', 'A014', '风控专员B');
INSERT INTO `SYS_USER` VALUES ('15', 'A015', '风控专员C');
INSERT INTO `SYS_USER` VALUES ('16', 'A016', '风控经理A');
INSERT INTO `SYS_USER` VALUES ('17', 'A017', '总监A');
INSERT INTO `SYS_USER` VALUES ('18', 'A018', '分管领导A');
INSERT INTO `SYS_USER` VALUES ('19', 'A019', '分管领导B');
INSERT INTO `SYS_USER` VALUES ('20', 'A020', '总经理A');

-- ----------------------------
-- Table structure for SYS_USER_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `SYS_USER_ROLE`;
CREATE TABLE `SYS_USER_ROLE` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYS_USER_ROLE
-- ----------------------------
INSERT INTO `SYS_USER_ROLE` VALUES ('1', '1', '1');
INSERT INTO `SYS_USER_ROLE` VALUES ('2', '2', '1');
INSERT INTO `SYS_USER_ROLE` VALUES ('3', '3', '1');
INSERT INTO `SYS_USER_ROLE` VALUES ('4', '4', '1');
INSERT INTO `SYS_USER_ROLE` VALUES ('5', '5', '1');
INSERT INTO `SYS_USER_ROLE` VALUES ('6', '6', '2');
INSERT INTO `SYS_USER_ROLE` VALUES ('7', '7', '2');
INSERT INTO `SYS_USER_ROLE` VALUES ('8', '8', '2');
INSERT INTO `SYS_USER_ROLE` VALUES ('9', '9', '3');
INSERT INTO `SYS_USER_ROLE` VALUES ('10', '10', '3');
INSERT INTO `SYS_USER_ROLE` VALUES ('11', '11', '4');
INSERT INTO `SYS_USER_ROLE` VALUES ('12', '12', '4');
INSERT INTO `SYS_USER_ROLE` VALUES ('13', '13', '5');
INSERT INTO `SYS_USER_ROLE` VALUES ('14', '14', '5');
INSERT INTO `SYS_USER_ROLE` VALUES ('15', '15', '5');
INSERT INTO `SYS_USER_ROLE` VALUES ('16', '16', '6');
INSERT INTO `SYS_USER_ROLE` VALUES ('17', '17', '7');
INSERT INTO `SYS_USER_ROLE` VALUES ('18', '18', '8');
INSERT INTO `SYS_USER_ROLE` VALUES ('19', '19', '8');
INSERT INTO `SYS_USER_ROLE` VALUES ('20', '20', '9');