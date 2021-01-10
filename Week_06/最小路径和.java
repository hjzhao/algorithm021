/**
 * 方法：动态规划
 *
 * 条件：
 *  1. 给出一个网格，网格是一个值为非负整数的二维数组
 *  2. 初始位置为网格做上角，终点为网格右下角
 *  2. 每次只能向下 或 向右 移动一步
 *  3. 找到移动路径上的数加起来总和最小，并返回总和
 *
 * 思路：
 *  假设 数组 nums[m][j], 当前位置为 nums[i][j]
 *  参考不同路径题目
 *
 *  一、重复性：
 *    如果要到达当前位置路径和最小
 *      那么需要保证上一步的路径和最小
 *        上一步的位置为 nums[i-1][j], nums[i][j-1]
 *        所以要去这两个位置的总和最小的值，然后加上当前位置的数字
 *      因此可以得出 sum[i][j] = min(sum[i-1][j], sum[i][j-1]) + nums[i][j]
 *
 *  二、状态数组：
 *    因为需要存储每一步的结果，涉及主要是上一步
 *      所以可以定义状态数组  sum[m][n]
 *
 *  三、递推方程
 *    根据重复性可以定义递推方程为 fn(i, j) = min(fn(i-1,j), fn(i,j-1)) + nums[i][j]
 *
 *  最后返回 sum[m-1][n-1] 的值
 *
 * 其中要考虑一下边界问题：
 *  1. 初始位置
 *      总和为初始位置的值
 *  2. i == 0
 *      从初始位置一直往右走，当前位置总和为 row_sum[0][j] += row_sum[0][j-1]
 *  3. j == 0
 *      从初始位置一直往下走，当前位置总和为 row_sum[i][0] += row_sum[i-1][0]
 *
 * 代码如下：
 */

class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        //定义状态数组
        int[][] dp = new int[m][n];

        //初始位置
        dp[0][0] = grid[0][0];

        // j == 0
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        // i == 0
        for (int j = 1; j < n; j++) {
            dp[0][j] += dp[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }
}
