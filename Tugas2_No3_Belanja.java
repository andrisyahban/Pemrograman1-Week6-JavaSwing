package tugasif;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tugas2_No3_Belanja extends JFrame {
    public Tugas2_No3_Belanja() {
        super("Tugas 2 No 3 - Daftar Belanja");
        setLayout(new BorderLayout(8, 8));

        // Bagian Atas: JTextField + Tombol Tambah (FlowLayout - Slide 10)
        JPanel pAtas = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        pAtas.setBorder(BorderFactory.createTitledBorder("Tambah Item Belanja")); // Slide 8
        JTextField txtItem = new JTextField(18);
        JButton btnTambah = new JButton("Tambah");
        pAtas.add(txtItem); pAtas.add(btnTambah);
        add(pAtas, BorderLayout.NORTH);

        // Bagian Tengah: DefaultListModel & JList dalam JScrollPane (Slide 14 & 21)
        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement("Beras 5kg");
        model.addElement("Minyak Goreng");
        JList<String> listBelanja = new JList<>(model);
        listBelanja.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(listBelanja), BorderLayout.CENTER);

        // Bagian Bawah: Tombol Hapus Terpilih
        JButton btnHapus = new JButton("Hapus Terpilih");
        add(btnHapus, BorderLayout.SOUTH);

        // Aksi Tambah Item
        Runnable aksiTambah = () -> {
            String item = txtItem.getText().trim();
            if (item.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Masukkan nama barang terlebih dahulu!", "Info", JOptionPane.WARNING_MESSAGE);
            } else {
                model.addElement(item); // Slide 14
                txtItem.setText("");
                txtItem.requestFocus();
            }
        };

        btnTambah.addActionListener(e -> aksiTambah.run());
        // Bonus Slide 28: KeyAdapter tekan Enter langsung tambah
        txtItem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) aksiTambah.run();
            }
        });

        // Aksi Hapus Terpilih (Slide 14 & 17)
        btnHapus.addActionListener(e -> {
            int index = listBelanja.getSelectedIndex();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Pilih item pada daftar yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            } else {
                String terpilih = listBelanja.getSelectedValue(); // Slide 14
                if (JOptionPane.showConfirmDialog(this, "Hapus '" + terpilih + "'?", "Konfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    model.remove(index);
                }
            }
        });

        // Ketentuan No. 5: Menu File > Keluar dan Bantuan > Tentang (Slide 19 & 25)
        JMenuBar bar = new JMenuBar();
        JMenu mFile = new JMenu("File"), mBantuan = new JMenu("Bantuan");
        JMenuItem miKeluar = new JMenuItem("Keluar"), miTentang = new JMenuItem("Tentang");
        miKeluar.addActionListener(e -> System.exit(0));
        miTentang.addActionListener(e -> JOptionPane.showMessageDialog(this, "Tugas 2 No 3: Aplikasi Daftar Belanja (JList)\nOleh: Muhammad Andri Syahban", "Tentang", JOptionPane.INFORMATION_MESSAGE));
        mFile.add(miKeluar); mBantuan.add(miTentang);
        bar.add(mFile); bar.add(mBantuan);
        setJMenuBar(bar);

        setSize(380, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); } catch (Exception ignored) {} // Slide 23
        SwingUtilities.invokeLater(() -> new Tugas2_No3_Belanja().setVisible(true));
    }
}