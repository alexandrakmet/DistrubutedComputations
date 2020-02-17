package Lab4.a;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class InsertRecord implements Runnable{
    private String name;
    private String number;

    private MyReadWriteLock locker;

    private final String fileName = "input.txt";

    InsertRecord(String name, String number, MyReadWriteLock locker) {
        this.name = name;
        this.number = number;
        this.locker = locker;
    }

    @Override
    public void run() {
        try {
            locker.writeLock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(fileName, true))) {
            printWriter.append(name).append(" - ").append(number);
            printWriter.println();
            System.out.println("Потік для записування додав наступні дані: " + name + " " + number);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        locker.unlockWrite();
    }
}
