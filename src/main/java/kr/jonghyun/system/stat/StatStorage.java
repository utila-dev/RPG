package kr.jonghyun.system.stat;

import kr.jonghyun.system.ability.Ability;

import java.util.HashMap;
import java.util.Map;

public class StatStorage {

    private final Map<Stat, Map<Ability, Double>> statAbilityMap = new HashMap<>();

    public void put(Stat stat, Map<Ability, Double> abilityMap) {
        statAbilityMap.put(stat, abilityMap);
    }

    public Map<Ability, Double> get(Stat stat) {
        return statAbilityMap.get(stat);
    }

    public void clear() {
        statAbilityMap.clear();
    }


}
