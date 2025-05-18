package rental;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MüşterininArayüzü extends JFrame {
    private JPanel panel;

    public MüşterininArayüzü() {
        setTitle("Ada Filo - Müşteri Arayüzü");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        add(panel);

        JButton btnÜrünEkle = new JButton("Araç Kirala");
        btnÜrünEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AraçKirala().setVisible(true);
            }
        });
        panel.add(btnÜrünEkle);


        JButton btnKiraladıklarım = new JButton("Kiraladıklarım");
        btnKiraladıklarım.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Kiraladıklarım().setVisible(true);
            }
        });
        panel.add(btnKiraladıklarım);



        JButton btnÇıkış = new JButton("Çıkış");
        btnÇıkış.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	KiralananlarıTemizle();
                dispose(); 
                new BaşlangıçSayfası().setVisible(true);
            }
        });
        panel.add(btnÇıkış);

        setVisible(true);
    }
    
    private void KiralananlarıTemizle() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Msi\\eclipse-workspace\\Proje\\src\\Proje\\kiralananaraçlar.txt"))) {
            // Dosyayı temizleme işlemi
            writer.write(""); // Dosyayı boşaltır
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MüşterininArayüzü().setVisible(true);
        });
    }
}
