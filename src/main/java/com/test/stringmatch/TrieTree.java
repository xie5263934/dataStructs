package com.test.stringmatch;

/**
 * trie树适合字符串集合中的查找操作，并且是前缀匹配的查找，搜索引擎的关键字提示，是其经典的应用场景，如果需要对字符串进行精确的匹配，那么hash表和红黑树等更适合，因为trie树在存储子节点
 * 的指针的时候，需要考察字符编码集合的大小，如果在字符集合比较大或者前缀重合比较少的情况下，就会比较浪费空间，属于是一种空间换时间的操作
 * 这里我们假设字符集只有26个小写字母，那么对于每个节点，我们使用一个char来存储对应的值，而使用一个26大小的数组来存储子节点的指针，如果子节点不存在，那么数组对应的值就是null，
 * 我们对于每个字符，用它的ascii码减去'a'的ascii码，然后作为数组的下标。
 *
 * @Auth:jinrun.xie
 * @Date:2021/2/19
 **/
public class TrieTree {
    /**
     * trie树的根节点，不存储任何的内容，只是作为trie的起点，相当于一个哨兵的作用
     */
    private TrieNode root = new TrieNode('/');

    public void insert(char[] main) {
        /**
         * 以根节点作为插入的起点
         */
        TrieNode p = root;
        /**
         * 遍历字符串中的每个字符，依次插入到trie树中
         */
        for (int i = 0; i < main.length; i++) {
            /**
             * 通过当前字符的ascii码减去'a'的ascii码作为数组的下标
             */
            int index = main[i] - 'a';
            /**
             * 查看当前要插入的字符在子节点的数组中是否存在，如果不存在就创建一个子节点，并且将子节点的指针复制给当前节点的子节点数组中对应的值
             */
            if (p.children[index] == null) {
                TrieNode node = new TrieNode(main[i]);
                p.children[index] = node;
            }
            /**
             * 将指针移动到子节点中
             */
            p = p.children[index];
        }
        /**
         * 最后将子节点设置成红色节点，表示是一个字符串的结束字符，是一个插入的完整字符串
         */
        p.isEndingChar = true;
    }

    public boolean search(char[] pattern) {
        /**
         * 以根节点为起点开始查找
         */
        TrieNode p = root;
        /**
         * 依次对模式串中的每个字符进行查找操作
         */
        for (int i = 0; i < pattern.length; i++) {
            /**
             * 通过当前字符的ascii码减去'a'的ascii码作为数组的下标
             */
            int index = pattern[i] - 'a';
            /**
             * 如果当前要查找的字符对应的节点不存在，那么表示当前模式串就不存在，查找匹配失败
             */
            if (p.children[index] == null) {
                return false;
            }
            /**
             * 将指针移动到子节点中
             */
            p = p.children[index];
        }
        /**
         * 最后返回查找到的最后一个字符的结束标识，用来标识当前匹配的前缀是否是一个已经预处理的字符串，还是只是一个前缀
         */
        return p.isEndingChar;
    }

    static class TrieNode {
        private char data;
        private TrieNode[] children;
        private boolean isEndingChar;

        public TrieNode(char data) {
            this.data = data;
            children = new TrieNode[26];
        }
    }
}
