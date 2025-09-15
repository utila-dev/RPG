package kr.jonghyun.system.battle;

import kr.jonghyun.system.Main;
import kr.jonghyun.system.System;

public class BattleSystem implements System {

    @Override
    public void enableStorage() {

    }

    @Override
    public void enableGame() {
        Main plugin = Main.getInstance();
        plugin.getServer().getPluginManager().registerEvents(new BattleListener(), plugin);
    }

    @Override
    public void disableStorage() {

    }

    @Override
    public void disableGame() {

    }
}
