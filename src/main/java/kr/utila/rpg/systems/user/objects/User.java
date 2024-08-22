package kr.utila.rpg.systems.user.objects;

import kr.utila.rpg.systems.stat.objects.Stat;
import org.bukkit.entity.Player;

import java.util.UUID;

public class User {

    private UUID uuid;
    private Stat stat;

    public User(Player player) {
        this.uuid = player.getUniqueId();
        this.stat = new Stat(player);
    }

    public Stat getStat() {
        return stat;
    }

    public UUID getUUID() {
        return uuid;
    }
}
