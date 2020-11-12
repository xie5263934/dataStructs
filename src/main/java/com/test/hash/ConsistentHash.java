package com.test.hash;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

//这里实现了一个简单的一致性hash，这里假设的使用场景是分布式缓存，我们需要将缓存数据放到对应的缓存服务器上
//首先我们这里使用treemap来模拟一致性hash中的环，treemap有个特性就是能够自动的按照key的自然顺序从小到大的排序节点，
// 这样我们就模拟了一个顺时针从小到大的无数个节点组成的环
//我们在添加服务器的时候，执行numberOfReplicas次，就模拟了我们在环上按照顺时针对同一个服务器增加numberOfReplicas个节点的操作，以此来均衡数据的分布
//接着我们对缓存数据使用hash算法，算出一个hash值x
//最后我们在查找的过程中，首先使用containskey(x)方法，简单的判断缓存数据有没有直接对应一个节点，如果没有，我们使用tailmap（x)，将map中key等于并且大于当前x的
//所有节点返回回来，如果返回回来的map不为空，那么我们取返回回来的map的第一个节点，也就是模拟了我们按照顺时针查找环中最近一个节点的操作。如果返回回来的map为空，那么我们直接返回
//原来map的第一个节点，也就是环中对应的第一个节点
public class ConsistentHash<T> { //泛型T表示一个服务器节点
    //在一致性hash中，每个服务器节点对应的一致性hash的环上的节点个数
    private final int numberOfReplicas;
    //一致性hash算法使用的环
    private final SortedMap<Integer, T> circle = new TreeMap<Integer, T>();

    //构造方法
    public ConsistentHash(int numberOfReplicas, Collection<T> nodes) {
        this.numberOfReplicas = numberOfReplicas;

        for (T node : nodes) {
            add(node);
        }
    }

    //新增服务器节点的方法
    public void add(T node) {
        //这里使用for循环，并且循环numberOfReplicas次的原因是，因为我们对于一个服务器节点，我们希望在环上能够随机的增加numberOfReplicas个节点，使到时候缓存数据
        //能够更加均衡的分布到这些节点上
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.put((node.toString() + i).hashCode(), node);
        }
    }

    //对应新增服务器节点的方法，在移除的时候，我们需要将对应的numberOfReplicas个节点都移除了
    public void remove(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.remove((node.toString() + i).hashCode());
        }
    }

    //key是我们要缓存的数据的主键，我们需要找到我们这个缓存数据对应的服务器节点
    public T get(Object key) {
        //如果服务器节点个数是空，表示不存在任何服务器，就返回空
        if (circle.isEmpty()) {
            return null;
        }
        //求解当前key对应的hash值
        int hash = key.hashCode();
        //如果这个hash值在环上不对应任何节点，那么我们就把从当前这个hash开始，到这个环结尾的所有节点全部查找出来，
        //判断是否为空，如果为空，那么我们直接就取环上的第一个节点，否则我们就取离当前这个hash往后最近的第一个节点，
        //这样我们就模拟了顺时针往后查找对应节点的操作，找到了我们对应的缓存服务器
        if (!circle.containsKey(hash)) {
            SortedMap<Integer, T> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }
}
