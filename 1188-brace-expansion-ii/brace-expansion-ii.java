import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Stack<Set<String>> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (i > 0) {
                char prev = expression.charAt(i - 1);
                if ((Character.isLetter(prev) || prev == '}') && (Character.isLetter(ch) || ch == '{')) {
                    while (!operatorStack.isEmpty() && operatorStack.peek() == '.') {
                        evaluate(operandStack, operatorStack.pop());
                    }
                    operatorStack.push('.');
                }
            }

            if (Character.isLetter(ch)) {
                Set<String> set = new HashSet<>();
                set.add(String.valueOf(ch));
                operandStack.push(set);
            } else if (ch == '{') {
                operatorStack.push('{');
            } else if (ch == ',') {
                while (!operatorStack.isEmpty() && (operatorStack.peek() == '.' || operatorStack.peek() == ',')) {
                    evaluate(operandStack, operatorStack.pop());
                }
                operatorStack.push(',');
            } else if (ch == '}') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '{') {
                    evaluate(operandStack, operatorStack.pop());
                }
                if (!operatorStack.isEmpty() && operatorStack.peek() == '{') {
                    operatorStack.pop();
                }
            }
        }

        while (!operatorStack.isEmpty()) {
            evaluate(operandStack, operatorStack.pop());
        }

        Set<String> resultSet = new TreeSet<>(operandStack.pop());
        return new ArrayList<>(resultSet);
    }

    private void evaluate(Stack<Set<String>> operandStack, char op) {
        Set<String> right = operandStack.pop();
        Set<String> left = operandStack.pop();

        Set<String> result = new HashSet<>();

        if (op == '.') {
            for (String l : left) {
                for (String r : right) {
                    result.add(l + r);
                }
            }
        } else if (op == ',') {
            result.addAll(left);
            result.addAll(right);
        }

        operandStack.push(result);
    }
}