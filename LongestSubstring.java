import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {


    public int lengthOfLongestSubstring(String s){

        Set<Character> window = new HashSet<>();
        int left =0;
        int maxLen=0;

        for(int right =0;right<s.length();right++){
            if(window.contains(s.charAt(right))){
                window.remove(s.charAt(right));
                left++;
            }

            window.add(s.charAt(right));
            maxLen = Math.max(maxLen, right-left+1);
        }
        return maxLen;
    }
    
}
