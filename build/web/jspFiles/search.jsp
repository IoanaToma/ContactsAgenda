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
        <h1>Fill in one of the following fields!</h1>
        
        <form action="Search" method="post">
            <table align="center">
                <tr>
                    <td align="left"> Last name: </td>
                    <td align="left"> <input type="text" name="last" value=""> </td>
                </tr>
                <tr>
                    <td align="left"> First name: </td>
                    <td align="left"> <input type="text" name="first" value=""> </td>
                </tr>
                <tr>
                    <td align="left"> Mobile: </td>
                    <td align="left"> <input type="text" name="mobile" value=""> </td>
                </tr>
                <tr>
                    <td align="left"> Email: </td>
                    <td align="left"> <input type="text" name="mail" value=""> </td>
                </tr>
            </table>
            
            <br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>