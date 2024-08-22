package kr.utila.rpg.systems.stat;

import kr.utila.rpg.systems.System;
import kr.utila.rpg.systems.stat.managers.StatManager;

public class StatSystem implements System {

    @Override
    public void onEnable() {
        StatManager.getInstance().read();
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void registerCommands() {

    }

    @Override
    public void registerEvents() {

    }
}
