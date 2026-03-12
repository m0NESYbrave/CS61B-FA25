import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class TestSorts {
    @Test
    public void testInsertionSort() {
        int[] arr = {6, 3, 5, 7, 2, 1, 4};
        int[] expected = {1, 2, 3, 4, 5, 6, 7};

        InsertionSort.sort(arr);
        assertThat(arr).isEqualTo(expected);
    }

    @Test
    public void testSelectionSort() {
        int[] arr = {6, 3, 5, 7, 2, 1, 4};
        int[] expected = {1, 2, 3, 4, 5, 6, 7};

        SelectionSort.sort(arr);
        assertThat(arr).isEqualTo(expected);
    }

    @Test
    public void testHeapSort() {
        int[] arr = {6, 3, 5, 7, 2, 1, 4};
        int[] expected = {1, 2, 3, 4, 5, 6, 7};

        HeapSort.sort(arr);
        assertThat(arr).isEqualTo(expected);

        arr = new int[]{32, 15, 2, 17, 19, 26, 41, 17, 17};
        expected = new int[]{2, 15, 17, 17, 17, 19, 26, 32, 41};

        HeapSort.sort(arr);
        assertThat(arr).isEqualTo(expected);

        arr = new int[]{32, 15, 2, 17, 19, 33, 41, 17, 17};
        expected = new int[]{2, 15, 17, 17, 17, 19, 32, 33, 41};

        HeapSort.sort(arr);
        assertThat(arr).isEqualTo(expected);

        arr = new int[]{1, 1, 1, 1, 1, 1};
        expected = new int[]{1, 1, 1, 1, 1, 1};

        HeapSort.sort(arr);
        assertThat(arr).isEqualTo(expected);

        arr = new int[]{6, 5, 4, 3, 2, 1};
        expected = new int[]{1, 2, 3, 4, 5, 6};

        HeapSort.sort(arr);
        assertThat(arr).isEqualTo(expected);
    }

    @Test
    public void testMergeSort() {
        int[] arr = {4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4};

        int[] res = MergeSort.sort(arr);
        assertThat(res).isEqualTo(expected);

        arr = new int[]{3, 2, 1};
        expected = new int[]{1, 2, 3};

        res = MergeSort.sort(arr);
        assertThat(res).isEqualTo(expected);

        arr = new int[]{6, 3, 5, 7, 2, 1, 4};
        expected = new int[]{1, 2, 3, 4, 5, 6, 7};

        res = MergeSort.sort(arr);
        assertThat(res).isEqualTo(expected);

        arr = new int[]{32, 15, 2, 17, 19, 26, 41, 17, 17};
        expected = new int[]{2, 15, 17, 17, 17, 19, 26, 32, 41};

        res = MergeSort.sort(arr);
        assertThat(res).isEqualTo(expected);

        arr = new int[]{1, 1, 1, 1, 1, 1};
        expected = new int[]{1, 1, 1, 1, 1, 1};

        res = MergeSort.sort(arr);
        assertThat(res).isEqualTo(expected);

        arr = new int[]{6, 5, 4, 3, 2, 1};
        expected = new int[]{1, 2, 3, 4, 5, 6};

        res = MergeSort.sort(arr);
        assertThat(res).isEqualTo(expected);
    }

    @Test
    public void testQuickSort() {
        int[] arr = {6, 8, 3, 1, 2, 7, 4, 6};
        int[] expected = {1, 2, 3, 4, 6, 6, 7, 8};

        int[] res = QuickSort.sort(arr);
        assertThat(res).isEqualTo(expected);

        arr = new int[]{6, 3, 5, 7, 2, 1, 4};
        expected = new int[]{1, 2, 3, 4, 5, 6, 7};

        res = QuickSort.sort(arr);
        assertThat(res).isEqualTo(expected);

        arr = new int[]{1, 1, 1, 1, 1, 1};
        expected = new int[]{1, 1, 1, 1, 1, 1};

        res = QuickSort.sort(arr);
        assertThat(res).isEqualTo(expected);

        arr = new int[]{6, 5, 4, 3, 2, 1};
        expected = new int[]{1, 2, 3, 4, 5, 6};

        res = QuickSort.sort(arr);
        assertThat(res).isEqualTo(expected);

        arr = new int[]{32, 15, 2, 17, 19, 26, 41, 17, 17};
        expected = new int[]{2, 15, 17, 17, 17, 19, 26, 32, 41};

        res = QuickSort.sort(arr);
        assertThat(res).isEqualTo(expected);
    }
}
