package Lab6;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FieldUpdater implements Runnable {
    private FieldPanel fieldPanel;
    private volatile FieldModel fieldModel;
    private ReentrantReadWriteLock lock;

    FieldUpdater(FieldPanel fieldPanel, FieldModel fieldModel, ReentrantReadWriteLock lock) {
        this.fieldPanel = fieldPanel;
        this.fieldModel = fieldModel;
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.writeLock().lock();
        fieldModel.swapField();
        lock.writeLock().unlock();
        fieldPanel.repaint();

        try {
            int timeSleep = 300;
            Thread.sleep(timeSleep);
        } catch (InterruptedException ignored) {
        }
    }
}