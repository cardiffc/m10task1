import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    private static String URL = "jdbc:mysql://localhost:3306/skillbox";
    private static String USER = "root";
    private static String PASSWORD = "test";
    private static String QUERY = "Select name from Courses;";
    public static void main(String[] args) {

        try {
            Connection newConnection = DriverManager.getConnection(URL,USER,PASSWORD);
            Statement newStatement = newConnection.createStatement();
            ResultSet myQuery = newStatement.executeQuery(QUERY);
            while (myQuery.next())
            {
                String name = myQuery.getString("name");
                System.out.println(name);
            }
            myQuery.close();
            newStatement.close();
            newConnection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
