package kr.utila.rpg.utils;

import org.bukkit.entity.Player;

public class TextUtil {

    private static final String PREFIX = "§f[§6SYSTEM§f] ";

    public static void t(Player player, String message) {
        player.sendMessage(PREFIX + message);
    }

}
