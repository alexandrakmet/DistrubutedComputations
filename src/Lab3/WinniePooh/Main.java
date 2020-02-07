package Lab3.WinniePooh;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        HoneyPot honeyPot = new HoneyPot();
        Winnie winnie = new Winnie(honeyPot);
        winnie.start();

        ExecutorService executors = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            Runnable bee = new Bee(honeyPot, i);
            executors.execute(bee);
        }

        executors.shutdown();
    }
}
