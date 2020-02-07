package Lab3.BarberShop;

import java.util.concurrent.Semaphore;

public class Customer implements Runnable{
    private Semaphore barberSemaphore, customerSemaphore;

    Customer(Semaphore barberSemaphore, Semaphore customerSemaphore){
        this.barberSemaphore = barberSemaphore;
        this.customerSemaphore = customerSemaphore;
    }


    @Override
    public void run(){
        while (!Thread.currentThread().isInterrupted()){
            try {
                System.out.println("Customer came");
                customerSemaphore.release();
                barberSemaphore.acquire();

            } catch (InterruptedException ignored) {
            }
        }
    }
}
