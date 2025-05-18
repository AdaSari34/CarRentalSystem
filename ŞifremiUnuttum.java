package rental;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ŞifremiUnuttum extends JFrame {
    private JTextField txtKullanıcıAdı;
    private JTextField txtSoyad;
    private JTextField txtTC;
    private JTextField txtYeniŞifre;

    public ŞifremiUnuttum() {
        setTitle("Şifre Değiştir");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450,350);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        JLabel lblKullanıcıAdı = new JLabel("Kullanıcı Adı:");
        txtKullanıcıAdı = new JTextField();
        add(lblKullanıcıAdı);
        add(txtKullanıcıAdı);

        JLabel lblSoyad = new JLabel("Soyad:");
        txtSoyad = new JTextField();
        add(lblSoyad);
        add(txtSoyad);

        JLabel lblTC = new JLabel("TC:");
        txtTC = new JTextField();
        add(lblTC);
        add(txtTC);
        
        JLabel lblYeniŞifre = new JLabel("Yeni Şifre:");
        txtYeniŞifre = new JTextField();
        add(lblYeniŞifre);
        add(txtYeniŞifre);

        
    

        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bilgileriDoğrula(txtKullanıcıAdı.getText(), txtSoyad.getText(), txtTC.getText())) {
                    şifreDeğiştir();
                } else {
                    JOptionPane.showMessageDialog(ŞifremiUnuttum.this, "Girdiğiniz bilgiler yanlış.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(btnOk);
        
        setVisible(true);

    }

    private boolean bilgileriDoğrula(String kullanıcıAdı, String soyad, String tc) {
        try (BufferedReader reader = new BufferedReader(new FileReader(KullanıcılarıDosyayaYazdır.DOSYA_YOLU))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 10 && parts[1].equals(kullanıcıAdı) && parts[3].equals(soyad) && parts[7].equals(tc)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void şifreDeğiştir() {
        String yeniŞifre = txtYeniŞifre.getText();
        String kullanıcıAdı = txtKullanıcıAdı.getText();
        String soyad = txtSoyad.getText();
        String tc = txtTC.getText();

        try {
            List<String> lines = Files.readAllLines(Paths.get(KullanıcılarıDosyayaYazdır.DOSYA_YOLU));
            BufferedWriter writer = new BufferedWriter(new FileWriter(KullanıcılarıDosyayaYazdır.DOSYA_YOLU));
            
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 10 && parts[1].equals(kullanıcıAdı) && parts[3].equals(soyad) && parts[7].equals(tc)) {
                    parts[4] = yeniŞifre;
                    writer.write(String.join(",", parts));
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }
            writer.close();
            JOptionPane.showMessageDialog(this, "Şifre başarıyla güncellendi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ŞifremiUnuttum();
    }
}
