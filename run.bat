@echo off
REM Script untuk compile dan run Mini Bank LP3I Pekanbaru

echo ===================================
echo Mini Bank LP3I Pekanbaru
echo ===================================
echo.

REM Check if SQLite JDBC driver exists
if not exist "sqlite-jdbc-3.42.0.0.jar" (
    echo SQLite JDBC driver tidak ditemukan!
    echo Silakan download dari: https://github.com/xerial/sqlite-jdbc/releases
    echo Letakkan file sqlite-jdbc-3.42.0.0.jar di direktori ini
    pause
    exit /b 1
)

REM Compile
echo Compiling Java files...
javac -cp ".;sqlite-jdbc-3.42.0.0.jar" MiniBankApp\utils\*.java MiniBankApp\model\*.java MiniBankApp\database\*.java MiniBankApp\repository\*.java MiniBankApp\controller\*.java MiniBankApp\view\*.java MiniBankApp\main\*.java

if %errorlevel% equ 0 (
    echo Compilation successful!
    echo.
    echo Running application...
    echo.
    java -cp ".;MiniBankApp;sqlite-jdbc-3.42.0.0.jar" --enable-native-access=ALL-UNNAMED main.Main
) else (
    echo Compilation failed!
    pause
    exit /b 1
)
