class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0);
            } else {
                int innerScore = stack.pop();
                int currentScore = Math.max(2 * innerScore, 1);
                stack.push(stack.pop() + currentScore);
            }
        }

        return stack.pop();
    }
    }