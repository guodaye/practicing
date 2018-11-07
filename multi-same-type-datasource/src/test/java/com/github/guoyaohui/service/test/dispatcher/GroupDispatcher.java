package com.github.guoyaohui.service.test.dispatcher;

import java.util.Date;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadPoolExecutor;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author 郭垚辉
 * @date 2018/11/07
 */
@NoArgsConstructor
@AllArgsConstructor
public class GroupDispatcher implements Runnable {

    private int cycleCount;
    private String name;

    private List<Runnable> runnables;

    private CyclicBarrier barrier;

    private ThreadPoolExecutor executor;


    @Override
    public void run() {

        int i = cycleCount;
        System.out.println("group " + name + " start " + (new Date()));
        while (i > 0) {
            for (Runnable runnable : runnables) {
                executor.submit(runnable);
            }
            try {
                barrier.await();
                barrier.reset();
                i--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
        System.out.println("group " + name + " finish " + (new Date()));
    }
}
