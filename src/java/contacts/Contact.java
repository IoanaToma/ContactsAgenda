package contacts;

import java.sql.*; 
import javax.servlet.http.*; 

public class Contact {
    String lastName;
    String firstName;
    String mobile;
    String home;
    String mail;
    String address;
    String city;
    String county;
    String postal;
    
    Contact(HttpServletRequest request)
    {
        lastName = request.getParameter("last");
        firstName = request.getParameter("first");
        mobile = request.getParameter("mobile");
        home = request.getParameter("home");
        mail = request.getParameter("mail");
        address = request.getParameter("address");
        city = request.getParameter("city");
        county = request.getParameter("county");
        postal = request.getParameter("postal");
    }
    
    Contact(ResultSet result)
    {   
        try{
            lastName = result.getString(1);
            firstName = result.getString(2);
            mobile = result.getString(3);
            home = result.getString(4);
            mail = result.getString(5);
            address = result.getString(6);
            city = result.getString(7);
            county = result.getString(8);
            postal = result.getString(9);
        }
        catch(SQLException e)
        {}
    }
    
    String toTableString(int rowNumber)       
    {
        String show = "";
        show += "<tr>";
        show += "<td>" + rowNumber + "</td>";
        show += "<td>" + lastName + "</td>";
        show += "<td>" + firstName + "</td>";
        show += "<td>" + mobile + "</td>";
        show += "<td>" + home + "</td>";
        show += "<td>" + mail + "</td>";
        show += "<td>" + address + "</td>";
        show += "<td>" + city + "</td>";
        show += "<td>" + county + "</td>";
        show += "<td>" + postal + "</td>";
        show += "</tr>";
        
        return show;
    }
}
