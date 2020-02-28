package Lab5.c;

public class MyCyclicBarrier {
    private int parties;
    private int currentParties = 0;
    private Runnable barrierAction;

    MyCyclicBarrier(int parties) {
        this.parties = parties;
    }

    MyCyclicBarrier(int parties, Runnable barrierAction) {
        this.parties = parties;
        this.barrierAction = barrierAction;
    }

    public synchronized void await() throws InterruptedException {
        currentParties++;
        if (currentParties == parties) {
            if (barrierAction != null) barrierAction.run();
            currentParties = 0;
            notifyAll();
        } else wait();
    }
}
