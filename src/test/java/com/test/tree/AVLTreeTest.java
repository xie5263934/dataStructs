package com.test.tree;

import com.test.tree.avl.AVLTree;
import org.junit.Test;

/**
 * @Auth 45208
 * @Date 1/3/2021
 **/
public class AVLTreeTest {

    @Test
    public void test() {
        AVLTree tree = new AVLTree();
        tree.add(new Integer(3));
        tree.add(new Integer(2));
        tree.add(new Integer(1));
        tree.add(new Integer(4));
        tree.add(new Integer(5));
        tree.add(new Integer(6));
        tree.add(new Integer(7));
        tree.add(new Integer(16));
        tree.add(new Integer(15));
        tree.add(new Integer(14));
        tree.add(new Integer(13));
        tree.add(new Integer(12));
        tree.add(new Integer(11));
        tree.add(new Integer(10));
        tree.add(new Integer(9));
        tree.add(new Integer(8));
        tree.mid(tree.getRoot());
        tree.delete(new Integer(13));
        tree.mid(tree.getRoot());
    }
}
