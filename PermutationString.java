import java.util.Arrays;

public class PermutationString {

    //brute force

    public boolean checkInclusion(String s1,String s2){

        int length1 = s1.length();
        int length2 = s2.length();

        if(length1>length2)
            return false;


        char[] sorted1 = s1.toCharArray();
        Arrays.sort(sorted1);

        for(int i=0;i<=length2-length1;i++){
            char[] window = s2.substring(i, i+length1).toCharArray();
            Arrays.sort(window);
            if(Arrays.equals(window, sorted1))
                return true;
        }
        return false;
    }


    public boolean checkInclusionV2(String s1, String s2){

        int length1 = s1.length(); //1 ab
        int length2 = s2.length(); //6 lecabee
        if(length1>length2)
            return false;


        int[] have = new int[26];
        int[] need = new int[26];

        //build have
        for(char c: s1.toCharArray()){
            need[c-'a']++;
        }

        for(int right=0;right<length2;right++){
            have[s2.charAt(right)-'a']++;

            if(right>=length1){
                have[s2.charAt(right - length1)-'a']--;
            }

            boolean res = Arrays.equals(need, have);
            if(res)
                return true;
        }
        return false;
    }
    
}
