package com.cqeec.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import com.cqeec.action.BookAction;
import com.cqeec.entity.Book;

public class BookList extends JPanel {
	JScrollPane pane;
	JTable table = null;
	public BookList() {
		this.setLayout(new BorderLayout());
		BookFrame.saveBtn.setEnabled(false);
		BookFrame.returnBtn.setEnabled(false);
		BookFrame.addBtn.setEnabled(true);
		BookFrame.modBtn.setEnabled(true);
		BookFrame.delBtn.setEnabled(true);
		BookFrame.findBtn.setEnabled(true);
	}
	
	// 获得图书信息
	public void getBookList(Book book) {
		// TODO 其它控件
		String colName[] = { "图书编号", "图书名称", "图书类别","出版日期", "出版社"};
		String colValue[][] = null;
		BookAction action = new BookAction();
		// 获得全部数据
		colValue = action.getBookList(book);

		if (colValue == null) {
			BookFrame.lbState.setText("状态：没有找到图书信息");
			pane = new JScrollPane();
		} else {
			table = new JTable(colValue, colName) {
				public boolean isCellEditable(int row, int column) {
					// TODO 自动生成的方法存根
					return false;
				}
			};
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
			tcr.setHorizontalAlignment(SwingConstants.LEFT);
			table.setDefaultRenderer(Object.class, tcr);
			pane = new JScrollPane(table);
			BookFrame.lbState.setText("状态：共有" + colValue.length + "行数据");
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
