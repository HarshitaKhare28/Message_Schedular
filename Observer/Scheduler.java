package Observer;
import java.util.ArrayList;
import java.util.List;

import Builder.Message;

public class Scheduler {
    private List<MessageSenderObserver> observers = new ArrayList<>();

    public void addObserver(MessageSenderObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(MessageSenderObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Message message) {
        for (MessageSenderObserver observer : observers) {
            observer.update(message);
        }
    }

    public void scheduleMessage(Message message) {
        // Logic to schedule message
        System.out.println("Scheduling message: " + message.getContent());
        notifyObservers(message);  // Notify all observers when it's time
    }
}
