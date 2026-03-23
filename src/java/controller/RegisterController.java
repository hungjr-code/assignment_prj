/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import dto.Student;
import dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "register.jsp";
        
        try {
            String email = request.getParameter("txtemail");
            String password = request.getParameter("txtpassword");
            String fullName = request.getParameter("txtfullname");
            String phone = request.getParameter("txtphone");
            String dateOfBirthStr = request.getParameter("txtdateofbirth");
            String address = request.getParameter("txtaddress");

            // --- Validation ---
            if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty() || fullName == null || fullName.trim().isEmpty()) {
                request.setAttribute("ERROR", "Email, Password, and Full Name must not be empty.");
            } else if (password.length() < 6) {
                request.setAttribute("ERROR", "Password must be at least 6 characters long.");
            } else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                request.setAttribute("ERROR", "Invalid email format.");
            } else {
                // --- Logic ---
                UserDAO dao = new UserDAO();
                if (dao.getUserByEmail(email) != null) {
                    request.setAttribute("ERROR", "This email is already registered.");
                } else {
                    // 1. Tạo user
                    User newUser = new User(null, email, password, "student", LocalDateTime.now(), true);
                    boolean userCreated = dao.registerUser(newUser);

                    if (userCreated) {
                        // 2. Lấy lại user vừa tạo để có user_id
                        User createdUser = dao.getUserByEmail(email);
                        // 3. Tạo student
                        Date dateOfBirth = (dateOfBirthStr != null && !dateOfBirthStr.isEmpty()) ? Date.valueOf(dateOfBirthStr) : null;
                        Student student = new Student(createdUser, fullName, dateOfBirth, phone, address);
                        boolean studentCreated = dao.registerStudent(student);

                        if (studentCreated) {
                            request.setAttribute("SUCCESS", "Registration successful! Please log in.");
                            url = "login.jsp"; // Chuyển đến trang login khi thành công
                        } else {
                            // Xử lý lỗi: Xóa user đã tạo nếu không tạo được student
                            dao.deleteUser(createdUser.getUserID());
                            request.setAttribute("ERROR", "Failed to register student details.");
                        }
                    } else {
                        request.setAttribute("ERROR", "User registration failed. Please try again.");
                    }
                }
            }
        } catch (Exception e) {
            log("Error at RegisterController: " + e.toString());
            request.setAttribute("ERROR", "An error occurred during registration.");
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
