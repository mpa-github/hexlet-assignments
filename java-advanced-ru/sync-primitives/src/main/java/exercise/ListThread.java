package exercise;

import java.util.Random;

// BEGIN
public class ListThread extends Thread {

    private SafetyList safetyList;

    public ListThread(SafetyList safetyList) {
        this.safetyList = safetyList;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(1);
                safetyList.add(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public SafetyList getSafetyList() {
        return safetyList;
    }
}
// END
