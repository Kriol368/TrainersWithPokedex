package pokedex.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pokedex.entity.Trainer;

import java.util.List;

@Component
public interface TrainerRepository extends CrudRepository<Trainer, Integer> {
    Trainer findByTrainerID(int trainerID);

    List<Trainer> findAllByTrainerIDIsNot(int trainerID);

    Trainer findTrainerByName(String name);
}
