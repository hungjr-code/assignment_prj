/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.sql.Date;

/**
 *
 * @author kAwa
 */
public class Class {
    private String classID;
    private String courseID;
    private String lecturerID;
    private Date startDate;
    private Date endDate;
    private int maxStudents;
    private String mode;
    private String meetLink;

    public Class() {
    }

    public Class(String classID, String courseID, String lecturerID, Date startDate, Date endDate, int maxStudents, String mode, String meetLink) {
        this.classID = classID;
        this.courseID = courseID;
        this.lecturerID = lecturerID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxStudents = maxStudents;
        this.mode = mode;
        this.meetLink = meetLink;
    }

    public String getClassID() {
        return classID;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getLecturerID() {
        return lecturerID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public String getMode() {
        return mode;
    }

    public String getMeetLink() {
        return meetLink;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setLecturerID(String lecturerID) {
        this.lecturerID = lecturerID;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setMeetLink(String meetLink) {
        this.meetLink = meetLink;
    }
    
}
