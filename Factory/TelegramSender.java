package Factory;

import Builder.Message;

public class TelegramSender implements MessageSender {
    @Override
    public void sendMessage(Message message) {
        System.out.println("Sending Telegram message to: " + message.getRecipient());
    }
}
