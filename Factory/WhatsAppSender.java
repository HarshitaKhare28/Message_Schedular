package Factory;

import Builder.Message;

public class WhatsAppSender implements MessageSender{
    @Override
    public void sendMessage(Message message) {
        System.out.println("Sending WhatsApp message to: " + message.getRecipient());
    }
}
