class Solution {
    public int minimumArea(int[][] grid) {
        int minr = Integer.MAX_VALUE;
        int maxr = Integer.MIN_VALUE;
        int minc = Integer.MAX_VALUE;
        int maxc = Integer.MIN_VALUE;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    minr = Math.min(minr,i);
                    maxr = Math.max(maxr,i);
                    minc = Math.min(minc,j);
                    maxc = Math.max(maxc,j);
                }
            }
        }
        return (maxr-minr+1)*(maxc-minc+1);
    }
}