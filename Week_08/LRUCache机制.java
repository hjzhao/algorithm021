import java.util.HashMap;

/**
 * 思路：哈希表+双向链表
 * 1. 定义双向链表节点 DLinkedNode
 * 2. 定义头结点和尾节点
 * 3. 定义一个哈希表，哈希表的值是双向链表的节点
 *
 * 实现：
 *  首先要实现一个双向链表，实现功能如下：
 *  1. 根据提供的节点删除双向链表中的节点
 *  2. 把一个节点插入到双向链表的头部
 *
 *
 *  然后实现LRU Cache添加和查询功能
 *  1. 添加
 *      首先判断要加入的key是否在哈希表中
 *      1. 如果存在，则获取节点，然后将节点从链表中删除，再插入到头部
 *      2. 如果不存在，则创建一个节点，然后将该节点插入到头部，然后需要判断是否超出容量
 *          a. 超出容量，则将链表尾部的节点删除
 *          b. 没有超出容量，则直接插入
 *  2. 查询
 *      判断查询的key是否存在哈希表中
 *      1. 不存在，则返回-1
 *      2. 存在，则从哈希表中查到对应的节点，从链表中删除，然后再插入到头部
 *
 * 时间复杂度：因为是通过哈希表查找节点，然后直接操作双向链表中对应的节点，时间复杂度是 O(1)
 * 空间复杂度：空间主要是LRUCache的容量，链表的长度是 容量 + 头节点 + 尾节点，容量 + 2
 *           假设容量是 capacity，那么空间复杂度为 O(capacity)
 */
public class Solution {
    private HashMap<Integer, DLinkedNode> dlinkedMap = new HashMap<>();

    private DLinkedNode head;
    private DLinkedNode tail;

    private int size;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        size = 0;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = dlinkedMap.get(key);
        if (node == null) {
            return -1;
        }
        removeNode(node);
        addToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = dlinkedMap.get(key);
        if (node == null) {
            node = new DLinkedNode(key, value);
            if (size == capacity) {
                dlinkedMap.remove(tail.prev.key);
                removeNode(tail.prev);
                size--;
            }
            addToHead(node);
            dlinkedMap.put(key, node);
            size++;
        } else {
            node.value = value;
            removeNode(node);
            addToHead(node);
        }
    }

    private void addToHead(DLinkedNode node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
        node.prev = null;
        node.next = null;
    }
}

class DLinkedNode {
    public int key;
    public int value;

    public DLinkedNode next;
    public DLinkedNode prev;

    public DLinkedNode() {
    }

    public DLinkedNode(int k, int v) {
        key = k;
        value = v;
    }
}