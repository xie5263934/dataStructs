package com.test.tree.rbTree;

/**
 * 红黑树实现
 *
 * @Auth 45208
 * @Date 1/17/2021
 **/
public class RbTree<T extends Comparable<T>> {
    /**
     * 根节点
     */
    private RbTreeNode<T> root;

    /**
     * 哨兵节点
     */
    private RbTreeNode<T> nil;

    /**
     * 左旋操作，是以当前节点以及当前节点右孩子的连接为轴，向左方向的旋转
     *
     * @param tree
     * @param node
     */
    public void leftRotation(RbTree<T> tree, RbTreeNode<T> node) {
        RbTreeNode<T> right = node.getRight();
        node.setRight(right.getLeft());
        if (right.getLeft() != tree.nil) {
            right.getLeft().setParent(node);
        }
        right.setLeft(node);
        right.setParent(node.getParent());
        if (node.getParent() != tree.nil) {
            if (node == node.getParent().getLeft()) {
                node.getParent().setLeft(right);
            } else {
                node.getParent().setRight(right);
            }
        } else {
            tree.setRoot(right);
        }
        node.setParent(right);
    }

    /**
     * 右旋操作，是以当前节点以及当前节点左孩子的连接为轴，向右方向的旋转
     *
     * @param tree
     * @param node
     */
    public void rightRotation(RbTree<T> tree, RbTreeNode<T> node) {
        RbTreeNode<T> left = node.getLeft();
        node.setLeft(left.getRight());
        if (left.getRight() != tree.nil) {
            left.getRight().setParent(node);
        }
        left.setRight(node);
        left.setParent(node.getParent());
        if (node.getParent() != tree.getNil()) {
            if (node == node.getParent().getLeft()) {
                node.getParent().setLeft(left);
            } else {
                node.getParent().setRight(left);
            }
        } else {
            tree.setRoot(left);
        }
        node.setParent(left);
    }

    public void insert(RbTree<T> tree, RbTreeNode<T> node){
        RbTreeNode<T> parent = tree.nil;
        RbTreeNode<T> location = tree.root;
        while(location != tree.nil){
            parent = location;
            if(node.getKey().compareTo(location.getKey()) < 0){
                location = location.getLeft();
            }else{
                location = location.getRight();
            }
        }
        node.setParent(parent);
        if(parent == tree.nil){
            tree.root = node;
        }
        node.setLeft(tree.nil);
        node.setRight(tree.nil);
        node.setColor(0);
        insertFix(tree,node);
    }

    /**
     * 总结插入修复最多不会超过3次修复动作，就能让红黑树恢复性质
     * 其中插入新节点之后，当前节点默认就是红色，如果父节点也是红色，那么就需要进行修正，因为违反了性质4，
     * 首先要找到当前节点的叔叔节点 ,然后根据叔叔节点的颜色和当前节点的方向与叔叔节点方向的关系进行不同的处理
     * 第一种情况，如果叔叔节点是红色的，那么就将父节点和叔叔节点都染成黑色的，将爷爷节点染成红色的，然后指针移动到爷爷节点
     * 第二种情况，如果叔叔节点是黑色的，那么判断当前节点的方向是否与叔叔节点方向一样，比如叔叔节点是爷爷节点的右孩子，当前节点也是父节点的右孩子，
     * 那么就需要将指针移动到父节点，然后执行一个与当前节点方向相反方向的旋转，本例中为左旋
     * 第三种情况，如果叔叔节点是黑色的，那么判断当前节点的方向是否与叔叔节点方向相反，例如叔叔节点是爷爷节点的右孩子，当前节点是父节点的左孩子，
     * 那么需要将父节点染成黑色的，将爷爷节点染成红色的，然后将指针移动到爷爷节点，最后对爷爷节点执行一个与当前节点相反方向的旋转，本例中为右旋
     * @param tree
     * @param node
     */
    public void insertFix(RbTree<T> tree , RbTreeNode<T> node){
        /**
         * 因为当前节点是红色的，并且如果父节点也是红色的，那么就需要进行处理
         */
        while(node.getParent().getColor() == 0){
            //如果当前节点的父节点是爷爷节点的左孩子
            if(node.getParent() == node.getParent().getParent().getLeft()){
                RbTreeNode uncle = node.getParent().getParent().getRight();
                //如果叔叔的节点也是红色的，那么就将父节点和叔叔节点染成黑色，将爷爷节点染成红色，然后将当前
                //然后将指针移动到爷爷节点，因为爷爷节点是红色的，可能会违反
                // (2根节点是黑色，4或者一个节点的左右孩子节点都是黑色两条性质中的一条)，需要继续执行
                if(uncle.getColor() == 0){
                    node.getParent().setColor(1);
                    uncle.setColor(1);
                    node.getParent().getParent().setColor(0);
                    node = node.getParent().getParent();
                }
                //如果叔叔节点是黑色的，并且当前节点是父节点的右孩子，那么当前节点指针上移到父节点，然后父节点进行左旋转
                else if(node == node.getParent().getRight()){
                    node = node.getParent();
                    leftRotation(tree,node);
                    //如果叔叔节点是黑色的，并且当前节点是父节点的左孩子，
                    //那么将父节点变成黑色的，将爷爷节点变成红色的，,然后将指针进行上移到爷爷节点，最后对爷爷节点进行右旋转
                }else{
                    node.getParent().setColor(1);
                    node.getParent().getParent().setColor(0);
                    node = node.getParent().getParent();
                    rightRotation(tree,node);
                }
                //如果当前节点的父节点是爷爷节点的右孩子
            }else{
                //当前爷爷节点的左孩子就是当前节点的叔叔节点
                RbTreeNode uncle = node.getParent().getParent().getLeft();
                //如果叔叔节点是红色的，那么将父节点和叔叔节点染黑，然后将爷爷节点染成红色,然后将指针指向爷爷节点
                if(uncle.getColor() == 0){
                    node.getParent().setColor(1);
                    uncle.setColor(1);
                    node.getParent().getParent().setColor(0);
                    node = node.getParent().getParent();
                    //如果叔叔节点是黑色的，并且当前节点是父节点的左孩子，那么将指针移到父节点，然后对父节点进行右旋
                }else if(node == node.getParent().getLeft()){
                    node = node.getParent();
                    rightRotation(tree,node);
                    //如果叔叔节点是黑色的，并且当前节点是父节点的右孩子，那么将父节点设置为黑色的，将爷爷节点变成红色的，
                    //最后对爷爷节点进行左旋
                }else{
                    node.getParent().setColor(1);
                    node.getParent().getParent().setColor(0);
                    node = node.getParent().getParent();
                    leftRotation(tree,node);
                }
            }
        }
        //最后将根节点颜色设置成黑色的
        tree.getRoot().setColor(1);
    }

    /**
     * 删除完成之后需要进行移植操作，主要就是处理孩子节点和父亲节点的关系，需要将孩子节点的父指针指向当前删除节点的父节点
     * 需要将父节点的某个孩子节点指针指向当前删除节点的孩子节点
     * @param tree
     * @param parent
     * @param child
     */
    private void transplant(RbTree<T> tree, RbTreeNode<T> parent, RbTreeNode<T> child){
        /**
         * 如果当前要删除节点的父节点是nil，那么表示当前要删除节点是根节点，那么删除之后，它的孩子节点变成新的根节点
         */
        if(parent.getParent() == tree.nil){
            tree.setRoot(child);
        }
        /**
         * 如果当前要删除节点是父节点的左孩子，那么父节点的左孩子指针指向当前删除节点的孩子节点，否则
         * 父节点的右孩子指针指向当前删除节点的孩子节点
         */
        if(parent == parent.getParent().getLeft()){
            parent.getParent().setLeft(child);
        }else{
            parent.getParent().setRight(child);
        }
        /**
         * 最后当前要删除节点的孩子节点的指针指向了当前要删除节点的父节点
         */
        child.setParent(parent.getParent());
    }

    private void delete(RbTree<T> tree, RbTreeNode<T> node){
        /**
         * 用tmp记录当前要删除或者要移动到树中的那个
         */
        RbTreeNode<T> tmp = node;
        int originColor = tmp.getColor();
        RbTreeNode<T> child = null;
        if(node.getLeft() == tree.nil){
            child = node.getRight();
            transplant(tree,node,child);
        }else if(node.getRight() == tree.nil){
            child = node.getLeft();
            transplant(tree, node, child);
        }else{
            tmp = getMinimun(tree,node.getRight());
            originColor = tmp.getColor();
            child = tmp.getRight();
            if(tmp.getParent() == node){
                child.setParent(tmp);
            }else{
                transplant(tree,tmp,child);
                tmp.setRight(node.getRight());
                tmp.getRight().setParent(tmp);
            }
            transplant(tree,node,tmp);
            tmp.setLeft(node.getLeft());
            tmp.getLeft().setParent(tmp);
            tmp.setColor(node.getColor());
        }
        if(originColor == 1){
            deleteFix(tree,child);
        }
    }

    /**
     * 总结一下，当删除一个节点之后可以通过染黑和旋转恢复红黑树的性质，步骤不会超过4次
     * 需要判断当前节点与兄弟节点的颜色关系，以及兄弟节点的孩子节点的颜色和位置关系来做不同的处理
     * 第一种情况，如果兄弟节点是红色的，那么将兄弟节点设置成黑色的，将父节点设置成红色的，并且对父节点执行一次与当前节点位置相同方向的旋转
     * 第二种情况，如果兄弟节点是黑色的，并且两个孩子节点也都是黑色的，那么将兄弟节点设置成黑色的，并且将指针移动到当前节点的父节点，进行下一轮处理
     * 第三种情况，如果兄弟节点是黑色的，并且兄弟节点中那个与自己位置相反方向的孩子节点颜色是黑色的，但是另外一个孩子的颜色是红色的，
     * 那么就交换兄弟节点和另外一个孩子节点的颜色，将兄弟节点设置成黑色的，将另外一个孩子设置成红色的，并且对兄弟节点执行一次与自己位置相反方向的旋转
     * 第四种情况，如果兄弟节点是黑色的，但是兄弟节点中那个与自己位置相反方向的孩子节点颜色是红色的，那么需要交换兄弟节点与父节点的颜色，并且将与自己位置
     * 相反方向的那个孩子节点的颜色设置成黑色的，并且对父节点执行一次与自己位置相同方向的旋转，将指针指向树的根节点，推出循环
     * 最后将根节点设置成黑色的
     * @param tree
     * @param node
     */
    private void deleteFix(RbTree<T> tree, RbTreeNode<T> node){
        //当当前节点不是根节点，并且当前节点的颜色为黑色时继续执行调整
        while(node != tree.nil && node.getColor() ==  1){
            //如果当前节点是父节点的左孩子
            if(node == node.getParent().getLeft()){
                //那么获取到当前节点的兄弟节点，也就是当前节点父节点的有孩子
                RbTreeNode<T> brother = node.getParent().getRight();
                //如果右兄弟节点是红色的
                if(brother.getColor() == 0){
                    /**
                     * 兄弟节点设置成黑色的
                     * 父节点设置成红色的
                     */
                    brother.setColor(1);
                    node.getParent().setColor(0);
                    /**
                     * 做与兄弟节点位置相反方向的选转，当前兄弟节点是右，那么就对父节点做左旋转，这样兄弟节点这个黑色节点就变成了当前子树的根节点
                     */
                    leftRotation(tree,node.getParent());
                    //获取当前节点新的兄弟
                    brother = node.getParent().getRight();
                }
                /**
                 * 当兄弟节点的右孩子是黑色的，并且兄弟节点的左孩子也是黑色的时候，那么将兄弟节点染红，然后指针移动到父节点继续执行
                 */
                if(brother.getRight().getColor() == 1 && brother.getLeft().getColor() == 1){
                    brother.setColor(0);
                    node = node.getParent();
                    /**
                     * 如果兄弟节点是黑色的，并且兄弟节点的右孩子是黑色，但是左孩子是红色的时候，将左孩子染黑，兄弟节点设置成红色的，
                     * 将兄弟节点按照染黑孩子的相反方向选转，最后获取节点新的兄弟节点
                     */
                }else if(brother.getRight().getColor() == 1 && brother.getLeft().getColor() == 0){
                    brother.getLeft().setColor(1);
                    brother.setColor(0);
                    rightRotation(tree,brother);
                }else{
                    /**
                     * 如果当前兄弟节点是黑色的，但是兄弟节点的右孩子是红色的，那么不管兄弟节点的左孩子是什么颜色
                     * 将兄弟节点的颜色设置成父节点的颜色
                     * 将父节点染黑
                     * 将兄弟节点的右孩子染黑
                     * 最后将父节点进行与兄弟节点相反方向的选转
                     * 将当前指针指向树的根节点，退出循环
                     */
                    brother.setColor(node.getParent().getColor());
                    node.getParent().setColor(1);
                    brother.getRight().setColor(1);
                    leftRotation(tree,node.getParent());
                    node = tree.root;
                }
            }else{
                /**
                 * 如果当前节点是父节点的右孩子，那么当前节点的兄弟节点就是父节点的左孩子
                 */
                RbTreeNode<T> brother = node.getParent().getLeft();
                /**
                 * 如果兄弟节点是红色的，那么将兄弟节点染黑，父节点染红，然后对父节点进行一次与兄弟节点位置相反方向的旋转
                 * 如果兄弟节点是左孩子，那么就进行右旋
                 * 然后获取到新的兄弟节点，并且新的兄弟节点一定是黑色的
                 */
                if(brother.getColor() == 0){
                    brother.setColor(1);
                    node.getParent().setColor(0);
                    rightRotation(tree,node.getParent());
                    brother = node.getParent().getLeft();
                }
                /**
                 * 如果兄弟节点是黑色的，并且兄弟节点的左孩子是黑色的，并且右孩子也是黑色的
                 * 那么兄弟节点设置成红色，并且将指针向上移动到父节点
                 */
                if(brother.getLeft().getColor() == 1&& brother.getRight().getColor() == 1){
                    brother.setColor(0);
                    node = node.getParent();
                    /**
                     * 如果兄弟节点是黑色的，并且兄弟节点的左孩子是黑色的，并且兄弟节点右孩子是红色的，
                     * 那么交换兄弟节点和兄弟节点右孩子的颜色
                     * 并且对兄弟节点致性一次与兄弟节点位置相反方向的旋转，这里是进行一次左旋
                     */
                }else if(brother.getLeft().getColor() == 1&&brother.getRight().getColor() ==0){
                    brother.getRight().setColor(1);
                    brother.setColor(0);
                    leftRotation(tree, brother);
                    /**
                     * 如果兄弟节点是红色的，并且兄弟节点的左孩子也是红色的，那么交换兄弟节点和父节点的颜色
                     * 并且将兄弟节点的右孩子染黑
                     * 然后对父节点执行一次与兄弟节点位置相反方向的旋转，这里是进行一次右旋
                     * 最后将当前指针指向树的根节点，让其在下一次执行中推出循环
                     */
                }else{
                    brother.setColor(node.getParent().getColor());
                    node.getParent().setColor(1);
                    node.getLeft().setColor(1);
                    rightRotation(tree,node.getParent());
                    node = tree.root;
                }
            }
        }
        //最后将根节点染黑
        node.setColor(1);
    }

    private RbTreeNode<T> getMinimun(RbTree<T> tree, RbTreeNode<T> node){
        RbTreeNode<T> mini = node;
        while(node.getLeft() != tree.nil){
            mini = mini.getLeft();
        }
        return mini;
    }

    public RbTreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(RbTreeNode<T> root) {
        this.root = root;
    }

    public RbTreeNode<T> getNil() {
        return nil;
    }

    public void setNil(RbTreeNode<T> nil) {
        this.nil = nil;
    }
}
