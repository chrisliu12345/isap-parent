-- t_alert_time_limit
DROP TABLE IF EXISTS t_alert_time_limit;
CREATE TABLE t_alert_time_limit
(
  id bigint NOT NULL AUTO_INCREMENT,
  dev_id char(36) NOT NULL COMMENT '设备 id',
  arrive_time int NOT NULL COMMENT '指定到达时间，以秒为单位',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_alert_time_limit PRIMARY KEY (id),
  CONSTRAINT fk_t_alert_time_limit_dev_id FOREIGN KEY (dev_id) 
      REFERENCES t_device (id)
	  ON UPDATE CASCADE ON DELETE CASCADE
)COMMENT='报警设备到达时间限制';