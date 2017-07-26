-- t_alarm_record
DROP TABLE IF EXISTS t_alarm_record;
CREATE TABLE t_alarm_record
(
  id bigint NOT NULL AUTO_INCREMENT,
  dev_id char(36) NOT NULL COMMENT '设备id',
  alarm_type bigint NOT NULL COMMENT '告警类型',
  flag tinyint(4) unsigned NOT NULL COMMENT '告警标志',
  alarm_level tinyint(4) unsigned NOT NULL COMMENT '告警等级',
  alarm_time datetime NOT NULL COMMENT '告警发生时间',
  alarm_data varchar(512) NOT NULL default '' COMMENT '告警数据',
  confirm tinyint(4) unsigned NOT NULL COMMENT '告警确认',
  check_user varchar(36) default NULL COMMENT '告警确认用户',
  check_time datetime default NULL COMMENT '告警确认时间',
  suggestion varchar(512) default NULL COMMENT '告警确认建议',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_alarm_record PRIMARY KEY (id),
  CONSTRAINT fk_t_alarm_record_dev_id FOREIGN KEY (dev_id) 
      REFERENCES t_device (id)
	  ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_t_alarm_record_alarm_type FOREIGN KEY (alarm_type) 
      REFERENCES t_alarm_type (alarm_type)
	  ON UPDATE CASCADE ON DELETE CASCADE
)COMMENT= '告警记录表';