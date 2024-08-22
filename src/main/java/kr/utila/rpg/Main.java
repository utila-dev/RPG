package kr.utila.rpg;

import kr.utila.rpg.libs.SimpleInventoryHolder;
import kr.utila.rpg.systems.System;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    private static Main instance;
    private List<System> systems = new ArrayList<>();

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        prepareLibs();

    }

    @Override
    public void onDisable() {
        disablePlugins();
    }

    private void prepareLibs() {
        getServer().getPluginManager().registerEvents(new SimpleInventoryHolder.InventoryHolderHandler(), this);
    }

    public void loadPlugin(Class<? extends System> clazz) {
        try {
            System system = clazz.newInstance();
            system.registerEvents();
            system.registerCommands();
            system.onEnable();
            systems.add(system);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disablePlugins() {
        for (System system : systems) {
            system.onDisable();
        }
    }

}
