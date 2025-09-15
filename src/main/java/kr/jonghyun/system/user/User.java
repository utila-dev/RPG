package kr.jonghyun.system.user;

import kr.jonghyun.system.ability.Ability;
import kr.jonghyun.system.stat.Stat;
import kr.jonghyun.system.stat.StatSystem;
import org.bukkit.entity.EntityType;

import java.util.Map;
import java.util.UUID;

public class User {

    private UUID uuid;
    private Map<Stat, Integer> statMap;
    private int statPoint;

    public Map<Stat, Integer> getStatMap() {
        return statMap;
    }

    public int getStatPoint() {
        return statPoint;
    }

    public void setStatPoint(int statPoint) {
        this.statPoint = statPoint;
    }

    public User(UUID uuid) {
        this.uuid = uuid;
    }

    public double getDamage(EntityType victimType) {
        double damage = 1.0d;
        double damagePercentage = 100;
        for (Map.Entry<Stat, Integer> entry : statMap.entrySet()) {
            Map<Ability, Double> coefficient = StatSystem.getStatManager().getCoefficient(entry.getKey());
            damage += coefficient.get(Ability.DAMAGE);
            damagePercentage += coefficient.get(Ability.DAMAGE_PERCENTAGE);
        }
        return damage * (damagePercentage / 100);
    }
}
