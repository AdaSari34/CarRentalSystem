package rental;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class KullanıcılarıDosyayaYazdır {
    static final String DOSYA_YOLU = "C:\\Users\\Msi\\eclipse-workspace\\Proje\\src\\Proje\\kullanıcılarım.txt";

    public static void kullanıcıBilgileriniDosyayaKaydet(Müşteri müşteri) {
        String kullanıcıAdı = müşteri.getKullanıcıAdı();
        String şifre = müşteri.getŞifre();
        String ad = müşteri.getAd();
        String soyad = müşteri.getSoyad();
        String eposta = müşteri.getEmail();
        String doğumTarihi = müşteri.getDoğumTarihi();
        String telefonNo = müşteri.getTelefonNumarası();
        String TCNo = müşteri.getTcKimlikNumarası();
        String EhliyetSeriNo = müşteri.getEhliyetSeriNumarası();

        if (kullanıcıBilgileridoğru(müşteri)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOSYA_YOLU, true))) {
                int sonIndex = sonIndexiAl() + 1;
                writer.write(sonIndex + "," + kullanıcıAdı + "," + ad + "," + soyad + "," + şifre + "," + telefonNo + "," + eposta + "," + TCNo + "," + EhliyetSeriNo + "," + doğumTarihi);
                writer.newLine();
                System.out.println("Kullanıcı kaydedildi: " + ad + " " + soyad);
            } catch (IOException e) {
                System.out.println("Dosya yazma hatası: " + e.getMessage());
            }
        } else {
            System.out.println("Hatalı kullanıcı bilgileri! Kullanıcı kaydedilemedi.");
        }
    }

    private static boolean kullanıcıBilgileridoğru(Müşteri müşteri) {
        return müşteri.getKullanıcıAdı().length() <= 20 &&
               müşteri.getŞifre().length() <= 20 &&
               müşteri.getAd().length() <= 20 &&
               müşteri.getSoyad().length() <= 20 &&
               müşteri.getEmail().length() <= 50 &&
               müşteri.getDoğumTarihi().length() <= 10 &&
               müşteri.getTelefonNumarası().length() <= 15 &&
               müşteri.getTcKimlikNumarası().length() == 11 &&
               müşteri.getEhliyetSeriNumarası().length() == 6;
    }

    private static int sonIndexiAl() {
        int sonIndex = -1;
        try {
            List<String> satırlar = Files.readAllLines(Paths.get(DOSYA_YOLU));
            if (!satırlar.isEmpty()) {
                String sonSatır = satırlar.get(satırlar.size() - 1);
                String[] parçalar = sonSatır.split(",");
                if (parçalar.length > 0 && !parçalar[0].isEmpty()) {
                    sonIndex = Integer.parseInt(parçalar[0]);
                }
            }
        } catch (IOException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz sayı formatı: " + e.getMessage());
        }
        return sonIndex;
    }
}
