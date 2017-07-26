-- t_guard_time_template
DROP TABLE IF EXISTS t_guard_time_template;
CREATE TABLE t_guard_time_template
(
  id bigint NOT NULL AUTO_INCREMENT,
  name  varchar(128) NOT NULL COMMENT '时间模板名称',
  template_type tinyint(4) unsigned NOT NULL default 0 COMMENT '模板类型(0/1：单时间模板/周重复时间模板)',
  description varchar(255) default '' COMMENT '描述信息',
  ref_count int unsigned NOT NULL default 0 COMMENT '引用计数（布防计划）',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_guard_time_template PRIMARY KEY (id)
)COMMENT= '时间模版表';