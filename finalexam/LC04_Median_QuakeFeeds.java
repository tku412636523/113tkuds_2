import java.util.*;

public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        double[] A = new double[n];
        double[] B = new double[m];
        
        for (int i = 0; i < n; i++) A[i] = sc.nextDouble();
        for (int i = 0; i < m; i++) B[i] = sc.nextDouble();
        sc.close();
        
        if (n > m) {
            double[] tempArr = A; A = B; B = tempArr;
            int tempN = n; n = m; m = tempN;
        }
        
        int left = 0, right = n;
        int halfLen = (n + m + 1) / 2;
        
        while (left <= right) {
            int i = (left + right) / 2;
            int j = halfLen - i;
            
            double Aleft = (i == 0) ? Double.NEGATIVE_INFINITY : A[i - 1];
            double Aright = (i == n) ? Double.POSITIVE_INFINITY : A[i];
            double Bleft = (j == 0) ? Double.NEGATIVE_INFINITY : B[j - 1];
            double Bright = (j == m) ? Double.POSITIVE_INFINITY : B[j];
            
            if (Aleft <= Bright && Bleft <= Aright) {
                double maxLeft = Math.max(Aleft, Bleft);
                double minRight = Math.min(Aright, Bright);
                
                double median;
                if ((n + m) % 2 == 1) {
                    median = maxLeft;
                } else {
                    median = (maxLeft + minRight) / 2.0;
                }
                
                System.out.printf("%.1f\n", median);
                return;
            } else if (Aleft > Bright) {
                right = i - 1;
            } else {
                left = i + 1;
            }
        }
    }
}
