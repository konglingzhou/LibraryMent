package com.cqeec.action;

import java.sql.*;

import com.cqeec.entity.Result;
import com.cqeec.entity.User;
import com.cqeec.util.DBUTIL;

public class UserAction {
	/**
	 * 1.登录功能 2.密码修改 3.用户编辑功能
	 */
	private Connection con;

	public UserAction() {
		con = DBUTIL.getConn();
	}

	public Result UserLogin(User user) {
		Result result = new Result();
		String login_sql = "select * from users where username=?";
		try {
			PreparedStatement pstm = con.prepareStatement(login_sql);
			pstm.setString(1, user.getUsername());
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				if (rs.getString("userpwd").equals(user.getUserpwd())) {
					result.setResultCode(0);
					user.setUsername(rs.getString("username"));
					// user.setUserpwd(rs.getString("userpwd"));

					user.setUserpwd(user.getUserpwd());
					result.setUser(user);
					// rs.close();
					// pstm.close();
					// return true;
				} else {
					result.setResultCode(-2);
					// return false;
				}
			} else {
				result.setResultCode(-1);
				// return false;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			// return false;
			result.setResultCode(-3);
		}
		return result;
	}

	public boolean ChangePassword(String new_pwd, String id) {
		// TODO 自动生成的方法存根
		String sql = "update users set userpwd=? where username=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, new_pwd);
			pstm.setString(2, id);
			pstm.executeUpdate();
			pstm.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
}
