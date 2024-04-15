package pokemon.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pokemon.entity.Pokemon;
import pokemon.entity.Trainer;

import java.util.List;

@Component
public interface PokemonRepository extends CrudRepository<Pokemon, Integer> {
    List<Pokemon> findAllByTrainer(Trainer trainer);
}
