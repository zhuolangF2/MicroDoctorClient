package com.zhuolang.main.model;

import java.util.Date;

/**
 * Created by hzg on 2016/11/6.
 */
public class Appointment {

    private int id;
    private int patientId;//病人ID
    private int doctorId;//医师ID
    private String seeTime;//预约了去就诊日期时间
    private String disease;//病症
    private String dateTime;//预约时间、
    private String diagnose;//医生诊断
    private double dstar;//评论（星号）
    private int dNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getSeeTime() {
        return seeTime;
    }

    public void setSeeTime(String seeTime) {
        this.seeTime = seeTime;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public double getDstar() {
        return dstar;
    }

    public void setDstar(double dstar) {
        this.dstar = dstar;
    }

    public int getdNumber() {
        return dNumber;
    }

    public void setdNumber(int dNumber) {
        this.dNumber = dNumber;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", seeTime=" + seeTime +
                ", disease='" + disease + '\'' +
                ", dateTime=" + dateTime +
                ", diagnose='" + diagnose + '\'' +
                ", dstar=" + dstar +
                ", dNumber=" + dNumber +
                '}';
    }
}
