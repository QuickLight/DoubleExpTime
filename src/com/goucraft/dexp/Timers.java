package com.goucraft.dexp;

import org.bukkit.entity.Player;

/**
 * Created by Administrator on 2017/2/12.
 */
public class Timers implements Runnable {
    private Player p = null;
    private int time = 0;
    @Override
    public void run() {
        new Configs().Reduce_one_second();
    }

    public Timers(Player p, int time) {
        this.time = time;
        this.p = p;
    }

}
