package com.test.tree.rbTree;

/**
 * 红黑树实现
 *
 * @Auth 45208
 * @Date 1/17/2021
 **/
public class RbTree<T extends Comparable<T>> {
    /**
     * 根节点,在初始化的时候需要将根节点设置成哨兵，这样方便统一做业务判断
     */
    private RbTreeNode<T> root;

    /**
     * 哨兵节点
     * 在这里哨兵节点试非常重要的一个节点，在初始化的时候创建一个哨兵，并且哨兵节点的颜色必须试黑色的，在之后的使用中，所有的叶子节点的左右孩子都指向哨兵，
     * 并且根节点的父节点也是指向哨兵节点，这样处理的目的试在后续的业务逻辑判断中，直接判断是否试哨兵节点并且哨兵节点的颜色是黑色的方便做业务逻辑的处理，而不用写一大堆的
     * 特殊处理代码去判断非空，判断颜色等，所以在插入节点的时候，需要设置好插入节点的左右孩子为哨兵
     */
    private RbTreeNode<T> nil;


    /**
     * 初始化的时候需要初始化哨兵节点，并且哨兵节点的颜色设置为黑色的
     * 根节点初始化为哨兵节点
     */
    public RbTree() {
        nil = new RbTreeNode<>();
        nil.setColor(1);
        root = nil;
    }

    /**
     * 左旋操作，是以当前节点以及当前节点右孩子的连接为轴，向左方向的旋转
     *
     * @param tree
     * @param node
     */
    private void leftRotation(RbTree<T> tree, RbTreeNode<T> node) {
        /**
         * 首先获取当前要左旋节点的右孩子x
         * 设置当前节点右孩子为右孩子的左孩子y
         * 如果y不是哨兵节点，那么需要设置y节点的父节点为当前节点
         * 判断当前节点的父节点是不是哨兵，如果是哨兵将x设置成树的根节点
         * 否则检查x是父节点的左孩子还是右孩子，将父节点对应的孩子节点的指针指向y
         * 最后设置右孩子y的父节点为父节点的父节点
         */
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
            tree.root = right;
        }
        node.setParent(right);
    }

    /**
     * 右旋操作，是以当前节点以及当前节点左孩子的连接为轴，向右方向的旋转
     * 原理与左旋一样
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
        if (node.getParent() != tree.nil) {
            if (node == node.getParent().getLeft()) {
                node.getParent().setLeft(left);
            } else {
                node.getParent().setRight(left);
            }
        } else {
            tree.root = left;
        }
        node.setParent(left);
    }

    private void insert(RbTree<T> tree, RbTreeNode<T> node) {
        RbTreeNode<T> parent = tree.nil;
        RbTreeNode<T> location = tree.root;
        while (location != tree.nil) {
            parent = location;
            if (node.getKey().compareTo(location.getKey()) < 0) {
                location = location.getLeft();
            } else {
                location = location.getRight();
            }
        }
        node.setParent(parent);
        if (parent == tree.nil) {
            tree.root = node;
        } else if (node.getKey().compareTo(parent.getKey()) < 0) {
            parent.setLeft(node);
        } else {
            parent.setRight(node);
        }
        node.setLeft(tree.nil);
        node.setRight(tree.nil);
        node.setColor(0);
        insertFix(tree, node);
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
     *
     * @param tree
     * @param node
     */
    private void insertFix(RbTree<T> tree, RbTreeNode<T> node) {
        /**
         * 因为当前节点是红色的，并且如果父节点也是红色的，那么就需要进行处理
         */
        while (node.getParent().getColor() == 0) {
            //如果当前节点的父节点是爷爷节点的左孩子
            if (node.getParent() == node.getParent().getParent().getLeft()) {
                RbTreeNode uncle = node.getParent().getParent().getRight();
                //如果叔叔的节点也是红色的，那么就将父节点和叔叔节点染成黑色，将爷爷节点染成红色，然后将当前
                //然后将指针移动到爷爷节点，因为爷爷节点是红色的，可能会违反
                // (2根节点是黑色，4或者一个节点的左右孩子节点都是黑色两条性质中的一条)，需要继续执行
                if (uncle.getColor() == 0) {
                    node.getParent().setColor(1);
                    uncle.setColor(1);
                    node.getParent().getParent().setColor(0);
                    node = node.getParent().getParent();
                }
                //如果叔叔节点是黑色的，并且当前节点是父节点的右孩子，那么当前节点指针上移到父节点，然后父节点进行左旋转
                else if (node == node.getParent().getRight()) {
                    node = node.getParent();
                    leftRotation(tree, node);
                    //如果叔叔节点是黑色的，并且当前节点是父节点的左孩子，
                    //那么将父节点变成黑色的，将爷爷节点变成红色的，,然后将指针进行上移到爷爷节点，最后对爷爷节点进行右旋转
                } else {
                    node.getParent().setColor(1);
                    node.getParent().getParent().setColor(0);
                    node = node.getParent().getParent();
                    rightRotation(tree, node);
                }
                //如果当前节点的父节点是爷爷节点的右孩子
            } else {
                //当前爷爷节点的左孩子就是当前节点的叔叔节点
                RbTreeNode uncle = node.getParent().getParent().getLeft();
                //如果叔叔节点是红色的，那么将父节点和叔叔节点染黑，然后将爷爷节点染成红色,然后将指针指向爷爷节点
                if (uncle.getColor() == 0) {
                    node.getParent().setColor(1);
                    uncle.setColor(1);
                    node.getParent().getParent().setColor(0);
                    node = node.getParent().getParent();
                    //如果叔叔节点是黑色的，并且当前节点是父节点的左孩子，那么将指针移到父节点，然后对父节点进行右旋
                } else if (node == node.getParent().getLeft()) {
                    node = node.getParent();
                    rightRotation(tree, node);
                    //如果叔叔节点是黑色的，并且当前节点是父节点的右孩子，那么将父节点设置为黑色的，将爷爷节点变成红色的，
                    //最后对爷爷节点进行左旋
                } else {
                    node.getParent().setColor(1);
                    node.getParent().getParent().setColor(0);
                    node = node.getParent().getParent();
                    leftRotation(tree, node);
                }
            }
        }
        //最后将根节点颜色设置成黑色的
        tree.root.setColor(1);
    }

    /**
     * 删除完成之后需要进行移植操作，主要就是处理孩子节点和父亲节点的关系，需要将孩子节点的父指针指向当前删除节点的父节点
     * 需要将父节点的某个孩子节点指针指向当前删除节点的孩子节点
     *
     * @param tree
     * @param parent
     * @param child
     */
    private void transplant(RbTree<T> tree, RbTreeNode<T> parent, RbTreeNode<T> child) {
        /**
         * 如果当前要删除节点的父节点是nil，那么表示当前要删除节点是根节点，那么删除之后，它的孩子节点变成新的根节点
         */
        if (parent.getParent() == tree.nil) {
            tree.root = child;
        }
        /**
         * 如果当前要删除节点是父节点的左孩子，那么父节点的左孩子指针指向当前删除节点的孩子节点，否则
         * 父节点的右孩子指针指向当前删除节点的孩子节点
         */
        if (parent == parent.getParent().getLeft()) {
            parent.getParent().setLeft(child);
        } else {
            parent.getParent().setRight(child);
        }
        /**
         * 最后当前要删除节点的孩子节点的指针指向了当前要删除节点的父节点
         */
        child.setParent(parent.getParent());
    }

    private void delete(RbTree<T> tree, T t) {
        /**
         * 先在树中找到要删除值对应的节点的位置，如果找不到直接返回，如果找到了那么就进行后面的删除操作
         */
        RbTreeNode<T> node = tree.root;
        while (node != tree.nil && node.getKey().compareTo(t) != 0) {
            if (t.compareTo(node.getKey()) < 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        if (node == tree.nil) {
            return;
        }
        /**
         * 用tmp记录当前要删除或者要移动到树中的那个节点，必须要记录tmp的原来颜色，因为如果试黑色的话，再被删除或者移动之后，就会使红黑树的某些性质被违反
         * 用child来记录tmp的孩子，因为tmp被移动或者删除之后，因为可能谋反了红黑树的某些性质，而child占据了tmp原来的位置，所以需要对child做调整使其恢复红黑树的性质
         * 对于删除来说，主要是根据child对应的兄弟节点的颜色和兄弟节点孩子的颜色来做不同的操作恢复性质
         */
        RbTreeNode<T> tmp = node;
        int originColor = tmp.getColor();
        RbTreeNode<T> child = null;
        if (node.getLeft() == tree.nil) {
            child = node.getRight();
            transplant(tree, node, child);
        } else if (node.getRight() == tree.nil) {
            child = node.getLeft();
            transplant(tree, node, child);
        } else {
            tmp = getMinimun(tree, node.getRight());
            originColor = tmp.getColor();
            child = tmp.getRight();
            if (tmp.getParent() == node) {
                child.setParent(tmp);
            } else {
                transplant(tree, tmp, child);
                tmp.setRight(node.getRight());
                tmp.getRight().setParent(tmp);
            }
            transplant(tree, node, tmp);
            tmp.setLeft(node.getLeft());
            tmp.getLeft().setParent(tmp);
            tmp.setColor(node.getColor());
        }
        if (originColor == 1) {
            deleteFix(tree, child);
        }
    }

    /**
     * 这里的整体思路就是，node指针指向的那个节点拥有一层额外的特殊黑色，加上了这层额外的黑色，那么使其从任何根路径到经过当前节点的路径的叶子节点的路径上都包含对应的黑色节点个数，
     * 我们要做的就是想办法将这层黑色往上移动，直到移动到某个子树的根节点，然后将这个根节点直接染黑，去掉多余的那层黑色，这也是最后一行代码的目的
     * <p>
     * 总结一下，当删除一个节点之后可以通过染黑和旋转恢复红黑树的性质，步骤不会超过4次
     * 需要判断当前节点与兄弟节点的颜色关系，以及兄弟节点的孩子节点的颜色和位置关系来做不同的处理
     * 第一种情况，如果兄弟节点是红色的，那么将兄弟节点设置成黑色的，将父节点设置成红色的，并且对父节点执行一次与当前节点位置相同方向的旋转
     * 第二种情况，如果兄弟节点是黑色的，并且两个孩子节点也都是黑色的，那么将兄弟节点设置成黑色的，并且将指针移动到当前节点的父节点，进行下一轮处理
     * 第三种情况，如果兄弟节点是黑色的，并且兄弟节点中那个与自己位置相反方向的孩子节点颜色是黑色的，但是另外一个孩子的颜色是红色的，
     * 那么就交换兄弟节点和另外一个孩子节点的颜色，将兄弟节点设置成黑色的，将另外一个孩子设置成红色的，并且对兄弟节点执行一次与自己位置相反方向的旋转
     * 第四种情况，如果兄弟节点是黑色的，但是兄弟节点中那个与自己位置相反方向的孩子节点颜色是红色的，那么需要交换兄弟节点与父节点的颜色，并且将与自己位置
     * 相反方向的那个孩子节点的颜色设置成黑色的，并且对父节点执行一次与自己位置相同方向的旋转，将指针指向树的根节点，推出循环
     * 最后将根节点设置成黑色的
     *
     * @param tree
     * @param node
     */
    private void deleteFix(RbTree<T> tree, RbTreeNode<T> node) {
        //当当前节点不是根节点，并且当前节点的颜色为黑色时继续执行调整
        while (node != tree.root && node.getColor() == 1) {
            //如果当前节点是父节点的左孩子
            if (node == node.getParent().getLeft()) {
                //那么获取到当前节点的兄弟节点，也就是当前节点父节点的有孩子
                RbTreeNode<T> brother = node.getParent().getRight();
                //如果右兄弟节点是红色的
                if (brother.getColor() == 0) {
                    /**
                     * 兄弟节点设置成黑色的
                     * 父节点设置成红色的
                     */
                    brother.setColor(1);
                    node.getParent().setColor(0);
                    /**
                     * 做与兄弟节点位置相反方向的选转，当前兄弟节点是右，那么就对父节点做左旋转，这样兄弟节点这个黑色节点就变成了当前子树的根节点
                     */
                    leftRotation(tree, node.getParent());
                    //获取当前节点新的兄弟
                    brother = node.getParent().getRight();
                }
                /**
                 * 当兄弟节点的右孩子是黑色的，并且兄弟节点的左孩子也是黑色的时候，那么将兄弟节点染红，然后指针移动到父节点继续执行
                 */
                if (brother.getRight().getColor() == 1 && brother.getLeft().getColor() == 1) {
                    brother.setColor(0);
                    node = node.getParent();
                    /**
                     * 如果兄弟节点是黑色的，并且兄弟节点的右孩子是黑色，但是左孩子是红色的时候，将左孩子染黑，兄弟节点设置成红色的，
                     * 将兄弟节点按照染黑孩子的相反方向选转，最后获取节点新的兄弟节点
                     */
                } else if (brother.getRight().getColor() == 1 && brother.getLeft().getColor() == 0) {
                    brother.getLeft().setColor(1);
                    brother.setColor(0);
                    rightRotation(tree, brother);
                } else {
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
                    leftRotation(tree, node.getParent());
                    node = tree.root;
                }
            } else {
                /**
                 * 如果当前节点是父节点的右孩子，那么当前节点的兄弟节点就是父节点的左孩子
                 */
                RbTreeNode<T> brother = node.getParent().getLeft();
                /**
                 * 如果兄弟节点是红色的，那么将兄弟节点染黑，父节点染红，然后对父节点进行一次与兄弟节点位置相反方向的旋转
                 * 如果兄弟节点是左孩子，那么就进行右旋
                 * 然后获取到新的兄弟节点，并且新的兄弟节点一定是黑色的
                 */
                if (brother.getColor() == 0) {
                    brother.setColor(1);
                    node.getParent().setColor(0);
                    rightRotation(tree, node.getParent());
                    brother = node.getParent().getLeft();
                }
                /**
                 * 如果兄弟节点是黑色的，并且兄弟节点的左孩子是黑色的，并且右孩子也是黑色的
                 * 那么兄弟节点设置成红色，并且将指针向上移动到父节点
                 */
                if (brother.getLeft().getColor() == 1 && brother.getRight().getColor() == 1) {
                    brother.setColor(0);
                    node = node.getParent();
                    /**
                     * 如果兄弟节点是黑色的，并且兄弟节点的左孩子是黑色的，并且兄弟节点右孩子是红色的，
                     * 那么交换兄弟节点和兄弟节点右孩子的颜色
                     * 并且对兄弟节点致性一次与兄弟节点位置相反方向的旋转，这里是进行一次左旋
                     */
                } else if (brother.getLeft().getColor() == 1 && brother.getRight().getColor() == 0) {
                    brother.getRight().setColor(1);
                    brother.setColor(0);
                    leftRotation(tree, brother);
                    /**
                     * 如果兄弟节点是红色的，并且兄弟节点的左孩子也是红色的，那么交换兄弟节点和父节点的颜色
                     * 并且将兄弟节点的右孩子染黑
                     * 然后对父节点执行一次与兄弟节点位置相反方向的旋转，这里是进行一次右旋
                     * 最后将当前指针指向树的根节点，让其在下一次执行中推出循环
                     */
                } else {
                    brother.setColor(node.getParent().getColor());
                    node.getParent().setColor(1);
                    node.getLeft().setColor(1);
                    rightRotation(tree, node.getParent());
                    node = tree.root;
                }
            }
        }
        //node指向红黑节点，那么只需要将当前node染黑就行，就去掉了多余的那层黑色
        node.setColor(1);
    }

    /**
     * 插入操作
     *
     * @param t
     */
    public void insert(T t) {
        RbTreeNode<T> node = new RbTreeNode<>();
        node.setKey(t);
        insert(this, node);
    }

    /**
     * 删除操作
     *
     * @param t
     */
    public void delete(T t) {
        delete(this, t);
    }

    private RbTreeNode<T> getMinimun(RbTree<T> tree, RbTreeNode<T> node) {
        RbTreeNode<T> mini = node;
        while (mini.getLeft() != tree.nil) {
            mini = mini.getLeft();
        }
        return mini;
    }

    public void mid() {
        if (this.root != this.nil) {
            innerMid(this.root);
        }
    }

    private void innerMid(RbTreeNode<T> node) {
        if (node == nil) {
            return;
        }
        innerMid(node.getLeft());
        System.out.println("{key=" + node.getKey() + ",color=" + node.getColor() + "}");
        innerMid(node.getRight());
    }


}
