class Solution {
    public int reverseDegree(String s) {
        int total = 0;
        for(int i = 0 ; i < s.length() ; i++){
            char ch = s.charAt(i);
            int alphabetPosition = ch-'a'+1;
            int reversedPosition = 27-alphabetPosition;
            int stringPosition = i+1;
            total += reversedPosition*stringPosition;
        }
        return total;
    }
}