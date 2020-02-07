package Lab1.b;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class MainForm extends JFrame {
    private JSlider slider;
    private JButton start2Button;
    private JButton stop2Button;
    private JButton start1Button;
    private JButton stop1Button;
    private JPanel jPanel;

    private AtomicInteger semaphore = new AtomicInteger(0);
    private Thread thread1, thread2;

    MainForm() {
        super("LabRasp1.1b");
        setContentPane(jPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(500, 300));
        setVisible(true);
        pack();

        start1Button.addActionListener(e -> {
            if (semaphore.compareAndSet(0,1)) {
                stop2Button.setEnabled(false);
                thread1 = new Thread(() -> {
                    while (!Thread.currentThread().isInterrupted())
                        if (slider.getValue() > 10) slider.setValue(slider.getValue() - 1);

                });
                thread1.setPriority(1);
                thread1.start();
            } else JOptionPane.showMessageDialog(this, "Занято потоком");
        });

        stop1Button.addActionListener(e -> {
            if (thread1 != null && thread1.isAlive()) {
                thread1.interrupt();
                try {
                    thread1.join();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                semaphore.set(0);
                stop2Button.setEnabled(true);
            }
        });

        start2Button.addActionListener(e -> {
            if (semaphore.compareAndSet(0,1)) {
                stop1Button.setEnabled(false);
                thread2 = new Thread(() -> {
                    while (!Thread.currentThread().isInterrupted())
                        if (slider.getValue() < 90) slider.setValue(slider.getValue() + 1);

                });
                thread2.setPriority(10);
                thread2.start();
            } else JOptionPane.showMessageDialog(this, "Занято потоком");
        });

        stop2Button.addActionListener(e -> {
            if (thread2 != null && thread2.isAlive()) {
                thread2.interrupt();
                try {
                    thread2.join();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                semaphore.set(0);
                stop1Button.setEnabled(true);
            }
        });

    }
}
