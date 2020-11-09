package core.entity.field;

import core.exceptions.CellAlreadyHasKnuckleException;
import core.exceptions.CellAlreadyHasSameNeighborException;
import core.util.Direction;
import core.util.Point;
import core.exceptions.CellAlreadyHasNeighborInDirectionException;
import java.util.*;

public class Cell {
    private Point _point;
    private Knuckle _knuckle;
    private Map<Direction, Cell> _neighbors = new EnumMap<Direction, Cell>(Direction.class);

    public Cell(Point point) {
        this._point = point;
    }

    public Knuckle getKnuckle() {
        return _knuckle;
    }

    public int getKnuckleNumber() {
        if (this._knuckle != null) return _knuckle.getNumber();
        return 0;
    }

    protected Cell getEmptyNeighborCell() {
        for (Cell neighborCell: _neighbors.values()) {
            if (neighborCell.isEmpty()) return neighborCell;
        }

        return null;
    }

    public List<Cell> getNeighbors() {
        return Collections.unmodifiableList(new ArrayList<>(this._neighbors.values()));
    }

    public Point getPosition() {
        return this._point;
    }

    protected boolean setKnuckle(Knuckle knuckle) throws CellAlreadyHasKnuckleException {
        boolean result = false;

        if (this._knuckle == null) {
            this._knuckle = knuckle;
            result = knuckle.setCell(this);
        } else throw new CellAlreadyHasKnuckleException(this);

        return result;
    }

    protected boolean removeKnuckle(Knuckle knuckle) {
        boolean result = false;
        if (this._knuckle.equals(knuckle)/*&& this._knuckle == knuckle*/) {
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
        return this._neighbors.containsValue(other);
    }

    protected void setNeighbor(Direction direction, Cell newNeighbor) {
        if (_neighbors.containsKey(direction) && _neighbors.containsValue(newNeighbor))
            return;
        if (_neighbors.containsKey(direction))
            throw new CellAlreadyHasNeighborInDirectionException(direction, this, newNeighbor);
        if (_neighbors.containsValue(newNeighbor) && !_neighbors.containsKey(direction))
            throw new CellAlreadyHasSameNeighborException(this, newNeighbor);
        if (this != newNeighbor && !isNeighbor(newNeighbor)) {
            this._neighbors.put(direction, newNeighbor);
            newNeighbor.setNeighbor(direction.getOpposite(), this);
        }
    }

    @Override
    public String toString() {
        return "Клетка " + this._point.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Cell) {
            Cell otherCell = (Cell)other;
            return this._point.equals(otherCell._point);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this._point.hashCode();
    }
}
