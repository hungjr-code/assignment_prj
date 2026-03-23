/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Class;
import dbutils.DBUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kAwa
 */
public class ClassDAO {

    Connection cn;

    public ClassDAO() throws ClassNotFoundException, SQLException {
        this.cn = DBUtil.getConnection();
    }
    
    public ArrayList<Class> getClassList() throws SQLException {
        ArrayList<Class> list = new ArrayList<>();
        String sql = "select *\n"
                + "from [dbo].[classes]";
        PreparedStatement st = cn.prepareStatement(sql);
        ResultSet table = st.executeQuery();
        while (table != null && table.next()) {
            String classID = table.getString("class_id");
            String courseID = table.getString("course_id");
            String lecturerID = table.getString("lecturer_id");
            Date startDate = table.getDate("start_date");
            Date endDate = table.getDate("end_date");
            int maxStudents = table.getInt("max_students");
            String mode = table.getString("mode");
            String meetLink = table.getString("meeting_link");   
            Class cla = new Class(classID, courseID, lecturerID, startDate, endDate, maxStudents, mode, meetLink);
            list.add(cla);
        }
        return list;
    }

    public int getTotalClass() throws SQLException {
        int count = 0;
        String sql = "select count(*) as count\n"
                + "from [dbo].[classes]";
        PreparedStatement st = cn.prepareStatement(sql);
        ResultSet table = st.executeQuery();
        if(table!=null && table.next()) {
            count = table.getInt(sql);
        }
        return count;
    }
}
