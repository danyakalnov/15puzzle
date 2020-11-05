package core.exceptions;

import core.entity.field.Cell;

public class CellAlreadyHasKnuckleException extends Exception {
    public CellAlreadyHasKnuckleException(Cell cell) {
        super(cell.toString() + " already has knuckle");
    }
}
