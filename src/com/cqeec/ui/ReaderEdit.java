package com.cqeec.ui;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReaderEdit extends JPanel{
	JLabel lbReaderid;
	JTextField txtReaderid;
	JLabel lbReaderName;
	JTextField txtReaderName;
	JLabel lbBirthday;
	JTextField txtBirthday;
	JLabel lbTelphone;
	JTextField txtTelphone;
	JComboBox sex;
	JLabel lbsex;
	String flag = "";
	public ReaderEdit(String flag) {
		this.flag = flag;
		ReaderFrame.lbState.setText("状态：数据填写后请保存");
		ReaderFrame.saveBtn.setEnabled(true);
		ReaderFrame.returnBtn.setEnabled(true);
		ReaderFrame.addBtn.setEnabled(false);
		ReaderFrame.modBtn.setEnabled(false);
		ReaderFrame.delBtn.setEnabled(false);
		ReaderFrame.findBtn.setEnabled(false);
		this.setLayout(null);
		initGUI(flag);
	}
    //GUI初始化
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initGUI(String flag) {
		try {
			lbReaderid = new JLabel();
			this.add(lbReaderid);
			lbReaderid.setText("读者编号:");
			lbReaderid.setBounds(50, 26, 72, 15);
			txtReaderid = new JTextField();
			this.add(txtReaderid);
			txtReaderid.setBounds(122, 23, 187, 22);
			if (flag.equals("edit")){
				txtReaderid.setEditable(false);
			}
			
			lbReaderName = new JLabel();
			this.add(lbReaderName);
			lbReaderName.setText("读者姓名:");
			lbReaderName.setBounds(50, 66, 72, 15);
			txtReaderName = new JTextField();
			this.add(txtReaderName);
			txtReaderName.setBounds(122, 63, 187, 22);

			lbsex = new JLabel();
			this.add(lbsex);
			lbsex.setText("性别:");
			lbsex.setBounds(50, 106, 72, 15);
			ComboBoxModel xbBoxModel = new DefaultComboBoxModel(new String[] {
					"男", "女"});
			sex = new JComboBox();
			this.add(sex);
			sex.setModel(xbBoxModel);
			sex.setBounds(122, 103, 187, 22);
			
			lbBirthday = new JLabel();
			this.add(lbBirthday);
			lbBirthday.setText("出生日期:");
			lbBirthday.setBounds(50, 146, 72, 15);
			txtBirthday = new JTextField();
			txtBirthday.setToolTipText("输入格式：yyyy-MM-dd");
			this.add(txtBirthday);
			txtBirthday.setBounds(122, 143, 187, 22);
			
			lbTelphone = new JLabel();
			this.add(lbTelphone);
			lbTelphone.setText("联系电话:");
			lbTelphone.setBounds(50, 186, 72, 15);
			txtTelphone = new JTextField();
			this.add(txtTelphone);
			txtTelphone.setBounds(122, 183, 187, 22);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
