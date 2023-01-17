package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
public class App {

    public static boolean scrabble(String string, String word){
        if (string.length() == 0) {
            return false;
        }
        char[] stringChars = string.toCharArray();
        char[] wordChars = word.toLowerCase().toCharArray();
        List<Character> stringCharList = new ArrayList<>();
        List<Character> wordCharList = new ArrayList<>();
        for (char ch : stringChars) {
            stringCharList.add(ch);
        }
        for (char ch : wordChars) {
            wordCharList.add(ch);
        }
        for (Character ch : wordCharList) {
           if (!stringCharList.remove(ch)) {
               return false;
           }
        }
        return true;
    }
}
//END
