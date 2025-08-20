import java.util.*;

public class M06_PalindromeClean {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        sc.close();

        int left = 0, right = s.length() - 1;
        boolean isPalindrome = true;

        while (left < right) {
            while (left < right && !Character.isLetter(s.charAt(left))) left++;
            while (left < right && !Character.isLetter(s.charAt(right))) right--;

            if (left < right) {
                char c1 = Character.toLowerCase(s.charAt(left));
                char c2 = Character.toLowerCase(s.charAt(right));
                if (c1 != c2) {
                    isPalindrome = false;
                    break;
                }
                left++;
                right--;
            }
        }

        System.out.println(isPalindrome ? "Yes" : "No");
    }
}
