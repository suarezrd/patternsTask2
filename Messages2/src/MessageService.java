import java.util.ArrayList;
import java.util.List;

public class MessageService implements Subject {
    private List<Observer> observers;

    public MessageService() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    // Método para recibir nuevos mensajes
    public void receiveMessage(String message) {
        System.out.println("Nuevo mensaje recibido: " + message);
        notifyObservers(message);
    }
}