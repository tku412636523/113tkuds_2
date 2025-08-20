import java.util.*;

public class M02_YouBikeNextArrival {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine().trim());
        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            String t = sc.nextLine().trim();
            times[i] = toMinutes(t);
        }
        String q = sc.nextLine().trim();
        int query = toMinutes(q);
        sc.close();

        int idx = binarySearchNext(times, query);
        if (idx == -1) {
            System.out.println("No bike");
        } else {
            System.out.println(toHHMM(times[idx]));
        }
    }

    static int toMinutes(String s) {
        String[] parts = s.split(":");
        int hh = Integer.parseInt(parts[0]);
        int mm = Integer.parseInt(parts[1]);
        return hh * 60 + mm;
    }

    static String toHHMM(int minutes) {
        int hh = minutes / 60;
        int mm = minutes % 60;
        return String.format("%02d:%02d", hh, mm);
    }

    static int binarySearchNext(int[] arr, int query) {
        int lo = 0, hi = arr.length - 1;
        int ans = -1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] > query) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }
}
