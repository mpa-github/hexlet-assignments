package exercise;

import java.util.Arrays;

class SafetyList {

    int[] array = new int[10];
    int size = 0;

    // BEGIN
    public synchronized void add(int element) {
        if (size == array.length) {
            this.array = Arrays.copyOf(array, array.length * 2);
        }
        array[size] = element;
        size++;
    }

    public int get(int index) {
        return array[index];
    }

    public int getSize() {
        return this.size;
    }
    // END
}
