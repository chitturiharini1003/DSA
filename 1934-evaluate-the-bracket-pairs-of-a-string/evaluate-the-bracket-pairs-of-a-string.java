import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Step 1: Store knowledge pairs in a HashMap for O(1) retrieval
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder sb = new StringBuilder();
        int n = s.length();
        int i = 0;

        // Step 2: Iterate through the string s
        while (i < n) {
            char ch = s.charAt(i);

            if (ch == '(') {
                // Find the closing bracket ')'
                int j = i + 1;
                while (j < n && s.charAt(j) != ')') {
                    j++;
                }

                // Extract key between '(' and ')'
                String key = s.substring(i + 1, j);

                // Append value if found, otherwise '?'
                sb.append(map.getOrDefault(key, "?"));

                // Advance index past ')'
                i = j + 1;
            } else {
                sb.append(ch);
                i++;
            }
        }

        return sb.toString();
    }
}