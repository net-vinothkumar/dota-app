package gg.bayes.challenge.service;

import gg.bayes.challenge.rest.model.HeroDamage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeroDamageParserTest {

    private HeroDamageParser heroDamageParser = new HeroDamageParser();

    @Test
    void should_get_heroDamage_for_the_given_payload() {
        String payload = "[00:10:47.163] npc_dota_hero_abyssal_underlord hits npc_dota_hero_bloodseeker with abyssal_underlord_firestorm for 5 damage (644->639)\n" +
                "[00:10:47.196] npc_dota_hero_abyssal_underlord hits npc_dota_hero_bloodseeker with abyssal_underlord_firestorm for 20 damage (639->619)\n";

        List<HeroDamage> heroDamages = heroDamageParser.getHeroDamages("abyssal_underlord", payload);

        assertEquals(1, heroDamages.size());
        assertEquals("bloodseeker", heroDamages.get(0).getTarget());
        assertEquals(2, heroDamages.get(0).getDamageInstances());
        assertEquals(25, heroDamages.get(0).getTotalDamage());
    }
}