import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, String current, int open, int close, int max) {
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }

        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max);
        }

        if (close < open) {
            backtrack(result, current + ")", open, close + 1, max);
        }
    }
}

/*
解題思路：
1. 使用回溯 (backtracking)。
2. 保證在任何時刻：右括號數量 <= 左括號數量。
3. 每次遞迴嘗試加入 '(' 或 ')'，直到長度達到 2n。
4. 時間複雜度為 O(4^n / sqrt(n))，對應卡特蘭數。
*/
