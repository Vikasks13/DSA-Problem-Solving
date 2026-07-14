class Solution {
    public int subsequencePairCount(int[] nums) {
        int n = nums.length;
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        int MOD = 1_000_000_007;
        // dp[g1][g2] stores the number of pairs of subsequences 
        // with GCD g1 and g2 respectively. 0 represents an empty subsequence.
        int[][] dp = new int[maxVal + 1][maxVal + 1];
        dp[0][0] = 1;

        for (int num : nums) {
            int[][] nextDp = new int[maxVal + 1][maxVal + 1];
            for (int g1 = 0; g1 <= maxVal; g1++) {
                for (int g2 = 0; g2 <= maxVal; g2++) {
                    if (dp[g1][g2] == 0) continue;

                    long count = dp[g1][g2];

                    // Choice 1: Skip the current number
                    nextDp[g1][g2] = (int) ((nextDp[g1][g2] + count) % MOD);

                    // Choice 2: Add to seq1
                    int ng1 = (g1 == 0) ? num : gcd(g1, num);
                    nextDp[ng1][g2] = (int) ((nextDp[ng1][g2] + count) % MOD);

                    // Choice 3: Add to seq2
                    int ng2 = (g2 == 0) ? num : gcd(g2, num);
                    nextDp[g1][ng2] = (int) ((nextDp[g1][ng2] + count) % MOD);
                }
            }
            dp = nextDp;
        }

        // Sum up all states where g1 == g2 and both are > 0 (non-empty)
        long totalPairs = 0;
        for (int g = 1; g <= maxVal; g++) {
            totalPairs = (totalPairs + dp[g][g]) % MOD;
        }

        return (int) totalPairs;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
} ////SMJHA NAHI