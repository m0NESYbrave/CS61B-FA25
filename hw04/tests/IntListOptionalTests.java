import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntListOptionalTests {
    /**
     * Returns an IntList of the given integers.
     */
    public static IntList of(int... nums) {
        IntList L = new IntList(nums[0], null);
        IntList current = L;
        for (int i = 1; i < nums.length; i++) {
            current.rest = new IntList(nums[i], null);
            current = current.rest;
        }
        return L;
    }

    @Test
    public void testSum() {
        IntList L1 = of(1);
        assertThat(L1.sum()).isEqualTo(1);

        IntList L2 = of(1, 2, -3);
        assertThat(L2.sum()).isEqualTo(0);
    }

    @Test
    public void testAddLast() {
        IntList L = of(1);

        L.addLast(2);
        L.addLast(3);

        assertThat(L.sum()).isEqualTo(6);
        assertThat(L.get(0)).isEqualTo(1);
        assertThat(L.get(2)).isEqualTo(3);
    }

    @Test
    public void testAddFirst() {
        IntList L = of(5);

        L.addFirst(10);
        L.addFirst(15);
        L.addFirst(0);

        assertThat(L.sum()).isEqualTo(30);
        assertThat(L.get(0)).isEqualTo(0);
        assertThat(L.get(3)).isEqualTo(5);
    }
}
