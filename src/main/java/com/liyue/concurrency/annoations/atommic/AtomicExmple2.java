package com.liyue.concurrency.annoations.atommic;

import com.liyue.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicExmple2 {
    private  volatile int age;// 需要validate 修饰 不能用static修饰

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public AtomicExmple2() {

    }

    //
    private static AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExmple2.class,"age");

    public static void main(String[] args) {
        AtomicExmple2 atomicExmple2 = new AtomicExmple2();
        atomicExmple2.setAge(0);
        updater.compareAndSet(atomicExmple2,0,2);
        log.info("更新的内容：{}",updater.get(atomicExmple2));
    }

    private static AtomicReferenceFieldUpdater updater1 = AtomicReferenceFieldUpdater.newUpdater(AtomicExmple2.class,AtomicExmple2.class,"");



}
