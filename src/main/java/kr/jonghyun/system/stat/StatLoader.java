package kr.jonghyun.system.stat;

import kr.jonghyun.system.FileLoader;
import kr.jonghyun.system.ability.Ability;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class StatLoader implements FileLoader<Map<Ability, Double>> {

    @Override
    public Map<Ability, Double> read(File file) {
        if (!file.exists()) {
            return null;
        }
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        Map<Ability, Double> abilityMap = new HashMap<>();
        for (Ability ability : Ability.values()) {
            abilityMap.put(Ability.valueOf(ability.name()), yamlConfiguration.getDouble(ability.name()));
        }
        return abilityMap;
    }
}
