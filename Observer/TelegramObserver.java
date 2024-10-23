package Observer;

import Builder.Message;
import Factory.MessageSender;
import Factory.TelegramSender;

public class TelegramObserver implements MessageSenderObserver {
    private MessageSender messageSender = new TelegramSender();

    @Override
    public void update(Message message) {
        if ("telegram".equalsIgnoreCase(message.getPlatform())) {
            messageSender.sendMessage(message);
        }
    }
}
