/**
 * 思路：并查集
 * 这个题目可以使用 DFS、BFS、并查集，根据现在的学习情况，这里只给出并查集的写法
 *
 * 条件：
 *  1. 给一个由 1代表陆地 0代表水的网格
 *  2. 网格是四连通，上下左右是连通的，连通起来的陆地被称为岛屿
 *  3. 如果一个陆地周围都被水包围，那么也认为是一个岛屿
 * 目的：
 *  返回岛屿的数量
 *
 * 从题目上看，这个题目主要是求集合合并的问题，所以可以使用并查集来处理
 *
 * 下面是套用并查集模板，和每个过程处理的注释，代码如下：
 * 时间复杂度：假设是一个 m*n的矩阵，需要初始化集合，时间复杂度为 O(m*n)
 *          合并时，需要遍历矩阵，时间复杂度为 O(m*n)，每次遍历需要对上下左右执行两次查找操做，
 *          这里使用了路径压缩，查找单次时间复杂度最差为 O(log n)，所以，每个陆地与周围陆地合并时间复杂度为 O(4*2*log n)，
 *          最终整体时间复杂度为 O(m * n * log n)
 * 空间复杂度：假设是一个 m*n的矩阵，因为需要创建岛屿和水的集合，那么空间复杂度为 O(m*n)
 */
class Solution {
    //创建一个并查集，并查集里包含每个陆地，记录陆地之前的连通的集合
    private int[] parent;
    //初始时存储所有的陆地个数，当遇到两个陆地连通的时候，对其-1，最后的数字就是岛屿的数量
    private int count = 0;

    //四连通的坐标增量
    private int[] xv = new int[]{0, 1, 0, -1};
    private int[] yv = new int[]{1, 0, -1, 0};

    //并查集
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //初始并查集集合，长度为岛屿和水的总数
        parent = new int[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //对矩阵中值为 '1' 的陆地初始化，将集合中 p[i] = i
                if (grid[i][j] == '1') {
                    //i * n + j 是 grid[i][j] 在并查集中对应的位置
                    parent[i * n + j] = i * n + j;
                    //每遍历到一个陆地，陆地数量+1
                    count++;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //如果不是陆地，则跳过
                if (grid[i][j] != '1') {
                    continue;
                }

                //如果是陆地，则判断其周围是陆地还是水，并将连通的陆地合并
                for (int k = 0; k < xv.length; k++) {
                    int x = i + xv[k];
                    int y = j + yv[k];
                    //上下左后越界或者不是陆地，则跳过
                    if (x < 0 || x >= m || y < 0 || y>= n || grid[x][y] != '1') {
                        continue;
                    }
                    //将周围的陆地与当前的陆地连通
                    union(i * n + j, x * n + y);
                }
            }
        }
        return count;
    }

    public void union(int x, int y) {
        int i = find(x);
        int j = find(y);
        //已经连通，则不处理
        if (i  == j) {
            return;
        }
        //将x 和 y连通
        parent[i] = j;
        //发现有两个需要连通的陆地，则对陆地数量-1
        count--;
    }

    public int find(int x) {
        while (x != parent[x]) {
            //并查集压缩路径
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
}
