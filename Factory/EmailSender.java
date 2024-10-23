package Factory;

import javax.mail.*;
import javax.mail.internet.*;
import Builder.Message;
import java.util.Properties;

public class EmailSender implements MessageSender {
    @Override
    public void sendMessage(Message message) {
        final String username = "sender@gmail.com"; // Your email
        final String password = "app password(12 digits) of gmail account(mandetory for 2 factor verification)"; // Your app password

        Properties props = new Properties(); // Initialize Properties
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Get the Session object
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(username)); // Sender's email
            mimeMessage.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(message.getRecipient())); // Receiver's email
            mimeMessage.setSubject("Subject"); // Subject of the email
            mimeMessage.setText(message.getBody()); // Body of the email

            // Send message
            Transport.send(mimeMessage);
            System.out.println("Email sent successfully to: " + message.getRecipient());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}