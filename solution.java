import java.util.Scanner;

public class Main {

    //@ requires t > 0;
    //@ requires (\forall int i; 0 <= i && i < t; (\exists int n; n >= 0));
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t-- > 0) {
            // Read the number of messages in this test case
            int n = scanner.nextInt();

            // Read the list of message times
            int[] a = new int[n+2];
            a[0] = 0;
            for (int i = 1; i <= n; i++) {
                a[i] = scanner.nextInt();
            }
            a[n+1] = 1440;

            // Count the number of walks that can be scheduled between consecutive messages
            int ans = count_walks(a);

            // Output the result for this test case
            if (ans >= 2) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    //@ requires a != null && a.length >= 2;
    //@ ensures \result >= 0;
    private static int count_walks(int[] a) {
        // Add two extra elements to the array representing the start and end of the day.
        int n = a.length;
        int[] b = new int[n+2];
        b[0] = 0;
        b[n+1] = 1440;
        System.arraycopy(a, 0, b, 1, n);

        // Count the number of walks that can be scheduled between consecutive messages,
        // and return the sum of these counts.
        int ans = 0;
        for (int i = 0; i < n+1; i++) {
            int walk_length = b[i+1] - b[i];
            int walks_possible = walk_length / 120;
            ans += walks_possible;
        }

        return ans;
    }
}

/*
Algorithm:

Read the number of test cases.
For each test case:
a. Read the number of messages.
b. Read the list of message times.
c. Add two extra elements to the list representing the start and end of the day.
d. For each consecutive pair of elements, count the number of walks that can be scheduled
   between them, by dividing the time between them by the length of a walk.
e. Return the sum of all the counts.
f. If the sum is at least 2, output YES. Otherwise, output NO.

Time complexity: O(n), where n is the length of the input array.
Space complexity: O(n), since we create a new array with two extra elements.
*/

