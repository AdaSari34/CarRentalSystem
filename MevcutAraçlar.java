package rental;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MevcutAraçlar extends JFrame {
    private static final String DOSYA_YOLU = "C:\\Users\\Msi\\eclipse-workspace\\Proje\\src\\Proje\\Araçlar.txt";
    private JTextArea araçlarTextArea;

    public MevcutAraçlar() {
        setTitle("Mevcut Araçlar");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        araçlarTextArea = new JTextArea();
        araçlarTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(araçlarTextArea);
        add(scrollPane, BorderLayout.CENTER);

        araciListele();
    }

    private void araciListele() {
        araçlarTextArea.setText("");
        try (BufferedReader reader = new BufferedReader(new FileReader(DOSYA_YOLU))) {
            String satır;
            while ((satır = reader.readLine()) != null) {
                String[] parçalar = satır.split(",");
                if (parçalar.length >= 17) {
                    String araçBilgisi = String.format("Marka: %s - Model: %s - Model Yılı: %s - Günlük Fiyat: %s\n",
                            parçalar[2], parçalar[3], parçalar[4], parçalar[13]);
                    araçlarTextArea.append(araçBilgisi);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MevcutAraçlar mevcutAraclar = new MevcutAraçlar();
            mevcutAraclar.setVisible(true);
        });
    }
}
