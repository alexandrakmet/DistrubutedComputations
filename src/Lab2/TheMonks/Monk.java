package Lab2.TheMonks;

public class Monk {
    enum Monastery {
        Guan_Yin, Guan_Yan
    }

    private Monastery monastery;
    private int qiEnergy;

    Monk(Monastery monastery, int qiEnergy) {
        this.monastery = monastery;
        this.qiEnergy = qiEnergy;
    }

    public int getQiEnergy() {
        return qiEnergy;
    }

    @Override
    public String toString() {
        return "монах с" + " монастыря "
                + (monastery == Monastery.Guan_Yin ? "Гуань-Инь" : "Гуань-Янь")
                + ", " + qiEnergy + " енергии Ци";
    }
}
