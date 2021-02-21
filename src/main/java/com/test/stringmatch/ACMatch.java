package com.test.stringmatch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * AC自动机
 * 首先AC自动机基于trie树，在trie树之上增加了一个fail指针，这个fail指针的含义是，从root节点到当前节点的字符串，是否存在一个最大的后缀字符串，并且这个后缀字符串正好是trie树中某个字符串的一个前缀，
 * 那么fail指针就指向那个前缀字符串的最后一个字符，这样当我们在主串进行匹配的过程中，如果某个字符在当前这个节点匹配失败了，那么通过fail指针我们就可以移动到另外一个字符串的前缀中，并且这个前缀因为我们已经
 * 匹配过了，所以不需要再进行匹配，我们要左的事情就是继续匹配下一个trie树中的节点，这样就提高了匹配的效率，不需要我们匹配失败以后，再从root节点开始去匹配了。
 * trie树的节点还要增加另外一个属性，exist数组，如果当前这个节点正好是一个字符串的结束字符，那么我们需要存储当前这个字符串的长度，标识这个节点正好是某个字符串的结束节点，并且这个字符串是多长。
 * 并且在构建fail指针的过程中，我们还需要检查fail指针指向的那个节点是否也是一个结束节点，如果是，那么我们在exist数组中还要存储fail指针指向的那个节点标识的字符串的长度，这样在与主串的匹配过程中
 * 我们就能在当前匹配过程中将匹配到的两个字符串都展示出来，而不用在fail指针跳转的过程中还要去检查fail指针指向的那个节点是否是一个字符串的结束节点，因为这样逻辑会显得有点混乱和啰嗦。
 *
 * @Auth 45208
 * @Date 2/21/2021
 **/
public class ACMatch {

    private ACNode root = new ACNode('/');

    /**
     * 构建trie树，将模式串插入到trie树中
     *
     * @param pattern
     */
    public void insert(char[] pattern) {
        ACNode p = root;
        int i = 0;
        for (; i < pattern.length; i++) {
            int index = pattern[i] - 'a';
            if (p.children[index] == null) {
                ACNode acNode = new ACNode(pattern[i]);
                p.children[index] = acNode;
            }
            p = p.children[index];
        }
        p.exist[0] = i;
    }

    public void buildFail() {
        ACNode p = root;
        Queue<ACNode> queue = new LinkedList<>();
        queue.add(p);
        while (!queue.isEmpty()) {
            p = queue.remove();
            if (p == root) {
                p.fail = null;
            }
            for (ACNode pc : p.children) {
                if (pc != null) {
                    if (p == root) {
                        pc.fail = p;
                    } else {
                        ACNode q = p.fail;
                        while (q != null) {
                            int index = pc.data - 'a';
                            ACNode qc = q.children[index];
                            if (qc != null) {
                                pc.fail = qc;
                                pc.exist[1] = qc.exist[0];
                                break;
                            }
                            q = q.fail;
                        }
                    }
                    queue.add(pc);
                }
            }
        }
    }

    public void query(char[] main) {
        ACNode p = root;
        for (int i = 0; i < main.length; i++) {
            int index = main[i] - 'a';
            ACNode node = p.children[index];
            if (node != null && node.data == main[i]) {
                for (int s : node.exist) {
                    if (s != -1) {
                        int start = i - s + 1;
                        StringBuilder builder = new StringBuilder();
                        while (start <= i) {
                            builder.append(main[start]);
                            start++;
                        }
                        System.out.println("在主串的第" + start + "位置匹配到了一个模式串" + builder.toString());
                    }
                }
                p = node;
            } else if (p.fail != null) {
                p = p.fail;
            }
        }
    }


    static class ACNode {
        /**
         * 数据区域，存储一个字符
         */
        char data;
        /**
         * 孩子节点的指针，因为我们这里的例子假定只处理a-z这26这个小写字母，所以孩子指针最多只会有26个。
         */
        ACNode[] children = new ACNode[26];
        /**
         * fail指针，表示，当主串在当前节点匹配失败以后，通过fail指针可以转向另外一个分支上继续进行匹配，并且因为fail指针表示的是当前字符串的最大后缀与另外一个字符串的最大前缀想关联的最后一个节点，
         * 所以可以直接从那个节点的后续开始继续匹配，这样不用从root节点再重新开始匹配，从而提高了匹配的效率。
         */
        ACNode fail;
        /**
         * 初始值为-1，如果当前节点正好是一个字符串的结束字符，那么数组的第一个元素，就存储了当前这个字符串的长度，如果当前节点的fail指针指向的节点，正好也是一个字符串的结束节点，那么第二个元素
         * 就存储那个节点对应的字符串的长度。
         */
        int[] exist = {-1, -1};

        public ACNode(char data) {
            this.data = data;
        }
    }
}
