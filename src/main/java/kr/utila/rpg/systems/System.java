package kr.utila.rpg.systems;

import kr.utila.rpg.Main;

public interface System {

    Main PLUGIN = Main.getInstance();

    void onEnable();
    void onDisable();
    void registerCommands();
    void registerEvents();

}
