package rental;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AraçKirala extends JFrame {
    private JPanel panel;
    private List<String> KiralananAraçlar;

    public AraçKirala() {
        setTitle("Araç Kirala");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        add(new JScrollPane(panel));

        KiralananAraçlar = new ArrayList<>();
        AraçlarıDosyadanOku();

        for (String Araç : KiralananAraçlar) {
            String[] araçBilgileri = Araç.split(",");
            if (araçBilgileri.length >= 6) {
                String araçFormat = "Araç Markası: " + araçBilgileri[2] +
                                    " - Araç Modeli: " + araçBilgileri[3] +
                                    " - Yakıt Türü: " + araçBilgileri[5] +
                                    " - Vites Türü: " + araçBilgileri[6] +
                                    " - Durum: " + araçBilgileri[7] +
                                    " - Kilometre: " + araçBilgileri[8] +
                                    " - Güç: " + araçBilgileri[10] +
                                    " - Renk: " + araçBilgileri[11];
                JButton araçButton = new JButton(araçFormat);
                araçButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int result = JOptionPane.showConfirmDialog(null, "Seçtiğiniz aracı kiralamak istiyor musunuz ?", "Kiralama", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            dispose();
                            kiralamaSüresiEkranı(Araç);
                        } else {
                            JOptionPane.showMessageDialog(null, "Araç kiralama  işlemini iptal ettiniz.");
                        }
                    }
                });
                panel.add(araçButton);
            }
        }

        setVisible(true);
    }

    private void AraçlarıDosyadanOku() {
        String dosyaYolu = "C:\\Users\\Msi\\eclipse-workspace\\Proje\\src\\Proje\\Araçlar.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaYolu))) {
            String satır;
            while ((satır = reader.readLine()) != null) {
                KiralananAraçlar.add(satır);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void kiralamaSüresiEkranı(String araç) {
        JFrame kiralamaSüresiFrame = new JFrame("Kiralama Süresi");
        kiralamaSüresiFrame.setSize(400, 200);
        kiralamaSüresiFrame.setLayout(new GridLayout(0, 1));
        kiralamaSüresiFrame.setLocationRelativeTo(null);

        String[] kiralamaSüresiSeçenekleri = {"Günlük", "Haftalık", "Aylık", "Yıllık"};
        int[] kiralamaFiyatları = {Integer.parseInt(araç.split(",")[13].trim()), Integer.parseInt(araç.split(",")[14].trim()), Integer.parseInt(araç.split(",")[15].trim()), Integer.parseInt(araç.split(",")[16].trim())};

        for (int i = 0; i < kiralamaSüresiSeçenekleri.length; i++) {
            JButton kiralamaSüresiButton = new JButton(kiralamaSüresiSeçenekleri[i] + ": " + kiralamaFiyatları[i] + " TL");
            int index = i;
            kiralamaSüresiButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    ödemeEkrani(araç, kiralamaSüresiSeçenekleri[index], kiralamaFiyatları[index]);
                    kiralamaSüresiFrame.dispose();
                }
            });
            kiralamaSüresiFrame.add(kiralamaSüresiButton);
        }

        kiralamaSüresiFrame.setVisible(true);
    }

    private void ödemeEkrani(String araç, String kiralamaSüresi, int fiyat) {
        JFrame ödemeFrame = new JFrame("Ödeme Ekranı");
        ödemeFrame.setSize(300, 200);
        ödemeFrame.setLayout(new GridLayout(4, 2));
        ödemeFrame.setLocationRelativeTo(null);

        JLabel kartNumarasıLabel = new JLabel("Kart Numarası:");
        JTextField kartNumarasıField = new JTextField();
        JLabel cvvLabel = new JLabel("CVV:");
        JTextField cvvField = new JTextField();
        JLabel sonKullanmaTarihiLabel = new JLabel("Son Kullanma Tarihi:");
        JTextField sonKullanmaTarihiField = new JTextField();

        JButton ödemeYapButton = new JButton("Ödeme Yap");
        JButton iptalEtButton = new JButton("İptal Et");

        ödemeYapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kartNumarası = kartNumarasıField.getText();
                String cvv = cvvField.getText();
                String sonKullanmaTarihi = sonKullanmaTarihiField.getText();

                if (Ödeme.krediKartıylaÖdemeYap(kartNumarası, sonKullanmaTarihi, cvv)) {
                	AracıDosyadanSil(araç);
                    KiralananAraçlarıKaydet(araç, kiralamaSüresi, fiyat);
                    KiralananAraçlarıKaydett(araç, kiralamaSüresi, fiyat);
                    JOptionPane.showMessageDialog(null, "Ödeme başarılı.Aracı " + kiralamaSüresi + " Kiraladınız");
                    ödemeFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Ödeme bilgileriniz hatalı, lütfen kontrol ediniz.");
                }
            }
        });

        iptalEtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ödemeFrame.dispose();
            }
        });

        ödemeFrame.add(kartNumarasıLabel);
        ödemeFrame.add(kartNumarasıField);
        ödemeFrame.add(cvvLabel);
        ödemeFrame.add(cvvField);
        ödemeFrame.add(sonKullanmaTarihiLabel);
        ödemeFrame.add(sonKullanmaTarihiField);
        ödemeFrame.add(ödemeYapButton);
        ödemeFrame.add(iptalEtButton);

        ödemeFrame.setVisible(true);
    }
    
    private void AracıDosyadanSil(String araç) {
    	KiralananAraçlar.remove(araç);
        String dosyaYolu = "C:\\Users\\Msi\\eclipse-workspace\\Proje\\src\\Proje\\Araçlar.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dosyaYolu))) {
            for (String Araç : KiralananAraçlar) {
                writer.write(Araç);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void KiralananAraçlarıKaydet(String araç, String kiralamaSüresi, int fiyat) {
        String dosyaYolu = "C:\\Users\\Msi\\eclipse-workspace\\Proje\\src\\Proje\\kiralananaraçlar.txt";
        String[] araçBilgileri = araç.split(",");
        if (araçBilgileri.length >= 17) {
            String kiralamaBilgisi = "Araç Markası: " + araçBilgileri[2] +
                                     " - Model: " + araçBilgileri[3] +
                                     " - Model Yılı: " + araçBilgileri[4] +
                                     " - Yakıt Türü: " + araçBilgileri[5] +
                                     " - Vites Türü: " + araçBilgileri[6] +
                                     " - Durum: " + araçBilgileri[7] +
                                     " - Kilometre: " + araçBilgileri[8] +
                                     " - Güç: " + araçBilgileri[10] + " HP" +
                                     " - Renk: " + araçBilgileri[11] +
                                     " - Kiralama Süresi: " + kiralamaSüresi +
                                     " - Fiyat: " + fiyat + " TL";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(dosyaYolu, true))) {
                writer.write(kiralamaBilgisi);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    private void KiralananAraçlarıKaydett(String araç, String kiralamaSüresi, int fiyat) {
        String dosyaYolu = "C:\\Users\\Msi\\eclipse-workspace\\Proje\\src\\Proje\\kiralananaraçlarYÖNETİCİ.txt";
        String[] araçBilgileri = araç.split(",");
        if (araçBilgileri.length >= 17) {
            String kiralamaBilgisi = "Araç Markası: " + araçBilgileri[2] +
                                     " - Model: " + araçBilgileri[3] +
                                     " - Model Yılı: " + araçBilgileri[4] +
                                     " - Yakıt Türü: " + araçBilgileri[5] +
                                     " - Vites Türü: " + araçBilgileri[6] +
                                     " - Durum: " + araçBilgileri[7] +
                                     " - Kilometre: " + araçBilgileri[8] +
                                     " - Güç: " + araçBilgileri[10] + " HP" +
                                     " - Renk: " + araçBilgileri[11] +
                                     " - Kiralama Süresi: " + kiralamaSüresi +
                                     " - Fiyat: " + fiyat + " TL";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(dosyaYolu, true))) {
                writer.write(kiralamaBilgisi);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        new AraçKirala();
    }
}
