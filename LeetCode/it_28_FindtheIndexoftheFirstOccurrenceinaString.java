class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0; 

        int n = haystack.length();
        int m = needle.length();

        for (int i = 0; i <= n - m; i++) { 
            if (haystack.substring(i, i + m).equals(needle)) {
                return i; 
            }
        }

        return -1; 
    }
}

/*
解題思路：
1. 如果 needle 是空字串，回傳 0。
2. 在 haystack 上滑動長度為 needle.length() 的窗口。
3. 如果子字串與 needle 相同，回傳索引。
4. 遍歷完成仍沒找到，回傳 -1。
範例：
haystack = "sadbutsad", needle = "sad"
i=0 → haystack[0:3]="sad" == needle → 回傳 0
*/
