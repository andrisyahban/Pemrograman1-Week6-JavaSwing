package tugasif;

import javax.swing.*;
import java.awt.*;

public class fromregis extends JFrame {
    public fromregis() {
        super("Form Registrasi");

        // 1. Panel utama dengan TitledBorder (Slide 8)
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createTitledBorder("Data Registrasi"));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);
        g.fill = GridBagConstraints.HORIZONTAL;

        // Komponen Input
        JTextField nama = new JTextField(15), email = new JTextField(15);
        JPasswordField sandi = new JPasswordField(15);
        
        JRadioButton lk = new JRadioButton("Laki-laki", true), pr = new JRadioButton("Perempuan"); // Default terpilih (Slide 13)
        ButtonGroup bg = new ButtonGroup(); bg.add(lk); bg.add(pr);
        
        JComboBox<String> prodi = new JComboBox<>(new String[]{"Informatika", "Sistem Informasi", "DKV"});
        JCheckBox h1 = new JCheckBox("Musik"), h2 = new JCheckBox("Belajar"), h3 = new JCheckBox("Coding");
        
        // Pengaturan background checkbox & radio agar menyatu dengan panel putih
        lk.setBackground(Color.WHITE); pr.setBackground(Color.WHITE);
        h1.setBackground(Color.WHITE); h2.setBackground(Color.WHITE); h3.setBackground(Color.WHITE);

        // Tombol dengan Tooltip & Mnemonic Alt+D (Slide 13)
        JButton btn = new JButton("Daftar");
        btn.setToolTipText("Klik atau tekan Alt+D untuk mendaftar");
        btn.setMnemonic('D');

        // Susun ke GridBagLayout
        g.gridx = 0; g.gridy = 0; p.add(new JLabel("Nama:"), g);
        g.gridx = 1; p.add(nama, g);

        g.gridx = 0; g.gridy = 1; p.add(new JLabel("Email:"), g);
        g.gridx = 1; p.add(email, g);

        g.gridx = 0; g.gridy = 2; p.add(new JLabel("Sandi:"), g);
        g.gridx = 1; p.add(sandi, g);

        g.gridx = 0; g.gridy = 3; p.add(new JLabel("Gender:"), g);
        JPanel pGender = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pGender.setBackground(Color.WHITE);
        pGender.add(lk); pGender.add(pr);
        g.gridx = 1; p.add(pGender, g);

        g.gridx = 0; g.gridy = 4; p.add(new JLabel("Prodi:"), g);
        g.gridx = 1; p.add(prodi, g);

        g.gridx = 0; g.gridy = 5; p.add(new JLabel("Hobi:"), g);
        JPanel pHobi = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pHobi.setBackground(Color.WHITE);
        pHobi.add(h1); pHobi.add(h2); pHobi.add(h3);
        g.gridx = 1; p.add(pHobi, g);

        g.gridx = 1; g.gridy = 6; p.add(btn, g);

        // 2. Menu Bar (Slide 19 & Tugas 2 No. 5)
        JMenuBar bar = new JMenuBar();
        JMenu mFile = new JMenu("File"), mBantuan = new JMenu("Bantuan");
        JMenuItem miReset = new JMenuItem("Reset"), miKeluar = new JMenuItem("Keluar"), miTentang = new JMenuItem("Tentang");

        miReset.addActionListener(e -> {
            nama.setText(""); email.setText(""); sandi.setText("");
            lk.setSelected(true); prodi.setSelectedIndex(0);
            h1.setSelected(false); h2.setSelected(false); h3.setSelected(false);
        });
        miKeluar.addActionListener(e -> System.exit(0));
        miTentang.addActionListener(e -> JOptionPane.showMessageDialog(this, "Aplikasi Form Registrasi v1.0", "Tentang", JOptionPane.INFORMATION_MESSAGE));

        mFile.add(miReset); mFile.addSeparator(); mFile.add(miKeluar);
        mBantuan.add(miTentang);
        bar.add(mFile); bar.add(mBantuan);
        setJMenuBar(bar);

        // 3. Aksi Tombol Daftar + Validasi + Tipe JOptionPane (Slide 17)
        btn.addActionListener(e -> {
            // Cek jika ada kolom teks yang kosong (Slide 12: getPassword().length)
            if (nama.getText().trim().isEmpty() || email.getText().trim().isEmpty() || sandi.getPassword().length == 0) {
                JOptionPane.showMessageDialog(this, "Nama, Email, dan Sandi wajib diisi!", "Peringatan", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Konfirmasi sebelum menampilkan ringkasan (Slide 17: showConfirmDialog)
            int yakin = JOptionPane.showConfirmDialog(this, "Apakah data sudah benar?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (yakin == JOptionPane.YES_OPTION) {
                String gender = lk.isSelected() ? "Laki-laki" : "Perempuan";
                String hobi = "";
                if (h1.isSelected()) hobi += "Musik ";
                if (h2.isSelected()) hobi += "Olahraga ";
                if (h3.isSelected()) hobi += "Game ";

                String hasil = "Nama: " + nama.getText() +
                               "\nEmail: " + email.getText() +
                               "\nSandi: " + "*".repeat(sandi.getPassword().length) + // Sensor sandi agar aman
                               "\nGender: " + gender +
                               "\nProdi: " + prodi.getSelectedItem() +
                               "\nHobi: " + (hobi.isEmpty() ? "-" : hobi.trim());

                JOptionPane.showMessageDialog(this, hasil, "Ringkasan Registrasi", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        add(p);
        setResizable(false); // Ukuran jendela terkunci rapi (Slide 8)
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // 4. Mengaktifkan Look and Feel Nimbus (Slide 23)
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> new fromregis().setVisible(true));
    }
}