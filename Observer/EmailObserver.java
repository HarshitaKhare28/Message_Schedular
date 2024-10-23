package Observer;

import Builder.Message;
import Factory.EmailSender;
import Factory.MessageSender;

public class EmailObserver implements MessageSenderObserver {
    private MessageSender messageSender = new EmailSender();

    @Override
    public void update(Message message) {
        if ("email".equalsIgnoreCase(message.getPlatform())) {
            messageSender.sendMessage(message);
        }
    }
}
