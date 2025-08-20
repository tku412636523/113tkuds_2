import java.util.*;

public class M04_TieredTaxSimple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        long totalTax = 0; 

        for (int i = 0; i < n; i++) {
            long income = sc.nextLong();
            long tax = calcTax(income);
            totalTax += tax;
            System.out.println("Tax: " + tax);
        }

        long avg = totalTax / n;
        System.out.println("Average: " + avg);

        sc.close();
    }

    static long calcTax(long income) {
        long tax = 0;

        if (income > 1_000_000) {
            tax += (income - 1_000_000) * 30 / 100;
            income = 1_000_000;
        }
        if (income > 500_000) {
            tax += (income - 500_000) * 20 / 100;
            income = 500_000;
        }
        if (income > 120_000) {
            tax += (income - 120_000) * 12 / 100;
            income = 120_000;
        }
        tax += income * 5 / 100;

        return tax;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * 1) 共 n 筆收入，每筆僅需依稅率區間進行常數次比較與加法，成本 O(1)。
 * 2) 因此整體時間複雜度為 O(n)。
 * 3) 額外使用 O(1) 記憶體計算稅額與平均。
 */
