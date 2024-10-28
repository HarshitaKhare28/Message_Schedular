package Observer;

import Builder.Message;
import Factory.MessageSender;
import Factory.TelegramSender;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TelegramObserver implements MessageSenderObserver {

    private MessageSender messageSender = new TelegramSender();
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public TelegramObserver() {
    }

    @Override
    public void update(Message message) {
        if ("telegram".equalsIgnoreCase(message.getType())) {
            System.out.println("TelegramObserver: Scheduling Telegram message to " + message.getRecipient());

            // Get the current time and the scheduled time
            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime scheduledTime = message.getScheduledTime(); // Assuming message contains the scheduled time

            // Calculate the delay in milliseconds
            long delay = calculateDelayInMillis(currentTime, scheduledTime);

            // Schedule the message to be sent after the delay
            scheduler.schedule(() -> {
                System.out.println("TelegramObserver: Sending Telegram message to " + message.getRecipient());
                messageSender.sendMessage(message);
            }, delay, TimeUnit.MILLISECONDS);

        } else {
            System.out.println("TelegramObserver: Not a Telegram message. Ignoring...");
        }
    }

    private long calculateDelayInMillis(LocalDateTime currentTime, LocalDateTime scheduledTime) {
        // Convert LocalDateTime to milliseconds since epoch
        long currentMillis = currentTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long scheduledMillis = scheduledTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        return scheduledMillis - currentMillis;
    }
}
