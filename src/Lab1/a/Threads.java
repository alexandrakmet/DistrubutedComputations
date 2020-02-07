package Lab1.a;

import javax.swing.*;

class Threads {
    private Thread inc, dec;
    private final JSlider slider;

    Threads(JSlider slider) {
        this.slider = slider;

        initializeThreads();
        inc.setDaemon(true);
        dec.setDaemon(true);
        inc.start();
        dec.start();
    }

    private void initializeThreads() {
        inc = new Thread(() -> {
            while (true) {
                synchronized (slider) {
                    if (slider.getValue() < 90) slider.setValue(slider.getValue() + 1);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        dec = new Thread(() -> {
            while (true) {
                synchronized (slider) {
                    if (slider.getValue() > 10) slider.setValue(slider.getValue() - 1);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void increaseThreadIncPriority() {
        if (inc.getPriority() < Thread.MAX_PRIORITY)
            inc.setPriority(inc.getPriority() + 1);
    }

    public void decreaseThreadIncPriority() {
        if (inc.getPriority() > Thread.MIN_PRIORITY)
            inc.setPriority(inc.getPriority() - 1);
    }

    public void increaseThreadDecPriority() {
        if (dec.getPriority() < Thread.MAX_PRIORITY)
            dec.setPriority(dec.getPriority() + 1);
    }

    public void decreaseThreadDecPriority() {
        if (dec.getPriority() > Thread.MIN_PRIORITY)
            dec.setPriority(dec.getPriority() - 1);
    }
}
