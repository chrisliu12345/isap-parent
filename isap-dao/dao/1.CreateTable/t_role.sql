-- t_role
DROP TABLE IF EXISTS t_role;
CREATE TABLE t_role
(
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(128) NOT NULL COMMENT '角色名称',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_role PRIMARY KEY (id)
)COMMENT= '角色信息表';