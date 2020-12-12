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

import com.cqeec.action.ReaderAction;
import com.cqeec.entity.Reader;
import com.cqeec.util.ValidateUtil;

public class ReaderFrame {
	static JInternalFrame readerFrame = null;
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
	static ReaderList listPanl = null;
	static ReaderFind findPanl = null;
	static ReaderEdit editPanl = null;
	static String flag = "";
	static Reader reader = new Reader();
	static ReaderAction action = new ReaderAction();

	public ReaderFrame() {
	}

	public static void show(JDesktopPane deskPane) {
		if (readerFrame == null) {
			readerFrame = new JInternalFrame("读者信息", false, true, false, true);
			readerFrame.setSize(600, 600);

			deskPane.add(readerFrame);
			final Container c = readerFrame.getContentPane();
			Init(c);
			readerFrame.setVisible(true);
		}
		readerFrame.addInternalFrameListener(new InternalFrameAdapter() {
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
				reader = null;
				action = null;
				readerFrame = null;
			}

		});
	}

	public static void Init(Container c) {
		lbState = new JLabel("状态");
		reader = new Reader();
		action = new ReaderAction();
		mainPanl = new JPanel();
		toolBar = new JToolBar();
		
		findBtn = new JButton("查询");
		findBtn.setForeground(Color.WHITE);
		findBtn.setFont(new Font("行体",Font.BOLD,20));
		findBtn.setBackground(Color.gray);
		findBtn.addActionListener(new ReaderEventActionListener());
		toolBar.add(findBtn);
		
		addBtn = new JButton("添加");
		addBtn.setForeground(Color.WHITE);
		addBtn.setFont(new Font("行体",Font.BOLD,20));
		addBtn.setBackground(Color.getHSBColor(113, 191, 234));
		addBtn.addActionListener(new ReaderEventActionListener());
		toolBar.add(addBtn);
		
		modBtn = new JButton("修改");
		modBtn.setForeground(Color.WHITE);
		modBtn.setFont(new Font("行体",Font.BOLD,20));
		modBtn.setBackground(Color.gray);
		modBtn.addActionListener(new ReaderEventActionListener());
		toolBar.add(modBtn);
		
		saveBtn = new JButton("保存");
		saveBtn.setForeground(Color.green);
		saveBtn.setFont(new Font("行体",Font.BOLD,20));
		saveBtn.setBackground(Color.getHSBColor(113, 191, 234));
		saveBtn.addActionListener(new ReaderEventActionListener());
		toolBar.add(saveBtn);
		
		delBtn = new JButton("删除");
		delBtn.setForeground(Color.red);
		delBtn.setFont(new Font("行体",Font.BOLD,20));
		delBtn.setBackground(Color.gray);
		delBtn.addActionListener(new ReaderEventActionListener());
		toolBar.add(delBtn);

		returnBtn = new JButton("返回");
		returnBtn.setForeground(Color.WHITE);
		returnBtn.setBackground(Color.getHSBColor(113, 191, 234));
		returnBtn.setFont(new Font("行体",Font.BOLD,20));
		returnBtn.addActionListener(new ReaderEventActionListener());
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

		listPanl = new ReaderList();
		listPanl.getReaderList(reader);
		mainPanl.add(listPanl, BorderLayout.CENTER);
		c.add(mainPanl, BorderLayout.CENTER);
	}
}

class ReaderEventActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ReaderFrame.findBtn) {
			find();
		}
		if (e.getSource() == ReaderFrame.returnBtn) {
			ret();
		}
		if (e.getSource() == ReaderFrame.addBtn) {
			add();
		}
		if (e.getSource() == ReaderFrame.modBtn) {
			edit();
		}
		if (e.getSource() == ReaderFrame.saveBtn) {
			save();
		}
		if (e.getSource() == ReaderFrame.delBtn) {
			del();
		}
	}
	private void find() {
		if (ReaderFrame.flag.equals("query")) {
			if (ReaderFrame.findPanl.cbBirthday.isSelected()) {
				if (!ReaderFrame.findPanl.txtStartDate.getText().trim()
						.equals("")) {
					if (!ValidateUtil
							.StringToDate(ReaderFrame.findPanl.txtStartDate
									.getText().trim())) {// 验证数据类型
						JOptionPane.showMessageDialog(null, "输入的日期格式有误，请按yyyy-mm-dd格式输入", "读者信息",JOptionPane.ERROR_MESSAGE);
						ReaderFrame.findPanl.txtStartDate.requestFocusInWindow();
						return;
					}
				}
				ReaderFrame.reader.setStartDate(ReaderFrame.findPanl.txtStartDate
						.getText().trim());
				if (!ReaderFrame.findPanl.txtEndDate.getText().trim().equals("")) {
					if (!ValidateUtil
							.StringToDate(ReaderFrame.findPanl.txtEndDate
									.getText().trim())) {// 验证数据类型
						JOptionPane.showMessageDialog(null, "输入的日期格式有误，请按yyyy-mm-dd格式输入", "读者信息",JOptionPane.ERROR_MESSAGE);
						ReaderFrame.findPanl.txtEndDate.requestFocusInWindow();
						return;
					}
				}
				ReaderFrame.reader.setEndDate(ReaderFrame.findPanl.txtEndDate
						.getText().trim());
			} else {
				ReaderFrame.reader.setStartDate("");
				ReaderFrame.reader.setEndDate("");
			}
			

			if (ReaderFrame.findPanl.cbReaderid.isSelected()) {
				ReaderFrame.reader.setReaderid(ReaderFrame.findPanl.txtReaderid.getText()
						.trim());
			} else {
				ReaderFrame.reader.setReaderid("");
			}
			if (ReaderFrame.findPanl.cbReaderName.isSelected()) {
				ReaderFrame.reader.setReadername(ReaderFrame.findPanl.txtReaderName.getText()
						.trim());
			} else {
				ReaderFrame.reader.setReadername("");
			}
			if (ReaderFrame.findPanl.cbSex.isSelected()) {
				ReaderFrame.reader.setSex(ReaderFrame.findPanl.sex
						.getSelectedItem().toString().trim());
			} else {
				ReaderFrame.reader.setSex("");
			}
			if (ReaderFrame.findPanl.cbTelephone.isSelected()) {
				ReaderFrame.reader.setTelephone(ReaderFrame.findPanl.txtTelephone.getText()
						.trim());
			} else {
				ReaderFrame.reader.setTelephone("");
			}
			
			
			ret();
			ReaderFrame.flag = "";
			return;
		}
		ReaderFrame.findPanl = new ReaderFind();
		ReaderFrame.mainPanl.removeAll();
		ReaderFrame.mainPanl.add(ReaderFrame.findPanl, BorderLayout.CENTER);
		ReaderFrame.flag = "query";
	}
	private void ret() {
		ReaderFrame.flag = "";
		ReaderFrame.listPanl = new ReaderList();
		ReaderFrame.listPanl.getReaderList(ReaderFrame.reader);
		ReaderFrame.mainPanl.removeAll();
		ReaderFrame.mainPanl.add(ReaderFrame.listPanl, BorderLayout.CENTER);
	}
	private void add() {
		ReaderFrame.editPanl = new ReaderEdit("add");
		ReaderFrame.mainPanl.removeAll();
		ReaderFrame.mainPanl.add(ReaderFrame.editPanl, BorderLayout.CENTER);
	}

	private void edit() {
		if (ReaderFrame.listPanl.table != null){
			int row = ReaderFrame.listPanl.table.getSelectedRow();
			if (row >= 0) {
				ReaderFrame.editPanl = new ReaderEdit("edit");
				ReaderFrame.editPanl.txtReaderid.setText(ReaderFrame.listPanl.table
						.getValueAt(row, 0).toString());
				ReaderFrame.editPanl.txtReaderName.setText(ReaderFrame.listPanl.table
						.getValueAt(row, 1).toString());
				ReaderFrame.editPanl.sex.setSelectedItem(ReaderFrame.listPanl.table
						.getValueAt(row, 2).toString());
				ReaderFrame.editPanl.txtBirthday.setText(ReaderFrame.listPanl.table
						.getValueAt(row, 3).toString());
				ReaderFrame.editPanl.txtTelphone.setText(ReaderFrame.listPanl.table
						.getValueAt(row, 4).toString());
				
			
				
				ReaderFrame.mainPanl.removeAll();
				ReaderFrame.mainPanl.add(ReaderFrame.editPanl, BorderLayout.CENTER);
			} else {
				JOptionPane.showMessageDialog(null, "请选择要修改的数据行", "读者信息",JOptionPane.WARNING_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(null, "没有需要修改的数据行", "读者信息",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void save() {
		if (ReaderFrame.editPanl != null) {
			Reader reader = new Reader();
			reader.setReaderid(ReaderFrame.editPanl.txtReaderid.getText().trim());
			reader.setReadername(ReaderFrame.editPanl.txtReaderName.getText().trim());
			reader.setSex(ReaderFrame.editPanl.sex.getSelectedItem()
					.toString());
			reader.setBirthday(ReaderFrame.editPanl.txtBirthday.getText().trim());
			reader.setTelephone(ReaderFrame.editPanl.txtTelphone.getText().trim());

			// 判断数据是否填写完整以及数据类型是否正确
			if (reader.getReaderid().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "请填写读者编号", "读者信息",JOptionPane.WARNING_MESSAGE);
				ReaderFrame.editPanl.txtReaderid.requestFocusInWindow();
				return;
			}
			if (reader.getReaderid().trim().length() != 5) {
				JOptionPane.showMessageDialog(null, "读者编号长度为5位", "读者信息",JOptionPane.ERROR_MESSAGE);
				ReaderFrame.editPanl.txtReaderid.requestFocusInWindow();
				return;
			}
			if (reader.getReadername().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "请填写读者姓名", "读者信息",JOptionPane.WARNING_MESSAGE);
				ReaderFrame.editPanl.txtReaderName.requestFocusInWindow();
				return;
			}
			if (reader.getReadername().trim().length() > 10) {
				JOptionPane.showMessageDialog(null, "读者姓名长度不能超出10", "读者信息",JOptionPane.ERROR_MESSAGE);
				ReaderFrame.editPanl.txtReaderName.requestFocusInWindow();
				return;
			}
			if (reader.getBirthday().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "请填写读者出生日期", "读者信息",JOptionPane.WARNING_MESSAGE);
				ReaderFrame.editPanl.txtBirthday.requestFocusInWindow();
				return;
			}
			if (!ValidateUtil.StringToDate(reader.getBirthday().trim())) {// 验证数据类型
				JOptionPane.showMessageDialog(null, "输入的日期格式有误，请按yyyy-MM-dd格式输入", "读者信息",JOptionPane.ERROR_MESSAGE);
				ReaderFrame.editPanl.txtBirthday.requestFocusInWindow();
				return;
			}
			if (reader.getTelephone().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "请填写读者联系电话", "读者信息",JOptionPane.WARNING_MESSAGE);
				ReaderFrame.editPanl.txtTelphone.requestFocusInWindow();
				return;
			}
			if (reader.getTelephone().trim().length() > 11) {
				JOptionPane.showMessageDialog(null, "联系电话长度不能超出11", "读者信息",JOptionPane.ERROR_MESSAGE);
				ReaderFrame.editPanl.txtTelphone.requestFocusInWindow();
				return;
			}
			
			
			
			
			boolean reValue = false;
			if (ReaderFrame.editPanl.flag.equals("edit")) {
				reValue = ReaderFrame.action.update(reader);
			} else {
				if (!ReaderFrame.action.beforeadd(reader.getReaderid().trim())){
					JOptionPane.showMessageDialog(null, "读者已添加过，请检查数据后重新录入读者编号", "读者信息",JOptionPane.ERROR_MESSAGE);
					ReaderFrame.editPanl.txtReaderid.requestFocusInWindow();
					return;
				}else{
					reValue = ReaderFrame.action.add(reader);
				}
			}
			if (reValue) {
				JOptionPane.showMessageDialog(null, "操作成功", "读者信息",JOptionPane.INFORMATION_MESSAGE);
				ret();
			} else {
				JOptionPane.showMessageDialog(null, "操作失败，请与系统管理员联系", "读者信息",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void del() {
		if (ReaderFrame.listPanl.table != null){
			int row = ReaderFrame.listPanl.table.getSelectedRow();
			if (row >= 0) {
				int i = JOptionPane.showConfirmDialog(null, "是否确认删除该行数据？", "确认",
						JOptionPane.YES_NO_OPTION);
				if (i == 1) {
					return;
				}
				String readerid = ReaderFrame.listPanl.table.getValueAt(row, 0)
						.toString();
				if (ReaderFrame.action.beforedel(readerid)){
					if (ReaderFrame.action.del(readerid)) {
						JOptionPane.showMessageDialog(null, "操作成功", "读者信息",JOptionPane.INFORMATION_MESSAGE);
						ret();
					} else {
						JOptionPane.showMessageDialog(null, "操作失败，请与系统管理员联系", "读者信息",JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "当前读者有借阅无法删除", "读者信息",JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "请选择要删除的数据行", "读者信息",JOptionPane.WARNING_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(null, "没有需要删除的数据行", "读者信息",JOptionPane.WARNING_MESSAGE);
		}
		
	}
}
