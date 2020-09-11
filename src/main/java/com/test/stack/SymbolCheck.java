package com.test.stack;

import java.util.Arrays;
import java.util.List;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/11 检查例如[({ })]这种符号的检查
 * 从左到右遍历字符串，如果是向左的括号就入栈，如果遇到向右的括号就弹出栈顶的元素，如果栈顶元素为空或者与向右的括号不匹配，那么直接结束认为当前字符串整体不匹配
 * 直到遍历完最后一个元素之后，检查栈是否为空，如果为空那么整体匹配，如果不为空，那么整体不匹配
 **/
public class SymbolCheck {

    private MyStack stack;

    private List<String> symbols = Arrays.asList("}", ")", "]");

    public SymbolCheck(int n) {
        stack = new MyStack(n);
    }

    public boolean check(String pattern) {
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String str = String.valueOf(c);
            if (symbols.contains(str)) {
                String match = stack.pop();
                if (str.equals("}") && !"{".equals(match)) {
                    return false;
                }
            } else {
                stack.push(str);
            }
        }
        String str = stack.pop();
        if (str != null) {
            return false;
        }
        return true;
    }
}
