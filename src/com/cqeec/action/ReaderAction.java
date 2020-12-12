package com.cqeec.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import com.cqeec.entity.Reader;
import com.cqeec.util.DBUTIL;

public class ReaderAction {
	Connection con = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public ReaderAction() {
		con = DBUTIL.getConn();
	}

	
	public String[][] getReaderList(Reader reader) {
		String sql = "";
		sql = "SELECT * FROM reader where 1=1 ";
		if (!reader.getReaderid().trim().equals("")) {
			sql = sql + " and readerid='" + reader.getReaderid().trim() + "'";
		}
		if (!reader.getReadername().trim().equals("")) {
			sql = sql + " and readername like '%" + reader.getReadername().trim()
					+ "%'";
		}
		if (!reader.getSex().trim().equals("")) {
			sql = sql + " and sex='" + reader.getSex().trim() + "'";
		}
		
		if (!reader.getStartDate().trim().equals("")) {
			sql = sql + " and birthday>='" + reader.getStartDate().trim()
					+ "'";
		}
		if (!reader.getEndDate().trim().equals("")) {
			sql = sql + " and birthday<='" + reader.getEndDate().trim() + "'";
		}
		if (!reader.getTelephone().trim().equals("")) {
			sql = sql + " and telephone=" + reader.getTelephone().trim();
		}
		
		
		ResultSet rs = null;
		String sn[][] = null;
		int row = 0;
		try {
			Statement stat = con.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stat.executeQuery(sql);
			if (rs.last()) {
				row = rs.getRow();
			}
			if (row != 0) {
				sn = new String[row][5];
				rs.first();
				rs.previous();
				int i = 0;
				while (rs.next()) {
					sn[i][0] = rs.getString("readerid");
					sn[i][1] = rs.getString("readername");
					sn[i][2] = rs.getString("sex");
					if (rs.getDate("birthday") != null){
						sn[i][3] = sdf.format(rs.getDate("birthday"));
					}else{
						sn[i][3] = rs.getString("birthday");
					}
					sn[i][4] = rs.getString("telephone");
					i++;
				}
				rs.close();
				stat.close();
			}
		} catch (Exception e) {
		}
		return sn;
	}
	
	
	//添加成功返回true，否则返回false
	public boolean add(Reader reader) {
		String sql = "Insert into reader(readerid,readername,sex,birthday,telephone) values(?,?,?,?,?)";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, reader.getReaderid().trim());
			pstm.setString(2, reader.getReadername().trim());
			pstm.setString(3, reader.getSex());
			pstm.setString(4, reader.getBirthday().trim());
			pstm.setString(5, reader.getTelephone().trim());
			pstm.executeUpdate();
			pstm.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	
	//修改成功返回true，否则返回false

	public boolean update(Reader reader) {
		String sql = "UPDATE reader set readername=?,sex=?,birthday=?,telephone=? where readerid=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, reader.getReadername().trim());
			pstm.setString(2, reader.getSex());
			pstm.setString(3, reader.getBirthday().trim());
			pstm.setString(4, reader.getTelephone().trim());
			pstm.setString(5, reader.getReaderid().trim());
			pstm.executeUpdate();
			pstm.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	
	
	 //删除成功返回true，否则返回false
	public boolean del(String readerid) {
		String sql = "Delete reader where readerid=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, readerid);
			pstm.executeUpdate();
			pstm.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	
	//添加读者时若存在返回false，否则返回true
	public boolean beforeadd(String readerid){
		String sql = "Select count(*) from reader where readerid=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, readerid);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()){
				int count = rs.getInt(1);
				if (count == 0){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}
	
	
	 //删除读者时，若在借阅信息表中存在返回false，否则返回true
	public boolean beforedel(String readerid){
		String sql = "select count(*) from Lender where readerid=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, readerid);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()){
				int count = rs.getInt(1);
				if (count == 0){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}


	public Reader getReaderInfoByID(String readerid){
		Reader reader = null;
		String sql = "select * from reader where readerid=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, readerid);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()){
				reader = new Reader();
				reader.setReadername(rs.getString("readerName"));
				reader.setBirthday(sdf.format(rs.getDate("birthday")));
				reader.setSex(rs.getString("sex"));
				reader.setTelephone(rs.getString("telephone"));
			}
		} catch (SQLException e) {
			
		}
		return reader;
	}
	
}
