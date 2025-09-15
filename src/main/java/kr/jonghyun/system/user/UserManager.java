package kr.jonghyun.system.user;

import org.bukkit.entity.Player;

public class UserManager {

    private static final UserStorage userStorage = new UserStorage();

    public User get(Player player) {
        return userStorage.get(player.getUniqueId());
    }

}
