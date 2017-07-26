-- t_alert
DROP TABLE IF EXISTS t_alert;
CREATE TABLE t_alert
(
  id bigint NOT NULL AUTO_INCREMENT,
  description varchar(255) default NULL,
  reason tinyint(4) unsigned NOT NULL default 0 COMMENT '报警原因',
  dev_id char(36) NOT NULL COMMENT '设备 id',
  department_id char(36) NOT NULL COMMENT '社区id',
  status tinyint(4) unsigned NOT NULL default 0 COMMENT '警情处理状态\n0:未处理\n1:等待保安回电\n2:等待保安二次回电\n3:处理成功\n4:处理失败',
  is_over_time tinyint(1) unsigned NOT NULL default 0 COMMENT '是否处理超时',
  alert_type tinyint(4) unsigned default NULL COMMENT '警情确认类型\n0:误报\n1:可疑出警（发现可疑人员）\n2:异常出警（未发现可疑人员）',
  unprocessed_alerts int NOT NULL default 0 COMMENT '警情发生前，未处理的警情数',
  add_time datetime NOT NULL default CURRENT_TIMESTAMP COMMENT '警情发生时间',
  confirm_start_time datetime default NULL COMMENT '警情确认开始时间\n',
  confirm_end_time datetime default NULL COMMENT '警情确认结束时间',
  confirm_time int default NULL COMMENT '警情发生到用户开始确认的时间',
  confirmed_alerts int default NULL COMMENT '警情发生到确认完成，期间确认的警情数',
  user_id bigint default NULL COMMENT '用户 id',
  confirm_desc varchar(255) default NULL COMMENT '警情确认描述',
  finish_time datetime default NULL,
  create_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_alert PRIMARY KEY (id),
  CONSTRAINT fk_t_alert_dev_id FOREIGN KEY (dev_id) 
      REFERENCES t_device (id)
	  ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_t_alert_user_id FOREIGN KEY (user_id) 
      REFERENCES t_user (id)
	  ON UPDATE CASCADE ON DELETE CASCADE
)COMMENT='警情表-需要人工确认的警情表';