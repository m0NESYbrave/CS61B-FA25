import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class ArrayDeque61BTest {
    @Test
    public void toListTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

        assertThat(ad1.toList()).isEmpty();

        ad1.addFirst(0);
        ad1.addLast(1);

        assertThat(ad1.toList()).containsExactly(0, 1).inOrder();
    }

    @Test
    public void addFirstTestBasic() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

        ad1.addFirst(0);
        ad1.addFirst(1);
        ad1.addFirst(2);

        assertThat(ad1.toList()).containsExactly(2, 1, 0).inOrder();
    }

    @Test
    public void addLastTestBasic() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

        ad1.addLast(0);
        ad1.addLast(1);
        ad1.addLast(2);

        assertThat(ad1.toList()).containsExactly(0, 1, 2).inOrder();
    }

    @Test
    public void addFirstAndLastTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

        ad1.addLast(0);
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addLast(2);

        assertThat(ad1.toList()).containsExactly(2, 1, 0, 2).inOrder();
    }

    @Test
    public void getTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

        assertThat(ad1.get(0)).isNull(); // out-of-bound large

        ad1.addLast(0);
        ad1.addFirst(1);
        ad1.addFirst(2);

        assertThat(ad1.get(-1)).isNull(); // out-of-bound negative
        assertThat(ad1.get(0)).isEqualTo(2);
        assertThat(ad1.get(1)).isEqualTo(1);
        assertThat(ad1.get(2)).isEqualTo(0);
        assertThat(ad1.get(3)).isNull();

        ad1.removeFirst();
        ad1.removeLast();
        assertThat(ad1.get(0)).isEqualTo(1);
    }

    @Test
    public void sizeAndIsEmptyTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

        assertThat(ad1.size()).isEqualTo(0);
        assertThat(ad1.isEmpty()).isTrue();

        ad1.addLast(0);
        ad1.addLast(1);
        ad1.addFirst(2);

        assertThat(ad1.size()).isEqualTo(3);
        assertThat(ad1.isEmpty()).isFalse();

        ad1.removeFirst();
        ad1.removeLast();

        assertThat(ad1.size()).isEqualTo(1);
    }

    @Test
    public void sizeWithRemoveTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

        // remove from empty
        ad1.removeFirst();
        ad1.removeLast();

        assertThat(ad1.size()).isEqualTo(0);

        // remove to empty
        ad1.addLast(0);
        ad1.addLast(1);
        ad1.addFirst(2);
        ad1.removeLast();
        ad1.removeLast();
        ad1.removeLast();

        assertThat(ad1.size()).isEqualTo(0);
    }

    @Test
    public void removeFirstTestBasic() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

        ad1.addLast(0);
        ad1.addFirst(1);
        ad1.addFirst(2);

        assertThat(ad1.removeFirst()).isEqualTo(2);
        assertThat(ad1.toList()).containsExactly(1, 0).inOrder();
        assertThat(ad1.removeFirst()).isEqualTo(1);
        assertThat(ad1.toList()).containsExactly(0).inOrder();
        assertThat(ad1.removeFirst()).isEqualTo(0);
        assertThat(ad1.toList()).isEmpty();
    }

    @Test
    public void removeLastTestBasic() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

        ad1.addLast(0);
        ad1.addFirst(1);
        ad1.addFirst(2);

        assertThat(ad1.removeLast()).isEqualTo(0);
        assertThat(ad1.toList()).containsExactly(2, 1).inOrder();
        assertThat(ad1.removeLast()).isEqualTo(1);
        assertThat(ad1.toList()).containsExactly(2).inOrder();
        assertThat(ad1.removeLast()).isEqualTo(2);
        assertThat(ad1.toList()).isEmpty();
    }

    @Test
    public void removeFirstAndLastTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

        ad1.addLast(0);
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addLast(3);

        assertThat(ad1.removeFirst()).isEqualTo(2);
        assertThat(ad1.toList()).containsExactly(1, 0, 3).inOrder();
        assertThat(ad1.removeLast()).isEqualTo(3);
        assertThat(ad1.toList()).containsExactly(1, 0).inOrder();
        assertThat(ad1.removeLast()).isEqualTo(0);
        assertThat(ad1.toList()).containsExactly(1).inOrder();
        assertThat(ad1.removeLast()).isEqualTo(1);
        assertThat(ad1.toList()).isEmpty();

        // add after remove
        ad1.addFirst(1);
        ad1.addLast(2);

        assertThat(ad1.toList()).containsExactly(1, 2).inOrder();

        ad1.removeFirst();
        ad1.removeFirst();
        ad1.addLast(1);
        ad1.addFirst(2);

        assertThat(ad1.toList()).containsExactly(2, 1).inOrder();
    }

    // Test resizing

    @Test
    public void addFirstResizeUpTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        ad1.addFirst(4);
        ad1.addFirst(5);
        ad1.addFirst(6);
        ad1.addFirst(7);
        ad1.addFirst(8);
        ad1.addFirst(9); // resize up

        assertThat(ad1.toList()).containsExactly(9, 8, 7, 6, 5, 4, 3, 2, 1).inOrder();
        assertThat(ad1.size()).isEqualTo(9);
        assertThat(ad1.get(0)).isEqualTo(9);
        assertThat(ad1.get(8)).isEqualTo(1);

        ad1.removeFirst();
        ad1.removeFirst();
        ad1.addFirst(9);
        ad1.addLast(10);

        assertThat(ad1.toList()).containsExactly(9, 7, 6, 5, 4, 3, 2, 1, 10).inOrder();
        assertThat(ad1.size()).isEqualTo(9);
        assertThat(ad1.get(0)).isEqualTo(9);
        assertThat(ad1.get(8)).isEqualTo(10);
    }

    @Test
    public void addLastResizeUpTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

        ad1.addLast(1);
        ad1.addLast(2);
        ad1.addLast(3);
        ad1.addLast(4);
        ad1.addLast(5);
        ad1.addLast(6);
        ad1.addLast(7);
        ad1.addLast(8);
        ad1.addLast(9); // resize up

        assertThat(ad1.toList()).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9).inOrder();
        assertThat(ad1.size()).isEqualTo(9);
        assertThat(ad1.get(0)).isEqualTo(1);
        assertThat(ad1.get(8)).isEqualTo(9);

        ad1.removeLast();
        ad1.removeLast();
        ad1.addLast(9);
        ad1.addFirst(0);

        assertThat(ad1.toList()).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 9).inOrder();
        assertThat(ad1.size()).isEqualTo(9);
        assertThat(ad1.get(0)).isEqualTo(0);
        assertThat(ad1.get(8)).isEqualTo(9);
    }

    @Test
    public void removeFirstResizeDownTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        ad1.addLast(4);
        ad1.addLast(5);
        ad1.addFirst(6);
        ad1.addFirst(7);
        ad1.addLast(8);
        ad1.addLast(9); // resize up

        assertThat(ad1.toList()).containsExactly(7, 6, 3, 2, 1, 4, 5, 8, 9).inOrder();

        ad1.removeFirst();
        ad1.removeFirst();
        ad1.removeFirst();
        ad1.removeLast();
        ad1.removeLast();
        ad1.removeFirst(); // resize down

        assertThat(ad1.toList()).containsExactly(1, 4, 5).inOrder();
        assertThat(ad1.size()).isEqualTo(3);
        assertThat(ad1.get(0)).isEqualTo(1);
        assertThat(ad1.get(2)).isEqualTo(5);
    }

    @Test
    public void removeLastResizeDownTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        ad1.addLast(4);
        ad1.addLast(5);
        ad1.addFirst(6);
        ad1.addFirst(7);
        ad1.addLast(8);
        ad1.addLast(9); // resize up

        assertThat(ad1.toList()).containsExactly(7, 6, 3, 2, 1, 4, 5, 8, 9).inOrder();

        ad1.removeFirst();
        ad1.removeFirst();
        ad1.removeFirst();
        ad1.removeLast();
        ad1.removeLast();
        ad1.removeLast(); // resize down

        assertThat(ad1.toList()).containsExactly(2, 1, 4).inOrder();
        assertThat(ad1.size()).isEqualTo(3);
        assertThat(ad1.get(0)).isEqualTo(2);
        assertThat(ad1.get(2)).isEqualTo(4);
    }
}
