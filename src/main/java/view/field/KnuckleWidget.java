package view.field;

import core.entity.field.Knuckle;

import javax.swing.*;
import java.awt.*;

public class KnuckleWidget extends JButton {

    private Knuckle knuckle;

    public KnuckleWidget(Knuckle knuckle) {
        this.knuckle = knuckle;
        this.addActionListener(e -> knuckle.move());
    }

    @Override
    protected void paintComponent(Graphics g){
        g.drawString(Integer.toString(this.knuckle.getNumber()), this.getSize().width / 2, this.getSize().height / 2);

    }
}
