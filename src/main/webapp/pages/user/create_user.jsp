<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User</title>
    </head>
    <body>
        <!--<form action="/Project-INT303/user" method="post">-->
        <form action="user?action=create" method="post">
            <!--<input type="hidden" name="action" value="createuser"/>-->
            Email : <input type="email" required name="email" /><br>
            Password : <input type="password" required name="password" /><br>
            FirstName : <input type="text" required name="firstname" /><br>
            LastName : <input type="text" required name="lastname" /><br>
            DOB : <input type="date"  name="dob" /><br>
            Mobile : <input type="text" required name="mobile" /><br>
            Gender : <input type="text" required name="gender" /><br>
            
            <input type="submit" value="Create"/>

        </form>
    </body>
</html>
