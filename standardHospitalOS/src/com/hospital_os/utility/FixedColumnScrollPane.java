/*
 * FixedColumnScrollPane.java
 *
 * Created on 13 มิถุนายน 2549, 17:08 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.utility;

/**
 *
 * @author Padungrat(tong)
 */

import java.awt.BorderLayout;
 
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
 

 
public class FixedColumnScrollPane extends JScrollPane 
{
        public FixedColumnScrollPane()
        {
        }
        /**
         *Fix คอลัมภ์ ไม่สามารถกำหนด DefaultTableCellRenderer ได้
         *@param main เป็น JTable ที่เก็บชื่อตารางต้องการ Fix
         *@param fixedColumns เป็น integer ที่เก็บลำดับของคอลัมภ์ที่ต้องการ Fix
         *@param size เป็น integer ที่เก็บขนาดของคอลัมภ์ที่ต้องการ Fix
         */
        public void setFixedColumnScrollPane(JTable main, int fixedColumns, int size)
        {
        	
          //      scrollPane.setViewportView(main);
		//  Use the table to create a new table sharing
		//  the DataModel and ListSelectionModel

		JTable fixed = new JTable(main.getModel());

		fixed.setFocusable(false);
		fixed.setSelectionModel(main.getSelectionModel());
		fixed.getTableHeader().setReorderingAllowed(false);
                
                //amp:14/6/2549
                if(fixedColumns > 0)
                {
                    fixed.getColumnModel().getColumn(fixedColumns-1).setPreferredWidth(size);
                }

		fixed.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		main.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
 
		//  Remove the fixed columns from the main table
 
		for (int i = 0; i < fixedColumns; i++) {
			TableColumnModel columnModel = main.getColumnModel();
			columnModel.removeColumn(columnModel.getColumn(i));
		}
 
		//  Remove the non-fixed columns from the fixed table
		while (fixed.getColumnCount() > fixedColumns) {
			TableColumnModel columnModel = fixed.getColumnModel();
			columnModel.removeColumn(columnModel.getColumn(fixedColumns));
		}
 
		//  Add the fixed table to the scroll pane
 
		fixed.setPreferredScrollableViewportSize(fixed.getPreferredSize());
		setRowHeaderView(fixed);
		setCorner(JScrollPane.UPPER_LEFT_CORNER, fixed.getTableHeader());
                
                
        }
        
        /**
         *Fix คอลัมภ์สามารถกำหนด DefaultTableCellRenderer ได้
         *@param main เป็น JTable ที่เก็บชื่อตารางต้องการ Fix
         *@param fixedColumns เป็น integer ที่เก็บลำดับของคอลัมภ์ที่ต้องการ Fix
         *@param size เป็น integer ที่เก็บขนาดของคอลัมภ์ที่ต้องการ Fix
         *@param renderer เป็น DefaultTableCellRenderer ที่เก็บลักษณะของคอลัมภ์ว่าต้องการเซ็ตให้อยู่ทางซ้าย ทางขวา ก็อยู่ตรงกลาง
         *@Modifier pu
         *@Date 27/07/2006
         */
        public void setFixedColumnScrollPane(JTable main, int fixedColumns, int size ,DefaultTableCellRenderer renderer)
        {
           
            JTable fixed = new JTable(main.getModel());
            
            fixed.setFocusable(false);
            fixed.setSelectionModel(main.getSelectionModel());
            fixed.getTableHeader().setReorderingAllowed(false);
            
            //amp:14/6/2549
            if(fixedColumns>0)
            {
                fixed.getColumnModel().getColumn(fixedColumns-1).setPreferredWidth(size);
                fixed.getColumnModel().getColumn(fixedColumns-1).setCellRenderer(renderer);
            }
            
            fixed.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
            main.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
            
            //  Remove the fixed columns from the main table
            
            for (int i = 0; i < fixedColumns; i++)
            {
                TableColumnModel columnModel = main.getColumnModel();
                columnModel.removeColumn(columnModel.getColumn(i));
            }
            
            //  Remove the non-fixed columns from the fixed table
            while (fixed.getColumnCount() > fixedColumns)
            {
                TableColumnModel columnModel = fixed.getColumnModel();
                columnModel.removeColumn(columnModel.getColumn(fixedColumns));
            }
            
            //  Add the fixed table to the scroll pane
            
            fixed.setPreferredScrollableViewportSize(fixed.getPreferredSize());
            setRowHeaderView(fixed);
            setCorner(JScrollPane.UPPER_LEFT_CORNER, fixed.getTableHeader());    
        }        
	
 
	public static void main(String[] args) {
		//  Build your table normally
 
		String[] columnNames = new String[] { "Date", "Name",
				"Errored Seconds", "Reinit", "Code Violations" };
 
		Object[][] data = new Object[][] {
				{ "02-14-1994", "Narla", new Integer(2), new Integer(5),
						new Double(1.1) },
				{ "06-14-1994", "Ramineni", new Integer(2), new Integer(3),
						new Double(3) },
				{ "05-14-1994", "Walrath", new Integer(4), new Integer(2),
						new Double(1.2) },
				{ "04-14-1994", "Zakhour", new Integer(0), new Integer(20),
						new Double(10) },
				{ "03-14-1994", "Milne", new Integer(2), new Integer(10),
						new Double(1.5) } };
 
		TableModel tm = new DefaultTableModel(data, columnNames);
		
		JTable table = new JTable();
                table.setModel(tm);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		FixedColumnScrollPane scrollPane = new FixedColumnScrollPane();
                scrollPane.setViewportView(table);
                
                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
                renderer.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
                
                scrollPane.setFixedColumnScrollPane(table,1,150);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
 
		JFrame frame = new JFrame("Table Fixed Column Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(BorderLayout.CENTER, scrollPane);
		frame.setSize(300, 150);
		frame.setVisible(true);
	}
}
