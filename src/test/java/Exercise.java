import java.util.Scanner;

public class Exercise {
    /**
     * Array Exploration
     * Create an Array:
     * <p>
     * Create a new array called numbers to store 5 numerical values of your choice (they can be whole numbers or decimals).
     * <p>
     * Access and Print:
     * <p>
     * Print the following:
     * <p>
     * The first element of the numbers array.
     * <p>
     * The last element of the numbers array.
     * <p>
     * Reverse Order:
     * <p>
     * Using a loop, print the elements of the numbers array in reverse order.
     * <p>
     * Bonus: Element Count:
     * <p>
     * Calculate the total number of elements within the numbers array.
     */
//    public static void main(String[] args) {
//        int[] numbers = new int[]{1, 2, 3, 4, 5};
//
//        System.out.println(numbers[0]);
//        System.out.println(numbers[numbers.length-1]);
//
//        for (int i = numbers.length-1; i >= 0 ; i--) {
//            System.out.println(numbers[i]);
//        }
//
//        int count = 0;
//        for (int i = 0; i < numbers.length; i++) {
//            count++;
//        }
//
//        System.out.println(count);
//        System.out.println(getAlternates(new int[]{1, 2, 3, 4, 5}));
//}
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = n; i > 0; i--) {
            for (int j = i; j >0; j--) {
                System.out.print("*");
            }
            System.out.println();
        }


        sc.close();
    }
}
