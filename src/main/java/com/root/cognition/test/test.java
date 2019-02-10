package com.root.cognition.test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.root.cognition.until.ThreadsTools;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;


/**
 * @author 王睿
 * @version 2018/12/27
 */
public class test {

    public static void main(String[] args) {
        ExecutorService singleThreadPool = ThreadsTools.startThreadPool(
                1,10,ThreadsTools.buildThreadFactory("test-pool"));
        for (int i=0;i<10;i++) {
            String number = String.valueOf(i);
            singleThreadPool.execute(() -> study("u->"+number));
        }
//        singleThreadPool.shutdown();
    }

    public static void study(String value){
        try {
            int n=30;
            for (int i =0;i<n;i++){
                sleep(1000);
                System.out.println(value+"size"+i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

    /**
     * 验证Map声明使用的方法
        List value1 =new ArrayList();
        List value2 =new ArrayList();
        Map<String,String> gh=new HashMap<>();
        int n = 10;
        int i = bootstrap;
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
     */
