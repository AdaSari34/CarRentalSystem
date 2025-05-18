package rental;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Kiraladıklarım extends JFrame {
    private static final String DOSYA_YOLU = "C:\\Users\\Msi\\eclipse-workspace\\Proje\\src\\Proje\\kiralananaraçlar.txt";
    private JTextArea kiraladıklarımTextArea;

    public Kiraladıklarım() {
        setTitle("Kiraladıklarım");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        kiraladıklarımTextArea = new JTextArea();
        kiraladıklarımTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(kiraladıklarımTextArea);
        add(scrollPane, BorderLayout.CENTER);

        aracıListele();
    }

    private void aracıListele() {
        kiraladıklarımTextArea.setText("");
        try (BufferedReader reader = new BufferedReader(new FileReader(DOSYA_YOLU))) {
            String satır;
            while ((satır = reader.readLine()) != null) {
                kiraladıklarımTextArea.append(satır + "\n");
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
