package gg.bayes.challenge.repository;

import gg.bayes.challenge.repository.entity.Hero;
import org.springframework.data.repository.CrudRepository;

public interface HeroRepository extends CrudRepository<Hero, Long> {
    Hero findByHeroName(String heroName);
}
