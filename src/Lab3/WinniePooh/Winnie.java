package Lab3.WinniePooh;

public class Winnie extends Thread {
    private HoneyPot honeyPot;

    Winnie(HoneyPot honeyPot) {
        this.honeyPot = honeyPot;
    }

    @Override
    public void run() {
        while (!isInterrupted()){
            honeyPot.lockForDrinking();
            System.out.println("Winnie drank all honey ");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
            }
            honeyPot.unlockForDrinking();
        }
    }
}
