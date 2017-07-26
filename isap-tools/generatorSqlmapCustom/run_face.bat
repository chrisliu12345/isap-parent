@echo off

if not defined JAVA_HOME (
  echo Error: JAVA_HOME is not set.
  goto :eof
)

set JAVA_HOME=%JAVA_HOME:"=%

if not exist "%JAVA_HOME%"\bin\java.exe (
  echo Error: JAVA_HOME is incorrectly set.
  goto :eof
)

del ..\..\isap-dao\src\main\resources\com\gosun\isap\dao\mapper\face\*.xml


set JAVA="%JAVA_HOME%"\bin\java

set MYHOME=.
set CFGDIR=%MYHOME%\conf
set CLASSPATH=%MYHOME%\bin;%MYHOME%\lib\*;%CLASSPATH%

set GENERATOR_CONFIG=%CFGDIR%\generatorConfig_face.xml

set MAIN=GeneratorSqlmap

echo on
call %JAVA% "-Dgenerator_config=%GENERATOR_CONFIG%" -cp "%CLASSPATH%" %MAIN% %*
