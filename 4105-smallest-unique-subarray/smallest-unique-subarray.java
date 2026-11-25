import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final long BASE1 = 131L;
    private static final long MOD1 = 1_000_000_007L;
    private static final long BASE2 = 137L;
    private static final long MOD2 = 1_000_000_009L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        long[] p1 = new long[n + 1];
        long[] p2 = new long[n + 1];
        long[] h1 = new long[n + 1];
        long[] h2 = new long[n + 1];

        p1[0] = 1;
        p2[0] = 1;
        for (int i = 0; i < n; i++) {
            p1[i + 1] = (p1[i] * BASE1) % MOD1;
            p2[i + 1] = (p2[i] * BASE2) % MOD2;
            h1[i + 1] = (h1[i] * BASE1 + nums[i]) % MOD1;
            h2[i + 1] = (h2[i] * BASE2 + nums[i]) % MOD2;
        }

        int left = 1;
        int right = n;
        int ans = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (hasUnique(mid, n, h1, h2, p1, p2)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUnique(int len, int n, long[] h1, long[] h2, long[] p1, long[] p2) {
        Map<Long, Integer> freq = new HashMap<>();
        for (int i = 0; i + len <= n; i++) {
            int j = i + len;
            long v1 = (h1[j] - (h1[i] * p1[len]) % MOD1 + MOD1) % MOD1;
            long v2 = (h2[j] - (h2[i] * p2[len]) % MOD2 + MOD2) % MOD2;
            long key = (v1 << 32) ^ v2;
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }

        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }
        return false;
    }
}