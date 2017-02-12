package com.goucraft.dexp;

/**
 * Created by Administrator on 2017/2/12.
 */
public class Timers implements Runnable {
    @Override
    public void run() {
        new Tools().Reduce_one_second();
    }
}
