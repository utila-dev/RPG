package kr.jonghyun.system;

import kr.jonghyun.feat.lib.SimpleInventoryHolder;
import kr.jonghyun.system.battle.BattleSystem;
import kr.jonghyun.system.experience.ExperienceSystem;
import kr.jonghyun.system.stat.StatSystem;
import kr.jonghyun.system.user.UserSystem;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    private static Main instance;

    private static List<System> systems;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        systems = new ArrayList<>();
        registerSystem(new StatSystem());
        registerSystem(new UserSystem());
        registerSystem(new BattleSystem());
        registerSystem(new ExperienceSystem());
        registerLibraries();
        for(System system : systems) {
            system.enableStorage();
            system.enableGame();
        }
    }

    private void registerSystem(System system) {
        systems.add(system);
    }

    private void registerLibraries() {
        getServer().getPluginManager().registerEvents(new SimpleInventoryHolder.InventoryHolderHandler(), this);
    }

    @Override
    public void onDisable() {
        for(System system : systems) {
            system.disableStorage();
            system.disableGame();
        }
    }
}
