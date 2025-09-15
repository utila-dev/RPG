package kr.jonghyun.system.item;

import org.bukkit.inventory.ItemFlag;

import java.util.List;
import java.util.Map;

public class CustomItem {

    private final String id;
    private String displayName;
    private List<String> lore;
    private List<ItemFlag> itemFlags;
    private int customModelData;
    private Map<String, String> nbtTagMap;

    public CustomItem(String id, String displayName, List<String> lore, List<ItemFlag> itemFlags, int customModelData, Map<String, String> nbtTagMap) {
        this.id = id;
        this.displayName = displayName;
        this.lore = lore;
        this.itemFlags = itemFlags;
        this.customModelData = customModelData;
        this.nbtTagMap = nbtTagMap;
    }
}
