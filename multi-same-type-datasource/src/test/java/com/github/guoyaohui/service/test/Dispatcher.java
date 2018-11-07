package com.github.guoyaohui.service.test;

import com.github.guoyaohui.service.test.dispatcher.GroupDispatcher;
import com.github.guoyaohui.service.test.groupA.GroupAOneRunnable;
import com.github.guoyaohui.service.test.groupA.GroupATwoRunnable;
import com.github.guoyaohui.service.test.groupB.GroupBOneRunnable;
import com.github.guoyaohui.service.test.groupB.GroupBTwoRunnable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

/**
 * @author 郭垚辉
 * @date 2018/11/07
 */
public class Dispatcher {

    ThreadPoolExecutor groupDispatcherExecutor = new ThreadPoolExecutor(3, 3, 10, TimeUnit.MINUTES,
        new ArrayBlockingQueue<>(10));

    ThreadPoolExecutor groupAExecutor = new ThreadPoolExecutor(3, 3, 10, TimeUnit.MINUTES,
        new ArrayBlockingQueue<>(10));

    ThreadPoolExecutor groupBExecutor = new ThreadPoolExecutor(3, 3, 10, TimeUnit.MINUTES,
        new ArrayBlockingQueue<>(10));

    CyclicBarrier groupABarrier = new CyclicBarrier(3);


    CyclicBarrier groupBBarrier = new CyclicBarrier(3);


    @Test
    public void test() throws InterruptedException {
        Envelop envelop = new Envelop();

        GroupAOneRunnable groupAOneRunnable = new GroupAOneRunnable(envelop, groupABarrier);
        GroupATwoRunnable groupATwoRunnable = new GroupATwoRunnable(envelop, groupABarrier);

        GroupBTwoRunnable groupBTwoRunnable = new GroupBTwoRunnable(envelop, groupBBarrier);
        GroupBOneRunnable groupBOneRunnable = new GroupBOneRunnable(envelop, groupBBarrier);

        List<Runnable> groupAList = Arrays.asList(groupAOneRunnable, groupATwoRunnable);
        List<Runnable> groupBList = Arrays.asList(groupBOneRunnable, groupBTwoRunnable);

        GroupDispatcher groupDispatcher = new GroupDispatcher(4, "AAA", groupAList, groupABarrier, groupAExecutor);
        GroupDispatcher groupBDispatcher = new GroupDispatcher(7, "BBB", groupBList, groupBBarrier, groupBExecutor);

        groupDispatcherExecutor.submit(groupDispatcher);
        groupDispatcherExecutor.submit(groupBDispatcher);

        Thread.sleep(30 * 1000);
        System.out.println(envelop);
    }

}
