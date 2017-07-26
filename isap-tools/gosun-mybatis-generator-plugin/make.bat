
call mvn clean package -Dmaven.test.skip=true

echo off
echo "copy file..."
xcopy /Y target\gosun-mybatis-generator-plugin-1.0.0.jar ..\generatorSqlmapCustom\lib