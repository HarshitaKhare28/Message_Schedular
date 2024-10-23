package Builder;

import java.time.LocalDateTime;

public class Message {
    private String recipient;
    private String body;
    private String type; // Add this field to store the type of the message
    private LocalDateTime scheduledTime;

    // Constructor
    public Message(String recipient, String body, String type, LocalDateTime scheduledTime) {
        this.recipient = recipient;
        this.body = body;
        this.type = type; // Initialize the type
        this.scheduledTime = scheduledTime;
    }

    // Getters
    public String getRecipient() {
        return recipient;
    }

    public String getBody() {
        return body;
    }

    public String getType() {  // Add this method
        return type;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    // Builder class
    public static class Builder {
        private String recipient;
        private String body;
        private String type; // Field for message type
        private LocalDateTime scheduledTime;

        public Builder(String recipient, String body, String type) {
            this.recipient = recipient;
            this.body = body;
            this.type = type; // Set the type in the builder
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
