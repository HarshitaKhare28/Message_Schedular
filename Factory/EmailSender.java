package Factory;

import Builder.Message;

public class EmailSender implements MessageSender{
    @Override
    public void sendMessage(Message message) {
        System.out.println("Sending Email to: " + message.getRecipient());
    }
}
