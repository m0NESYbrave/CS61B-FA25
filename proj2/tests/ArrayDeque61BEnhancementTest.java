import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class ArrayDeque61BEnhancementTest {
    @Test
    public void iteratorTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

        assertThat(ad1).isEmpty();

        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        ad1.addLast(4);
        ad1.addLast(5);
        ad1.addFirst(6);
        ad1.addFirst(7);
        ad1.addLast(8);
        ad1.addLast(9); // resize up

        assertThat(ad1).containsExactly(7, 6, 3, 2, 1, 4, 5, 8, 9).inOrder();

        ad1.removeFirst();
        ad1.removeFirst();
        ad1.removeFirst();
        ad1.removeLast();
        ad1.removeLast();
        ad1.removeLast(); // resize down

        assertThat(ad1).containsExactly(2, 1, 4).inOrder();
    }

    @Test
    public void equalsTest() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        Deque61B<String> ad2 = new ArrayDeque61B<>();
        Deque61B<Integer> ad3 = new ArrayDeque61B<>();

        ad1.addLast("a");
        ad1.addLast("b");
        ad1.addLast("c");
        ad2.addFirst("c");
        ad2.addFirst("b");
        ad2.addFirst("a");

        assertThat(ad1).isEqualTo(ad2);
        assertThat(ad1).isEqualTo(ad1);

        ad3.addLast(1);
        ad3.addLast(2);
        ad3.addLast(3);

        assertThat(ad1).isNotEqualTo(ad3);

        ad1.addFirst("d");
        assertThat(ad1).isNotEqualTo(ad2);

        ad2.addLast("d");
        assertThat(ad1).isNotEqualTo(ad2);
    }

    @Test
    public void toStringTest() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        Deque61B<Integer> ad2 = new ArrayDeque61B<>();

        assertThat(ad1.toString()).contains("[]");

        ad1.addLast("a");
        assertThat(ad1.toString()).contains("[a]");

        ad1.addLast("b");
        ad1.addLast("c");

        assertThat(ad1.toString()).contains("[a, b, c]");

        ad2.addFirst(1);
        ad2.addFirst(2);
        ad2.addFirst(3);

        assertThat(ad2.toString()).contains("[3, 2, 1]");
    }
}
