package com.goucraft.dexp;

import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;
import org.black_ixx.playerpoints.PlayerPoints;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Administrator on 2017/2/12 0012.
 */
public class DEMain extends JavaPlugin {
    private PlayerPoints playerPoints;
    private Vault vault;
    private Economy economy = null;
    private static DEMain Instance;

    public static DEMain getInstance() {
        return Instance;
    }

    private boolean hookPlayerPoints() {
        final Plugin plugin = this.getServer().getPluginManager().getPlugin("PlayerPoints");
        playerPoints = PlayerPoints.class.cast(plugin);
        return playerPoints != null;
    }

    public PlayerPoints getPlayerPoints() {
        return playerPoints;
    }

    public Economy getEconomy() {
        return this.economy;
    }

    public boolean hookVault() {
        this.vault = (Vault) this.getServer().getPluginManager().getPlugin("Vault");
        if (this.vault != null) {
            RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
            if (rsp != null) {
                this.economy = rsp.getProvider();
                this.getLogger().info("succeed");
            } else if (rsp == null) {
                this.getLogger().info("Failed to get economy");
            }
            this.getLogger().info("found vault");
        }
        return true;
    }


    @Override
    public void onEnable() {
        Instance = this;
        getLogger().info(ChatColor.RED + "======================");
        getLogger().info(ChatColor.RED + "接小中型插件定制");
        getLogger().info(ChatColor.RED + "联系方式QQ:775659148");
        getLogger().info(ChatColor.RED + "======================");
        getServer().getScheduler().runTaskTimer(this, new Timers(), 0L, 20L);
        hookVault();
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.RED + "======================");
        getLogger().info(ChatColor.RED + "接小中型插件定制");
        getLogger().info(ChatColor.RED + "联系方式QQ:775659148");
        getLogger().info(ChatColor.RED + "======================");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = Bukkit.getPlayer(args[1]);
        Tools c = new Tools();
        int time = 0;
        int times = 0;
        int money = 0;
        if (!label.equalsIgnoreCase("dexp")) {
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage("&b /dexp set [玩家] [倍数] [时间 分钟] [方式]");
            return true;
        }
        //dexp set [player] [倍数] [时间] [方式]
        if (p == null) {
            sender.sendMessage("§4错误的玩家名称");
            return true;
        }
        if (Integer.valueOf(args[2]) != null) {
            times = Integer.valueOf(args[2]);
        } else {
            sender.sendMessage("§4错误的倍数");
            return true;
        }
        if (Integer.valueOf(args[2]) != null) {
            time = Integer.valueOf(args[2]);
        } else {
            sender.sendMessage("§4错误的时间");
            return true;
        }
        if (Integer.valueOf(args[5]) != null) {
            money = Integer.valueOf(args[5]);
        } else {
            sender.sendMessage("§4错误的金额");
            return true;
        }
        if (!args[0].equalsIgnoreCase("set")) {
            sender.sendMessage("&b /dexp set [player] [倍数] [时间] [方式]");
            return true;
        }
        if (!sender.isOp() || sender.hasPermission("dexp.set")) {
            sender.sendMessage("§4权限不足!");
            return true;
        }
        if (c.isDouble(p)) {
            if (times != c.getTimes(p)) {
                sender.sendMessage("§b若要续费请输入相同倍数,或者等待结束");
                return true;
            }
        }
        if (args[4].equalsIgnoreCase("0")) {
            if (economy.has(args[1], money)) {
                economy.withdrawPlayer(args[1], money);
                sender.sendMessage("扣费成功已为" + args[1] + "开启" + args[2] + "倍经验" + "持续" + args[3] + "分钟");
                if (c.isDouble(p)) {
                    c.addTime(p, time * 60);
                } else {
                    c.adds(p, times, time * 60);
                }
            } else {
                sender.sendMessage("扣费失败");
            }
            return true;
        }
        if (args[4].equalsIgnoreCase("1")) {
            //预计扣的钱
            if (playerPoints.getAPI().take(args[1], money)) {
                sender.sendMessage("扣费成功已为" + args[1] + "开启" + args[2] + "倍经验" + "持续" + args[3] + "分钟");
                if (c.isDouble(p)) {
                    c.addTime(p, time * 60);
                } else {
                    c.adds(p, times, time * 60);
                }
            } else {
                sender.sendMessage("扣费失败");
            }
            return true;
        }
        sender.sendMessage("付款方式错误：0-点券付款，1-金币付款");
        return true;
    }

}