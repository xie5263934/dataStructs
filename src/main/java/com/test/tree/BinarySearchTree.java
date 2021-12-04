package com.test.tree;

import java.util.Stack;

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

    /**
     * 非递归实现先序遍历
     */
    public void preOrder(){
        if(tree==null){
            return;
        }
        /**
         * 首先创建一个栈，这个栈用来存储节点，用来模拟递归调用的逻辑
         * 首先这里我们把每次处理的当前节点都当成是以当前节点为根的子树的根节点
         */
        Stack<Node> stack = new Stack<>();
        Node node = tree;
        //如果当前处理的节点，或者栈不为空那么就继续处理
        while(node != null || !stack.isEmpty()){
            /**
             * 首先处理当前节点，把当前节点入栈，相当于递归调用的当前方法入栈，然后获取当前节点的左孩子，
             * 如果左孩子不为空，那么左孩子变成了当前节点，重复里面的逻辑。
             */
            while(node!=null){
                System.out.println(node.getData());
                stack.push(node);
                node = node.getLeft();
            }
            /**
             * 如果栈不为空，那么标识当前栈顶的元素已经处理过左孩子和自己了。但是还没有处理右孩子
             * 那么这个时候，将当前节点从栈中弹出，我们取出右孩子，右孩子变成了是右子树的根节点，那么我们对
             * 右孩子重复整个根节点的逻辑。
             */
            if(!stack.isEmpty()){
                node = stack.pop();
                node = node.getRight();
            }
        }
    }

    /**
     * 中序遍历
     * 逻辑上面非递归前序遍历逻辑一样的。只不过我们并不是一来就处理当前节点，而是先处理左孩子节点，
     * 等到左孩子节点处理完成了，我们再处理当前节点，然后再处理右孩子节点
     */
    public void midOrder(){
        if(tree==null){
            return;
        }
        Node node = tree;
        Stack<Node> stack = new Stack<>();
        /**
         * 如果当前节点或者跟栈不为空，那么继续当前逻辑
         */
        while(node != null || !stack.isEmpty()){
            /**
             * 如果当前根节点不为空，那么我们先不处理当前节点，而是将当前节点压入栈，然后继续处理当前节点的左子树
             */
            while(node != null){
                stack.push(node);
                node = node.getLeft();
            }
            /**
             * 如果栈不为空，那么标识位于当前栈顶的元素左子树已经处理完毕了，但是栈顶这个节点和右子树还没有处理，那么我们先获取
             * 当前节点的值，然后获取当前节点的右子树，然后继续重复整个逻辑。
             */
            if(!stack.isEmpty()){
                node = stack.pop();
                System.out.println(node.getData());
                node = node.getRight();
            }
        }
    }

    /**
     * 整体逻辑与前序或者中序一样的，但是因为当前节点是最后处理，所以我们需要一个标识来标识当前节点是否已经处理了右孩子
     * 为什么需要一个标识来标识是否已经处理右孩子，是因为我们使用了一个栈来保存节点的访问，模拟了一个递归的过程，首先当前节点入栈
     * 然后一直不停地获取获取当前节点的左孩子，把左孩子压入栈，表示对于左孩子的处理，这个时候左孩子被压入栈中位于当前节点的上面。
     * 等到弹栈到当前节点的时候，如果没有一个标识，对于当前节点的右孩子我们是处理了还是没处理我们没办法知道。因为假设我们弹栈到当前节点的时候
     * 当前节点不出栈，然后我们获取当前节点的右子树进行遍历访问，将当前节点的右子树的孩子入住栈中，位于当前节点的上面，等到弹栈到当前节点的时候
     * 我们是无法得知当前节点的右子树到底有没有被访问过，到底是应该访问当前节点，然后出栈还是，访问当前节点右子树，所以需要一个标识，如果这个标识表示
     * 我们没有访问过右子树，那么我们就去访问右子树，并且把这个标识修改了。等到右子树处理完成了，弹栈到当前节点的时候，我们通过标识知道右子树都已经被访问过了
     * 那么这个节点的左右子树都已经被访问过了，应该访问自己了。然后出站。
     */
    public void postOrder(){
        if(tree == null){
            return ;
        }
        Node node = tree;
        Stack<Node> stack = new Stack<>();
        /**
         * 如果当前根节点不为空或者栈不为空，那么继续执行
         */
        while(node != null||!stack.isEmpty()){
            /**
             * 如果当前根节点不为空，那么当前根节点入栈，然后获取当前节点的左孩子，然后处理左孩子。
             */
            while(node != null){
                stack.push(node);
                node = node.getLeft();
            }
            /**
             * 如果栈不为空，那么处理栈顶的元素，
             * 这个时候栈顶的元素它的左孩子肯定处理完成了，因为我们前面入栈的时候是一路往左，代码走到这步，肯定是左孩子已经处理好了
             * 那么这个时候我们要处理他自己或者它的右孩子，那么我们就需要一个标识来告诉我们，我们是处理右孩子还是自己
             * 如果标识为假，那么右孩子还没有处理过，那么设置标识，处理右孩子，否则处理自己。
             */
            if(!stack.isEmpty()){
                node = stack.peek();
                if(node.getRight() == null || node.getFlag()){
                    System.out.println(node.getData());
                    stack.pop();
                    node = null;
                }else if(node.getRight() != null ){
                    node.setFlag(true);
                    node = node.getRight();
                }
            }
        }
    }
}
