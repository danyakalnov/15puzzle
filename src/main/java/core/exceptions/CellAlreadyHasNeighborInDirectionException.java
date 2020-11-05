package core.exceptions;

import core.entity.field.Cell;
import core.util.Direction;

public class CellAlreadyHasNeighborInDirectionException extends IllegalArgumentException {
    public CellAlreadyHasNeighborInDirectionException(Direction dir, Cell cell, Cell neighbor) {
        super(cell.toString() + " already has neighbor in " + dir.toString() + " direction");
        System.out.println("Cell trying to neighbor: " + neighbor.toString());
    }
}
