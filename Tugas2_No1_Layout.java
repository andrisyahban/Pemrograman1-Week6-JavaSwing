package tugasif;

import javax.swing.*;
import java.awt.*;

public class Tugas2_No1_Layout extends JFrame {
    public Tugas2_No1_Layout() {
        super("Tugas 2 No 1 - Ganti Layout");

        // Awalnya menggunakan FlowLayout (Slide 9 & 10)
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelTombol.setBorder(BorderFactory.createTitledBorder("Area Tombol")); // Slide 8

        JButton b1 = new JButton("Tombol 1");
        JButton b2 = new JButton("Tombol 2");
        JButton b3 = new JButton("Tombol 3");
        panelTombol.add(b1); panelTombol.add(b2); panelTombol.add(b3);

        // Tombol kontrol untuk mengubah ke GridLayout 1x3 (Slide 27)
        JButton btnUbah = new JButton("Ubah ke GridLayout 1x3");
        btnUbah.setToolTipText("Klik untuk mengubah tata letak tombol"); // Slide 13
        btnUbah.addActionListener(e -> {
            panelTombol.setLayout(new GridLayout(1, 3, 5, 5)); // GridLayout 1 baris x 3 kolom
            panelTombol.revalidate(); // Memperbarui susunan layout secara dinamis
            panelTombol.repaint();
            btnUbah.setText("Sudah GridLayout 1x3");
            btnUbah.setEnabled(false); // Slide 13
        });

        add(panelTombol, BorderLayout.CENTER);
        add(btnUbah, BorderLayout.SOUTH);

        // Ketentuan No. 5: Menu File > Keluar dan Bantuan > Tentang (Slide 19 & 25)
        JMenuBar bar = new JMenuBar();
        JMenu mFile = new JMenu("File"), mBantuan = new JMenu("Bantuan");
        JMenuItem miKeluar = new JMenuItem("Keluar"), miTentang = new JMenuItem("Tentang");
        miKeluar.addActionListener(e -> System.exit(0));
        miTentang.addActionListener(e -> JOptionPane.showMessageDialog(this, "Tugas 2 No 1: Demo FlowLayout ke GridLayout 1x3\nOleh: Muhammad Andri Syahban", "Tentang", JOptionPane.INFORMATION_MESSAGE));
        mFile.add(miKeluar); mBantuan.add(miTentang);
        bar.add(mFile); bar.add(mBantuan);
        setJMenuBar(bar);

        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); } catch (Exception ignored) {} // Slide 23
        SwingUtilities.invokeLater(() -> new Tugas2_No1_Layout().setVisible(true));
    }
}