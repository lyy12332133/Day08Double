package com.lanou.action;

/**
 * Created by dllo on 17/10/19.
 */
public class Department {
    private int did;
    private String dname;

    public Department(int did, String dname) {
        this.did = did;
        this.dname = dname;
    }

    @Override
    public String toString() {
        return "Department{" +
                "did=" + did +
                ", dname='" + dname + '\'' +
                '}';
    }

    public Department() {
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}
