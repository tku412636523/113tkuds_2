import java.util.*;

public class KthSmallestElement {

    public static int kthSmallestMaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int num : arr) {
            maxHeap.add(num);
            if (maxHeap.size() > k) {
                maxHeap.poll(); 
            }
        }
        return maxHeap.peek();
    }

    public static int kthSmallestMinHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            minHeap.add(num);
        }
        for (int i = 1; i < k; i++) {
            minHeap.poll(); 
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] arr1 = {7, 10, 4, 3, 20, 15};
        System.out.println("方法1 (Max Heap)：K=3 → " + kthSmallestMaxHeap(arr1, 3));
        System.out.println("方法2 (Min Heap)：K=3 → " + kthSmallestMinHeap(arr1, 3));

        int[] arr2 = {1};
        System.out.println("方法1：K=1 → " + kthSmallestMaxHeap(arr2, 1));
        System.out.println("方法2：K=1 → " + kthSmallestMinHeap(arr2, 1));

        int[] arr3 = {3, 1, 4, 1, 5, 9, 2, 6};
        System.out.println("方法1：K=4 → " + kthSmallestMaxHeap(arr3, 4));
        System.out.println("方法2：K=4 → " + kthSmallestMinHeap(arr3, 4));
    }
}
