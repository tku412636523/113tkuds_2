class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return ""; 
        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1); 
                if (prefix.isEmpty()) return ""; 
            }
        }

        return prefix;
    }
}

/*
解題思路：
1. 使用第一個字串作為初始前綴 prefix。
2. 逐一遍歷陣列中其他字串：
   - 如果當前字串不以 prefix 開頭，縮短 prefix（刪掉最後一個字元）。
   - 重複此操作直到 prefix 成為當前字串的前綴。
3. 遍歷完成後，prefix 即為最長公共前綴。
4. 若在過程中 prefix 被縮短為空字串，直接返回 ""。
5. 時間複雜度 O(n * m)，其中 n 為字串數量，m 為最短字串長度。
   空間複雜度 O(1)（不額外使用資料結構）。
*/
