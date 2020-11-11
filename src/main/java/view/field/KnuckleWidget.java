package view.field;

import core.entity.field.Knuckle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KnuckleWidget extends JPanel {

    private static final Color KNUCKLE_COLOR = new Color(222, 225, 227);
    private final Dimension dimension;
    private Knuckle knuckle;

    public KnuckleWidget(Knuckle knuckle, Dimension dimension) {
        this.dimension = dimension;
        setPreferredSize(dimension);
        this.knuckle = knuckle;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (knuckle != null)
                    knuckle.move();
            }
        });
    }

    public int getKnuckleNumber() {
        if (knuckle != null)
            return knuckle.getNumber();
        return 0;
    }

    private void drawKnuckleValue(String text, int width, int height, Graphics g) {
        FontMetrics fontMetrics = g.getFontMetrics();
        int x = (width - fontMetrics.stringWidth(text)) / 2;
        int y = (fontMetrics.getAscent() + (height - (fontMetrics.getAscent() + fontMetrics.getDescent())) / 2);
        g.drawString(text, x, y);
    }

    @Override
    protected void paintComponent(Graphics graphics){
        Font f = new Font("Arial", Font.BOLD, 35);
        if(this.knuckle != null) {
            Graphics2D g = (Graphics2D)graphics;
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.BLACK);
            g.fillRoundRect(0, 0, this.dimension.width, this.dimension.height, 20, 20);
            g.setColor(KNUCKLE_COLOR);
            g.fillRoundRect(2, 2, this.dimension.width - 4, this.dimension.height - 4, 20, 20);
            g.setFont(f);
            g.setColor(Color.BLACK);
            drawKnuckleValue(Integer.toString(this.knuckle.getNumber()), this.dimension.width, this.dimension.height, g);
        }
    }
}
