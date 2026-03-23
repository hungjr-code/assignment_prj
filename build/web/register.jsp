<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./CSS/style.css"/>
        <title>Register</title>
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
            <form id="register-form" action="MainController" method="post">
                <label>Register New Account</label>
                
                <jstl:if test="${requestScope.ERROR != null}">
                    <div class="error">
                        ${requestScope.ERROR}
                    </div>
                </jstl:if>
                
                <jstl:if test="${requestScope.SUCCESS != null}">
                    <div class="success">
                        ${requestScope.SUCCESS}
                    </div>
                </jstl:if>
                
                <div class="form-row form-element">
                    <label for="txtemail">Email</label>
                    <input id="txtemail" type="email" name="txtemail" required=""/>
                </div>
                
                <div class="form-row form-element">
                    <label for="txtpassword">Password</label>
                    <input id="txtpassword" type="password" name="txtpassword" required=""/>
                </div>
                
                <div class="form-row form-element">
                    <label for="txtfullname">Full Name</label>
                    <input id="txtfullname" type="text" name="txtfullname" required=""/>
                </div>
                
                <div class="form-row form-element">
                    <label for="txtphone">Phone</label>
                    <input id="txtphone" type="tel" name="txtphone"/>
                </div>
                
                <div class="form-row form-element">
                    <label for="txtdateofbirth">Date of Birth</label>
                    <input id="txtdateofbirth" type="date" name="txtdateofbirth"/>
                </div>
                
                <div class="form-row form-element">
                    <label for="txtaddress">Address</label>
                    <input id="txtaddress" type="text" name="txtaddress"/>
                </div>
                
                <input class="form-element" type="submit" name="action" value="register"/>
            </form>
        </main>
        <footer>
            <b>@fpt.edu.vn</b>
        </footer>
    </body>
</html>