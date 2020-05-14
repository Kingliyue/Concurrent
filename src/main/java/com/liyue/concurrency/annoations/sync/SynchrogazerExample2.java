package com.liyue.concurrency.annoations.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchrogazerExample2 {//Synchrogazerd 不支持继承

    //修饰类;作用于同一个对象
    public  void test(){

        synchronized (SynchrogazerExample2.class){
            for (int i = 0 ;i< 10;i++){
                log.info("输出结果-》{}",i);
            }
        }
    }
    //修饰方法 ,作用于所有对象
    public static synchronized  void  test2(){
        for (int i = 0 ;i< 10;i++){
            log.info("输出结果-》{}",i);
        }
    }

    public static void main(String[] args) {
        final int a= 100;
        //
        SynchrogazerExample2 example = new SynchrogazerExample2();
        SynchrogazerExample2 exampl2 = new SynchrogazerExample2();
        ExecutorService service = Executors.newCachedThreadPool();
//        service.execute(()->{
//            //example.test();
//        });
//        service.execute(()->{
//            //exampl2.test();
//        });

        service.execute(()->{
            example.test2();
        });
        service.execute(()->{
            exampl2.test2();
        });



        service.shutdown();
    }
}
