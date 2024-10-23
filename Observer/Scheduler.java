package Observer;

import Builder.Message;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<WhatsAppObserver> whatsAppObservers = new ArrayList<>();
    private List<TelegramObserver> telegramObservers = new ArrayList<>();
    private List<EmailObserver> emailObservers = new ArrayList<>();

    // Method to add WhatsAppObserver
    public void addObserver(WhatsAppObserver observer) {
        whatsAppObservers.add(observer);
    }

    // Method to add TelegramObserver
    public void addObserver(TelegramObserver observer) {
        telegramObservers.add(observer);
    }

    // Method to add EmailObserver
    public void addObserver(EmailObserver observer) {
        emailObservers.add(observer);
    }

    public void notifyObservers(Message message) {
        // Notify WhatsApp observers
        for (WhatsAppObserver observer : whatsAppObservers) {
            observer.update(message);
        }
        // Notify Telegram observers
        for (TelegramObserver observer : telegramObservers) {
            observer.update(message);
        }
        // Notify Email observers
        for (EmailObserver observer : emailObservers) {
            observer.update(message);
        }
    }

    public void scheduleMessage(Message message) {
        // Implement scheduling logic here
        long delay = message.getScheduledTime() != null ?
                java.time.Duration.between(LocalDateTime.now(), message.getScheduledTime()).toMillis() :
                0;
        System.out.println("Scheduling message: " + message.getBody() + " to be sent in " + delay + " milliseconds.");
        // Call notifyObservers
        notifyObservers(message);
    }
}
