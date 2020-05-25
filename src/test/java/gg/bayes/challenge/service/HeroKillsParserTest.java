package gg.bayes.challenge.service;

import gg.bayes.challenge.repository.entity.Match;
import gg.bayes.challenge.rest.model.HeroKills;
import org.junit.jupiter.api.Test;

import java.util.Set;

class HeroKillsParserTest {

    private HeroKillsParser heroKillsParser = new HeroKillsParser();

    @Test
    void should_get_heroKills_for_the_given_payload() {
        String payload = "[00:11:17.489] npc_dota_hero_snapfire is killed by npc_dota_hero_mars\n" +
                "[00:11:20.322] npc_dota_hero_rubick is killed by npc_dota_hero_pangolier\n";

        Match match = new Match();
        Set<HeroKills> heroKills = heroKillsParser.getHeroKills(payload, match);

//        assertEquals(2, heroKills.size());
//        assertTrue("mars", heroKills.contains());
//        assertTrue(1, heroKills.get(0).getKills());
//
//        assertTrue("pangolier", heroKills.get(1).getHero());
//        assertTrue(1, heroKills.get(1).getKills());
    }

}