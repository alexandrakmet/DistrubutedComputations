package Lab2.TheMonks;

import java.util.concurrent.RecursiveTask;

public class Fight extends RecursiveTask {

    private final int fighters = 2;
    private Monk[] data;
    private int start, end;

    Fight(Monk[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Monk compute() {
        if ((end - start) <= fighters) {
            return (data[start].getQiEnergy() > data[end - 1].getQiEnergy() ? data[start] : data[end - 1]);

        } else {
            int middle = (start + end) / 2;

            Fight subtaskA = new Fight(data, start, middle);
            Fight subtaskB = new Fight(data, middle, end);

            subtaskA.fork();
            subtaskB.fork();

            Monk a = (Monk) subtaskA.join();
            Monk b = (Monk) subtaskB.join();
            return (a.getQiEnergy() > b.getQiEnergy() ? a : b);
        }
    }

}
