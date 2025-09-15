package kr.jonghyun.system.user;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserStorage {

    private final Map<UUID, User> userMap = new HashMap<>();

    public void put(UUID uuid, User user) {
        userMap.put(uuid, user);
    }

    public void clear() {
        userMap.clear();
    }

    public void remove(UUID uuid) {
        userMap.remove(uuid);
    }

    public boolean exists(UUID uuid) {
        return userMap.containsKey(uuid);
    }

    public User get(UUID uuid) {
        return userMap.get(uuid);
    }
}