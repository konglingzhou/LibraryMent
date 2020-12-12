package com.cqeec.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import com.cqeec.action.ReaderAction;
import com.cqeec.entity.Reader;

public class ReaderList extends JPanel {
	JScrollPane pane;
	JTable table = null;
	public ReaderList() {
		this.setLayout(new BorderLayout());
		ReaderFrame.saveBtn.setEnabled(false);
		ReaderFrame.returnBtn.setEnabled(false);
		ReaderFrame.addBtn.setEnabled(true);
		ReaderFrame.modBtn.setEnabled(true);
		ReaderFrame.delBtn.setEnabled(true);
		ReaderFrame.findBtn.setEnabled(true);
	}

	// 获得读者信息
	public void getReaderList(Reader reader) {

		// 其它控件
		String colName[] = { "读者编号", "读者姓名", "性别","出生日期", "联系电话"};
		String colValue[][] = null;
		ReaderAction action = new ReaderAction();
		// 获得全部数据
		colValue = action.getReaderList(reader);

		if (colValue == null) {
			ReaderFrame.lbState.setText("状态：没有找到相关的读者信息");
			pane = new JScrollPane();
		} else {
			table = new JTable(colValue, colName) {
				public boolean isCellEditable(int row, int column) {
					// TODO Auto-generated method stub
					return false;
				}
			};
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
			tcr.setHorizontalAlignment(SwingConstants.LEFT);
			table.setDefaultRenderer(Object.class, tcr);
			pane = new JScrollPane(table);
			ReaderFrame.lbState.setText("状态：共有" + colValue.length + "行数据");
			// 调用设置JTable列属性方法
			setJTableColumn();
		}
		this.add(pane, BorderLayout.CENTER);
	}

	// 设置JTable列属性
	private void setJTableColumn() {

		TableColumn column;
		// 获得第一列
		column = table.getColumnModel().getColumn(0);
		// 设置列宽度
		column.setPreferredWidth(105);
		// 设置列不可拖大
		column.setResizable(false);

		// 设置列数据集中
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		column.setCellRenderer(render);

		column = table.getColumnModel().getColumn(1);
		column.setPreferredWidth(80);

		column = table.getColumnModel().getColumn(2);
		column.setPreferredWidth(30);
		column.setResizable(false);
		column.setCellRenderer(render);
		
		column = table.getColumnModel().getColumn(3);
		column.setPreferredWidth(80);
		column.setResizable(false);
		
		
		column = table.getColumnModel().getColumn(4);
		column.setPreferredWidth(80);
		column.setResizable(false);

		// 设置行高
		table.setRowHeight(22);

	}

}
