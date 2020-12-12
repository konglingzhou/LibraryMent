package com.cqeec.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import com.cqeec.entity.Book;
import com.cqeec.util.DBUTIL;

public class BookAction {
	Connection con = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public BookAction() {
		con = DBUTIL.getConn();
	}

	
	public String[][] getBookList(Book book) {
		String sql = "";
		sql = "SELECT * FROM book where 1=1 ";
		if (!book.getBookid().trim().equals("")) {
			sql = sql + " and bookid='" + book.getBookid().trim() + "'";
		}
		if (!book.getBookname().trim().equals("")) {
			sql = sql + " and bookname like '%" + book.getBookname().trim()
					+ "%'";
		}
		if (!book.getType().trim().equals("")) {
			sql = sql + " and type='" + book.getType().trim() + "'";
		}
		
		if (!book.getStartDate().trim().equals("")) {
			sql = sql + " and date>='" + book.getStartDate().trim()
					+ "'";
		}
		if (!book.getEndDate().trim().equals("")) {
			sql = sql + " and date<='" + book.getEndDate().trim() + "'";
		}
		if (!book.getAdress().trim().equals("")) {
			sql = sql + " and adress=" + book.getAdress().trim();
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
					sn[i][0] = rs.getString("bookid");
					sn[i][1] = rs.getString("bookname");
					sn[i][2] = rs.getString("type");
					if (rs.getDate("date") != null){
						sn[i][3] = sdf.format(rs.getDate("date"));
					}else{
						sn[i][3] = rs.getString("date");
					}
					sn[i][4] = rs.getString("adress");
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
	public boolean add(Book book) {
		String sql = "Insert into book(bookid,bookname,type,date,adress) values(?,?,?,?,?)";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, book.getBookid().trim());
			pstm.setString(2, book.getBookname().trim());
			pstm.setString(3, book.getType());
			pstm.setString(4, book.getDate().trim());
			pstm.setString(5, book.getAdress().trim());
			pstm.executeUpdate();
			pstm.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	
	//修改成功返回true，否则返回false

	public boolean update(Book book) {
		String sql = "UPDATE book set bookname=?,type=?,date=?,adress=? where bookid=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, book.getBookname().trim());
			pstm.setString(2, book.getType());
			pstm.setString(3, book.getDate().trim());
			pstm.setString(4, book.getAdress().trim());
			pstm.setString(5, book.getBookid().trim());
			pstm.executeUpdate();
			pstm.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	
	
	 //删除成功返回true，否则返回false
	public boolean del(String bookid) {
		String sql = "Delete book where bookid=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, bookid);
			pstm.executeUpdate();
			pstm.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	
	//添加读者时若存在返回false，否则返回true
	public boolean beforeadd(String bookid){
		String sql = "Select count(*) from book where bookid=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, bookid);
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
	public boolean beforedel(String bookid){
		String sql = "select count(*) from Lender where bookid=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, bookid);
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


	public Book getBookInfoByID(String bookid){
		Book book = null;
		String sql = "select * from book where bookid=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, bookid);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()){
				book = new Book();
				book.setBookname(rs.getString("bookName"));
				book.setDate(sdf.format(rs.getDate("date")));
				book.setType(rs.getString("type"));
				book.setAdress(rs.getString("adress"));
			}
		} catch (SQLException e) {
			
		}
		return book;
	}
	
}
