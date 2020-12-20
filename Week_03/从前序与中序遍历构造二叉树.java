import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

/*
 * 分治:
 *
 * 根据
 *   前序遍历: [根] [左] [右]
 *   中序遍历: [左] [根] [右]
 * 的特性可得
 *   前序遍历第一个节点就是根节点
 * 以此可通过查找根节点在中序遍历中位置，得出
 *   [左子树节点个数] [根] [右子树节点个数]
 * 然后根据左右子树节点个数，可以在前序遍历中确定
 *   [根] [左子树节点个数] [右子树节点个数]
 *
 * 那么就可以确定 左子树和右子树 分别在前序遍历和后序遍历的左右边界
 * 最后进入下一层递归
 *
 * 时间复杂度为 O(n)  n是树的节点数
 * 空间复杂度为 O(n)
 *   1. 存储结果需要 O(n)  n是树的节点数
 *   2. 存储中序遍历的hashmap  O(n)
 *   3. 递归需要开辟的栈空间 O(h)  h为树的高度
 */

public class Solution {
    private Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length) {
            return null;
        }

        int n = inorder.length;
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        //中序遍历
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        //preorder [3,9,20,15,7]
        //inorder [9,3,15,20,7]

        //recursion terminator

        //前序遍历 [根左右] 左边界 < 右边界，中序遍历 [左根右] 左边界 < 右边界
        if (preorder_left > preorder_right) {
            //左边界大于右边界时，终止递归
            return null;
        }

        //process current logic

        //前序遍历第一个节点就是根节点 根据前序遍历确定 根节点是 3
        int preorder_root = preorder_left;
        //创建根节点
        TreeNode root = new TreeNode(preorder[preorder_root]);

        //根据中序遍历 [左根右]，先找到 根节点3 在中序遍历的位置
        //那么以该位置为根节点，该位置左侧就是有子树，该位置右侧就是右子树
        int inorder_root = map.get(preorder[preorder_root]);
        //最终得出
        // 根 [3]
        // 左子树 [9]
        // 右子树 [15, 20, 7]
        //以此得出，左子树节点个数为1，右子树节点数个数为3

        //继续，根据 前序遍历 [根] [左] [右] 的顺序 可以得出
        //假设 前序根节点root的位置（也就是前序遍历第一个元素位置）为 preorder_root_index
        //  前序遍历的左子树边界为[preorder_root_index + 1, preorder_root_index + 1]
        //  前序遍历的右子树边界为[preorder_root_index + 1 + 1, 列表的最右边界]
        //假设 左子树长度为 size_left_subtree, 那么可得出
        //  前序遍历的左子树边界为[preorder_root_index + 1, preorder_root_index + size_left_subtree]
        //  前序遍历的右子树边界为[preorder_root_index + size_left_subtree + 1, 列表的最右边界]

        //相对应，中序根据中序遍历结果可得出
        //假设 中序遍历根节点root的位置 为 inorder_root_index
        //  中序遍历的左子树边界为 [列表的最左边界, inorder_root_index - 1]
        //  中序遍历的右子树边界为 [inorder_root_index - 1, 列表的最右边界]

        //drill down
        int size_left_subtree = inorder_root - inorder_left;
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }
}
