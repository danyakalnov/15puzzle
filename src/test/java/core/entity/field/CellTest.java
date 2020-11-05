package core.entity.field;

import core.exceptions.CellAlreadyHasKnuckleException;
import core.util.Direction;
import core.util.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class CellTest {

    @Test
    void getKnuckleNumber() {
        final int knuckleNumber = 5;
        final int[] pointCoordinates = new int[] {1, 1};
        Point point = new Point(pointCoordinates[0], pointCoordinates[1]);
        Cell cell = new Cell(point);
        Knuckle knuckle = new Knuckle(knuckleNumber);
        try {
            cell.setKnuckle(knuckle);
        } catch (CellAlreadyHasKnuckleException exception) {
            System.out.println(exception.getMessage());
        }

        Assertions.assertEquals(knuckleNumber, cell.getKnuckleNumber());
    }

    @Test
    void setNeighbor() {
        final int[] pointCoordinates = new int[] {1, 1};
        Point targetPoint = new Point(pointCoordinates[0], pointCoordinates[1]);
        Cell targetCell = new Cell(targetPoint);

        Cell topNeighbor = new Cell(new Point(targetPoint.getX(), targetPoint.getY() - 1));
        targetCell.setNeighbor(Direction.NORTH, topNeighbor);

        List<Cell> expectedCellNeighbors = new ArrayList<>();
        expectedCellNeighbors.add(topNeighbor);

        List<Cell> actualCellNeighbors = targetCell.getNeighbors();

        Assertions.assertEquals(expectedCellNeighbors, actualCellNeighbors);
    }

    @Test
    void isNeighbor() {
    }

    @Test
    void oneEmptyNeighborCell() {
        final int[] pointCoordinates = new int[] {1, 1};
        Point targetPoint = new Point(pointCoordinates[0], pointCoordinates[1]);
        Cell targetCell = new Cell(targetPoint);

        List<Cell> targetCellNeighbors = new ArrayList<>();
        Cell topNeighbor = new Cell(new Point(targetPoint.getX(), targetPoint.getY() - 1));
        targetCell.setNeighbor(Direction.NORTH, topNeighbor);

        Cell bottomNeighbor = new Cell(new Point(targetPoint.getX(), targetPoint.getY() + 1));
        targetCell.setNeighbor(Direction.SOUTH, bottomNeighbor);

        Cell rightNeighbor = new Cell(new Point(targetPoint.getX() + 1, targetPoint.getY()));
        targetCell.setNeighbor(Direction.EAST, rightNeighbor);

        Cell leftNeighbor = new Cell(new Point(targetPoint.getX() - 1, targetPoint.getY()));
        targetCell.setNeighbor(Direction.WEST, leftNeighbor);

        targetCellNeighbors.add(topNeighbor);
        targetCellNeighbors.add(bottomNeighbor);
        targetCellNeighbors.add(leftNeighbor);
        targetCellNeighbors.add(rightNeighbor);

        int counter = 0;
        for ( ; counter < targetCellNeighbors.size() - 1; ++counter) {
            Cell currentCell = targetCellNeighbors.get(counter);
            try {
                currentCell.setKnuckle(new Knuckle(counter + 1));
            } catch (CellAlreadyHasKnuckleException exception) {
                System.out.println(exception.getMessage());
            }

        }

        Cell expectedEmptyCell = targetCellNeighbors.get(targetCellNeighbors.size() - 1);
        Cell actualEmptyCell = targetCell.getEmptyNeighborCell();

        Assertions.assertEquals(expectedEmptyCell.getPosition(), actualEmptyCell.getPosition());
    }

    @Test
    void noEmptyNeighborCell() {
        final int[] pointCoordinates = new int[] {1, 1};
        Point targetPoint = new Point(pointCoordinates[0], pointCoordinates[1]);
        Cell targetCell = new Cell(targetPoint);

        List<Cell> targetCellNeighbors = new ArrayList<>();
        Cell topNeighbor = new Cell(new Point(targetPoint.getX(), targetPoint.getY() - 1));
        targetCell.setNeighbor(Direction.NORTH, topNeighbor);

        Cell bottomNeighbor = new Cell(new Point(targetPoint.getX(), targetPoint.getY() + 1));
        targetCell.setNeighbor(Direction.SOUTH, bottomNeighbor);

        Cell rightNeighbor = new Cell(new Point(targetPoint.getX() + 1, targetPoint.getY()));
        targetCell.setNeighbor(Direction.EAST, rightNeighbor);

        Cell leftNeighbor = new Cell(new Point(targetPoint.getX() - 1, targetPoint.getY()));
        targetCell.setNeighbor(Direction.WEST, leftNeighbor);

        targetCellNeighbors.add(topNeighbor);
        targetCellNeighbors.add(bottomNeighbor);
        targetCellNeighbors.add(leftNeighbor);
        targetCellNeighbors.add(rightNeighbor);

        int counter = 0;
        for ( ; counter < targetCellNeighbors.size(); ++counter) {
            Cell currentCell = targetCellNeighbors.get(counter);
            try {
                currentCell.setKnuckle(new Knuckle(counter + 1));
            } catch (CellAlreadyHasKnuckleException exception) {
                System.out.println(exception.getMessage());
            }
        }

        Cell actualEmptyCell = targetCell.getEmptyNeighborCell();

        Assertions.assertEquals(null, actualEmptyCell);
    }

    @Test
    void getPosition() {
        final int[] pointCoordinates = new int[] {1, 1};
        Point point = new Point(pointCoordinates[0], pointCoordinates[1]);
        Cell cell = new Cell(point);

        Point actualCellPosition = cell.getPosition();

        Assertions.assertEquals(point, actualCellPosition);
    }

    @Test
    void setKnuckle() {
        final int knuckleNumber = 1;
        final int[] pointCoordinates = new int[] {1, 1};
        Point point = new Point(pointCoordinates[0], pointCoordinates[1]);
        Cell cell = new Cell(point);
        Knuckle knuckle = new Knuckle(knuckleNumber);

        boolean settingResult = false;
        try{
            settingResult = cell.setKnuckle(knuckle);
        } catch (CellAlreadyHasKnuckleException exception) {
            System.out.println(exception.getMessage());
        }
        Cell actualKnuckleCell = knuckle.getCell();
        int actualCellKnuckleNumber = cell.getKnuckleNumber();

        Assertions.assertEquals(true, settingResult);
        Assertions.assertEquals(cell, actualKnuckleCell);
        Assertions.assertEquals(knuckleNumber, actualCellKnuckleNumber);
    }

    @Test
    void removeKnuckle() {
        final int knuckleNumber = 1;
        final int[] pointCoordinates = new int[] {1, 1};
        Point point = new Point(pointCoordinates[0], pointCoordinates[1]);
        Cell cell = new Cell(point);
        Knuckle knuckle = new Knuckle(knuckleNumber);
        try{
            cell.setKnuckle(knuckle);
        } catch (CellAlreadyHasKnuckleException exception) {
            System.out.println(exception.getMessage());
        }

        boolean removingResult = cell.removeKnuckle(knuckle);

        int actualCellKnuckleNumber = cell.getKnuckleNumber();
        Cell actualKnuckleCell = knuckle.getCell();

        Assertions.assertEquals(true, removingResult);
        Assertions.assertEquals(0, actualCellKnuckleNumber);
        Assertions.assertEquals(null, actualKnuckleCell);
    }

    @Test
    void removeKnuckleByAnotherKnuckle() {
        final int firstKnuckleNumber = 1;
        final int secondKnuckleNumber = 2;
        final int[] pointCoordinates = new int[] {1, 1};
        Point point = new Point(pointCoordinates[0], pointCoordinates[1]);
        Cell cell = new Cell(point);
        Knuckle firstKnuckle = new Knuckle(firstKnuckleNumber);
        try{
            cell.setKnuckle(firstKnuckle);
        } catch (CellAlreadyHasKnuckleException exception) {
            System.out.println(exception.getMessage());
        }

        Knuckle secondKnuckle = new Knuckle(secondKnuckleNumber);

        boolean removingResult = cell.removeKnuckle(secondKnuckle);

        int actualCellKnuckleNumber = cell.getKnuckleNumber();
        Cell actualKnuckleCell = firstKnuckle.getCell();

        Assertions.assertEquals(false, removingResult);
        Assertions.assertEquals(firstKnuckleNumber, actualCellKnuckleNumber);
        Assertions.assertEquals(cell, actualKnuckleCell);
    }

    @Test
    void isEmptyIfContainsKnuckle() {
        final int[] pointCoordinates = new int[] {1, 1};
        final int knuckleNumber = 1;
        Point point = new Point(pointCoordinates[0], pointCoordinates[1]);
        Cell cell = new Cell(point);
        Knuckle knuckle = new Knuckle(knuckleNumber);
        try {
            cell.setKnuckle(knuckle);
        } catch (CellAlreadyHasKnuckleException exception) {
            System.out.println(exception.getMessage());
        }

        Assertions.assertEquals(false, cell.isEmpty());
    }

    @Test
    void isEmptyIfDoesNotContainKnuckle() {
        final int[] pointCoordinates = new int[] {1, 1};
        Point point = new Point(pointCoordinates[0], pointCoordinates[1]);
        Cell cell = new Cell(point);

        Assertions.assertEquals(true, cell.isEmpty());
    }
}