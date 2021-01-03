package com.test.tree.avl;

/**
 * 当前类，封装了AVL树的基本操作，包括新增，删除，前序遍历，中序遍历，后续遍历
 */
public class AVLTree<T extends Comparable<T>> {
    /**
     * 表示当前树的根节点
     */
    private AVLNode<T> root;

    public void add(T value) {
        AVLNode node = new AVLNode(value, null, null);
        root = add(root, node);
    }

    private AVLNode<T> add(AVLNode<T> tree, AVLNode value) {
        /**
         * 新增时，tree表示在查找插入位置的过程中经过的某个部分子树的根节点
         */
        if (tree == null) {
            tree = value;
            return tree;
        }
        int cmp = value.getKey().compareTo(tree.getKey());
        /**
         * 如果比较结果小于0，那么表示插入到当前tree节点的左子树中L
         */
        if (cmp < 0) {
            tree.setLeft(add(tree.getLeft(), value));
            /**
             * 因为是插入到tree的左子树中，所以左子树一定不会比右子树低，如果左子树比右子树高2，那么当前tree节点就是
             * 失去了平衡了，需要通过旋转来调节平衡
             */
            if (height(tree.getLeft()) - height(tree.getRight()) == 2) {
                /**
                 * 因为插入到了当前节点的左子树中，并且高度比右子树高出了2，那么左子树至少有两个节点，所以获取当前节点的左孩子
                 * 肯定是不会空的，另外还有一个节点肯定在左孩子的左子树或者右子树上，通过比较插入的值和左子树的值就可以判断出插入
                 * 的节点在左子树的左边还是右边。
                 * 如果插入的值比左孩子的值还要小，那么就是插入
                 * 到了左孩子的左子树中，也是L，所以就是LL的情况，如果插入的值大于等于左子树的值，那么就是插入到了左孩子的
                 * 右子树中，那么就是R，所以就是LR的情况
                 */
                AVLNode l = tree.getLeft();
                if (l.getKey().compareTo(value.getKey()) > 0) {
                    tree = leftleftRotation(tree);
                } else {
                    tree = leftRightRotation(tree);
                }
            }
        } else {
            /**
             * 因为比较结果大于0，所以插入到了tree节点的右子树中R
             *
             */
            tree.setRight(add(tree.getRight(), value));
            if (height(tree.getRight()) - height(tree.getLeft()) == 2) {
                /**
                 * 因为当前节点的右子树比左子树高2，所以右子树肯定至少有两个节点，所以获取当前节点的右子树肯定不为空
                 * 比较当前要插入节点的值和右子树的值，如果比右子树的值相等或者还大，那么肯定是插入到了右子树的右子树中R，所以是RR的情况
                 * 如果小于右子树的值，那么就插入到了右子树的左子树中L，所以是RL的情况
                 */
                AVLNode r = tree.getRight();
                if (r.getKey().compareTo(value.getKey()) > 0) {
                    tree = rightLeftRotaion(tree);
                } else {
                    tree = rightRightRotation(tree);
                }
            }
        }
        /**
         * 当插入节点之后，因为经过了当前tree节点，也就是在tree的子树中插入了新的节点，所以需要更新tree的高度
         * 取高度的时候，选择两个子树中高度大的那个，然后再+1，就是当前节点的高度
         */
        tree.setHeight(max(height(tree.getLeft()), height(tree.getRight())) + 1);
        return tree;
    }

    public void delete(T value) {
        AVLNode node = new AVLNode(value, null, null);
        delete(root, node);
    }

    /**
     * 从树中删除一个节点
     *
     * @param tree
     * @param value
     * @return
     */
    private AVLNode delete(AVLNode tree, AVLNode value) {
        if (tree == null) {
            return tree;
        }
        /**
         * 如果当前节点的值比要删除的节点的值大，那么就在当前节点的左子树中进行删除
         */
        if (tree.getKey().compareTo(value.getKey()) > 0) {
            /**
             * 在当前节点的左子树中进行删除，其实删除操作可以反过来看，如果是在左子树中进行删除，那么可以看成是在当前节点的右子树R中进行插入操作，
             * 那么我们只需要比较右子树的左右子树的高度，如果当前节点右孩子的左子树高，可以看成在当前节点右孩子的左子树L中进行了插入操作，那么就是RL情况，
             * 如果是当前节点的右孩子的右子树高，那么可以看成是在当前节点右孩子的右子树中进行了插入操作，那么是RR情况
             */
            tree.setLeft(delete(tree.getLeft(), value));
            if (height(tree.getRight()) - height(tree.getLeft()) == 2) {
                AVLNode right = tree.getRight();
                if (height(right.getRight()) >= height(right.getLeft())) {
                    tree = rightRightRotation(tree);
                } else {
                    tree = rightLeftRotaion(tree);
                }
            }
        } else if (tree.getKey().compareTo(value.getKey()) < 0) {
            /**
             * 在当前节点的右子树中进行删除操作，其实删除操作可以反过来看，如果是在右子树中进行删除，那么可以看成是在当前节点的左子树L中进行插入操作，
             * 那么我们只需要比较左子树的左右子树的高度，如果当前节点的左孩子的左子树高，那么看成在当前节点左孩子的左子树L中进行了插入操作，那么就是LL情况，
             * 如果是当前节点的左孩子的右子树高，那么可以看成是在当前节点的左孩子的右子树R中进行了插入操作，那么是LR情况
             */
            tree.setRight(delete(tree.getRight(), value));
            if (height(tree.getLeft()) - height(tree.getRight()) == 2) {
                AVLNode left = tree.getLeft();
                if (height(left.getLeft()) > height(left.getRight())) {
                    tree = leftleftRotation(tree);
                } else {
                    tree = leftRightRotation(tree);
                }
            }
        } else {
            /**
             * 删除的时候，如果当前的节点左右孩子都不为空，那么就使用替代删除的方案，根据当前要删除的节点的值，
             * 并且根据左右子树的高度，选择高度大的那一边，找一个替代的节点进行删除
             * 如果左子树高，那么就在左子树中选择最右的那棵节点
             * 如果右子树高，那么就在右子树中选择最左的那颗节点
             */
            if (tree.getLeft() != null && tree.getRight() != null) {
                if (height(tree.getLeft()) > height(tree.getRight())) {
                    AVLNode right = tree.getLeft();
                    while (right.getRight() != null) {
                        right = right.getRight();
                    }
                    tree.setKey(right.getKey());
                    tree.setLeft(delete(tree.getLeft(), right));
                } else {
                    AVLNode left = tree.getRight();
                    while (left.getLeft() != null) {
                        left = left.getLeft();
                    }
                    tree.setKey(left.getKey());
                    tree.setRight(delete(tree.getRight(), left));
                }
            } else {
                AVLNode tmp = tree;
                tree = tree.getLeft() != null ? tree.getLeft() : tree.getRight();
                tmp = null;
            }
        }
        if(tree != null){
            tree.setHeight(max(height(tree.getLeft()),height(tree.getRight())+1));
        }
        return tree;
    }


    /**
     * 查找某个节点的高度，因为节点的高度是存放在节点的height字段中，所以直接返回
     * 对于tree是null的情况，我们认为当前节点是个虚拟节点，高度为0
     *
     * @param tree
     * @return
     */
    private int height(AVLNode tree) {
        if (tree == null) {
            return -1;
        }
        return tree.getHeight();
    }

    /**
     * 左左情况下的旋转，以当前传入的root节点的左孩子pivot做往右方方向的旋转，
     * 当前pivot的右孩子变成了root的左孩子，root变成了pivot的右孩子
     * 重新计算root和pivot的高度
     * 返回新的root
     *
     * @param node
     * @return
     */
    private AVLNode leftleftRotation(AVLNode node) {
        if (node == null) {
            return node;
        }
        AVLNode leftNode = node.getLeft();
        node.setLeft(leftNode.getRight());
        leftNode.setRight(node);
        node.setHeight(max(height(node.getLeft()), height(node.getRight())) + 1);
        leftNode.setHeight(max(height(leftNode.getLeft()), node.getHeight()) + 1);
        return leftNode;
    }

    /**
     * 右右情况下的旋转，以当前传入的root节点的右孩子pivot做往左方方向的旋转
     * 当前pivot的左孩子变成了root的右孩子，root变成了pivot左孩子
     * 重新计算root和pivot的高度
     * 返回新的root
     *
     * @param node
     * @return
     */
    private AVLNode rightRightRotation(AVLNode node) {
        if (node == null) {
            return node;
        }
        AVLNode rightNode = node.getRight();
        node.setRight(rightNode.getLeft());
        rightNode.setLeft(node);
        node.setHeight(max(height(node.getLeft()), height(node.getRight())) + 1);
        rightNode.setHeight(max(node.getHeight(), height(rightNode.getRight())) + 1);
        return rightNode;
    }

    /**
     * 左右情况下的旋转，那么需要进行两步旋转
     * 第一步是对root的左孩子为根节点的树做右右旋转
     * 第二部是对root做左左旋转
     * 返回新的root
     *
     * @param node
     * @return
     */
    private AVLNode leftRightRotation(AVLNode node) {
        if (node == null) {
            return node;
        }
        node.setLeft(rightRightRotation(node.getLeft()));
        return leftleftRotation(node);
    }

    /**
     * 右左情况下的旋转，那么需要进行两步的旋转
     * 第一步是对root的右孩子为根节点的树做左左旋转
     * 第二部是对root做右右旋转
     * 返回新的root
     *
     * @param node
     * @return
     */
    private AVLNode rightLeftRotaion(AVLNode node) {
        if (node == null) {
            return node;
        }
        node.setRight(leftleftRotation(node.getRight()));
        return rightRightRotation(node);
    }

    private int max(int a, int b) {
        return a >= b ? a : b;
    }

    /**
     * 对当前树进行中旬遍历
     */
    public void mid(AVLNode<T> root) {
        if (root == null) {
            return;
        }
        mid(root.getLeft());
        System.out.println(root.getKey());
        mid(root.getRight());
    }

    public AVLNode<T> getRoot() {
        return root;
    }
}
