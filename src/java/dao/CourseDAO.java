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

/**
 *
 * @author kAwa
 */
public class CourseDAO {

    Connection cn;

    public ArrayList<Course> getCourseList() throws SQLException {
        ArrayList<Course> list = new ArrayList<>();
        String sql = "select *\n"
                + "from [dbo].[courses]";
        PreparedStatement st = cn.prepareStatement(sql);
        ResultSet table = st.executeQuery();
        while (table != null && table.next()) {
            String courseID = table.getString("course_id");
            String courseName = table.getString("course_name");
            String language = table.getString("language");
            String level = table.getString("level");
            String description = table.getString("description");
            int fee = table.getInt("fee");
            int duration = table.getInt("duration_weeks");
            Course course = new Course(courseID, courseName, language, level, description, fee, duration);
            list.add(course);
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
