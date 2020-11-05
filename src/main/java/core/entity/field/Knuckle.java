package core.entity.field;

import core.exceptions.CellAlreadyHasKnuckleException;

public class Knuckle {
    private int _number;
    private Cell _position;

    public Knuckle(int number) {
        this._number = number;
    }

    public int getNumber() {
        return this._number;
    }

    protected Cell getCell() {
        return this._position;
    }

    protected boolean setCell(Cell newCell) {
        boolean result = false;

        if (this._position == null) {
            this._position = newCell;
            result = true;
        }

        return result;
    }

    protected boolean removeCell(Cell cell) {
        boolean result = false;

        if (/*this._position == cell &&*/ this._position.equals(cell)) {
            this._position = null;
            result = true;
        }

        return result;
    }

    public Cell canMove() {
        return this._position.getEmptyNeighborCell();
    }

    public Cell move() {
        Cell emptyCell = canMove();
        if (emptyCell != null) {
            this._position.removeKnuckle(this);
            try {
                if (emptyCell.setKnuckle(this)) return emptyCell;
            } catch (CellAlreadyHasKnuckleException exception) {
                System.out.println(exception.getMessage());
            }
        }

        return null;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Knuckle) {
            Knuckle otherKnuckle = (Knuckle)other;
            return this._number == otherKnuckle._number; // Возвращаем результат сравнения номеров костяшек
        }

        return false;
    }

    @Override
    public String toString() {
        return "Костяшка №" + Integer.toString(this._number);
    }
}
