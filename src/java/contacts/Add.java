package contacts;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/Add"})
public class Add extends HttpServlet 
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
            
            Contact myContact = new Contact(request);
            out.println("<html> <body style=\"text-align: center; background-color: #FDE3A7;\">");
            String insertString = "insert into Contacts (Last,First,Mobile,Home,Mail,Address,City,County,Postal) values ('";
            insertString += myContact.lastName + "','" + myContact.firstName + "','";
            insertString += myContact.mobile + "','" + myContact.home + "','";
            insertString += myContact.mail + "','" + myContact.address + "','";
            insertString += myContact.city + "','" + myContact.county + "','" + myContact.postal + "')";
            
            boolean check = false;
            int result = -1;
            if(!myContact.lastName.isEmpty())
                if(!myContact.firstName.isEmpty())
                    if(!myContact.mobile.isEmpty())
                        if(!myContact.mail.isEmpty())
                            if(Pattern.matches("[0-9]+", myContact.mobile) && myContact.mobile.length() >= 9)
                                if(Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", myContact.mail))
                                {   
                                    if(!myContact.home.isEmpty() && (myContact.home.length() < 9 || !Pattern.matches("[0-9]+", myContact.home)))
                                        out.println("<h2> 'Home number' field doesn't match the format! </h2>");
                                    else
                                    {
                                        check = true;
                                        result = state.executeUpdate(insertString);
                                    }
                                }
                                else
                                    out.println("<h2> 'Email' field doesn't match the format! </h2>");
                            else
                                out.println("<h2> Mobile number should be made up of at least 9 digits (and only digits)! </h2>");
                        else
                            out.println("<h2> 'Mail' field is mandatory! </h2>");
                    else
                        out.println("<h2> 'Mobile' field is mandatory! </h2>");
                else
                    out.println("<h2> 'First Name' field is mandatory! </h2>");
            else
                out.println("<h2> 'Last Name' field is mandatory! </h2>");
            
            if(check == true && result != 0)
                out.println("<h2> Succes! </h2>");
            else
            {   
                if(result == 0)
                    out.println("<h2> The insert command failed. We're sorry for the incovenience. :( </h2>");
                out.println("<a href=\"/servletsAgenda/add\"> Retry </a> </br>");
            }
            
            String s = "<a href=\"/servletsAgenda/home\"> Return to home page </a>";
            s += "</body> </html>";
            out.println(s);  
        }
        catch(MySQLIntegrityConstraintViolationException e)
        {
            String err = "<html> <body style=\"text-align: center; background-color: #FDE3A7;\">";
            err += "<h2> The mobile number you tried to add already exists in your agenda! </h2>";
            err += "<a href=\"/servletsAgenda/add\"> Retry </a> </br>";
            err += "<a href=\"/servletsAgenda/home\"> Return to home page </a>";
            err += "</body> </html>";
            out.println(err);
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
