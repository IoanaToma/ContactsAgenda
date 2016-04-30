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
        <h1>Fill in the following fields!</h1>
        
        <form action="Add" method="post">
            <table align="center">
                <tr>
                    <td align="left"> Last name*: </td>
                    <td align="left"> <input type="text" name="last" value=""> </td>
                </tr>
                <tr>
                    <td align="left"> First name*: </td>
                    <td align="left"> <input type="text" name="first" value=""> </td>
                </tr>
                <tr>
                    <td align="left"> Mobile*: </td>
                    <td align="left"> <input type="text" name="mobile" value=""> </td>
                </tr>
                <tr>
                    <td align="left"> Home number: </td>
                    <td align="left"> <input type="text" name="home" value=""> </td>
                </tr>
                <tr>
                    <td align="left"> Email*: </td>
                    <td align="left"> <input type="text" name="mail" value=""> </td>
                </tr>
                <tr>
                    <td align="left"> Address: </td>
                    <td align="left"> <input type="text" name="address" value=""> </td>
                </tr>
                <tr>
                    <td align="left"> City: </td>
                    <td align="left"> <input type="text" name="city" value=""> </td>
                </tr>
                <tr>
                    <td align="left"> County: </td>
                    <td align="left"> <input type="text" name="county" value=""> </td>
                </tr>
                <tr>
                    <td align="left"> Postal code: </td>
                    <td align="left"> <input type="text" name="postal" value=""> </td>
                </tr>
            </table>
            
            <br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>