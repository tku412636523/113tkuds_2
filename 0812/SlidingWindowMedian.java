import java.util.*;

public class SlidingWindowMedian {
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;
    private Map<Integer, Integer> delayed;
    private int leftSize;  
    private int rightSize; 
    public SlidingWindowMedian() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
        delayed = new HashMap<>();
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] result = new double[n - k + 1];

        for (int i = 0; i < n; i++) {
            addNum(nums[i]);
            if (i >= k) {
                removeNum(nums[i - k]);
            }
            if (i >= k - 1) {
                result[i - k + 1] = getMedian(k);
            }
        }
        return result;
    }

    private void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
            leftSize++;
        } else {
            minHeap.offer(num);
            rightSize++;
        }
        balanceHeaps();
    }

    private void removeNum(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);

        if (num <= maxHeap.peek()) {
            leftSize--;
            if (num == maxHeap.peek()) {
                prune(maxHeap);
            }
        } else {
            rightSize--;
            if (!minHeap.isEmpty() && num == minHeap.peek()) {
                prune(minHeap);
            }
        }
        balanceHeaps();
    }

    private void balanceHeaps() {
        if (leftSize > rightSize + 1) {
            minHeap.offer(maxHeap.poll());
            leftSize--;
            rightSize++;
            prune(maxHeap);
        } else if (rightSize > leftSize) {
            maxHeap.offer(minHeap.poll());
            rightSize--;
            leftSize++;
            prune(minHeap);
        }
    }

    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && delayed.containsKey(heap.peek())) {
            int num = heap.peek();
            delayed.put(num, delayed.get(num) - 1);
            if (delayed.get(num) == 0) {
                delayed.remove(num);
            }
            heap.poll();
        }
    }

    private double getMedian(int k) {
        if (k % 2 == 1) {
            return maxHeap.peek();
        } else {
            return ((double) maxHeap.peek() + (double) minHeap.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();
        System.out.println(Arrays.toString(swm.medianSlidingWindow(
                new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3))); 
        System.out.println(Arrays.toString(swm.medianSlidingWindow(
                new int[]{1, 2, 3, 4}, 2))); 
    }
}
