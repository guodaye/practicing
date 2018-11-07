package com.github.guoyaohui.service.test.groupA;

import com.github.guoyaohui.service.test.Envelop;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author 郭垚辉
 * @date 2018/11/07
 */
@NoArgsConstructor
@AllArgsConstructor
public class GroupAOneRunnable implements Runnable {

    private Envelop envelop;

    private CyclicBarrier barrier;

    @Override
    public void run() {
        try {
            Thread.sleep(1000 * 1);
            int num = envelop.getGroupAOneNumber();
            num++;
            envelop.setGroupAOneNumber(num);
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
