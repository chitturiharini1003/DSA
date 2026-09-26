class Solution {
    public int sumFourDivisors(int[] nums) {

        int answer = 0;

        for (int j = 0; j < nums.length; j++) {

            int n = nums[j];
            int count = 0;
            int sum = 0;

            for (int i = 1; i * i <= n; i++) {

                if (n % i == 0) {

                    count++;
                    sum += i;

                    if (i != n / i) {
                        count++;
                        sum += n / i;
                    }
                }
            }

            if (count == 4) {
                answer += sum;
            }
        }

        return answer;
    }
}