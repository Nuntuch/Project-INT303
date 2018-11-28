<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

      
        <form action="user?action=active" method="post">
       
            Email : <input type="email" required name="email" />
            Token : <input type="text" required name="token" />
            <input type="submit" value="Active"/>

        </form>

    </body>
</html>
