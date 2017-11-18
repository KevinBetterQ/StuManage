
package com.up.student.dao;

import java.sql.SQLException;

import com.up.student.base.BaseDAO;
import com.up.student.model.Admin;

/**
 * 模块说明： 管理员增删改查
 * 
 */
public class AdminDAO extends BaseDAO {

	private static AdminDAO ad = null;

	public static synchronized AdminDAO getInstance() {
		if (ad == null) {
			ad = new AdminDAO();
		}
		return ad;
	}
	
	public boolean insert(Admin admin) throws SQLException {
		boolean result = false;
		if (admin == null) {
			return result;
		}
			// insert
			String sql = "insert into admin(name,username,password,privi) values(?,?,?,?)";
			String[] param = { admin.getName(), admin.getUsername(), admin.getPassword(), admin.getPrivi() };
			if (db.executeUpdate(sql, param) == 1) {
				result = true;
			}
		
		return result;
	}

	public int queryForLogin(String username, String password) {
		int result = 0;
		String privi = "stu";
		if (username.length() == 0 || password.length() == 0) {
			return result;
		}
		String sql = "select * from admin where username=? and password=?";
		String[] param = { username, password };
		rs = db.executeQuery(sql, param);
		try {
			if (rs.next()) {
				privi = rs.getString("privi");
				if(privi.equals("teacher")) result = 1;
				if(privi.equals("student")) result = 2;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			destroy();
		}
		return result;
	}

}
