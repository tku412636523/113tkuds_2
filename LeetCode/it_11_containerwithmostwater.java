class Solution {
    public int maxArea(int[] height) {
        int left = 0;               
        int right = height.length - 1; 
        int maxArea = 0;             

        while (left < right) {
            int width = right - left;
            int currentHeight = Math.min(height[left], height[right]); 
            int area = width * currentHeight;
            maxArea = Math.max(maxArea, area); 
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}

/*
解題思路：
1. 使用雙指標法：一個指向陣列最左，一個指向最右。
2. 計算當前容器面積，更新最大值。
3. 移動較矮的一側指標，因為移動高的一側不會增加容器面積。
4. 重複以上步驟直到左右指標相遇。
5. 時間複雜度 O(n)，空間複雜度 O(1)。
*/
