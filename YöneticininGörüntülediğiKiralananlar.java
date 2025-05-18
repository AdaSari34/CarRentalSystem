package rental;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class YöneticininGörüntülediğiKiralananlar extends JFrame {
    private static final String DOSYA_YOLU = "C:\\Users\\Msi\\eclipse-workspace\\Proje\\src\\Proje\\kiralananaraçlarYÖNETİCİ.txt";
    private JTextArea KiralananlarTextArea;

    public  YöneticininGörüntülediğiKiralananlar() {
        setTitle("Kiralanan Araçlar - Tümü");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        KiralananlarTextArea = new JTextArea();
        KiralananlarTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(KiralananlarTextArea);
        add(scrollPane, BorderLayout.CENTER);

        aracıListele();
    }

    private void aracıListele() {
    	KiralananlarTextArea.setText("");
        try (BufferedReader reader = new BufferedReader(new FileReader(DOSYA_YOLU))) {
            String satır;
            while ((satır = reader.readLine()) != null) {
            	KiralananlarTextArea.append(satır + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Kiraladıklarım kiraladıklarım = new Kiraladıklarım();
            kiraladıklarım.setVisible(true);
        });
    }
}
