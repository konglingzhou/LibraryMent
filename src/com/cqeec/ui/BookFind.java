package com.cqeec.ui;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BookFind extends JPanel {
	JCheckBox cbBookid;
	JCheckBox cbBookName;
	JCheckBox cbType;
	JCheckBox cbDate;
	JCheckBox cbAdress;
	JLabel lbBookid;
	JTextField txtBookid;
	JLabel lbBookName;
	JTextField txtBookName;
	JLabel lbType;
	JTextField txtType;
	JLabel lbDate;
	JTextField txtStartDate, txtEndDate;
	JComboBox type;
	JLabel lbAdress;
	JTextField txtAdress;
	String flag = "";

	public BookFind() {
		BookFrame.lbState.setText("状态：请输入查询条件");
		BookFrame.saveBtn.setEnabled(false);
		BookFrame.returnBtn.setEnabled(true);
		BookFrame.addBtn.setEnabled(false);
		BookFrame.modBtn.setEnabled(false);
		BookFrame.delBtn.setEnabled(false);
		BookFrame.findBtn.setEnabled(true);
		this.setLayout(null);
		initGUI();
	}

	// GUI界面设置
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(443, 234));

			cbBookid = new JCheckBox();
			this.add(cbBookid);
			cbBookid.setBounds(27, 25, 23, 17);
			lbBookid = new JLabel();
			this.add(lbBookid);
			lbBookid.setText("图书编号:");
			lbBookid.setBounds(50, 26, 72, 15);
			txtBookid = new JTextField();
			this.add(txtBookid);
			txtBookid.setBounds(122, 23, 187, 22);

			cbBookName = new JCheckBox();
			this.add(cbBookName);
			cbBookName.setBounds(27, 65, 23, 17);
			lbBookName = new JLabel();
			this.add(lbBookName);
			lbBookName.setText("图书名称:");
			lbBookName.setBounds(50, 66, 72, 15);
			txtBookName = new JTextField();
			this.add(txtBookName);
			txtBookName.setBounds(122, 63, 187, 22);

			cbType = new JCheckBox();
			this.add(cbType);
			cbType.setBounds(27, 105, 23, 17);
			lbType = new JLabel();
			this.add(lbType);
			lbType.setText("图书类别:");
			lbType.setBounds(50, 106, 72, 15);
			ComboBoxModel xbBoxModel = new DefaultComboBoxModel(new String[] {
					"军事", "哲学","艺术","经济"});
			type = new JComboBox();
			this.add(type);
			type.setModel(xbBoxModel);
			type.setBounds(122, 103, 187, 22);
			
			cbDate = new JCheckBox();
			this.add(cbDate);
			cbDate.setBounds(27, 145, 23, 17);
			lbDate = new JLabel();
			this.add(lbDate);
			lbDate.setText("出版日期:");
			lbDate.setBounds(50, 146, 72, 15);
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
			
			
			cbAdress = new JCheckBox();
			this.add(cbAdress);
			cbAdress.setBounds(27, 185, 23, 17);
			lbAdress = new JLabel();
			this.add(lbAdress);
			lbAdress.setText("出版社:");
			lbAdress.setBounds(50, 186, 72, 15);
			txtAdress = new JTextField();
			this.add(txtAdress);
			txtAdress.setBounds(122, 183, 187, 22);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
