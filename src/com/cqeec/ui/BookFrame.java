package com.cqeec.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.cqeec.action.BookAction;
import com.cqeec.action.ReaderAction;
import com.cqeec.entity.Book;
import com.cqeec.entity.Reader;
import com.cqeec.util.ValidateUtil;

public class BookFrame {
	static JInternalFrame bookFrame = null;
	// 工具条
	static JToolBar toolBar = new JToolBar();
	static JButton addBtn = new JButton();
	static JButton delBtn = new JButton();
	static JButton modBtn = new JButton();
	static JButton saveBtn = new JButton();
	static JButton findBtn = new JButton();
	static JButton returnBtn = new JButton();
	// 状态栏
	static JToolBar stateBar = new JToolBar();
	static JLabel lbState = new JLabel("状态");
	// 面板
	static JPanel mainPanl = new JPanel();
	static BookList listPanl = null;
	static BookFind findPanl = null;
	static BookEdit editPanl = null;
	static String flag = "";
	static Book book = new Book();
	static BookAction action = new BookAction();
	
	public BookFrame() {
	}
	
	public static void show(JDesktopPane deskPane) {
		// TODO 自动生成的方法存根
		
		if (bookFrame == null) {
			bookFrame = new JInternalFrame("图书信息", false, true, false, true);
			bookFrame.setSize(600, 600);

			deskPane.add(bookFrame);
			final Container c = bookFrame.getContentPane();
			Init(c);
			bookFrame.setVisible(true);
		}
		bookFrame.addInternalFrameListener(new InternalFrameAdapter() {
			public void internalFrameClosing(InternalFrameEvent e) {
				//关闭窗体前释放对象
				flag = "";
				lbState = null;
				listPanl = null;
				findPanl = null;
				editPanl = null;
				mainPanl = null;
				toolBar = null;
				addBtn = null;
				delBtn = null;
				modBtn = null;
				saveBtn = null;
				findBtn = null;
				returnBtn = null;
				stateBar = null;
				lbState = null;
				book = null;
				action = null;
				bookFrame = null;
			}

		});
	}

	public static void Init(Container c) {
		lbState = new JLabel("状态");
		book = new Book();
		action = new BookAction();
		mainPanl = new JPanel();
		toolBar = new JToolBar();
		
		findBtn = new JButton();
		findBtn.setToolTipText("查询");
		findBtn.setIcon(new ImageIcon("images\\ChaXun.jpg"));
		findBtn.addActionListener(new BookEventActionListener());
		toolBar.add(findBtn);
		
		addBtn = new JButton();
		addBtn.setToolTipText("添加");
		addBtn.setIcon(new ImageIcon("images\\TianJia.jpg"));
		addBtn.addActionListener(new BookEventActionListener());
		toolBar.add(addBtn);
		
		modBtn = new JButton();
		modBtn.setToolTipText("修改");
		modBtn.setIcon(new ImageIcon("images\\XiuGai.jpg"));
		modBtn.addActionListener(new BookEventActionListener());
		toolBar.add(modBtn);
		
		saveBtn = new JButton();
		saveBtn.setToolTipText("保存");
		saveBtn.setIcon(new ImageIcon("images\\BaoCun.jpg"));
		saveBtn.addActionListener(new BookEventActionListener());
		toolBar.add(saveBtn);
		
		delBtn = new JButton();
		delBtn.setToolTipText("删除");
		delBtn.setIcon(new ImageIcon("images\\ShanChu.jpg"));
		delBtn.addActionListener(new BookEventActionListener());
		toolBar.add(delBtn);

		returnBtn = new JButton();
		returnBtn.setToolTipText("返回");
		returnBtn.setIcon(new ImageIcon("images\\FanHui.jpg"));
		returnBtn.addActionListener(new BookEventActionListener());
		toolBar.add(returnBtn);
		c.setLayout(new BorderLayout());
		toolBar.setFloatable(false);
		c.add(toolBar, BorderLayout.NORTH);
		// 状态栏
		stateBar = new JToolBar();
		stateBar.add(lbState);
		stateBar.setFloatable(false);
		c.add(stateBar, BorderLayout.SOUTH);
		mainPanl.setLayout(new BorderLayout());

		listPanl = new BookList();
		listPanl.getBookList(book);
		mainPanl.add(listPanl, BorderLayout.CENTER);
		c.add(mainPanl, BorderLayout.CENTER);
	}
}

class BookEventActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == BookFrame.findBtn) {
			find();
		}
		if (e.getSource() == BookFrame.returnBtn) {
			ret();
		}
		if (e.getSource() == BookFrame.addBtn) {
			add();
		}
		if (e.getSource() == BookFrame.modBtn) {
			edit();
		}
		if (e.getSource() == BookFrame.saveBtn) {
			save();
		}
		if (e.getSource() == BookFrame.delBtn) {
			del();
		}
	}
	private void find() {
		if (BookFrame.flag.equals("query")) {
			if (BookFrame.findPanl.cbDate.isSelected()) {
				if (!BookFrame.findPanl.txtStartDate.getText().trim()
						.equals("")) {
					if (!ValidateUtil
							.StringToDate(BookFrame.findPanl.txtStartDate
									.getText().trim())) {// 验证数据类型
						JOptionPane.showMessageDialog(null, "输入的日期格式有误，请按yyyy-mm-dd格式输入", "图书信息",JOptionPane.ERROR_MESSAGE);
						BookFrame.findPanl.txtStartDate.requestFocusInWindow();
						return;
					}
				}
				BookFrame.book.setStartDate(BookFrame.findPanl.txtStartDate
						.getText().trim());
				if (!BookFrame.findPanl.txtEndDate.getText().trim().equals("")) {
					if (!ValidateUtil
							.StringToDate(BookFrame.findPanl.txtEndDate
									.getText().trim())) {// 验证数据类型
						JOptionPane.showMessageDialog(null, "输入的日期格式有误，请按yyyy-mm-dd格式输入", "图书信息",JOptionPane.ERROR_MESSAGE);
						BookFrame.findPanl.txtEndDate.requestFocusInWindow();
						return;
					}
				}
				BookFrame.book.setEndDate(BookFrame.findPanl.txtEndDate
						.getText().trim());
			} else {
				BookFrame.book.setStartDate("");
				BookFrame.book.setEndDate("");
			}
			

			if (BookFrame.findPanl.cbBookid.isSelected()) {
				BookFrame.book.setBookid(BookFrame.findPanl.txtBookid.getText()
						.trim());
			} else {
				BookFrame.book.setBookid("");
			}
			if (BookFrame.findPanl.cbBookName.isSelected()) {
				BookFrame.book.setBookname(BookFrame.findPanl.txtBookName.getText()
						.trim());
			} else {
				BookFrame.book.setBookname("");
			}
			if (BookFrame.findPanl.cbType.isSelected()) {
				BookFrame.book.setType(BookFrame.findPanl.type
						.getSelectedItem().toString().trim());
			} else {
				BookFrame.book.setType("");
			}
			if (BookFrame.findPanl.cbAdress.isSelected()) {
				BookFrame.book.setAdress(BookFrame.findPanl.txtAdress.getText()
						.trim());
			} else {
				BookFrame.book.setAdress("");
			}
			
			
			ret();
			BookFrame.flag = "";
			return;
		}
		BookFrame.findPanl = new BookFind();
		BookFrame.mainPanl.removeAll();
		BookFrame.mainPanl.add(BookFrame.findPanl, BorderLayout.CENTER);
		BookFrame.flag = "query";
	}
	private void ret() {
		BookFrame.flag = "";
		BookFrame.listPanl = new BookList();
		BookFrame.listPanl.getBookList(BookFrame.book);
		BookFrame.mainPanl.removeAll();
		BookFrame.mainPanl.add(BookFrame.listPanl, BorderLayout.CENTER);
	}
	private void add() {
		BookFrame.editPanl = new BookEdit("add");
		BookFrame.mainPanl.removeAll();
		BookFrame.mainPanl.add(BookFrame.editPanl, BorderLayout.CENTER);
	}

	private void edit() {
		if (BookFrame.listPanl.table != null){
			int row = BookFrame.listPanl.table.getSelectedRow();
			if (row >= 0) {
				BookFrame.editPanl = new BookEdit("edit");
				BookFrame.editPanl.txtBookid.setText(BookFrame.listPanl.table
						.getValueAt(row, 0).toString());
				BookFrame.editPanl.txtBookName.setText(BookFrame.listPanl.table
						.getValueAt(row, 1).toString());
				BookFrame.editPanl.type.setSelectedItem(BookFrame.listPanl.table
						.getValueAt(row, 2).toString());
				BookFrame.editPanl.txtDate.setText(BookFrame.listPanl.table
						.getValueAt(row, 3).toString());
				BookFrame.editPanl.txtAdress.setText(BookFrame.listPanl.table
						.getValueAt(row, 4).toString());
				
			
				
				BookFrame.mainPanl.removeAll();
				BookFrame.mainPanl.add(BookFrame.editPanl, BorderLayout.CENTER);
			} else {
				JOptionPane.showMessageDialog(null, "请选择要修改的数据行", "图书信息",JOptionPane.WARNING_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(null, "没有需要修改的数据行", "图书信息",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void save() {
		if (BookFrame.editPanl != null) {
			Book book = new Book();
			book.setBookid(BookFrame.editPanl.txtBookid.getText().trim());
			book.setBookname(BookFrame.editPanl.txtBookName.getText().trim());
			book.setType(BookFrame.editPanl.type.getSelectedItem()
					.toString());
			book.setDate(BookFrame.editPanl.txtDate.getText().trim());
			book.setAdress(BookFrame.editPanl.txtAdress.getText().trim());

			// 判断数据是否填写完整以及数据类型是否正确
			if (book.getBookid().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "请填写图书编号", "图书信息",JOptionPane.WARNING_MESSAGE);
				BookFrame.editPanl.txtBookid.requestFocusInWindow();
				return;
			}
			if (book.getBookid().trim().length() != 5) {
				JOptionPane.showMessageDialog(null, "图书编号长度为5位", "图书信息",JOptionPane.ERROR_MESSAGE);
				BookFrame.editPanl.txtBookid.requestFocusInWindow();
				return;
			}
			if (book.getBookname().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "请填写图书名称", "图书信息",JOptionPane.WARNING_MESSAGE);
				BookFrame.editPanl.txtBookName.requestFocusInWindow();
				return;
			}
			if (book.getBookname().trim().length() > 10) {
				JOptionPane.showMessageDialog(null, "图书名称长度不能超出10", "图书信息",JOptionPane.ERROR_MESSAGE);
				BookFrame.editPanl.txtBookName.requestFocusInWindow();
				return;
			}
			if (book.getDate().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "请填写出版日期", "图书信息",JOptionPane.WARNING_MESSAGE);
				BookFrame.editPanl.txtDate.requestFocusInWindow();
				return;
			}
			if (!ValidateUtil.StringToDate(book.getDate().trim())) {// 验证数据类型
				JOptionPane.showMessageDialog(null, "输入的日期格式有误，请按yyyy-MM-dd格式输入", "图书信息",JOptionPane.ERROR_MESSAGE);
				BookFrame.editPanl.txtDate.requestFocusInWindow();
				return;
			}
			if (book.getAdress().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "请填写出版社", "图书信息",JOptionPane.WARNING_MESSAGE);
				BookFrame.editPanl.txtAdress.requestFocusInWindow();
				return;
			}
			if (book.getAdress().trim().length() > 20) {
				JOptionPane.showMessageDialog(null, "出版社长度不能超出20", "图书信息",JOptionPane.ERROR_MESSAGE);
				BookFrame.editPanl.txtAdress.requestFocusInWindow();
				return;
			}
			
			
			
			
			boolean reValue = false;
			if (BookFrame.editPanl.flag.equals("edit")) {
				reValue = BookFrame.action.update(book);
			} else {
				if (!BookFrame.action.beforeadd(book.getBookid().trim())){
					JOptionPane.showMessageDialog(null, "此书已添加过，请检查数据后重新录入图书编号", "图书信息",JOptionPane.ERROR_MESSAGE);
					BookFrame.editPanl.txtBookid.requestFocusInWindow();
					return;
				}else{
					reValue = BookFrame.action.add(book);
				}
			}
			if (reValue) {
				JOptionPane.showMessageDialog(null, "操作成功", "图书信息",JOptionPane.INFORMATION_MESSAGE);
				ret();
			} else {
				JOptionPane.showMessageDialog(null, "操作失败", "图书信息",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void del() {
		if (BookFrame.listPanl.table != null){
			int row = BookFrame.listPanl.table.getSelectedRow();
			if (row >= 0) {
				int i = JOptionPane.showConfirmDialog(null, "是否确认删除该行数据？", "确认",
						JOptionPane.YES_NO_OPTION);
				if (i == 1) {
					return;
				}
				String readerid = BookFrame.listPanl.table.getValueAt(row, 0)
						.toString();
				if (BookFrame.action.beforedel(readerid)){
					if (BookFrame.action.del(readerid)) {
						JOptionPane.showMessageDialog(null, "操作成功", "图书信息",JOptionPane.INFORMATION_MESSAGE);
						ret();
					} else {
						JOptionPane.showMessageDialog(null, "操作失败", "图书信息",JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "此书有读者借阅无法删除", "图书信息",JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "请选择要删除的数据行", "图书信息",JOptionPane.WARNING_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(null, "没有需要删除的数据行", "图书信息",JOptionPane.WARNING_MESSAGE);
		}
		
	}
}

