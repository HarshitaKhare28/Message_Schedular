import Builder.Message;
import Observer.EmailObserver;
import Observer.Scheduler;
import Observer.TelegramObserver;
import Observer.WhatsAppObserver;
import Singleton.SingletonScheduler;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // Creating observers
        Scheduler scheduler = new Scheduler();
        scheduler.addObserver(new WhatsAppObserver());
        scheduler.addObserver(new TelegramObserver());
        scheduler.addObserver(new EmailObserver());

        // Creating messages using Builder pattern
        Message whatsappMessage = new Message.Builder("1234567890", "Hello WhatsApp!", "whatsapp").build();

        // Schedule an email message
        LocalDateTime scheduledTime = LocalDateTime.now().plusSeconds(10); 
        Message emailMessage = new Message.Builder("recipent@example.com", "Hello Email!", "email")
                .atTime(scheduledTime)
                .build();

        // Create and schedule a Telegram message
        Message telegramMessage = new Message.Builder("7999154023", "Hello Telegram!", "telegram").build(); 

        // Scheduling messages using Singleton Scheduler
        // SingletonScheduler.getInstance().scheduleMessage(whatsappMessage);
        // SingletonScheduler.getInstance().scheduleMessage(emailMessage);
        SingletonScheduler.getInstance().scheduleMessage(telegramMessage); 

        // Scheduling messages and notifying observers
        scheduler.scheduleMessage(whatsappMessage);
        scheduler.scheduleMessage(emailMessage);
        scheduler.scheduleMessage(telegramMessage); 
    }
}
