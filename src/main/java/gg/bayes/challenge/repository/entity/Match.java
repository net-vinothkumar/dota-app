package gg.bayes.challenge.repository.entity;

import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "match",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<HeroKills> heroKills;

    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "match",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Hero> hero;
}
