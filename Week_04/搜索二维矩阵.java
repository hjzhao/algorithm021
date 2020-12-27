// 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：

// 每行中的整数从左到右按升序排列。
// 每行的第一个整数大于前一行的最后一个整数。

// 示例1：
// [
//     [1,  3,   5,  7],
//     [10, 11, 16, 20],
//     [23, 30, 34, 60],
// ]
// 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,50]], target = 3
// 输出：true

/**
 * 解题思路：
 *  1. 将二维数组转换成1维，然后再使用二分查找法
 *  2. 直接在二维数组中进行二分查找
 *
 * 方法2：直接在二维数组中进行二分查找
 *  1. 左右边界
 *      left = 0;
 *      right = 二维数组元素个数 - 1, 即 行 * 列 - 1
 *  2. 中间元素位置计算
 *      mid = (left + rigth) / 2;
 *  3. 中间元素位置，对应在二维数组中的索引计算
 *     每一行元素个数固定，假设为 n
 *        那么 中间元素的列索引，可以通过 mid % n，来计算
 *     然后需要计算，中间元素位置是在第几行
 *        二维数组的行数一定是 元素总个数/每一行元素个数
 *        那么 mid / n ，再取整，可得出 中间元素的行索引
 *
 *   根据以上分析，最后二分查找如下：
 */

class Solution4_3 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int matrixSize = matrix.length;
        if (matrixSize == 0) {
            return false;
        }

        int elementSize = matrix[0].length;

        int left = 0;
        int right = matrixSize * elementSize - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int element = matrix[mid / elementSize][mid % elementSize];
            if (element == target ) {
                return true;
            } else if (element < target) {
                left = mid + 1;
            } else if (element > target) {
                right = mid - 1;
            }
        }
        return false;
    }
}
