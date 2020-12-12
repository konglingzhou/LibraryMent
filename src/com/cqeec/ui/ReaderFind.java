package com.cqeec.ui;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ReaderFind extends JPanel {
	JCheckBox cbReaderid;
	JCheckBox cbReaderName;
	JCheckBox cbSex;
	JCheckBox cbBirthday;
	JCheckBox cbTelephone;
	JCheckBox cbAddress;
	JLabel lbReaderid;
	JTextField txtReaderid;
	JLabel lbReaderName;
	JTextField txtReaderName;
	JLabel lbSex;
	JTextField txtSex;
	JLabel lbBirthday;
	JTextField txtStartDate, txtEndDate;
	JComboBox sex;
	JLabel lbTelephone;
	JTextField txtTelephone;
	String flag = "";

	public ReaderFind() {
		ReaderFrame.lbState.setText("状态：请输入查询条件");
		ReaderFrame.saveBtn.setEnabled(false);
		ReaderFrame.returnBtn.setEnabled(true);
		ReaderFrame.addBtn.setEnabled(false);
		ReaderFrame.modBtn.setEnabled(false);
		ReaderFrame.delBtn.setEnabled(false);
		ReaderFrame.findBtn.setEnabled(true);
		this.setLayout(null);
		initGUI();
	}

	// GUI界面设置
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(443, 234));

			cbReaderid = new JCheckBox();
			this.add(cbReaderid);
			cbReaderid.setBounds(27, 25, 23, 17);
			lbReaderid = new JLabel();
			this.add(lbReaderid);
			lbReaderid.setText("读者编号:");
			lbReaderid.setBounds(50, 26, 72, 15);
			txtReaderid = new JTextField();
			this.add(txtReaderid);
			txtReaderid.setBounds(122, 23, 187, 22);

			cbReaderName = new JCheckBox();
			this.add(cbReaderName);
			cbReaderName.setBounds(27, 65, 23, 17);
			lbReaderName = new JLabel();
			this.add(lbReaderName);
			lbReaderName.setText("读者姓名:");
			lbReaderName.setBounds(50, 66, 72, 15);
			txtReaderName = new JTextField();
			this.add(txtReaderName);
			txtReaderName.setBounds(122, 63, 187, 22);

			cbSex = new JCheckBox();
			this.add(cbSex);
			cbSex.setBounds(27, 105, 23, 17);
			lbSex = new JLabel();
			this.add(lbSex);
			lbSex.setText("读者性别:");
			lbSex.setBounds(50, 106, 72, 15);
			ComboBoxModel xbBoxModel = new DefaultComboBoxModel(new String[] {
					"男", "女"});
			sex = new JComboBox();
			this.add(sex);
			sex.setModel(xbBoxModel);
			sex.setBounds(122, 103, 187, 22);
			
			cbBirthday = new JCheckBox();
			this.add(cbBirthday);
			cbBirthday.setBounds(27, 145, 23, 17);
			lbBirthday = new JLabel();
			this.add(lbBirthday);
			lbBirthday.setText("出生日期:");
			lbBirthday.setBounds(50, 146, 72, 15);
			txtStartDate = new JTextField();
			this.add(txtStartDate);
			txtStartDate.setToolTipText("输入格式：yyyy-MM-dd");
			txtStartDate.setBounds(122, 143, 187, 22);
			JLabel label1 = new JLabel("至");
			this.add(label1);
			label1.setBounds(320, 143, 72, 15);
			txtEndDate = new JTextField();
			txtEndDate.setToolTipText("输入格式：yyyy-MM-dd");
			this.add(txtEndDate);
			txtEndDate.setBounds(345, 143, 187, 22);
			
			
			cbTelephone = new JCheckBox();
			this.add(cbTelephone);
			cbTelephone.setBounds(27, 185, 23, 17);
			lbTelephone = new JLabel();
			this.add(lbTelephone);
			lbTelephone.setText("联系电话:");
			lbTelephone.setBounds(50, 186, 72, 15);
			txtTelephone = new JTextField();
			this.add(txtTelephone);
			txtTelephone.setBounds(122, 183, 187, 22);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
