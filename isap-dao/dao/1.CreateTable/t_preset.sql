-- t_preset
DROP TABLE IF EXISTS t_preset;
CREATE TABLE t_preset
(
  id bigint NOT NULL AUTO_INCREMENT,
  dev_id char(36) NOT NULL COMMENT '设备id',
  pre_value varchar(36) NOT NULL COMMENT '预置位值',
  name varchar(128) NOT NULL COMMENT '预置位名称',
  del_flag tinyint(1) unsigned NOT NULL COMMENT '删除标志',
  add_time timestamp NOT NULL default CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT pk_t_preset PRIMARY KEY (id),
  CONSTRAINT fk_t_preset_dev_id FOREIGN KEY (dev_id) 
      REFERENCES t_device (id)
	  ON UPDATE CASCADE ON DELETE CASCADE
)COMMENT= '预置位表';