package view;

import core.entity.GameModel;
import core.event.GameStateListener;
import view.field.FieldWidget;

public class GamePanel implements GameStateListener {

    private final GameModel model;
    private final FieldWidget field;
    boolean _gameOver;
    int _movesCount;

    public GamePanel(GameModel model) {
        this.model = model;
        this.field = new FieldWidget(model.getGameField());

        model.registerObserver(this);
    }

    @Override
    public void knuckleMoved(boolean isGameOver, int movesCount) {
        this._gameOver = isGameOver;
        this._movesCount = movesCount;
    }
}
