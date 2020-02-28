package Lab5.a;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Main {
    private static int amountWorkers = 3;
    private static int dataPerWorker = 5;

    public static void main(String[] args) {
        Position[] soldiers = new Position[amountWorkers * dataPerWorker];
        CyclicBarrier barrier = new CyclicBarrier(amountWorkers, new BarrierAction(soldiers));
        Thread[] workers = new Thread[amountWorkers];

        for (int i = 0; i < amountWorkers; ++i) {
            int start = i * dataPerWorker;
            int end = (i + 1) * dataPerWorker;
            workers[i] = new Thread(new SoldiersThread(barrier, soldiers, start, end));
        }

        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < soldiers.length; ++i) {
            soldiers[i] = (random.nextInt() % 2 == 0? Position.LEFT: Position.RIGHT);
        }

        System.out.println(soldiers);
        for (Thread worker : workers)
            worker.start();
    }
}
