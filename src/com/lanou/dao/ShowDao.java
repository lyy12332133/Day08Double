package com.lanou.dao;

import com.lanou.action.Department;
import com.lanou.action.Staff;
import com.lanou.utils.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dllo on 17/10/19.
 */
public class ShowDao {

    private QueryRunner qr = new TxQueryRunner();

    public List<Department> showDept(){
        String sql = "select * from department";
        try {
           return qr.query(sql,new BeanListHandler<>(Department.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Staff> showStaff(String id){
        String sql = "select * from staff where did = ?";
        try {
           return qr.query(sql,new BeanListHandler<>(Staff.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
