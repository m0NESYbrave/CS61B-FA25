public class MergeSort {
    /**
     * @param arr
     *
     * Sort the array arr using merge sort.
     * The merge sort algorithm is as follows:
     * 1. Split the collection to be sorted in half.
     * 2. Recursively call merge sort on each half.
     * 3. Merge the sorted half-lists.
     *
     */
    public static int[] sort(int[] arr) {
        int len = arr.length;
        if (len == 1) {
            return arr;
        }

        int[] firstHalf = new int[len / 2];
        for (int i = 0; i < len / 2; i++) {
            firstHalf[i] = arr[i];
        }
        firstHalf = sort(firstHalf);

        int[] secondHalf = new int[len - len / 2];
        for (int i = len / 2; i < len; i++) {
            secondHalf[i - len / 2] = arr[i];
        }
        secondHalf = sort(secondHalf);

        return merge(firstHalf, secondHalf);
    }

    /**
     * @param a
     * @param b
     *
     * Merge the sorted half-lists.
     *
     * Suggested helper method that will make it easier for you to implement merge sort.
     */
    private static int[] merge(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int i = 0;
        int j = 0;
        for (int k = 0; k < c.length; k++) {
            if (i != a.length && j != b.length) {
                if (a[i] <= b[j]) {
                    c[k] = a[i++];
                } else {
                    c[k] = b[j++];
                }
            } else if (i == a.length) { // a is done, no way to iterate two arrays all at the same time
                c[k] = b[j++];
            } else { // b is done
                c[k] = a[i++];
            }
        }
        return c;
    }
}
