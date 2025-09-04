class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return result;

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i <= s.length() - totalLen; i++) {
            Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            while (j < wordCount) {
                String word = s.substring(i + j * wordLen, i + (j + 1) * wordLen);
                if (!wordFreq.containsKey(word)) break;

                seen.put(word, seen.getOrDefault(word, 0) + 1);
                if (seen.get(word) > wordFreq.get(word)) break;

                j++;
            }
            if (j == wordCount) result.add(i); 
        }

        return result;
    }
}

/*
解題思路：
1. 每個 word 長度相同，總長度 totalLen = wordLen * wordCount。
2. 對每個可能起點 i 提取 totalLen 長度的子串。
3. 將子串分割成 wordLen 的小段，檢查每個 word 是否符合出現次數。
4. 如果完全匹配，將起點 i 加入結果。
範例：
s = "barfoothefoobarman", words = ["foo","bar"]
i=0 → "barfoo" → 匹配 ["bar","foo"] → 加入 0
i=1 → "arfoot" → 不匹配
i=9 → "foobar" → 匹配 ["foo","bar"] → 加入 9
結果 = [0,9]
*/
