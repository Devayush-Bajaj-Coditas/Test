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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/LoginUser")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        String userNameInput = req.getParameter("userName");
        String passwordInput = req.getParameter("password");
        ServletContext servletContext = getServletContext();
        Connection connection = (Connection) servletContext.getAttribute("connection");
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from userdata");
            while (resultSet.next()) {
                if(userNameInput.equals(resultSet.getString(1)) && passwordInput.equals(resultSet.getString(6))) {
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("display");
                    requestDispatcher.forward(req,resp);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
