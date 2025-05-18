package rental;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GirişYap extends JFrame { 
    public GirişYap() { 
        setTitle("Müşteri Girişi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        add(panel);

        JLabel lblKullanıcıAdı = new JLabel("Kullanıcı Adı:");
        JTextField txtKullanıcıAdı = new JTextField();
        panel.add(lblKullanıcıAdı);
        panel.add(txtKullanıcıAdı);

        JLabel lblşifre = new JLabel("Şifre:");
        JPasswordField txtşifre = new JPasswordField();
        panel.add(lblşifre);
        panel.add(txtşifre);

        JButton btnGirişYap = new JButton("Giriş Yap");
        btnGirişYap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kullanıcıAdı = txtKullanıcıAdı.getText();
                String şifre = new String(txtşifre.getPassword());

                // Kullanıcı bilgilerini kontrol et
                if (kullanıcıGirişKontrolü(kullanıcıAdı, şifre)) {
                    new MüşterininArayüzü().setVisible(true);
                    dispose();
                } else {
                    // Giriş başarısız, hata mesajı göster
                    JOptionPane.showMessageDialog(GirişYap.this, "Kullanıcı adı veya şifre yanlış!", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(btnGirişYap);

        setVisible(true);
        
        // Geri butonu
        JButton geriButton = new JButton("Geri");
        geriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            		dispose();
                new BaşlangıçSayfası().setVisible(true);
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(geriButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private boolean kullanıcıGirişKontrolü(String kullanıcıAdı, String şifre) {
        String dosyaYolu = "C:\\Users\\Msi\\eclipse-workspace\\Proje\\src\\Proje\\kullanıcılarım.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaYolu))) {
            String satır;
            while ((satır = reader.readLine()) != null) {
                String[] parçalar = satır.split(",");
                if (parçalar.length >= 10 && parçalar[1].equals(kullanıcıAdı) && parçalar[4].equals(şifre)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
