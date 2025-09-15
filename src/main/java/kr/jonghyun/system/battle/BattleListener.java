package kr.jonghyun.system.battle;

import kr.jonghyun.system.user.UserSystem;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BattleListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player attacker) {
            Entity victim = event.getEntity();
            double damage = UserSystem.getUserManager().get(attacker).getDamage(victim.getType());
            event.setDamage(damage);
        }
    }

}
