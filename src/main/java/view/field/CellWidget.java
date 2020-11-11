package view.field;

import core.entity.field.Cell;

import javax.swing.*;
import java.awt.*;

public class CellWidget extends JPanel {
    private Cell cell;
    private KnuckleWidget knuckle;
    private Dimension dimension;

    CellWidget(Cell cell, Dimension dim, Color color) {
        super();
        this.cell = cell;
        this.dimension = dim;
        setPreferredSize(dim);
        setLayout(null);
        setBackground(color);
        this.knuckle = new KnuckleWidget(cell.getKnuckle());
        knuckle.setBounds(0, 0, dim.width, dim.height);
        add(knuckle);
    }

    protected int getKnuckleNumber() {
        return knuckle.getKnuckleNumber();
    }

    protected KnuckleWidget removeKnuckle() {
        remove(knuckle);
        repaint();
        return knuckle;
    }

    protected Cell getCell() {
        return this.cell;
    }

    protected void setKnuckle(KnuckleWidget knuckle) {
        this.knuckle = knuckle;
        add(knuckle);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
