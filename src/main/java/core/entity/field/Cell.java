package core.entity.field;

import java.util.List;

import core.util.Point;

public class Cell {
    private Point _point;
    private Knuckle _knuckle = null;
    private List<Cell> _neighbors;

    public Cell(Point point) {
        this._point = point;
    }

    public int getKnuckleNumber() {
        return _knuckle.getNumber();
    }

    protected Cell getEmptyNeighborCell() {
        for (Cell currentCell: _neighbors) {
            if (currentCell.isEmpty()) return currentCell;
        }

        return null;
    }

    protected boolean setKnuckle(Knuckle knuckle) {
        boolean result = false;

        if (this._knuckle == null) {
            this._knuckle = knuckle;
            result = knuckle.setCell(this);
        }

        return result;
    }

    protected boolean removeKnuckle(Knuckle knuckle) {
        boolean result = false;
        if (this.equals(knuckle)/*&& this._knuckle == knuckle*/) {
            this._knuckle.removeCell(this);
            this._knuckle = null;
            result = true;
        }

        return result;
    }

    public boolean isEmpty() {
        return this._knuckle == null;
    }

    public boolean isNeighbor(Cell other) {
        return this._neighbors.contains(other);
    }

    private void setNeighbor(Cell newNeighbor) {
        if (this != newNeighbor && !isNeighbor(newNeighbor)) {
            this._neighbors.add(newNeighbor);
            newNeighbor.setNeighbor(this);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Cell) {
            Cell otherCell = (Cell)other;
            return this._point.equals(otherCell._point);
        }

        return false;
    }
}
