package com.liyue.concurrency.annoations.publish;

import com.liyue.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@NotThreadSafe
public class UNsafePublish {// 发布对象
    private String status[] = new String[]{"aa", "bb", "cc"};

    public String[] getStatus() {
        return status;
    }

    public static void main(String[] args) {
        UNsafePublish uNsafePublish = new UNsafePublish();
        log.info("数组：{}", Arrays.toString(uNsafePublish.getStatus()));
        uNsafePublish.getStatus()[0] = "dd";
        log.info("数组：{}", Arrays.toString(uNsafePublish.getStatus()));
    }

    //同过public 来访问内部的私有域 造成了其他的线程可以发现

}
