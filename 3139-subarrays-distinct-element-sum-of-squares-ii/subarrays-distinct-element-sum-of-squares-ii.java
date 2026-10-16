import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = 1_000_000_007;
    private long[] tree;
    private long[] lazy;
    private long[] sum;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        tree = new long[4 * n];
        lazy = new long[4 * n];
        sum = new long[4 * n];

        Map<Integer, Integer> lastPos = new HashMap<>();
        long totalSum = 0;
        long currentSum = 0;

        for (int i = 0; i < n; i++) {
            int prev = lastPos.getOrDefault(nums[i], -1);
            update(1, 0, n - 1, prev + 1, i, 1);
            currentSum = query(1, 0, n - 1, 0, i);
            totalSum = (totalSum + currentSum) % MOD;
            lastPos.put(nums[i], i);
        }

        return (int) totalSum;
    }

    private void pushDown(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = l + (r - l) / 2;
            apply(node * 2, l, mid, lazy[node]);
            apply(node * 2 + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void apply(int node, int l, int r, long val) {
        long len = r - l + 1;
        tree[node] = (tree[node] + 2 * val * sum[node] + val * val * len) % MOD;
        sum[node] = (sum[node] + val * len) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void update(int node, int l, int r, int ql, int qr, long val) {
        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            return;
        }
        pushDown(node, l, r);
        int mid = l + (r - l) / 2;
        if (ql <= mid) {
            update(node * 2, l, mid, ql, qr, val);
        }
        if (qr > mid) {
            update(node * 2 + 1, mid + 1, r, ql, qr, val);
        }
        tree[node] = (tree[node * 2] + tree[node * 2 + 1]) % MOD;
        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
    }

    private long query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[node];
        }
        pushDown(node, l, r);
        int mid = l + (r - l) / 2;
        long res = 0;
        if (ql <= mid) {
            res = (res + query(node * 2, l, mid, ql, qr)) % MOD;
        }
        if (qr > mid) {
            res = (res + query(node * 2 + 1, mid + 1, r, ql, qr)) % MOD;
        }
        return res;
    }
}