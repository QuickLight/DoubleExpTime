package com.goucraft.dexp;

import org.bukkit.entity.Player;

/**
 * Created by Administrator on 2017/2/12.
 */
public class Timers implements Runnable {
    @Override
    public void run() {
        new Configs().Reduce_one_second();
    }
}
