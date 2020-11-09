package view;

import javax.swing.*;
import java.awt.*;

public class GameInfoWidget extends JPanel {
    private int _movesCount;
    private final static Color TEXT_COLOR = new Color(222, 225, 227);

    public GameInfoWidget() {
        _movesCount = 0;
        setPreferredSize(new Dimension(200, 50));
    }

    public void updateMovesCount(int movesCount) {
        this._movesCount = movesCount;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D)graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font f = new Font("SansSerif", Font.BOLD, 25);
        g.setFont(f);
        g.setColor(TEXT_COLOR);
        g.drawString("Ходов: " + this._movesCount, 50, 35);
    }
}
