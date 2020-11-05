package core.entity.field;

import core.util.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void canMove() {
    }

    @Test
    void move() {
    }
}