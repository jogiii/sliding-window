import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate2 {


/**
     * Checks if any two distinct indices i and j exist in the array such that:
     * - nums[i] == nums[j]
     * - The absolute difference between i and j is at most k
     *
     * Approach: Brute Force with Sliding Window Constraint
     * - For each element at index i, look back at most k elements (j from i-1 down to i-k)
     * - If any of those previous elements match nums[i], return true
     * - Time Complexity: O(n * k)
     * - Space Complexity: O(1)
     */
    public boolean containsNearbyDuplicate(int[] nums, int k){


// Iterate through each element in the array
        for(int i=0;i<nums.length;i++){
            // Look back from i-1, but only within a window of size k (i.e., i-j <= k)
            for(int j = i-1;j>=0 && (i-j)<=k;j--){
                // If a duplicate is found within the k-distance window, return true
                if(nums[i]==nums[j])
                    return true;

            }
        }
        return false;
    }


    public boolean containsNearbyDuplicates2(int[] nums, int k){

        Set<Integer> window = new HashSet<>();

        for(int i=0;i<nums.length;i++){
            if(window.contains(nums[i]))
                return true;

            window.add(nums[i]);

            if(window.size()>k){
                window.remove(nums[i-k]);
            }
        }
        return false;









    }

    public static void main(String[] args) {
        ContainsDuplicate2 solution = new ContainsDuplicate2();

        // Test Case 1: Expected true → nums[0]==nums[3]==1, and |0-3|=3 <= k=3
        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Test 1: " + solution.containsNearbyDuplicates2(nums1, 3)); // true

        // Test Case 2: Expected true → nums[0]==nums[3]==1, and |0-3|=3 <= k=4
        int[] nums2 = {1, 0, 1, 1};
        System.out.println("Test 2: " + solution.containsNearbyDuplicates2(nums2, 1)); // true

        // Test Case 3: Expected false → duplicates exist but not within k distance
        int[] nums3 = {1, 2, 3, 1, 2, 3};
        System.out.println("Test 3: " + solution.containsNearbyDuplicates2(nums3, 2)); // false

        // Test Case 4: Expected false → no duplicates at all
        int[] nums4 = {1, 2, 3, 4};
        System.out.println("Test 4: " + solution.containsNearbyDuplicates2(nums4, 2)); // false
    }
    
}
