package Lab3.WinniePooh;

public class HoneyPot {
    private int maxCapacity = 20;
    private int currentCapacity = 0;

    private boolean semaphore;

    public synchronized void lockForDrinking() {
        while (!semaphore || !(currentCapacity == maxCapacity)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        currentCapacity = 0;

    }

    public synchronized void unlockForDrinking(){
        semaphore = false;
        notifyAll();
    }

    public synchronized void lockForFilling() {
        while (semaphore || currentCapacity == maxCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        currentCapacity++;
    }

    public synchronized void unlockForFilling(){
        if (currentCapacity == maxCapacity)
            semaphore = true;
        notifyAll();
    }

    public synchronized int getCurrentCapacity(){
        return currentCapacity;
    }

}
