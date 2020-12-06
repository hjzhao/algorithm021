# java queue
队列

java queue类是一个interface，里面定义队列需要实现的方法
## 方法
```
插入
    add    向队列里添加元素，添加成功返回true，添加失败会抛异常
    offer  向队列里添加元素，添加成功返回true，添加失败会返回false

删除
    remove  移除并返回队列头部的元素，如果队列元素为空，会抛异常
    poll    移除并返回队列头部的元素，如果队列为空会返回null

查看
    element  返回队列头部的元素，如果队列为空，会抛异常
    peek     返回队列头部的元素，如果队列为空，会返回null
```

# LinkedList
LinkedList 实现了 deque 接口，同时也实现了 queue 接口，下面对LinkList源码进行分析

## 实现方式及复杂度总结

### 底层数据结构和初始化
1. LinkList 封装了双向链表的一系列操做
```
类属性有 first节点、last节点，所以这是一个双向链表
初始化有两种方式
1. 初始化一个空链表
2. 初始化时传入一个集合(Collection)
```
2. 链表的节点是 Node类，Node类是LinkList的内部类，其中有3个属性
```
E item;
Node<E> next;
Node<E> prev;
```

### offer 方法
向链表的末尾添加一个节点
```
如果当前链表为空，则将插入节点作为头结点，同时 first节点 和 last节点 都指向添加的节点
如果链表不为空，则将插入节点指给 last节点 的 next指针， last节点 指向添加的节点

    时间复杂度 O(1)
```

### add 方法
因为链表没有容量限制，不用抛异常，所以添加操做与`offer方法`一致
LinkedList `offer方法`也是调用的`add方法`

### poll 方法
移除并返回链表的头结点
```
1. 首先定一个 item变量，存储 first节点 的item
2. 然后将 链表的 first节点，指向 first节点 的 next节点
    这时，如果 first节点 的 next指针 是null，则 first节点 也会变为null
3. 将原 first节点 的 item和next 置为null
    如果此时的 first节点 为null，则将 last节点 也置为null
    如果此时的 first节点 不为null， 则将 first节点 的 prev指针 置为null
4. 最后将之前定义的 item变量 返回

    时间复杂度 O(1)
```

### remove 方法
移除并返回链表的头结点
操做方式与`poll方法`一致
不同的是，如果当前链表为空，则会抛出`NoSuchElementException`异常

### peek 方法
返回`first节点`的`item属性`
如果`first节点`为null，则返回null

```
时间复杂度 O(1)
```

### elememt 方法
返回`first节点`的`item属性`
如果`first节点`为null，则会抛出`NoSuchElementException`异常

```
时间复杂度 O(1)
```