class Solution {
    public int gcdOfOddEvenSums(int n) {
        // n/2*(2*a+(n-1)*d)
        // 1,3,5,7
        //2,4,6,8

        int sumOdd = (n)*(2*1 + (n-1)*2)/2;
        int sumEven = (n)*(2*2 + (n-1)*2)/2;

        return gcd(sumOdd, sumEven);
    }
    
    int gcd(int a, int b) {
        if(b==0)
            return a;
        else return gcd(b, a%b);    
    }
}