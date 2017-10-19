package com.lanou.action;

/**
 * Created by dllo on 17/10/19.
 */
public class Staff {
    private int sid;
    private String sname;

    public Staff() {
    }

    @Override
    public String toString() {
        return "Staff{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                '}';
    }

    public Staff(int sid, String sname) {
        this.sid = sid;
        this.sname = sname;
    }

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
}
