/**
 * 思路：并查集
 *
 * 条件：
 *  1. 给一个矩阵，其中包含n个城市的相连关系，其中一些彼此相连，另一些没有相连
 *  2. 如果a与b相连，b与c相连，那么a和c也是相连的
 *  3. 自己跟自己也是相连的
 *  4. 省份是一组直接相连或间接相连的城市
 * 目标：
 *  返回矩阵中省份的数量
 *
 * 该题是处理集合合并的问题
 * 因为并查集用于处理一些不相交集合的合并及查询问题，所以并查集很适合处理这个题目
 *
 * 套用并查集的模板，代码如下：
 * 时间复杂度：城市数量为n，因为需要遍历isConnected找到连通关系，时间复杂度为O(n^2)，
 *          找到连通关系后，会涉及到两次查找，最差的情况时间复杂度为O(2n^2)，
 *          并查集里使用路径压缩之后，最差的情况查找的时间复杂度为O(log n)，
 *          所以最终时间复杂度为 O(n^2 * log n)
 * 空间复杂度：城市数量为n，那么就需要创建一个长度为 n 的parent数组记录城市连通关系，空间复杂度为 O(n)
 */
class SolutionTest {
    public int findCircleNum(int[][] isConnected) {
        int m = isConnected.length;

        //初始化一个并查集的集合，长度表示有多少个城市
        int[] p = new int[m];
        for (int i = 0; i < m; i++) {
            //因为自己跟自己也是相连的，所以首先将集合的元素都指向自己
            p[i] = i;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 1; j < m; j++) {
                if (isConnected[i][j] == 1) {
                    //如果两个城市是相连的
                    //那么使用并查集的合并方法，将两个城市合并
                    union(p, i, j);
                }
            }
        }

        //获取最终的结果
        //因为如果其中几个城市是相连的，那么他们一定属于同一个集合
        //同一个集合会有一个共同的根，共同的根是自己指向自己的
        //所以最后只要判断 p[i] == i 的城市有几个即可
        int circle = 0;
        for (int i = 0; i < m; i++) {
            if (p[i] == i) {
                circle++;
            }
        }
        return circle;
    }

    //并查集合并方法
    //目的是将同一个集合的成员，指向同一个根
    private void union(int[] p, int index1, int index2) {
        int p1 = find(p, index1);
        int p2 = find(p, index2);
        p[p1] = p2;
    }

    //找到给定 index 所属集合的根
    private int find(int[] p, int index) {
        //根一定是自己指向自己的
        //最后返回index所属集合的根
        while (index != p[index]) {
            //处理路径压缩
            p[index] = p[p[index]];
            index = p[index];
        }
        return p[index];
    }
}