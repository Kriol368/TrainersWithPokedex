package pokemon.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Trainer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int trainerID;

    private String name;
    @OneToMany(mappedBy = "trainerID", fetch=FetchType.EAGER)
    private List<Pokemon> pokemons;

    public Trainer() {
    }

    public Trainer(int trainerID, String name) {
        this.trainerID = trainerID;
        this.name = name;
        this.pokemons = new ArrayList<>();
    }

    public int getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(int trainerID) {
        this.trainerID = trainerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
    @Override
    public String toString(){
        return this.trainerID + " - " + this.name;
    }
}
