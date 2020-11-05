package core.entity.field;

import core.util.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

class GameFieldTest {
    @Test
    void reset() {
        final int size = 4;
        GameField testField = new GameField(size);

        testField.reset();

        List<Cell> cells = testField.getCells();
        cells.forEach(cell -> {
            Assertions.assertEquals(0, cell.getKnuckleNumber());
        });
    }

    @Test
    void isConstructionValid() {
        final int size = 4;
        GameField testField = new GameField(size);
        List<Cell> cells = testField.getCells();

        List<Integer> knucklesInOrder = new ArrayList<>();
        for (int i = 1; i < size * size; ++i) {
            knucklesInOrder.add(i);
        }
        knucklesInOrder.add(0);

        List<Integer> knucklesOnField = new ArrayList<>();
        cells.forEach(cell -> {
            knucklesOnField.add(cell.getKnuckleNumber());
        });

        Assertions.assertEquals(knucklesInOrder, knucklesOnField);
    }

    @Test
    void areNeighborsValidForCenterCell() {
        final int size = 4;
        GameField testField = new GameField(size);

        Point targetPoint = new Point(size / 2, size / 2);
        Cell centerCell = testField.getCellByPoint(targetPoint);

        /* Задаём список ожидаемых соседей */
        Set<Point> expectedNeighborsPositions = new HashSet<>();

        Point leftNeighborPoint = new Point(targetPoint.getX() - 1, targetPoint.getY());
        Point rightNeighborPoint = new Point(targetPoint.getX() + 1, targetPoint.getY());
        Point topNeighborPoint = new Point(targetPoint.getX(), targetPoint.getY() - 1);
        Point bottomNeighborPoint = new Point(targetPoint.getX(), targetPoint.getY() + 1);

        expectedNeighborsPositions.add(leftNeighborPoint);
        expectedNeighborsPositions.add(rightNeighborPoint);
        expectedNeighborsPositions.add(topNeighborPoint);
        expectedNeighborsPositions.add(bottomNeighborPoint);

        /* Получаем список реальных соседей клетки */
        Set<Point> realNeighborsPositions = new HashSet<>();
        List<Cell> realNeighbors = centerCell.getNeighbors();
        realNeighbors.forEach(cell -> {
            realNeighborsPositions.add(cell.getPosition());
        });

        Assertions.assertEquals(expectedNeighborsPositions, realNeighborsPositions);
    }

    @Test
    void areNeighborsValidForCornerCell() {
        final int size = 4;
        GameField testField = new GameField(size);

        Point targetPoint = new Point(0, 0);
        Cell topLeftCell = testField.getCellByPoint(targetPoint);

        /* Задаём список ожидаемых соседей */
        Set<Point> expectedNeighborsPositions = new HashSet<>();
        Point rightNeighbor = new Point(targetPoint.getX() + 1, targetPoint.getY());
        Point bottomNeighbor = new Point(targetPoint.getX(), targetPoint.getY() + 1);

        expectedNeighborsPositions.add(rightNeighbor);
        expectedNeighborsPositions.add(bottomNeighbor);

        /* Получаем список реальных соседей клетки */
        Set<Point> realNeighborsPositions = new HashSet<>();
        List<Cell> realNeighbors = topLeftCell.getNeighbors();
        realNeighbors.forEach(cell -> {
            realNeighborsPositions.add(cell.getPosition());
        });

        Assertions.assertEquals(expectedNeighborsPositions, realNeighborsPositions);
    }

    @Test
    void areNeighborsValidForBottomCell() {
        final int size = 4;
        GameField testField = new GameField(size);

        Point targetPoint = new Point(1, size - 1);
        Cell bottomCell = testField.getCellByPoint(targetPoint);

        /* Задаём список ожидаемых соседей */
        Set<Point> expectedNeighborsPositions = new HashSet<>();
        Point rightNeighbor = new Point(targetPoint.getX() + 1, targetPoint.getY());
        Point leftNeighbor = new Point(targetPoint.getX() - 1, targetPoint.getY());
        Point topNeighbor = new Point(targetPoint.getX(), targetPoint.getY() - 1);

        expectedNeighborsPositions.add(rightNeighbor);
        expectedNeighborsPositions.add(leftNeighbor);
        expectedNeighborsPositions.add(topNeighbor);

        /* Получаем список реальных соседей клетки */
        Set<Point> realNeighborsPositions = new HashSet<>();
        List<Cell> realNeighbors = bottomCell.getNeighbors();
        realNeighbors.forEach(cell -> {
            realNeighborsPositions.add(cell.getPosition());
        });

        Assertions.assertEquals(expectedNeighborsPositions, realNeighborsPositions);
    }

    @Test
    void areNeighborsValidForRightCell() {
        final int size = 4;
        GameField testField = new GameField(size);

        Point targetPoint = new Point(size - 1, 1);
        Cell rightCell = testField.getCellByPoint(targetPoint);

        /* Задаём список ожидаемых соседей */
        Set<Point> expectedNeighborsPositions = new HashSet<>();
        Point bottomNeighbor = new Point(targetPoint.getX(), targetPoint.getY() + 1);
        Point leftNeighbor = new Point(targetPoint.getX() -1, targetPoint.getY());
        Point topNeighbor = new Point(targetPoint.getX(), targetPoint.getY() - 1);

        expectedNeighborsPositions.add(bottomNeighbor);
        expectedNeighborsPositions.add(leftNeighbor);
        expectedNeighborsPositions.add(topNeighbor);

        /* Получаем список реальных соседей клетки */
        Set<Point> realNeighborsPositions = new HashSet<>();
        List<Cell> realNeighbors = rightCell.getNeighbors();
        realNeighbors.forEach(cell -> {
            realNeighborsPositions.add(cell.getPosition());
        });

        Assertions.assertEquals(expectedNeighborsPositions, realNeighborsPositions);
    }

    @Test
    void shuffle() {
        final int size = 4;
        GameField testField = new GameField(size);

        List<Integer> knucklesArrangement1 = testField.shuffle();
        List<Integer> knucklesArrangement2 = testField.shuffle();

        Assertions.assertNotEquals(knucklesArrangement1, knucklesArrangement2);
    }
}