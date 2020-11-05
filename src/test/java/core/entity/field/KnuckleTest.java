package core.entity.field;

import core.exceptions.CellAlreadyHasKnuckleException;
import core.util.Direction;
import core.util.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class KnuckleTest {

    @Test
    void setCellPositive() {
        Point point = new Point(0, 0);
        Cell cell = new Cell(point);

        final int knuckleNumber = 1;
        Knuckle knuckle = new Knuckle(knuckleNumber);

        boolean result = knuckle.setCell(cell);
        Cell actualCell = knuckle.getCell();

        Assertions.assertTrue(result);
        Assertions.assertEquals(cell, actualCell);
    }

    @Test
    void setCellNegative() {
        Point firstPoint = new Point(0, 0);
        Cell firstCell = new Cell(firstPoint);
        Point secondPoint = new Point(1, 1);
        Cell secondCell = new Cell(secondPoint);

        final int knuckleNumber = 1;
        Knuckle knuckle = new Knuckle(knuckleNumber);

        knuckle.setCell(firstCell);
        Cell actualCell = knuckle.getCell();
        boolean result = knuckle.setCell(secondCell);

        Assertions.assertFalse(result);
        Assertions.assertEquals(firstCell, actualCell);
    }

    @Test
    void removeCellPositive() {
        Point point = new Point(0, 0);
        Cell cell = new Cell(point);

        final int knuckleNumber = 1;
        Knuckle knuckle = new Knuckle(knuckleNumber);
        knuckle.setCell(cell);

        boolean result = knuckle.removeCell(cell);
        Cell actualCell = knuckle.getCell();

        Assertions.assertTrue(result);
        Assertions.assertNull(actualCell);
    }

    @Test
    void removeCellNegative() {
        Point firstPoint = new Point(0, 0);
        Cell firstCell = new Cell(firstPoint);
        Point secondPoint = new Point(1, 1);
        Cell secondCell = new Cell(secondPoint);

        final int knuckleNumber = 1;
        Knuckle knuckle = new Knuckle(knuckleNumber);
        knuckle.setCell(firstCell);

        Cell actualCell = knuckle.getCell();
        boolean result = knuckle.removeCell(secondCell);

        Assertions.assertFalse(result);
        Assertions.assertEquals(firstCell, actualCell);
    }

    @Test
    void canMovePositive() {
        final int[] pointCoordinates = new int[] {1, 1};
        Point targetPoint = new Point(pointCoordinates[0], pointCoordinates[1]);
        Cell targetCell = new Cell(targetPoint);

        Cell topNeighbor = new Cell(new Point(targetPoint.getX(), targetPoint.getY() - 1));
        targetCell.setNeighbor(Direction.NORTH, topNeighbor);

        final int knuckleNumber = 1;
        Knuckle knuckle = new Knuckle(knuckleNumber);

        try {
            targetCell.setKnuckle(knuckle);
        } catch (CellAlreadyHasKnuckleException exception) {
            System.out.println(exception.getMessage());
        }

        Assertions.assertEquals(topNeighbor, knuckle.canMove());
    }

    @Test
    void canMoveNegative() {
        final int[] pointCoordinates = new int[] {1, 1};
        Point targetPoint = new Point(pointCoordinates[0], pointCoordinates[1]);
        Cell targetCell = new Cell(targetPoint);

        Cell topNeighbor = new Cell(new Point(targetPoint.getX(), targetPoint.getY() - 1));
        targetCell.setNeighbor(Direction.NORTH, topNeighbor);

        final int firstKnuckleNumber = 1;
        Knuckle firstKnuckle = new Knuckle(firstKnuckleNumber);

        final int secondKnuckleNumber = 2;
        Knuckle secondKnuckle = new Knuckle(secondKnuckleNumber);

        try {
            targetCell.setKnuckle(firstKnuckle);
        } catch (CellAlreadyHasKnuckleException exception) {
            System.out.println(exception.getMessage());
        }

        try {
            topNeighbor.setKnuckle(secondKnuckle);
        } catch (CellAlreadyHasKnuckleException exception) {
            System.out.println(exception.getMessage());
        }

        Assertions.assertNull(firstKnuckle.canMove());
        Assertions.assertNull(secondKnuckle.canMove());
    }

    @Test
    void move() {
    }
}