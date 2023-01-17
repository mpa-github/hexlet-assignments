package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
public class App {

    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> data) {
        List<Map<String, String>> searchResult = new ArrayList<>();
        for (Map<String, String> book : books) {
            boolean isContainAll = false;
            for (Map.Entry<String, String> dataToSearch : data.entrySet()) {
                if (book.get(dataToSearch.getKey()).equals(dataToSearch.getValue())) {
                    isContainAll = true;
                } else {
                    isContainAll = false;
                    break;
                }
            }
            if (isContainAll) {
                searchResult.add(book);
            }
        }
        return searchResult;
    }
}
//END
