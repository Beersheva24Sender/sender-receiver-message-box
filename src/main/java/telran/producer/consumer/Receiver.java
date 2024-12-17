package telran.producer.consumer;

import java.util.concurrent.atomic.AtomicBoolean;

public class Receiver extends Thread {
    private MessageBox messageBox;
    private AtomicBoolean stopFlag;

    public Receiver(MessageBox messageBox, AtomicBoolean stopFlag) {
        this.messageBox = messageBox;
        this.stopFlag = stopFlag;
    }

    @Override
    public void run() {
        while (!stopFlag.get()) {
            try {
                String message = messageBox.take();
                System.out.printf("Thread: %s, message: %s\n", getName(), message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.printf("Thread: %s stopped.\n", getName());
    }
}
