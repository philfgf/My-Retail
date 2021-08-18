@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  myRetail startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and MY_RETAIL_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\myRetail-1.0-SNAPSHOT-plain.jar;%APP_HOME%\lib\kotlin-stdlib-jdk8-1.5.10.jar;%APP_HOME%\lib\spring-boot-starter-web-2.5.1.jar;%APP_HOME%\lib\spring-boot-gradle-plugin-2.5.1.jar;%APP_HOME%\lib\spring-boot-starter-jetty-2.5.1.jar;%APP_HOME%\lib\spring-boot-starter-actuator-2.5.1.jar;%APP_HOME%\lib\spring-boot-starter-webflux-2.5.1.jar;%APP_HOME%\lib\log4j-api-2.14.1.jar;%APP_HOME%\lib\spring-data-mongodb-3.2.4.jar;%APP_HOME%\lib\jaxb-api-2.3.1.jar;%APP_HOME%\lib\spring-boot-starter-data-jpa-2.5.1.jar;%APP_HOME%\lib\kotlin-stdlib-jdk7-1.5.10.jar;%APP_HOME%\lib\kotlin-stdlib-1.5.10.jar;%APP_HOME%\lib\spring-boot-starter-json-2.5.1.jar;%APP_HOME%\lib\spring-boot-starter-aop-2.5.1.jar;%APP_HOME%\lib\spring-boot-starter-jdbc-2.5.1.jar;%APP_HOME%\lib\spring-boot-starter-2.5.1.jar;%APP_HOME%\lib\spring-webmvc-5.3.8.jar;%APP_HOME%\lib\spring-webflux-5.3.8.jar;%APP_HOME%\lib\spring-web-5.3.8.jar;%APP_HOME%\lib\spring-boot-buildpack-platform-2.5.1.jar;%APP_HOME%\lib\spring-boot-loader-tools-2.5.1.jar;%APP_HOME%\lib\dependency-management-plugin-1.0.11.RELEASE.jar;%APP_HOME%\lib\commons-compress-1.20.jar;%APP_HOME%\lib\spring-data-jpa-2.5.1.jar;%APP_HOME%\lib\spring-orm-5.3.7.jar;%APP_HOME%\lib\spring-jdbc-5.3.8.jar;%APP_HOME%\lib\spring-tx-5.3.9.jar;%APP_HOME%\lib\spring-boot-actuator-autoconfigure-2.5.1.jar;%APP_HOME%\lib\spring-boot-autoconfigure-2.5.1.jar;%APP_HOME%\lib\spring-boot-actuator-2.5.1.jar;%APP_HOME%\lib\spring-boot-2.5.1.jar;%APP_HOME%\lib\spring-context-5.3.9.jar;%APP_HOME%\lib\spring-data-commons-2.5.4.jar;%APP_HOME%\lib\spring-aop-5.3.9.jar;%APP_HOME%\lib\spring-beans-5.3.9.jar;%APP_HOME%\lib\spring-expression-5.3.9.jar;%APP_HOME%\lib\spring-core-5.3.9.jar;%APP_HOME%\lib\jakarta.servlet-api-4.0.4.jar;%APP_HOME%\lib\jakarta.websocket-api-1.1.2.jar;%APP_HOME%\lib\tomcat-embed-el-9.0.46.jar;%APP_HOME%\lib\jetty-servlets-9.4.42.v20210604.jar;%APP_HOME%\lib\javax-websocket-server-impl-9.4.42.v20210604.jar;%APP_HOME%\lib\jetty-annotations-9.4.42.v20210604.jar;%APP_HOME%\lib\jetty-plus-9.4.42.v20210604.jar;%APP_HOME%\lib\jetty-webapp-9.4.42.v20210604.jar;%APP_HOME%\lib\websocket-server-9.4.42.v20210604.jar;%APP_HOME%\lib\micrometer-core-1.7.0.jar;%APP_HOME%\lib\spring-boot-starter-reactor-netty-2.5.1.jar;%APP_HOME%\lib\mongodb-driver-core-4.2.3.jar;%APP_HOME%\lib\HikariCP-4.0.3.jar;%APP_HOME%\lib\slf4j-api-1.7.30.jar;%APP_HOME%\lib\javax.activation-api-1.2.0.jar;%APP_HOME%\lib\jakarta.transaction-api-1.3.3.jar;%APP_HOME%\lib\jakarta.persistence-api-2.2.3.jar;%APP_HOME%\lib\hibernate-core-5.4.32.Final.jar;%APP_HOME%\lib\spring-aspects-5.3.8.jar;%APP_HOME%\lib\annotations-13.0.jar;%APP_HOME%\lib\kotlin-stdlib-common-1.5.10.jar;%APP_HOME%\lib\jakarta.annotation-api-1.3.5.jar;%APP_HOME%\lib\snakeyaml-1.28.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.12.3.jar;%APP_HOME%\lib\jackson-module-parameter-names-2.12.3.jar;%APP_HOME%\lib\jackson-annotations-2.12.3.jar;%APP_HOME%\lib\jackson-core-2.12.3.jar;%APP_HOME%\lib\jackson-datatype-jdk8-2.12.3.jar;%APP_HOME%\lib\jackson-databind-2.12.3.jar;%APP_HOME%\lib\jna-platform-5.7.0.jar;%APP_HOME%\lib\httpclient-4.5.13.jar;%APP_HOME%\lib\tomlj-1.0.0.jar;%APP_HOME%\lib\jetty-continuation-9.4.42.v20210604.jar;%APP_HOME%\lib\javax-websocket-client-impl-9.4.42.v20210604.jar;%APP_HOME%\lib\websocket-client-9.4.42.v20210604.jar;%APP_HOME%\lib\jetty-client-9.4.42.v20210604.jar;%APP_HOME%\lib\jetty-servlet-9.4.42.v20210604.jar;%APP_HOME%\lib\jetty-security-9.4.42.v20210604.jar;%APP_HOME%\lib\jetty-server-9.4.42.v20210604.jar;%APP_HOME%\lib\jetty-http-9.4.42.v20210604.jar;%APP_HOME%\lib\websocket-common-9.4.42.v20210604.jar;%APP_HOME%\lib\jetty-io-9.4.42.v20210604.jar;%APP_HOME%\lib\jetty-xml-9.4.42.v20210604.jar;%APP_HOME%\lib\jetty-util-ajax-9.4.42.v20210604.jar;%APP_HOME%\lib\jetty-util-9.4.42.v20210604.jar;%APP_HOME%\lib\websocket-servlet-9.4.42.v20210604.jar;%APP_HOME%\lib\HdrHistogram-2.1.12.jar;%APP_HOME%\lib\LatencyUtils-2.0.3.jar;%APP_HOME%\lib\reactor-netty-http-1.0.7.jar;%APP_HOME%\lib\reactor-netty-core-1.0.7.jar;%APP_HOME%\lib\reactor-core-3.4.6.jar;%APP_HOME%\lib\spring-jcl-5.3.9.jar;%APP_HOME%\lib\bson-4.2.3.jar;%APP_HOME%\lib\aspectjweaver-1.9.6.jar;%APP_HOME%\lib\hibernate-commons-annotations-5.1.2.Final.jar;%APP_HOME%\lib\jboss-logging-3.4.1.Final.jar;%APP_HOME%\lib\javassist-3.27.0-GA.jar;%APP_HOME%\lib\byte-buddy-1.10.22.jar;%APP_HOME%\lib\antlr-2.7.7.jar;%APP_HOME%\lib\jandex-2.2.3.Final.jar;%APP_HOME%\lib\classmate-1.5.1.jar;%APP_HOME%\lib\dom4j-2.1.3.jar;%APP_HOME%\lib\jaxb-runtime-2.3.1.jar;%APP_HOME%\lib\jna-5.7.0.jar;%APP_HOME%\lib\httpcore-4.4.13.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\commons-codec-1.11.jar;%APP_HOME%\lib\antlr4-runtime-4.7.2.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\websocket-api-9.4.42.v20210604.jar;%APP_HOME%\lib\asm-commons-9.0.jar;%APP_HOME%\lib\asm-analysis-9.0.jar;%APP_HOME%\lib\asm-tree-9.0.jar;%APP_HOME%\lib\asm-9.0.jar;%APP_HOME%\lib\netty-codec-http2-4.1.63.Final.jar;%APP_HOME%\lib\netty-handler-proxy-4.1.63.Final.jar;%APP_HOME%\lib\netty-codec-http-4.1.63.Final.jar;%APP_HOME%\lib\netty-resolver-dns-native-macos-4.1.63.Final-osx-x86_64.jar;%APP_HOME%\lib\netty-resolver-dns-4.1.63.Final.jar;%APP_HOME%\lib\netty-transport-native-epoll-4.1.63.Final-linux-x86_64.jar;%APP_HOME%\lib\reactive-streams-1.0.3.jar;%APP_HOME%\lib\txw2-2.3.1.jar;%APP_HOME%\lib\istack-commons-runtime-3.0.7.jar;%APP_HOME%\lib\stax-ex-1.8.jar;%APP_HOME%\lib\FastInfoset-1.2.15.jar;%APP_HOME%\lib\netty-handler-4.1.63.Final.jar;%APP_HOME%\lib\netty-codec-dns-4.1.63.Final.jar;%APP_HOME%\lib\netty-codec-socks-4.1.63.Final.jar;%APP_HOME%\lib\netty-codec-4.1.63.Final.jar;%APP_HOME%\lib\netty-transport-native-unix-common-4.1.63.Final.jar;%APP_HOME%\lib\netty-transport-4.1.63.Final.jar;%APP_HOME%\lib\netty-buffer-4.1.63.Final.jar;%APP_HOME%\lib\netty-resolver-4.1.63.Final.jar;%APP_HOME%\lib\netty-common-4.1.63.Final.jar


@rem Execute myRetail
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %MY_RETAIL_OPTS%  -classpath "%CLASSPATH%" com.target.rapid.MyRetailKt %*

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable MY_RETAIL_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%MY_RETAIL_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
