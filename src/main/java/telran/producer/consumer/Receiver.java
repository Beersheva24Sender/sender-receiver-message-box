package telran.producer.consumer;

public class Receiver extends Thread {
    private MessageBox messageBox;

    public Receiver(MessageBox messageBox) {
        this.messageBox = messageBox;
    }

    public void setMessageBox(MessageBox messageBox) {
        this.messageBox = messageBox;
    }

    @Override
    public void run() {
        String message = null;
        try {
            while (true) {

                message = messageBox.take();
                process(message);

            }
        } catch (InterruptedException e) {
            do {
                message = messageBox.poll();
                if (message != null) {
                    process(message);
                }
            } while (message != null);
        }
    }

    private void process(String message) {
        System.out.printf("Thread: %s, message: %s\n", getName(), message);
    }
}
