# 📁 STRUKTUR FILE VIEW - BANK LP3I PEKANBARU

## 📋 OVERVIEW

Folder `MiniBankApp/view/` berisi semua file tampilan (UI) aplikasi perbankan. Setiap file Java (.java) merepresentasikan satu halaman/layar dalam aplikasi, dan file .class adalah hasil kompilasi.

**Total View Files**: 12 file Java utama
**Pattern**: MVC (Model-View-Controller) - View Layer
**Framework**: Java Swing (JFrame, JPanel)
**Theme**: Dark Blue Professional Banking

---

## 🗂️ STRUKTUR FILE

### 📄 File Types

1. **`.java` files** - Source code (yang kita edit)
2. **`.class` files** - Compiled bytecode (hasil kompilasi)
3. **`$1.class`, `$2.class`, dll** - Inner classes / Anonymous classes (event handlers, custom components)

---

## 🎯 DAFTAR VIEW FILES & PERANNYA

### 1️⃣ **LoginView.java** 
**Peran**: Halaman Login (Entry Point Aplikasi)

**Fungsi**:
- Tampilan awal saat aplikasi dibuka
- Form input: Nomor Kartu ATM (16 digit) dan PIN (6 digit)
- Validasi login user
- Tombol "Login" dan "Buat Akun Baru"
- Checkbox "Tampilkan PIN"
- Redirect ke DashboardView jika login berhasil
- Redirect ke RegisterView jika klik "Buat Akun Baru"

**Komponen Utama**:
- JTextField untuk nomor kartu
- JPasswordField untuk PIN
- JButton untuk login dan register
- Gradient background (dark blue theme)

**Inner Classes** (5 inner classes):
- `$1` - Event handler untuk tombol Login
- `$2` - Event handler untuk tombol Buat Akun Baru
- `$3` - Event handler untuk checkbox Show/Hide PIN
- `$4` - FocusListener untuk field nomor kartu
- `$5` - FocusListener untuk field PIN

---

### 2️⃣ **RegisterView.java**
**Peran**: Halaman Registrasi User Baru

**Fungsi**:
- Form pendaftaran user baru
- Input data: Nama, NIK, Tanggal Lahir, Alamat, No HP
- Validasi data registrasi
- Membuat akun baru dengan saldo awal Rp 50.000
- Split panel: Info (kiri) + Form (kanan)
- Redirect ke LoginView setelah registrasi berhasil

**Komponen Utama**:
- JSplitPane (membagi layar jadi 2)
- Panel kiri: Info dan benefits
- Panel kanan: Form input (5 fields)
- JScrollPane untuk form yang panjang
- Tombol "Daftar Sekarang" dan "Kembali"

**Inner Classes** (4 inner classes):
- `$1` - Custom JPanel dengan gradient background
- `$2` - SwingWorker untuk async registration
- `$3` - Event handler untuk tombol Register
- `$4` - Event handler untuk tombol Back

---

### 3️⃣ **DashboardView.java** ⭐ (MAIN VIEW)
**Peran**: Halaman Utama Setelah Login (Hub/Central)

**Fungsi**:
- Tampilan utama setelah login berhasil
- Menampilkan saldo, info rekening, info kartu
- Menu navigasi ke semua fitur (sidebar)
- Single Page Application (SPA) style - tidak buka window baru
- Sidebar collapsible (bisa dikecilkan)
- Content area yang dinamis (ganti konten tanpa buka window baru)

**Komponen Utama**:
- Sidebar (menu navigasi)
- Header (judul halaman + info user)
- Content panel (area konten dinamis)
- Balance card (kartu saldo)
- Menu items: Dashboard, Cek Saldo, Tarik Uang, Setor Uang, Transfer, Riwayat, Info Kartu, Pengaturan, Keluar

**Inner Classes** (3 inner classes):
- `$1` - MouseAdapter untuk hover effect menu
- `$2` - MouseAdapter untuk toggle sidebar
- `$3` - ActionListener untuk menu items

**Metode Navigasi**:
- `showDashboardContent()` - Tampilkan dashboard
- `showBalanceContent()` - Tampilkan cek saldo
- `showWithdrawContent()` - Tampilkan form tarik uang
- `showDepositContent()` - Tampilkan form setor uang
- `showTransferContent()` - Tampilkan form transfer
- `showHistoryContent()` - Tampilkan riwayat transaksi
- `showCardInfoContent()` - Tampilkan info kartu
- `showSettingsContent()` - Tampilkan pengaturan

---

### 4️⃣ **WithdrawView.java**
**Peran**: Halaman Tarik Uang (Withdrawal)

**Fungsi**:
- Form untuk menarik uang dari rekening
- Input: Jumlah penarikan dan PIN
- Validasi: Kelipatan Rp 50.000, saldo cukup, PIN benar
- Mengurangi saldo rekening
- Mencatat transaksi ke database
- Tampil di dalam DashboardView (bukan window baru)

**Komponen Utama**:
- JTextField untuk jumlah
- JPasswordField untuk PIN
- Info panel (warning) dengan aturan penarikan
- Tombol "Tarik Uang" dan "Kembali"
- SwingWorker untuk async processing

**Inner Classes** (3 inner classes):
- `$1` - SwingWorker untuk proses withdrawal
- `$2` - Event handler untuk tombol Withdraw
- `$3` - Event handler untuk tombol Back

**Validasi**:
- Jumlah harus kelipatan 50.000
- Saldo harus mencukupi
- PIN harus benar

---

### 5️⃣ **DepositView.java**
**Peran**: Halaman Setor Uang (Deposit)

**Fungsi**:
- Form untuk menyetor uang ke rekening
- Input: Jumlah setoran
- Tidak perlu PIN (asumsi: setor di teller)
- Menambah saldo rekening
- Mencatat transaksi ke database
- Tampil di dalam DashboardView

**Komponen Utama**:
- JTextField untuk jumlah
- Info panel (success) dengan informasi
- Tombol "Setor Uang" dan "Kembali"
- SwingWorker untuk async processing

**Inner Classes** (3 inner classes):
- `$1` - SwingWorker untuk proses deposit
- `$2` - Event handler untuk tombol Deposit
- `$3` - Event handler untuk tombol Back

**Validasi**:
- Jumlah harus angka positif
- Tidak ada batas maksimal

---

### 6️⃣ **TransferView.java**
**Peran**: Halaman Transfer Uang

**Fungsi**:
- Form untuk transfer uang ke rekening lain
- Input: Nomor rekening tujuan dan jumlah transfer
- Validasi: Rekening tujuan ada, saldo cukup
- Mengurangi saldo pengirim, menambah saldo penerima
- Mencatat transaksi untuk kedua rekening
- Konfirmasi sebelum transfer
- Tampil di dalam DashboardView

**Komponen Utama**:
- JTextField untuk nomor rekening tujuan
- JTextField untuk jumlah
- Info panel dengan tips transfer
- Tombol "Transfer" dan "Kembali"
- Dialog konfirmasi
- SwingWorker untuk async processing

**Inner Classes** (6 inner classes):
- `$1` - SwingWorker untuk proses transfer
- `$2` - Event handler untuk tombol Transfer
- `$3` - Event handler untuk tombol Back
- `$4` - FocusListener untuk field nomor rekening
- `$5` - FocusListener untuk field jumlah
- `$6` - ActionListener untuk dialog konfirmasi

**Validasi**:
- Nomor rekening tujuan harus 10 digit
- Rekening tujuan harus ada di database
- Saldo harus mencukupi
- Tidak bisa transfer ke rekening sendiri

---

### 7️⃣ **TransactionHistoryView.java**
**Peran**: Halaman Riwayat Transaksi

**Fungsi**:
- Menampilkan semua transaksi user dalam bentuk tabel
- Kolom: Tanggal, Jenis, Jumlah, Deskripsi
- Data diambil dari database
- Scrollable table
- Tampil di dalam DashboardView

**Komponen Utama**:
- JTable untuk menampilkan data transaksi
- JScrollPane untuk table yang panjang
- DefaultTableModel untuk data
- Custom table styling (header, rows, colors)
- Tombol "Kembali ke Dashboard"

**Inner Classes** (3 inner classes):
- `$1` - DefaultTableModel dengan isCellEditable override
- `$2` - TableCellRenderer untuk styling
- `$3` - Event handler untuk tombol Back

**Jenis Transaksi**:
- DEPOSIT (Setor Uang)
- WITHDRAW (Tarik Uang)
- TRANSFER_IN (Transfer Masuk)
- TRANSFER_OUT (Transfer Keluar)

---

### 8️⃣ **CardInfoView.java**
**Peran**: Halaman Informasi Kartu ATM

**Fungsi**:
- Menampilkan detail kartu ATM user
- Visual kartu ATM (gradient card design)
- Info: Nomor kartu, No rekening, Status, Masa berlaku
- Format nomor kartu: XXXX XXXX XXXX XXXX
- Color-coded status (AKTIF=hijau, BLOKIR=merah, EXPIRED=kuning)
- Tampil di dalam DashboardView

**Komponen Utama**:
- Custom JPanel dengan gradient (visual kartu)
- Info panel dengan detail kartu
- Tombol "Kembali ke Dashboard"

**Inner Classes** (3 inner classes):
- `$1` - Custom JPanel dengan paintComponent untuk gradient
- `$2` - Event handler untuk tombol Back
- `$3` - Helper method untuk format nomor kartu

**Status Kartu**:
- AKTIF (hijau) - Kartu bisa digunakan
- BLOKIR (merah) - Kartu diblokir (3x salah PIN)
- EXPIRED (kuning) - Kartu sudah kadaluarsa

---

### 9️⃣ **SettingsView.java**
**Peran**: Halaman Pengaturan

**Fungsi**:
- Menu pengaturan akun dan keamanan
- Opsi: Ubah PIN, Informasi Akun
- Card-based menu dengan hover effect
- Tampil di dalam DashboardView

**Komponen Utama**:
- Menu card "Ubah PIN" → redirect ke ChangePinView
- Menu card "Informasi Akun" → tampilkan dialog info
- Hover effect pada menu cards
- Tombol "Kembali ke Dashboard"

**Inner Classes** (3 inner classes):
- `$1` - MouseAdapter untuk hover effect menu Ubah PIN
- `$2` - MouseAdapter untuk hover effect menu Info Akun
- `$3` - Event handler untuk tombol Back

**Navigasi**:
- Klik "Ubah PIN" → `parentDashboard.showChangePinView()`
- Klik "Informasi Akun" → Tampilkan JOptionPane dengan data user

---

### 🔟 **ChangePinView.java**
**Peran**: Halaman Ubah PIN

**Fungsi**:
- Form untuk mengganti PIN kartu ATM
- Input: PIN lama, PIN baru, Konfirmasi PIN baru
- Validasi: PIN lama benar, PIN baru 6 digit, konfirmasi cocok
- Update PIN di database
- Konfirmasi sebelum ubah PIN
- Tampil di dalam DashboardView

**Komponen Utama**:
- 3 JPasswordField (PIN lama, baru, konfirmasi)
- Warning panel dengan tips keamanan
- Tombol "Ubah PIN" dan "Batal"
- Dialog konfirmasi
- SwingWorker untuk async processing

**Inner Classes** (1 inner class):
- `$1` - SwingWorker untuk proses change PIN

**Validasi**:
- PIN lama harus benar
- PIN baru harus 6 digit angka
- Konfirmasi PIN harus sama dengan PIN baru
- PIN baru tidak boleh sama dengan PIN lama

---

### 1️⃣1️⃣ **CreateCardView.java**
**Peran**: Halaman Buat Kartu ATM (Setelah Registrasi)

**Fungsi**:
- Form untuk membuat kartu ATM pertama kali
- Input: Nomor rekening dan PIN baru (6 digit)
- Generate nomor kartu otomatis (16 digit)
- Set masa berlaku 5 tahun
- Status kartu: AKTIF
- Redirect ke LoginView setelah berhasil

**Komponen Utama**:
- JTextField untuk nomor rekening
- JPasswordField untuk PIN baru
- JPasswordField untuk konfirmasi PIN
- Info panel dengan instruksi
- Tombol "Buat Kartu" dan "Kembali"

**Inner Classes** (3 inner classes):
- `$1` - SwingWorker untuk proses create card
- `$2` - Event handler untuk tombol Create
- `$3` - Event handler untuk tombol Back

**Validasi**:
- Nomor rekening harus 10 digit
- Rekening harus ada di database
- Rekening belum punya kartu
- PIN harus 6 digit angka
- Konfirmasi PIN harus cocok

---

### 1️⃣2️⃣ **CreateCardByNIKView.java**
**Peran**: Halaman Buat Kartu ATM (Alternatif dengan NIK)

**Fungsi**:
- Form untuk membuat kartu ATM menggunakan NIK
- Input: NIK (16 digit) dan PIN baru (6 digit)
- Cari rekening berdasarkan NIK
- Generate nomor kartu otomatis
- Set masa berlaku 5 tahun
- Redirect ke LoginView setelah berhasil

**Komponen Utama**:
- JTextField untuk NIK
- JPasswordField untuk PIN baru
- JPasswordField untuk konfirmasi PIN
- Info panel dengan instruksi
- Tombol "Buat Kartu" dan "Kembali"

**Inner Classes** (5 inner classes):
- `$1` - SwingWorker untuk proses create card
- `$2` - Event handler untuk tombol Create
- `$3` - Event handler untuk tombol Back
- `$4` - FocusListener untuk field NIK
- `$5` - FocusListener untuk field PIN

**Validasi**:
- NIK harus 16 digit
- NIK harus terdaftar di database
- User belum punya kartu
- PIN harus 6 digit angka
- Konfirmasi PIN harus cocok

---

## 🔄 FLOW NAVIGASI APLIKASI

```
┌─────────────────────────────────────────────────────────────┐
│                      LoginView (Start)                       │
│                                                              │
│  [Login] ──────────────────────────────────────────────────┐│
│  [Buat Akun Baru] ────────────────────────────────────────┐││
└───────────────────────────────────────────────────────────┼┼┘
                                                             ││
                    ┌────────────────────────────────────────┘│
                    │                                         │
                    ▼                                         ▼
         ┌──────────────────────┐              ┌──────────────────────┐
         │   RegisterView       │              │   DashboardView      │
         │                      │              │   (Main Hub)         │
         │  [Daftar] ─────────┐ │              │                      │
         │  [Kembali] ────────┼─┼──────────────┤  Sidebar Menu:       │
         └────────────────────┼─┘              │  • Dashboard         │
                              │                │  • Cek Saldo ────────┼──> BalanceContent
                              │                │  • Tarik Uang ───────┼──> WithdrawView
                              ▼                │  • Setor Uang ───────┼──> DepositView
                   ┌──────────────────────┐    │  • Transfer ─────────┼──> TransferView
                   │  CreateCardView      │    │  • Riwayat ──────────┼──> TransactionHistoryView
                   │  atau                │    │  • Info Kartu ───────┼──> CardInfoView
                   │  CreateCardByNIKView │    │  • Pengaturan ───────┼──> SettingsView
                   │                      │    │  • Keluar ───────────┼──> LoginView
                   │  [Buat Kartu] ───────┼────┤                      │
                   └──────────────────────┘    └──────────────────────┘
                                                          │
                                                          │
                                    ┌─────────────────────┼─────────────────────┐
                                    │                     │                     │
                                    ▼                     ▼                     ▼
                          ┌──────────────────┐  ┌──────────────────┐  ┌──────────────────┐
                          │  ChangePinView   │  │  (Other Views)   │  │  (Other Views)   │
                          │                  │  │                  │  │                  │
                          │  [Ubah PIN]      │  │  [Action]        │  │  [Action]        │
                          │  [Batal] ────────┼──┤  [Kembali] ──────┼──┤  [Kembali] ──────┼──┐
                          └──────────────────┘  └──────────────────┘  └──────────────────┘  │
                                    │                     │                     │            │
                                    └─────────────────────┴─────────────────────┴────────────┘
                                                          │
                                                          ▼
                                                  Back to DashboardView
```

---

## 🎨 DESIGN PATTERN

### View Pattern yang Digunakan:

1. **JFrame** - Window utama (LoginView, RegisterView, DashboardView)
2. **JPanel** - Embedded views (WithdrawView, DepositView, dll)
3. **SPA (Single Page Application)** - Semua fitur tampil di DashboardView tanpa buka window baru

### Keuntungan SPA Pattern:
- ✅ Tidak banyak window terbuka
- ✅ Navigasi lebih smooth
- ✅ Memory efficient
- ✅ User experience lebih baik
- ✅ Konsisten dengan aplikasi modern

---

## 🧩 KOMPONEN UMUM DI SEMUA VIEW

### 1. **Layout Managers**:
- `BorderLayout` - Struktur utama (NORTH, CENTER, SOUTH, EAST, WEST)
- `BoxLayout` - Vertical/horizontal stacking
- `GridLayout` - Grid uniform
- `GridBagLayout` - Layout kompleks dengan constraints

### 2. **Input Components**:
- `JTextField` - Input text biasa
- `JPasswordField` - Input password (hidden)
- `JTextArea` - Input text multi-line
- `JButton` - Tombol aksi

### 3. **Display Components**:
- `JLabel` - Menampilkan text/icon
- `JTable` - Menampilkan data tabular
- `JScrollPane` - Scrollable container
- `JPanel` - Container untuk grouping

### 4. **Styling**:
- `UIConstants` - Warna, font, spacing
- `UIComponents` - Reusable styled components
- Custom `paintComponent()` - Gradient backgrounds
- `BorderFactory` - Borders dan padding

### 5. **Event Handling**:
- `ActionListener` - Button clicks
- `MouseAdapter` - Mouse events (hover, click)
- `FocusListener` - Focus events (focus gained/lost)
- `SwingWorker` - Async operations (database calls)

---

## 📊 STATISTIK

### File Count:
- **Java source files**: 12 files
- **Compiled class files**: 12 main + ~40 inner classes
- **Total lines of code**: ~3,500 lines (estimated)

### View Types:
- **JFrame views**: 3 (LoginView, RegisterView, DashboardView)
- **JPanel views**: 9 (embedded dalam DashboardView)

### Complexity:
- **Simple views**: DepositView, CardInfoView (1-2 inputs)
- **Medium views**: WithdrawView, ChangePinView (2-3 inputs)
- **Complex views**: RegisterView, TransferView, DashboardView (multiple components)

---

## 🔑 KEY POINTS

### 1. **Separation of Concerns**:
- View hanya handle UI
- Controller handle business logic
- Model handle data structure

### 2. **Reusability**:
- UIComponents untuk komponen reusable
- UIConstants untuk styling konsisten
- Pattern yang sama di semua view

### 3. **User Experience**:
- Dark blue theme (professional banking)
- Smooth transitions
- Loading states (SwingWorker)
- Validation feedback
- Confirmation dialogs

### 4. **Maintainability**:
- Kode terstruktur rapi
- Naming convention jelas
- Comments untuk dokumentasi
- Modular design

---

## 📝 NAMING CONVENTION

### File Naming:
- `[Feature]View.java` - Contoh: LoginView, DashboardView
- PascalCase untuk class names
- Descriptive names

### Variable Naming:
- `txt[Field]` - JTextField (contoh: txtNama, txtNik)
- `btn[Action]` - JButton (contoh: btnLogin, btnRegister)
- `lbl[Label]` - JLabel (contoh: lblTitle, lblSubtitle)
- camelCase untuk variable names

### Method Naming:
- `show[Feature]Content()` - Tampilkan konten
- `handle[Action]()` - Handle event
- `create[Component]()` - Buat komponen
- camelCase untuk method names

---

## ✅ BEST PRACTICES YANG DITERAPKAN

1. ✅ **MVC Pattern** - Separation of concerns
2. ✅ **SPA Pattern** - Single page application
3. ✅ **Async Operations** - SwingWorker untuk database calls
4. ✅ **Validation** - Input validation sebelum submit
5. ✅ **Error Handling** - Try-catch dan user feedback
6. ✅ **Responsive Design** - Layout yang adaptif
7. ✅ **Consistent Styling** - UIConstants dan UIComponents
8. ✅ **User Feedback** - Loading states, success/error messages
9. ✅ **Security** - Password fields, PIN validation
10. ✅ **Accessibility** - Clear labels, hints, error messages

---

**Dokumentasi ini menjelaskan struktur dan peran setiap file view tanpa mengubah kode apapun.**

**Version**: 4.2  
**Last Updated**: April 16, 2026  
**Application**: BANK LP3I PEKANBARU
