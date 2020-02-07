package Lab2.WinniePooh;

public class BeesRunnable implements Runnable {
    private int[] region;
    private int regionNumber;

    BeesRunnable(int[] region, int regionNumber) {
        this.region = region;
        this.regionNumber = regionNumber;
    }

    @Override
    public void run() {
        if (!Main.foundWinnie)
            for (int i = 0; i < region.length; i++) {
                if (region[i] == 1) {
                    System.out.println("Винни-Пух был найден и наказан, координаты преступника (" + regionNumber + ", " + i +')');
                    return;
                }
            }
    }
}
