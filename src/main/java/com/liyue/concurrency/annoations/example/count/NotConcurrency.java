package com.liyue.concurrency.annoations.example.count;

import com.liyue.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


@Slf4j
@NotThreadSafe
public class NotConcurrency {
    private static int threadTotal = 2000;//线程数
    private static int clientTotal =5000;//客户数
    private static int count = 0;

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
        log.info("count:{}",count);
    }

    private static void add() {
        count++;
    }
}
