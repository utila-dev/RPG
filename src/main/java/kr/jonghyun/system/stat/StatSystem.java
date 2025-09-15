package kr.jonghyun.system.stat;

import kr.jonghyun.system.System;

public class StatSystem implements System {

    private static final StatManager statManager = new StatManager();

    public static StatManager getStatManager() {
        return statManager;
    }

    @Override
    public void enableStorage() {
        statManager.registerAllStat();
    }

    @Override
    public void enableGame() {

    }

    @Override
    public void disableStorage() {

    }

    @Override
    public void disableGame() {

    }
}
