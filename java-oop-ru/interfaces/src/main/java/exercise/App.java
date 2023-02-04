package exercise;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {

    public static List<String> buildApartmentsList(List<Home> apartments, int count) {
        return apartments.stream()
                         .sorted(Comparator.comparing(Home::getArea))
                         .limit(count)
                         .map(Home::toString)
                         .collect(Collectors.toList());
    }
}
// END
