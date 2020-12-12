package com.cqeec.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import com.cqeec.entity.User;

public class MainFrame extends JFrame implements ActionListener{
	public static User user;
	ImageIcon image;
	JLabel menu_bj;
	public static JDesktopPane deskPane = new JDesktopPane();
	
	//菜单栏
	JMenuBar menuBar = new JMenuBar();
	JMenu Carte = new JMenu("菜单");
	JMenuItem alterpwd = new JMenuItem("修改密码");
	JMenuItem quit = new JMenuItem("退出");
	
	JMenu Manage = new JMenu("系统管理");
	JMenuItem readerList = new JMenuItem("读者信息列表");
	JMenuItem bookList = new JMenuItem("图书信息列表");
	JMenuItem BorrowingList = new JMenuItem("借阅信息列表");
	
//	JMenu Quit = new JMenu("退出");
	
	//图标栏
	JToolBar toolBar = new JToolBar();
	JButton Pwd = new JButton();
	JButton DuZhe = new JButton();
	JButton JieYue = new JButton();
	JButton Book = new JButton();
	JButton TuiChu = new JButton();
	
	
	public MainFrame(User user) {
		this.user = user;
		Init();
	}

	public void Init() {
		setLayout(null);
		setSize(800, 600);
		setTitle("系统主菜单");
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Menu();
		Icto();
//		deskPane.setBackground(Color.green);
		add(deskPane);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				int i = JOptionPane.showConfirmDialog(null, "确认关闭本程序!!!", "退出页面",
						JOptionPane.YES_NO_OPTION);
				if (i != JOptionPane.YES_OPTION){
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}else{
					setDefaultCloseOperation(EXIT_ON_CLOSE);
					super.windowClosing(e);
				}
			}
		});
		
//		image = new ImageIcon("images\\menu_bj.jpg");
//		menu_bj = new JLabel(image);
//		menu_bj.setOpaque(false);
//		menu_bj.setIcon(image);
//		menu_bj.setBounds(0, 0, 800, 600);
//		add(menu_bj);
		
		setVisible(true);
	}
	
	public void Menu() {
		menuBar.add(Carte);
		Carte.add(alterpwd);
		alterpwd.addActionListener(this);
		Carte.addSeparator();// 添加分割线
		Carte.add(quit);
		quit.addActionListener(this);
		
		menuBar.add(Manage);
		Manage.add(readerList);
		readerList.addActionListener(this);
		Manage.addSeparator();
		Manage.add(bookList);
		bookList.addActionListener(this);
		Manage.addSeparator();
		Manage.add(BorrowingList);
		BorrowingList.addActionListener(this);
		this.setJMenuBar(menuBar);
		
//		menuBar.add(Quit);
		
	}
	
	public void Icto() {
		Pwd.setToolTipText("修改密码");
		Pwd.setIcon(new ImageIcon("images\\Pwd.jpg"));
		Pwd.addActionListener(this);
		toolBar.add(Pwd);
		
		Book.setToolTipText("图书信息");
		Book.setIcon(new ImageIcon("images\\Book.jpg"));
		Book.addActionListener(this);
		toolBar.add(Book);
		
		DuZhe.setToolTipText("读者信息");
		DuZhe.setIcon(new ImageIcon("images\\DuZhe.jpg"));
		DuZhe.addActionListener(this);
		toolBar.add(DuZhe);
		
		JieYue.setToolTipText("借阅信息");
		JieYue.setIcon(new ImageIcon("images\\JieYue.jpg"));
		JieYue.addActionListener(this);
		toolBar.add(JieYue);
		
		TuiChu.setToolTipText("退出");
		TuiChu.setIcon(new ImageIcon("images\\TuiChu.png"));
		TuiChu.addActionListener(this);
		toolBar.add(TuiChu);
		
		this.setLayout(new BorderLayout());
		toolBar.setFloatable(false);
		this.add(toolBar, BorderLayout.NORTH);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if (e.getSource() == alterpwd || e.getSource() == Pwd) {
			ChangePasswordJPanel.pwdd(deskPane);// 弹出修改密码窗体
		}
		
		
		if (e.getSource() == readerList || e.getSource() == DuZhe) {
			ReaderFrame.show(deskPane);// 弹出读者信息窗体
		}
		
		if (e.getSource() == bookList || e.getSource() == Book) {
			BookFrame.show(deskPane);// 弹出图书信息窗体
		}
		
		if (e.getSource() == quit || e.getSource() == TuiChu ) {
			int i = JOptionPane.showConfirmDialog(null, "确认关闭本程序!!!", "退出页面",
					JOptionPane.YES_NO_OPTION);
			if (i == JOptionPane.YES_OPTION) {
				System.exit(0);// 退出系统
			}
		}
	}
	public static void main(String[] args) {
		new MainFrame(user);
	}
}
