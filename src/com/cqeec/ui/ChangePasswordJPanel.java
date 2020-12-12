package com.cqeec.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.cqeec.action.UserAction;

public class ChangePasswordJPanel {
	static JInternalFrame changePasswordJPanel = null;
	static JPasswordField old_pwd, new_pwd, affirm_pwd;
	static JButton save;

	public ChangePasswordJPanel() {

	}

	public static void pwdd(JDesktopPane deskPane) {
		// TODO 自动生成的方法存根
		if (changePasswordJPanel == null) {
			changePasswordJPanel = new JInternalFrame("修改密码", false, true, false, true);
			changePasswordJPanel.setSize(400,400);
			deskPane.add(changePasswordJPanel);
			final Container container = changePasswordJPanel.getContentPane();
			container.setLayout(null);
			JLabel lbl_old = new JLabel("旧密码：");
			container.add(lbl_old);
			lbl_old.setBounds(50, 20, 100, 25);
			old_pwd = new JPasswordField();
			container.add(old_pwd);
			old_pwd.setEchoChar('*');
			old_pwd.setBounds(120, 20, 160, 25);

			JLabel lbl_new = new JLabel("新密码：");
			container.add(lbl_new);
			lbl_new.setBounds(50, 70, 100, 25);
			new_pwd = new JPasswordField();
			container.add(new_pwd);
			new_pwd.setEchoChar('*');
			new_pwd.setBounds(120, 70, 160, 25);

			JLabel lbl_confirm = new JLabel("确认密码：");
			container.add(lbl_confirm);
			lbl_confirm.setBounds(50, 120, 100, 25);
			affirm_pwd = new JPasswordField();
			container.add(affirm_pwd);
			affirm_pwd.setEchoChar('*');
			affirm_pwd.setBounds(120, 120, 160, 25);

			save = new JButton("保存");
			container.add(save);
			save.setBounds(140, 167, 80, 25);
			save.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					if (old_pwd.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "请输入原始密码", "修改密码", JOptionPane.WARNING_MESSAGE);
						old_pwd.requestFocusInWindow();
						return;
					}
					if (new_pwd.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "请输入新密码", "修改密码", JOptionPane.WARNING_MESSAGE);
						new_pwd.requestFocusInWindow();
						return;
					}
					if (affirm_pwd.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "请输入确认密码", "修改密码", JOptionPane.WARNING_MESSAGE);
						affirm_pwd.requestFocusInWindow();
						return;
					}
					if (!affirm_pwd.getText().trim().equals(new_pwd.getText().trim())) {
						JOptionPane.showMessageDialog(null, "新密码与确认密码不一致", "修改密码", JOptionPane.ERROR_MESSAGE);
						new_pwd.requestFocusInWindow();
						return;
					}
					if (!old_pwd.getText().trim().equals(MainFrame.user.getUserpwd())) {
						JOptionPane.showMessageDialog(null, "原始密码错误", "修改密码", JOptionPane.ERROR_MESSAGE);
						old_pwd.requestFocusInWindow();
						return;
					}
					UserAction action = new UserAction();
					if (action.ChangePassword(new_pwd.getText().trim(), MainFrame.user.getUsername())) {
						JOptionPane.showMessageDialog(null, "操作成功", "修改密码", JOptionPane.INFORMATION_MESSAGE);
						// 将修改过后的密码重新存放在userinfo对象中
						MainFrame.user.setUserpwd(new_pwd.getText().trim());
						old_pwd.setText("");
						new_pwd.setText("");
						affirm_pwd.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "操作失败，请与系统管理员联系", "修改密码", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			changePasswordJPanel.setVisible(true);
		}
		changePasswordJPanel.addInternalFrameListener(new InternalFrameAdapter() {
			public void internalFrameClosing(InternalFrameEvent e) {
				old_pwd = null;
				new_pwd = null;
				affirm_pwd = null;
				save = null;
				changePasswordJPanel = null;
			}

		});
	}
}
