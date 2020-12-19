
//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
//百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

/*
 *最终的结果为:
 *   1. p  q 不存在树中
 *   2. p q 在树的子树中
 *   3. p == root, q在树的子树中
 *   4. q == root, p在树的子树中
 */

/*
 * 解法一:
 *
 * 深度优先搜索
 * 如果 p 或 q 不在树中, 会返回 null; 如果 p 和 q 都在树中, 返回公共祖先
 *
 * 递归终止条件:
 *   遍历越过了叶子节点
 *
 * 自底下上查找
 *   如果找到 p 或 q 则返回true，否则false
 *   dfs(root.left)
 *   dfs(root.rigth)
 *   递归到树的叶子节点，开始自底下上查找
 *
 * 根据查找结果判断公共祖先
 *   1. 节点等于 p 或 q 返回 true, 否则 false
 *   2. 往上一层判断:
 *      1. 左右节点遍历 都返回true, 则 本层root节点是 p 和 q 的公共祖先
 *      2. 下层的节点返回true, 且 本层 root 节点 包含 p 或 q, 则 本层root节点是 p 和 q 的公共祖先
 *
 * 时间复杂度:
 *   O(n)  N 为树的节点数
 * 空间复杂度:
 *   O(n)  N 为树的节点数，最差的情况树为一条链
 *
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = null;
        dfs(root, p, q, ancestor);
        return ancestor;
    }

    public boolean dfs(TreeNode root, TreeNode p, TreeNode q, TreeNode ancestor) {
        // recursion terminator
        if (root == null || p == null || q == null) {
            return false;
        }

        // drill down
        boolean leftRes = dfs(root.left, p, q, ancestor);
        boolean rightRes = dfs(root.right, p, q, ancestor);

        // process and generate the final result
        if ((leftRes && rightRes) || (root == p || root  == q) && (leftRes || rightRes)) {
            ancestor = root;
        }

        return leftRes || rightRes || root == p || root == q;
    }
}

/*
 * 解法二:
 * 缺点:
 *   前提是 p 和 q 必须存在于树中
 *
 * 终止条件为:
 *   1. 遍历到叶子节点，未查到 p 和 q
 *   2. 遍历到的节点 等于 p 或 q
 *
 * 分别递归遍历左右子树
 *   1. left = fn(root.left)
 *   2. right = fn(root.right)
 *
 * 最后根据左右子树的结果判断最终的结果:
 *   1. 如果 root.left 和 root.right 分别包含p q, 则返回 root
 *   2. 如果 root.right == null
 *          则 p 和 q 均在左子树中, 返回 root.left
 *   3. 如果 root.left == null
 *          则 p 和 q 均在左子树中, 返回 root.right
 *   4. 左右子树都没找到 p q 节点, 则返回 null
 *
 * 时间复杂度:
 *   O(n)  N 为树的节点数
 * 空间复杂度:
 *   O(n)  N 为树的节点数，最差的情况树为一条链
 *
 */
class Solution2 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // recursion terminator
        if (root == null || p == null || q == null) {
            return null;
        } else if (root == p || root == q) {
            return root;
        }

        // drill down
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // process and generate the final result
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        }
        return root;
    }
}
