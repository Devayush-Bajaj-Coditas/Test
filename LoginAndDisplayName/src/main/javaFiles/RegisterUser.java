import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class RegisterUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/HTML");
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        long mobile = Long.parseLong(req.getParameter("mobileNumber"));
        String city = req.getParameter("city");
        String edu = req.getParameter("edu");
        String password = req.getParameter("password");

        User user = new User();
        user.setFname(fname);
        user.setLname(lname);
        user.setMobileNumber(mobile);
        user.setCity(city);
        user.setEdu(edu);
        user.setPassword(password);
        UserDao userDao = new UserDao();
        userDao.connection();
        userDao.save();


        getServletContext().getRequestDispatcher("LoginServlet").include(req,resp);



    }
}
