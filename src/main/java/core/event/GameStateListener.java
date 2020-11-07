package core.event;

public interface GameStateListener {
    void knuckleMoved(boolean isGameOver, int movesCount);
}
