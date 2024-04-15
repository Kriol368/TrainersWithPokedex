package pokemon;
import java.sql.SQLException;
public class AppService {

    static java.sql.Connection connection;

    public static java.sql.Connection getConnection() {
        String host = "jdbc:sqlite:src/main/resources/pokemon.sqlite";
        if (connection == null) {
            try {
                connection = java.sql.DriverManager.getConnection(host);
            } catch (SQLException sql) {
                System.out.println(sql.getMessage());
                System.exit(0);
            }
        }
        return connection;
    }
}
