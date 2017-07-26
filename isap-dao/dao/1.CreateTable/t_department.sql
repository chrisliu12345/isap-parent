-- t_department
DROP TABLE IF EXISTS t_department;
CREATE TABLE t_department
(
  id char(36) NOT NULL,
  name varchar(128) NOT NULL COMMENT '部门名称',
  parent char(36) NOT NULL default '' COMMENT '父部门id',
  community_flag tinyint(4) NOT NULL COMMENT '小区标志',
  parent_ids varchar(255) NOT NULL,
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_department PRIMARY KEY (id)
)COMMENT= '部门信息表';