package gg.bayes.challenge.repository;

import gg.bayes.challenge.rest.model.HeroItems;
import org.springframework.data.repository.CrudRepository;

public interface HeroItemsRepository extends CrudRepository<HeroItems, Long> {
}
