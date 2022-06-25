package com.lin.common.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.sqlite.SQLiteConfig;

@Configuration
@ComponentScan()
public class SQLiteSample {

	public static void main(String[] args) throws Exception {
		
//		NestedRuntimeException
//		 org.springframework.aop.TargetSource
		ApplicationContext context = new AnnotationConfigApplicationContext();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement prep = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:sqlite_sample.db");
			Statement stat = conn.createStatement();
			stat.executeUpdate("drop table if exists people;");
			stat.executeUpdate("create table people (id integer, name, occupation);");
			prep = conn.prepareStatement("insert into people values (?, ?, ?);");
			prep.setInt(1, 1);
			prep.setString(2, "Gandhi");
			prep.setString(3, "politics");
			prep.addBatch();
			prep.setInt(1, 2);
			prep.setString(2, "Turing");
			prep.setString(3, "computers");
			prep.addBatch();
			prep.setInt(1, 3);
			prep.setString(2, "Wittgenstein");
			prep.setString(3, "smartypants");
			prep.addBatch();
			conn.setAutoCommit(false);
			prep.executeBatch();
			conn.setAutoCommit(true);
			rs = stat.executeQuery("select * from people;");
			while (rs.next()) {
				System.out.println("id = " + rs.getInt("id"));
				System.out.println("name = " + rs.getString("name"));
				System.out.println("job = " + rs.getString("occupation"));
			}
			// 分页时，可以使用ResultSet来完成分页rs.absolute(100)，也可以sql语句中完成分页select ... limit 100,10；
			// 下面是释放数据库连接的过程，使用数据库连接池时不应该释放连接，而是将连接重新放到连接池中。
			// 以代理的方式生成Connection的对象，调用Connection的close方法时将Connection加入到线程池中。线程池中加入的是Connection的代理对象即可。
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} finally {
					if (prep != null) {
						try {
							prep.close();
						} finally {
							if (conn != null) {
								conn.close();
							}
						}
					}
				}
			}
		}
	}

}
