package com.test.linklist;

/**
 * 链表排序，对于一个链表排序，我们可以使用归并排序法的思想，自定向下地进行排序，首先对链表不停地进行分割，直到链表只剩下一个节点或者为空的时候，然后对这些有序的链表进行合并，最后得到的就是一个有序的链表
 *
 * @Auth 45208
 * @Date 3/14/2021
 **/
public class LinkListSort1 {

    /**
     * 排序方法，方法的返回值是排好序之后的头节点的指针
     *
     * @param head
     * @return
     */
    public ListNode sort(ListNode head) {
        /**
         *         如果链表为空，或者链表只有一个节点就直接返回
         */
        if (head == null || head.next == null) {
            return head;
        }
        /**
         * 首先求出链表的长度，之后我们要对链表进行分割和合并，分割的最小单位为1，最大为链表的长度，从最小单位进行分割之后，两两合并，之后分割单位设置成之前的2倍，重复之前的操作，直到分割单位大于
        等于链表长度为止
         **/
        int linkListLength = 0;
        /**
         * 从链表头开始往后遍历链表，统计链表长度
         */
        ListNode node = head;
        while (node != null) {
            linkListLength++;
            node = node.next;
        }
        /**
         * 设置一个哨兵，并且哨兵的第一次的后续节点为之前链表的头节点
         */
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        /**
         * 从分割单位为1，分割链表，然后两两合并，之后将分割单位设置为原来的两倍，直到分割单位大于等于链表长度为止。
         */
        for (int subLength = 1; subLength < linkListLength; subLength = subLength << 1) {
            /**
             * 每次处理的时候，都从哨兵开始，因为在分割过程中需要将链表打断，所以在分割并且合并完成之后，需要将链表又重新连接起来，这个时候就需要pre指针，每次都指向哨兵位置
             */
            ListNode pre = dummyHead;
            /**
             * 每次遍历处理的时候，都是从当前处理链表的头节点开始，对整个链表进行处理
             */
            ListNode curr = dummyHead.next;
            while (curr != null) {
                /**
                 * 第一个分割出来的链表的头节点等于当前指针
                 */
                ListNode head1 = curr;
                /**
                 * 遍历链表直到后续节点达到分割单位减去1个或者到达链表尾节点，因为头节点已经占据了一个数量了，所以后续只需要分割单位-1个节点就行了。但是要特殊处理到达尾节点的情况。
                 */
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                /**
                 * 第二个节点的开始节点就是当前第一个链表的最后一个节点的下一个节点，这里curr不会出现空指针的情况是，因为在上面循环中我们已经处理了这种情况，curr最极端的情况是指向了链表
                 * 的尾节点，所以不会出现curr空指针的情况。
                 */
                ListNode head2 = curr.next;
                /**
                 * 这里就是将第一个链表的最后一个节点的后驱指针指向null，将第一个分割出来的链表成为一个真正单独的链表，方便后续的合并操作
                 */
                curr.next = null;
                /**
                 * curr遍历指针指向了第二个要分割的链表的头节点，注意这里第二个头节点有可能是空节点，所以在下面遍历第二个链表的后续节点的时候，我们特殊处理了curr不等于空的情况，除此之外与
                 * 第一的操作是完全类似的。
                 */
                curr = head2;
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }
                /**
                 * 因为我们要断开第二个链表，要将第二个链表的最后一个节点的后驱设置成null，我们必须有一个指针指向后面的节点，否则这个链表后面的节点就丢失了。
                 */
                ListNode next = null;
                /**
                 * 如果curr不等于null，表示第二个链表是有节点存在的，那么next就指向了第二个分割链表的后续节，将第二个链表的最后一个节点的后驱设置成null，表示将第二个链表分割出来，方便后续的
                 * 合并处理
                 */
                if (curr != null) {
                    next = curr.next;
                    curr.next = null;
                }
                /**
                 * 合并第一个和第二个链表，并且返回头节点
                 */
                ListNode merged = merge(head1, head2);
                /**
                 * pre的后驱设置成合并后的两个分割子链表的头节点，这样就将之前从当前链表上截取出去的两个链表又合并回来了，又是一个完整的链表，后续的操作才能继续，否则就七零八落了。
                 */
                pre.next = merged;
                /**
                 * 如果当前pre不是指向了原始链表的最末尾节点，那么pre就需要往后移动，直到当前合并完成之后的链表的末尾，等待下两个子链表合并完成返回的头节点，将其连接起来，这样就又
                 * 将所有的节点连接回原来的链表上了，否则就丢失了。
                 */
                while (pre.next != null) {
                    pre = pre.next;
                }
                /**
                 * 将cur指针指向之前第二个链表的末尾节点后面的那个节点，继续重复分割两个子链表，然后合并的操作，直到curr为空
                 */
                curr = next;
            }
        }
        return dummyHead.next;
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode tmp = dummyHead;
        ListNode tmp1 = l1;
        ListNode tmp2 = l2;
        while (tmp1 != null && tmp2 != null) {
            if (tmp1.value <= tmp2.value) {
                tmp.next = tmp1;
                tmp1=tmp1.next;
            } else {
                tmp.next = tmp2;
                tmp2=tmp2.next;
            }
            tmp = tmp.next;
        }
        if (tmp1 != null) {
            tmp.next = tmp1;
        }
        if (tmp2 != null) {
            tmp.next = tmp2;
        }
        return dummyHead.next;
    }

    public static class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }
}
