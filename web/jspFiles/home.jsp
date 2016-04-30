
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home page</title>
        <style>
            body {
                text-align: center;
                background-color: #FDE3A7;
            }
            
        </style>
    </head>
    <body>
        <h1>This is your home page. Please choose what do you want to do next!</h1>
        
        <form method="post">
            <table align="center">
                <tr>
                    <td align="left"><input type="radio" name="Option" value="search" checked>Search contact </td>
                </tr>
                <tr>
                    <td align="left"><input type="radio" name="Option" value="add">Add contact </td>
                </tr>
                <tr>
                    <td align="left"><input type="radio" name="Option" value="update">Update contact </td>
                </tr>
                <tr>
                    <td align="left"><input type="radio" name="Option" value="delete">Delete contact </td>
                </tr>
            </table>
            
            <br>
            <input type="submit" value="Submit">
        </form>
        
        <%
            String option = request.getParameter("Option");
            if(option != null)
                switch(option)
                {
                    case "search":
                        response.sendRedirect("/servletsAgenda/search");
                        break;
                    case "add":
                        response.sendRedirect("/servletsAgenda/add");
                        break;
                    case "update":
                        response.sendRedirect("/servletsAgenda/update");
                        break;
                    case "delete":
                        response.sendRedirect("/servletsAgenda/delete");
                        break;
                }
        %>
    </body>
</html>
