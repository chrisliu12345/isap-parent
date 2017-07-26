#!/bin/sh

create_isap_tables()
{
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_enum.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_enum table failed!"
		return 1
	fi
    
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_department.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_department table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_face_server.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_face_server table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_face_server_dep.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_face_server_dep table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_plat.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_plat table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_device.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_device table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_dev_authdef.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_dev_authdef table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_menu_authdef.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_menu_authdef table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_role.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_role table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_role_dev_auth.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_role_dev_auth table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_role_menu_auth.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_role_menu_auth table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_role_dep.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_role_dep table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_user.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_role_dep table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_user_belong.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_user_belong table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_user_device_auth.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_user_device_auth table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_preset.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_preset table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_alarm_type.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_alarm_type table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_alarm_record.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_alarm_record table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_action_linkage.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_action_linkage table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_action.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_action table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_action_param.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_action_param table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_alert.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_alert table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_alert_resource.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_alert_resource table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_alert_log.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_alert_log table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_alert_log_template.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_alert_log_template table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_alert_detail.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_alert_detail table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_alert_time_limit.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_alert_time_limit table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_guard_group.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_guard_group table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_guard_group_belong.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_guard_group_belong table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_guard.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_guard table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_guard_alert.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_guard_alert table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_guard_time_template.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_guard_time_template table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_guard_week_time.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_guard_week_time table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_guard_single_time.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_guard_single_time table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_guard_plan.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_guard_plan table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_guard_plan_res.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_guard_plan_res table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_operlog.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_operlog table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_version.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_version table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_version_history.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_version_history table failed!"
		return 1
	fi
	
	mysql -uroot -p$PASSWORD -D$ISAPDATABASE<$SOFTSRCDIR/create_tables/t_version_judge.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_version_judge table failed!"
		return 1
	fi
}

create_quartz_tables()
{
	mysql -uroot -p$PASSWORD -D$QUARTZDATABASE<$SOFTSRCDIR/create_tables/t_quartz.sql >>/dev/null 2>&1
	if [ $? -ne 0 ];then
		echo "create t_quartz table failed!"
		return 1
	fi
}

SOFTSRCDIR=$(cd `dirname $0`; pwd)
PASSWORD=$1
ISAPDATABASE=$2
QUARTZDATABASE=$3

create_isap_tables
create_quartz_tables
