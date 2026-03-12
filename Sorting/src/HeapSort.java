public class HeapSort {
    /**
     * @param arr
     *
     * Sort the array arr using heap sort.
     * The heap sort algorithm is as follows:
     * 1. Turn the array into a heap using heapify.
     * 2. Repeatedly swap the root of the heap with the last element of the heap,
     *   then bubble down the new root in the unsorted part of the array.
     *
     * This is an in-place sort, so you shouldn't create any additional arrays.
     *
     * We've provided you with a slightly modified implementation of bubbleDown that you can use.
     * Read through it carefully and try to understand how it works.
     */
    public static void sort(int[] arr) {
        heapify(arr);
        for (int i = 0; i < arr.length; i++) {
            swap(arr, 0, arr.length - 1 - i);
            bubbleDown(arr, 0, arr.length - 1 - i);
        }
    }

    /**
     * @param arr
     *
     * Turn the array into a heap. Repeatedly calls bubble down on each element,
     * starting from the end of the array.
     *
     * Suggested helper method that will make it easier for you to implement heap sort.
     */
    private static void heapify(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            bubbleDown(arr, i, arr.length); // the index can't be >= the size of the heap
        }
    }


    /**
     * @param arr
     * @param i
     * @param limit
     *
     * Bubble down the element at index i in the array arr, stopping at index limit.
     * This allows you to bubble down only in the unsorted part of the array.
     * Any elements at indices >= limit will remain untouched.
     */
    private static void bubbleDown(int[] arr, int i, int limit) {
        int left = getLeftChild(i);
        int right = getRightChild(i);
        // I tried maxChild without limit, then we can access sorted part.
        // In the first instance, after we fix 5, the array is [2, 4, 1, 3, 5, 6, 7]
        // bubbleDown(i = 0), [4, 2, 1, 3], bubbleDown(i = 1), left is 3 and right is 4.
        // If we don't check limit in maxChild, it will find arr[4] = 5 > arr[3] = 3.
        int maxChild = max(arr, left, right, limit);
        if (left >= limit) { // if left is beyond the limit, definitely the same for right
            return;
        }
        if (arr[i] < arr[maxChild]) {
            swap(arr, i, maxChild);
            bubbleDown(arr, maxChild, limit);
        }
    }

    /**
     * @param arr
     * @param i
     * @param j
     *
     * Swap the elements at indices i and j in the array arr.
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * @param arr
     * @param i
     * @param j
     * @param limit
     * @return the index of the maximum element between the elements at
     *         indices i and j in the array arr, stopping at index limit.
     *
     */
    private static int max(int[] arr, int i, int j, int limit) {
        // If the node has left child but no right child (already sorted), then we may access sorted part.
        // So if right is beyond the limit, just return left which >= limit or < limit,
        // bubbleDown handles this.
        if (j >= limit || arr[i] > arr[j]) {
            return i;
        }
        return j;
    }


    /**
     * @param i
     * @return the index of the left child of the node at index i
     *
     * Note that this is different from what we covered in the Heaps lab, as the root
     * of the heap is at index 0, not 1.
     */
    private static int getLeftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * @param i
     * @return the index of the right child of the node at index i
     *
     * Note that this is different from what we covered in the Heaps lab, as the root
     * of the heap is at index 0, not 1.
     */
    private static int getRightChild(int i) {
        return 2 * i + 2;
    }
}
