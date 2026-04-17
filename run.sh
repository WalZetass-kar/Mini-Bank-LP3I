#!/bin/bash

# Script untuk compile dan run Mini Bank LP3I Pekanbaru

echo "==================================="
echo "Mini Bank LP3I Pekanbaru"
echo "==================================="
echo ""

# Check if SQLite JDBC driver exists
if [ ! -f "sqlite-jdbc-3.42.0.0.jar" ]; then
    echo "SQLite JDBC driver tidak ditemukan!"
    echo "Downloading SQLite JDBC driver..."
    wget https://github.com/xerial/sqlite-jdbc/releases/download/3.42.0.0/sqlite-jdbc-3.42.0.0.jar
    echo ""
fi

# Compile
echo "Compiling Java files..."
javac -cp ".:sqlite-jdbc-3.42.0.0.jar" MiniBankApp/*/*.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo ""
    echo "Running application..."
    echo ""
    java -cp ".:MiniBankApp:sqlite-jdbc-3.42.0.0.jar" --enable-native-access=ALL-UNNAMED main.Main
else
    echo "Compilation failed!"
    exit 1
fi
