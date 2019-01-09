package com.yinyao.entity;

public class Student {
    private int sid;
    private String sname;
    private Classes classes;
    public int getSid() {
        return sid;
    }
    public void setSid(int sid) {
        this.sid = sid;
    }
    public String getSname() {
        return sname;
    }
    public void setSname(String sname) {
        this.sname = sname;
    }
    public Classes getClasses() {
        return classes;
    }
    public void setClasses(Classes classes) {
        this.classes = classes;
    }
    @Override
    public String toString() {
        return "Student [sid=" + sid + ", sname=" + sname + ", classes=" + classes + "]";
    }
}
