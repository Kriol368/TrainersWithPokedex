package pokedex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pokedex.entity.Trainer;
import pokedex.repository.PokemonRepository;
import pokedex.repository.TrainerRepository;

import java.util.Scanner;

@SpringBootApplication
public class App implements CommandLineRunner {

    public static int currentScreen = 0;
    public static Trainer trainer = null;
    private static PokemonRepository pokemonRepository;
    private static TrainerRepository trainerRepository;

    public App(PokemonRepository pokemonRepository, TrainerRepository trainerRepository) {
        App.pokemonRepository = pokemonRepository;
        App.trainerRepository = trainerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) {
        int opcion;
        printBanner();
        boolean salir = false;
        while (!salir) {
            printMenu();
            opcion = selectedOption();
            if (currentScreen == 0) {
                switch (opcion) {
                    case 0:
                        salir = true;
                        break;
                    case 1:
                        trainer = AppController.login(trainerRepository);
                        break;
                    case 2:
                        AppController.register(trainerRepository);
                        break;
                }
            } else if (currentScreen == 1) {
                switch (opcion) {
                    case 0:
                        currentScreen = 0;
                        break;
                    case 1:
                        AppController.viewMyPokemons(trainer, pokemonRepository);
                        break;
                    case 2:
                        AppController.catchPokemon(trainer, pokemonRepository);
                        break;
                    case 3:
                        AppController.releasePokemon(trainer, pokemonRepository);
                        break;
                    case 4:
                        AppController.otherTrainers(trainer, trainerRepository);
                        break;
                    case 5:
                        AppController.viewPokemonByTrainer(trainer, trainerRepository, pokemonRepository);
                        break;
                }
            }

        }
    }


    public static void printMenu() {
        if (currentScreen == 0) {
            System.out.println(AnsiColor.RED.getCode());
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(AnsiColor.BLUE.getCode());
            System.out.println("0 Exit | 1 Login | 2 Register");
            System.out.println(AnsiColor.RED.getCode());
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(AnsiColor.RESET.getCode());
        } else if (currentScreen == 1) {
            System.out.println(AnsiColor.RED.getCode());
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(AnsiColor.BLUE.getCode());
            System.out.println("0 Logout " + trainer.getName() + " | 1 My Pokemons | 2 Catch Pokemon | 3 Release Pokemon | 4 Other trainers | 5 View other trainers Pokemons");
            System.out.println(AnsiColor.RED.getCode());
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(AnsiColor.RESET.getCode());
        }

    }

    private static void printBanner() {
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("""
                ██████╗  ██████╗ ██╗  ██╗███████╗██████╗ ███████╗██╗  ██╗
                ██╔══██╗██╔═══██╗██║ ██╔╝██╔════╝██╔══██╗██╔════╝╚██╗██╔╝
                ██████╔╝██║   ██║█████╔╝ █████╗  ██║  ██║█████╗   ╚███╔╝\s
                ██╔═══╝ ██║   ██║██╔═██╗ ██╔══╝  ██║  ██║██╔══╝   ██╔██╗\s
                ██║     ╚██████╔╝██║  ██╗███████╗██████╔╝███████╗██╔╝ ██╗
                ╚═╝      ╚═════╝ ╚═╝  ╚═╝╚══════╝╚═════╝ ╚══════╝╚═╝  ╚═╝
                                                                        \s
                """);
        System.out.println(AnsiColor.RESET.getCode());
    }

    public static int selectedOption() {
        Scanner sc = new Scanner(System.in);
        int option;
        while (true) {
            try {
                option = Integer.parseInt(sc.next());
                if (currentScreen == 0 && option < 3 || currentScreen == 1 && option < 6) {
                    break;
                } else {
                    System.out.println(AnsiColor.RED.getCode());
                    System.out.println("Incorrect option");
                    System.out.println(AnsiColor.RESET.getCode());
                }
            } catch (IllegalArgumentException iae) {
                System.out.println(AnsiColor.RED.getCode());
                System.out.println("Incorrect option");
                System.out.println(AnsiColor.RESET.getCode());
            }
        }
        return option;
    }

}
