class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }

        // Count frequencies of each number
        int[] count = new int[maxVal + 1];
        for (int num : nums) {
            count[num]++;
        }

        // gcdPairsCount[i] will store the total number of pairs with GCD exactly equal to i
        long[] gcdPairsCount = new long[maxVal + 1];

        // Process from maxVal down to 1 using inclusion-exclusion
        for (int i = maxVal; i >= 1; i--) {
            long totalMultiples = 0;
            for (int j = i; j <= maxVal; j += i) {
                totalMultiples += count[j];
            }

            // Total pairs where GCD is a multiple of i
            long totalPairsWithMultipleGcd = totalMultiples * (totalMultiples - 1) / 2;

            // Subtract pairs that have a strictly larger multiple of i as their GCD
            for (int j = 2 * i; j <= maxVal; j += i) {
                totalPairsWithMultipleGcd -= gcdPairsCount[j];
            }

            gcdPairsCount[i] = totalPairsWithMultipleGcd;
        }

        // Compute prefix sums of the GCD pair counts
        long[] prefixSums = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefixSums[i] = prefixSums[i - 1] + gcdPairsCount[i];
        }

        // Answer each query using binary search
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long target = queries[i];
            
            // Binary search for the smallest index where prefixSums[index] > target
            int low = 1, high = maxVal;
            int ansGcd = maxVal;
            
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (prefixSums[mid] > target) {
                    ansGcd = mid;
                    high = mid - 1; // Try to find a smaller valid GCD
                } else {
                    low = mid + 1;
                }
            }
            answer[i] = ansGcd;
        }

        return answer;
    }
}