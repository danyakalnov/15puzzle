package core.entity;

import core.entity.field.Cell;
import core.entity.field.GameField;
import core.event.GameStateListener;
import core.event.KnuckleListener;
import core.event.KnuckleObservable;
import core.event.ModelObservable;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class GameModel implements KnuckleListener, ModelObservable {
    private boolean _gameOver;
    private int _movesCount;
    private final int _fieldSize = 4;
    private GameField _field;
    private List<GameStateListener> _listeners;

    public GameModel() {
        this._listeners = new ArrayList<>();
    }

    public void start() {
        this._gameOver = false;
        this._movesCount = 0;
        generateGameField();
        List<Integer> knucklesArrangement;
        do {
            knucklesArrangement = this._field.shuffle();
        } while (!isSolvable(knucklesArrangement));
    }

    private void generateGameField() {
        this._field = new GameField(_fieldSize);
        List<KnuckleObservable> knucklesList = this._field.getCells().stream().map(Cell::getKnuckle).collect(Collectors.toList());
        for (KnuckleObservable knuckle : knucklesList) {
            knuckle.registerObserver(this);
        }
    }

    private boolean isSolvable(List<Integer> knucklesArrangement) {
        int inversionsCount = 0;
        for (int i = 0; i < _fieldSize * _fieldSize; ++i) {
            int currentKnuckleNumber = knucklesArrangement.get(i);
            if (currentKnuckleNumber != 0)
                for (int j = 0; j < i; ++j) {
                    int precededKnuckleNumber = knucklesArrangement.get(j);
                    if (precededKnuckleNumber > currentKnuckleNumber) ++inversionsCount;
                }
        }

        for (int i = 0; i < _fieldSize * _fieldSize; ++i) {
            int currentKnuckleNumber = knucklesArrangement.get(i);
            if (currentKnuckleNumber == 0) inversionsCount += 1 + i / _fieldSize; // TODO: возможно, прибавление 1 влечёт за собой ошибку! Обратить на это внимание!!!
        }

        return inversionsCount % 2 == 0;
    }

    private boolean areKnucklesInOrder() {
        List<Integer> correctKnucklesOrder = new ArrayList<Integer>();

        for (int i = 1; i < _fieldSize * _fieldSize; i++) {
            correctKnucklesOrder.add(i);
        }

        List<Cell> cellList = this._field.getCells();
        List<Integer> knucklesNumbers = new ArrayList<Integer>();
        cellList.forEach(cell -> {
            knucklesNumbers.add(cell.getKnuckleNumber());
        });

        return correctKnucklesOrder.equals(knucklesNumbers);
    }

    public GameField getGameField() {
        return this._field;
    }

    private void notifyObservers(boolean isGameOver, int movesCount) {
        for (GameStateListener listener : this._listeners)
            listener.knuckleMoved(isGameOver, movesCount);
    }

    @Override
    public void knuckleMoved() {
        this._movesCount += 1;
        this._gameOver = areKnucklesInOrder();
        notifyObservers(this._gameOver, this._movesCount);
    }

    @Override
    public void registerObserver(GameStateListener listener) {
        if (listener != null && !_listeners.contains(listener))
            this._listeners.add(listener);
    }

    @Override
    public void removeObserver(GameStateListener listener) {
        if (listener != null && _listeners.contains(listener))
            this._listeners.remove(listener);
    }
}
