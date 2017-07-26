-- t_guard_plan_res
DROP TABLE IF EXISTS t_guard_plan_res;
CREATE TABLE t_guard_plan_res
(
  id bigint NOT NULL AUTO_INCREMENT,
  plan_id bigint NOT NULL COMMENT '布防计划ID',
  guard_status tinyint(4) unsigned NOT NULL  default 0 COMMENT '布防状态(0/1：关闭/打开)',
  dev_id char(36) NOT NULL COMMENT '设备ID',
  alarm_type bigint NOT NULL COMMENT '告警类型',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_guard_plan_res PRIMARY KEY (id),
  CONSTRAINT uk_t_guard_plan_res UNIQUE (dev_id, alarm_type),
  CONSTRAINT fk_t_guard_plan_res_plan_id FOREIGN KEY (plan_id) 
      REFERENCES t_guard_plan (id)
	  ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_t_guard_plan_res_dev_id FOREIGN KEY (dev_id) 
      REFERENCES t_device (id)
	  ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_t_guard_plan_res_alarm_type FOREIGN KEY (alarm_type) 
      REFERENCES t_alarm_type (alarm_type)
	  ON UPDATE CASCADE ON DELETE CASCADE
)COMMENT= '布防计划资源表';