package Week_01;

/**
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 */

class Solution {
    public int removeDuplicates(int[] nums) {
        //nums为null 或 长度为0 直接返回0
        if (nums == null || nums.length == 0) {
            return 0;
        }

        //用一个变量记录当前不重复值的位置，再加一个循环遍历数据
        int index = 0;

        /*
         * 有序数组，不用对数组进行排序
         * 因为数组第一个元素一定不会有重复，所以直接从第二个元素开始遍历
         */
        for (int i = 1; i < nums.length; i++ ) {
            /*
             * 每遍历一次比较一下循环的当前元素，与index变量记录的元素是否相等
             */
            if (nums[i] != nums[index]) {
                /* 
                 * 不相等，把数组遍历的当前位置值，赋值给index+1位置，然后index自增，用于下次循环
                 */
                nums[++index] = nums[i];
            }
        }
        return index + 1;
    }
}