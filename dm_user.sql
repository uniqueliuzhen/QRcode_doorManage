/*
Heidi SQL MySQL Data Transfer

Source Server Version : 50626
Source Host           : 120.26.133.63:3306

Target Server Type    : MYmybatisSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2019-03-12 22:22:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dm_user
-- ----------------------------
DROP TABLE IF EXISTS `dm_user`;
CREATE TABLE `dm_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account` varchar(255) DEFAULT NULL COMMENT '登录名',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `type` int(1) DEFAULT NULL COMMENT '类型 1:管理员 2:用户',
  `status` int(1) DEFAULT NULL COMMENT '状态 1:不可用 2:可用 3:待审核',
  `wechat_id` varchar(255) DEFAULT NULL COMMENT '微信id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dm_user
-- ----------------------------
INSERT INTO `dm_user` VALUES ('1', 'admin', '管理员', 'admin', '1', '2', null, null, null);
