class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int slow = 0; 

        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                slow++;           
                nums[slow] = nums[fast]; 
            }
        }

        return slow + 1; 
    }
}

/*
解題思路：
1. 使用雙指標，slow 指向最後一個唯一元素。
2. fast 遍歷陣列，遇到新元素就放到 slow 的下一個位置。
3. 原地修改陣列，返回唯一元素數量。
範例：
nums = [0,0,1,1,1,2,2,3,3,4]
slow=0, fast=1 → nums[1]=0 == nums[0] → 不動
fast=2 → nums[2]=1 != nums[0] → slow=1, nums[1]=1
fast=3 → nums[3]=1 == nums[1] → 不動
fast=5 → nums[5]=2 != nums[1] → slow=2, nums[2]=2
... 最後 k = 5, nums = [0,1,2,3,4,...]
*/
