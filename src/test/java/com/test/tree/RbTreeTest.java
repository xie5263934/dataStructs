package com.test.tree;

import com.test.tree.rbTree.RbTree;
import org.junit.Test;


/**
 * @Auth:jinrun.xie
 * @Date:2021/1/27
 **/
public class RbTreeTest {

    @Test
    public void test() {
        RbTree<Integer> tree = new RbTree<>();
        int[] arr = new int[]{675, 336, 717, 234, 491, 708, 821, 178, 321, 482, 519, 749, 941, 440, 784};
        for (int i = 0; i < arr.length; i++) {
            tree.insert(arr[i]);
        }
        tree.mid();
        int i = 675;
        tree.delete(i);
        tree.mid();
    }
}
