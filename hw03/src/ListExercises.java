import java.util.ArrayList;
import java.util.List;

public class ListExercises {
    /** Returns the total sum in a list of integers */
    public static int sum(List<Integer> L) {
        int sum = 0;
        for (int item : L) {
            sum += item;
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        ArrayList<Integer> evens = new ArrayList<>();
        for (int item : L) {
            if (item % 2 == 0) {
                evens.add(item);
            }
        }
        return evens;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        ArrayList<Integer> common = new ArrayList<>();
        for (int item : L1) {
            if (L2.contains(item)) {
                common.add(item);
            }
        }
        return common;
    }

    public static int countOccurrencesOfWord(List<String> words, String w) {
        int cnt = 0;
        for (String word : words) {
            if (word.equals(w)) {
                cnt += 1;
            }
        }
        return cnt;
    }

    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int cnt = 0;
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                if (c == word.charAt(i)) {
                    cnt += 1;
                }
            }
        }
        return cnt;
    }
}
