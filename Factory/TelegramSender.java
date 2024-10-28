package Factory;

import Builder.Message;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TelegramSender implements MessageSender {

    private static final String TELEGRAM_API_KEY = System.getenv("TELEGRAM_API_KEY");

    @Override
    public void sendMessage(Message message) {
        if (TELEGRAM_API_KEY == null || TELEGRAM_API_KEY.isEmpty()) {
            System.out.println("Error: TELEGRAM_API_KEY environment variable is not set.");
            return;
        }

        try {
            // Encode chat ID and message body to handle special characters
            String encodedChatId = URLEncoder.encode(message.getRecipient(), StandardCharsets.UTF_8.toString());
            String encodedBody = URLEncoder.encode(message.getBody(), StandardCharsets.UTF_8.toString());

            // Construct the API URL and payload
            String telegramApiUrl = "https://api.telegram.org/bot" + TELEGRAM_API_KEY + "/sendMessage";
            String payload = "chat_id=" + encodedChatId + "&text=" + encodedBody;

            URL url = new URL(telegramApiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Write payload to output stream
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Check response code and read error message if needed
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Message sent successfully to: " + message.getRecipient());
            } else {
                System.out.println("Failed to send message. Response Code: " + responseCode);
                // Print error details
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println("Error response: " + response.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An exception occurred while sending the message.");
        }
    }
}
