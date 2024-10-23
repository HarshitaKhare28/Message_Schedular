package Singleton;

import Builder.Message;

public class SingletonScheduler {
    private static SingletonScheduler instance;

    private SingletonScheduler() {}

    public static SingletonScheduler getInstance() {
        if (instance == null) {
            instance = new SingletonScheduler();
        }
        return instance;
    }

    public void scheduleMessage(Message message) {
        // Log the message scheduling
        System.out.println("Scheduling message in SingletonScheduler: " + message.getBody()); // Change getContent() to getBody()
        // Add your scheduling logic here
    }
}
