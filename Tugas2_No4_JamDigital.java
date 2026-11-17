package tugasif;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Tugas2_No4_JamDigital extends JFrame {
    public Tugas2_No4_JamDigital() {
        super("Tugas 2 No 4 - Jam Digital");

        JPanel p = new JPanel(new BorderLayout(5, 5));
        p.setBackground(new Color(230, 244, 252)); // Warna latar dari Slide 23
        p.setBorder(BorderFactory.createTitledBorder("Waktu Real-Time (EDT)")); // Slide 8

        DateTimeFormatter fmtJam = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter fmtTgl = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy");

        JLabel lblJam = new JLabel(LocalTime.now().format(fmtJam), SwingConstants.CENTER);
        lblJam.setFont(new Font("Arial", Font.BOLD, 36)); // Font & Warna dari Slide 23
        lblJam.setForeground(new Color(0, 70, 140));

        JLabel lblTgl = new JLabel(LocalDate.now().format(fmtTgl), SwingConstants.CENTER);
        lblTgl.setFont(new Font("Arial", Font.PLAIN, 14));

        // javax.swing.Timer berjalan setiap 1000 ms di EDT (Slide 22)
        Timer t = new Timer(1000, e -> {
            lblJam.setText(LocalTime.now().format(fmtJam));
            lblTgl.setText(LocalDate.now().format(fmtTgl));
        });
        t.start(); // Slide 22

        // Kontrol Start/Stop Timer (Slide 22)
        JButton btnToggle = new JButton("Jeda Jam");
        btnToggle.addActionListener(e -> {
            if (t.isRunning()) {
                t.stop();
                btnToggle.setText("Lanjutkan Jam");
            } else {
                t.start();
                btnToggle.setText("Jeda Jam");
            }
        });

        p.add(lblTgl, BorderLayout.NORTH);
        p.add(lblJam, BorderLayout.CENTER);
        p.add(btnToggle, BorderLayout.SOUTH);
        add(p);

        // Ketentuan No. 5: Menu File > Keluar dan Bantuan > Tentang (Slide 19 & 25)
        JMenuBar bar = new JMenuBar();
        JMenu mFile = new JMenu("File"), mBantuan = new JMenu("Bantuan");
        JMenuItem miKeluar = new JMenuItem("Keluar"), miTentang = new JMenuItem("Tentang");
        miKeluar.addActionListener(e -> System.exit(0));
        miTentang.addActionListener(e -> JOptionPane.showMessageDialog(this, "Tugas 2 No 4: Jam Digital dengan javax.swing.Timer\nOleh: Muhammad Andri Syahban", "Tentang", JOptionPane.INFORMATION_MESSAGE));
        mFile.add(miKeluar); mBantuan.add(miTentang);
        bar.add(mFile); bar.add(mBantuan);
        setJMenuBar(bar);

        setSize(340, 210);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); } catch (Exception ignored) {} // Slide 23
        SwingUtilities.invokeLater(() -> new Tugas2_No4_JamDigital().setVisible(true));
    }
}