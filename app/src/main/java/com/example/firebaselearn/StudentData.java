package com.example.firebaselearn;

import java.util.Date;

public class StudentData {

    String studentId;
    String studentNameD ;
    Date studentDateD ;
    int studentTelNoD ;
    String studentMailD;
    String studentDeparmantD;
    String studentClassD ;

    public StudentData(){

    }

    public StudentData(String studentId, String studentNameD, Date studentDateD, int studentTelNoD, String studentMailD, String studentDeparmantD, String studentClassD) {
        this.studentId = studentId;
        this.studentNameD = studentNameD;
        this.studentDateD = studentDateD;
        this.studentTelNoD = studentTelNoD;
        this.studentMailD = studentMailD;
        this.studentDeparmantD = studentDeparmantD;
        this.studentClassD = studentClassD;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentNameD() {
        return studentNameD;
    }

    public Date getStudentDateD() {
        return studentDateD;
    }

    public int getStudentTelNoD() {
        return studentTelNoD;
    }

    public String getStudentMailD() {
        return studentMailD;
    }

    public String getStudentDeparmantD() {
        return studentDeparmantD;
    }

    public String getStudentClassD() {
        return studentClassD;
    }
}
