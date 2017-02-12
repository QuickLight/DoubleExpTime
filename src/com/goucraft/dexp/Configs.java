package com.goucraft.dexp;

import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/2/12.
 */
public class Configs {
    private static HashMap<Player, Integer> map_time = new HashMap<>();
    //剩余时间
    private static HashMap<Player, Integer> map_times = new HashMap<>();
    //倍数
    protected void Reduce_one_second() {
        //减少时间方法
        for (Player p : map_time.keySet()) {
            int i = map_time.get(p) - 1;
            if (i == 0) {
                p.sendMessage("§b[多倍经验]您的多倍经验已经到期!");
                map_time.remove(p);
                map_times.remove(p);
            } else {
                map_time.put(p, i);
            }
        }
    }
    //减少时间方法
    public int getSecond(Player p) {

        if (map_time.containsKey(p)) {
            return map_time.get(p);
        } else {
            return 0;
        }

    }
    //获取剩余时间
    public boolean isDouble(Player p) {
        if (map_times.containsKey(p) && map_time.containsKey(p)) {
            return true;
        }
        return false;
    }
    //判断玩家是否是氪金玩家
    public int getTimes(Player p) {

        if (map_times.containsKey(p)) {
            return map_times.get(p);
        } else {
            return 0;
        }
    }
    //获取倍数
    public void adds(Player p, int times, int time) {

        //times 倍数
        //time  时间
        map_time.put(p, time);
        map_times.put(p, times);
    }
    //添加玩家
    public void addTime(Player p,int time){
        //续费
        int i=map_time.get(p);
        map_time.put(p,map_time.get(p)+time);
    }
    //续费
}