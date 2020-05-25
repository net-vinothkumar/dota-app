package gg.bayes.challenge.service;

public class Parser {
    private static final String NPC_DOTA = " npc_dota_";
    private static final String NPC_DOTA_HERO = " npc_dota_hero";
    private static final int NPC_DOTA_LENGTH = 10;
    private static final int NPC_DOTA_HERO_LENGTH = 15;
    private static final String NEW_LINE_PATTERN = "\\r?\\n";

    public static String extractHeroNameFromEvent(String event, String KILLED_BY) {
        String npc_heroName = event.substring(event.indexOf(KILLED_BY) + KILLED_BY.length(), event.length());
        if (npc_heroName.startsWith(NPC_DOTA_HERO)) {
            return npc_heroName.substring(NPC_DOTA_HERO_LENGTH, npc_heroName.length());
        } else if (npc_heroName.startsWith(NPC_DOTA)) {
            return npc_heroName.substring(NPC_DOTA_LENGTH, npc_heroName.length());
        }
        return "";
    }

    public static String extractHeroName(String heroNameStartWithNPC) {
        if (heroNameStartWithNPC.startsWith(NPC_DOTA_HERO)) {
            return heroNameStartWithNPC.substring(NPC_DOTA_HERO_LENGTH, heroNameStartWithNPC.length());
        } else if (heroNameStartWithNPC.startsWith(NPC_DOTA)) {
            return heroNameStartWithNPC.substring(NPC_DOTA_LENGTH, heroNameStartWithNPC.length());
        }
        return "";
    }

    public static String[] splitPayload(String payload) {
        return payload.split(NEW_LINE_PATTERN);
    }
}
