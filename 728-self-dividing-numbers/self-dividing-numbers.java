class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new ArrayList<>();
        for(int number = left; number <= right ; number++){
            int temp = number;
            boolean isSelfDividing = true;
            while(temp > 0){
                int digit = temp % 10;
                if(digit == 0){
                    isSelfDividing = false;
                    break;
                }
                if(number%digit != 0){
                    isSelfDividing = false;
                    break;
                }
                temp = temp/10;
            }
            if(isSelfDividing){
                result.add(number);
            }
        }
        return result;
    }
}