package com.test.binarySearch;

import java.math.BigDecimal;
import java.util.Formatter;

//通过二分法求解某个数A的平方根
//基本思路是如果是0-1之间的某个数，那么开始数位0，结束数位1，那么通过二分法首先找到中间数据B，然后对中间数据做平方运算得到结果C，
//然后进行将平方运算的结果C和数A做减法运算得到数据D，并且数据D的绝对值小于我们设定的误差E，那么B就是我们要寻找的平方根。
//如果D小于0，那么我们要寻找的平方根是大于D的，在右半边区间继续进行二分查找，否则在左边区间进行二分查找，直到分区为0
public class BinarySearchForSquare {

    public double getSquare(double num, int precious) {
        //定义数据的上下边界
        BigDecimal low;
        BigDecimal high;
        BigDecimal mid = BigDecimal.ZERO;
        //计算平方根，1是一个分界线，小于1的数据，下限是0，上限是1,这部分数据比较特殊，需要特殊处理，因为这部分的数据平方根是比自己大的数据，必须要放大处理
        if (num < 1) {
            low = BigDecimal.ZERO;
            high = BigDecimal.ONE;
        } else {
            //大于1的数据，下限是1，上限是当前数据
            low = BigDecimal.ONE;
            high = BigDecimal.valueOf(num);
        }
        //计算精度，因为很多数据的平方根跟数据是除不尽的，所以我们在后面的时候只要保证平方根做平方计算以后，跟当前数据的差值在精度以内就算是符合要求的数据
        BigDecimal reduce = BigDecimal.valueOf(1);
        for(int i = 0; i< precious; i++){
            reduce = reduce.divide(BigDecimal.TEN);
        }
        while (low.compareTo(high) < 1) {
            //计算上下限之间的中间值
            mid = low.add(high.subtract(low).divide(BigDecimal.valueOf(2)).setScale(precious+1,BigDecimal.ROUND_HALF_UP));
            //使用当前数据除以中间值
            BigDecimal tmp = BigDecimal.valueOf(num).divide(mid,precious+1,BigDecimal.ROUND_HALF_UP);
            //如果除法的结果和中间值是相等的，那么当前中间值正好就是平方根
            if (mid.compareTo(tmp)==0) {
                return mid.setScale(precious,BigDecimal.ROUND_HALF_UP).doubleValue();
                //如果除法的结果和中间值的差额在精度以内，那么我们也认位当前中间值正好就是平方根
            }else if(mid.subtract(tmp).abs().compareTo(reduce)<0){
                return mid.setScale(precious,BigDecimal.ROUND_HALF_UP).doubleValue();
            }
            //如果中间值小于除法结果，那么说明平方根在右区间，将下限设置为当前中间值
            else if (mid.compareTo(tmp)<0) {
                low = mid;
                //如果中间值大于除法结果，那么说明平方根在左区间，那么将上限设置位当前中间值
            } else {
                high = mid;
            }
        }
        return mid.setScale(precious,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
