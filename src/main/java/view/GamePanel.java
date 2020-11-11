package view;

import core.entity.GameModel;
import core.entity.field.Cell;
import core.entity.field.Knuckle;
import core.event.GameStateListener;
import view.field.FieldWidget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class GamePanel extends JFrame implements GameStateListener {

    private final FieldWidget field;
    private final GameInfoWidget gameInfo;
    boolean _gameOver;
    int _movesCount;

    public GamePanel(GameModel model) {
        this.field = new FieldWidget(model.getGameField());
        this.gameInfo = new GameInfoWidget();
        model.registerObserver(this);

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        JPanel contentPanel = (JPanel)this.getContentPane();
        contentPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        contentPanel.setBackground(new Color(11, 72, 163));
        contentPanel.add(field);
        contentPanel.add(gameInfo);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Пятнашки");
    }

    @Override
    public void knuckleMoved(boolean isGameOver, int movesCount, Knuckle movedKnuckle, Cell newPosition) {
        this._gameOver = isGameOver;
        this._movesCount = movesCount;
        this.gameInfo.updateMovesCount(movesCount);
        field.moveKnuckle(movedKnuckle, newPosition);
        repaint();

        if (isGameOver) {
            JOptionPane.showMessageDialog(this, "Поздравляем! Вы решили головоломку", "Игра окончена", JOptionPane.PLAIN_MESSAGE);
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }
}
