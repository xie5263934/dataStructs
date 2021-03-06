package com.test.sort;

/**
 * @Auth 45208
 * @Date 9/17/2020
 * 计数排序
 * 1:首先要排序的数据必须是正整数，并且数据的范围不能太大，如果我们遇到的是有负数或者是有小数，那么我们就需要通过加法或者乘法，将原始数据放大成正整数然后进行排序，最后再将数据还原
 * 2:我们记原始无须数组为a，那么我们要找到a里面最大的一个数据max，然后创建一个小标为0-max的max+1个元素的新数组c，然后将新数组所有的元素初始化为0，其中a的元素对应的就是c数据的下标
 * 3;我们遍历a数组，a数组中的元素对应的就是c数组的下标，如果那个元素出现一次，我们的c数组对应下标的个数就加1
 * 4:我们我们对c数组里面的元素做累加，a[i]=a[i]+a[i-1]
 * 4:我们创建一个跟a数组一样大的临时数组r，然后从尾到头遍历a数组，a数组中的元素就是对应的c数组的下标，然后去除c数组对应下标中的值，减去1，得到的就是a数组元素在r数组中的下标位置，将a数组元素
 * 放入对应r数组的位置
 * 5:将r数组元素拷贝回a数组，排序完成
 **/
public class CountSort {

    public void sort(int[] arr, int n) {
        if (n <= 1) {
            return;
        }
        //找出原来数组中最大的数据
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        //创建从0到max下标一共max+1个元素的数组，这也是为什么原来数组的范围不能太大，否则这个数组就太大了，内存空间占用就太大了
        int[] c = new int[max + 1];
        //将计数数组的所有元素初始化为0
        for (int i = 0; i < max + 1; i++) {
            c[i] = 0;
        }
       //遍历原始数组，原始数组的数据就是计数数组的下标，所以这里也就说明了，为什么原始要排序的数据，必须是正整数，因为它要作为计数数组的下标，出现一次，计数数组的计数就加1，这也是这个算法的第一个重要节点
        for (int i = 0; i < n; i++) {
            c[arr[i]]++;
        }
        //然后对这个数组从左到右进行累加，其实就是在计算原始数组的对应的元素到自己为止，之前一共包含了多少个数据，其实也就是在计算位置
        for (int i = 1; i < max + 1; i++) {
            c[i] = c[i] + c[i - 1];
        }
        //创建一个跟原数组大小一样的临时数组，用来存储排序后的数据
        int r[] = new int[n];
        //然后遍历原始数组，以当前数据为下标在计数数组中，取出对应元素，就是原始数组数据对应的位置，但是数组是以0开始，所以这个位置是需要减去1
        //才是在临时排序数组中真正对应的位置
        for (int i = 0; i < n; i++) {
            int index = --c[arr[i]];
            r[index] = arr[i];
        }
        //将排好序的数据拷贝回原来的数组
        for (int i = 0; i < n; i++) {
            arr[i] = r[i];
        }
    }
}
