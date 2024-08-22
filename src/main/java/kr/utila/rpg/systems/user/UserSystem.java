package kr.utila.rpg.systems.user;

import kr.utila.rpg.systems.System;
import kr.utila.rpg.systems.user.listeners.UserListener;
import kr.utila.rpg.systems.user.managers.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class UserSystem implements System {

    @Override
    public void onEnable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            UserManager.getInstance().register(player);
        }
    }

    @Override
    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            UserManager.getInstance().unregister(player);
        }
    }

    @Override
    public void registerCommands() {

    }

    @Override
    public void registerEvents() {
        PLUGIN.getServer().getPluginManager().registerEvents(new UserListener(), PLUGIN);
    }
}
