import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) return result;

        Arrays.sort(nums); 

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++; 
                } else {
                    right--; 
                }
            }
        }

        return result;
    }
}

/*
解題思路：
1. 先對陣列排序，方便處理重複元素與使用雙指標。
2. 遍歷陣列每個元素作為第一個數 nums[i]：
   - 若與前一個元素相同，跳過以避免重複三元組。
3. 對剩餘部分使用左右雙指標 left 和 right：
   - 計算三數和 sum = nums[i] + nums[left] + nums[right]。
   - 若 sum == 0，加入結果，並跳過重複元素。
   - 若 sum < 0，左指標右移；若 sum > 0，右指標左移。
4. 重複直到左右指標相遇。
5. 時間複雜度 O(n^2)，空間複雜度 O(1)（不計結果集）。
*/
