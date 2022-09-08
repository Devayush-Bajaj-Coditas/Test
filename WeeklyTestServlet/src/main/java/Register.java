import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/RegisterUser")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        String firstName = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String mobile = req.getParameter("mobileNumber");
        String city = req.getParameter("city");
        String edu = req.getParameter("edu");
        String password = req.getParameter("password");

        boolean flag = true;

        if(!(validateString(firstName))) {
            flag = false;
        }
        if(!(validateString(lname))) {
            flag = false;
        }
        if(!(validateString(city))) {
            flag = false;
        }
        if(!(validateString(edu))) {
            flag = false;
        }
        if(!(validateNumber(mobile))) {
            flag = false;
        }

        if(flag == false) {
            printWriter.println("Invalid Inputs Try Again");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.html");
            requestDispatcher.include(req,resp);
        }
        else {
            try {
                ServletContext servletContext = getServletContext();
                servletContext.setAttribute("name",firstName);
                servletContext.setAttribute("number",mobile);
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weeklytest","root","12345");
                servletContext = getServletContext();
                servletContext.setAttribute("connection",connection);
                PreparedStatement preparedStatement = connection.prepareStatement("insert into userdata values (?,?,?,?,?,?)");
                preparedStatement.setString(1,firstName);
                preparedStatement.setString(2,lname);
                preparedStatement.setString(3,mobile);
                preparedStatement.setString(4,city);
                preparedStatement.setString(5,edu);
                preparedStatement.setString(6,password);
                preparedStatement.executeUpdate();

                printWriter.println("USer registered successfully");

                    printWriter.println("<a href = 'login.html'>Click here to Login</a>");

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public boolean validateString(String formString) {
        formString = formString.toLowerCase();
        for(int i = 0; i < formString.length(); i++) {
            if(formString.charAt(i) >= 'a' && formString.charAt(i) <= 'z') {

            }
            else
                return false;
        }
        return true;
    }
    public boolean validateNumber(String number) {
        for(int i = 0; i < number.length(); i++) {
            if(number.charAt(i) >= '0' && number.charAt(i) <= '9') {

            }
            else
                return false;
        }
        return true;
    }
}
