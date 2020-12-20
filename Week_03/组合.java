import java.util.*;

/**
 * 结束条件:
 *     递归层数已经满足了最大深度，通过 深度 > n + 1 来判断
 *   如果当前层剩余的数字已得不出 给定长度k的结果，后续就没必要再进行递归
 *   结束条件可改为:
 *     递归当前层剩余的数字，已经得不到给定长度k的结果
 *
 * 从n个当中选k个组合，对于递归当前层，有两种选择
 *   1. 选择当前层的数字
 *   2. 不选择当前层的数字
 *  所以调用List.add，进入选择递归，再通过List.remove(list.size())，进入不选择递归
 *
 * 结果：
 *   当前层得到的List.size() == k，则及时要得到的结果
 *   当前层长度已经满足 >= 2，所以此时可退出循环
 */

class Solution {
    public ArrayList<Integer> ans = new ArrayList<Integer>();

    public ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();

    public List<List<Integer>> combine(int n, int k) {
        dfs(n, k);
        return result;
    }

    public void dfs(int n, int k) {
        //recursion 结束递归
        if (ans.size() + n < k) {
            System.out.println(n);
            return;
        }

        //process current logic 记录满足条件的答案
        if (ans.size() == k) {
            result.add(new ArrayList<Integer>(ans));
            return;
        }

        //drill down
        ans.add(n);
        //选择当前位置
        dfs(n - 1, k);
        ans.remove(ans.size() - 1);
        //不选择当前位置
        dfs(n - 1, k);
    }
}
