import java.util.HashMap;
import java.util.Map;

class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int total = 0; 
        int prevValue = 0; 
        for (int i = s.length() - 1; i >= 0; i--) {
            int currValue = romanMap.get(s.charAt(i));

            if (currValue < prevValue) {
                total -= currValue;
            } else {
                total += currValue;
            }

            prevValue = currValue;
        }

        return total;
    }
}

/*
解題思路：
1. 建立羅馬數字與整數值對應的映射。
2. 從右到左遍歷字串：
   - 若當前數字小於其右側數字，則依減法規則減去當前數字。
   - 否則加上當前數字。
3. 使用 prevValue 變數記錄上一個數字，方便判斷是否為減法情況。
4. 遍歷完成後得到最終整數值。
5. 時間複雜度 O(n)，空間複雜度 O(1)（除去映射表大小固定）。
*/
