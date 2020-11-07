package core.event;

public interface ModelObservable {
    void registerObserver(GameStateListener listener);
    void removeObserver(GameStateListener listener);
}
