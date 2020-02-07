package Lab2.TheMonks;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool();

        Monk[] monks = new Monk[4096];
        Random random = new Random();
        for (int i = 0; i < monks.length; i++) {
            monks[i] = new Monk((random.nextBoolean() ? Monk.Monastery.Guan_Yan : Monk.Monastery.Guan_Yin),
                    random.nextInt(10000));
        }
        Fight task = new Fight(monks, 0, monks.length);
        Monk monk = (Monk) fjp.invoke(task);
        System.out.print("Победитель - " + monk);
    }
}
