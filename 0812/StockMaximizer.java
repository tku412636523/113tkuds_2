import java.util.*;

public class StockMaximizer {
    public static int maxProfit(int[] prices, int k) {
        if (prices == null || prices.length < 2 || k <= 0) return 0;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int buy = 0; 
        int sell = 0; 
        int n = prices.length;

        while (buy < n && sell < n) {
            buy = sell;
            while (buy < n - 1 && prices[buy + 1] <= prices[buy]) buy++;
            sell = buy;
            while (sell < n - 1 && prices[sell + 1] >= prices[sell]) sell++;
            if (buy < sell) {
                maxHeap.offer(prices[sell] - prices[buy]);
            }
            sell++;
        }

        int profit = 0;
        for (int i = 0; i < k && !maxHeap.isEmpty(); i++) {
            profit += maxHeap.poll();
        }

        return profit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{2,4,1}, 2)); 
        System.out.println(maxProfit(new int[]{3,2,6,5,0,3}, 2)); 
        System.out.println(maxProfit(new int[]{1,2,3,4,5}, 2)); 
    }
}
