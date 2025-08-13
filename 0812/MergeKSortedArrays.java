import java.util.*;

public class MergeKSortedArrays {

    static class HeapNode {
        int value;
        int arrayIndex;  
        int elementIndex; 

        HeapNode(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }
    }

    public static List<Integer> mergeKSortedArrays(int[][] arrays) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<HeapNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.value));

        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.add(new HeapNode(arrays[i][0], i, 0));
            }
        }

        while (!minHeap.isEmpty()) {
            HeapNode node = minHeap.poll();
            result.add(node.value);

            int nextIndex = node.elementIndex + 1;
            if (nextIndex < arrays[node.arrayIndex].length) {
                minHeap.add(new HeapNode(arrays[node.arrayIndex][nextIndex], node.arrayIndex, nextIndex));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] arr1 = {{1,4,5}, {1,3,4}, {2,6}};
        System.out.println(mergeKSortedArrays(arr1)); 

        int[][] arr2 = {{1,2,3}, {4,5,6}, {7,8,9}};
        System.out.println(mergeKSortedArrays(arr2)); 

        int[][] arr3 = {{1}, {0}};
        System.out.println(mergeKSortedArrays(arr3)); 
    }
}
