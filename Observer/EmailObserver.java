package Observer;

import Builder.Message;
import Factory.EmailSender;
import Factory.MessageSender;

public class EmailObserver implements MessageSenderObserver {
    private MessageSender messageSender = new EmailSender();

    @Override
    public void update(Message message) {
        // Check if the message type is "email"
        if ("email".equalsIgnoreCase(message.getType())) {  // Ensure this matches your Message type field
            System.out.println("EmailObserver: Preparing to send email to " + message.getRecipient());
            messageSender.sendMessage(message);
        } else {
            System.out.println("EmailObserver: Not an email message. Ignoring...");
        }
    }
}

