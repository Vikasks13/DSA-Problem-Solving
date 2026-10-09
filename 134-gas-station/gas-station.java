class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total_sum=0;
        for (int i=0; i<cost.length; i++) {
            total_sum += (gas[i]-cost[i]);
        }
        if (total_sum<0) {
            return -1;
        }

        int curr_sum=0;
        int idx=0;
        for (int i=0; i<cost.length; i++) {
            curr_sum += (gas[i]-cost[i]);
            if (curr_sum<0) {
                idx=i+1;
                curr_sum=0;
            }
        }
        return idx;
    }
}