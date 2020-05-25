package gg.bayes.challenge.service;

import gg.bayes.challenge.repository.entity.Match;
import gg.bayes.challenge.rest.model.HeroKills;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HeroKillsParser {

    private static final String KILLED_BY = "is killed by";

    public Set<HeroKills> getHeroKills(String payload, Match match) {
        Map<String, Integer> heroKillsCount = new HashMap<>();
        String[] events = Parser.splitPayload(payload);
        Arrays.stream(events)
                .filter(event -> event.contains(KILLED_BY))
                .map(event -> Parser.extractHeroNameFromEvent(event, KILLED_BY))
                .forEach(heroName -> trackHeroKilledCount(heroKillsCount, heroName));

        return prepareHeroKills(heroKillsCount, match);
    }

    private void trackHeroKilledCount(Map<String, Integer> heroKillsCount, String heroName) {
        if (heroKillsCount.containsKey(heroName)) {
            heroKillsCount.put(heroName, heroKillsCount.get(heroName) + 1);
        } else {
            heroKillsCount.put(heroName, 1);
        }
    }

    private Set<HeroKills> prepareHeroKills(Map<String, Integer> heroKillsCount, Match match) {
        Set<HeroKills> heroKills = new HashSet<>();
        Set<Map.Entry<String, Integer>> entrySet = heroKillsCount.entrySet();
        entrySet.forEach(entry -> {
            HeroKills heroKill = new HeroKills();
            heroKill.setMatch(match);
            heroKill.setHero(entry.getKey());
            heroKill.setKills(entry.getValue());
            heroKills.add(heroKill);
        });
        return heroKills;
    }


}
