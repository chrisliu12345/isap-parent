-- t_guard_single_time
DROP TABLE IF EXISTS t_guard_single_time;
CREATE TABLE t_guard_single_time
(
  id bigint NOT NULL AUTO_INCREMENT,
  template_id bigint NOT NULL COMMENT '时间模板ID',
  begin_time1 datetime default NULl COMMENT '开始时间1',
  end_time1 datetime default NULl COMMENT '结束时间1',
  begin_time2 datetime default NULl COMMENT '开始时间2',
  end_time2 datetime default NULl COMMENT '结束时间2',
  begin_time3 datetime default NULl COMMENT '开始时间3',
  end_time3 datetime default NULl COMMENT '结束时间3',
  begin_time4 datetime default NULl COMMENT '开始时间4',
  end_time4 datetime default NULl COMMENT '结束时间4',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_guard_single_time PRIMARY KEY (id),
  CONSTRAINT fk_t_guard_single_time FOREIGN KEY (template_id) 
      REFERENCES t_guard_time_template (id)
	  ON UPDATE CASCADE ON DELETE CASCADE
)COMMENT= '单时间段表';