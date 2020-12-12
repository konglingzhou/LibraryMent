package com.cqeec.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.cqeec.action.UserAction;
import com.cqeec.entity.Result;
import com.cqeec.entity.User;

public class LoginFrame extends JFrame implements ActionListener{
	JTextField txt_username;
	JPasswordField txt_userpwd;
	JButton btn_login,btn_cancel;
	ImageIcon image;
	JLabel dl_bj;
	public LoginFrame() {
		setTitle("登录窗口");
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		JLabel l_username = new JLabel("账号:");
		l_username.setBounds(95, 76, 100, 30);
		l_username.setFont(new Font("楷体",Font.BOLD,25));
		add(l_username);
		txt_username = new JTextField();
		txt_username.setBounds(160, 80, 170, 25);
		add(txt_username);
		
		
		JLabel l_userpwd = new JLabel("密码:");
		l_userpwd.setBounds(95, 126, 100, 30);
		l_userpwd.setFont(new Font("楷体",Font.BOLD,25));
		add(l_userpwd);
		txt_userpwd = new JPasswordField();
		txt_userpwd.setBounds(160, 130, 170, 25);
		txt_userpwd.setEchoChar('*');
		add(txt_userpwd);
		
		JLabel bt = new JLabel("图书管理系统");
		bt.setBounds(130, 20, 200, 30);
		bt.setFont(new Font("行体",Font.BOLD+Font.ITALIC,25));
		bt.setForeground(Color.GRAY);
		add(bt);
		
		btn_login = new JButton("登录");
		btn_login.setBounds(140, 180, 100, 50);
		add(btn_login);
		btn_login.addActionListener(this);
		
		
		
		image = new ImageIcon("images\\dl_bj.jpg");
		dl_bj = new JLabel();
		dl_bj.setOpaque(true);
		dl_bj.setIcon(image);
		dl_bj.setBounds(0, 0, 400, 300);
		add(dl_bj);
		setVisible(true);
	}
	public static void main(String[] args) {
		new LoginFrame();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		/**
		 * 1.数据非空验证
		 * 2.登录验证
		 */
		if (txt_username.getText().trim().equals("")){
			JOptionPane.showMessageDialog(null, "请输入登录用户名", "登录信息",JOptionPane.WARNING_MESSAGE);
			txt_username.requestFocusInWindow();
			return;
		}
		if (txt_userpwd.getText().trim().equals("")){
			JOptionPane.showMessageDialog(null, "请输入登录密码", "登录信息",JOptionPane.WARNING_MESSAGE);
			txt_userpwd.requestFocusInWindow();
			return;
		}
		User user = new User();
		user.setUsername(txt_username.getText().trim());
		user.setUserpwd(txt_userpwd.getText().trim());
		UserAction ua = new UserAction();
		Result result = ua.UserLogin(user);
		if (result.getResultCode() == 0){
			setVisible(false);
			new MainFrame(user);
		}else if (result.getResultCode() == -1){
			JOptionPane.showMessageDialog(null, "用户名错误", "登录信息",JOptionPane.ERROR_MESSAGE);
			txt_username.requestFocusInWindow();
			return;
		}else if (result.getResultCode() == -2){
			JOptionPane.showMessageDialog(null, "密码错误", "登录信息",JOptionPane.ERROR_MESSAGE);
			txt_userpwd.requestFocusInWindow();
			return;
		}else if (result.getResultCode() == -3){
			JOptionPane.showMessageDialog(null, "系统错误，请联系系统管理员", "登录信息",JOptionPane.ERROR_MESSAGE);
			return;
		}
//		if(ua.UserLogin(user)) {
//			/**
//			 * 1.打开主窗体
//			 * 2.将登录用户信息传到主窗体
//			 */
//			new MainFrame(user);
//		}
//		else {
//			
//		}
	}
}
