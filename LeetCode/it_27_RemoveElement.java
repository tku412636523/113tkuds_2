class Solution {
    public int removeElement(int[] nums, int val) {
        int slow = 0; 

        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }

        return slow; 
    }
}

/*
解題思路：
1. 使用雙指標，slow 指向新陣列位置，fast 遍歷原陣列。
2. 遇到非 val 元素，放到 nums[slow]，並 slow++。
3. 最後 slow 就是剩餘元素數量。
範例：
nums = [0,1,2,2,3,0,4,2], val = 2
fast=0 → 0 != 2 → nums[0]=0, slow=1
fast=1 → 1 != 2 → nums[1]=1, slow=2
fast=2 → 2 == 2 → skip
fast=3 → 2 == 2 → skip
fast=4 → 3 != 2 → nums[2]=3, slow=3
fast=5 → 0 != 2 → nums[3]=0, slow=4
fast=6 → 4 != 2 → nums[4]=4, slow=5
fast=7 → 2 == 2 → skip
結果 k=5, nums = [0,1,3,0,4,...]
*/
