package Factory;

import Builder.Message;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class TelegramSender implements MessageSender {

    private static final String TELEGRAM_API_KEY = "Your_API_Key";
    private static final String TELEGRAM_API_URL = "https://api.telegram.org/bot" + TELEGRAM_API_KEY + "/sendMessage";

    @Override
    public void sendMessage(Message message) {
        try {
            String payload = "chat_id=" + message.getRecipient() + "&text=" + message.getBody();

            URL url = new URL(TELEGRAM_API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Message sent successfully to: " + message.getRecipient());
            } else {
                System.out.println("Failed to send message. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
