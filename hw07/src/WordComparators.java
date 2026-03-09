import java.util.Comparator;
import java.util.List;

public class WordComparators {

    /** Returns a comparator that orders strings by the number of lowercase 'x' characters (ascending). */
    public static Comparator<String> getXComparator() {
        return new GetXComparator();
    }

    private static class GetXComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return WordComparators.compare(o1, o2, List.of('x'));
        }
    }

    /** Returns a comparator that orders strings by the count of the given character (ascending). */
    public static Comparator<String> getCharComparator(char c) {
        return new GetCharComparator(c);
    }

    private static class GetCharComparator implements Comparator<String> {
        char c;

        GetCharComparator(char c) {
            this.c = c;
        }

        @Override
        public int compare(String o1, String o2) {
            return WordComparators.compare(o1, o2, List.of(c));
        }
    }

    /** Returns a comparator that orders strings by the total count of the given characters (ascending). */
    public static Comparator<String> getCharListComparator(List<Character> chars) {
        return new GetCharListComparator(chars);
    }

    private static class GetCharListComparator implements Comparator<String> {
        List<Character> chars;

        GetCharListComparator(List<Character> chars) {
            this.chars = chars;
        }

        @Override
        public int compare(String o1, String o2) {
            return WordComparators.compare(o1, o2, chars);
        }
    }

    // Should be static, otherwise all the comparator methods can't access this
    private static int compare(String o1, String o2, List<Character> chars) {
        int cnt1 = 0;
        int cnt2 = 0;
        for (int i = 0; i < o1.length(); i++) {
            if (chars.contains(o1.charAt(i))) {
                cnt1 += 1;
            }
        }
        for (int i = 0; i < o2.length(); i++) {
            if (chars.contains(o2.charAt(i))) {
                cnt2 += 1;
            }
        }
        return cnt1 - cnt2;
    }
}
