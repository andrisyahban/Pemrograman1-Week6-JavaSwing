package tugasif;

import javax.swing.*;
import java.awt.*;

public class Tugas2_No2_Login extends JFrame {
    public Tugas2_No2_Login() {
        super("Tugas 2 No 2 - Form Login");

        // Panel dengan TitledBorder & GridBagLayout (Slide 8 & 11)
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createTitledBorder("Silakan Login"));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);
        g.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtUser = new JTextField(15);
        JPasswordField txtPass = new JPasswordField(15);
        JButton btnMasuk = new JButton("Masuk");
        btnMasuk.setMnemonic('M'); // Alt+M (Slide 13)
        btnMasuk.setToolTipText("Klik atau tekan Enter untuk masuk");

        g.gridx = 0; g.gridy = 0; p.add(new JLabel("Username:"), g);
        g.gridx = 1; p.add(txtUser, g);

        g.gridx = 0; g.gridy = 1; p.add(new JLabel("Password:"), g);
        g.gridx = 1; p.add(txtPass, g);

        g.gridx = 1; g.gridy = 2; p.add(btnMasuk, g);

        // Aksi tombol Masuk + pesan bila kosong (Slide 12 & 17)
        btnMasuk.addActionListener(e -> {
            String user = txtUser.getText().trim();
            char[] pass = txtPass.getPassword(); // Slide 12: pakai char[]

            if (user.isEmpty() || pass.length == 0) {
                JOptionPane.showMessageDialog(this, "Username dan Password tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Selamat datang, " + user + "!", "Login Berhasil", JOptionPane.INFORMATION_MESSAGE);
                txtPass.setText(""); // Bersihkan memori field password
            }
        });

        // Ketentuan No. 5: Menu File > Keluar dan Bantuan > Tentang (Slide 19 & 25)
        JMenuBar bar = new JMenuBar();
        JMenu mFile = new JMenu("File"), mBantuan = new JMenu("Bantuan");
        JMenuItem miKeluar = new JMenuItem("Keluar"), miTentang = new JMenuItem("Tentang");
        miKeluar.addActionListener(e -> System.exit(0));
        miTentang.addActionListener(e -> JOptionPane.showMessageDialog(this, "Tugas 2 No 2: Aplikasi Form Login GridBagLayout\nOleh: Muhammad Andri Syahban", "Tentang", JOptionPane.INFORMATION_MESSAGE));
        mFile.add(miKeluar); mBantuan.add(miTentang);
        setJMenuBar(bar); bar.add(mFile); bar.add(mBantuan);

        add(p);
        getRootPane().setDefaultButton(btnMasuk); // Bisa submit pakai tombol Enter
        setResizable(false); // Slide 8
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); } catch (Exception ignored) {} // Slide 23
        SwingUtilities.invokeLater(() -> new Tugas2_No2_Login().setVisible(true));
    }
}