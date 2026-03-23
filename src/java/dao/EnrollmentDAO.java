/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Enrollment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kAwa
 */
public class EnrollmentDAO {

    Connection cn;

    public EnrollmentDAO(Connection cn) {
        this.cn = cn;
    }

    public ArrayList<Enrollment> getEnrollmentList() {
        ArrayList<Enrollment> list = new ArrayList<>();
        String sql = "select *\n"
                + "from [dbo].[enrollments]";
        return list;
    }

    public int getTotalEnrollmentsByCourse(String courseID) throws SQLException {
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
