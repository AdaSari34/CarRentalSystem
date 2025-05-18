package rental;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AraçSilme implements ActionListener {
    private static final String DOSYA_YOLU = "C:\\Users\\Msi\\eclipse-workspace\\Proje\\src\\Proje\\Araçlar.txt";

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("Araba Silme");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 

        try (BufferedReader reader = new BufferedReader(new FileReader(DOSYA_YOLU))) {
            String satır;
            while ((satır = reader.readLine()) != null) {
                String[] parçalar = satır.split(",");
                if (parçalar.length >= 17) {
                    JButton silButton = new JButton("Marka: " + parçalar[2] +
                            " - Model: " + parçalar[3] +
                            " - Model Yılı: " + parçalar[4] +
                            " - Yakıt Türü: " + parçalar[5] +
                            " - Vites Türü: " + parçalar[6] +
                            " - Durum: " + parçalar[7] +
                            " - Kilometre: " + parçalar[8] +
                            " - Güç: " + parçalar[10] +
                            " - Renk: " + parçalar[11]);

                    String marka = parçalar[1];
                    String model = parçalar[2];
                    silButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int result = JOptionPane.showConfirmDialog(null, "Seçtiğiniz aracı silmek istiyor musunuz ?", "silme", JOptionPane.YES_NO_OPTION);
                            if (result == JOptionPane.YES_OPTION) {
                            	araçSil(marka, model);
                                frame.dispose();
                                new YöneticininArayüzü().setVisible(true);
                            } else {
                                JOptionPane.showMessageDialog(null, "Aracı silmediniz");
                            }
                        }
                    });

                    panel.add(silButton);
                    panel.add(Box.createVerticalStrut(10)); // Butonlar arasına boşluk kalması
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Geri butonu
        JButton geriButton = new JButton("Geri");
        geriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new YöneticininArayüzü().setVisible(true);
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(geriButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void araçSil(String marka, String model) {
        List<String> güncelAraçlar = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DOSYA_YOLU))) {
            String satır;
            while ((satır = reader.readLine()) != null) {
                String[] parçalar = satır.split(",");
                if (!(parçalar[1].equals(marka) && parçalar[2].equals(model))) {
                    güncelAraçlar.add(satır);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOSYA_YOLU))) {
            for (String araba : güncelAraçlar) {
                writer.write(araba);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new YöneticininArayüzü();
    }
}
