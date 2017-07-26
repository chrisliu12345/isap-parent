-- t_guard_alert
DROP TABLE IF EXISTS t_guard_alert;
CREATE TABLE t_guard_alert
(
  id bigint NOT NULL AUTO_INCREMENT,
  guard_id bigint NOT NULL COMMENT '保安id',
  alert_id bigint NOT NULL COMMENT '警情id',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_guard_alert PRIMARY KEY (id),
  CONSTRAINT uk_t_guard_alert UNIQUE (alert_id),
  CONSTRAINT fk_t_guard_alert_guard_id FOREIGN KEY (guard_id) 
      REFERENCES t_guard (id)
	  ON UPDATE CASCADE ON DELETE CASCADE, 
  CONSTRAINT fk_t_guard_alert_alert_id FOREIGN KEY (alert_id) 
      REFERENCES t_alert (id)
	  ON UPDATE CASCADE ON DELETE CASCADE
)COMMENT= '保安警情表表';