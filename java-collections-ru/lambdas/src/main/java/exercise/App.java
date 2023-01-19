package exercise;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
public class App {

    public static String[][] enlargeArrayImage(String[][] array) {
        String[][] newArray;

        newArray = Arrays.stream(array)
                         .flatMap(row -> Stream.of(enlargeArray(row), enlargeArray(row)))
                         .toArray(String[][]::new);
        return newArray;
    }

    private static String[] enlargeArray(String[] strArray) {
        return Arrays.stream(strArray)
                     .flatMap(element -> Stream.of(element, element))
                     .toArray(String[]::new);
    }
}
// END
