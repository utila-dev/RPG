package kr.utila.rpg.systems;

public interface System {

    void onEnable();
    void onDisable();
    void registerCommands();
    void registerEvents();

}
