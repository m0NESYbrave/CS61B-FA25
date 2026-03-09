import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufForBackWash;
    private boolean[] isOpen;
    private int numberOfOpenSites;
    private int N;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N > 0");
        }
        // Two dummy sites, one represents the top row, the other one represents the bottom row
        uf = new WeightedQuickUnionUF(N * N + 2);
        // Prevent backwash, only one dummy site representing the top row
        ufForBackWash = new WeightedQuickUnionUF(N * N + 1);
        isOpen = new boolean[N * N];
        numberOfOpenSites = 0;
        this.N = N;
    }

    public void open(int row, int col) {
        validate(row, col);
        int index = index(row, col);
        isOpen[index] = true;
        numberOfOpenSites += 1;

        if (row == 0) { // if the site is at the top row
            uf.union(index, N * N);
            ufForBackWash.union(index, N * N);
        }
        // no else-if since one-block has only one row which is both top and bottom
        if (row == N - 1) { // if the site is at the bottom row
            uf.union(index, N * N + 1);
        }

        if (row > 0 && isOpen[index(row - 1, col)]) { // union with the above site if it's open
            uf.union(index, index(row - 1, col));
            ufForBackWash.union(index, index(row - 1, col));
        }
        if (row < N - 1 && isOpen[index(row + 1, col)]) { // bottom
            uf.union(index, index(row + 1, col));
            ufForBackWash.union(index, index(row + 1, col));
        }
        if (col > 0 && isOpen[index(row, col - 1)]) { // left
            uf.union(index, index(row, col - 1));
            ufForBackWash.union(index, index(row, col - 1));
        }
        if (col < N - 1 && isOpen[index(row, col + 1)]) { // right
            uf.union(index, index(row, col + 1));
            ufForBackWash.union(index, index(row, col + 1));
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return isOpen[index(row, col)];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return ufForBackWash.find(index(row, col)) == ufForBackWash.find(N * N);
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        // If two dummy sites connected, then percolates.
        return uf.find(N * N) == uf.find(N * N + 1);
    }

    private void validate(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("[0, N - 1]");
        }
    }

    private int index(int row, int col) {
        return row * N + col;
    }
}
