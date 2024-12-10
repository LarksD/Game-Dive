import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionListener;


public class Abas {


    public static void Main(Container panelMain) {

        //Botões
        MainTab.createTitleButton(panelMain, "logo link aqui");
        createHeaderButton(panelMain, "JOGOS", 490, 60, 80, 50, e -> System.out.println("Jogos"));
        createHeaderButton(panelMain, "LISTAS", 590, 60, 110, 50, e -> System.out.println("Listas"));
        createHeaderButton(panelMain, "RANKINGS", 710, 60, 110, 50, e -> System.out.println("Rankings"));
        createHeaderButton(panelMain, "LOGIN", 1070, 57, 80, 50, e -> System.out.println("Login"));

        RoundedTextField search = new RoundedTextField(20);
        search.setBounds(850, 70, 200, 25);
        search.setFont(new java.awt.Font("SansSeriff", Font.PLAIN, 18));
        search.setForeground(Color.WHITE);
        search.setBackground(new Color(16, 11, 37));
        search.setCaretColor(Color.WHITE);
        search.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        RoundedTextField.setTextInRoundedTextField(search, "Pesquisar...");
        panelMain.add(search);

        search.addActionListener(e -> {
            String searchText = search.getText();
            System.out.println("Pesquisando por: " + searchText);
            APIRequests.searchGame("search", searchText);
        });



        Game banner = APIRequests.fetchGame("search", "The Last of Us Part II");
        Game game1 = APIRequests.fetchGame("search", "Hollow Knight");
        Game anothagame = APIRequests.fetchGame("search", "Inside");
        Game game2 = APIRequests.fetchGame("search", "Red Dead Redemption II");
        Game game3 = APIRequests.fetchGame("search", "Elden Ring");
        Game game4 = APIRequests.fetchGame("search", "Black Myth: Wukong");
        Game game5 = APIRequests.fetchGame("search", "Destiny");

        if (banner != null) {
            displayBanner(panelMain, banner, 0, 100);
        }
        if (game1 != null) {
            displayGame(panelMain, game1, 100   , 430);
        }
        if (anothagame != null) {
            displayGame(panelMain, anothagame, 280, 430);
        }
        if (game2 != null) {
            displayGame(panelMain, game2, 460, 430);
        }
        if (game3 != null) {
            displayGame(panelMain, game3, 640, 430);
        }
        if (game4 != null) {
            displayGame(panelMain, game4, 820, 430);
        }
        if (game5 != null) {
            displayGame(panelMain, game5, 1000, 430);
        }
    }

    public static void clean(Container panelMain) {
        panelMain.removeAll();
        panelMain.revalidate();
        panelMain.repaint();
    }

    public static void gameDetails(Container panelMain, Game game) {
        descDisplayBanner(panelMain, game, 0, 100);



        JLabel gameTitle = new JLabel(game.getTitle());
        gameTitle.setBounds(-10, 280, 500, 40);
        gameTitle.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 18));
        gameTitle.setForeground(new Color(255, 255, 255, 255));
        gameTitle.setHorizontalAlignment(SwingConstants.RIGHT);
        panelMain.add(gameTitle);

        JLabel gameDescription = new JLabel("<html>" + game.getDescription() + "</html>");
        gameDescription.setBounds(10, 310, 500, 200);
        gameDescription.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 10));
        gameDescription.setForeground(new Color(255, 255, 255, 255));
        gameDescription.setHorizontalAlignment(SwingConstants.RIGHT);
        panelMain.add(gameDescription);

        JLabel gameScore = new JLabel(game.getRating() + "/5.0");
        gameScore.setBounds(350, 360, 500, 40);
        gameScore.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 18));
        gameScore.setForeground(new Color(255, 255, 255, 255));
        gameScore.setHorizontalAlignment(SwingConstants.RIGHT);
        panelMain.add(gameScore);





    }

    private static void descDisplayBanner(Container panelMain, Game game, int x, int y) {
        try {
            URL url = new URL(game.getImage());
            BufferedImage image = ImageIO.read(url);

            int height = y + 230;
            BufferedImage newimg = new BufferedImage(1280, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = newimg.createGraphics();

            g2d.drawImage(image.getScaledInstance(1280, height, java.awt.Image.SCALE_SMOOTH), 0, 0, null);

            float[] fractionsBottom = {0.5f, 1.0f};
            Color[] colorsBottom = {
                    new Color(16, 11, 37, 0),
                    new Color(16, 11, 37, 255)
            };
            LinearGradientPaint maskBottom = new LinearGradientPaint(0, height - 150, 0, height, fractionsBottom, colorsBottom, MultipleGradientPaint.CycleMethod.NO_CYCLE);

            g2d.setComposite(AlphaComposite.SrcOver);
            g2d.setPaint(maskBottom);
            g2d.fillRect(0, height - 150, 1280, 150);

            g2d.dispose();

            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setBounds(x, 0, 1280, height);

            JButton label = new JButton(new ImageIcon(newimg));
            label.setBounds(0, 0, 1280, height);
            label.setBorderPainted(false);
            label.setFocusPainted(false);
            layeredPane.add(label, JLayeredPane.DEFAULT_LAYER);


            JLabel titulo = new JLabel(game.getTitle());
            titulo.setBounds(-20, y + 150, 500, 50);
            titulo.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 22));
            titulo.setForeground(Color.WHITE);
            titulo.setHorizontalAlignment(SwingConstants.RIGHT);
            layeredPane.add(titulo, JLayeredPane.PALETTE_LAYER);

            JLabel ano = new JLabel(game.getReleaseYear());
            ano.setBounds(-20, y + 185, 500, 40);
            ano.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 14));
            ano.setForeground(Color.WHITE);
            ano.setHorizontalAlignment(SwingConstants.RIGHT);
            layeredPane.add(ano, JLayeredPane.PALETTE_LAYER);

            try {
                URL logourl = new URL("logo link aqui");
                Image logo = ImageIO.read(logourl);
                JButton logobutton = new JButton(new ImageIcon(logo));
                Image newlogo = logo.getScaledInstance(389, 103, java.awt.Image.SCALE_SMOOTH);
                logobutton.setIcon(new ImageIcon(newlogo));
                logobutton.setBounds(108, y - 70, 389, 103);
                logobutton.setBorderPainted(false);
                logobutton.setFocusPainted(false);
                logobutton.setContentAreaFilled(false);
                layeredPane.add(logobutton, JLayeredPane.PALETTE_LAYER);

                logobutton.addActionListener(e -> {
                    clean(panelMain);
                    Main(panelMain);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                URL gametileURL = new URL(game.getImage());
                Image gametileImage = ImageIO.read(gametileURL);
                RoundedButton gametileLabel = new RoundedButton("", 15);
                Image gametileNewimg = gametileImage.getScaledInstance(332, 410, java.awt.Image.SCALE_SMOOTH);
                gametileLabel.setIcon(new ImageIcon(gametileNewimg));
                gametileLabel.setBounds(510, 200, 221, 273);
                layeredPane.add(gametileLabel, JLayeredPane.PALETTE_LAYER);

                panelMain.revalidate();
                panelMain.repaint();
            } catch (IOException e) {
                e.printStackTrace();
            }

            panelMain.add(layeredPane);
            panelMain.revalidate();
            panelMain.repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayBanner(Container panelMain, Game game, int x, int y) {
        try {
            URL url = new URL(game.getImage());
            BufferedImage image = ImageIO.read(url);
            BufferedImage newimg = new BufferedImage(1280, 300, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = newimg.createGraphics();

            // Imagem original
            g2d.drawImage(image.getScaledInstance(1280, 300, java.awt.Image.SCALE_SMOOTH), 0, 0, null);

            // Gradiente de cima
            float[] fractionsTop = {0.0f, 0.5f};
            Color[] colorsTop = {
                    new Color(16, 11, 37, 255), // 0% transparencia no topo
                    new Color(16, 11, 37, 0)    // 100% transparencia no meio
            };
            LinearGradientPaint maskTop = new LinearGradientPaint(0, 0, 0, 150, fractionsTop, colorsTop, MultipleGradientPaint.CycleMethod.NO_CYCLE);

            // Gradiente de baixo
            float[] fractionsBottom = {0.5f, 1.0f};
            Color[] colorsBottom = {
                    new Color(16, 11, 37, 0),
                    new Color(16, 11, 37, 255)
            };
            LinearGradientPaint maskBottom = new LinearGradientPaint(0, 150, 0, 300, fractionsBottom, colorsBottom, MultipleGradientPaint.CycleMethod.NO_CYCLE);

            g2d.setComposite(AlphaComposite.SrcOver);
            g2d.setPaint(maskTop);
            g2d.fillRect(0, 0, 1280, 150);

            g2d.setComposite(AlphaComposite.SrcOver);
            g2d.setPaint(maskBottom);
            g2d.fillRect(0, 150, 1280, 150);

            g2d.dispose();

            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setBounds(x, y, 1280, 300);

            JButton label = new JButton(new ImageIcon(newimg));
            label.setBounds(0, 0, 1280, 300);
            label.setBorderPainted(false);
            label.setFocusPainted(false);
            layeredPane.add(label, JLayeredPane.DEFAULT_LAYER);

            label.addActionListener(e -> {
                clean(panelMain);
                gameDetails(panelMain, game);
            });

            JLabel titulo = new JLabel(game.getTitle());
            titulo.setBounds(900, 150, 500, 50);
            titulo.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 22));
            titulo.setForeground(Color.WHITE);
            layeredPane.add(titulo, JLayeredPane.PALETTE_LAYER);

            JLabel ano = new JLabel(game.getReleaseYear());
            ano.setBounds(1080, 175, 500, 40);
            ano.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 14));
            ano.setForeground(Color.WHITE);
            layeredPane.add(ano, JLayeredPane.PALETTE_LAYER);

            JLabel emalta = new JLabel("EM ALTA");
            emalta.setBounds(1040, 120, 500, 40);
            emalta.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 16));
            emalta.setForeground(new Color(255, 255, 255, 100));
            layeredPane.add(emalta, JLayeredPane.PALETTE_LAYER);


            JLabel gdtext = new JLabel("<html>Registre suas experiências. Expresse<br/>sua paixão. Descubra novas aventuras.<br/><br/>Compartilhe o seu legado <b>Gamer</b><br/><br/>Tudo em um só lugar.</html>");
            gdtext.setBounds(110, 30, 500, 200);
            gdtext.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 16));
            gdtext.setForeground(Color.WHITE);
            layeredPane.add(gdtext, JLayeredPane.PALETTE_LAYER);

            try {
                URL logourl = new URL('logo link aqui');
                Image logo = ImageIO.read(logourl);
                JButton logobutton = new JButton(new ImageIcon(logo));
                Image newlogo = logo.getScaledInstance(389, 103, java.awt.Image.SCALE_SMOOTH);
                logobutton.setIcon(new ImageIcon(newlogo));
                logobutton.setBounds(108, -70, 389, 103);
                logobutton.setBorderPainted(false);
                logobutton.setFocusPainted(false);
                logobutton.setContentAreaFilled(false);
                layeredPane.add(logobutton, JLayeredPane.PALETTE_LAYER);
            } catch (IOException e) {
                e.printStackTrace();
            }

            panelMain.add(layeredPane);
            panelMain.revalidate();
            panelMain.repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static void createHeaderButton(Container panelMain, String text, int x, int y, int width, int height, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
        button.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.addActionListener(actionListener);
        panelMain.add(button);
    }

    private static void displayGame(Container panelMain, Game game, int x, int y) {
        try {
            URL url = new URL(game.getImage());
            Image image = ImageIO.read(url);
            RoundedButton label = new RoundedButton("", 15);
            Image newimg = image.getScaledInstance(166, 205, java.awt.Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(newimg));
            label.setBounds(x, y, 166, 205);
            panelMain.add(label);

            label.addActionListener(e -> {
                clean(panelMain);
                gameDetails(panelMain, game);
            });

            panelMain.revalidate();
            panelMain.repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
