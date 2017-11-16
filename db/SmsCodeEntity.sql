CREATE TABLE `wb_sms_code` (
  `id`           BIGINT(15)  NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  `phone_number` VARCHAR(50) NOT NULL DEFAULT ''
  COMMENT 'phoneNumber',
  `sms_code`     VARCHAR(50) NOT NULL DEFAULT ''
  COMMENT 'smsCode',
  `sms_type`     VARCHAR(50) NOT NULL DEFAULT ''
  COMMENT 'smsType',
  `available`    TINYINT(3)  NOT NULL DEFAULT 0
  COMMENT 'available',
  `effect_time`  DATETIME    NOT NULL
  COMMENT 'effectTime',
  `create_time`  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT 'createTime',
  `update_time`  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT 'updateTime',
  INDEX (phone_number),
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '`wb_sms_code`';
