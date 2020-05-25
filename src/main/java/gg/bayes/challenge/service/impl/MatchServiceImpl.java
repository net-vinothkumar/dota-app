package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.repository.HeroKillsRepository;
import gg.bayes.challenge.repository.MatchRepository;
import gg.bayes.challenge.repository.entity.Match;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.service.HeroItemParser;
import gg.bayes.challenge.service.HeroKillsParser;
import gg.bayes.challenge.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@Transactional
public class MatchServiceImpl implements MatchService {

    @Autowired
    private HeroKillsParser heroKillsParser;

    @Autowired
    private HeroItemParser heroItemParser;

    @Autowired
    public MatchRepository matchRepository;

    @Autowired
    public HeroKillsRepository heroKillsRepository;

    @Autowired
    public MatchServiceImpl() {
    }

    @Override
    public Long ingestMatch(String payload) {
        Match match = new Match();
        Set<HeroKills> heroKills = heroKillsParser.getHeroKills(payload, match);
        match.setHeroKills(heroKills);
        match = matchRepository.save(match);
        return match.getId();
    }

    @Override
    public List<HeroKills> getHeroKills(Long matchId) {
        Match match = new Match();
        match.setId(matchId);
        return heroKillsRepository.findByMatch(match);
    }

    @Override
    public List<HeroItems> getHeroItems(Long matchId, String heroName) {
        //TODO fix it
        return new ArrayList<>();
    }
}
