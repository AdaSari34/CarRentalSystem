package rental;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaşlangıçSayfası {
	
    private JFrame frame;
    private JLabel labelDateTime;

    public BaşlangıçSayfası() {
        frame = new JFrame("Ada Filo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 200);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        frame.add(panel);

       
        

        JLabel labelAdaFilo = new JLabel("Ada Filo", SwingConstants.CENTER);
        labelAdaFilo.setForeground(Color.BLACK);
        labelAdaFilo.setFont(new Font("Arial", Font.BOLD, 30));
        panel.add(labelAdaFilo, BorderLayout.CENTER);


   

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton btnYöneticiGirişi = new JButton("Yönetici Girişi");
        btnYöneticiGirişi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new YöneticiGirişi().setVisible(true);
            }
        });
        buttonPanel.add(btnYöneticiGirişi);

        JButton btnMüşteriKayıtOl = new JButton("Müşteri Kayıt Ol");
        btnMüşteriKayıtOl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new KayıtOl().setVisible(true);       
            }
        });
        buttonPanel.add(btnMüşteriKayıtOl);

        JButton btnŞifremiUnuttum = new JButton("Şifremi Unuttum");
        btnŞifremiUnuttum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new ŞifremiUnuttum().setVisible(true);       
            }
        });
        buttonPanel.add(btnŞifremiUnuttum);

        JButton btnMüşteriGirişi = new JButton("Müşteri Girişi");
        btnMüşteriGirişi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new GirişYap().setVisible(true);
            }
        });
        buttonPanel.add(btnMüşteriGirişi);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BaşlangıçSayfası());
    }
}
