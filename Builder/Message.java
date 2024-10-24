package Builder;

import java.time.LocalDateTime;

public class Message {
    private String recipient;
    private String body;
    private String type;
    private LocalDateTime scheduledTime;

    // Constructor
    public Message(String recipient, String body, String type, LocalDateTime scheduledTime) {
        this.recipient = recipient;
        this.body = body;
        this.type = type;
        this.scheduledTime = scheduledTime;
    }

    // Getters
    public String getRecipient() {
        return recipient;
    }

    public String getBody() {
        return body;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    // Builder class
    public static class Builder {
        private String recipient;
        private String body;
        private String type;
        private LocalDateTime scheduledTime;

        public Builder(String recipient, String body, String type) {
            this.recipient = recipient;
            this.body = body;
            this.type = type;
        }

        public Builder atTime(LocalDateTime scheduledTime) {
            this.scheduledTime = scheduledTime;
            return this;
        }

        public Message build() {
            return new Message(recipient, body, type, scheduledTime);
        }
    }
}
