class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int arrivalAliceDay = convertToDays(arriveAlice);
        int leavingAliceDay = convertToDays(leaveAlice);
        int arrivalBobDay = convertToDays(arriveBob);
        int leavingBobDay = convertToDays(leaveBob);

        int overlapStart = Math.max(arrivalAliceDay, arrivalBobDay);
        int overlapEnd = Math.min(leavingAliceDay, leavingBobDay);

        return Math.max(0, overlapEnd - overlapStart + 1);
    }

    private int convertToDays(String date) {
        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        int totalDays = day;
        for (int i = 0; i < month - 1; i++) {
            totalDays += daysInMonths[i];
        }

        return totalDays;
    }
}