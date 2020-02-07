package Lab2.WinniePooh;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final int N = 10;
    private static int[][] forest = new int[N][N];
    static boolean foundWinnie = false;


    public static void main(String[] args) {
        Random random = new Random();
        forest[random.nextInt(N)][random.nextInt(N)] = 1;

        ExecutorService executor = Executors.newFixedThreadPool(N / 2);
        for (int i = 0; i < N; i++) {
            Runnable worker = new BeesRunnable(forest[i], i);
            executor.execute(worker);

        }
        executor.shutdown();
    }
}
