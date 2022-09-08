import javax.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("RegisterUsee")
public class UserDao {

    Connection con = null;
    void connection(){
        try {
            Class.forName("com.mysql.jdbc.driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/weeklytest","root","12345");




        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    void save()  {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement("insert into userdata values (?,?,?,?,?,?)");

        User user = new User();
        preparedStatement.setString(1, user.getFname());
        preparedStatement.setString(2, user.getLname());
        preparedStatement.setLong(3, user.getMobileNumber());
        preparedStatement.setString(4, user.getCity());
        preparedStatement.setString(5, user.getEdu());
        preparedStatement.setString(6, user.getPassword());
        preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
