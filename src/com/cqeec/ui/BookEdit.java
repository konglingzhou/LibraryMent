package com.cqeec.ui;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BookEdit extends JPanel{
	JLabel lbBookid;
	JTextField txtBookid;
	JLabel lbBookName;
	JTextField txtBookName;
	JLabel lbDate;
	JTextField txtDate;
	JLabel lbAdress;
	JTextField txtAdress;
	JComboBox type;
	JLabel lbtype;
	String flag = "";
	public BookEdit(String flag) {
		this.flag = flag;
		BookFrame.lbState.setText("状态：数据填写后请保存");
		BookFrame.saveBtn.setEnabled(true);
		BookFrame.returnBtn.setEnabled(true);
		BookFrame.addBtn.setEnabled(false);
		BookFrame.modBtn.setEnabled(false);
		BookFrame.delBtn.setEnabled(false);
		BookFrame.findBtn.setEnabled(false);
		this.setLayout(null);
		initGUI(flag);
	}
    //GUI初始化
	private void initGUI(String flag) {
		try {
			lbBookid = new JLabel();
			this.add(lbBookid);
			lbBookid.setText("图书编号:");
			lbBookid.setBounds(50, 26, 72, 15);
			txtBookid = new JTextField();
			this.add(txtBookid);
			txtBookid.setBounds(122, 23, 187, 22);
			if (flag.equals("edit")){
				txtBookid.setEditable(false);
			}
			
			lbBookName = new JLabel();
			this.add(lbBookName);
			lbBookName.setText("图书名称:");
			lbBookName.setBounds(50, 66, 72, 15);
			txtBookName = new JTextField();
			this.add(txtBookName);
			txtBookName.setBounds(122, 63, 187, 22);

			lbtype = new JLabel();
			this.add(lbtype);
			lbtype.setText("图书类别:");
			lbtype.setBounds(50, 106, 72, 15);
			ComboBoxModel xbBoxModel = new DefaultComboBoxModel(new String[] {
					"军事", "哲学","艺术","经济"});
			type = new JComboBox();
			this.add(type);
			type.setModel(xbBoxModel);
			type.setBounds(122, 103, 187, 22);
			
			lbDate = new JLabel();
			this.add(lbDate);
			lbDate.setText("出版日期:");
			lbDate.setBounds(50, 146, 72, 15);
			txtDate = new JTextField();
			txtDate.setToolTipText("输入格式：yyyy-MM-dd");
			this.add(txtDate);
			txtDate.setBounds(122, 143, 187, 22);
			
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
