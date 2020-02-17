package Lab4.a;

public class MyReadWriteLock {

    private int readers;

    private int writers;

    private int writeRequests;

    public synchronized void readLock() throws InterruptedException {
        while (writers > 0 || writeRequests > 0)
            wait();
        readers++;
    }

    public synchronized void unlockRead() {
        readers--;
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        writeRequests++;

        while (readers > 0 || writers > 0) {
            wait();
        }
        writeRequests--;
        writers++;
    }

    public synchronized void unlockWrite() {
        writers--;
        notifyAll();
    }
}
