class Solution {
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {
            "M", "CM", "D", "CD", "C", "XC",
            "L", "XL", "X", "IX", "V", "IV", "I"
        };
        
        StringBuilder sb = new StringBuilder(); 
        
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                sb.append(symbols[i]);
                num -= values[i]; 
            }
        }
        
        return sb.toString(); 
    }
}

/*
解題思路：
1. 將所有特殊羅馬數字（減法形式）與普通數字列出，按數值從大到小排列。
   例如：M(1000), CM(900), D(500), CD(400), C(100), XC(90), ...
2. 從最大數值開始，檢查當前數字 num 是否 >= 該值。
3. 若是，將對應符號添加到結果字串，並從 num 中減去該值。
4. 重複步驟 2~3，直到 num 減為 0。
5. 這種方法保證了最大數值優先處理，同時自動處理了減法規則。
6. 時間複雜度 O(1)，因為最大數字固定範圍 1~3999，符號表固定長度。
*/
