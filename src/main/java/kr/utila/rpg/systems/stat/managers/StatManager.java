package kr.utila.rpg.systems.stat.managers;

import kr.utila.rpg.Main;
import kr.utila.rpg.systems.stat.objects.StatSetting;
import kr.utila.rpg.utils.FileUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public final class StatManager {

    private static final File SETTING_FILE = new File(Main.getInstance().getDataFolder(), "stat_setting.json");
    private static final File EXPERIENCE_FILE = new File(Main.getInstance().getDataFolder(), "experience.json");

    private static final StatManager instance = new StatManager();
    private StatSetting setting;
    private Map<Integer, Long> experience;

    private StatManager() {

    }

    public static StatManager getInstance() {
        return instance;
    }

    public void read() {
        if (!SETTING_FILE.exists()) {
            Main.getInstance().saveResource("stat_setting.json", false);
        }
        if (!EXPERIENCE_FILE.exists()) {
            Main.getInstance().saveResource("experience.json", false);
        }
        setting = (StatSetting) FileUtil.read(SETTING_FILE, StatSetting.class);
        experience = (Map<Integer, Long>) FileUtil.read(EXPERIENCE_FILE, HashMap.class);
    }

    public StatSetting getSetting() {
        return setting;
    }

    public Map<Integer, Long> getExperience() {
        return experience;
    }
}
