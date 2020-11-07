package view.field;

import core.entity.field.Cell;

import javax.swing.*;

public class CellWidget extends JPanel {
    private Cell cell;
    private KnuckleWidget knuckle;

    CellWidget(Cell cell) {
        this.cell = cell;

        this.knuckle = new KnuckleWidget(cell.getKnuckle());
        add(knuckle);
    }
}
