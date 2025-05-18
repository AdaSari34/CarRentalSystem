package rental;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YöneticiGirişi extends JFrame {
    public YöneticiGirişi() {
        setTitle("Ada Filo - Yönetici İşlemleri");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel);

       
        JLabel lblBaşlık = new JLabel("Yönetici Girişi", SwingConstants.CENTER);
        panel.add(lblBaşlık, BorderLayout.NORTH);

        
        JPanel girişPaneli = new JPanel();
        girişPaneli.setLayout(new GridLayout(3, 2, 5, 5));
        panel.add(girişPaneli, BorderLayout.CENTER);

        
        JLabel lblKullanıcıAdı = new JLabel("Kullanıcı Adı:");
        JTextField txtKullanıcıAdı = new JTextField();
        girişPaneli.add(lblKullanıcıAdı);
        girişPaneli.add(txtKullanıcıAdı);

       
        JLabel lblŞifre = new JLabel("Şifre:");
        JPasswordField txtŞifre = new JPasswordField();
        girişPaneli.add(lblŞifre);
        girişPaneli.add(txtŞifre);


        girişPaneli.add(new JLabel());
        JButton btnGiriş = new JButton("Giriş Yap");
        btnGiriş.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                girişYap(txtKullanıcıAdı.getText(), new String(txtŞifre.getPassword()));
                dispose(); 
                new YöneticininArayüzü().setVisible(true);

            }
        });
        girişPaneli.add(btnGiriş);

        
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

    private void girişYap(String kullanıcıAdı, String şifre) {
        if (kullanıcıAdı.equals("1") && şifre.equals("1")) {
        } else {
            JOptionPane.showMessageDialog(this, "Yanlış kullanıcı adı veya şifre!");
        }
    }

    public static void main(String[] args) {
        new YöneticiGirişi();
    }
}
