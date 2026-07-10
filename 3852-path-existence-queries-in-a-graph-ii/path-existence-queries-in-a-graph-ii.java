class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int sorted [][] = new int[n][2];
        for(int i=0; i<n; i++) {
            sorted[i][0] = nums[i];
            sorted[i][1] = i;
        }
        Arrays.sort(sorted, (a,b)->Integer.compare(a[0],b[0]));
        //hashmap --> original index
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<n; i++) {
            map.put(sorted[i][1], i);
        }
        
        //LOG(n) -->
        int LOG = 1;
        while((1<<LOG) < n)
            LOG++;
        //binary matrix
        int up[][] = new int[n][LOG];

        //first, 1hop
        int r=0;
        for(int l=0; l<n; l++) {
            while(r<l)
                r = l;

            while(r+1 < n && sorted[r+1][0] - sorted[l][0] <= maxDiff) {
                r++;
            }
            up[l][0] = r;    
        }

        for(int j=1; j<LOG; j++) {
            for(int i=0; i<n; i++) {
                up[i][j] = up[up[i][j-1]][j-1];
            }
        }

        int ans[] = new int[queries.length];
        for(int i=0; i<queries.length; i++) {
            int src = map.get(queries[i][0]);
            int dest = map.get(queries[i][1]);
            if(src==dest) {
                ans[i] = 0;
                continue;
            }

            if(src > dest) {
                int temp = src;
                src = dest;
                dest = temp;
            }

            //binary jumping
            int curr = src;
            int hops = 0;

            //try furthest jump
            for(int j=LOG-1; j>=0; j--) {
                if(up[curr][j] < dest) {
                    curr = up[curr][j];
                    hops += (1<<j);
                }
            }

            if(up[curr][0] >= dest) {
                ans[i] = hops+1;
            }else {
                ans[i] = -1;
            }
        }
        return ans;

    }
}