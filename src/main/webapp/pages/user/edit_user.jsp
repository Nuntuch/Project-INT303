<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit User</title>
    </head>
    <body>
  
      <form action="user?action=edit" method="post">
      
            Email : <input type="email" required name="email" value="${sessionScope.userAccount.email}" /><br>
            Password : <input type="password" required name="password" value="${sessionScope.userAccount.password}" /><br>
            FirstName : <input type="text" required name="firstname" value="${sessionScope.userAccount.firstName}" /><br>
            LastName : <input type="text" required name="lastname" value="${sessionScope.userAccount.lastName}" /><br>
            DOB : <input type="date"  name="dob" value="${sessionScope.userAccount.dob}" /><br>
            Mobile : <input type="text" required name="mobile" value="${sessionScope.userAccount.mobile}" /><br>
            Gender : <input type="text" required name="gender" value="${sessionScope.userAccount.gender}" /><br>
            
            <input type="submit" value="EditUser"/>

        </form>

        </form>
    </body>
</html>
