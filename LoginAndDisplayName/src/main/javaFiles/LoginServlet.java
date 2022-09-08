import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("Text/HTML");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<h1>Login</h1>");
        printWriter.println("<input type = 'text' name = nameLogin> ");
        printWriter.println("<input type = 'password' name = passwordLogin>");
        printWriter.println("<input type = 'submit' value = 'Login'>");

        String name = req.getParameter("nameLogin");
        String pass = req.getParameter("passwordLogin");
        User user = new User();
        if(pass.equals(user.getPassword())){
            getServletContext().getRequestDispatcher("WelcomeUser").include(req,resp);
        }
        else{
            printWriter.println("Invalid username or password");
        }


    }
}
