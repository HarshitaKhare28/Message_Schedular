import Builder.Message;
import Observer.EmailObserver;
import Observer.Scheduler;
import Observer.TelegramObserver;
import Observer.WhatsAppObserver;
import Singleton.SingletonScheduler;

public class Main {
    public static void main(String[] args) {
        // Creating observers
        Scheduler scheduler = new Scheduler();
        scheduler.addObserver(new WhatsAppObserver());
        scheduler.addObserver(new TelegramObserver());
        scheduler.addObserver(new EmailObserver());

        // Creating messages using Builder pattern
        Message whatsappMessage = new Message.Builder("1234567890", "Hello WhatsApp!", "whatsapp")
                .withAttachment("photo.jpg")
                .build();

        Message emailMessage = new Message.Builder("example@mail.com", "Hello Email!", "email")
                .build();

        // Scheduling messages using Singleton Scheduler
        SingletonScheduler.getInstance().scheduleMessage(whatsappMessage);
        SingletonScheduler.getInstance().scheduleMessage(emailMessage);

        // Scheduling messages and notifying observers
        scheduler.scheduleMessage(whatsappMessage);
        scheduler.scheduleMessage(emailMessage);
    }
}
