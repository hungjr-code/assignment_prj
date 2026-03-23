<%-- 
    Document   : index
    Created on : Mar 22, 2026, 5:52:50 PM
    Author     : kAwa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./CSS/style.css"/>
        <title>Language Center</title>
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
            <h1>Our Courses</h1>
            <div class="course-container">
                <c:if test="${not empty requestScope.COURSE_LIST}">
                    <c:forEach var="course" items="${requestScope.COURSE_LIST}">
                        <div class="course-card">
                            <h3>${course.courseName}</h3>
                            <p>${course.description}</p>
                            <p><strong>Language:</strong> ${course.language}</p>
                            <p><strong>Level:</strong> ${course.level}</p>
                            <p><strong>Fee:</strong> ${course.fee} VND</p>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${empty requestScope.COURSE_LIST}">
                    <p>No courses are available at this moment. Please check back later!</p>
                </c:if>
            </div>
        </main>
        <footer>
            <b>@fpt.edu.vn</b>
        </footer>
    </body>
</html>
