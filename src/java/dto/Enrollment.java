/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDateTime;

/**
 *
 * @author kAwa
 */
public class Enrollment {
    private String enrollmentID;
    private String studentID;
    private String classID;
    private LocalDateTime enrolledAt;
    private String paymentID;

    public Enrollment() {
    }

    public Enrollment(String enrollmentID, String studentID, String classID, LocalDateTime enrolledAt, String paymentID) {
        this.enrollmentID = enrollmentID;
        this.studentID = studentID;
        this.classID = classID;
        this.enrolledAt = enrolledAt;
        this.paymentID = paymentID;
    }

    public String getEnrollmentID() {
        return enrollmentID;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getClassID() {
        return classID;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setEnrollmentID(String enrollmentID) {
        this.enrollmentID = enrollmentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public void setEnrolledAt(LocalDateTime enrolledAt) {
        this.enrolledAt = enrolledAt;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }
    
}
