package pokedex;

import pokedex.entity.Pokemon;
import pokedex.entity.Trainer;
import pokedex.repository.PokemonRepository;
import pokedex.repository.TrainerRepository;

import java.util.Scanner;

public class AppController {

    public static void viewPokemonByTrainer(Trainer trainer, TrainerRepository trainerRepository, PokemonRepository pokemonRepository) {
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println(AnsiColor.BLUE.getCode());
        System.out.println("Select the id of the trainer from whom you want to see its Pokemon:");
        AppController.otherTrainers(trainer, trainerRepository);
        int id = Integer.parseInt(scanner.nextLine());
        Trainer current = trainerRepository.findByTrainerID(id);
        System.out.println(AnsiColor.BLUE.getCode());
        System.out.println(pokemonRepository.findAllByTrainer(current));
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }


    public static void register(TrainerRepository trainerRepository) {
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println(AnsiColor.BLUE.getCode());
        System.out.println("Introduce your name:");
        String name = scanner.nextLine();
        Trainer current = new Trainer(name);
        trainerRepository.save(current);
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static Trainer login(TrainerRepository trainerRepository) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Username:");
        String current = scanner.nextLine();
        Trainer currentTrainer = trainerRepository.findTrainerByName(current);
        App.currentScreen = 1;
        return currentTrainer;
    }

    public static void viewMyPokemons(Trainer trainer, PokemonRepository pokemonRepository) {
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(AnsiColor.BLUE.getCode());
        System.out.println(pokemonRepository.findAllByTrainer(trainer));
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void catchPokemon(Trainer trainer, PokemonRepository pokemonRepository) {
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println(AnsiColor.BLUE.getCode());
        System.out.println("Introduce the pokemon name: ");
        String name = scanner.nextLine();
        System.out.println("Introduce the pokemon number: ");
        int number = Integer.parseInt(scanner.nextLine());
        System.out.println("Introduce its first type: ");
        String type1 = scanner.nextLine();
        System.out.println("Introduce its second type (Type - if it doesn't have) :");
        String type2 = scanner.nextLine();
        Pokemon pokemon = new Pokemon(number, name, type1, type2, trainer);
        pokemonRepository.save(pokemon);
        System.out.println(name + " successfully caught");
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void releasePokemon(Trainer trainer, PokemonRepository pokemonRepository) {
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(AnsiColor.BLUE.getCode());
        Scanner scanner = new Scanner(System.in);
        viewMyPokemons(trainer, pokemonRepository);
        System.out.println("Introduce the number of the pokemon that you want to release: ");
        int number = Integer.parseInt(scanner.nextLine());
        Pokemon current = pokemonRepository.findByNumber(number);
        System.out.println("Are you sure you want to release your " + current.getName() + " (yes/no)?");
        String response = scanner.nextLine();
        if (response.equals("yes")) {
            pokemonRepository.delete(current);
            System.out.println(current.getName() + " was released successfully");
        } else {
            System.out.println("Your " + current.getName() + " was not released");
        }
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void otherTrainers(Trainer trainer, TrainerRepository trainerRepository) {
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(AnsiColor.BLUE.getCode());
        System.out.println(trainerRepository.findAllByTrainerIDIsNot(trainer.getTrainerID()));
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }
}
