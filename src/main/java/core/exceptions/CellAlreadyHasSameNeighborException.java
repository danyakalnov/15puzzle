package core.exceptions;

import core.entity.field.Cell;

public class CellAlreadyHasSameNeighborException extends IllegalArgumentException {
    public CellAlreadyHasSameNeighborException(Cell cell, Cell neighbor) {
        super(cell.toString() + " already has neighbor " + neighbor.toString());
    }
}
