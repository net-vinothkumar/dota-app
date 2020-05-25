package gg.bayes.challenge.repository;

import gg.bayes.challenge.repository.entity.Match;
import gg.bayes.challenge.rest.model.HeroKills;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HeroKillsRepository extends CrudRepository<HeroKills, Long> {

    public List<HeroKills> findByMatch(Match match);
}
