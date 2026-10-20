class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] prefMax = new int[n + 1];
        prefMax[0] = 0;
        for (int i = 0; i < n; i++) {
            prefMax[i + 1] = Math.max(prefMax[i], nums[i]);
        }

        int[] suffMin = new int[n + 1];
        suffMin[n] = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            suffMin[i] = Math.min(nums[i], suffMin[i + 1]);
        }

        int[] prefDrop = new int[n];
        for (int i = 1; i < n; i++) {
            prefDrop[i] = prefDrop[i - 1] + (nums[i - 1] > nums[i] ? 1 : 0);
        }

        int totalSum = 0;
        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;
            boolean ok = true;
            for (int i = 0; i < n; i += k) {
                int start = i;
                int end = i + k - 1;
                
                if (start > 0 && prefMax[start] > suffMin[start]) {
                    ok = false;
                    break;
                }
                
                int drops = prefDrop[end] - prefDrop[start];
                if (nums[end] > nums[start]) {
                    drops++;
                }
                if (drops > 1) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                totalSum += k;
            }
        }
        return totalSum;
    }
}