
import java.util.*;

class TimeEntry implements Comparable<TimeEntry> {
    int time;
    int listIndex;
    int elemIndex;
    TimeEntry(int time, int listIndex, int elemIndex) {
        this.time = time;
        this.listIndex = listIndex;
        this.elemIndex = elemIndex;
    }

    public int compareTo(TimeEntry other) {
        return this.time - other.time; // Min-Heap
    }
}

public class M12_MergeKTimeTables {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int K = sc.nextInt();
        List<int[]> lists = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int len = sc.nextInt();
            int[] arr = new int[len];
            for (int j = 0; j < len; j++) {
                arr[j] = sc.nextInt();
            }
            lists.add(arr);
        }

        PriorityQueue<TimeEntry> heap = new PriorityQueue<>();
        for (int i = 0; i < K; i++) {
            if (lists.get(i).length > 0) {
                heap.add(new TimeEntry(lists.get(i)[0], i, 0));
            }
        }

        List<Integer> merged = new ArrayList<>();
        while (!heap.isEmpty()) {
            TimeEntry curr = heap.poll();
            merged.add(curr.time);
            int nextIdx = curr.elemIndex + 1;
            if (nextIdx < lists.get(curr.listIndex).length) {
                heap.add(new TimeEntry(lists.get(curr.listIndex)[nextIdx], curr.listIndex, nextIdx));
            }
        }

        for (int i = 0; i < merged.size(); i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(merged.get(i));
        }
    }
}
