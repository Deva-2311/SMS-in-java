@echo off
title Stock Management System - Launcher
echo ==============================================
echo     Starting Stock Management System...
echo ==============================================
echo.

REM Go to project source folder
cd /d "E:\Backup\Desktop\Deva\KRCE\Second Year\Java\Project\Ex_GUIStockManagement\src"

echo Compiling project...
javac -classpath "..\lib\mysql-connector-j-9.5.0.jar" gui\*.java product\*.java order\*.java inventory\*.java persistence\*.java report\*.java utilities\*.java

IF %ERRORLEVEL% NEQ 0 (
    echo.
    echo Compilation failed. Fix errors and try again.
    echo.
    pause
    exit /b
)

echo.
echo Running application...
echo.

java -classpath ".;..\lib\mysql-connector-j-9.5.0.jar" gui.RunApp

echo.
echo Application closed.
pause
