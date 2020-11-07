package core.entity.field;

import core.exceptions.CellAlreadyHasKnuckleException;
import core.util.Direction;
import core.util.Point;
import java.util.*;
import java.util.stream.Collectors;

public class GameField {
    private int _size; // Размеры коробки
    private int _nbKnuckles;

    private static final Random RANDOM = new Random();

    private Map<Point, Cell> _cells;
    private List<Knuckle> _knuckles;

    private void setSize(int size) {
        this._size = size;
        this._nbKnuckles = size * size - 1;
    }

    private void setup(int size) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Point newCellPoint = new Point(col, row); // x - колонка, y - строка
                Cell newCell = new Cell(newCellPoint);

                if (row > 0) {
                    Cell northCell = getCellByPoint(new Point(col, row - 1));
                    northCell.setNeighbor(Direction.SOUTH, newCell);
                }

                if (col > 0) {
                    Cell westCell = getCellByPoint(new Point(col - 1, row));
                    westCell.setNeighbor(Direction.EAST, newCell);
                }

                int newKnuckleNumber = row * size + col + 1; // Получаем очередной номер для костяшки (также с номером 0)
                if (newKnuckleNumber != size * size) {
                    Knuckle newKnuckle = new Knuckle(newKnuckleNumber);
                    this._knuckles.add(newKnuckle);
                    try {
                        newCell.setKnuckle(newKnuckle);
                    } catch (CellAlreadyHasKnuckleException exception) {
                        System.out.println(exception.getMessage());
                    }
                }
                this._cells.put(newCellPoint, newCell);
            }
        }
        this._knuckles.add(null); /* Добавляем пустую костяшку (нужна для перемешивания костяшек) */
    }

    public GameField(int size) {
        this._cells = new TreeMap<>((o1, o2) -> {
            if (o1.getY() > o2.getY())
                return 1;
            else if (o1.getY() == o2.getY())
                return o1.getX() - o2.getX();
            else return -1;
        });
        this._knuckles = new ArrayList<>();

        setSize(size); /* Задаём полю размеры */

        /* Создаём клетки и помещаем их на поле; в клетки
         * устанавливаем костяшки в первоначальном порядке */
        setup(size);
    }

    public int size() {
        return _size;
    }

    /**
     * Получение списка клеток
     * @return список клеток в порядке возрастания (сверху-вниз, слева-направо)
     */
    public List<Cell> getCells() {
        return Collections.unmodifiableList(new ArrayList<>(this._cells.values()));
    }

    protected Cell getCellByPoint(Point point) {
        return this._cells.get(point);
    }

    /**
     * Расстановка костяшек в первоначальную позицию
     */
    public void reset() {
        /* Алгоритм: убрать все костяшки из их текуших
         * клеток и поместить в клетки по порядку */
        this._knuckles.forEach(knuckle -> {
            if (knuckle != null) knuckle.getCell().removeKnuckle(knuckle);
        });
    }

    public List<Integer> shuffle() {
        /* Метод решения: перемешивать сами костяшки (не установлены на поле),
        * на поле поставить такую расстановку костяшек, которая решаема */
        reset();
        Collections.shuffle(this._knuckles, RANDOM);
        int counter = 0;
        List<Cell> cellsList = this.getCells();
        for ( ; counter <= _nbKnuckles; counter++) {
            Cell currentCell = cellsList.get(counter);
            Knuckle currentKnuckle = this._knuckles.get(counter);
            if (currentKnuckle != null)
                try {
                    currentCell.setKnuckle(currentKnuckle);
                } catch (CellAlreadyHasKnuckleException exception) {
                    System.out.println(exception.getMessage());
                }
        }

        return Collections.unmodifiableList(this._knuckles.stream()
                .map(knuckle -> Optional.ofNullable(knuckle).map(Knuckle::getNumber).orElse(0))
                .collect(Collectors.toList()));
    }
}
