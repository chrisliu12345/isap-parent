#!/bin/sh
CP_CMD=/bin/cp

SOFTSRCDIR=$(cd `dirname $0`; pwd)
#安装目录
INSTALLDIR=/opt
MYSQLINSTALLDIR=$INSTALLDIR/mysql
SQLDATADIR=/mnt/mysql/data
SECUREFILEPRIV=/var/lib/mysql-files
LOGFILE=/var/log/mysqld.log

#数据库配置
new_passwd=root
ISAPDATABASE=isap
QUARTZDATABASE=quartz

installmysql()
{
	#如果有msyql服务启动，必须停止，否则会安装失败
	if [ -n "`pidof mysqld`" ]; then
		systemctl stop mysqld
		sleep 1
	fi
	
	#强制停止mysql服务
	if [ -n "`pidof mysqld`" ]; then
		killall mysqld
		sleep 1
	fi
	
	#无法停止，返回错误，不再安装
	if [ -n "`pidof mysqld`" ]; then
		echo "stop mysql failed! install database failed!"
		return 1
	fi
	
	#用户组和用户
	if [ -n "`grep mysql /etc/passwd`" ]; then
		userdel -r mysql  >>/dev/null 2>&1
	fi
	
	if [ -n "`grep mysql /etc/group`" ]; then
		groupdel mysql
	fi
	
	if [ -z "`grep mysql /etc/group`" ]; then
		groupadd mysql
	fi
	
	if [ -z "`grep mysql /etc/passwd`" ]; then
		useradd -r -g mysql mysql
	fi
	
	#环境初始化
	if [ -e "$MYSQLINSTALLDIR" ] && [ -d "$MYSQLINSTALLDIR" ]; then
		rm -fr $MYSQLINSTALLDIR
	fi
	mkdir -p $MYSQLINSTALLDIR
	
	if [ -e "$SQLDATADIR" ] && [ -d "$SQLDATADIR" ]; then
		rm -fr $SQLDATADIR
	fi
	mkdir -p $SQLDATADIR
	
	if [ -e "$SECUREFILEPRIV" ] && [ -d "$SECUREFILEPRIV" ]; then
		rm -fr $SECUREFILEPRIV
	fi
	mkdir -p $SECUREFILEPRIV
	
	#安装环境必须是64位
	system_bit=`getconf LONG_BIT`
	if [ ${system_bit} -eq 64 ]; then
		tar xzvf $SOFTSRCDIR/mysql_x86_64-5.7.18.tar.gz -C $SOFTSRCDIR >>/dev/null 2>&1
	elif [ ${system_bit} -eq 32 ]; then
		echo "just support system 64"
		return 1
	fi
	
	cp -fr $SOFTSRCDIR/mysql/*  $MYSQLINSTALLDIR
	
	#检查系统是否缺少libaio库
	if [ -z "`ls -al /usr/lib64 | grep libaio`" ]; then
		chmod 755 $SOFTSRCDIR/extra_lib/*
		cp -fr $SOFTSRCDIR/extra_lib/* /usr/lib64
	fi
	
	chown -R mysql:mysql $MYSQLINSTALLDIR
	chown -R mysql:mysql $SQLDATADIR
	chown -R mysql:mysql $SECUREFILEPRIV
	
	#删除旧的Mysql配置文件，防止初始化失败
	if [ -f  "/etc/my.cnf" ]; then
		rm -f /etc/my.cnf
	fi
	
	#删除旧的Mysql日志文件，防止启动失败
	if [ -f $LOGFILE ]; then
		rm -f $LOGFILE
	fi
	
	#初始化配置表
	echo "start init mysql"
	if [ -f $SOFTSRCDIR/install.tmp ]; then
		rm -f $SOFTSRCDIR/install.tmp
	fi
	$MYSQLINSTALLDIR/bin/mysqld --initialize --user=mysql  --basedir=$MYSQLINSTALLDIR  --datadir=$SQLDATADIR  >>$SOFTSRCDIR/install.tmp 2>&1
	if [ $? -eq 0 ];then
		echo "Initialize mysql succeed!"
	else
		echo "Initialize mysql failed!"
		exit 1
	fi
	
	#配置Mysql配置文件
	${CP_CMD} -rf $SOFTSRCDIR/config/my.cnf /etc/my.cnf
	
	#拷贝数据库备份脚本
	${CP_CMD} -rf $SOFTSRCDIR/dbbackup.sh $INSTALLDIR
	
	#将Mysql加入到systemctl管理
	if [ -f /usr/lib/systemd/system/mysqld.service ]; then
		rm -fr /usr/lib/systemd/system/mysqld.service
	fi  
	echo -e "[Unit]\nDescription=mysqld\nAfter=network.target" >>/usr/lib/systemd/system/mysqld.service
	echo -e "\n\n"  >>/usr/lib/systemd/system/mysqld.service
	echo -e "[Service]\nType=forking\nExecStart=$MYSQLINSTALLDIR/support-files/mysql.server start" >>/usr/lib/systemd/system/mysqld.service
	echo -e "ExecReload=$MYSQLINSTALLDIR/support-files/mysql.server restart" >>/usr/lib/systemd/system/mysqld.service
	echo -e "ExecStop=$MYSQLINSTALLDIR/support-files/mysql.server stop"  >>/usr/lib/systemd/system/mysqld.service
	echo -e "\n\n"  >>/usr/lib/systemd/system/mysqld.service
	echo -e "[Install]\nWantedBy=multi-user.target" >>/usr/lib/systemd/system/mysqld.service	
	
	#建立软连接
	if [ -e /usr/local/bin/mysql ] && [ -L /usr/local/bin/mysql ]; then                 
		rm -f /usr/local/bin/mysql
	fi
	ln -s $MYSQLINSTALLDIR/bin/mysql /usr/local/bin
	
	if [ -e /usr/local/bin/mysqladmin ] && [ -L /usr/local/bin/mysqladmin ]; then
		rm -f /usr/local/bin/mysqladmin
	fi
	ln -s $MYSQLINSTALLDIR/bin/mysqladmin /usr/local/bin
	
	if [ -e /usr/local/bin/mysqld_safe ] && [ -L /usr/local/bin/mysqld_safe ]; then
		rm -f /usr/local/bin/mysqld_safe
        fi
	ln -s $MYSQLINSTALLDIR/bin/mysqld_safe /usr/local/bin
	
	#启动mysql,启动失败直接退出
	systemctl start mysqld    >>/dev/null 2>&1                
	if [ -z "`pidof mysqld`" ];
	then	
		exit 1
	fi
	
	#配置新密码
	#获取初始化配置表生成的临时密码  
	mysql_passwd=`cat $SOFTSRCDIR/install.tmp | grep -i "root@localhost: " | awk -F 'root@localhost: ' '{print $2}'`   >>/dev/null 2>&1
	#mysql -uroot -p${mysql_passwd} -e "alter user 'root'@'localhost' identified by 'passwd'";
	$MYSQLINSTALLDIR/bin/mysqladmin -u root --password=${mysql_passwd} password $new_passwd    >>/dev/null 2>&1
	if [ $? -eq 0 ]; then
		echo "mysql set new password succeed!"
	else
		echo "mysql set new password failed!"
		exit 1
	fi

	#配置用户
 	mysql -uroot -p$new_passwd -e "grant all privileges on *.* to 'root'@'%' identified by '$new_passwd' with grant option"  >>/dev/null 2>&1
 	if [ $? -eq 0 ]; then
		 mysql -uroot -p$new_passwd -e "flush privileges;"  >>/dev/null 2>&1
		echo "init user succeed!"
	else
		echo "init user failed!"
		exit 1
	fi

	#新建数据库
	mysql -uroot -p$new_passwd -Dmysql<$SOFTSRCDIR/createdatabase.sql  >>/dev/null 2>&1
	if [ $? -eq 0 ]; then
		echo "create database succeed!"
	else
		echo "create database failed!"
		exit 1
	fi
	
	#新建表
	sh $SOFTSRCDIR/createtables.sh $new_passwd $ISAPDATABASE $QUARTZDATABASE >>/dev/null 2>&1
	if [ $? -eq 0 ]; then
		echo "create tables succeed!"
	else
		echo "create tables failed!"
		exit 1
	fi
	
	#初始化表
	sh $SOFTSRCDIR/inittables.sh $new_passwd $ISAPDATABASE >>/dev/null 2>&1
	if [ $? -eq 0 ]; then
		echo "init tables succeed!"
	else
		echo "init tables failed!"
		exit 1
	fi
	
	#关闭mysql
	systemctl stop mysqld  >>/dev/null 2>&1
}

installmysql