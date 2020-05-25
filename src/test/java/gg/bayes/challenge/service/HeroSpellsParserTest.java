package gg.bayes.challenge.service;

import gg.bayes.challenge.rest.model.HeroSpells;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeroSpellsParserTest {

    private HeroSpellsParser heroSpellsParser = new HeroSpellsParser();

    @Test
    void should_get_heroSpells_for_the_given_heroName() {
        String payload = "[00:10:41.998] npc_dota_hero_abyssal_underlord casts ability abyssal_underlord_firestorm (lvl 1) on dota_unknown\n" +
                "[00:11:10.224] npc_dota_hero_rubick casts ability rubick_fade_bolt (lvl 1) on npc_dota_hero_snapfire\n";

        List<HeroSpells> heroSpells = heroSpellsParser.getHeroSpells(payload);

        assertEquals(2, heroSpells.size());
        assertEquals("abyssal_underlord_firestorm", heroSpells.get(0).getSpell());
        assertEquals(1, heroSpells.get(0).getCasts());

        assertEquals("rubick_fade_bolt", heroSpells.get(1).getSpell());
        assertEquals(1, heroSpells.get(1).getCasts());
    }
}
