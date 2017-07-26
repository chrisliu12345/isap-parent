-- t_device
DROP TABLE IF EXISTS t_device;
CREATE TABLE t_device
(
  id char(36) NOT NULL,
  plat_id bigint NOT NULL COMMENT '平台id',
  code varchar(128) NOT NULL COMMENT '设备编码',
  name varchar(128) NOT NULL COMMENT '设备名称',
  dev_type int NOT NULL COMMENT '设备类型',
  channel_type int default NULL COMMENT '通道类型',
  channel_sub_type int default NULL COMMENT '通道子类型',
  status tinyint(4) unsigned NOT NULL COMMENT '设备状态',
  net_status tinyint(4) unsigned NOT NULL COMMENT '入网状态',
  parent char(36) default NULL COMMENT '父设备id',
  department_id char(36) NOT NULL COMMENT '部门id',
  location_x varchar(36) default NULL COMMENT '经纬度X',
  location_y varchar(36) default NULL COMMENT '经纬度Y',
  brief_name varchar(36) default NULL COMMENT '简名',
  name_spell char(10) default NULL COMMENT '名称拼写',
  description varchar(255) default NULL COMMENT '设备描述',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_device PRIMARY KEY (id),
  CONSTRAINT uk_t_device UNIQUE (plat_id, code),
  CONSTRAINT fk_t_device_plat_id FOREIGN KEY (plat_id) 
      REFERENCES t_plat (id) 
	  ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_t_device_department_id FOREIGN KEY (department_id) 
      REFERENCES t_department (id) 
	  ON UPDATE CASCADE ON DELETE CASCADE
)COMMENT= '设备表';