package com.liyue.concurrency.annoations.SingletonExample;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//单例  懒汉式   单线程
public class SingleDam1 {
    //私有的构造方法
    private SingleDam1() {
    }

    private static SingleDam1 singleDam = null;
    //静态工厂方法
    public static SingleDam1 getInstall() {
        if (singleDam == null) {
            return new SingleDam1();
        }
        return singleDam;
    }
}
