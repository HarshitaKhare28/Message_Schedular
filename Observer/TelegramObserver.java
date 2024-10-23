package Observer;

import Builder.Message;
import Factory.MessageSender;
import Factory.TelegramSender;

public class TelegramObserver implements MessageSenderObserver {
    private MessageSender messageSender = new TelegramSender();

    @Override
    public void update(Message message) {
        if ("telegram".equalsIgnoreCase(message.getType())) {  // Change to getType()
            System.out.println("TelegramObserver: Sending Telegram message to " + message.getRecipient());
            messageSender.sendMessage(message);
        } else {
            System.out.println("TelegramObserver: Not a Telegram message. Ignoring...");
        }
    }
}
