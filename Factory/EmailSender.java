package Factory;

import javax.mail.*;
import javax.mail.internet.*;
import Builder.Message;
import java.util.Properties;

public class EmailSender implements MessageSender {
    @Override
    public void sendMessage(Message message) {
        final String senderEmail = message.getSender();    // Get sender's email from Message
        final String senderPassword = message.getPassword(); // Get sender's password from Message

        if (senderEmail == null || senderPassword == null) {
            System.out.println("Error: Email sender credentials are missing.");
            return;
        }

        // Set email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Get the Session object
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a MimeMessage object
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(senderEmail));
            mimeMessage.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(message.getRecipient()));
            mimeMessage.setSubject("Subject"); // Set email subject
            mimeMessage.setText(message.getBody()); // Set email body

            // Send message
            Transport.send(mimeMessage);
            System.out.println("Email sent successfully to: " + message.getRecipient());
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email to: " + message.getRecipient());
        }
    }
}
