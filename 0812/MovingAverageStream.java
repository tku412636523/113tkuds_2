import java.util.*;

public class MovingAverageStream {
    private final int size;
    private final Queue<Integer> window;
    private double sum;

    private PriorityQueue<Integer> maxHeap; 
    private PriorityQueue<Integer> minHeap; 

    private PriorityQueue<Integer> minPQ;
    private PriorityQueue<Integer> maxPQ;

    private Map<Integer, Integer> delayed;

    public MovingAverageStream(int size) {
        this.size = size;
        this.window = new ArrayDeque<>();
        this.sum = 0.0;

        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<>();

        this.minPQ = new PriorityQueue<>();
        this.maxPQ = new PriorityQueue<>(Collections.reverseOrder());

        this.delayed = new HashMap<>();
    }

    public double next(int val) {
        window.offer(val);
        sum += val;

        if (maxHeap.isEmpty() || val <= maxHeap.peek()) {
            maxHeap.offer(val);
        } else {
            minHeap.offer(val);
        }
        balanceHeaps();

        minPQ.offer(val);
        maxPQ.offer(val);

        if (window.size() > size) {
            int removed = window.poll();
            sum -= removed;

            delayed.put(removed, delayed.getOrDefault(removed, 0) + 1);

            if (removed <= maxHeap.peek()) {
                prune(maxHeap);
            } else {
                prune(minHeap);
            }
            balanceHeaps();

            prune(minPQ);
            prune(maxPQ);
        }

        return sum / window.size();
    }

    public double getMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else if (maxHeap.size() < minHeap.size()) {
            return minHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    public int getMin() {
        prune(minPQ);
        return minPQ.peek();
    }

    public int getMax() {
        prune(maxPQ);
        return maxPQ.peek();
    }

    private void balanceHeaps() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
            prune(maxHeap);
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
            prune(minHeap);
        }
    }

    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && delayed.getOrDefault(heap.peek(), 0) > 0) {
            int num = heap.poll();
            delayed.put(num, delayed.get(num) - 1);
            if (delayed.get(num) == 0) {
                delayed.remove(num);
            }
        }
    }

    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println(ma.next(1)); 
        System.out.println(ma.next(10)); 
        System.out.println(ma.next(3));  
        System.out.println(ma.next(5));  
        System.out.println(ma.getMedian()); 
        System.out.println(ma.getMin());   
        System.out.println(ma.getMax());  
    }
}
