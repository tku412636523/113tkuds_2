import java.util.*;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); 
        int n = nums.length;

        int closestSum = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                    closestSum = sum;
                }

                if (sum == target) {
                    return sum; 
                } else if (sum < target) {
                    left++;
                } else {
                    right--; 
                }
            }
        }

        return closestSum;
    }
}

/*
解題思路：
1. 排序 nums，方便雙指標遍歷。
2. 遍歷每個元素 nums[i] 作為三元組中的第一個數。
3. 使用雙指標 left = i+1, right = n-1。
   - 計算 sum = nums[i] + nums[left] + nums[right]。
   - 若 |sum - target| 更小，更新 closestSum。
   - 若 sum == target，直接返回。
   - 若 sum < target，左指標右移；否則右指標左移。
4. 遍歷完成後，返回 closestSum。
5. 時間複雜度 O(n^2)，空間複雜度 O(1)。
*/
