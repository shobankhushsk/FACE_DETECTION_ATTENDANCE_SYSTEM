/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BeanClasses;

/**
 *
 * @author Faizan Hussain S
 */
public class StudentBean {
    
    private int deptId;

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
    private int stdId;
    private String stdName;
    private String remarks;
    
    private String fName;
    private String surName;
    private String rollNo;
    private String shift;
    private String group;

    public String getfName() {
        return fName;
    }

    public String getSurName() {
        return surName;
    }

    public String getRollNo() {
        return rollNo;
    }

    public String getShift() {
        return shift;
    }

    public String getGroup() {
        return group;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getStdId() {
        return stdId;
    }

    public void setStdId(int stdId) {
        this.stdId = stdId;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return stdName;
    }

   
    
}
