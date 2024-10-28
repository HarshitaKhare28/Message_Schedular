package Builder;

import java.time.LocalDateTime;

public class Message {
    private String recipient;
    private String body;
    private String type;
    private LocalDateTime scheduledTime;
    private String sender;       // New field for sender's email
    private String password;      // New field for sender's password

    // Constructor
    public Message(String recipient, String body, String type, LocalDateTime scheduledTime, String sender, String password) {
        this.recipient = recipient;
        this.body = body;
        this.type = type;
        this.scheduledTime = scheduledTime;
        this.sender = sender;
        this.password = password;
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

    public String getSender() {
        return sender;
    }

    public String getPassword() {
        return password;
    }

    // Builder class
    public static class Builder {
        private String recipient;
        private String body;
        private String type;
        private LocalDateTime scheduledTime;
        private String sender;       // Builder field for sender's email
        private String password;      // Builder field for sender's password

        public Builder(String recipient, String body, String type) {
            this.recipient = recipient;
            this.body = body;
            this.type = type;
        }

        public Builder atTime(LocalDateTime scheduledTime) {
            this.scheduledTime = scheduledTime;
            return this;
        }

        public Builder withSender(String sender) {
            this.sender = sender;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Message build() {
            return new Message(recipient, body, type, scheduledTime, sender, password);
        }
    }
}
