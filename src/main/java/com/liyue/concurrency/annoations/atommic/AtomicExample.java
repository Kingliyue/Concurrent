package com.liyue.concurrency.annoations.atommic;


import com.liyue.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@ThreadSafe
public class AtomicExample {
    private static int threadTotal = 2000;//线程数
    private static int clientTotal =5000;//客户数
    //原子性：要么执行完成，要不执行不完成中间不可中断，属于单线程
    private static AtomicInteger atomicInteger =  new AtomicInteger(clientTotal);//原子性

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
        log.info("count:{}",atomicInteger.get());
    }

    private static void add() {
        atomicInteger.incrementAndGet();
    }


}
