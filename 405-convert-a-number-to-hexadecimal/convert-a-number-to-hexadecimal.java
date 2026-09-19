class Solution {
    public String toHex(int num) {
        if (num == 0){
              return "0";
        }
        String digits = "0123456789abcdef";
        StringBuilder result = new StringBuilder();
        for(int i = 0 ; i < 8 && num != 0 ; i++){
            int digit = num & 15;
            result.append(digits.charAt(digit));
            num = num >>> 4;
        }        
        return result.reverse().toString();
    }
}