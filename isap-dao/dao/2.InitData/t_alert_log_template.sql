insert into t_alert_log_template(log_type, content, params_count) VALUES (0, '%2$s 将警情标记为 %3$s，%1$s', 3);
insert into t_alert_log_template(log_type, content, params_count) VALUES (1, '%2$s 联系保安 %3$s 出警，%1$s', 3);
insert into t_alert_log_template(log_type, content, params_count) VALUES (2, '%2$s 联系保安 %3$s 失败，原因：%4$s，%1$s', 4);
insert into t_alert_log_template(log_type, content, params_count) VALUES (3, '%2$s 确认保安 %3$s 到达现场，%1$s', 3);
insert into t_alert_log_template(log_type, content, params_count) VALUES (10, '%2$s 收到保安 %3$s 二次回电，内容：%4$s，%1$s', 4);
insert into t_alert_log_template(log_type, content, params_count) VALUES (11, '%2$s 记录保安 %3$s 找到可疑人员，警情处理结束，%1$s', 4);
insert into t_alert_log_template(log_type, content, params_count) VALUES (12, '%2$s 30 分钟内未收到保安二次回电，警情处理结束，%1$s', 2);
insert into t_alert_log_template(log_type, content, params_count) VALUES (20, '%2$s 联系不到保安，警情处理失败，%1$s', 2);
insert into t_alert_log_template(log_type, content, params_count) VALUES (21, '%2$s 确认保安 %3$s 未到达现场，原因：%4$s，警情处理失败，%1$s', 4);
insert into t_alert_log_template(log_type, content, params_count) VALUES (22, '%2$s 30 分钟内未收到保安回电，警情处理失败，%1$s', 2);

