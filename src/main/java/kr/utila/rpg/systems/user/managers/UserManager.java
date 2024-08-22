package kr.utila.rpg.systems.user.managers;

import com.mongodb.client.MongoCollection;
import kr.utila.rpg.Main;
import kr.utila.rpg.database.DBConnector;
import kr.utila.rpg.database.DBUtil;
import kr.utila.rpg.systems.user.objects.User;
import kr.utila.rpg.utils.FileUtil;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bson.Document;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class UserManager {
    private static final MongoCollection<Document> USER_COLLECTION = DBConnector.getMongoDatabase().getCollection("user");

    private static final UserManager instance = new UserManager();

    private final Map<UUID, User> users;

    private UserManager() {
        users = new HashMap<>();
    }

    public static UserManager getInstance() {
        return instance;
    }

    public void register(Player player) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§f[§6SYSTEM§f] §7Data Loading..."));
        new BukkitRunnable() {
            @Override
            public void run() {
                UUID uuid = player.getUniqueId();
                Document userDocument = USER_COLLECTION.find(new Document("uuid", uuid)).first();
                if (userDocument == null) {
                    User user = new User(player);
                    USER_COLLECTION.insertOne(DBUtil.toDocument(user));
                    users.put(uuid, user);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§f[§6SYSTEM§f] §aData Loading Success!"));
                    return;
                }
                users.put(uuid, (User) DBUtil.toObject(userDocument, User.class));
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§f[§6SYSTEM§f] §aData Loading Success!"));
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
            }
        }.runTaskLater(Main.getInstance(), 5l);
    }

    public void unregister(Player player) {
        UUID uuid = player.getUniqueId();
        DBUtil.update(USER_COLLECTION, new Document("uuid", uuid), DBUtil.toDocument(users.get(uuid)));
    }

    public User get(Player player) {
        return users.get(player.getUniqueId());
    }

}
