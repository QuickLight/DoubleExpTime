package com.goucraft.dexp;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.event.Listener;

/**
 * Created by Administrator on 2017/2/12 0012.
 */
public class Listeners implements Listener {
    private final DEMain plugin = DEMain.getInstance();
    private Economy economy = plugin.getEconomy();
}
