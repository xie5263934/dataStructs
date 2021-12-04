package com.test.tree;

import org.junit.Test;

import java.util.List;

public class BinarySearchTreeTest {

    @Test
    public void test() {
        BinarySearchTree tree = new BinarySearchTree();
        Node node = new Node(16);
        Node node1 = new Node(10);
        Node node2 = new Node(9);
        Node node3 = new Node(13);
        Node node4 = new Node(11);
        Node node5 = new Node(14);
        tree.add(node);
        tree.add(node1);
        tree.add(node2);
        tree.add(node3);
        tree.add(node4);
        tree.add(node5);
        //tree.middle(tree.getTree());
       /* int value  = 11;
        Node node6 = tree.search(value);
        print(node6,value);
        value=15;
        Node node7 = tree.search(value);
        print(node7,value);*/
       /* int value = 16;
        System.out.println("准备删除" + value);
        tree.delete(value);
        tree.middle(tree.getTree());*/
//        tree.preOrder();
//        tree.midOrder();
        tree.postOrder();
    }

    private void print(Node node, int value) {
        if (node != null) {
            System.out.println(node.getData());
        } else {
            System.out.println("未找到" + value);
        }
    }

    @Test
    public void test1() {
        CommonBinaryTree tree = new CommonBinaryTree();
        Node node = new Node(16);
        Node node1 = new Node(10);
        Node node2 = new Node(9);
        Node node3 = new Node(13);
        Node node4 = new Node(13);
        Node node5 = new Node(11);
        Node node6 = new Node(14);
        Node node7 = new Node(14);
        tree.add(node);
        tree.add(node1);
        tree.add(node2);
        tree.add(node3);
        tree.add(node4);
        tree.add(node5);
        tree.add(node6);
        tree.add(node7);
        tree.middle(tree.getTree());
        /*List<Node> nodeList = tree.search(13);
        print(nodeList);*/
        System.out.println("准备删除13");
        tree.delete(13,tree.getTree(), tree.getTree());
        tree.middle(tree.getTree());
        System.out.println("准备删除16");
        tree.delete(16,tree.getTree(), tree.getTree());
        tree.middle(tree.getTree());
        System.out.println("准备删除14");
        tree.delete(14,tree.getTree(), tree.getTree());
        tree.middle(tree.getTree());
        System.out.println("准备删除9");
        tree.delete(9,tree.getTree(), tree.getTree());
        tree.middle(tree.getTree());
    }

    private void print(List<Node> nodeList){
        if(nodeList != null && nodeList.size() > 0){
            nodeList.stream().forEach(System.out::println);
        }
    }
}
