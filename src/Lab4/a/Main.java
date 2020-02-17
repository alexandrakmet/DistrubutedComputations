package Lab4.a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {
    private static final ArrayList<String> NAMES = new ArrayList<>(Arrays.asList("name1", "name2", "name3", "name4", "name5", "name6", "name7"));
            private static final ArrayList<String> NUMBERS = new ArrayList<>(Arrays.asList("111111", "222222", "333333", "444444", "5555555", "666666", "7777777"));

    public static void main(String[] args) {
        MyReadWriteLock myReadWriteLock = new MyReadWriteLock();
        Random rand = new Random();

        for (int i = 0; i <= NAMES.size() - 1; i++) {
            new Thread(new InsertRecord(NAMES.get(i), NUMBERS.get(i), myReadWriteLock)).start();
            int numberIdx = Math.abs(rand.nextInt()) % NUMBERS.size();
            new Thread(new SearchByNumber(NUMBERS.get(numberIdx), myReadWriteLock)).start();
            int nameIdx = Math.abs(rand.nextInt()) % NAMES.size();
            new Thread(new SearchByName(NAMES.get(nameIdx), myReadWriteLock)).start();
            int r = rand.nextInt(NAMES.size() - 1);
            new Thread(new RemoveRecord(NAMES.get(r), NUMBERS.get(r), myReadWriteLock)).start();
        }
    }
}