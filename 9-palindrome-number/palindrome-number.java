class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }
        if(x != 0 && x % 10 == 0){
            return false;
        }
        int reversehalf = 0;
        while(x > reversehalf){
            int digit = x % 10;
            reversehalf = reversehalf*10+digit;
            x = x/10;
        }
        return  x == reversehalf || x== reversehalf/10;
        
    }
}