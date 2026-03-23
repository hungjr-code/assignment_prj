/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbutils.DBUtil;
import dto.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class UserDAO {

    Connection cn;

    public UserDAO() throws ClassNotFoundException, SQLException {
        this.cn = DBUtil.getConnection();
    }

    public User getUser(String email, String password) throws SQLException {
        User user = null;

        String sql = "select [user_id], [role], [created_at]\n"
                + "from [dbo].[users]\n"
                + "where [email] = ? and [password_hash] = ? collate SQL_Latin1_General_CP1_CI_AI";
        PreparedStatement st = cn.prepareStatement(sql);
        st.setString(1, email);
        st.setString(2, password);
        ResultSet table = st.executeQuery();
        if (table != null && table.next()) {
            String userID = table.getString("user_id");
            String role = table.getString("role");
            LocalDateTime createdAt = table.getTimestamp("created_at").toLocalDateTime();
            boolean active = true;
            user = new User(userID, email, password, role, createdAt, active);
            switch (role) {
                case "admin":
                    user = this.getAdmin(user);
                    break;
                case "student":
                    user = this.getStudent(user);
                    break;
                case "lecturer":
                    user = this.getLecturer(user);
                    break;
                default:
                    throw new AssertionError();
            }
        }
        return user;
    }

    public User getUserByEmail(String email) throws SQLException {
        User user = null;
        String sql = "select [user_id], [email], [password_hash], [role], [created_at]\n"
                + "from [dbo].[users]\n"
                + "where [email] = ?";
        PreparedStatement st = cn.prepareStatement(sql);
        st.setString(1, email);
        ResultSet table = st.executeQuery();
        if (table != null && table.next()) {
            String userID = table.getString("user_id");
            String passwordHash = table.getString("password_hash");
            String role = table.getString("role");
            LocalDateTime createdAt = table.getTimestamp("created_at").toLocalDateTime();
            boolean active = true;
            user = new User(userID, email, passwordHash, role, createdAt, active);
        }
        return user;
    }

    public boolean registerUser(User user) throws SQLException {
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = DBUtil.getConnection();
            
            String sql = "INSERT INTO [dbo].[users] ([email], [password_hash], [role], [created_at])\n"
                    + "VALUES (?, ?, ?, ?)";
            st = cn.prepareStatement(sql);
            st.setString(1, user.getEmail());
            st.setString(2, user.getPasswordHash());
            st.setString(3, user.getRole());
            st.setTimestamp(4, java.sql.Timestamp.valueOf(user.getCreatedAt()));

            int result = st.executeUpdate();
            System.out.println("Insert result: " + result);
            return result > 0;
        } catch (Exception e) {
            System.err.println("Error in registerUser: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            if (st != null) {
                st.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
    }

    public boolean registerStudent(Student student) throws SQLException {
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = DBUtil.getConnection();
            String sql = "INSERT INTO [dbo].[students] ([user_id], [full_name], [date_of_birth], [phone], [address])\n"
                    + "VALUES (?, ?, ?, ?, ?)";
            st = cn.prepareStatement(sql);
            st.setString(1, student.getUserID());
            st.setString(2, student.getFullName());
            if (student.getDateOfBirth() != null) {
                st.setDate(3, student.getDateOfBirth());
            } else {
                st.setNull(3, java.sql.Types.DATE);
            }
            st.setString(4, student.getPhone());
            st.setString(5, student.getAddress());

            int result = st.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            System.err.println("Error in registerStudent: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            if (st != null) {
                st.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
    }

    private Admin getAdmin(User user) throws SQLException {
        Admin admin = null;
        String sql = "select [full_name], [position]\n"
                + "from [dbo].[users]\n"
                + "inner join [dbo].[admins] on [dbo].[users].[user_id] = [dbo].[admins].[user_id]\n"
                + "where [dbo].[users].[user_id] = ?";
        PreparedStatement st = cn.prepareStatement(sql);
        st.setString(1, user.getUserID());
        ResultSet table = st.executeQuery();
        if (table != null && table.next()) {
            String fullName = table.getString("full_name");
            String position = table.getString("position");
            admin = new Admin(user, fullName, position);
        }
        return admin;
    }

    private Student getStudent(User user) throws SQLException {
        Student student = null;
        String sql = "select [full_name], [date_of_birth], [phone], [address]\n"
                + "from [dbo].[users]\n"
                + "inner join [dbo].[students] on [dbo].[users].[user_id]=[dbo].[students].[user_id]\n"
                + "where [dbo].[users].[user_id] = ?";
        PreparedStatement st = cn.prepareStatement(sql);
        st.setString(1, user.getUserID());
        ResultSet table = st.executeQuery();
        if (table != null && table.next()) {
            String fullName = table.getString("full_name");
            Date dateOfBirth = table.getDate("date_of_birth");
            String phone = table.getString("phone");
            String address = table.getString("address");
            student = new Student(user, fullName, dateOfBirth, phone, address);
        }
        return student;
    }

    private Lecturer getLecturer(User user) throws SQLException {
        Lecturer lecturer = null;
        String sql = "select [full_name], [specialization], [qualification], [phone], [bio]\n"
                + "from [dbo].[users]\n"
                + "inner join [dbo].[lecturers] on [dbo].[users].[user_id]=[dbo].[lecturers].[user_id]\n"
                + "where [dbo].[users].[user_id] = ?";
        PreparedStatement st = cn.prepareStatement(sql);
        st.setString(1, user.getUserID());
        ResultSet table = st.executeQuery();
        if (table != null && table.next()) {
            String fullName = table.getString("full_name");
            String specialization = table.getString("specialization");
            String qualification = table.getString("qualification");
            String phone = table.getString("phone");
            String bio = table.getString("bio");
            lecturer = new Lecturer(user, fullName, specialization, qualification, phone, bio);
        }
        return lecturer;
    }

    public int getTotalStudents() throws SQLException {
        int count = 0;
        String sql = "select count(*) as count\n"
                + "from [dbo].[users]\n"
                + "inner join [dbo].[students] on [dbo].[users].[user_id]=[dbo].[students].[user_id]";
        PreparedStatement st = cn.prepareStatement(sql);
        ResultSet table = st.executeQuery();
        if (table != null && table.next()) {
            count = table.getInt("count");
        }
        return count;
    }
}
