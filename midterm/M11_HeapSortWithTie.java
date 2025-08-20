/*
 * Time Complexity: O(n log n)
 * 說明：
 * 1. 建立最大堆（Max-Heap）需 O(n)  
 * 2. 每次取出堆頂元素，堆調整需 O(log n)，共 n 次 → O(n log n)  
 * 3. 總計複雜度 O(n log n)，額外空間 O(n) 用於封裝 (score,index)
 */

import java.util.*;

class Student implements Comparable<Student> {
    int score;
    int index;
    Student(int score, int index) {
        this.score = score;
        this.index = index;
    }

    public int compareTo(Student other) {
        if (this.score != other.score) return other.score - this.score; 
        return this.index - other.index; 
    }
}

public class M11_HeapSortWithTie {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Student[] students = new Student[n];
        for (int i = 0; i < n; i++) {
            int score = sc.nextInt();
            students[i] = new Student(score, i);
        }

        PriorityQueue<Student> heap = new PriorityQueue<>();
        for (Student s : students) heap.add(s);

        int[] sorted = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            sorted[i] = heap.poll().score;
        }

        for (int i = 0; i < n; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(sorted[i]);
        }
    }
}
