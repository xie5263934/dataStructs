package com.test.linklist;

/**
 * 链表排序，对于一个链表排序，我们可以使用归并排序法的思想，自定向下地进行排序，首先对链表不停地进行分割，直到链表只剩下一个节点或者为空的时候，然后对这些有序的链表进行合并，最后得到的就是一个有序的链表
 *
 * @Auth 45208
 * @Date 3/14/2021
 **/
public class LinkListSort {

    /**
     * 排序方法，方法的返回值是排好序之后的头节点的指针
     * @param head
     * @return
     */
    public ListNode sort(ListNode head) {
        /**
         *         如果链表为空，或者链表只有一个节点就直接返回
         */
        if (head == null || head.next == null) {
            return null;
        }
        return sort(head, null);
    }

    /**
     * 内部排序方法，使用归并排序的方法来对链表进行排序
     * @param head
     * @param tail
     * @return
     */
    private ListNode sort(ListNode head, ListNode tail) {
        /**
         * 如果当前链表没有节点那么直接返回
         */
        if (head == null) {
            return head;
        }
        /**
         * 这里是第一个关键点，head.next==tail，表示当前链表只有一个节点，因为tail是下一个链表的开始头节点了，不能算着当前链表的节点
         * 所以这里我们需要把head.next设置成null，表示当前链表结束了，这样就将当前链表从原始链表上截取了下来，这样在后续的操作种，特别是合并的过程种才不会有问题。
         */
        if (head.next == tail) {
            head.next = null;
            return head;

        }
        /**
         * 通过快慢指针来查找中间节点，慢指针每次走一步，快指针如果没有到达尾节店，那么每次走两部，这样当快指针到达尾节店的时候，慢指针就走到了链表中间。
         * 我们通过中间节点，将链表分成两部分，分别处理
         */
        ListNode slow = head;
        ListNode quick = head;
        while (quick.next != tail) {
            slow = slow.next;
            quick = quick.next;
            /**
             * 这里是第二个关键点，需要判断快指针是否到达尾节点，如果没有，快指针就再走一部，这样快指针就一次走两步了。
             */
            if (quick.next != tail) {
                quick = quick.next;
            }
        }
        /**
         * 从中间节点将链表分成两部分，第一部分是从head到mid，但是不包含mid，所以对应了上面的head.next=tail就返回的逻辑。
         * 第二部分是从mid到tail，包含mid，并且mid是当前链表的开始节点
         */
        ListNode mid = slow;
        ListNode l1 = sort(head, mid);
        ListNode l2 = sort(mid, tail);
        /**
         * 最后对链表进行合并操作，返回合并之后的头节点
         */
        ListNode sorted = merge(l1, l2);
        return sorted;
    }

    /**
     * 合并操作
     * @param l1
     * @param l2
     * @return
     */
    private ListNode merge(ListNode l1, ListNode l2) {
        /**
         * 哨兵节点，这样的好处是不用特殊处理头节点的问题
         */
        ListNode dummyNode = new ListNode(0);
        /**
         * tmp首先指向哨兵节点，这样tmp节点就不用特殊处理是否是头节点了
         */
        ListNode tmp = dummyNode;
        /**
         * 两个临时变量分别指向两个链表的头节点
         */
        ListNode tmp1 = l1;
        ListNode tmp2 = l2;
        /**
         * 只要其中的任何一个链表没有到达链表尾部，就继续合并
         */
        while (tmp1 != null && tmp2 != null) {
            /**
             * 谁的值小就取谁，然后将对应的节点加入到新链表中
             */
            if (tmp1.value <= tmp2.value) {
                tmp.next = tmp1;
                tmp1 = tmp1.next;
            } else {
                tmp.next = tmp2;
                tmp2 = tmp2.next;
            }
            tmp = tmp.next;
        }
        /**
         * 最后判断是某个链表还有节点没有处理完，直接加入到新链表的尾部
         */
        if (tmp1 != null) {
            tmp.next = tmp1;
        }
        if (tmp2 != null) {
            tmp.next = tmp2;
        }
        /**
         * 最后返回哨兵的下一个节点，也就是新链表的头节点
         */
        return dummyNode.next;
    }

    public static class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }
}
