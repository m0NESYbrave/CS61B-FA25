public class StackClient {
    public static Stack flipped(Stack s) {
        Stack flipped = new Stack();
        int totalSize = s.size(); // size can change so cache it first
        for (int i = 0; i < totalSize; i++) {
            flipped.push(s.pop());
        }
        return flipped;
    }
}
