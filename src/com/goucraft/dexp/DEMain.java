package com.goucraft.dexp;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Administrator on 2017/2/12 0012.
 */
public class DEMain extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info(ChatColor.RED + "======================");
        getLogger().info(ChatColor.RED + "接小中型插件定制");
        getLogger().info(ChatColor.RED + "联系方式QQ:775659148");
        getLogger().info(ChatColor.RED + "======================");
        getServer().getScheduler().runTaskTimer(this,new Timers(),0L,20L);
    }
}
