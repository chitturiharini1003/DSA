class Solution {
    static class Node {
        int[] count;
        int prod;

        Node(int k) {
            count = new int[k];
            prod = 1;
        }
    }

    private Node[] tree;
    private int K;
    private int N;

    private Node merge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;

        Node res = new Node(K);
        res.prod = (int) (((long) left.prod * right.prod) % K);

        for (int r = 0; r < K; r++) {
            res.count[r] = left.count[r];
        }

        for (int r = 0; r < K; r++) {
            if (right.count[r] > 0) {
                int newRem = (int) (((long) left.prod * r) % K);
                res.count[newRem] += right.count[r];
            }
        }

        return res;
    }

    private void build(int node, int start, int end, int[] nums) {
        if (start == end) {
            int rem = (int) (nums[start] % K);
            tree[node].prod = rem;
            tree[node].count[rem] = 1;
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid, nums);
        build(2 * node + 1, mid + 1, end, nums);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            int rem = (int) (val % K);
            for (int r = 0; r < K; r++) {
                tree[node].count[r] = 0;
            }
            tree[node].prod = rem;
            tree[node].count[rem] = 1;
            return;
        }
        int mid = start + (end - start) / 2;
        if (start <= idx && idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private Node query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return null;
        }
        if (l <= start && end <= r) {
            return tree[node];
        }
        int mid = start + (end - start) / 2;
        Node leftRes = query(2 * node, start, mid, l, r);
        Node rightRes = query(2 * node + 1, mid + 1, end, l, r);
        return merge(leftRes, rightRes);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        N = nums.length;
        K = k;
        tree = new Node[4 * N];
        for (int i = 0; i < 4 * N; i++) {
            tree[i] = new Node(K);
        }

        build(1, 0, N - 1, nums);

        int q = queries.length;
        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, N - 1, idx, val);
            Node res = query(1, 0, N - 1, start, N - 1);
            ans[i] = (res != null) ? res.count[x] : 0;
        }

        return ans;
    }
}