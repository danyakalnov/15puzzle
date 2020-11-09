import core.entity.GameModel;
import view.GamePanel;

import javax.swing.*;

public class GameOfFifteen {
    public static void main(String[] args) {
        GameModel model = new GameModel();
        model.start();

        SwingUtilities.invokeLater(() -> new GamePanel(model));
    }
}
