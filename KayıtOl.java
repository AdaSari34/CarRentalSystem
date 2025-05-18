package rental;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class KayıtOl extends JFrame {
    public KayıtOl() {
        setTitle("Müşteri Kayıt Ol");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2));
        add(panel);

        JLabel lblKullanıcıAdı = new JLabel("Kullanıcı Adı:");
        JTextField txtKullanıcıAdı = new JTextField();
        panel.add(lblKullanıcıAdı);
        panel.add(txtKullanıcıAdı);
        
        JLabel lblAd = new JLabel("Ad:");
        JTextField txtAd = new JTextField();
        panel.add(lblAd);
        panel.add(txtAd);

        JLabel lblSoyad = new JLabel("Soyad:");
        JTextField txtSoyad = new JTextField();
        panel.add(lblSoyad);
        panel.add(txtSoyad);

        JLabel lblşifre = new JLabel("Şifre:");
        JPasswordField txtşifre = new JPasswordField();
        panel.add(lblşifre);
        panel.add(txtşifre);

        JLabel lblEposta = new JLabel("E-posta:");
        JTextField txtEposta = new JTextField();
        panel.add(lblEposta);
        panel.add(txtEposta);
        
        JLabel lblTel = new JLabel("Telefon numarası:");
        JTextField txtTel = new JTextField();
        panel.add(lblTel);
        panel.add(txtTel);
        
        JLabel lblTC = new JLabel("TC kimlik no:");
        JTextField txtTC = new JTextField();
        panel.add(lblTC);
        panel.add(txtTC);
        
        JLabel lblSeriNo = new JLabel("Ehliyet seri no:");
        JTextField txtSeriNo = new JTextField();
        panel.add(lblSeriNo);
        panel.add(txtSeriNo);

        JLabel lblDoğumTarih = new JLabel("Doğum Tarihi:");
        JTextField txtDoğumTarih = new JTextField();
        panel.add(lblDoğumTarih);
        panel.add(txtDoğumTarih);

        JButton btnKayıtOl = new JButton("Kayıt Ol");
        btnKayıtOl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kullanıcıAdı = txtKullanıcıAdı.getText();
                String şifre = new String(txtşifre.getPassword());
                String eposta = txtEposta.getText();
                String ad = txtAd.getText();
                String soyad = txtSoyad.getText();
                String doğumTarih = txtDoğumTarih.getText();
                String telefonNo = txtTel.getText();
                String TCNo = txtTC.getText();
                String EhliyetSeriNo = txtSeriNo.getText();

                if (!EhliyetSeriNo.matches("\\d{6}")) {
                    JOptionPane.showMessageDialog(null, "Ehliyet seri numarası 6 haneden oluşmalıdır.");
                } else if (!TCNo.matches("\\d{11}")) {
                    JOptionPane.showMessageDialog(null, "TC kimlik numarası sadece 11 haneden oluşmalıdır.");
                } else if (TCvarMı(TCNo)) {
                    JOptionPane.showMessageDialog(null, "Böyle bir TC bulunmaktadır. Lütfen tekrar deneyin.");
                } else if (kullanıcıAdıVarMı(kullanıcıAdı)) {
                    JOptionPane.showMessageDialog(null, "Böyle bir kullanıcı adı bulunmaktadır. Lütfen başka bir kullanıcı adı deneyin.");
                } else if (!doğumTarih.matches("\\d{2}/\\d{2}/\\d{2}")) {
                    JOptionPane.showMessageDialog(null, "Doğum tarihini yanlış girdiniz. Lütfen doğru girin.");
                } else {
                    Müşteri müşteri = new Müşteri(kullanıcıAdı, ad, soyad, şifre, telefonNo, eposta, TCNo, EhliyetSeriNo, doğumTarih);
                    KullanıcılarıDosyayaYazdır.kullanıcıBilgileriniDosyayaKaydet(müşteri);
                    JOptionPane.showMessageDialog(null, "Yeni kullanıcı kaydedildi!");
                    dispose();
                    new BaşlangıçSayfası().setVisible(true);
                }
            }
        });
        panel.add(btnKayıtOl);

        setVisible(true);
        
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

    private static boolean TCvarMı(String tc) {
        try (BufferedReader reader = new BufferedReader(new FileReader(KullanıcılarıDosyayaYazdır.DOSYA_YOLU))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parçalar = line.split(",");
                if (parçalar.length == 10 && parçalar[8].equals(tc)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean kullanıcıAdıVarMı(String kullanıcıAdı) {
        try (BufferedReader reader = new BufferedReader(new FileReader(KullanıcılarıDosyayaYazdır.DOSYA_YOLU))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parçalar = line.split(",");
                if (parçalar.length == 10 && parçalar[1].equals(kullanıcıAdı)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        new KayıtOl();
    }
}
