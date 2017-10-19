package com.lanou.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by zyf on 2017/5/9.
 */
public class JdbcUtils {
	//配置文件的默认配置!要求必须给出c3p0-config.xml
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

	//它是事务专用连接
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();


	/***
	 * 使用连接池返回一个连接对象
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {

		Connection conn = threadLocal.get();


		//当conn不等于null,说明已经调用过beginTransaction()方法
		//已经开启事务了
		if (conn != null) {
			return conn;
		}
		return dataSource.getConnection();
	}

	/***
	 * 返回dataSource
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * 开启事务
	 * 1.获取一个Connection,设置它的setAutoCommit(false)
	 * 2,还要保证dao中使用的连接是我们刚刚创建的!
	 * <p>
	 * ----------------------
	 * <p>
	 * 1,创建一个Connection,设置为手动提交
	 * 2,把这个Connection给dao使用
	 * 3,还要让commitTransaction或rollbackTransaction可以获取到
	 */
	public static void beginTransaction() throws SQLException {
		Connection conn = threadLocal.get();
		/**
		 * 1, 给conn赋值
		 * 2, 给conn设置为手动提交
		 */

		if (conn != null) {
			throw new SQLException("已经开启了事务,不能重复开启,不能直接提交");
		}
		conn = getConnection();

		conn.setAutoCommit(false);

		//将当前线程的连接保存起来
		threadLocal.set(conn);
	}

	/**
	 * 提交事务
	 * 1.获取beginTranscation提供的Connection
	 * 2.调用commit方法
	 */
	public static void commitTransaction() throws SQLException {
		Connection conn = threadLocal.get();
		/**
		 * 1,直接使用conn.commit();
		 */
		if (conn == null) {
			throw new SQLException("还没有开启事务,不能直接提交");
		}
		conn.commit();
		conn.close();

		//从已经提交的事务从threadLocal中移除
		threadLocal.remove();
	}

	/**
	 * 回滚事务
	 * 1.获取beginTranscation提供的Connection
	 * 2,调用rollback方法
	 */
	public static void rollbackTransaction() throws SQLException {
		Connection conn = threadLocal.get();
		/**
		 * 1,直接使用conn.rollback();
		 */

		if (conn == null) {
			throw new SQLException("还没有开启事务,不能直接回滚");
		}
		conn.rollback();
		conn.close();

		//将conn置空,方便下次使用时获取的是新的连接对象
		threadLocal.remove();
	}

	/**
	 * 释放连接
	 * @param connection
	 */
	public static void releaseConnection(Connection connection) throws SQLException {

		Connection conn = threadLocal.get();

		/**
		 * 1,判断它是不是事务专用,如果是,就不关闭(开始事务,提交事务,回滚事务中都进行了关闭操作)
		 * 2,如果不是事务专用,那么就要关闭
		 */
		if(conn == null){
			//如果coon==null,说明现在没有事务,那么Connection一定不是事务专用的!
			connection.close();
			return;
		}

		//如果conn不等于null,说明有事务,但是还需要判断一下,参数连接对象与conn是否是同一个
		//如果不相等,说明参数连接不是事务专用连接
		if(conn != connection){
			connection.close();
		}
	}
}
