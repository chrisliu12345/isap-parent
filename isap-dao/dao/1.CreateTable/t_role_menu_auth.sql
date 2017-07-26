-- t_role_menu_auth
DROP TABLE IF EXISTS t_role_menu_auth;
CREATE TABLE t_role_menu_auth
(
  id bigint NOT NULL AUTO_INCREMENT,
  role_id bigint NOT NULL COMMENT '角色id',
  auth_id bigint NOT NULL COMMENT '菜单权限id',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_role_menu_auth PRIMARY KEY (id),
  CONSTRAINT ul_t_role_menu_auth UNIQUE (role_id, auth_id),
  CONSTRAINT fk_t_role_menu_auth_role_id FOREIGN KEY (role_id) 
      REFERENCES t_role (id)
	  ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_t_role_menu_auth_auth_id FOREIGN KEY (auth_id) 
      REFERENCES t_menu_authdef (id)
	  ON UPDATE CASCADE ON DELETE CASCADE
)COMMENT= '角色菜单权限表';