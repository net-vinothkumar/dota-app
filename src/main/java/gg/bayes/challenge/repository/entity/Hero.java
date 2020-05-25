package gg.bayes.challenge.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gg.bayes.challenge.rest.model.HeroItems;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String heroName;

    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "hero",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<HeroItems> heroItems;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", referencedColumnName = "id")
    private Match match;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hero)) return false;
        Hero hero = (Hero) o;
        return Objects.equals(getId(), hero.getId()) &&
                Objects.equals(getHeroName(), hero.getHeroName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getHeroName());
    }
}
