package tugasif;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DataMahasiswa extends JFrame {
    public DataMahasiswa() {
        super("Data Mahasiswa");
        setLayout(new BorderLayout(8, 8));

        // 1. Panel Input Atas dengan TitledBorder (Slide 8 & GridBagLayout Slide 11)
        JPanel pForm = new JPanel(new GridBagLayout());
        pForm.setBackground(Color.WHITE);
        pForm.setBorder(BorderFactory.createTitledBorder("Input / Edit Data Mahasiswa"));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(4, 4, 4, 4);
        g.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtNim = new JTextField(10), txtNama = new JTextField(15);
        JComboBox<String> cbProdi = new JComboBox<>(new String[]{"Informatika", "Sistem Informasi", "DKV"});
        // JSpinner untuk IPK 0.00 - 4.00 naik 0.05 (Slide 14)
        JSpinner spIpk = new JSpinner(new SpinnerNumberModel(3.50, 0.00, 4.00, 0.05));

        g.gridx = 0; g.gridy = 0; pForm.add(new JLabel("NIM:"), g);
        g.gridx = 1; pForm.add(txtNim, g);
        g.gridx = 2; pForm.add(new JLabel("Nama:"), g);
        g.gridx = 3; pForm.add(txtNama, g);

        g.gridx = 0; g.gridy = 1; pForm.add(new JLabel("Prodi:"), g);
        g.gridx = 1; pForm.add(cbProdi, g);
        g.gridx = 2; pForm.add(new JLabel("IPK:"), g);
        g.gridx = 3; pForm.add(spIpk, g);

        add(pForm, BorderLayout.NORTH);

        // 2. JTable & DefaultTableModel dengan kolom IPK (Slide 20 & Soal)
        DefaultTableModel model = new DefaultTableModel(new Object[]{"NIM", "Nama", "Prodi", "IPK"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; } // Agar tabel tidak diedit langsung tanpa tombol Ubah
        };
        JTable tabel = new JTable(model);
        add(new JScrollPane(tabel), BorderLayout.CENTER); // Wajib dibungkus JScrollPane agar header tampil (Slide 20)

        // Data contoh awal
        model.addRow(new Object[]{"2401001", "Andri", "Informatika", 3.85});
        model.addRow(new Object[]{"2401002", "Sinta", "Sistem Informasi", 3.70});

        // 3. ListSelectionListener: Klik baris tabel -> otomatis isi ke form atas (Slide 15 & 20)
        tabel.getSelectionModel().addListSelectionListener(e -> {
            int baris = tabel.getSelectedRow();
            if (baris >= 0) {
                txtNim.setText(model.getValueAt(baris, 0).toString());
                txtNama.setText(model.getValueAt(baris, 1).toString());
                cbProdi.setSelectedItem(model.getValueAt(baris, 2).toString());
                spIpk.setValue(Double.parseDouble(model.getValueAt(baris, 3).toString()));
            }
        });

        // 4. Panel Tombol Bawah (FlowLayout - Slide 10)
        JPanel pTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton btnTambah = new JButton("Tambah");
        JButton btnUbah = new JButton("Ubah");
        JButton btnHapus = new JButton("Hapus");
        JButton btnReset = new JButton("Reset");

        btnTambah.setMnemonic('T'); btnUbah.setMnemonic('U'); btnHapus.setMnemonic('H'); // Shortcut Alt+T/U/H (Slide 13)
        pTombol.add(btnTambah); pTombol.add(btnUbah); pTombol.add(btnHapus); pTombol.add(btnReset);
        add(pTombol, BorderLayout.SOUTH);

        // Helper untuk membersihkan form
        Runnable resetForm = () -> {
            txtNim.setText(""); txtNama.setText("");
            cbProdi.setSelectedIndex(0); spIpk.setValue(3.50);
            tabel.clearSelection();
        };

        // Aksi Tambah Data
        btnTambah.addActionListener(e -> {
            if (txtNim.getText().trim().isEmpty() || txtNama.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "NIM dan Nama wajib diisi!", "Peringatan", JOptionPane.ERROR_MESSAGE);
                return;
            }
            double ipk = Math.round((Double) spIpk.getValue() * 100.0) / 100.0;
            model.addRow(new Object[]{txtNim.getText().trim(), txtNama.getText().trim(), cbProdi.getSelectedItem(), ipk});
            resetForm.run();
        });

        // Aksi Ubah Data Terpilih (Inti Soal)
        btnUbah.addActionListener(e -> {
            int baris = tabel.getSelectedRow();
            if (baris < 0) { // getSelectedRow() bernilai -1 jika belum dipilih (Slide 20)
                JOptionPane.showMessageDialog(this, "Pilih dulu baris data pada tabel yang ingin diubah!", "Info", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (txtNim.getText().trim().isEmpty() || txtNama.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "NIM dan Nama tidak boleh kosong!", "Peringatan", JOptionPane.ERROR_MESSAGE);
                return;
            }
            double ipk = Math.round((Double) spIpk.getValue() * 100.0) / 100.0;
            model.setValueAt(txtNim.getText().trim(), baris, 0);
            model.setValueAt(txtNama.getText().trim(), baris, 1);
            model.setValueAt(cbProdi.getSelectedItem(), baris, 2);
            model.setValueAt(ipk, baris, 3);
            JOptionPane.showMessageDialog(this, "Data berhasil diubah!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            resetForm.run();
        });

        // Aksi Hapus Data Terpilih dengan Konfirmasi (Slide 17 & Slide 20)
        btnHapus.addActionListener(e -> {
            int baris = tabel.getSelectedRow();
            if (baris < 0) {
                JOptionPane.showMessageDialog(this, "Pilih baris yang akan dihapus!", "Info", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (JOptionPane.showConfirmDialog(this, "Yakin hapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                model.removeRow(baris);
                resetForm.run();
            }
        });

        btnReset.addActionListener(e -> resetForm.run());

        // 5. Menu Bar Sederhana (Slide 19 & Tugas 2 No. 5)
        JMenuBar bar = new JMenuBar();
        JMenu mFile = new JMenu("File"), mBantuan = new JMenu("Bantuan");
        JMenuItem miKeluar = new JMenuItem("Keluar"), miTentang = new JMenuItem("Tentang");
        miKeluar.addActionListener(e -> System.exit(0));
        miTentang.addActionListener(e -> JOptionPane.showMessageDialog(this, "Mini Project Data Mahasiswa (CRUD JTable)", "Tentang", JOptionPane.INFORMATION_MESSAGE));
        mFile.add(miKeluar); mBantuan.add(miTentang);
        bar.add(mFile); bar.add(mBantuan);
        setJMenuBar(bar);

        setSize(580, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); // Slide 23
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> new DataMahasiswa().setVisible(true));
    }
}