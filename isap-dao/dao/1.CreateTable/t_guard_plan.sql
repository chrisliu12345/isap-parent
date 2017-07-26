-- t_guard_plan
DROP TABLE IF EXISTS t_guard_plan;
CREATE TABLE t_guard_plan
(
  id bigint NOT NULL AUTO_INCREMENT COMMENT '布防计划ID',
  name varchar(128) NOT NULL COMMENT '布防计划名称',
  template_id bigint NOT NULL COMMENT '时间模板ID',
  status tinyint(4) unsigned NOT NULL default 1 COMMENT '计划状态(0表示停止，1表示运行中）',
  description varchar(255) default NULL COMMENT '计划描述',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_guard_plan PRIMARY KEY (id),
  CONSTRAINT fk_t_guard_plan_template_id FOREIGN KEY (template_id) 
      REFERENCES t_guard_time_template (id)
	  ON UPDATE CASCADE ON DELETE NO ACTION
)COMMENT= '布防计划表';