package core.event;

import core.entity.field.Cell;
import core.entity.field.Knuckle;

public interface GameStateListener {
    void knuckleMoved(boolean isGameOver, int movesCount, Knuckle movedKnuckle, Cell newKnucklePosition);
}
