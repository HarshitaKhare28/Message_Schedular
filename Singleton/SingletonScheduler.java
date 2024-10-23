package Singleton;

import Builder.Message;

public class SingletonScheduler {
    private static SingletonScheduler instance;

    private SingletonScheduler() { }

    public static SingletonScheduler getInstance() {
        if (instance == null) {
            instance = new SingletonScheduler();
        }
        return instance;
    }

    public void scheduleMessage(Message message) {
        System.out.println("Scheduling message in SingletonScheduler: " + message.getContent());
        // Additional scheduling logic
    }
}
