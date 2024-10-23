package Builder;

public class Message {
    private String recipient;
    private String content;
    private String platform; // WhatsApp, Telegram, Email
    private String attachment;

    private Message(Builder builder) {
        this.recipient = builder.recipient;
        this.content = builder.content;
        this.platform = builder.platform;
        this.attachment = builder.attachment;
    }

    public String getRecipient() { return recipient; }
    public String getContent() { return content; }
    public String getPlatform() { return platform; }
    public String getAttachment() { return attachment; }

    public static class Builder {
        private String recipient;
        private String content;
        private String platform;
        private String attachment; // Optional

        public Builder(String recipient, String content, String platform) {
            this.recipient = recipient;
            this.content = content;
            this.platform = platform;
        }

        public Builder withAttachment(String attachment) {
            this.attachment = attachment;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}
