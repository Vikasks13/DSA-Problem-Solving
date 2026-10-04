class Solution {
    public void sortColors(int[] nums) {
        Sort(nums);
    }

    public static void Sort(int[] arr) {
        int zero = 0;
        int two = arr.length-1;
        int i=0;
        while (i<=two) {
            if (arr[i] == 0) {
                int temp = arr[i];
                arr[i] = arr[zero];
                arr[zero] = temp;
                zero++;
                i++;
            } else if (arr[i] == 2) {
                int temp = arr[i];
                arr[i] = arr[two];
                arr[two] = temp;
                two--;
            } else {
                i++;
            }
        }
    }
}