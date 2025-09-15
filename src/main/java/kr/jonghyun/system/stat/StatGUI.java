package kr.jonghyun.system.stat;

import kr.jonghyun.feat.lib.SimpleInventoryHolder;
import kr.jonghyun.feat.util.ItemStackBuilder;
import kr.jonghyun.system.user.UserSystem;
import kr.jonghyun.system.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;

public class StatGUI implements SimpleInventoryHolder {

    private Inventory inventory;

    private final int STR_SLOT = 10;
    private final int LUK_SLOT = 12;
    private final int INT_SLOT = 14;
    private final int DEX_SLOT = 16;


    public StatGUI(Player player) {
        inventory = Bukkit.createInventory(this, 9 * 3, "[SYSTEM] STAT");
        update(player);
    }

    public void update(Player player) {
        User user = UserSystem.getUserManager().get(player);
        inventory.setItem(STR_SLOT, ItemStackBuilder.of(Material.DIAMOND_SWORD)
                .displayName("§6[STR] §7[" + user.getStatMap().get(Stat.STR))
                .lore(
                        "§7나의 잔여 어빌리티 포인트 : " + user.getStatPoint()
                )
                .itemFlags(ItemFlag.HIDE_ATTRIBUTES)
                .amount(1).create());

        inventory.setItem(LUK_SLOT, ItemStackBuilder.of(Material.TNT)
                .displayName("§6[LUK] §7[" + user.getStatMap().get(Stat.LUK))
                .lore(
                        "§7나의 잔여 어빌리티 포인트 : " + user.getStatPoint()
                )
                .itemFlags(ItemFlag.HIDE_ATTRIBUTES)
                .amount(1).create());

        inventory.setItem(INT_SLOT, ItemStackBuilder.of(Material.STICK)
                .displayName("§6[INT] §7[" + user.getStatMap().get(Stat.INT))
                .lore(
                        "§7나의 잔여 어빌리티 포인트 : " + user.getStatPoint()
                )
                .itemFlags(ItemFlag.HIDE_ATTRIBUTES)
                .amount(1).create());

        inventory.setItem(DEX_SLOT, ItemStackBuilder.of(Material.DIAMOND_BOOTS)
                .displayName("§6[DEX] §7[" + user.getStatMap().get(Stat.DEX))
                .lore(
                        "§7나의 잔여 어빌리티 포인트 : " + user.getStatPoint()
                )
                .itemFlags(ItemFlag.HIDE_ATTRIBUTES)
                .amount(1).create());
    }


    @NotNull
    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void executeEvent(InventoryEvent inventoryEvent, EventType type) {
        switch (type) {
            case CLICK -> {
                InventoryClickEvent event = (InventoryClickEvent) inventoryEvent;
                event.setCancelled(true);
                final int CLICKED_SLOT = event.getRawSlot();
                Player player = (Player) event.getWhoClicked();
                User user = UserSystem.getUserManager().get(player);
                if (user.getStatPoint() <= 0) return;
                Stat selectedStat = null;
                switch (CLICKED_SLOT) {
                    case STR_SLOT -> {
                        selectedStat = Stat.STR;
                    }
                    case DEX_SLOT -> {
                        selectedStat = Stat.DEX;
                    }
                    case INT_SLOT -> {
                        selectedStat = Stat.INT;
                    }
                    case LUK_SLOT -> {
                        selectedStat = Stat.LUK;
                    }
                }
                if (selectedStat == null) return;
                increaseStat(selectedStat, player);
            }
        }
    }

    public void increaseStat(Stat stat, Player player) {
        User user = UserSystem.getUserManager().get(player);
        user.setStatPoint(user.getStatPoint() - 1);
        user.getStatMap().put(stat, user.getStatMap().get(stat) + 1);
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
        update(player);
    }

}
