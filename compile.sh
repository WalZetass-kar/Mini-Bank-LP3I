#!/bin/bash

# Script untuk compile Mini Bank LP3I Pekanbaru

echo "==================================="
echo "Compiling Mini Bank LP3I Pekanbaru"
echo "==================================="
echo ""

# Check if SQLite JDBC driver exists
if [ ! -f "sqlite-jdbc-3.42.0.0.jar" ]; then
    echo "Error: SQLite JDBC driver tidak ditemukan!"
    echo "Silakan download dari: https://github.com/xerial/sqlite-jdbc/releases"
    echo "Atau jalankan: wget https://github.com/xerial/sqlite-jdbc/releases/download/3.42.0.0/sqlite-jdbc-3.42.0.0.jar"
    exit 1
fi

# Compile all Java files
echo "Compiling Java files..."
javac -cp ".:sqlite-jdbc-3.42.0.0.jar" \
    MiniBankApp/utils/*.java \
    MiniBankApp/model/*.java \
    MiniBankApp/database/*.java \
    MiniBankApp/repository/*.java \
    MiniBankApp/controller/*.java \
    MiniBankApp/view/*.java \
    MiniBankApp/main/*.java

if [ $? -eq 0 ]; then
    echo ""
    echo "✅ Compilation successful!"
    echo ""
    echo "Untuk menjalankan aplikasi, gunakan:"
    echo "  ./run.sh"
    echo "atau:"
    echo "  java -cp \".:sqlite-jdbc-3.42.0.0.jar\" main.Main"
else
    echo ""
    echo "❌ Compilation failed!"
    exit 1
fi
