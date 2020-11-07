package view.field;

import core.entity.field.Cell;
import core.entity.field.GameField;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FieldWidget extends JPanel {
    private GameField field;
    private List<CellWidget> cells;

    private final static int CELL_SIZE = 100;

    public FieldWidget(GameField gameField) {
        this.field = gameField;
        int fieldSize = gameField.size();
        setPreferredSize(new Dimension(CELL_SIZE * fieldSize, CELL_SIZE * fieldSize));
        setLayout(new GridLayout(fieldSize, fieldSize));

        for(CellWidget cellWidget : createCells())
            add(cellWidget);
    }

    private List<CellWidget> createCells() {
        cells = new ArrayList<>();

        for(Cell cell : this.field.getCells()) {
            CellWidget cellWidget = new CellWidget(cell);
            cells.add(cellWidget);
        }

        return cells;
    }
}
