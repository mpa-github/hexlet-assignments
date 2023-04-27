package exercise;

// BEGIN
public class MaxThread extends Thread {

    private int[] array;
    private int max;

    public MaxThread(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        max = array[0];
        for (int i : array) {
            if (i > max) {
                max = i;
            }
        }
    }

    public int getMax() {
        return max;
    }
}
// END
