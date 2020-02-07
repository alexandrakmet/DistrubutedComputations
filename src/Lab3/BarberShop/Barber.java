package Lab3.BarberShop;

import java.util.concurrent.Semaphore;

public class Barber extends Thread {
    private Semaphore barberSemaphore, customerSemaphore;

    Barber(Semaphore barberSemaphore, Semaphore customerSemaphore) {
        this.barberSemaphore = barberSemaphore;
        this.customerSemaphore = customerSemaphore;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                customerSemaphore.acquire();
                System.out.println("strizhka ");
                sleep(1000);
                barberSemaphore.release();
            } catch (InterruptedException ignored) {
            }
        }
    }
}
