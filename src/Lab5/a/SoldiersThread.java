package Lab5.a;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class SoldiersThread implements Runnable {

    private final CyclicBarrier barrier;
    private final Position[] soldiers;
    private final int start, end;

    SoldiersThread(CyclicBarrier barrier, Position[] soldiers, int start, int end) {
        this.barrier = barrier;
        this.soldiers = soldiers;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        while (!isDone(soldiers)) {
            for (int i = start; i < end; ++i) {
                if (i == 0)
                    continue;

                Position left = soldiers[i - 1];
                Position right = soldiers[i];
                if (left != right && left == Position.RIGHT) {
                    soldiers[i - 1] = inversePosition(soldiers[i - 1]);
                    soldiers[i] = inversePosition(soldiers[i]);
                }
            }

            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    private Position inversePosition(Position p) {
        if (p == Position.LEFT) return Position.RIGHT;
        if (p == Position.RIGHT) return Position.LEFT;
        return p;
    }

    private boolean isDone(Position[] soldiers) {
        for (int i = 0; i < soldiers.length - 1; i++) {
            Position left = soldiers[i];
            Position right = soldiers[i+1];
            if (left != right && left == Position.RIGHT) {
               return false;
            }
        }
        return true;
    }
}
