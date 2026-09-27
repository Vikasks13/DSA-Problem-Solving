class Solution {
    public int[] productExceptSelf(int[] nums) {
        return Product_Of_Array(nums);
    }

    public static int[] Product_Of_Array(int[] arr) {
        int n = arr.length;
        //Prefix
        int[] left = new int[n];
        left[0] = 1;
        for(int i=1; i<n; i++) {
            left[i] = left[i-1]*arr[i-1];
        }
        //Suffix
        int[] right = new int[n];
        right[n-1] = 1;
        for(int i=n-2; i>=0; i--) {
            right[i] = right[i+1]*arr[i+1];
        }
        //calculation
        for(int i=0; i<n; i++) {
            left[i] = left[i]*right[i];
        }
        return left;
    }
}