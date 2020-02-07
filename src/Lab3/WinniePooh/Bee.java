package Lab3.WinniePooh;

public class Bee implements Runnable {
    private HoneyPot honeyPot;
    private int beeIndex;

    Bee(HoneyPot honeyPot, int beeIndex){
        this.honeyPot = honeyPot;
        this.beeIndex = beeIndex;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            honeyPot.lockForFilling();
            System.out.println("Bee " + beeIndex + "carried a sip of honey");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }

            honeyPot.unlockForFilling();
        }
    }
}
