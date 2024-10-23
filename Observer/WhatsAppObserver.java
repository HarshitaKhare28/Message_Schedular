package Observer;
import Builder.Message;
import Factory.MessageSender;
import Factory.WhatsAppSender;

public class WhatsAppObserver implements MessageSenderObserver{
    private MessageSender messageSender = new WhatsAppSender();

    @Override
    public void update(Message message) {
        if ("whatsapp".equalsIgnoreCase(message.getPlatform())) {
            messageSender.sendMessage(message);
        }
    }
}
