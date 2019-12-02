package com.mateJay.stream;

import com.mateJay.stream.pojo.Apple;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**  map() 可以返回其他的数据类型，filter（） 过滤，过滤掉不符合的数据，返回的还是原先的类型
 * @Author: mate_J
 * @Date: 2019/4/28 22:48
 * @Version 1.0
 */
public class StreamTest {

    private static List<Apple> appleList = new ArrayList<>();



    public static void init(){
        Apple apple1 =  new Apple(1,"苹果1",new BigDecimal("3.25"),10);
        Apple apple12 = new Apple(1,"苹果2",new BigDecimal("1.35"),20);
        Apple apple2 =  new Apple(2,"香蕉",new BigDecimal("2.89"),30);
        Apple apple3 =  new Apple(3,"荔枝",new BigDecimal("9.99"),40);

        appleList.add(apple1);
        appleList.add(apple12);
        appleList.add(apple2);
        appleList.add(apple3);
    }

    public static void toList(){
        List<String> nameList = appleList.stream().map(e -> e.getName()).collect(Collectors.toList());
        System.out.println("nameList:" + nameList);
    }

    public static void groupBy(){
        Map<Integer, List<Apple>> listMap = appleList.stream().collect(Collectors.groupingBy(Apple::getId));
        System.out.println("groupBy:" + listMap);
    }

    /**
     * List -> Map
     * 需要注意的是：
     * toMap 如果集合对象有重复的key，会报错Duplicate key ....
     * apple1,apple12的id都为1。
     * 可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
     */
    public static void listToMap(){
        Map<Integer, Apple> appleMap = appleList.stream().collect(Collectors.toMap(e->e.getId(), a -> a,(k1,k2) -> k1));
        System.out.println(appleMap);
        for (Map.Entry<Integer,Apple> entry : appleMap.entrySet()){
            System.out.println(entry.getKey() +""+ entry.getValue());
        }
    }

    /**
     * 函数式接口  Predicate  返回的是Boolean类型
     *
     */
    public static void filter(){
        List<Apple> appleList = new ArrayList<>();
        appleList = StreamTest.appleList.stream().filter(apple -> apple.getName().equals("香333333蕉")).collect(Collectors.toList());
        System.out.println(appleList.size());
    }

    /**
     * Function Function<? super T, ? extends R> 表示接收一个泛型T，返回一个泛型R
     *  Apple::getMoney  和  a -> a.getMoney()一样 ::是用来获取方法使用的
     */
    public static void add(){
        BigDecimal reduce = appleList.stream().map(Apple::getMoney).reduce(BigDecimal.ZERO,BigDecimal::add);
        System.out.println("totalPrice:" + reduce);
    }

    public static void sum() {
        long sum = appleList.stream().mapToLong(Apple::getNum).sum();
        System.out.println("sum:" + sum);
    }

    public static void max(){
        //Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象
        //Optional 是个容器：它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测
        //Optional 类的引入很好的解决空指针异常。
        Optional<Integer> collect = appleList.stream().map(Apple::getId).collect(Collectors.maxBy((e1, e2) -> e1 > e2 ? 1 : -1));
        Integer value = collect.get();
        System.out.println(value);
    }

    /**
     * 第一段输出代码里，我们先看map操作，map可以改变返回的Stream的泛型，str.split("")，根据空字符串分隔，返回的类型是一个数组，返回的流也是Stream<String[]>，而不是Stream<String>；
     * 在第二段代码中，数组的流，经过map操作，返回Stream<String[]>后，再经过flatMap，把数组通过Arrays.stream变成一个新的流，再返回到原来的流里；这样，两个流就合并成一个流
     */
    public static void flatmap(){
        String[] strs = {"a","bb","ccc"};
        Arrays.stream(strs).map(str -> str.split("")).forEach(e -> System.out.println(e));
        Arrays.stream(strs).map(str -> str.split("")).flatMap(str -> Arrays.stream(str)).forEach(e -> System.out.println(e));
        List<String> stringList = Arrays.stream(strs).collect(Collectors.toList());
        System.out.println("StringList:" + stringList);
    }

    public static void StreamToString() {
        String s1 = appleList.stream().map(Apple::getName).reduce("start:", (a, b) -> a + b + ",");
        System.out.println(s1);
        String s2 = appleList.stream().map(Apple::getName).reduce("start:", (a, b) -> a + "," + b);
        System.out.println(s2);
        Optional s3 = appleList.stream().map(Apple::getName).reduce((a, b) -> a + b + ",");
        System.out.println(s3.orElse(""));
        Optional s4 = appleList.stream().map(Apple::getName).reduce((a, b) -> a + "," + b);
        System.out.println(s4.orElse(""));
    }
    public static void main(String[] args) {
//        init();
//        groupBy();
//        listToMap();
//        filter();
//        add();
//        max();
//        toList();
//        flatmap();
//        StreamToString();
        System.out.println(System.currentTimeMillis());

    }
}
