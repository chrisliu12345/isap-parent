@echo off & setlocal enabledelayedexpansion

set BIN_DIR=%~p0
cd %BIN_DIR%
set DEPLOY_DIR=%BIN_DIR%\..
set CONF_DIR=%DEPLOY_DIR%\conf

set LIB_JARS=""
cd ..\lib
for %%i in (*) do set LIB_JARS=!LIB_JARS!;..\lib\%%i
cd ..\bin

set proxy_id=%1%
set protocol_port=%2%
set platform_type=%3%
set platform_ipaddress=%4%
set platform_port=%5%
set login_user=%6%
set login_password=%7%

for /f "delims=*" %%i in ('findstr libraryPath %CONF_DIR%\instance.properties') do set xx=%%i
set LIBRARY_PATH="%xx:~12,999999%"

set APP_HOME=%DEPLOY_DIR%
set MAIN_CLASS=com.gosun.isap.main.Main
set APP_OPTS=-Disap.application.home=%APP_HOME% -Ddubbo.application.name=%proxy_id% -Ddubbo.registry.group=%proxy_id% ^
	-Ddubbo.registry.id=%proxy_id% -Ddubbo.provider.registry=%proxy_id% -Ddubbo.protocol.port=%protocol_port% ^
	-Disap.proxy.platform_type=%platform_type%  -Disap.proxy.platform_ipaddress=%platform_ipaddress% ^
	-Disap.proxy.platform_port=%platform_port% -Disap.proxy.user=%login_user% -Disap.proxy.password=%login_password% ^
	-Ddubbo.shutdown.hook=true -Djava.library.path=%LIBRARY_PATH%

call java -Xms64m -Xmx1024m -XX:MaxPermSize=64M -classpath ..\conf;%LIB_JARS% %APP_OPTS% %MAIN_CLASS%
