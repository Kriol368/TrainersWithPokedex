package pokedex.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pokedex.entity.Pokemon;
import pokedex.entity.Trainer;

import java.util.List;

@Component
public interface TrainerRepository extends CrudRepository<Trainer, Integer> {
    List<Trainer> findAllByName(String name);
    List<Trainer> findBy();
    Trainer findByTrainerID(int trainerID);
}
