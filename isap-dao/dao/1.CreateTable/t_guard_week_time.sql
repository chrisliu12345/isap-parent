-- t_guard_week_time
DROP TABLE IF EXISTS t_guard_week_time;
CREATE TABLE t_guard_week_time
(
  id bigint NOT NULL AUTO_INCREMENT,
  template_id bigint NOT NULL COMMENT '时间模板ID',
  weekdays varchar(16) NOT NULL COMMENT '星期列表（逗号隔开）',
  begin_time1 time default NULL COMMENT '开始时间1',
  end_time1 time default NULL COMMENT '结束时间1',
  begin_time2 time default NULL COMMENT '开始时间2',
  end_time2 time default NULL COMMENT '结束时间2',
  begin_time3 time default NULL COMMENT '开始时间3',
  end_time3 time default NULL COMMENT '结束时间3',
  begin_time4 time default NULL COMMENT '开始时间4',
  end_time4 time default NULL COMMENT '结束时间4',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_guard_week_time PRIMARY KEY (id),
  CONSTRAINT fk_t_guard_week_time_demplate_id FOREIGN KEY (template_id) 
      REFERENCES t_guard_time_template (id)
	  ON UPDATE CASCADE ON DELETE CASCADE
)COMMENT= '循环时间表';