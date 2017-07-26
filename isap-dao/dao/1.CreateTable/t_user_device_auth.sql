-- t_user_device_auth
DROP TABLE IF EXISTS t_user_device_auth;
CREATE TABLE t_user_device_auth
(
 id bigint NOT NULL AUTO_INCREMENT,
 user_id bigint NOT NULL COMMENT '用户id',
 auth_id bigint NOT NULL COMMENT '权限id',
 end_date datetime NOT NULL COMMENT '结束时间',
 dev_id bigint default NULL,
 add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
 update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 CONSTRAINT pk_t_user_device_auth PRIMARY KEY (id),
 CONSTRAINT uk_t_user_device_auth UNIQUE (user_id, auth_id),
 CONSTRAINT fk_t_user_device_auth_user_id FOREIGN KEY (user_id) 
     REFERENCES t_user (id)
	 ON UPDATE CASCADE ON DELETE CASCADE,
 CONSTRAINT fk_t_user_device_auth_auth_id FOREIGN KEY (auth_id) 
     REFERENCES t_dev_authdef (id)
	 ON UPDATE CASCADE ON DELETE CASCADE
)COMMENT= '用户设备权限临时表';