-- t_alert_resource
DROP TABLE IF EXISTS t_alert_resource;
CREATE TABLE t_alert_resource
(
  id bigint NOT NULL AUTO_INCREMENT,
  alert_id bigint NOT NULL COMMENT '警情id',
  resource_type tinyint(4) unsigned NOT NULL COMMENT '源类型',
  resource_index tinyint(4) unsigned NOT NULL default 0,
  path varchar(255) default null COMMENT '路径',
  url varchar(255) default null COMMENT 'url',
  content_type varchar(45) default NULL,
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_alert_resource PRIMARY KEY(id),
  CONSTRAINT uk_t_alert_resource UNIQUE (alert_id, resource_type, resource_index),
  CONSTRAINT fk_t_alert_resource_alert_id FOREIGN KEY (alert_id)
	  REFERENCES t_alert (id)
	  ON UPDATE CASCADE ON DELETE CASCADE
)COMMENT= '警情源表';