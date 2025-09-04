class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        boolean negative = (dividend < 0) ^ (divisor < 0);

        long dvd = Math.abs((long) dividend);
        long dvs = Math.abs((long) divisor);

        int result = 0;

        while (dvd >= dvs) {
            long temp = dvs;
            int multiple = 1;

            while (dvd >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }

            dvd -= temp;       // 減去這個倍數
            result += multiple; // 累加倍數到結果
        }

        return negative ? -result : result;
    }
}

/*
解題思路：
1. 使用 long 型處理邊界溢位問題。
2. 判斷結果符號，使用 XOR 判斷異號。
3. 利用位運算倍增減法加速減法：
   - 每次找最大的 2^x 倍的除數 <= 被除數，減掉並累加倍數。
4. 返回結果並加上符號。
範例：
dividend=10, divisor=3
temp=3, multiple=1 → dvd=7, result=1
temp=6, multiple=2 → dvd=1, result=3
返回 3
*/
