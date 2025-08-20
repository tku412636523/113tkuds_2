import java.util.*;

public class M01_BuildHeap {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = sc.next();        
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        sc.close();

        boolean isMax = type.equals("max");
        buildHeap(arr, isMax);

        // 輸出堆陣列
        for (int i = 0; i < n; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    // 自底向上建堆
    static void buildHeap(int[] arr, boolean isMax) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, isMax);
        }
    }

    static void heapify(int[] arr, int n, int i, boolean isMax) {
        int target = i; 
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && compare(arr[left], arr[target], isMax)) {
            target = left;
        }
        if (right < n && compare(arr[right], arr[target], isMax)) {
            target = right;
        }
        if (target != i) {
            int tmp = arr[i];
            arr[i] = arr[target];
            arr[target] = tmp;
            heapify(arr, n, target, isMax);
        }
    }

    static boolean compare(int a, int b, boolean isMax) {
        if (isMax) return a > b; 
        else return a < b;    
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * 1) 自底向上建堆從 n/2 個非葉節點呼叫 heapify。
 * 2) heapify 在高度 h 的節點最多花費 O(h)。
 * 3) 各層節點數 * O(高度) = n/2 *1 + n/4 *2 + n/8 *3 + ... ≈ 2n = O(n)。
 */
