package Lab1.a;

import javax.swing.*;

public class MainForm extends JFrame {
    private JSlider slider;
    private JButton increaseThreadIncPriorityButton;
    private JButton increaseThreadDecPriorityButton;
    private JButton decreaseThreadDecPriorityButton;
    private JButton decreaseThreadIncPriorityButton;
    private JPanel jPanel;
    private JButton startButton;
    private Threads threads;

    MainForm() {
        super("LabRasp1.1a");
        setContentPane(jPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(500, 300));
        setVisible(true);
        pack();
        
        // = new Threads(slider);

        startButton.addActionListener(e->{
            threads = new Threads(slider);
        });
        increaseThreadIncPriorityButton.addActionListener(e -> threads.increaseThreadIncPriority());
        decreaseThreadIncPriorityButton.addActionListener(e -> threads.decreaseThreadIncPriority());
        increaseThreadDecPriorityButton.addActionListener(e -> threads.increaseThreadDecPriority());
        decreaseThreadDecPriorityButton.addActionListener(e -> threads.decreaseThreadDecPriority());

    }
}
