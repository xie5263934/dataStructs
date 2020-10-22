package com.test.random;


/**
 * @Auth:jinrun.xie
 * @Date:2020/9/28
 **/
public class IntegerTest implements Comparable<IntegerTest> {
    private String value;
    private Integer cmp;

    @Override
    public int compareTo(IntegerTest o) {
        return this.getCmp() - o.getCmp();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getCmp() {
        return cmp;
    }

    public void setCmp(Integer cmp) {
        this.cmp = cmp;
    }
}
