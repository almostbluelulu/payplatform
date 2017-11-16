-- auto Generated on 2017-09-18 18:58:08 
-- DROP TABLE IF EXISTS `wb_api_config`; 
CREATE TABLE `wb_api_config`(
	`id` BIGINT (15) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`api_name` VARCHAR (50) DEFAULT '' COMMENT 'apiName',
	`api_host` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'apiHost',
	`api_uri` VARCHAR (100) NOT NULL DEFAULT '' COMMENT 'apiUri',
	`backend_class` VARCHAR (100) NOT NULL DEFAULT '' COMMENT 'backendClass',
	`backend_method` VARCHAR (256) NOT NULL DEFAULT '' COMMENT 'backendMethod',
	`need_session` TINYINT (3) NOT NULL DEFAULT 0 COMMENT 'needSession',
	`available` TINYINT (3) NOT NULL DEFAULT 0 COMMENT 'available',
	`param_valid_rule` VARCHAR (256) NOT NULL DEFAULT '' COMMENT 'paramValidRule',
	`description` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'description',
	`operator` VARCHAR (50) DEFAULT '' COMMENT 'operator',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'createTime',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updateTime',
	UNIQUE INDEX(api_uri),
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`wb_api_config_entity`';
