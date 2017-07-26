
set isap_proxy_home=..

set isap_native_home=%isap_proxy_home%\isap-proxy-native

set classpath=%isap_proxy_home%\isap-proxy-api\src\main\java 

set include_dir=%isap_native_home%
set src_dir=%isap_native_home%\src
set lib_dir=%isap_native_home%\lib

javah.exe -d %include_dir% -classpath %classpath% com.gosun.isap.proxy.api.sdk.NativeSdk