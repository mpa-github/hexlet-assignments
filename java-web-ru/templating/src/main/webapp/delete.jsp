<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Example application | Users</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <c:set value="${requestScope.get('user').get('id')}" var="userID"/>
        <c:set value="${requestScope.get('user').get('firstName')}" var="userFirstName"/>
        <c:set value="${requestScope.get('user').get('lastName')}" var="userLastName"/>
        <form action="/users/delete?id=${userID}" method="post">
            <div class="form-group">
                <input type="text" readonly class="form-control-plaintext" id="staticText"
                       value="Вы действительно хотите удалить пользователя ${userFirstName} ${userLastName}?">
            </div>
            <button type="submit" class="btn btn-danger">Удалить</button>
        </form>
    </div>
</body>
</html>
<!-- END -->
