-- auto Generated on 2017-09-18 19:03:11 
-- DROP TABLE IF EXISTS `wb_user_token`; 
CREATE TABLE `wb_user_token`(
	`id` BIGINT (15) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`phone_number` VARCHAR (50) DEFAULT '' COMMENT 'phoneNumber',
	`user_number` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'userNumber',
	`token` VARCHAR (256) NOT NULL DEFAULT '' COMMENT 'token',
	`imei` VARCHAR (100) DEFAULT '' COMMENT 'imei',
	`refresh_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'refreshTime',
	`expire_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'expireTime',
	INDEX(token),
	UNIQUE INDEX(user_number),
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`wb_user_token_entity`';