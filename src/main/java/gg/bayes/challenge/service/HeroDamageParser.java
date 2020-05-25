package gg.bayes.challenge.service;

import gg.bayes.challenge.rest.model.HeroDamage;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
class HeroDamageParser {
    private static final String NPC_DOTA = "npc_dota_hero_";
    private static final String HITS = " hits";

    List<HeroDamage> getHeroDamages(String sourceHeroName, String payload) {
        Map<String, HeroDamage> heroDamageCount = new HashMap<>();
        String[] events = Parser.splitPayload(payload);
        Arrays.stream(events)
                .filter(event -> event.contains(NPC_DOTA + sourceHeroName + HITS))
                .map(event -> event.split("\\s+"))
                .forEachOrdered(eventItems -> extractHeroDamageFromEvents(heroDamageCount, eventItems));

        return prepareHeroDamagesList(heroDamageCount);
    }

    private void extractHeroDamageFromEvents(Map<String, HeroDamage> heroDamageCount, String[] eventItems) {
        HeroDamage heroDamage = new HeroDamage();
        String heroName = extractHeroName(eventItems[3]);
        String damage = eventItems[eventItems.length - 3];
        if (heroDamageCount.containsKey(heroName)) {
            HeroDamage existingHeroDamage = heroDamageCount.get(heroName);
            existingHeroDamage.setDamageInstances(existingHeroDamage.getDamageInstances() + 1);
            existingHeroDamage.setTotalDamage(existingHeroDamage.getTotalDamage() + Integer.valueOf(damage));
            heroDamageCount.put(heroName, existingHeroDamage);
        } else {
            heroDamage.setTarget(heroName);
            heroDamage.setDamageInstances(1);
            heroDamage.setTotalDamage(Integer.valueOf(damage));
            heroDamageCount.put(heroName, heroDamage);
        }
    }

    private List<HeroDamage> prepareHeroDamagesList(Map<String, HeroDamage> heroDamageCount) {
        Set<Map.Entry<String, HeroDamage>> entries = heroDamageCount.entrySet();
        List<HeroDamage> heroDamages = new ArrayList<>();
        for (Map.Entry entry : entries) {
            heroDamages.add((HeroDamage) entry.getValue());
        }
        return heroDamages;
    }

    private String extractHeroName(String heroNameStartWithNPC) {
        if (heroNameStartWithNPC.startsWith(NPC_DOTA)) {
            return heroNameStartWithNPC.substring(NPC_DOTA.length(), heroNameStartWithNPC.length());
        }
        return "";
    }
}
