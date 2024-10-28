import Builder.Message;
import Observer.EmailObserver;
import Observer.Scheduler;
import Observer.TelegramObserver;
import Singleton.SingletonScheduler;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        System.out.println("=== Interactive Message Scheduler ===");

        // Create a scheduler and add observers for Email and Telegram
        Scheduler scheduler = new Scheduler();
        scheduler.addObserver(new TelegramObserver());
        scheduler.addObserver(new EmailObserver());

        // Loop to allow multiple messages to be scheduled
        while (true) {
            System.out.println("\nChoose the platform you wish to use:");
            System.out.println("1. Telegram");
            System.out.println("2. Email");
            System.out.println("3. Exit");

            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice == 3) {
                System.out.println("Exiting the scheduler. Goodbye!");
                break;
            }

            // Get platform-specific details
            String platform = (choice == 1) ? "telegram" : (choice == 2) ? "email" : null;
            if (platform == null) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            String sender = "", password = "", recipient;
            if (platform.equals("email")) {
                System.out.print("Enter your Email Address: ");
                sender = scanner.nextLine().trim();
                System.out.print("Enter your Email Password: ");
                password = scanner.nextLine().trim();
                System.out.print("Enter the Recipient's Email Address: ");
                recipient = scanner.nextLine().trim();
            } else {
                System.out.print("Enter the Recipient's Telegram Chat ID: ");
                recipient = scanner.nextLine().trim();
            }

            System.out.println("Enter the message you wish to send:");
            String content = scanner.nextLine().trim();

            System.out.println("Enter the date and time to send the message (format: yyyy-MM-dd HH:mm):");
            LocalDateTime scheduledTime = null;
            while (scheduledTime == null) {
                try {
                    String dateTimeInput = scanner.nextLine().trim();
                    scheduledTime = LocalDateTime.parse(dateTimeInput, dateTimeFormatter);
                    if (scheduledTime.isBefore(LocalDateTime.now())) {
                        System.out.println("Scheduled time must be in the future. Please enter again:");
                        scheduledTime = null;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid format. Please enter the date and time in format yyyy-MM-dd HH:mm:");
                }
            }

            // Build the message with the provided details
            Message message = new Message.Builder(recipient, content, platform)
                    .atTime(scheduledTime)
                    .withSender(sender)
                    .withPassword(password)
                    .build();

            // Schedule message with Singleton Scheduler and Observer
            SingletonScheduler.getInstance().scheduleMessage(message);
            scheduler.scheduleMessage(message);
            System.out.println("Message scheduled successfully for " + platform + " at " + scheduledTime.format(dateTimeFormatter));
        }
    }
}
