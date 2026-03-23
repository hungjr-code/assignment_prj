<%-- 
    Document   : dashboard
    Created on : Mar 23, 2026, 12:25:35 AM
    Author     : kAwa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<!DOCTYPE html>
<jstl:if test="${sessionScope.USER.role!='student'}">
    <jstl:redirect url="MainController"/>
</jstl:if>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./CSS/style.css"/>
        <title>Dashboard</title>
    </head>
    <body>
        <header>
            <b>Welcome ${sessionScope.USER.fullName}</b>
            <nav>
                <a href="MainController?action=godash">Dashboard</a>
                <a href="MainController?action=logout">Logout</a>
            </nav>
        </header>
        <main>
            
        </main>
        <footer>
            <b>@fpt.edu.vn</b>
        </footer>
    </body>
</html>
