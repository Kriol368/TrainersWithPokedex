package pokemon.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pokemon.entity.Pokemon;
import pokemon.entity.Trainer;

import java.util.List;

@Component
public interface TrainerRepository extends CrudRepository<Trainer, Integer> {
    List<Trainer> findAllByName(String name);
    Trainer findByPokemonsContaining(Pokemon pokemon);
}
