class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<Integer>();
        loop(root, list);
        return list;
    }

    public void loop(Node node, List list) {
        if (node == null) {
            return;
        }

        list.add(node.val);
        Iterator it = node.children.iterator();
        while (it.hasNext()) {
            loop((Node)it.next(), list);
        }
    }
}