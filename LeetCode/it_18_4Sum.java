import java.util.*;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) return result;

        Arrays.sort(nums); 
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right]; 

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++; 
                    } else {
                        right--; 
                    }
                }
            }
        }

        return result;
    }
}

/*
解題思路：
1. 對陣列排序，方便處理重複與使用雙指標。
2. 用兩層迴圈固定前兩個數 nums[i]、nums[j]。
3. 在剩餘區間使用左右指標，尋找和為 target 的組合。
4. 遇到符合的四元組，加入結果，並移動指標跳過重複數字。
5. 若和小於 target，左指標右移；若大於 target，右指標左移。
6. 時間複雜度 O(n^3)，空間複雜度 O(1)（不包含輸出）。
*/
