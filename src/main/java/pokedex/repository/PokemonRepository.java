package pokedex.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pokedex.entity.Pokemon;
import pokedex.entity.Trainer;

import java.util.List;

@Component
public interface PokemonRepository extends CrudRepository<Pokemon, Integer> {
    List<Pokemon> findAllByTrainer(Trainer trainer);
}
