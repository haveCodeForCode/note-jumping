package com.root.cognition.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 王睿
 * @version 2018/12/27
 */
public class test {

    public static void main(String[] args) {
        /**
         * 验证Map声明使用的方法
         */
        List value1 =new ArrayList();
        List value2 =new ArrayList();
        Map<String,String> gh=new HashMap<>();
        int n = 10;
        int i = 0;
        while (i<n){
            Map<String,String> wh = new HashMap<>();
            String n1 = n+"";
            String i1 = i+"";
            gh.put(i1,n1);
            wh.put(i1,n1);
            value1.add(gh);
            value2.add(wh);
            n=n-1;
            i++;
        }
        System.out.println(value1);
        System.err.println(value2);
    }
}
