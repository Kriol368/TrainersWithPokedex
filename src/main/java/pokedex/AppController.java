package pokedex;

import pokedex.entity.Trainer;
import pokedex.repository.PokemonRepository;
import pokedex.repository.TrainerRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AppController {
    private static java.sql.Connection con = AppService.getConnection();


    public static void viewAllTrainers(TrainerRepository trainerRepository) throws SQLException {
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println(AnsiColor.BLUE.getCode());
        System.out.println(trainerRepository.findBy());
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------");
    }


    public static void viewPokemonByTrainer(PokemonRepository pokemonRepository, TrainerRepository trainerRepository) throws SQLException {
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println(AnsiColor.BLUE.getCode());
        System.out.println("Select the id of the trainer from whom you want to see its Pokemon:");
        AppController.viewAllTrainers(trainerRepository);
        int id = Integer.parseInt(scanner.nextLine());
        Trainer trainer = trainerRepository.findByTrainerID(id);
        System.out.println(AnsiColor.BLUE.getCode());
        System.out.println(pokemonRepository.findAllByTrainer(trainer));
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------");
    }


    public static void register() throws SQLException {
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        PreparedStatement st;
        System.out.println(AnsiColor.BLUE.getCode());
        System.out.println("Introduce your name:");
        String name = scanner.nextLine();
        String query = "INSERT INTO trainer (name) VALUES (?)";
        st = con.prepareStatement(query);
        st.setString(1, name);
        st.executeUpdate();
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------");
    }
}
