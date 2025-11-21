package Model.Forum;

public interface Sujet {
    void addObserver(Observateur o);
    void removeObserver(Observateur o);
    void notifyObservers(Message message);
}
