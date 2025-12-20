@echo off
echo Compiling Java classes...

REM Set paths
set PROJECT_DIR=%~dp0
set SRC_DIR=%PROJECT_DIR%src\main\java
set BUILD_DIR=%PROJECT_DIR%build\classes
set LIB_DIR=%PROJECT_DIR%src\main\webapp\WEB-INF\lib

REM Create build directory if it doesn't exist
if not exist "%BUILD_DIR%" mkdir "%BUILD_DIR%"

REM Find servlet-api.jar (adjust path as needed)
set SERVLET_API=C:\Program Files\Apache Software Foundation\Tomcat 9.0\lib\servlet-api.jar
if not exist "%SERVLET_API%" set SERVLET_API=C:\apache-tomcat-9.0.95\lib\servlet-api.jar
if not exist "%SERVLET_API%" set SERVLET_API=C:\tomcat\lib\servlet-api.jar

REM Set classpath
set CP=%BUILD_DIR%;%SERVLET_API%

echo Compiling utility classes...
javac -cp "%CP%" -d "%BUILD_DIR%" "%SRC_DIR%\util\*.java"

echo Compiling filter classes...
javac -cp "%CP%" -d "%BUILD_DIR%" "%SRC_DIR%\filter\*.java"

echo Compiling controller classes...
javac -cp "%CP%" -d "%BUILD_DIR%" "%SRC_DIR%\controller\*.java"

echo Compilation complete!
echo.
echo Files compiled to: %BUILD_DIR%
echo.
pause