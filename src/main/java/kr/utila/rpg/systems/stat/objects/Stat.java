package kr.utila.rpg.systems.stat.objects;

import kr.utila.rpg.systems.stat.managers.StatManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.UUID;

import static kr.utila.rpg.utils.TextUtil.t;

public class Stat {

    public static final int BASIC_HEALTH = 100;

    private UUID uuid;
    private int level;
    private long experience;
    private int strength;
    private int agility;
    private int hp;
    private int intelligence;
    private int point;

    public Stat(Player player) {
        this.uuid = player.getUniqueId();
        this.level = 1;
        this.point = StatManager.getInstance().getSetting().getPointPerLevel();
    }

    public void checkLevel() {
        long requireExperience = StatManager.getInstance().getExperience().get(level);
        if (experience >= requireExperience) {
            level++;
            point += StatManager.getInstance().getSetting().getPointPerLevel();
            experience = experience - requireExperience;
            Player player = Bukkit.getPlayer(uuid);
            t(player, "§a레벨업!");
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            update();
            checkLevel();
        }
    }

    public void update() {
        Player player = Bukkit.getPlayer(uuid);
        StatSetting setting = StatManager.getInstance().getSetting();
        player.setMaxHealth(
                BASIC_HEALTH + (level * setting.getHealthPerLevel())
                        + (hp * setting.getHPHealth())
                        + (intelligence * setting.getIntelligenceHealth())
                        + (strength * setting.getStrengthHealth())
        );
        player.setWalkSpeed((float) (0.2 + (0.2 * (agility * setting.getAgilitySpeedPercentage()))));
    }

    public double getDamage(AttackType attackType, AttackVictim attackVictim) {
        StatSetting setting = StatManager.getInstance().getSetting();
        double damage = 1 + (
                strength * setting.getStrengthDamage() +
                        agility * setting.getAgilityDamage() +
                        hp * setting.getHPDamage() +
                        intelligence * setting.getIntelligenceDamage()
        );
        if (attackVictim == AttackVictim.MONSTER) {
            damage += hp * setting.getHPDamageToMonster();
        }
        switch (attackType) {
            case PHYSICS -> {
                damage += damage * (strength * setting.getStrengthDamagePercentage() + agility * setting.getAgilityDamagePercentage());
            }
            case SKILL -> {
                damage += damage * (strength * setting.getStrengthDamagePercentage() + agility * setting.getAgilityDamagePercentage() + intelligence * setting.getIntelligenceSkillDamagePercentage());
            }
        }
        return damage;
    }

    public double getDefense(double damage) {
        StatSetting setting = StatManager.getInstance().getSetting();
        damage -= damage * (hp * setting.getHpDefensePercentage());
        return damage < 0 ? 0 : damage;
    }

    public UUID getUUID() {
        return uuid;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getExperience() {
        return experience;
    }

    public void setExperience(long experience) {
        this.experience = experience;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getHP() {
        return hp;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
