package gg.bayes.challenge.repository;

import gg.bayes.challenge.repository.entity.Match;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long> {
}
