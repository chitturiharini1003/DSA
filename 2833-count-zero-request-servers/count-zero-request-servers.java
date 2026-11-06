import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        
        // Sort queries by time, keep original indices
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        // Sort logs by time
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int[] ans = new int[m];
        Map<Integer, Integer> serverCount = new HashMap<>();
        int l = 0, r = 0;
        int L = logs.length;

        for (int i = 0; i < m; i++) {
            int qTime = sortedQueries[i][0];
            int qIdx = sortedQueries[i][1];
            int startTime = qTime - x;

            // 1. Add all logs that arrive up to qTime
            while (r < L && logs[r][1] <= qTime) {
                int serverId = logs[r][0];
                serverCount.put(serverId, serverCount.getOrDefault(serverId, 0) + 1);
                r++;
            }

            // 2. Remove all logs that arrived before startTime
            while (l < L && logs[l][1] < startTime) {
                int serverId = logs[l][0];
                int count = serverCount.get(serverId);
                if (count == 1) {
                    serverCount.remove(serverId);
                } else {
                    serverCount.put(serverId, count - 1);
                }
                l++;
            }

            // Unactive servers = total servers - active unique servers
            ans[qIdx] = n - serverCount.size();
        }

        return ans;
    }
}