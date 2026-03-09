public class IntList {
    int first;
    IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    /** Return the size of the list using... recursion! */
    public int size() {
        if (rest == null) {
            return 1;
        }
        return 1 + this.rest.size();
    }

    /** Return the size of the list using no recursion! */
    public int iterativeSize() {
        IntList p = this;
        int totalSize = 0;
        while (p != null) {
            totalSize += 1;
            p = p.rest;
        }
        return totalSize;
    }

    /** Returns the ith item of this IntList. */
    public int get(int i) {
        if (i == 0) {
            return first;
        }
        return rest.get(i - 1);
    }

    /**
     * Returns an IntList identical to L, but with
     * each element incremented by x. L is not allowed
     * to change. Must use recursion.
     *
     * This method is non-destructive, i.e. it must not modify the original list.
     */
    public static IntList incrRecursiveNondestructive(IntList L, int x) {
        //IntList Q = new IntList(L.first + x, null);
        //incrRecursiveNondestructiveHelper(L.rest, Q, x);
        //return Q;
        if (L == null) {
            return null;
        }
        return new IntList(L.first + x, incrRecursiveNondestructive(L.rest, x));
    }

    private static void incrRecursiveNondestructiveHelper(IntList L, IntList Q, int x) {
        if (L != null) {
            Q.rest = new IntList(L.first + x, null);
            incrRecursiveNondestructiveHelper(L.rest, Q.rest, x);
        }
    }

    /**
     * Returns an IntList identical to L, but with
     * each element incremented by x. Modifies the original list.
     * You are not allowed to use "new" in this method.
     */
    public static IntList incrRecursiveDestructive(IntList L, int x) {
        if (L == null) {
            return null;
        }
        L.first += x;
        L.rest = incrRecursiveDestructive(L.rest, x);
        return L;
    }

    /**
     * Returns an IntList identical to L, but with
     * each element incremented by x. Not allowed
     * to use recursion. May not modify the original list.
     */
    public static IntList incrIterativeNondestructive(IntList L, int x) {
        IntList Q = new IntList(L.first + x, null);
        IntList P = Q;
        for (IntList temp = L.rest; temp != null; temp = temp.rest) {
            P.rest = new IntList(temp.first + x, null);
            P = P.rest;
        }
        return Q;
    }

    /**
     * Returns an IntList identical to L, but with
     * each element incremented by x. Not allowed
     * to use recursion. Modifies the original list.
     * You are not allowed to use "new" in this method.
     */
    public static IntList incrIterativeDestructive(IntList L, int x) {
        for (IntList temp = L; temp != null; temp = temp.rest) {
            temp.first += x;
        }
        return L;
    }

    /**
     * Returns an IntList consisting of the elements of L1 followed by the
     * elements of L2.
     */
    public static IntList concatenate(IntList L1, IntList L2) {
        if (L1 == null) {
            return L2;
        } else if (L2 == null) {
            return L1;
        }
        L1.rest = concatenate(L1.rest, L2);
        return L1;


        //IntList temp = L1;
        //while (temp.rest != null) {
        //    temp = temp.rest;
        //}
        //temp.rest = L2;
        //return L1;
    }

    /*
     * =================================================================
     * OPTIONAL METHODS
     * =================================================================
     */

    // Assume IntList has at least one item
    /**
     * Returns the sum of all elements in the IntList.
     */
    public int sum() {
        if (this.rest == null) {
            return this.first;
        }
        return this.first + this.rest.sum();
    }

    /**
     * Destructively adds x to the end of the list.
     */
    public void addLast(int x) {
        addLast(this, x);
    }

    private void addLast(IntList L, int x) {
        if (L.rest == null) {
            L.rest = new IntList(x, null);
            return;
        }
        addLast(L.rest, x);
    }

    /**
     * Destructively adds x to the front of this IntList.
     * This is a bit tricky to implement. The standard way to do this would be
     * to return a new IntList, but for practice, this implementation should
     * be destructive.
     */
    public void addFirst(int x) {
        this.rest = new IntList(x, this.rest); // e.g. 1 -> 2 -> 3 now 1 -> 4 -> 2 -> 3

        // swap values
        int temp = this.first;
        this.first = this.rest.first;
        this.rest.first = temp;
    }
}
