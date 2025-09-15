package kr.jonghyun.feat.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemStackBuilder {

    private Material material;
    private String displayName;
    private List<String> lore;
    private List<ItemFlag> itemFlags;
    private int customModelData;
    private int amount;

    private ItemStackBuilder(Material material) {
        this.material = material;
    }

    public static ItemStackBuilder of(Material material) {
        return new ItemStackBuilder(material);
    }

    public ItemStackBuilder displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public ItemStackBuilder lore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemStackBuilder lore(String... lore) {
        this.lore = List.of(lore);
        return this;
    }

    public ItemStackBuilder itemFlags(ItemFlag... itemFlags) {
        this.itemFlags = List.of(itemFlags);
        return this;
    }

    public ItemStackBuilder customModelData(int customModelData) {
        this.customModelData = customModelData;
        return this;
    }

    public ItemStackBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemStack create() {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (displayName != null) itemMeta.setDisplayName(displayName);
        if (lore != null) itemMeta.setLore(lore);
        if (itemFlags != null) {
            for (ItemFlag itemFlag : itemFlags) itemMeta.addItemFlags(itemFlag);
        }
        itemMeta.setCustomModelData(customModelData);
        itemStack.setAmount(amount);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
