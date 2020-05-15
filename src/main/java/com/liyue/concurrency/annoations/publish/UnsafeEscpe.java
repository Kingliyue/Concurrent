package com.liyue.concurrency.annoations.publish;

import com.liyue.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
public class UnsafeEscpe {//不推荐这样写 会造成线程安全的问题，使用工厂方式 或者私有构造方法可以避免 线程star方式
    private int count = 0;

    private UnsafeEscpe() {
        new Innerclass();//属于启动线程 显示启动隐式启动
    }
    //对象没有构造完成之前就会发布造成溢出 线程不安全
    private class Innerclass{
        public Innerclass(){
            log.info("{}", UnsafeEscpe.this.count);      }
    }

    public static void main(String[] args) {
        new UnsafeEscpe();
    }
}
