package Observer;

import Builder.Message;
import Factory.MessageSender;
import Factory.WhatsAppSender;

public class WhatsAppObserver implements MessageSenderObserver {
    private MessageSender messageSender = new WhatsAppSender();

    @Override
    public void update(Message message) {
        if ("whatsapp".equalsIgnoreCase(message.getType())) {  // Change to getType()
            System.out.println("WhatsAppObserver: Sending WhatsApp message to " + message.getRecipient());
            messageSender.sendMessage(message);
        } else {
            System.out.println("WhatsAppObserver: Not a WhatsApp message. Ignoring...");
        }
    }
}
