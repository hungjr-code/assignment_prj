<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./CSS/style.css"/>
        <title>Register Account</title>
    </head>
    <body>
        <header>
            <nav>
                <a href="MainController?action=home">Home</a>
                <a href="MainController?action=gologin">Login</a>
                <a href="MainController?action=goregister">Register</a>
            </nav>
            <form id="search-bar" action="MainController" method="GET">
                <input type="text" name="txtkeyword" value="${param.txtkeyword}" placeholder="What language you want to learn?"/>
                <input type="submit" name="action" value="Search"/>
            </form>
        </header>
        <main>
            <form id="register-form" action="MainController" method="POST">
                <label class="form-title">Register New Account</label>
                
                <jstl:if test="${requestScope.ERROR != null}">
                    <div class="error-message">
                        <p>${requestScope.ERROR}</p>
                    </div>
                </jstl:if>

                <div class="form-row">
                    <label for="txtemail">Email</label>
                    <input id="txtemail" type="email" name="txtemail" required="" value="${param.txtemail}"/>
                </div>
                
                <div class="form-row">
                    <label for="txtpassword">Password</label>
                    <input id="txtpassword" type="password" name="txtpassword" required="" minlength="6"/>
                </div>
                
                <div class="form-row">
                    <label for="txtfullname">Full Name</label>
                    <input id="txtfullname" type="text" name="txtfullname" required="" value="${param.txtfullname}"/>
                </div>
                
                <div class="form-row">
                    <label for="txtphone">Phone (Optional)</label>
                    <input id="txtphone" type="tel" name="txtphone" value="${param.txtphone}"/>
                </div>
                
                <div class="form-row">
                    <label for="txtdateofbirth">Date of Birth (Optional)</label>
                    <input id="txtdateofbirth" type="date" name="txtdateofbirth" value="${param.txtdateofbirth}"/>
                </div>
                
                <div class="form-row">
                    <label for="txtaddress">Address (Optional)</label>
                    <input id="txtaddress" type="text" name="txtaddress" value="${param.txtaddress}"/>
                </div>
                
                <input class="form-element" type="submit" name="action" value="register"/>
                
                <div class="sub-link">
                    <a href="MainController?action=gologin">Already have an account? Login here.</a>
                </div>
            </form>
        </main>
        <footer>
            <b>@fpt.edu.vn</b>
        </footer>
    </body>
</html>