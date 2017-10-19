package com.lanou.action;

import com.lanou.dao.ShowDao;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/10/19.
 */
public class BAction extends ActionSupport{
    private List<Department> departmentList = new ArrayList<>();
    private List<Staff> staffList = new ArrayList<>();
    private String did;
    private ShowDao showDao = new ShowDao();

    public String showDept(){
        departmentList = showDao.showDept();
        return SUCCESS;
    }
    public String showStaff(){
        staffList = showDao.showStaff(did);
        return SUCCESS;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

    public String getDid() {
        return did;
    }
    public void setDid(String did) {
        this.did = did;
    }
}
