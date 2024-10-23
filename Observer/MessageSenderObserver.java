package Observer;

import Builder.Message;

public interface MessageSenderObserver {
    void update(Message message);
}
