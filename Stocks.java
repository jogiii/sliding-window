public class Stocks {
     /**
     * Finds the maximum profit from a single buy and sell transaction.
     *
     * Approach: Brute Force
     * - For every pair (i, j) where i < j, calculate profit as prices[j] - prices[i]
     *   (buy on day i, sell on day j)
     * - Track the maximum profit seen across all valid pairs
     * - Time Complexity: O(n²) — two nested loops over all pairs
     * - Space Complexity: O(1) — only a single variable to track max profit
     */

    public int maxProfit(int[] prices){
        int maxProfit =0;

        for(int i=0; i<prices.length; i++){
            for (int j=i+1;j<prices.length;j++){
                if((prices[j] - prices[i]) >maxProfit){
                    maxProfit = prices[j] - prices[i];
                }
            }
        }
        return maxProfit;
    }


    public static void main(String[] args) {
        Stocks solution = new Stocks();

        // Test Case 1: Expected 5 → buy at 1, sell at 6
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Test 1: " + solution.maxProfit(prices1)); // 5

        // Test Case 2: Expected 0 → prices always decrease, no profitable transaction
        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println("Test 2: " + solution.maxProfit(prices2)); // 0

        // Test Case 3: Expected 3 → buy at 2, sell at 5
        int[] prices3 = {2, 4, 1, 5};
        System.out.println("Test 3: " + solution.maxProfit(prices3)); // 4

        // Test Case 4: Single price, no transaction possible
        int[] prices4 = {5};
    }
    
}
