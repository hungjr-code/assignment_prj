<%-- 
    Document   : login
    Created on : Mar 22, 2026, 6:09:55 PM
    Author     : kAwa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./CSS/style.css"/>
        <title>Login</title>
    </head>
    <body>
        <header>
            <nav>
                <a href="MainController?action=home">Home</a>
                <a href="MainController?action=gologin">Login</a>
                <a href="MainController?action=goregister">Register</a>
            </nav>
            <form id="search-bar">
                <input type="text" name="txtkeyword" value="${param.txtkeyword}" placeholder="What language you want to learn?"/>
                <input type="submit" name="action" value="Search"/>
            </form>
        </header>
        <main>
            <form id="login-form" action="MainController" method="post">
                <label>Login</label>
                <div class="form-row form-element">
                    <label for="login-email">Email</label>
                    <input id="login-email" type="text" name="txtemail" required=""/>
                </div>
                <div class="form-row form-element">
                    <label for="login-email">Password</label>
                    <input id="login-password" type="password" name="txtpassword" required=""/>
                </div>
                <input class="form-element" type="submit" name="action" value="Login"/>
                <jstl:if test="${requestScope.ERROR!=null}">
                    <strong>${requestScope.ERROR}</strong>
                </jstl:if>
            </form>
        </main>
        <footer>
            <b>@fpt.edu.vn</b>
        </footer>
    </body>
</html>
