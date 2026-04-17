# 🏦 BANK LP3I PEKANBARU - Mini Banking System

Aplikasi perbankan mini berbasis Java Swing dengan fitur lengkap untuk manajemen rekening, transaksi, dan kartu ATM.

## 🚀 Quick Start

### Buka di NetBeans IDE

1. **Open Project**:
   ```
   File → Open Project → Pilih folder project ini
   ```

2. **Run**:
   ```
   Tekan F6 atau klik Run Project
   ```

3. **Build JAR**:
   ```
   Tekan Shift+F11 atau Clean and Build
   ```

📖 **Panduan lengkap**: Lihat [NETBEANS_SETUP.md](NETBEANS_SETUP.md)

---

## 📋 Persyaratan

- **JDK**: 11 atau lebih baru
- **IDE**: NetBeans 12.0+ (recommended) atau IDE Java lainnya
- **Database**: SQLite (driver sudah included)

---

## ✨ Fitur Utama

### 🔐 Autentikasi
- Login dengan nomor kartu ATM dan PIN
- Registrasi user baru
- Pembuatan kartu ATM

### 💰 Transaksi
- **Cek Saldo** - Lihat saldo rekening real-time
- **Tarik Uang** - Penarikan tunai (kelipatan Rp 50.000)
- **Setor Uang** - Setoran tunai
- **Transfer** - Transfer antar rekening
- **Riwayat** - Lihat semua transaksi

### 💳 Manajemen Kartu
- Info kartu ATM lengkap
- Status kartu (AKTIF/BLOKIR/EXPIRED)
- Ubah PIN kartu
- Masa berlaku kartu

### ⚙️ Pengaturan
- Ubah PIN kartu ATM
- Lihat informasi akun lengkap

---

## 🎨 Tampilan

- **Theme**: Dark Blue Professional Banking
- **Design**: Modern, Clean, Responsive
- **Layout**: Single Page Application (SPA) style
- **Components**: Custom styled dengan Java Swing

---

## 📁 Struktur Project

```
MiniBankLP3IPekanbaru/
├── MiniBankApp/
│   ├── controller/      # Business logic
│   ├── database/        # Database connection & initialization
│   ├── main/           # Main entry point
│   ├── model/          # Data models (User, Account, Card, Transaction)
│   ├── repository/     # Database operations (CRUD)
│   ├── utils/          # UI components & constants
│   └── view/           # UI views (12 screens)
├── nbproject/          # NetBeans configuration
├── build.xml           # Ant build script
├── manifest.mf         # JAR manifest
├── sqlite-jdbc-3.42.0.0.jar  # SQLite JDBC driver
└── bank.db            # Database file (auto-created)
```

📖 **Penjelasan lengkap**: Lihat [VIEW_STRUCTURE_EXPLANATION.md](VIEW_STRUCTURE_EXPLANATION.md)

---

## 🔨 Build & Run

### Dari NetBeans:
```
F6          - Run Project
Shift+F11   - Clean and Build
Ctrl+F5     - Debug
```

### Dari Terminal:
```bash
# Compile
bash compile.sh

# Run
bash run.sh

# Atau manual
javac -cp ".:sqlite-jdbc-3.42.0.0.jar" MiniBankApp/**/*.java main/*.java
java -cp ".:sqlite-jdbc-3.42.0.0.jar" main.Main
```

### Run JAR:
```bash
java -jar dist/MiniBankLP3IPekanbaru.jar
```

---

## 💾 Database

### Schema:
- **users** - Data user (nama, NIK, alamat, dll)
- **accounts** - Rekening (nomor rekening, saldo)
- **cards** - Kartu ATM (nomor kartu, PIN, status)
- **transactions** - Riwayat transaksi

### Initial Data:
- Saldo awal: **Rp 50.000** (setelah registrasi)
- Nomor kartu: Auto-generate 16 digit
- Nomor rekening: Auto-generate 10 digit
- PIN: User-defined 6 digit

---

## 🎯 User Flow

```
Login → Dashboard → Pilih Menu:
├── Cek Saldo
├── Tarik Uang
├── Setor Uang
├── Transfer
├── Riwayat Transaksi
├── Info Kartu ATM
├── Pengaturan
│   ├── Ubah PIN
│   └── Info Akun
└── Keluar
```

---

## 🔒 Keamanan

- ✅ PIN terenkripsi (hashed)
- ✅ Validasi input ketat
- ✅ Blokir kartu setelah 3x salah PIN
- ✅ Konfirmasi untuk transaksi penting
- ✅ Session management

---

## 🐛 Troubleshooting

### Database tidak ditemukan
```bash
# Database akan dibuat otomatis saat pertama kali run
# Atau buat manual dengan menjalankan DatabaseInitializer
```

### Library tidak ditemukan
```bash
# Pastikan sqlite-jdbc-3.42.0.0.jar ada di root folder
# Di NetBeans: Properties → Libraries → Add JAR
```

### Main class tidak ditemukan
```bash
# Di NetBeans: Properties → Run → Main Class: main.Main
```

---

## 📚 Dokumentasi

- [NETBEANS_SETUP.md](NETBEANS_SETUP.md) - Setup NetBeans lengkap
- [VIEW_STRUCTURE_EXPLANATION.md](VIEW_STRUCTURE_EXPLANATION.md) - Penjelasan struktur view
- [DEBUG_REGISTRATION_FIX.md](DEBUG_REGISTRATION_FIX.md) - Debug report

---

## 🎓 Teknologi

- **Language**: Java 11+
- **GUI Framework**: Java Swing
- **Database**: SQLite
- **JDBC Driver**: sqlite-jdbc-3.42.0.0
- **Build Tool**: Ant (NetBeans)
- **Architecture**: MVC Pattern

---

## 👨‍💻 Development

### IDE Recommended:
- **NetBeans** 12.0+ (best support)
- IntelliJ IDEA
- Eclipse
- VS Code (dengan Java extensions)

### Coding Standards:
- Java naming conventions
- MVC architecture
- Clean code principles
- Comprehensive comments

---

## 📝 License

Educational project - Free to use and modify

---

## 🙏 Credits

**Project**: Mini Banking System  
**Institution**: LP3I Pekanbaru  
**Version**: 4.2  
**Year**: 2026

---

## 📞 Support

Untuk pertanyaan atau issue, silakan buat issue di repository atau hubungi developer.

---

**Happy Banking! 🏦💰**
