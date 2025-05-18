package rental;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AraçlarıDosyayaYaz {
    private static final String DOSYA_YOLU = "C:\\Users\\Msi\\eclipse-workspace\\Proje\\src\\Proje\\Araçlar.txt";

    public static void araçlarıYaz(String araçKategori, String marka, String model, String modelYılı, String yakıtTürü, 
                                   String vitesTürü, String durum, String kilometre, String çekiş, String güç, 
                                   String renk, String yakıtTüketimi, String günlükFiyat, String haftalıkFiyat, 
                                   String aylıkFiyat, String yıllıkFiyat) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOSYA_YOLU, true))) {
            int index = getSonIndex() + 1;
            writer.write(index + "," + araçKategori + "," + marka + "," + model + "," + modelYılı + "," + yakıtTürü + ","
                    + vitesTürü + "," + durum + "," + kilometre + "," + çekiş + "," + güç + "," + renk + ","
                    + yakıtTüketimi + "," + günlükFiyat + "," + haftalıkFiyat + "," + aylıkFiyat + "," + yıllıkFiyat);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getSonIndex() {
        try {
            List<String> satırlar = Files.readAllLines(Paths.get(DOSYA_YOLU));
            if (!satırlar.isEmpty()) {
                String sonSatır = satırlar.get(satırlar.size() - 1);
                String[] parçalar = sonSatır.split(",");
                if (parçalar.length > 0 && !parçalar[0].isEmpty()) {
                    return Integer.parseInt(parçalar[0]);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
