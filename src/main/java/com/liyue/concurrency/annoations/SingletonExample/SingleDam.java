package com.liyue.concurrency.annoations.SingletonExample;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//单例  懒汉式
public class SingleDam {
    //私有的构造方法
    private SingleDam() {
    }

    private static SingleDam singleDam = null;
    //静态工厂方法
    public static SingleDam getInstall() {
        if (singleDam == null) {
            return new SingleDam();
        }
        return singleDam;
    }
}
