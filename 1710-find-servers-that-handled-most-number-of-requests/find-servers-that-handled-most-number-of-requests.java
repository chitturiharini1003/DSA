import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }
        
        int[] requests = new int[k];
        int maxReq = 0;
        
        for (int i = 0; i < arrival.length; i++) {
            int currTime = arrival[i];
            while (!busy.isEmpty() && busy.peek()[0] <= currTime) {
                available.add(busy.poll()[1]);
            }
            
            if (available.isEmpty()) {
                continue;
            }
            
            Integer server = available.ceiling(i % k);
            if (server == null) {
                server = available.first();
            }
            
            available.remove(server);
            busy.offer(new int[] {currTime + load[i], server});
            requests[server]++;
            maxReq = Math.max(maxReq, requests[server]);
        }
        
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requests[i] == maxReq) {
                ans.add(i);
            }
        }
        return ans;
    }
}