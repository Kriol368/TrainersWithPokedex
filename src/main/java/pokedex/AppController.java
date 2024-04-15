package pokedex;

import pokedex.entity.Trainer;
import pokedex.repository.PokemonRepository;
import pokedex.repository.TrainerRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AppController {
    public static void viewAllTrainers(TrainerRepository trainerRepository) throws SQLException {
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println(AnsiColor.BLUE.getCode());
        trainerRepository.findBy();
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------");
    }


    private static void viewPokemonByTrainer(PokemonRepository pokemonRepository) {

        System.out.println("");
    }


  /*  private static void register() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        PreparedStatement st;
        System.out.println("Name:");
        String name = scanner.nextLine();
        System.out.println("Last name");
        String lastName = scanner.nextLine();
        String query = "INSERT INTO usuarios (nombre,apellidos) VALUES (?,?)";
        st = con.prepareStatement(query);
        st.setString(1, name);
        st.setString(2, lastName);
        st.executeUpdate();
    } */
}
