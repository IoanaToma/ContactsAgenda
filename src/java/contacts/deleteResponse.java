
package contacts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "deleteResponse", urlPatterns = {"/deleteResponse"})
public class deleteResponse extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection connection = null;
        Statement state;
        
        try {
            String userName = "root";  
            String password = "";
            String url = "jdbc:mysql://localhost:3306/Agenda";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, userName, password);
            state = (Statement) connection.createStatement();
            
            out.println("<html> <body style=\"text-align: center; background-color: #FDE3A7;\">");
            
            String mobile = request.getParameter("mobile");
            String deleteString = "delete from Contacts where mobile=\"" + mobile + "\"";
            
            if(mobile.isEmpty())
            {
                out.println("<h2> You didn't choose any mobile number! </h2>");
                out.println("<a href=\"/servletsAgenda/delete\"> Retry </a> </br>");
            }
            else
            {
                int result = state.executeUpdate(deleteString);
                if(result == 0)
                {
                    out.println("<h2> The contact you've chosen could not be deleted! </h2>");
                    out.println("<a href=\"/servletsAgenda/delete\"> Retry </a> </br>");
                }
                else
                    out.println("<h2> Succes! </h2>");
            }
            String s = "<a href=\"/servletsAgenda/home\"> Return to home page </a>";
            s += "</body> </html>";
            out.println(s); 
            
        }
        catch(Exception e)
        {
            String err = "<html> <body style=\"text-align: center; background-color: #FDE3A7;\">";
            err += "<h2> An error occured. We're sorry for the inconvenience :( </h2>";
            err += "<a href=\"/servletsAgenda/home\"> Return to home page </a>";
            err += "</body> </html>";
            out.println(err);
        }
        finally
        {
            out.flush();
            if(connection != null)
                connection.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
