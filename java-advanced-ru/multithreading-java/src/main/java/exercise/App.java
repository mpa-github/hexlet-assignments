package exercise;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {

    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] array) {
        Map<String, Integer> result = new LinkedHashMap<>(2);
        MaxThread threadMax = new MaxThread(array);
        MinThread threadMin = new MinThread(array);
        LOGGER.log(Level.INFO, "Thread Thread-Max started");
        threadMax.start();
        LOGGER.log(Level.INFO, "Thread Thread-Max finished");
        LOGGER.log(Level.INFO, "Thread Thread-Min started");
        threadMin.start();
        LOGGER.log(Level.INFO, "Thread Thread-Min finished");
        try {
            threadMax.join();
            threadMin.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        int max = threadMax.getMax();
        int min = threadMin.getMin();
        result.put("min", min);
        result.put("max", max);
        return result;
    }
    // END
}
