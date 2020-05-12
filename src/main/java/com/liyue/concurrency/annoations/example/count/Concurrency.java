package com.liyue.concurrency.annoations.example.count;

import com.liyue.concurrency.annoations.NotThreadSafe;
import com.liyue.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@ThreadSafe(name =  "线程安全")
public class Concurrency {
    private static int threadTotal = 2000;//线程数
    private static int clientTotal =5000;//客户数
    private static AtomicInteger count = new AtomicInteger(0);//原子类型

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i= 0;i <clientTotal;i++){
            pool.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        pool.shutdown();
        log.info("count:{}",count.get());
    }

    private static void add() {
        count.incrementAndGet();//原理：当前的值和底层的值比较并交换
    }

}
