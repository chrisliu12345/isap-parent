-- t_action_linkage
DROP TABLE IF EXISTS t_action_linkage;
CREATE TABLE t_action_linkage
(
  id bigint NOT NULL AUTO_INCREMENT,
  dev_id char(36) NOT NULL COMMENT '设备id',
  alarm_type bigint NOT NULL COMMENT '告警类型',
  action_type tinyint(4) unsigned NOT NULL COMMENT '联动类型',
  is_enable tinyint(1) unsigned NOT NULL COMMENT '是否使能',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_action_linkage PRIMARY KEY (id),
  CONSTRAINT uk_t_action_lingage UNIQUE (dev_id, alarm_type, action_type),
  CONSTRAINT fk_t_action_linkage_dev_id FOREIGN KEY (dev_id) 
      REFERENCES t_device (id)
	  ON UPDATE CASCADE ON DELETE NO ACTION,
  CONSTRAINT fk_t_action_linkage_alarm_type FOREIGN KEY (alarm_type) 
      REFERENCES t_alarm_type (alarm_type)
	  ON UPDATE CASCADE ON DELETE NO ACTION
)COMMENT= '联动业务表';