package kr.jonghyun.system.user;

import kr.jonghyun.system.System;

public class UserSystem implements System {
    private static final UserManager userManager = new UserManager();

    public static UserManager getUserManager() {
        return userManager;
    }

    @Override
    public void enableStorage() {
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
