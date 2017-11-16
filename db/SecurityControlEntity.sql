CREATE TABLE `wb_security_control` (
  `phone_number`       VARCHAR(64)      NOT NULL DEFAULT ''
  COMMENT 'phoneNumber',
  `control_type_enum`  VARCHAR(50)      NOT NULL DEFAULT ''
  COMMENT 'controlTypeEnum',
  `mistake_times`      INT(11)          NOT NULL DEFAULT -1
  COMMENT 'mistakeTimes',
  `first_mistake_time` TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT 'firstMistakeTime',
  `last_mistake_time`  TIMESTAMP UNIQUE NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT 'lastMistakeTime',
  `freezed`            TINYINT(3)       NOT NULL DEFAULT 0
  COMMENT 'freezed',
  `id`                 BIGINT(15)       NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  `create_time`        TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT 'createTime',
  `update_time`        TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT 'updateTime',
  INDEX (phone_number),
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '`wb_security_control`';
