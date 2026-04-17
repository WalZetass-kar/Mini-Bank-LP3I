# 🔧 Setup Project di NetBeans IDE

## 📋 Persyaratan

- **NetBeans IDE** 12.0 atau lebih baru
- **JDK** 11 atau lebih baru
- **SQLite JDBC Driver** (sudah termasuk: `sqlite-jdbc-3.42.0.0.jar`)

---

## 🚀 Cara Membuka Project di NetBeans

### Metode 1: Open Project (Recommended)

1. **Buka NetBeans IDE**

2. **Klik Menu**: `File` → `Open Project...`
   - Atau tekan `Ctrl+Shift+O` (Windows/Linux)
   - Atau tekan `Cmd+Shift+O` (Mac)

3. **Pilih Folder Project**:
   - Navigate ke folder project ini
   - Folder akan memiliki icon NetBeans (🔷) jika sudah terdeteksi
   - Klik `Open Project`

4. **Project akan muncul** di panel "Projects" di sebelah kiri

5. **Expand project tree** untuk melihat struktur:
   ```
   MiniBankLP3IPekanbaru
   ├── Source Packages
   │   ├── MiniBankApp
   │   │   ├── controller
   │   │   ├── database
   │   │   ├── main
   │   │   ├── model
   │   │   ├── repository
   │   │   ├── utils
   │   │   └── view
   │   └── (default package)
   ├── Libraries
   │   ├── JDK 11
   │   └── sqlite-jdbc-3.42.0.0.jar
   └── Test Packages
   ```

---

### Metode 2: Import Existing Project

1. **Buka NetBeans IDE**

2. **Klik Menu**: `File` → `Import Project` → `From Existing Sources...`

3. **Pilih folder project** dan klik `Next`

4. **NetBeans akan auto-detect** struktur project

5. **Klik Finish**

---

## ⚙️ Konfigurasi Project

### 1. Verifikasi Library (SQLite JDBC)

Jika library tidak terdeteksi otomatis:

1. **Klik kanan** pada project → `Properties`
2. Pilih **`Libraries`** di panel kiri
3. Klik **`Add JAR/Folder`**
4. Pilih file **`sqlite-jdbc-3.42.0.0.jar`**
5. Klik **`OK`**

### 2. Set Main Class

1. **Klik kanan** pada project → `Properties`
2. Pilih **`Run`** di panel kiri
3. Di field **`Main Class`**, masukkan: `main.Main`
4. Klik **`OK`**

### 3. Set Java Version

1. **Klik kanan** pada project → `Properties`
2. Pilih **`Sources`** di panel kiri
3. Set **`Source/Binary Format`** ke: `JDK 11` atau lebih baru
4. Klik **`OK`**

---

## ▶️ Menjalankan Aplikasi

### Cara 1: Run Project
1. **Klik kanan** pada project → `Run`
2. Atau tekan **`F6`**
3. Atau klik tombol **▶️ Run** di toolbar

### Cara 2: Run Main Class
1. **Buka file** `main/Main.java`
2. **Klik kanan** di editor → `Run File`
3. Atau tekan **`Shift+F6`**

### Cara 3: Debug Mode
1. **Klik kanan** pada project → `Debug`
2. Atau tekan **`Ctrl+F5`**

---

## 🔨 Build Project

### Clean and Build
1. **Klik kanan** pada project → `Clean and Build`
2. Atau tekan **`Shift+F11`**
3. File JAR akan dibuat di folder `dist/`

### Build Only
1. **Klik kanan** pada project → `Build`
2. Atau tekan **`F11`**

### Clean
1. **Klik kanan** pada project → `Clean`
2. Menghapus folder `build/` dan `dist/`

---

## 📁 Struktur Project di NetBeans

```
MiniBankLP3IPekanbaru/
├── nbproject/                    # NetBeans configuration
│   ├── project.xml              # Project metadata
│   ├── project.properties       # Build properties
│   ├── build-impl.xml           # Build implementation
│   └── genfiles.properties      # Generated files tracking
├── build/                        # Compiled classes (auto-generated)
│   └── classes/
├── dist/                         # Distribution JAR (auto-generated)
│   ├── MiniBankLP3IPekanbaru.jar
│   ├── sqlite-jdbc-3.42.0.0.jar
│   └── bank.db
├── MiniBankApp/                  # Source code
│   ├── controller/
│   ├── database/
│   ├── main/
│   ├── model/
│   ├── repository/
│   ├── utils/
│   └── view/
├── build.xml                     # Ant build script
├── manifest.mf                   # JAR manifest
├── sqlite-jdbc-3.42.0.0.jar     # SQLite JDBC driver
└── bank.db                       # Database file
```

---

## 🐛 Troubleshooting

### Problem 1: "Cannot find main class"
**Solution**:
1. Klik kanan project → Properties → Run
2. Set Main Class: `main.Main`
3. Klik OK

### Problem 2: "ClassNotFoundException: org.sqlite.JDBC"
**Solution**:
1. Klik kanan project → Properties → Libraries
2. Add JAR/Folder → Pilih `sqlite-jdbc-3.42.0.0.jar`
3. Klik OK
4. Clean and Build project

### Problem 3: "Source level 11 requires target level 11"
**Solution**:
1. Klik kanan project → Properties → Sources
2. Set Source/Binary Format: JDK 11
3. Klik OK

### Problem 4: Database tidak ditemukan
**Solution**:
1. Pastikan file `bank.db` ada di root folder project
2. Atau akan dibuat otomatis saat pertama kali run
3. Check console output untuk error messages

### Problem 5: Project tidak terdeteksi sebagai NetBeans project
**Solution**:
1. Pastikan folder `nbproject/` ada
2. Pastikan file `build.xml` ada
3. Restart NetBeans IDE
4. File → Open Project → Pilih folder project

---

## 🎨 NetBeans Features yang Bisa Digunakan

### 1. Code Completion
- Tekan **`Ctrl+Space`** untuk auto-complete
- Tekan **`Ctrl+Shift+Space`** untuk smart completion

### 2. Code Navigation
- **`Ctrl+Click`** pada class/method untuk jump to definition
- **`Alt+Shift+O`** untuk Go to File
- **`Ctrl+Shift+T`** untuk Go to Type
- **`Alt+F7`** untuk Find Usages

### 3. Refactoring
- **`Ctrl+R`** untuk Rename
- **`Alt+Shift+R`** untuk Refactor menu
- Klik kanan → Refactor → (pilih opsi)

### 4. Debugging
- **`Ctrl+F8`** untuk Toggle Breakpoint
- **`F5`** untuk Step Into
- **`F6`** untuk Step Over
- **`F7`** untuk Step Out
- **`Ctrl+F5`** untuk Continue

### 5. Code Formatting
- **`Alt+Shift+F`** untuk Format code
- **`Ctrl+Shift+I`** untuk Fix Imports

### 6. Version Control (Git)
- **`Ctrl+Shift+9`** untuk Git menu
- Klik kanan project → Git → (pilih opsi)

---

## 📊 Build Output

Setelah build berhasil, file-file berikut akan dibuat:

### dist/MiniBankLP3IPekanbaru.jar
- Executable JAR file
- Bisa dijalankan dengan: `java -jar dist/MiniBankLP3IPekanbaru.jar`

### dist/sqlite-jdbc-3.42.0.0.jar
- SQLite JDBC driver (dependency)
- Harus ada di folder yang sama dengan JAR utama

### dist/bank.db
- Database file (jika sudah ada)
- Akan dibuat otomatis jika belum ada

---

## 🚀 Menjalankan JAR File

### Dari NetBeans:
1. Klik kanan project → Run
2. Atau F6

### Dari Terminal/Command Prompt:
```bash
# Dari folder project
java -jar dist/MiniBankLP3IPekanbaru.jar

# Atau dari folder dist
cd dist
java -jar MiniBankLP3IPekanbaru.jar
```

### Double-click JAR (Windows):
1. Navigate ke folder `dist/`
2. Double-click `MiniBankLP3IPekanbaru.jar`
3. Aplikasi akan terbuka

---

## 📝 Tips NetBeans

### 1. Customize Editor
- Tools → Options → Editor → Formatting
- Pilih Java → Set indentation, braces, spaces

### 2. Dark Theme
- Tools → Options → Appearance
- Pilih "Dark Metal" atau "FlatLaf Dark"

### 3. Increase Memory
- Edit `netbeans.conf`
- Set: `-J-Xms512m -J-Xmx2048m`

### 4. Plugins
- Tools → Plugins
- Install: Git, Maven, Gradle (jika perlu)

### 5. Keyboard Shortcuts
- Help → Keyboard Shortcuts Card
- Atau tekan `Ctrl+Shift+/` untuk help

---

## ✅ Checklist Setup

- [ ] NetBeans IDE terinstall (12.0+)
- [ ] JDK 11+ terinstall
- [ ] Project dibuka di NetBeans
- [ ] Library `sqlite-jdbc-3.42.0.0.jar` terdeteksi
- [ ] Main class set ke `main.Main`
- [ ] Source level set ke JDK 11
- [ ] Build berhasil (no errors)
- [ ] Run berhasil (aplikasi terbuka)
- [ ] Database `bank.db` ada/terbuat

---

## 🎉 Selesai!

Project sekarang sudah siap dibuka dan dikembangkan di NetBeans IDE!

**Untuk menjalankan**:
1. Open Project di NetBeans
2. Tekan F6 atau klik Run
3. Aplikasi akan terbuka

**Untuk build JAR**:
1. Clean and Build (Shift+F11)
2. JAR file ada di `dist/MiniBankLP3IPekanbaru.jar`

---

**Happy Coding! 🚀**

**Project**: BANK LP3I PEKANBARU  
**Version**: 4.2  
**IDE**: NetBeans 12.0+  
**Java**: JDK 11+
