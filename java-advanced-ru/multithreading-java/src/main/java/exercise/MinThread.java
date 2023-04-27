package exercise;

// BEGIN
public class MinThread extends Thread {

    private int[] array;
    private int min;

    public MinThread(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        min = array[0];
        for (int i : array) {
            if (i < min) {
                min = i;
            }
        }
    }

    public int getMin() {
        return min;
    }
}
// END
