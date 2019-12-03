import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    private static String URL = "jdbc:mysql://localhost:3306/skillbox";
    private static String USER = "root";
    private static String PASSWORD = "test";

    /**Выбираем количество студентов (оно явно равно числу продаж), делим число продаж на количество месяцев, за которое
    продажи были осуществлены и группируем по имени курса
     */
    private static String QUERY = "select course_name as name, count(student_name) / max(month(subscription_date))" +
            " as avg_sale from PurchaseList group by course_name;\n";

    public static void main(String[] args) {

        try {
            Connection newConnection = DriverManager.getConnection(URL,USER,PASSWORD);
            Statement newStatement = newConnection.createStatement();
            ResultSet myQuery = newStatement.executeQuery(QUERY);
            while (myQuery.next())
            {
                String courseName = myQuery.getString("name");
                String averageSale = myQuery.getString("avg_sale");

                System.out.println(courseName + " - " + averageSale);
            }
            myQuery.close();
            newStatement.close();
            newConnection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
