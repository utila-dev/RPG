package kr.jonghyun.system.stat;

import kr.jonghyun.system.Main;
import kr.jonghyun.feat.ability.Ability;

import java.io.File;
import java.util.Map;

public class StatManager {

    private final StatStorage statStorage = new StatStorage();
    private final StatLoader statLoader = new StatLoader();

    public void registerAllStat() {
        final File DIRECTORY = new File(Main.getInstance().getDataFolder(), "stat");
        for (Stat stat : Stat.values()) {
            if (!DIRECTORY.exists()) {
                Main.getInstance().saveResource("stat/" + stat.name(), false);
            }
            statStorage.put(stat, statLoader.read(new File(DIRECTORY, stat.name() + ".yml")));
        }
    }

    public Map<Ability, Double> getCoefficient(Stat stat) {
        return statStorage.get(stat);
    }

}
