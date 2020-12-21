package com.test.tree.avl;

/**
 * 当前类，封装了AVL树的基本操作，包括新增，删除，前序遍历，中序遍历，后续遍历
 */
public class AVLTree {
    /**
     * 表示当前树的根节点
     */
    private AVLNode<Integer> root;

    public void add(Integer value){

    }

    private AVLNode<Integer> add(AVLNode<Integer> tree,Integer value){
        /**
         * 新增时，tree表示当前要新增节点经过的某个节点或者就是当前要新增的节点自己，只不过当新增时，自己是空，所以需要
         * 创建一个新的节点对象，并且将自己返回
         */
        if(tree == null){
            tree = new AVLNode(value,null,null);
            return tree;
        }
        int cmp = value.compareTo(tree.getKey());
        /**
         * 如果比较结果小于0，那么表示插入到当前tree节点的左子树中L
         */
        if(cmp < 0){
            tree.setLeft(add(tree.getLeft(),value));
            /**
             * 因为是插入到tree的左子树中，所以左子树一定不会比右子树低，如果左子树比右子树高2，那么当前tree节点就是
             * 失去了平衡了，需要通过旋转来调节平衡
             */
            if((height(tree.getLeft()) - height(tree.getRight())) == 2){
                /**
                 * 因为插入到了当前节点的左子树，所以获取左孩子肯定不会为空，如果插入的值比左孩子的值还要下，那么就是插入
                 * 到了左孩子的左子树中，也是L，所以就是LL的情况，如果插入的值大于等于左子树的值，那么就是插入到了最孩子的
                 * 右子树中，那么就是R，所以就是LR的情况
                 */
                AVLNode l = tree.getLeft();
                if(l.getKey().compareTo(value) > 0){
                    tree = leftleftRotation(tree);
                }else{
                    tree = leftrightRotaion(tree);
                }
            }
        }else{

        }
        /**
         * 当插入节点之后，因为经过了当前tree节点，也就是在tree的子树中插入了新的节点，所以需要更新tree的高度
         * 取高度的时候，选择两个子树中高度大的那个，然后再+1，就是当前节点的高度
         */
        tree.setHeight(max(height(tree.getLeft()),height(tree.getRight()))+1);
        return tree;
    }



    /**
     * 查找某个节点的高度，因为节点的高度是存放在节点的height字段中，所以直接返回
     * 对于tree是null的情况，我们认为当前节点是个虚拟节点，高度为0
     * @param tree
     * @return
     */
    private int height(AVLNode tree){
        if(tree == null){
            return 0;
        }
        return tree.getHeight();
    }

    private int max(int a, int b){
        return a>=b?a:b;
    }
}
