package view.field;

import core.entity.field.Cell;
import core.entity.field.GameField;
import core.entity.field.Knuckle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FieldWidget extends JPanel {
    private GameField field;
    private List<CellWidget> cells;

    private final static Color BACKGROUND_COLOR = new Color(11, 72, 163);
    private final static Dimension CELL_DIMENSION = new Dimension(110, 110);
    private final static int GAP = 5;

    public FieldWidget(GameField gameField) {
        this.field = gameField;
        int fieldSize = gameField.size();
        setPreferredSize(new Dimension(CELL_DIMENSION.width * fieldSize + GAP * (fieldSize - 1), CELL_DIMENSION.width * fieldSize + GAP * (fieldSize - 1)));
        setLayout(new GridLayout(fieldSize, fieldSize, GAP, GAP));
        setBackground(BACKGROUND_COLOR);
        for(CellWidget cellWidget : createCells())
            add(cellWidget);
    }

    private List<CellWidget> createCells() {
        cells = new ArrayList<>();

        for(Cell cell : this.field.getCells()) {
            CellWidget cellWidget = new CellWidget(cell, CELL_DIMENSION, BACKGROUND_COLOR);
            cells.add(cellWidget);
        }

        return cells;
    }

    public void moveKnuckle(Knuckle knuckle, Cell newPosition) {
        CellWidget previousCell = cells.stream().filter(cellWidget -> cellWidget.getKnuckleNumber() == knuckle.getNumber()).findFirst().get();
        KnuckleWidget knuckleWidget = previousCell.removeKnuckle();

        CellWidget newCell = cells.stream().filter(cellWidget -> cellWidget.getCell() == newPosition).findFirst().get();
        KnuckleWidget nullKnuckle = newCell.removeKnuckle(); // Из старой клетки надо удалить нулевую костяшку перед установкой новой!
        newCell.setKnuckle(knuckleWidget);
        previousCell.setKnuckle(nullKnuckle); // В старую клетку надо добавить нулевую костяшку!
    }
}
