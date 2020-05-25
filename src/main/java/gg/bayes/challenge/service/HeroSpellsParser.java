package gg.bayes.challenge.service;

import gg.bayes.challenge.rest.model.HeroSpells;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
class HeroSpellsParser {

    private static final String CASTS_ABILITY = "casts ability";

    List<HeroSpells> getHeroSpells(String payload) {
        Map<String, Integer> heroSpellsCount = new HashMap<>();
        String[] events = Parser.splitPayload(payload);
        Arrays.stream(events)
                .filter(event -> event.contains(CASTS_ABILITY))
                .map(this::extractSpellNameFromEvent)
                .forEach(spellName -> trackHeroSpellsCount(heroSpellsCount, spellName));

        return prepareHeroSpells(heroSpellsCount);
    }

    private List<HeroSpells> prepareHeroSpells(Map<String, Integer> heroSpellsCount) {
        List<HeroSpells> heroSpells = new ArrayList<>();
        Set<Map.Entry<String, Integer>> entrySet = heroSpellsCount.entrySet();
        entrySet.forEach(entry -> {
            HeroSpells heroSpell = new HeroSpells();
            heroSpell.setSpell(entry.getKey());
            heroSpell.setCasts(entry.getValue());
            heroSpells.add(heroSpell);
        });
        return heroSpells;
    }

    private void trackHeroSpellsCount(Map<String, Integer> heroSpellsCount, String heroName) {
        if (heroSpellsCount.containsKey(heroName)) {
            heroSpellsCount.put(heroName, heroSpellsCount.get(heroName) + 1);
        } else {
            heroSpellsCount.put(heroName, 1);
        }
    }

    private String extractSpellNameFromEvent(String event) {
        String castNameItem = event.substring(event.indexOf(CASTS_ABILITY) + CASTS_ABILITY.length() + 1, event.length());
        return castNameItem.split("\\s+")[0];
    }
}
