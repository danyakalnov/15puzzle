package core.entity;

import core.entity.field.Cell;
import core.entity.field.GameField;
import core.entity.field.Knuckle;
import java.util.List;
import java.util.ArrayList;

public class GameModel {
    private boolean _gameOver;
    private final int _fieldSize = 4;
    private GameField _field;

    public void start() {
        this._gameOver = false;
        generateGameField();
        List<Cell> cellsArrangement;
        do {
            cellsArrangement = this._field.shuffle();
        } while (!isSolvable(cellsArrangement));
    }

    private void generateGameField() {
        this._field = new GameField(_fieldSize);
    }

    private boolean isSolvable(List<Cell> cellsArrangement) {
        int inversionsCount = 0;
        for (int i = 0; i < _fieldSize * _fieldSize; ++i) {
            int currentKnuckleNumber = cellsArrangement.get(i).getKnuckleNumber();
            if (currentKnuckleNumber != 0)
                for (int j = 0; j < i; ++j) {
                    int precededKnuckleNumber = cellsArrangement.get(j).getKnuckleNumber();
                    if (precededKnuckleNumber > currentKnuckleNumber) ++inversionsCount;
                }
        }

        for (int i = 0; i < _fieldSize * _fieldSize; ++i) {
            int currentKnuckleNumber = cellsArrangement.get(i).getKnuckleNumber();
            if (currentKnuckleNumber == 0) inversionsCount += 1 + i / _fieldSize; // TODO: возможно, прибавление 1 влечёт за собой ошибку! Обратить на это внимание!!!
        }

        return inversionsCount % 2 == 0;
    }

    private boolean areKnucklesInOrder() {
        List<Integer> correctKnucklesOrder = new ArrayList<Integer>();

        for (int i = 1; i < _fieldSize * _fieldSize; i++) {
            correctKnucklesOrder.add(i);
        }

        List<Cell> cellList = this._field.getCells();
        List<Integer> knucklesNumbers = new ArrayList<Integer>();
        cellList.forEach(cell -> {
            knucklesNumbers.add(cell.getKnuckleNumber());
        });

        return correctKnucklesOrder.equals(knucklesNumbers);
    }
}
