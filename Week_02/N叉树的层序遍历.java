class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        traverse(root, 0, list);
        return list;
    }

    public void traverse(Node node, int level, List<List<Integer>> list) {
        if (node == null) {
            return;
        }
        if (list.size() == level) {
            list.add(new ArrayList<Integer>());
        }
        list.get(level).add(node.val);
        for (Node child : node.children) {
            traverse(child, level + 1, list);
        }
    }
}
