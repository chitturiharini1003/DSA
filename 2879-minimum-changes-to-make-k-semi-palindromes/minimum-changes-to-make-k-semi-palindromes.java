import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        
        // Precompute proper divisors for each number up to n
        List<Integer>[] divisors = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            divisors[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 2 * i; j <= n; j += i) {
                divisors[j].add(i);
            }
        }
        
        // cost[i][j] stores the minimum changes to make substring s[i...j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    cost[i][j] = 1000000000; // Length 1 cannot be a semi-palindrome
                } else {
                    cost[i][j] = getCost(s, i, j, divisors);
                }
            }
        }
        
        // dp[i][j] stores the minimum changes to partition the prefix of length i into j parts
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], 1000000000);
        }
        
        // Base case: 1 part for prefix of length i (from index 0 to i - 1)
        for (int i = 1; i <= n; i++) {
            dp[i][1] = cost[0][i - 1];
        }
        
        // DP transitions
        for (int j = 2; j <= k; j++) {
            for (int i = j; i <= n; i++) {
                for (int h = j - 1; h < i; h++) {
                    dp[i][j] = Math.min(dp[i][j], dp[h][j - 1] + cost[h][i - 1]);
                }
            }
        }
        
        return dp[n][k];
    }
    
    private int getCost(String s, int l, int r, List<Integer>[] divisors) {
        int m = r - l + 1;
        int minCost = Integer.MAX_VALUE;
        
        // Check all proper divisors of length m
        for (int d : divisors[m]) {
            int currentCost = 0;
            for (int offset = 0; offset < d; offset++) {
                int left = l + offset;
                int right = r - d + 1 + offset;
                while (left < right) {
                    if (s.charAt(left) != s.charAt(right)) {
                        currentCost++;
                    }
                    left += d;
                    right -= d;
                }
            }
            minCost = Math.min(minCost, currentCost);
        }
        return minCost;
    }
}