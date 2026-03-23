/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbutils.DBUtil;
import dto.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kAwa
 */
public class CourseDAO {

    Connection cn;

    public List<Course> getCourseList() throws SQLException, ClassNotFoundException {
        List<Course> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sql = "SELECT course_id, course_name, language, level, description, fee, duration_weeks FROM [dbo].[courses]";
                st = cn.prepareStatement(sql);
                rs = st.executeQuery();
                while (rs != null && rs.next()) {
                    String courseID = rs.getString("course_id");
                    String courseName = rs.getString("course_name");
                    String language = rs.getString("language");
                    String level = rs.getString("level");
                    String description = rs.getString("description");
                    int fee = rs.getInt("fee");
                    int duration = rs.getInt("duration_weeks");
                    Course course = new Course(courseID, courseName, language, level, description, fee, duration);
                    list.add(course);
                }
                System.out.println("CourseDAO.getCourseList: Found " + list.size() + " courses.");
            }
        } finally {
            // Luôn đóng tất cả tài nguyên sau khi sử dụng
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }

    public CourseDAO() throws ClassNotFoundException, SQLException {
        this.cn = DBUtil.getConnection();
    }

    public int getTotalCourses() throws SQLException {
        int count = 0;
        String sql = "select count(*) as count\n"
                + "from [dbo].[courses]";
        PreparedStatement st = cn.prepareStatement(sql);
        ResultSet table = st.executeQuery();
        if (table != null && table.next()) {
            count = table.getInt(sql);
        }
        return count;
    }

    public int getTotalEnrollments(String courseID) throws SQLException {
        int count = 0;
        String sql = "select count(*) as count\n"
                + "from [dbo].[courses]";
        PreparedStatement st = cn.prepareStatement(sql);
        ResultSet table = st.executeQuery();
        if (table != null && table.next()) {
            count = table.getInt(sql);
        }
        return count;
    }
}
