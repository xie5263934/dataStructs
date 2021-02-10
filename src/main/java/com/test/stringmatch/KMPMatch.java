package com.test.stringmatch;

import java.util.Arrays;

/**
 * KMP算法的核心思想就我们在匹配模式串的过程中，模式串可以在主串上进行前后的滑动，就像一个卡尺一样，然后来对齐所有的要匹配的字符，如果当匹配到某个字符不相等的时候，前面已经匹配过的字符是否存在
 * 公共公共的前后缀字串，这样在匹配的时候，我们就可以跳过这些公共的前后缀字串，直接将模式串的游标移动到公共前后缀的后面继续与主串进行比较，这样提高了模式串匹配的效率。而对于公共前后缀的处理我们
 * 可以提前进行预处理提高算法的效率，而对于模式串求公共前后缀字串的过程，我们可以把模式串既当成主串又当成模式串进行匹配，其实过程与KMP算一样的。只不过在比较的过程中，对于主串我们的下标从1开始，
 * 而对于模式串我们从0开始，这样就将主串与模式串错开一位，相当于主串就是原始模式串的后缀，而模式串就是原始模式串的前缀，然后对他们求最大公共前后缀，当求解的过程遇到不匹配的时候，我们前面已经匹配
 * 的可能会有公共前后缀字串，这个时候我们只需要将模式串移动到那个公共前后缀的后一位继续与主串进行比较。
 *
 * @Auth:jinrun.xie
 * @Date:2021/2/10
 **/
public class KMPMatch {

    /**
     *
     * @param main
     * @param pattern
     * @return
     */
    public int indexOf(char[] main, char[] pattern) {
        int n = main.length;
        /**
         * 第一步首先生成next数组，这里next数组的下标是好前缀字符的个数，而对应的值就是好前缀的后缀和模式串的前缀对应的最大公共前后缀的字符个数。
         */
        int[] next = generateNext(pattern);
        int m = pattern.length;
        /**
         * 匹配的时候，从主串和模式串的第一个字符开始匹配
         */
        int i = 0;
        int j = 0;
        while (i < n) {
            /**
             * 如果匹配成功，也就是下标为i的主串对应的字符和下标为j的模式串对应的字符相等，那么两个游标都往后移动一位继续比较
             */
            if (main[i] == pattern[j]) {
                i++;
                j++;
                /**
                 * 如果匹配失败，那么如果j大于0，那么对于0，j-1这个子串是已经匹配成功了的，我们要想办法利用这个匹配好的子串，方法就是查找从i-j到i-1这个主串上的子串
                 * 和模式串上0到j-1这个前缀子串的最大公共子串，这样我们就可以跳过这个公共子串的匹配，直接从最大公共子串的下一个字符开始比较起，所以j=next[j-1]
                 */
            } else {
                if (j > 0) {
                    j = next[j];
                } else {
                    i++;
                }
            }
            /**
             * 如果j的大小等于了m，表示匹配完成了，并且整个模式串都和主串的某个子串完全匹配了。那么返回那个子串在主串上的开始下标。
             */
            if (j == m) {
                return i - j;
            }
        }
        return -1;
    }

    /**
     * 求next数组
     * next因为存的是模式串的前缀对应的模式串的前缀对应的公共前后缀的大小
     * 下标i，i从1到m-1,表示从pattern[0，i]一共i+1个字符的这个前缀子串
     * next[i]对应的值，表示从pattern[0，i]这个一共i+1个字符的前缀子串和对应的字符个数的后缀子串对应的公共前后缀子串的大小
     * 求解思路，其实跟KMP的字符串匹配思路一样，我们把原始的模式串既当成主串，又当成模式串来进行匹配，并且在匹配的过程中，主串的起点是i=1第二个字符开始，
     * 而模式串是从j=0第一个字符开始匹配，这样就将主串和模式串错开一位，相当于就是原始模式串的后缀子串(原始模式串对应的主串)和前缀子串(原始模式串对应的模式串)来进行匹配的过程，
     * 而这整个的过程就是一个KMP匹配的过程，当i和j对应的字符相等的时候，我们就把a[i]=j+1,因为j是从0开始的，当匹配上一个的时候，我们的值最少是1，除非是没有匹配上，那么就是0，
     * 当i和j中对应的主串的字符和模式串的字符不相等的时候，我们不能轻易下结论next[i]=0,因为我们之前i个元素已经匹配成功了，只是当前这个字符匹配不成功而已，所以我们取得之前已经匹配成功的
     * 那个串的最大公共前后子串进行对齐，其实也就是将j移动到那个最大公共前后缀子串的后面一个字符进行匹配，如果这个时候对应的i和j对应的主串字符和模式串字符相等，那么next[i]，这个[0,i]前缀
     * 对应的最大公共子串的大小就是j+1;如果这个时候还是不能匹配，因为j之前的还是已经匹配成功的，那么我们再求j之前的那个前缀对应的最大公共前后缀子串，如此重复，直到j的下标为0，这个时候模式串只有一个
     * 元素，不可能存在子串，更不可能存在最大公共前后缀子串，所以不可能匹配成功了，那么我们只有将i往后移动一位，重复之前的匹配操作。
     *
     * @param pattern
     * @return
     */
    private int[] generateNext(char[] pattern) {
        int m = pattern.length;
        /**
         * 构建一个next数组，并且全部初始化为0
         */
        int[] next = new int[m];
        Arrays.fill(next, 0);
        /**
         * 对于主串，从下标1开始匹配，也就是原模式串的后缀
         */
        int i = 1;
        /**
         * 对于模式串，从下标0开始匹配，也就是远模式串的前缀
         */
        int j = 0;
        /**
         * 这里对于i从1开始匹配，求解p[0,1]这个前缀子串的最大公共前后缀子串
         */
        while (i < m) {
            /**
             * 如果当前i下标对应的主串和j对应的模式串相等，那么next[i]的值就等于j+1,因为j是从0开始的，是下标，而我们这个next数组是存的大小，所以需要加一个
             */
            if (pattern[i] == pattern[j]) {
                next[i++] = ++j;
            } else {
                /**
                 * 如果当前i下标对应的主串字符和j对应的模式串字符不相等，那么判断当前j是否已经是模式串的开头了，也就是下标是0了，如果不是0，那么当前j之前的字符串已经是匹配成功了，
                 * 那么我们需要查找j-1也就是p[0,j-1]对应的最大公共前后缀子串的大小，来移动模式串，将j重新设置到next[j-1],这样我们又重新比较，如果i对应下标和j对应的下标的主串和
                 * 模式串对应字符相等，那么next[i]=j+1,我们就找到了m[0,i]这个子串对应的公共前后缀子串的大小，否则又继续之前的步骤重复查找，直到j等于0了，这个时候模式串已经到开头了，
                 * 那么我们就需要比较模式串开头的这个字符和主串的下一个字符了，也就是i+1这个字符，所以j==0的时候，直接i++。
                 */
                if (j > 0) {
                    j = next[j - 1];
                } else {
                    i++;
                }
            }
        }
        return next;
    }
}