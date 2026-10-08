class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int depth = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                // If depth > 0, this '(' is not the outermost one
                if (depth > 0) {
                    sb.append(c);
                }
                depth++;
            } else {
                depth--;
                // If depth > 0, this ')' is not the outermost one
                if (depth > 0) {
                    sb.append(c);
                }
            }
        }
        
        return sb.toString();
    }
}