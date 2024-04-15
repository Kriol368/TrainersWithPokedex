package pokedex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pokedex.repository.PokemonRepository;
import pokedex.repository.TrainerRepository;

import java.sql.SQLException;
import java.util.Scanner;

@SpringBootApplication
public class App implements CommandLineRunner {

    private final PokemonRepository pokemonRepository;
    private final TrainerRepository trainerRepository;
    private static int curentScreen = 0;
    private static String trainerName;
    private static int trainerID;

    public App(PokemonRepository pokemonRepository, TrainerRepository trainerRepository) {
        this.pokemonRepository = pokemonRepository;
        this.trainerRepository = trainerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws SQLException {
        int opcion;
        printBanner();
        boolean salir = false;
        while (!salir) {
            printMenu();
            opcion = selectedOption();
            if (curentScreen == 0) {
                switch (opcion) {
                    case 0:
                        salir = true;
                        break;
                    case 1:
                        AppController.viewAllTrainers(trainerRepository);
                        break;
                    case 2:
                        break;
                }
            } else if (curentScreen == 1) {
                switch (opcion) {
                    case 0:
                        salir = true;
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:

                        break;
                }
            }

        }
    }


    public static void printMenu() {
        if (curentScreen == 0) {
            System.out.println(AnsiColor.RED.getCode());
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println(AnsiColor.BLUE.getCode());
            System.out.println("0 Exit | 1 Login | 2 Register");
            System.out.println(AnsiColor.RED.getCode());
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println(AnsiColor.RESET.getCode());
        } else if (curentScreen == 1) {
            System.out.println(AnsiColor.RED.getCode());
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println(AnsiColor.BLUE.getCode());
            System.out.println("0 Logout | 1 My Pokemons | 2 Catch Pokemon | 3 Release Pokemon | 4 Otheer trainers | 5 Find other trainers' Pokemons");
            System.out.println(AnsiColor.RED.getCode());
            System.out.println("------------------------------------------------------------------------------------------");
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
                if (curentScreen == 0 && option < 2 || curentScreen == 1 && option < 5) {
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
