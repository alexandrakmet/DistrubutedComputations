package Lab3.BarberShop;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Semaphore barberS = new Semaphore(1);
        Semaphore customersS = new Semaphore(10);
        customersS.acquire(10);

        Barber barber = new Barber(barberS, customersS);
        barber.start();

        ExecutorService executors = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            Runnable customer = new Customer(barberS, customersS);
            executors.execute(customer);
        }
        executors.shutdown();
    }
}
