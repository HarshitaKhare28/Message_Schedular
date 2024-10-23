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
        Message whatsappMessage = new Message.Builder("1234567890", "Hello WhatsApp!", "whatsapp")
                .build(); // Removed attachment

        // Schedule an email message
        LocalDateTime scheduledTime = LocalDateTime.now().plusSeconds(10); // Schedule to send 10 seconds from now
        Message emailMessage = new Message.Builder("receiver@gmail.com", "Hello Email!", "email") // Ensure the third argument is "email"
        .atTime(scheduledTime)
        .build();

        // Scheduling messages using Singleton Scheduler
        SingletonScheduler.getInstance().scheduleMessage(whatsappMessage);
        SingletonScheduler.getInstance().scheduleMessage(emailMessage);

        // Scheduling messages and notifying observers
        scheduler.scheduleMessage(whatsappMessage);
        scheduler.scheduleMessage(emailMessage);
    }
}
