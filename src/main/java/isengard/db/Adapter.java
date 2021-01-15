package isengard.db;

import java.sql.*;

public class Adapter {

    private static int role;

    private static int id;

    public static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/isengardbookdb", "guest", "pass");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static boolean login(String login, String pass) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT id, nazwa, haslo, rola FROM uzytkownicy");
        while (rs.next()) {
            if (rs.getString(2).equals(login)) {
                if (rs.getString(3).equals(pass)) {
                    id = rs.getInt(1);
                    role = rs.getInt(4);
                    System.out.println(role);
                    connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/isengardbookdb", login, pass);
                    return true;
                }
            }
        }
        return false;
    }

    public static int getRole() {
        return role;
    }

    public static int getId() {
        return id;
    }

    public static void backup() {

    }


    public static boolean register(String log, String pass) throws SQLException {
        connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/isengardbookdb", "root", "root");
        if (log.matches("[a-zA-Z0-9]+") && pass.matches("[a-zA-Z0-9]+")) {

            Statement statement1 = connection.createStatement();
            ResultSet rs = statement1.executeQuery("SELECT nazwa FROM uzytkownicy");
            while (rs.next()) {
                if (rs.getString(1).equals(log)) {
                    return false;
                }
            }

            PreparedStatement statement = connection.prepareStatement("CREATE USER ? IDENTIFIED BY ?;");
            statement.setString(1, log);
            statement.setString(2, pass);
            statement.executeQuery();


            statement = connection.prepareStatement("GRANT SELECT ON isengardbookdb.* TO ?;");
            statement.setString(1, log);
            statement.executeQuery();

            statement = connection.prepareStatement("GRANT UPDATE ON isengardbookdb.recenzje TO ?;");
            statement.setString(1, log);
            statement.executeQuery();

            statement = connection.prepareStatement("INSERT INTO uzytkownicy (nazwa,haslo,rola,stanKonta) VALUES (?,?,0,0)");
            statement.setString(1, log);
            statement.setString(2, pass);
            statement.executeQuery();

            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/isengardbookdb", "guest", "pass");
            System.out.println("User registered");
            return true;
        }
        else return false;
    }

    public static ResultSet execute(String text) throws SQLException {
      //connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/isengardbookdb", "root", "root");
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(text);
      return rs;
    }
}
