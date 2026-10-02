import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(res, "", 0, 0, n);
        return res;
    }
    
    private void backtrack(List<String> res, String curr, int left, int right, int n) {
        if (curr.length() == 2 * n) {
            res.add(curr);
            return;
        }
        if (left < n) backtrack(res, curr + "(", left + 1, right, n);
        if (right < left) backtrack(res, curr + ")", left, right + 1, n);
    }
}
