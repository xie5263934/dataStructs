package com.test.tree;

/**
 * 二叉查找树，当前节点的值大于节点左子树的所有节点，小于当前当前节点右子树的所有节点
 * 新增:如果如果值小于当前节点，就往左子树查找，如果大于当前节点就往节点的右子树中查找，直到查找到的节点的左子树或者右子树
 * 为空，那么就将当前节点加入到树中
 * 查找:如果值等于当前节点返回，如果小于当前节点，就在左子树中查找，如果大于当前节点，就在右子树中查找，如果到最后都找不到，就返回空
 * 删除:分成三种情况处理
 * 1:如果当前节点没有左子树和右子树，就是一个叶子节点，那么删除的时候直接将当前节点的父亲节点到当前节点的索引设置为空
 * 2:如果当前当前节点只有一个节点，只有左子树或者右子树，那么删除当前节点的时候，只需要将父节点的对应指针指向当前节点的左子树或者右子树就行
 * 3:如果当前节点既有左子树，又有右子树，那么记录当前节点，并且在当前节点的右子树中，查找最左最小子节点，并且将当前节点的值设置成找到的
 * 那个最左最小节点的值，然后删除就变成了删除那个最左最小节点了，因为我们查找的是最左最小节点，所以最后找到的一定是一个最多只有一个右子树的
 * 节点，因为如果它还有左子树，那么我们肯定还会继续往左查找，它肯定就不是那个最左最小的节点，然后我们再按照1，2的方式把这个最左最小节点删除
 * 这里为了简单起见，我们假设所有元素的值不重复，实现一个简单的版本
 */
public class BinarySearchTree {

    //根节点,也是哨兵节点，这样对于所有节点的操作都是同一套逻辑，用来保存二叉树的根节点
    private Node tree;

    /**
     * 新增节点
     * @param node
     */
    public void add(Node node) {
        //如果根节点为空，那么当前新增节点就是根节点
        if (tree == null) {
            tree = node;
        } else {
            //当前遍历节点
            Node p = tree;
            //当前节点的父节点
            Node pp = null;
            //通过循环不停第遍历，找到当前要插入节点的位置，也就是p的位置，并且找到父节点pp的位置，方便之后插入节点
            while (p != null) { //当指针为空的时候，那么就退出循环，表示找到了对应的位置，pp为当前要插入位置的父节点
                //首先父节指向指向当前节点
                pp = p;
                //比较当前节点和要插入节点的值的大小，然后将当前节点往左或者是往右移动
                //如果要插入节点的值比当前节点的值大，那么就将指针指向当前节点的右节点
                if (node.getData() > p.getData()) {
                    p = p.getRight();
                    //否则将指针指向当前节点的左节点
                } else {
                    p = p.getLeft();
                }
            }
            //如果当前节点大于父节点，那么当前节点为右孩子
            if (node.getData() > pp.getData()) {
                pp.setRight(node);
            } else {
                //否则当前节点为左孩子
                pp.setLeft(node);
            }
        }
    }

    /**
     * 在二叉树中查找一个值
     * @param value
     * @return
     */
    public Node search(int value) {
        //如果根节点为空，那么直接返回空
        if (tree == null) {
            return null;
        }
        //首先指针指向根节点
        Node p = tree;
        //然后循环从根节点开始遍历，比较每个节点的值
        while (p != null) {
            //如果当前节点的值等于要查找的值，那么就返回当前节点
            if (p.getData() == value) {
                return p;
                //如果当前要查找的值，大于当前节点的的值，那么就在当前节点的右子树中查找
            } else if (value > p.getData()) {
                p = p.getRight();
                //否则就在左子树中查找
            } else {
                p = p.getLeft();
            }
        }
        //如果没有查找到，最后就返回空
        return null;
    }

    /**
     * 删除一个节点
     * @param value
     */
    public void delete(int value) {
        //如果根节点为空，那么直接就返回，不做任何操作
        if (tree == null) {
            return;
        }
        /**
         * p指针指向当前节点
         */
        Node p = tree;
        /**
         * 指针指向父节点
         */
        Node pp = null;
        /**
         * 当p节点为空了，或者p节点的值等于要删除的值了，表示找到了要删除的节点了，退出循环
         */
        while (p != null && p.getData() != value) {
            //首先将父节点指针指向当前节点
            pp = p;
            //如果要删除节点的值大于当前节点的值，那么就在右子树中查找
            if (value > p.getData()) {
                p = p.getRight();
            } else {
                //否则就在左子树中查找
                p = p.getLeft();
            }
        }
        //如果p指针为空，那么就是没有找到要删除的节点，直接退出
        if (p == null) {
            return;
        }
        /**
         * 如果当前节点的左右子树都不为空，那么就要查找当前节点的右子树中最小的那个左节点，这样经过处理之后，要删除的节点就变成了右子树的最左子节点
         * 那么最终要删除的那个节点，最多只有一个子节点
         */
        if (p.getLeft() != null && p.getRight() != null) {
            //minP指针指向当前节点的右子树的根节点
            Node minP = p.getRight();
            //minPP指针当前节点，也就是指向要查找的右子树的节点的父节点
            Node minPP = p;
            //循环查找最小的那个左节点
            while (minP.getLeft() != null) {
                //父节点指向当前节点
                minPP = minP;
                //当前节点指向当前节点的左节点
                minP = minP.getLeft();
            }
            //这样将当前要删除节点的值，设置为当前节点右子树中的最左节点的值，表示删除就变成了删除那个右子树中的最左子节点
            p.setData(minP.getData());
            //要删除的指针就指向了最左子节点，父节点就变成了那个最左子节点的父节点
            p = minP;
            pp = minPP;
        }
        //经过上面循环的处理，最终变成了要删除一个最多只有一个子节点的操作
        //如果要删除的是根节点
        if(p==tree){
            //如果根节点的右孩子不为空，那么根节点指向右孩子
            if(p.getRight() != null){
                tree=p.getRight();
                //如果左孩子不为空，那么指向左孩子
            }else if(p.getLeft() != null){
                tree=p.getLeft();
            }else{
                //否则根节点变成空
                tree=null;
            }
        }
        //如果当前要删除节点是父节点的左孩子
         else if (p == pp.getLeft()) {
             //如果当前节点的左孩子不为空，那么当前节点左孩子变成父节点左孩子
            if (p.getLeft() != null) {
                pp.setLeft(p.getLeft());
                //如果当前节点右孩子不为空，那么当前节点右孩子变成父节点左孩子
            } else if (p.getRight() != null) {
                pp.setLeft(p.getRight());
            } else {
                //否则父节点左孩子为空
                pp.setLeft(null);
            }
        }
         //如果当前要删除的节点是父节点的右孩子
        else {
            //如果当前节点的左孩子不为空，那么当前节点左孩子变成了父节点的右孩子
            if (p.getLeft() != null) {
                pp.setRight(p.getLeft());
                //如果当前节点的右孩子不为空，那么当前节点的右孩子变成了父节点的右孩子
            } else if (p.getRight() != null) {
                pp.setRight(p.getRight());
            } else {
                //否则父节点的右孩子为空
                pp.setRight(null);
            }
        }
    }

    /**
     * 中序遍历，先打印左子树，然后打印当前节点，最后打印右子树，最后遍历也是相当于一个排序过程了
     * @param node
     */
    public void middle(Node node) {
        if (node == null) {
            return;
        }
        middle(node.getLeft());
        System.out.println(node.getData());
        middle(node.getRight());
    }

    public Node getTree() {
        return tree;
    }
}
