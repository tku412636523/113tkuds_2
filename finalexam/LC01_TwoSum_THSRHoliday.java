import java.util.*;

public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] seats = new int[n];
        
        for (int i = 0; i < n; i++) {
            seats[i] = sc.nextInt();
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        boolean found = false;
        
        for (int i = 0; i < n; i++) {
            if (map.containsKey(seats[i])) {
                System.out.println(map.get(seats[i]) + " " + i);
                found = true;
                break;
            } else {
                map.put(target - seats[i], i);
            }
        }
        
        if (!found) {
            System.out.println("-1 -1");
        }
        
        sc.close();
    }
}
