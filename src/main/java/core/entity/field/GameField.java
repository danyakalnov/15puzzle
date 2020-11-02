package core.entity.field;

import core.util.Point;

import java.util.*;

public class GameField {
    private int _size; // Размеры коробки
    private int _nbKnuckles;

    private List<Cell> _cells;
    private List<Knuckle> _knuckles;

    private static final Random RANDOM = new Random();

    private void setSize(int size) {
        this._size = size;
        this._nbKnuckles = size * size - 1;
    }

    public GameField(int size) {
        setSize(size); // Задаём полю размеры

        /* Создаём клетки и помещаем их на поле; в клетки
        * устанавливаем костяшки в первоначальном порядке */
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Cell newCell = new Cell(new Point(row, col));
                int newKnuckleNumber = row * size + col + 1; // Получаем очередной номер для костяшки
                if (newKnuckleNumber != size * size) {
                    Knuckle newKnuckle = new Knuckle(newKnuckleNumber);
                    this._knuckles.add(newKnuckle);
                    newCell.setKnuckle(newKnuckle);
                }
                this._cells.add(newCell);
            }
        }
    }

    public int size() {
        return _size;
    }

    public List<Cell> getCells() {
        return Collections.unmodifiableList(this._cells);
    }

    /**
     * Расстановка костяшек в первоначальную позицию
     */
    public void reset() {
        /* Алгоритм: убрать все костяшки из их текуших
         * клеток и поместить в клетки по порядку */
        this._knuckles.forEach(knuckle -> {
            knuckle.getCell().removeKnuckle(knuckle);
        });

        int counter = 0;
        for ( ; counter < _nbKnuckles; counter++) {
            this._cells.get(counter).setKnuckle(_knuckles.get(counter));
        }
    }

    public void shuffle() {

    }
}
