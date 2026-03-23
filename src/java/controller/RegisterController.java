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

            System.out.println("=== Register Request ===");
            System.out.println("Email: " + email);
            System.out.println("Password: " + password);
            System.out.println("FullName: " + fullName);

            // Validation
            if (email == null || email.trim().isEmpty()) {
                request.setAttribute("ERROR", "Email cannot be empty!");
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }

            if (password == null || password.trim().isEmpty()) {
                request.setAttribute("ERROR", "Password cannot be empty!");
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }

            if (password.length() < 6) {
                request.setAttribute("ERROR", "Password must be at least 6 characters!");
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }

            if (fullName == null || fullName.trim().isEmpty()) {
                request.setAttribute("ERROR", "Full name cannot be empty!");
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                request.setAttribute("ERROR", "Invalid email format!");
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }

            System.out.println("Validation passed!");

            UserDAO userDAO = new UserDAO();

            // Check if email already exists
            User existingUser = userDAO.getUserByEmail(email);
            if (existingUser != null) {
                System.out.println("Email already exists!");
                request.setAttribute("ERROR", "Email already registered!");
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }

            System.out.println("Email is unique!");

            // Register new user
            User newUser = new User(
                    null,
                    email,
                    password,
                    "student",
                    LocalDateTime.now(),
                    true
            );

            System.out.println("Attempting to register user...");
            boolean registerSuccess = userDAO.registerUser(newUser);
            System.out.println("Register result: " + registerSuccess);

            if (registerSuccess) {
                System.out.println("User registered successfully!");
                
                // Get the registered user to retrieve their user_id
                User registeredUser = userDAO.getUserByEmail(email);
                
                if (registeredUser != null) {
                    System.out.println("Retrieved user with ID: " + registeredUser.getUserID());
                    
                    // Register student details
                    Date dateOfBirth = null;
                    if (dateOfBirthStr != null && !dateOfBirthStr.isEmpty()) {
                        dateOfBirth = Date.valueOf(dateOfBirthStr);
                    }

                    Student student = new Student(
                            registeredUser,
                            fullName,
                            dateOfBirth,
                            phone != null ? phone : "",
                            address != null ? address : ""
                    );

                    System.out.println("Attempting to register student...");
                    boolean studentSuccess = userDAO.registerStudent(student);
                    System.out.println("Student register result: " + studentSuccess);

                    if (studentSuccess) {
                        System.out.println("Student registered successfully!");
                        request.setAttribute("SUCCESS", "Registration successful! Please login.");
                        url = "login.jsp";
                    } else {
                        System.out.println("Failed to register student!");
                        request.setAttribute("ERROR", "Failed to register student details!");
                    }
                } else {
                    System.out.println("Could not retrieve registered user!");
                    request.setAttribute("ERROR", "Could not retrieve user ID!");
                }
            } else {
                System.out.println("User registration failed!");
                request.setAttribute("ERROR", "Registration failed! Please try again.");
            }

        } catch (Exception e) {
            System.err.println("=== EXCEPTION in RegisterController ===");
            System.err.println("Error type: " + e.getClass().getName());
            System.err.println("Error message: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("ERROR", "An error occurred: " + e.getMessage());
        } finally {
            try {
                request.getRequestDispatcher(url).forward(request, response);
            } catch (ServletException | IOException ex) {
                System.err.println("Error forwarding request: " + ex.getMessage());
                ex.printStackTrace();
            }
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
