import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<List<Integer>> origAdj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            origAdj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            origAdj.get(parent[i]).add(i);
        }

        int[] newParent = new int[n];
        newParent[0] = -1;
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        dfsFindParents(0, origAdj, s, parent, newParent, lastSeen);

        List<List<Integer>> newAdj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            newAdj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            newAdj.get(newParent[i]).add(i);
        }

        int[] ans = new int[n];
        computeSubtreeSizes(0, newAdj, ans);
        return ans;
    }

    private void dfsFindParents(int u, List<List<Integer>> adj, String s, int[] parent, int[] newParent, int[] lastSeen) {
        int c = s.charAt(u) - 'a';
        if (u > 0) {
            newParent[u] = (lastSeen[c] != -1) ? lastSeen[c] : parent[u];
        }
        int prev = lastSeen[c];
        lastSeen[c] = u;
        for (int v : adj.get(u)) {
            dfsFindParents(v, adj, s, parent, newParent, lastSeen);
        }
        lastSeen[c] = prev;
    }

    private int computeSubtreeSizes(int u, List<List<Integer>> adj, int[] ans) {
        int size = 1;
        for (int v : adj.get(u)) {
            size += computeSubtreeSizes(v, adj, ans);
        }
        ans[u] = size;
        return size;
    }
}