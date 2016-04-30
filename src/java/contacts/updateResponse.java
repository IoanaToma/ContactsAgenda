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

@WebServlet(name = "updateResponse", urlPatterns = {"/updateResponse"})
public class updateResponse extends HttpServlet 
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
            
            String oldMobile = request.getParameter("mobileOld");
            Contact myContact = new Contact(request);
            out.println("<html> <body style=\"text-align: center; background-color: #FDE3A7;\">");
            String updateString = "update Contacts set ";
            boolean check = false;
            
            if(!oldMobile.isEmpty())
            {
                if(!myContact.lastName.isEmpty())
                {
                    updateString += "Last='" + myContact.lastName + "'";
                    check = true;
                }
                if(!myContact.firstName.isEmpty())
                    if(check == true)
                        updateString += ", First='" + myContact.firstName + "'";
                    else
                    {
                        updateString += "First='" + myContact.firstName + "'";
                        check = true;
                    }
                if(!myContact.mobile.isEmpty())
                    if(Pattern.matches("[0-9]+", myContact.mobile) && myContact.mobile.length() >= 9)
                        if(check == true)
                            updateString += ", Mobile='" + myContact.mobile + "'";
                        else
                        {
                            updateString += "Mobile='" + myContact.mobile + "'";
                            check = true;
                        }
                    else
                        out.println("<h2> Mobile number should be made up of at least 9 digits (and only digits)! </h2>");
                if(!myContact.home.isEmpty())
                    if(myContact.home.length() >= 9 && Pattern.matches("[0-9]+", myContact.home))
                        if(check == true)
                            updateString += ", Home='" + myContact.home + "'";
                        else
                        {
                            updateString += "Home='" + myContact.home + "'";
                            check = true;
                        }
                    else
                        out.println("<h2> 'Home number' field doesn't match the format! </h2>");
                if(!myContact.mail.isEmpty())
                    if(Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", myContact.mail))
                        if(check == true)
                            updateString += ", Mail='" + myContact.mail + "'";
                        else
                        {
                            updateString += "Mail='" + myContact.mail + "'";
                            check = true;
                        }
                    else
                        out.println("<h2> 'Email' field doesn't match the format! </h2>");
                if(!myContact.address.isEmpty())
                    if(check == true)
                        updateString += ", Address='" + myContact.address + "'";
                    else
                    {
                        updateString += "Address='" + myContact.address + "'";
                        check = true;
                    }
                if(!myContact.city.isEmpty())
                    if(check == true)
                        updateString += ", City='" + myContact.city + "'";
                    else
                    {
                        updateString += "City='" + myContact.city + "'";
                        check = true;
                    }
                if(!myContact.county.isEmpty())
                    if(check == true)
                        updateString += ", County='" + myContact.county + "'";
                    else
                    {
                        updateString += "County='" + myContact.county + "'";
                        check = true;
                    }
                if(!myContact.postal.isEmpty())
                    if(check == true)
                        updateString += ", Postal='" + myContact.postal + "'";
                    else
                    {
                        check = true;
                        updateString += "Postal='" + myContact.postal + "'";
                    }
                
                if(check == false)
                {
                    out.println("<h2> You didn't modify any fields! </h2>");
                    out.println("<a href=\"/servletsAgenda/update\"> Retry </a> </br>");
                }
                else
                {
                    updateString += " where Mobile='" + oldMobile + "'";
                    int result = state.executeUpdate(updateString);
                    if(result == 0)
                    {
                        out.println("<h2> The update command failed. We're sorry for the incovenience. :(</h2>");
                        out.println("<a href=\"/servletsAgenda/update\"> Retry </a> </br>");
                    }
                    else
                        out.println("<h2>Succes!</h2>");
                }
            }
            else
            {
                out.println("<h2> You didn't enter any mobile number! </h2>");
                out.println("<a href=\"/servletsAgenda/update\"> Retry </a> </br>");
            }
               
            String s = "<a href=\"/servletsAgenda/home\"> Return to home page </a>";
            s += "</body> </html>";
            out.println(s);  
        }
        catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e)
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
            out.println(e.toString());
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
