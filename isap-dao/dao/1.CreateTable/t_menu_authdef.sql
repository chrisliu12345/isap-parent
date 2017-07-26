-- t_menu_authdef
DROP TABLE IF EXISTS t_menu_authdef;
CREATE TABLE t_menu_authdef
(
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(128) NOT NULL COMMENT '菜单权限名称',
  parent bigint NOT NULL default 0 COMMENT '父权限',
  permission varchar(50) NOT NULL COMMENT '权限标识',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_menu_authdef PRIMARY KEY(id)
)COMMENT= '菜单权限信息表';