package tugasif;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.Files;

public class EditorTeks extends JFrame {
    public EditorTeks() {
        super("Editor Teks");

        // 1. Area Teks dalam JScrollPane (Slide 12)
        JTextArea area = new JTextArea(18, 45);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setFont(new Font("Consolas", Font.PLAIN, 14)); // Font custom (Slide 23)
        add(new JScrollPane(area), BorderLayout.CENTER);

        // Label status di bawah (BorderLayout.SOUTH - Slide 10)
        JLabel status = new JLabel(" Siap | Jumlah Karakter: 0");
        add(status, BorderLayout.SOUTH);

        // 2. Fungsi Buka & Simpan dengan JFileChooser (Slide 18)
        JFileChooser fc = new JFileChooser();
        Runnable aksiBaru = () -> {
            if (!area.getText().isEmpty() && JOptionPane.showConfirmDialog(this, "Hapus teks saat ini?", "Baru", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;
            area.setText("");
            setTitle("Editor Teks - Dokumen Baru");
        };

        Runnable aksiBuka = () -> {
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    area.setText(Files.readString(fc.getSelectedFile().toPath()));
                    setTitle("Editor Teks - " + fc.getSelectedFile().getName());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Gagal membuka file!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        Runnable aksiSimpan = () -> {
            if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    Files.writeString(fc.getSelectedFile().toPath(), area.getText());
                    setTitle("Editor Teks - " + fc.getSelectedFile().getName());
                    JOptionPane.showMessageDialog(this, "File berhasil disimpan!", "Info", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Gagal menyimpan file!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        // 3. Menu Bar + Shortcut Keyboard Accelerator (Slide 19)
        JMenuBar bar = new JMenuBar();
        JMenu mFile = new JMenu("File"), mEdit = new JMenu("Edit"), mTampilan = new JMenu("Tampilan");

        JMenuItem miBaru = new JMenuItem("Baru"), miBuka = new JMenuItem("Buka"), miSimpan = new JMenuItem("Simpan"), miKeluar = new JMenuItem("Keluar");
        miBaru.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        miBuka.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        miSimpan.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));

        miBaru.addActionListener(e -> aksiBaru.run());
        miBuka.addActionListener(e -> aksiBuka.run());
        miSimpan.addActionListener(e -> aksiSimpan.run());
        miKeluar.addActionListener(e -> System.exit(0));
        mFile.add(miBaru); mFile.add(miBuka); mFile.add(miSimpan); mFile.addSeparator(); mFile.add(miKeluar);

        // Menu Edit (Potong, Salin, Tempel - method bawaan JTextArea)
        JMenuItem miPotong = new JMenuItem("Potong"), miSalin = new JMenuItem("Salin"), miTempel = new JMenuItem("Tempel");
        miPotong.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        miSalin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        miTempel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));

        miPotong.addActionListener(e -> area.cut());
        miSalin.addActionListener(e -> area.copy());
        miTempel.addActionListener(e -> area.paste());
        mEdit.add(miPotong); mEdit.add(miSalin); mEdit.add(miTempel);

        // Bonus Slide 18: JColorChooser untuk ganti warna latar editor
        JMenuItem miWarna = new JMenuItem("Warna Latar...");
        miWarna.addActionListener(e -> {
            Color c = JColorChooser.showDialog(this, "Pilih Warna Latar", area.getBackground());
            if (c != null) area.setBackground(c);
        });
        mTampilan.add(miWarna);

        bar.add(mFile); bar.add(mEdit); bar.add(mTampilan);
        setJMenuBar(bar);

        // 4. Popup Menu Klik Kanan di Area Teks (Slide 19)
        JPopupMenu pop = new JPopupMenu();
        JMenuItem pPotong = new JMenuItem("Potong"), pSalin = new JMenuItem("Salin"), pTempel = new JMenuItem("Tempel");
        pPotong.addActionListener(e -> area.cut());
        pSalin.addActionListener(e -> area.copy());
        pTempel.addActionListener(e -> area.paste());
        pop.add(pPotong); pop.add(pSalin); pop.add(pTempel);
        area.setComponentPopupMenu(pop);

        // 5. ToolBar di Bagian Atas (Slide 19)
        JToolBar tool = new JToolBar();
        JButton bBaru = new JButton("Baru"), bBuka = new JButton("Buka"), bSimpan = new JButton("Simpan");
        JButton bPotong = new JButton("Potong"), bSalin = new JButton("Salin"), bTempel = new JButton("Tempel");

        bBaru.addActionListener(e -> aksiBaru.run());
        bBuka.addActionListener(e -> aksiBuka.run());
        bSimpan.addActionListener(e -> aksiSimpan.run());
        bPotong.addActionListener(e -> area.cut());
        bSalin.addActionListener(e -> area.copy());
        bTempel.addActionListener(e -> area.paste());

        tool.add(bBaru); tool.add(bBuka); tool.add(bSimpan);
        tool.addSeparator();
        tool.add(bPotong); tool.add(bSalin); tool.add(bTempel);
        add(tool, BorderLayout.NORTH);

        // 6. KeyAdapter untuk menghitung jumlah karakter real-time (Slide 28)
        area.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                status.setText(" Siap | Jumlah Karakter: " + area.getText().length());
            }
        });

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); // Slide 23
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> new EditorTeks().setVisible(true));
    }
}