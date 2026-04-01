public class LongestRepeatingCharacter {

    //brute force
    //replacements needed = windowSize - count(most frequent char)

    /*
    window = "AABA",  maxFreq = 3 (A appears 3 times)
    replacements = 4 - 3 = 1 ≤ k(1) → valid ✓

    window = "BABB",  maxFreq = 3 (B appears 3 times)
    replacements = 4 - 3 = 1 ≤ k(1) → valid ✓

    window = "ABAB",  maxFreq = 2
    replacements = 4 - 2 = 2 > k(1) → invalid ✗
    */

    /**
     * Brute Force Approach
     * Time Complexity: O(n²) — two nested loops
     * Space Complexity: O(1) — fixed array of size 26
     */
    public int characterReplacement(String s, int k) {
        int maxLen = 0;

        // Fix the start of the window at every index i
        for (int i = 0; i < s.length(); i++) {

            // Fresh frequency count and maxFreq for each new starting point
            int[] count = new int[26];
            int maxFreq = 0;

            // Expand the window to the right one character at a time
            for (int j = i; j < s.length(); j++) {

                // Increment frequency of current character
                // e.g. 'C' - 'A' = 2 → count[2]++
                count[s.charAt(j) - 'A']++;

                // Update the highest frequency of any character in current window
                maxFreq = Math.max(maxFreq, count[s.charAt(j) - 'A']);

                // Window size from i to j
                int windowSize = j - i + 1;

                // Check if this window is valid:
                // replacements needed = windowSize - maxFreq
                // If replacements ≤ k → we can make all chars the same
                if (windowSize - maxFreq <= k) {
                    maxLen = Math.max(maxLen, windowSize);
                } else {
                    // Window is invalid and will only grow larger → no point continuing
                    break;
                }
            }
        }
        return maxLen;
    }


    /**
     * Optimised Sliding Window Approach
     * - Same validity formula: windowSize - maxFreq <= k
     * - Use two pointers (left, right) instead of nested loops
     * - Expand right always, shrink left only when window becomes invalid
     * - Time Complexity: O(n) — single pass
     * - Space Complexity: O(1) — fixed array of size 26
     */
    public int characterReplacementV2(String s, int k) {

        // Frequency count of each character in the current window
        int[] count = new int[26];

        int left = 0;       // Left boundary of the sliding window
        int maxFreq = 0;    // Highest frequency of any single char in the window
        int maxLen = 0;     // Result: longest valid window found so far

        // Expand the window by moving right pointer forward
        for (int right = 0; right < s.length(); right++) {  // BUG FIX: right-- → right++

            // Add the new character (at right) into the window
            count[s.charAt(right) - 'A']++;

            // Update maxFreq — the most frequent char in the current window
            maxFreq = Math.max(maxFreq, count[s.charAt(right) - 'A']);

            // Current window size
            int windowSize = right - left + 1;

            // If window is invalid (needs more than k replacements)
            // → shrink from the left by one step
            if (windowSize - maxFreq > k) {
                // Remove the leftmost character from the window
                count[s.charAt(left) - 'A']--;
                left++; // Slide the left boundary forward
            }

            // Window is now valid → update the max length
            // Note: window size stays the same or grows, never shrinks
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        LongestRepeatingCharacter solution = new LongestRepeatingCharacter();

        // Test Case 1: Expected 4 → "AABA" replace 1 'B' → "AAAA"
        System.out.println("Test 1: " + solution.characterReplacementV2("AABABBA", 1)); // 4

        // Test Case 2: Expected 4 → "ABAB" replace 2 → "AAAA"
        System.out.println("Test 2: " + solution.characterReplacementV2("ABAB", 2)); // 4

        // Test Case 3: Expected 2 → only 1 replacement allowed
        System.out.println("Test 3: " + solution.characterReplacementV2("ABCD", 1)); // 2
    }

}
    

