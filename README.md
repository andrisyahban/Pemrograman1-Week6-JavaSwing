# Pemrograman 1 - Minggu 6 (Java Swing)

Repositori ini berisi hasil pengerjaan **Tugas 1** dan **Tugas 2** mata kuliah **Pemrograman 1** – Departemen Informatika, Universitas Internasional Semen Indonesia (UISI).

**Identitas Mahasiswa:**
* **Nama:** Muhammad Andri Syahban
* **Mata Kuliah:** Pemrograman 1
* **Materi:** Java Swing (Komponen GUI, Layout Manager, Event Handling, Dialog, Menu, Tabel, & Timer)

---

## Daftar Program

### Tugas 1
1. **`fromregis.java` (Form Registrasi)**
   * Menggunakan `GridBagLayout` untuk input Nama, Email, Sandi (`JPasswordField`), Gender (`JRadioButton`), Prodi (`JComboBox`), dan Hobi (`JCheckBox`).
   * Dilengkapi validasi input kosong, konfirmasi, dan ringkasan data menggunakan `JOptionPane`.
2. **`EditorTeks.java` (Editor Teks)**
   * Menggunakan `JTextArea` di dalam `JScrollPane` dengan penghitung karakter *real-time* (`KeyAdapter`).
   * Dilengkapi `JMenuBar` (File: Baru, Buka, Simpan, Keluar menggunakan `JFileChooser`; Edit: Potong, Salin, Tempel; Tampilan: `JColorChooser`), `JPopupMenu`, dan `JToolBar`.
3. **`DataMahasiswa.java` (CRUD Data Mahasiswa)**
   * Menggunakan `JTable` dan `DefaultTableModel` dengan kolom NIM, Nama, Prodi, dan IPK (`JSpinner`).
   * Mendukung fitur Tambah, Ubah data terpilih (`ListSelectionListener`), dan Hapus data dengan konfirmasi.

### Tugas 2
1. **`Tugas2_No1_Layout.java`** – Perubahan tata letak dinamis dari `FlowLayout` ke `GridLayout 1x3`.
2. **`Tugas2_No2_Login.java`** – Form login menggunakan `GridBagLayout` dengan validasi kolom kosong.
3. **`Tugas2_No3_Belanja.java`** – Aplikasi daftar belanja interaktif menggunakan `JList` dan `DefaultListModel`.
4. **`Tugas2_No4_JamDigital.java`** – Jam digital *real-time* menggunakan `javax.swing.Timer` di *Event Dispatch Thread* (EDT).
5. **Menu Bar Standar** – Seluruh program dilengkapi menu **File > Keluar** dan **Bantuan > Tentang**, serta menerapkan tema `NimbusLookAndFeel`.

---

## Cara Menjalankan
1. Pastikan **JDK (Java Development Kit)** sudah terinstal di perangkat.
2. Buka folder proyek di **Visual Studio Code** (dengan *Extension Pack for Java*) atau IDE Java lainnya.
3. Jalankan method `main` pada masing-masing file `.java` di dalam folder `src/tugasif/`.

---

## Catatan
Sebagian struktur kode, optimasi fitur GUI, dan penyusunan dokumentasi pada repositori ini dikembangkan dengan bantuan asisten AI (Gemini) sebagai alat bantu belajar, yang kemudian disesuaikan dengan materi perkuliahan Minggu 6 (Java Swing).
