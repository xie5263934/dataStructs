package com.test.stack;

import java.util.Arrays;
import java.util.List;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/11 表达式计算，例如3+5*8-6
 * 使用两个栈，一个栈用来存储数字，一个栈用来存储符号
 * 从左到右遍历表达式，如果是数字就入栈，如果是符号就需要与当前符号栈栈顶的符号进行比较
 * 如果要入栈的符号操作优先级大于栈顶符号，就继续入栈
 * 如果要入栈的符号操作优先级小于等于栈顶符号，那么就从数字栈取出两个数字，取出当前符号栈栈顶的符号进行运算，然后将结果压入数字栈，
 * 然后当前要入栈的符号继续与符号栈栈顶的符号进行比较重复当前的操作，直到数字栈为空，或者符号栈为空，或者比符号栈栈顶操作符运算级别低，那么将当前符号入栈
 * 遍历完表达式之后，每次从符号栈取出栈顶运算符，然后从操作数栈取出两个数进行运算，然后将运算结果存入数字栈，继续刚才的操作直到符号栈为空取出数字栈的最后一个数字就是结果
 **/
public class ReguleCaculate {
    /**
     * 数字栈
     */
    public MyStack number;
    /**
     * 符号栈
     */
    public MyStack symbol;

    public List<String> hignSymbols = Arrays.asList("*", "/");

    public List<String> lowSymbols = Arrays.asList("+", "-");

    public ReguleCaculate(int n) {
        this.number = new MyStack(n);
        this.symbol = new MyStack(n);
    }

    public String eval(String pattern) {
        for (int i = 0; i < pattern.length(); i++) {
            /**
             * level用来标识表达式中的字符是什么，0标识数字，1标识加减，2标识乘除,并且乘除优先级比加减高
             */
            int level = 0;
            char c = pattern.charAt(i);
            if (lowSymbols.contains(String.valueOf(c))) {
                level = 1;
            } else if (hignSymbols.contains(String.valueOf(c))) {
                level = 2;
            } else {
                number.push(String.valueOf(c));
            }
            if (1 <= level && level <= 2) {
                int level1 = 1;
                do {
                    String sign = symbol.pop();
                    if (sign == null) {
                        break;
                    }
                    if (hignSymbols.contains(sign)) {
                        level1 = 2;
                    }
                    if (level > level1) {
                        symbol.push(sign);
                        break;
                    } else {
                        String num1 = number.pop();
                        String num2 = number.pop();
                        String result = cal(num1, num2, sign);
                        number.push(result);
                    }
                } while (level <= level1);
                symbol.push(String.valueOf(c));
            }
        }
        String sign = symbol.pop();
        while (sign != null) {
            String num1 = number.pop();
            String num2 = number.pop();
            String result = cal(num1, num2, sign);
            number.push(result);
            sign = symbol.pop();
        }
        return number.pop();
    }


    private String cal(String num1, String num2, String symbol) {
        Integer n2 = Integer.valueOf(num2);
        Integer n1 = Integer.valueOf(num1);
        Integer result = null;
        if (symbol.equals("+")) {
            result = n2 + n1;
        }
        if (symbol.equals("-")) {
            result = n2 - n1;
        }
        if (symbol.equals("*")) {
            result = n2 * n1;
        }
        if (symbol.equals("/")) {
            result = n2 / n1;
        }
        return String.valueOf(result);
    }
}
