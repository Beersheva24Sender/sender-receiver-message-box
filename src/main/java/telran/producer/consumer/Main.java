package telran.producer.consumer;

import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    private static final int N_MESSAGES = 20;
    static final int N_RECEIVERS = 10;

    public static void main(String[] args) throws InterruptedException {
        MessageBox messageBox = new SimpleMessageBox();
        Sender sender = new Sender(N_MESSAGES, messageBox);
        AtomicBoolean stopFlag = new AtomicBoolean(false);

        Receiver[] receivers = new Receiver[N_RECEIVERS];
        for (int i = 0; i < N_RECEIVERS; i++) {
            receivers[i] = new Receiver(messageBox, stopFlag);
            receivers[i].start();
        }

        sender.start();
        sender.join(); // Wait for sender to finish
        stopFlag.set(true); // Signal receivers to stop

        // Ensure all receivers stop
        for (Receiver receiver : receivers) {
            receiver.join();
        }

        System.out.println("All receivers stopped, program finished.");
    }
}
