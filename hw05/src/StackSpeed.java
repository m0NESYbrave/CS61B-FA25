public class StackSpeed {
    public static void main(String[] args) {
        int N = 1000000;
        Stack stack = new Stack();
        long startMs = System.currentTimeMillis();

        for (int i = 0; i < N; i++) {
            stack.push(i);
        }

        long endMs = System.currentTimeMillis();
        System.out.println("Push 1000000 took " + (endMs - startMs) + " millliseconds.");

        startMs = System.currentTimeMillis();

        for (int i = 0; i < N; i++) {
            stack.pop();
        }

        endMs = System.currentTimeMillis();
        System.out.println("Pop 1000000 took " + (endMs - startMs) + " millliseconds.");

        N = 10000000;
        startMs = System.currentTimeMillis();

        for (int i = 0; i < N; i++) {
            stack.push(i);
        }

        endMs = System.currentTimeMillis();
        System.out.println("Push 10000000 took " + (endMs - startMs) + " millliseconds.");

        startMs = System.currentTimeMillis();

        for (int i = 0; i < N; i++) {
            stack.pop();
        }

        endMs = System.currentTimeMillis();
        System.out.println("Pop 10000000 took " + (endMs - startMs) + " millliseconds.");
    }
}
