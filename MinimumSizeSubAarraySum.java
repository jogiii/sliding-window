public class MinimumSizeSubAarraySum {

    //brute force

    public int minSubArrayLen(int target, int[] nums){

        int minLength = Integer.MAX_VALUE;

        for(int i =0;i<nums.length;i++){

            int sum =0;
            for(int j=i;j<nums.length;j++){

                sum += nums[j];

                if(sum >= target)
                {
                    minLength = Math.min(minLength, j -i+1);
                    break;
                }
            }
        }
    
    return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
    
}
