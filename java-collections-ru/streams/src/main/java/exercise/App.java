package exercise;

import java.util.List;
import java.util.Arrays;
import java.util.Set;

// BEGIN
public class App {

    public static long getCountOfFreeEmails(List<String> emails) {
        long emailCounter = 0;
        Set<String> freeDomain = Set.of("gmail.com", "yandex.ru", "hotmail.com");
        emailCounter = emails.stream()
                             .map(email -> email.split("@")[1])
                             .filter(domain -> freeDomain.contains(domain))
                             .count();
        return emailCounter;
    }
}
// END
