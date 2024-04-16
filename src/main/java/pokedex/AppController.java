package pokedex;

import pokedex.entity.Pokemon;
import pokedex.entity.Trainer;
import pokedex.repository.PokemonRepository;
import pokedex.repository.TrainerRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AppController {
    private static final java.sql.Connection con = AppService.getConnection();

    public static void viewPokemonByTrainer(PokemonRepository pokemonRepository, TrainerRepository trainerRepository) {
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println(AnsiColor.BLUE.getCode());
        System.out.println("Select the id of the trainer from whom you want to see its Pokemon:");
        AppController.otherTrainers(trainerRepository);
        int id = Integer.parseInt(scanner.nextLine());
        Trainer trainer = trainerRepository.findByTrainerID(id);
        System.out.println(AnsiColor.BLUE.getCode());
        System.out.println(pokemonRepository.findAllByTrainer(trainer));
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }


    public static void register() throws SQLException {
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
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
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void login(TrainerRepository trainerRepository) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Username:");
        String post = scanner.nextLine();
        PreparedStatement st;
        String query = "SELECT * FROM trainer WHERE name = ?";
        st = con.prepareStatement(query);
        st.setString(1, post);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            App.trainer = new Trainer(rs.getInt(1), rs.getString(2));
            App.curentScreen = 1;
        } else {
            System.out.println("User not found");
        }
    }

    public static void viewMyPokemons(PokemonRepository pokemonRepository) {
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(AnsiColor.BLUE.getCode());
        System.out.println(pokemonRepository.findAllByTrainer(App.trainer));
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void catchPokemon(PokemonRepository pokemonRepository) throws SQLException {
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println(AnsiColor.BLUE.getCode());
        PreparedStatement st;
        System.out.println("Introduce the pokemon name: ");
        String name = scanner.nextLine();
        System.out.println("Introduce the pokemon number: ");
        int number = Integer.parseInt(scanner.nextLine());
        System.out.println("Introduce its first type: ");
        String type1 = scanner.nextLine();
        System.out.println("Introduce its second type (Type - if it doesn't have) :");
        String type2 = scanner.nextLine();
        Pokemon pokemon = new Pokemon(number, name, type1, type2, App.trainer);
        pokemonRepository.save(pokemon);
        System.out.println(name + " successfully caught");
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void releasePokemon(PokemonRepository pokemonRepository) throws SQLException {
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(AnsiColor.BLUE.getCode());
        Scanner scanner = new Scanner(System.in);
        viewMyPokemons(pokemonRepository);
        System.out.println("Introduce the number of the pokemon that you want to release: ");
        int number = Integer.parseInt(scanner.nextLine());
        Pokemon current = pokemonRepository.findByNumber(number);
        System.out.println("Are you sure you want to release your " + current.getName() + " (yes/no)?");
        String response = scanner.nextLine();
        if (response.equals("yes")) {
            PreparedStatement st = con.prepareStatement("DELETE FROM pokemon WHERE number = ?");
            st.setInt(1, current.getNumber());
            st.executeUpdate();
            st.close();
            System.out.println(current.getName() + " was released successfully");
        } else {
            System.out.println("Your " + current.getName() + " was not released");
        }
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void otherTrainers(TrainerRepository trainerRepository){
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(AnsiColor.BLUE.getCode());
        System.out.println(trainerRepository.findAllByTrainerIDIsNot(App.trainer.getTrainerID()));
        System.out.println(AnsiColor.RED.getCode());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }
}
