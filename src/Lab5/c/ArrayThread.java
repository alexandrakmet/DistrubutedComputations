package Lab5.c;

import java.util.Random;

public class ArrayThread extends Thread {
    private int[] array;
    private int currentSum = 0;
    private Action nextAction;
    private MyCyclicBarrier barrier;
    final private Random rand = new Random();

    public ArrayThread(MyCyclicBarrier barrier, int[] array) {
        this.array = array;
        this.barrier = barrier;
        this.nextAction = rand.nextInt(2) == 1 ? Action.DECREMENT : Action.INCREMENT;
    }

    public ArrayThread(MyCyclicBarrier barrier) {
        this(barrier, null);
        int len = 15;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++)
            arr[i] = rand.nextInt(100);
        this.array = arr;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            this.applyAction();
            int sum = this.calculateSum();
            System.out.printf("Винонана дія: %s, Сума: %d\n", this.nextAction, sum);

            this.setCurrentSum(sum);
            try {
                Thread.sleep(100);
                barrier.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    // Підрахування власне суми
    private int calculateSum() {
        int sum = 0;
        for (int i1 : array)
            sum += i1;
        return sum;
    }

    // Встановлення нової дії
    public void setNextAction(Action nextAction) {
        this.nextAction = nextAction;
    }

    // Встановлення нової суми
    synchronized private void setCurrentSum(int currentSum) {
        this.currentSum = currentSum;
    }

    // Повертає поточну суму
    synchronized public int getCurrentSum() {
        return currentSum;
    }

    // Виконання обраної дії
    private void applyAction() {
        if (nextAction.equals(Action.DECREMENT))
            this.decrement();
        else if (nextAction.equals(Action.INCREMENT))
            this.increment();
    }

    private void decrement() {
        int index = rand.nextInt(array.length);
        array[index]--;
    }

    private void increment() {
        int index = rand.nextInt(array.length);
        array[index]++;
    }
}
