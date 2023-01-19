package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
public class App {

    public static String getForwardedVariables(String configContent) {
        String[] lines = configContent.split("\\n");
        return Arrays.stream(lines)
                     .filter(line -> line.startsWith("environment="))
                     .map(line -> line.substring(12).replaceAll("\"", ""))
                     .flatMap(line -> Stream.of(line.split(",")))
                     .filter(parameter -> parameter.startsWith("X_FORWARDED_"))
                     .map(parameter -> parameter.replaceAll("X_FORWARDED_", ""))
                     .collect(Collectors.joining(","));
    }
}
//END
