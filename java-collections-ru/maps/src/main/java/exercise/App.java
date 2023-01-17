package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {

    public static Map<String, Integer> getWordCount(String sentence) {
        final String emptyWord = "";
        String[] words = sentence.split(" ");
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            if (map.containsKey(word)) {
                int oldValue = map.get(word);
                int newValue = oldValue + 1;
                map.put(word, newValue);
            } else {
                if (!word.equals(emptyWord)) {
                    map.put(word, 1);
                }
            }
        }
        return map;
    }

    public static String toString(Map<String, Integer> map) {
        final String newLine = "\n";
        final String indent = "  ";
        final String splitter = ": ";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            stringBuilder.append(newLine)
                         .append(indent)
                         .append(entry.getKey())
                         .append(splitter)
                         .append(entry.getValue());
        }
        if (!map.isEmpty()) {
            stringBuilder.append(newLine);
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
//END
