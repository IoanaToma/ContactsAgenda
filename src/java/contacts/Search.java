package contacts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/Search"})
public class Search extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection connection = null;
        ResultSet result = null;
        Statement state;
    
        try {
            String userName = "root";  
            String password = "";
            String url = "jdbc:mysql://localhost:3306/Agenda";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, userName, password);
            state = (Statement) connection.createStatement();
            
            String lastName = request.getParameter("last");
            String firstName = request.getParameter("first");
            String mobile = request.getParameter("mobile");
            String mail = request.getParameter("mail");
            
            int choice = 0;
            if(!mail.isEmpty()) choice = 4;
            if(!mobile.isEmpty()) choice = 3;
            if(!firstName.isEmpty()) choice = 2;
            if(!lastName.isEmpty()) choice = 1;
            
            String selectString = "";
            out.println("<html> <body style=\"text-align: center; background-color: #FDE3A7;\">");
            if(choice != 0)
            {
                out.println("<table align=\"center\" rules=\"all\" style=\"border:1px solid black;\">");
                String tableHead = "<tr>";
                tableHead += "<th> Rownum </th>";
                tableHead += "<th> Last Name </th>";
                tableHead += "<th> First Name </th>";
                tableHead += "<th> Mobile </th>";
                tableHead += "<th> Home </th>";
                tableHead += "<th> Mail </th>";
                tableHead += "<th> Address </th>";
                tableHead += "<th> City </th>";
                tableHead += "<th> County </th>";
                tableHead += "<th> Postal code </th>";
                tableHead += "</tr>";
                out.println(tableHead);
            }
            int row = 1;
            switch(choice)
            {
                case 1:
                    selectString = "select * from Contacts where Last = \"" + lastName + "\"";
                    result = state.executeQuery(selectString);
                    while(result.next())
                    {
                        Contact c = new Contact(result);
                        out.println(c.toTableString(row++));
                    }
                    out.println("</table> </br>");
                    break;
                case 2:
                    selectString = "select * from Contacts where First = \"" + firstName + "\"";
                    result = state.executeQuery(selectString);
                    while(result.next())
                    {
                        Contact c = new Contact(result);
                        out.println(c.toTableString(row++));
                    }
                    out.println("</table> </br>");
                    break;
                case 3:
                    selectString = "select * from Contacts where Mobile = \"" + mobile + "\"";
                    result = state.executeQuery(selectString);
                    while(result.next())
                    {
                        Contact c = new Contact(result);
                        out.println(c.toTableString(row++));
                    }
                    out.println("</table> </br>");
                    break;
                case 4:
                    selectString = "select * from Contacts where Mail = \"" + mail + "\"";
                    result = state.executeQuery(selectString);
                    while(result.next())
                    {
                        Contact c = new Contact(result);
                        out.println(c.toTableString(row++));
                    }
                    out.println("</table> </br>");
                    break;
                default:
                    out.println("<h2> You didn't fill in any field! </h2>");
                    out.println("<a href=\"/servletsAgenda/search\"> Retry </a> </br>");
                    break;
            }
            out.println("<a href=\"/servletsAgenda/home\"> Return to home page </a>");
            out.println("</body>");
            out.println("</html>");
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
            if(result != null)
                result.close();
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
