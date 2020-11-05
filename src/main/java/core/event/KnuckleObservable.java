package core.event;

public interface KnuckleObservable {
    void registerObserver(KnuckleListener newListener);
    void removeObserver(KnuckleListener listener);
}
