package utils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseReader {

    private final static String URL = "jdbc:postgresql://localhost:5432/postgres";

    private final static String USERNAME = "postgres";

    private final static String PASSWORD = "Qwerty123";

    private final static String SELECT_QUERY = "select * from names where id =?";

    private final static String INSERT_QUERY = "insert into names values(?,?,?)";

    private final static String DELETE_QUERY = "delete from names where id =?";

    private final static String UPDATE_QUERY = "update names set name=? where id =?";



    public static List<String> getNamesFromDB() {

        List<String> names = new ArrayList<>();

       try (Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD)) {

           PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
           preparedStatement.setInt(1,4);
           ResultSet resultSet= preparedStatement.executeQuery();

       while (resultSet.next()) {
           String name = resultSet.getString("name");
           String surname= resultSet.getString("surname");
           names.add(name);
           names.add(surname);
       }
       } catch (SQLException exception){
           throw new RuntimeException("Please check connection");
       }
        return names;
    }

    public static void insertToDB() {

        try (Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setInt(1,6);
            preparedStatement.setString(2,"Roberto");
            preparedStatement.setString(3,"Carlos");
            preparedStatement.executeUpdate();

        } catch (SQLException exception){
            throw new RuntimeException("Please check connection");
        }
    }

    public static void updateDBData() {

        try (Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1,"Shmarlos");
            preparedStatement.setInt(2,6);
            preparedStatement.executeUpdate();

        } catch (SQLException exception){
            throw new RuntimeException("Please check connection");
        }
    }


    public static void deleteDBData() {

        try (Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setInt(1,6);
            preparedStatement.executeUpdate();

        } catch (SQLException exception){
            throw new RuntimeException("Please check connection");
        }
    }
}
