package Factory;

public class MessageSenderFactory {
    public static MessageSender getMessageSender(String platform) {
        switch (platform.toLowerCase()) {
            case "whatsapp":
                return new WhatsAppSender();
            case "telegram":
                return new TelegramSender();
            case "email":
                return new EmailSender();
            default:
                throw new IllegalArgumentException("Unsupported platform: " + platform);
        }
    }
}
