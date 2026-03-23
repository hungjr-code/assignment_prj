/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author kAwa
 */
public class Course {
    private String courseID;
    private String courseName;
    private String language;
    private String level;
    private String description;
    private int fee;
    private int duration;
    private boolean enroll;

    public Course() {
    }

    public Course(String courseID, String courseName, String language, String level, String description, int fee, int duration) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.language = language;
        this.level = level;
        this.description = description;
        this.fee = fee;
        this.duration = duration;
        this.enroll = false;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getLanguage() {
        return language;
    }

    public String getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }

    public int getFee() {
        return fee;
    }

    public int getDuration() {
        return duration;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isEnroll() {
        return enroll;
    }

    public void setEnroll(boolean enroll) {
        this.enroll = enroll;
    }

}
