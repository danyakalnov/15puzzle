package core.entity.field;

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
            this._position = null;
            if (emptyCell.setKnuckle(this)) return emptyCell;
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
}
