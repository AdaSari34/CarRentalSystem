package rental;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YöneticininArayüzü extends JFrame {

    public YöneticininArayüzü() {

        setTitle("Ada Filo - Yönetici Arayüzü");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel anaPanel = new JPanel();
        anaPanel.setLayout(new GridLayout(5, 1, 10, 10));
        add(anaPanel);

        final AraçEkleme[] araçEkleme = {null};

        JButton AraçEkleButton = new JButton("Filoya Araç Ekle");
        AraçEkleButton.addActionListener(e -> {
            if (araçEkleme[0] == null) {
                araçEkleme[0] = new AraçEkleme();
            }
            dispose();
            araçEkleme[0].setVisible(true);
        });
        anaPanel.add(AraçEkleButton);

        JButton AraçSilButton = new JButton("Filodan Araç Sil");
        AraçSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AraçSilme().actionPerformed(e);
                dispose();
            }
        });
        anaPanel.add(AraçSilButton);

       
        JButton MevcutButton = new JButton("Mevcut Araçları Görüntüle");
        MevcutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new MevcutAraçlar().setVisible(true);
            }
        });
        anaPanel.add(MevcutButton);
                
        JButton KiralananlarıGörüntüleButon = new JButton("Kiralananlar");
        KiralananlarıGörüntüleButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new YöneticininGörüntülediğiKiralananlar().setVisible(true);
            }
        });
        anaPanel.add(KiralananlarıGörüntüleButon);


        JPanel altPanel = new JPanel();
        anaPanel.add(altPanel);

        JButton ÇıkışButton = new JButton("Çıkış Yap");
        ÇıkışButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Yönetici çıkış yaptı.");
                dispose();
                new BaşlangıçSayfası().setVisible(true);

                
            }
        });
        altPanel.add(ÇıkışButton);

        setVisible(true);
    }

    public static void main(String[] args) {
    	new YöneticininArayüzü();
        
    }
}
