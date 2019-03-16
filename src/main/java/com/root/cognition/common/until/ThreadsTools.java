/**
 * Copyright (c) 2005-2012 springside.org.cn
 */
package com.root.cognition.common.until;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 线程相关工具类.
 *
 * @author calvin
 * @version 2013-01-15
 */
public class ThreadsTools {


    public static ThreadFactory buildThreadFactory(String FactoryName) {
        ThreadFactory newNameThreadFactory;
        if (!FactoryName.isEmpty() && "".equals(FactoryName)) {
            newNameThreadFactory = new ThreadFactoryBuilder().setNameFormat(FactoryName).build();
        } else {
            newNameThreadFactory = new ThreadFactoryBuilder().setNameFormat(IdGenerate.uuid()).build();
        }
        return newNameThreadFactory;
    }


    public static ExecutorService startThreadPool(int corePoolSize, int maximumPoolSize, ThreadFactory namedThreadFactory) {
        ExecutorService singleThreadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024),
                namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        return singleThreadPool;
    }

    /**
     * sleep等待,单位为毫秒,忽略InterruptedException.
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // Ignore.
            return;
        }
    }

    /**
     * sleep等待,忽略InterruptedException.
     */
    public static void sleep(long duration, TimeUnit unit) {
        try {
            Thread.sleep(unit.toMillis(duration));
        } catch (InterruptedException e) {
            // Ignore.
            return;
        }
    }
}
