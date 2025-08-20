import java.util.*;

public class M05_GCD_LCM_Recursive {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        sc.close();

        long g = gcd(a, b);
        long l = (a / g) * b; 

        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }

    static long gcd(long x, long y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }
}

/*
 * Time Complexity: O(log(min(a, b)))
 * 說明：
 * 1) 歐幾里得演算法每次遞迴將參數縮小至少一半（取餘數）。
 * 2) 遞迴深度最多約 log(min(a, b)) 次。
 * 3) 每步操作為常數時間，因此總時間複雜度為 O(log(min(a, b)))。
 */
