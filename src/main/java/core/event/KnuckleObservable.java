package core.event;

public interface KnuckleObservable {
    void registerObserver(KnuckleListener listener);
    void removeObserver(KnuckleListener listener);
}
