package kr.utila.rpg;

import kr.utila.rpg.libs.SimpleInventoryHolder;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

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
    }

    private void prepareLibs() {
        getServer().getPluginManager().registerEvents(new SimpleInventoryHolder.InventoryHolderHandler(), this);
    }

}
