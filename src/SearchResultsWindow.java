import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;


public class SearchResultsWindow {
    private JFrame frame;
    private JPanel panel;

    public SearchResultsWindow(List<Game> games) {
        frame = new JFrame("Search Results");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        frame.add(scrollPane, BorderLayout.CENTER);
        panel.setBackground(new Color(16, 11, 37));

        for (Game game : games) {
            addGameButton(game);
        }

        frame.setVisible(true);
    }

    private void addGameButton(Game game) {
        try {
            URL url = new URL(game.getImage());
            BufferedImage image = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(image.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
            JButton button = new JButton(game.getTitle(), icon);
            button.setHorizontalAlignment(SwingConstants.LEFT);
            button.setBackground(new Color(16, 11, 37));
            button.setForeground(Color.WHITE);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.addActionListener(e -> {

            });
            panel.add(button);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}