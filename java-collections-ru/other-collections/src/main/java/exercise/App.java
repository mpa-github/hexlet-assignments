package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

// BEGIN
public class App {

    public static <T> LinkedHashMap<String, String> genDiff(Map<String, T> map1, Map<String, T> map2) {
        final String added = "added";
        final String deleted = "deleted";
        final String changed = "changed";
        final String unchanged = "unchanged";
        LinkedHashMap<String, String> diff = new LinkedHashMap<>();
        Stream.concat(map1.keySet().stream(), map2.keySet().stream())
              .forEach(key -> diff.put(key, null));
        diff.keySet().forEach(key -> {
            if (!map1.containsKey(key) && map2.containsKey(key)) {
                diff.put(key, added);
            } else if (map1.containsKey(key) && !map2.containsKey(key)) {
                diff.put(key, deleted);
            } else if (map1.containsKey(key) && map2.containsKey(key) &&
                       map1.get(key).equals(map2.get(key))) {
                diff.put(key, unchanged);
            } else {
                diff.put(key, changed);
            }
        });
        return diff;
    }
}
//END
