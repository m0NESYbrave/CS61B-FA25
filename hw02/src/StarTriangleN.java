public class StarTriangleN {
   /**
     * Prints a right-aligned triangle of stars ('*') with N lines.
     * The first row contains 1 star, the second 2 stars, and so on. 
     */
   public static void starTriangle(int N) {
      for (int i = 0; i < N; i++) {
         for (int j = 0; j < N - i - 1; j++) {
            System.out.print(" ");
         }
         for (int k = 0; k < i + 1; k++) {
            System.out.print("*");
         }
         System.out.println();
      }
   }
   
   public static void main(String[] args) {
      starTriangle(7);
   }
}