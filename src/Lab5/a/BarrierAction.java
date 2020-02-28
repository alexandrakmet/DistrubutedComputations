package Lab5.a;

public class BarrierAction implements Runnable {
    private final Position[] soldiers;

    BarrierAction(Position[] soldiers) {
        this.soldiers = soldiers;
    }

    @Override
    public void run() {

        boolean finished = true;
        for (int i = 1; i < soldiers.length && finished; ++i) {
            Position left = soldiers[i - 1];
            Position right = soldiers[i];
            if (left != right)
                finished = false;
        }

        for (int i = 0; i < soldiers.length ; i++) {
            System.out.print(soldiers[i] + " ");
        }
        System.out.println();
    }
}
