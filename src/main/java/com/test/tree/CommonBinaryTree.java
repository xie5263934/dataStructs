package com.test.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 普通二分查找，处理值存在重复的情况
 * 在这种情况下，如果要插入的值等于节点中的某个值了，这种时候我们就假定要插入的值大于节点的值，将其插入到当前节点的右子树中
 * 所以在查找和删除的过程中都要处理重复的情况
 */
public class CommonBinaryTree {
    /**
     * 哨兵节点，也是根节点
     */
    private Node tree;

    public void add(Node node) {
        if (tree == null) {
            tree = node;
            return;
        }
        Node p = tree;
        Node pp = null;
        while (p != null) {
            pp = p;
            if (node.getData() >= p.getData()) {
                p = p.getRight();
            } else {
                p = p.getLeft();
            }
        }
        if (node.getData() >= pp.getData()) {
            pp.setRight(node);
        } else {
            pp.setLeft(node);
        }
    }

    public List<Node> search(int value) {
        List<Node> nodeList = new ArrayList<>();
        if (tree != null) {
            Node p = tree;
            while (p != null) {
                if (p.getData() == value) {
                    nodeList.add(p);
                }
                if (value >= p.getData()) {
                    p = p.getRight();
                } else {
                    p = p.getLeft();
                }
            }
        }
        return nodeList;
    }

    /**
     * 删除方法，因为存在相同的值，我们所以认为在删除的时候，需要递归地多次删除，直到结束
     * @param value 需要删除的值
     * @param node 开始删除时最开始的节点 ，第一次删除遍历的时候，是从根节点开始，所以和第三个参数的值是一样的，但是之后递归删除的时候，可能就不一样了。
     * @param parentNode 开始删除时最开始节点的父节点
     */
    public void delete(int value, Node node, Node parentNode){
        //如果删除要遍历的起始节点为空，那么就返回
        if(node == null){
            return ;
        }
        //用来存储当前要删除的节点，因为当要删除节点有两个孩子的时候，我们就转移为去删除当前要删除节点的右子树中的最左节点，那么需要保存当前节点的位置，递归删除的时候，就以当前节点作为遍历的起始节点
        Node tmp = null;
        //作用同上，只不过保存的是当前要删除节点的父节点
        Node parentTmp = null;
        //遍历查找当前要删除节点
        Node p = node;
        Node pp = parentNode;
        //如果节点不为空，并且节点的值不等于要删除的值，那么就继续在子树中查找
        while(p!=null&&p.getData() != value){
            pp = p;
            if(value>p.getData()){
                p = p.getRight();
            }else{
                p = p.getLeft();
            }
        }
        if(p == null){
            return ;
        }
        //保存当前要删除的节点，因为有可能转移了
        tmp = p;
        parentTmp = pp;
        //如果要删除的节点左右孩子都不为空，那么就去查找当前节点右子树中的最左节点，从而转移去删除那个最左节点
        if(p.getLeft() != null && p.getRight() != null){
            Node minP = p.getRight();
            Node minPP = p;
            while(minP.getLeft() != null){
                minPP = minP;
                minP = minP.getLeft();
            }
            //将最左节点的值设置到当前节点中
            p.setData(minP.getData());
            //将指针指向找到的最左节点和最左节点的父节点
            p = minP;
            pp = minPP;
        }
        //因为代码走到这里，要删除的节点肯定至多只有一个孩子，所以处理的时候按照至多一个孩子的逻辑进行处理
        //判断是否是删除根节点，特殊处理
        if(p == tree){
            if(p.getRight() != null) {
                tree = p.getRight();
            }else if(p.getLeft() != null){
                tree = p.getLeft();
            }else{
                tree = null;
            }
            return;
        }
        //如果当前要删除节点是父节点的左孩子，那么就将当前节点的孩子设置成父节点的左孩子
        if(p == pp.getLeft()){
            if(p.getLeft() != null){
                pp.setLeft(p.getLeft());
            }else if(p.getRight() != null){
                pp.setLeft(p.getRight());
            }else{
                pp.setLeft(null);
            }
        }else{
            //如果当前要删除节点是父节点的右孩子，那么就将当前节点的孩子设置成父节点的右孩子
            if(p.getLeft() != null){
                pp.setRight(p.getLeft());
            }else if(p.getRight() != null){
                pp.setRight(p.getRight());
            }else{
                pp.setRight(null);
            }
        }
        //判断当前要删除节点tmp和父节点tmpParent的关系，从而直到应该继续往哪边递归删除，直到将所有等于要删除的值的节点全部删除完毕，
        //并且这里使用parent.getLeft()或者parnet.getRight()的原因是因为，节点虽然从树上摘除了，但是tmp依然指向的这个节点，会导致
        //tmp永远不为空，递归永远不结束，所以通过parent来获取子孩子，这样被摘除的节点肯定对应位置就是空，递归就能结束
        if(tmp==parentTmp.getLeft()) {
            delete(value, parentTmp.getLeft(), parentTmp);
        }else {
            delete(value, parentTmp.getRight(), parentTmp);
        }
    }


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

    public void setTree(Node tree) {
        this.tree = tree;
    }
}
