package rental;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AraçEkleme extends JFrame {
    public AraçEkleme() {
        setTitle("Araba Ekle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 700);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(17, 2, 10, 10));
        add(panel);

        // Araç Kategorisi
        JLabel lblAraçKategori = new JLabel("Araç Kategorisi:");
        JComboBox<String> cmbAraçKategori = new JComboBox<>(new String[]{"SUV", "Sedan", "Pickup"});
        panel.add(lblAraçKategori);
        panel.add(cmbAraçKategori);

        // Marka
        JLabel lblMarka = new JLabel("Marka:");
        JTextField txtMarka = new JTextField();
        panel.add(lblMarka);
        panel.add(txtMarka);

        // Model
        JLabel lblModel = new JLabel("Model:");
        JTextField txtModel = new JTextField();
        panel.add(lblModel);
        panel.add(txtModel);

        // Model Yılı
        JLabel lblModelYılı = new JLabel("Model Yılı:");
        JTextField txtModelYılı = new JTextField();
        panel.add(lblModelYılı);
        panel.add(txtModelYılı);

        // Yakıt Türü
        JLabel lblYakıtTürü = new JLabel("Yakıt Türü:");
        JComboBox<String> cmbYakıtTürü = new JComboBox<>(new String[]{"Dizel", "Benzin", "Elektrik"});
        panel.add(lblYakıtTürü);
        panel.add(cmbYakıtTürü);

        // Vites Türü
        JLabel lblVitesTürü = new JLabel("Vites Türü:");
        JComboBox<String> cmbVitesTürü = new JComboBox<>(new String[]{"Otomatik", "Manuel"});
        panel.add(lblVitesTürü);
        panel.add(cmbVitesTürü);

        // Araç Durumu
        JLabel lblDurum = new JLabel("Araç Durumu:");
        JComboBox<String> cmbDurum = new JComboBox<>(new String[]{"Sıfır", "İkinci el"});
        panel.add(lblDurum);
        panel.add(cmbDurum);

        // Kilometre
        JLabel lblKilometre = new JLabel("Kilometre:");
        JTextField txtKilometre = new JTextField();
        panel.add(lblKilometre);
        panel.add(txtKilometre);

        // Çekiş
        JLabel lblÇekiş = new JLabel("Çekiş:");
        JComboBox<String> cmbÇekiş = new JComboBox<>(new String[]{"4X2", "4X4"});
        panel.add(lblÇekiş);
        panel.add(cmbÇekiş);

        // Güç
        JLabel lblGüç = new JLabel("Güç(hp):");
        JTextField txtGüç = new JTextField();
        panel.add(lblGüç);
        panel.add(txtGüç);

        // Renk
        JLabel lblRenk = new JLabel("Renk:");
        JTextField txtRenk = new JTextField();
        panel.add(lblRenk);
        panel.add(txtRenk);

        // Yakıt Tüketimi
        JLabel lblYakıtTüketimi = new JLabel("Yakıt Tüketimi(l/100 km):");
        JTextField txtYakıtTüketimi = new JTextField();
        panel.add(lblYakıtTüketimi);
        panel.add(txtYakıtTüketimi);

        // Fiyat alanları
        JLabel lblGünlükFiyat = new JLabel("Günlük Fiyat:");
        JTextField txtGünlükFiyat = new JTextField();
        panel.add(lblGünlükFiyat);
        panel.add(txtGünlükFiyat);

        JLabel lblHaftalıkFiyat = new JLabel("Haftalık Fiyat:");
        JTextField txtHaftalıkFiyat = new JTextField();
        panel.add(lblHaftalıkFiyat);
        panel.add(txtHaftalıkFiyat);

        JLabel lblAylıkFiyat = new JLabel("Aylık Fiyat:");
        JTextField txtAylıkFiyat = new JTextField();
        panel.add(lblAylıkFiyat);
        panel.add(txtAylıkFiyat);

        JLabel lblYıllıkFiyat = new JLabel("Yıllık Fiyat:");
        JTextField txtYıllıkFiyat = new JTextField();
        panel.add(lblYıllıkFiyat);
        panel.add(txtYıllıkFiyat);

      
        JButton btnEkle = new JButton("Filoya Ekle");
        btnEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtMarka.getText().isEmpty() || txtModel.getText().isEmpty() || txtModelYılı.getText().isEmpty() || txtKilometre.getText().isEmpty() ||
                        txtGüç.getText().isEmpty() || txtRenk.getText().isEmpty() || txtYakıtTüketimi.getText().isEmpty() || txtGünlükFiyat.getText().isEmpty() ||
                        txtHaftalıkFiyat.getText().isEmpty() || txtAylıkFiyat.getText().isEmpty() || txtYıllıkFiyat.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurun", "Eksik Bilgi", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String araçKategori = (String) cmbAraçKategori.getSelectedItem();
                String marka = txtMarka.getText();
                String model = txtModel.getText();
                String modelYılı = txtModelYılı.getText();
                String yakıtTürü = (String) cmbYakıtTürü.getSelectedItem();
                String vitesTürü = (String) cmbVitesTürü.getSelectedItem();
                String durum = (String) cmbDurum.getSelectedItem();
                String kilometre = txtKilometre.getText();
                String çekiş = (String) cmbÇekiş.getSelectedItem();
                String güç = txtGüç.getText();
                String renk = txtRenk.getText();
                String yakıtTüketimi = txtYakıtTüketimi.getText();
                String günlükFiyat = txtGünlükFiyat.getText();
                String haftalıkFiyat = txtHaftalıkFiyat.getText();
                String aylıkFiyat = txtAylıkFiyat.getText();
                String yıllıkFiyat = txtYıllıkFiyat.getText();

               
                AraçlarıDosyayaYaz.araçlarıYaz(araçKategori, marka, model, modelYılı, yakıtTürü, vitesTürü, durum, kilometre, çekiş, güç, renk, yakıtTüketimi, günlükFiyat, haftalıkFiyat, aylıkFiyat, yıllıkFiyat);
                JOptionPane.showMessageDialog(null, marka + " " + model + " aracı başarıyla eklendi");
                dispose();
                new YöneticininArayüzü().setVisible(true);
            }
            
            
        });
        panel.add(new JLabel()); 
        panel.add(btnEkle);

        
        // Geri butonu
        JButton geriButton = new JButton("Geri");
        geriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            		dispose();
                new YöneticininArayüzü().setVisible(true);
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(geriButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new AraçEkleme();
    }
}
