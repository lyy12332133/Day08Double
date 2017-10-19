package com.lanou.utils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 这个类中的方法,自己来处理连接的问题.
 * 无需外界传递.
 * 怎么处理呢?
 * 	通过JdbcUtils.getConnection()得到连接,有可能是事务连接,也可能是普通的连接.
 * 	JdbcUtils.releaseConnection()完成对连接的释放,如果是普通连接,将其关闭.
 * Created by zyf on 2017/5/9.
 */
public class TxQueryRunner extends QueryRunner {

	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		/**
		 * 1,得到连接
		 * 2,执行父类方法,将连接对象传递过去
		 * 3,释放连接
		 * 4,返回值
		 */
		Connection con = JdbcUtils.getConnection();
		int[] result = super.batch(con,sql,params);
		JdbcUtils.releaseConnection(con);
		return result;
	}


	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		T result = super.query(con,sql,rsh,params);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		T result = super.query(con,sql,rsh);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public int update(String sql) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		int result = super.update(con,sql);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public int update(String sql, Object param) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		int result = super.update(con,sql,param);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public int update(String sql, Object... params) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		int result = super.update(con,sql,params);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public <T> T insert(String sql, ResultSetHandler<T> rsh) throws SQLException {
		return super.insert(sql, rsh);
	}

	@Override
	public <T> T insert(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		return super.insert(sql, rsh, params);
	}

	@Override
	public <T> T insertBatch(String sql, ResultSetHandler<T> rsh, Object[][] params) throws SQLException {
		return super.insertBatch(sql, rsh, params);
	}


}
