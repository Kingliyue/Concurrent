package com.liyue.concurrency.annoations.atommic;

import com.liyue.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@ThreadSafe
public class AtomicExample3 {
    private static AtomicReference<Integer> atomicReference = new AtomicReference<>();
    // 可以用于代码执行一次的需求上
    public static void main(String[] args) {
        atomicReference.compareAndSet(0,2);
        atomicReference.compareAndSet(2,4);

        log.info("更新的数据：{}",atomicReference.get());
    }


}
