package gg.bayes.challenge.service;

import gg.bayes.challenge.repository.entity.Hero;
import gg.bayes.challenge.repository.entity.Match;
import gg.bayes.challenge.rest.model.HeroItems;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class HeroItemParser {

    private final String ITEM_ = "item_";

    public Set<Hero> getHeroItems(String payload, Match match) {
        String[] events = Parser.splitPayload(payload);
        Set<HeroItems> heroItems = new HashSet<>();
        Set<Hero> heros = new HashSet<>();
        for (String event : events) {
            String[] tokens = event.split("\\s+");
            Hero hero = new Hero();
            hero.setMatch(match);
            hero.setHeroName(Parser.extractHeroName(tokens[1]));

            HeroItems heroItem = new HeroItems();
            heroItem.setItem(extractHeroItem(tokens));
            heroItem.setTimestamp(getItemTimestamp(tokens[0]));
            heroItem.setHero(hero);
            heroItems.add(heroItem);

            hero.setHeroItems(heroItems);
            heros.add(hero);
        }
        return heros;
    }

    private String extractHeroItem(String[] heroItems) {
        return Arrays.stream(heroItems)
                .filter(heroItem -> heroItem.startsWith(ITEM_))
                .findFirst()
                .map(heroItem -> heroItem.substring(5, heroItem.length()))
                .orElse("");
    }

    private Long getItemTimestamp(String timestamp) {
        if (timestamp.isEmpty())
            return 0L;
        String time = StringUtils.substringBetween(timestamp, "[", "]");
        return getTimeInMillis(time);
    }

    private Long getTimeInMillis(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = sdf.parse("1970-01-01 " + time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }
}
