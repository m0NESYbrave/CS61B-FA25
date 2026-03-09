public class Stack {
    private static class Node {
        int item;
        Node next;

        Node(int i, Node n) {
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private int size;
    private int sum;

    public Stack() {
        sentinel = new Node(16, null);
        size = 0;
        sum = 0;
    }

    public void push(int x) {
        sentinel.next = new Node(x, sentinel.next);
        size += 1;
        sum += x;
    }

    public int pop() {
        // You may assume that the stack is never empty when pop or size is called
        int item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        size -= 1;
        sum -= item;
        return item;
    }

    public int size() {
        return size;
    }

    public int sum() {
        return sum;
    }
}
