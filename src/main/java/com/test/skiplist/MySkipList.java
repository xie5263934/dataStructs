package com.test.skiplist;

import java.util.Random;

/**
 * 个人的调表实现
 *
 * @Auth 45208
 * @Date 11/1/2020
 **/
public class MySkipList {
    //用在计算随机层高的函数中，表示当前跳表的索引的最高层级只有maxLevelCount
    private final int maxLevelCount = 16;
    //用来标识当前跳表中实际存在的数据中索引最高的值
    private int levelCount = 1;
    //用来生成随机数，当随机数是奇数的时候，那么当前要插入的节点的索引层高就增加一层
    private Random random = new Random();
    //头节点，不存任何数据，就是用来标识链表的头，是一个哨兵，作用是始终引用当前链表，这样对于链表的所有节点都可以做相同的处理，而不用对头节点或者尾节点做特殊处理
    private Node head = new Node(maxLevelCount);


    /**
     * 跳表的节点
     */
    public static class Node {
        public Node(int level) {
            this.forwards = new Node[level];
        }
        //数据区域
        private Integer data;
        //指向当前节点后驱的指针，这里使用数组的原因是因为我们设计了索引，所以根据随机出来的层高，指向N个后驱节点，当然也有可能某些不同层高实际指向的后驱节点是同一个
        private Node forwards[];
    }

    public void insert(int value) {
        //当前要插入的节点的索引的层高
        int level = level();
        //创建新的要插入的节点
        Node node = new Node(level);
        node.data = value;
        //找到要插入的位置
        //如果当前已经存在的最高层高比当前要插入节点的层高还低，那么将最高层高更新为当前插入节点的层高
        if (levelCount < level) {
            levelCount = level;
        }
        //当前指针指向哨兵节点,表示从头开始遍历
        Node p = head;
        //用来存储当前要插入节点的所有前驱节点，这样在插入中，当前节点的每级索引对应的前驱都可以在这个数组里面找到
        Node[] preNodes = new Node[levelCount];
        //外层循环用来控制索引的层级，表示当前在哪一层索引上进行查找操作，跳表的操作，默认一般都是从最高层级开始查找，当高层索引不满足之后，就从当前层级的下一层开始查找
        for (int i = levelCount - 1; i >= 0; i--) {
            //内层循环用来控制在当前对应的层级的链表上进行查找，从前往后查找对应的要插入节点位置的前驱
            while (p.forwards[i] != null && p.forwards[i].data != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            preNodes[i] = p;
        }
        //将当前节点插入到跳表中
        //从最高层的索引开始处理
        for (int i = level - 1; i >= 0; i--) {
            //当前节点的对应层的索引的后续节点等于当前节点的前驱节点的后续节点
            node.forwards[i] = preNodes[i].forwards[i];
            preNodes[i].forwards[i] = node;
        }
    }

    public void delete(int value) {
        Node p = head;
        Node[] preNodes = new Node[levelCount];
        //在跳表中查找当前value对应的节点
        for (int i = levelCount - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].data != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            preNodes[i] = p;
        }
        //删除当前节点
        if (p.forwards[0] != null && p.forwards[0].data != null && p.forwards[0].data == value) {
            //从当前要删除节点的最高层级的索引开始删除当前节点
            for (int i = p.forwards[0].forwards.length - 1; i >= 0; i--) {
                //当前要删除节点的前驱对应的后驱指向了当前要删除节点的后驱，表示将当前节点摘除
                preNodes[i].forwards[i] = p.forwards[i].forwards[i];
            }
        }
    }

    public Integer find(int value) {
        Node p = head;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].data != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
        }
        if (p.forwards[0] != null && p.forwards[0].data != null && p.forwards[0].data == value) {
            return p.forwards[0].data;
        } else {
            return null;
        }
    }

    /**
     * 随机函数，用来随机生成当前要插入的节点的索引的层高，其中level初始值是1，这样标识索引至少也是1级，极端情况levele是0，导致出错
     * 算法是循环最大层级次数，然后每次都随机一个数，当随机数是奇数的时候，索引层高就增加一级
     *
     * @return
     */
    public int level() {
        //标识至少也存在一个层级
        int level = 1;
        for (int i = 0; i < maxLevelCount; i++) {
            if (random.nextInt() % 2 == 1) {
                level += 1;
            }
        }
        return level;
    }

    /**
     * 打印所有数据
     */
    public void printAll_beautiful() {
        Node p = head;
        Node[] c = p.forwards;
        Node[] d = c;
        int maxLevel = c.length;
        for (int i = maxLevel - 1; i >= 0; i--) {
            do {
                System.out.print((d[i] != null ? d[i].data : null) + ":" + i + "-------");
            } while (d[i] != null && (d = d[i].forwards)[i] != null);
            System.out.println();
            d = c;
        }
    }
}
