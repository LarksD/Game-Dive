import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;


public class MainTab {

    public static void main(String[] args) {
        MainTab.abaJogo();
    }

    public static void abaJogo() {
        JFrame mainframe = new JFrame("GameDive");
        mainframe.getContentPane().setBackground(new Color(16, 11, 37));
        Container panelMain = mainframe.getContentPane();
        panelMain.setLayout(null);





        //Configurações da janela
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setSize(1280, 720);
        mainframe.setVisible(true);

        Abas.Main(panelMain);



    }

    public static void createTitleButton(Container panelMain, String urlString) {
        try {
            URL url = new URL(urlString);
            Image image = ImageIO.read(url);
            JButton titulo = new JButton(new ImageIcon(image));
            Image newimg = image.getScaledInstance(389, 103, java.awt.Image.SCALE_SMOOTH);
            titulo.setIcon(new ImageIcon(newimg));
            titulo.setBounds(108, 30, 389, 103);
            titulo.setBorderPainted(false);
            titulo.setFocusPainted(false);
            titulo.setContentAreaFilled(false);
            panelMain.add(titulo);
            titulo.addActionListener(e -> Abas.Main(panelMain));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}