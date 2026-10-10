import java.util.Arrays;
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int intK1, int intK2) {
        long k = (long) intK1 + (long) intK2;
        int n = nums1.length;
        int maxDiff = 0;
        int[] diffCounts = new int[100001];
        for (int i = 0; i < n; i++) {
            int d = Math.abs(nums1[i] - nums2[i]);
            diffCounts[d]++;
            if (d > maxDiff) {
                maxDiff = d;
            }
        }
        for (int i = maxDiff; i > 0 && k > 0; i--) {
            if (diffCounts[i] == 0) continue;
            
            long take = Math.min(k, diffCounts[i]);
            diffCounts[i] -= take;
            diffCounts[i - 1] += (int) take;
            k -= take;
        }
        long totalSum = 0;
        for (int i = 1; i <= maxDiff; i++) {
            if (diffCounts[i] > 0) {
                totalSum += (long) diffCounts[i] * i * i;
            }
        }
        return totalSum;
    }
}