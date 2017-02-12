package com.goucraft.dexp;

import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;
import org.black_ixx.playerpoints.PlayerPoints;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
        if (args.length > 0) {
            //dexp set [player] [倍数] [时间] [方式]
            if (args[0].equalsIgnoreCase("set")) {
                if (sender.isOp() || sender.hasPermission("dexp.set")) {
                    if (args[4].equalsIgnoreCase("0")) {

                    } else if (args[4].equalsIgnoreCase("1")) {
                    } else {
                        sender.sendMessage("付款方式错误：0-点券付款，1-金币付款");
                    }
                } else {
                    sender.sendMessage("§4§l权限丢失-dexp.set");
                }
            }
        }
        return true;
    }
}
