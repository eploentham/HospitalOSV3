/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hosv3.gui.dialog;

import com.hospital_os.object.Item;
import com.hospital_os.utility.TaBleModel;
import com.hospital_os.utility.TaBleModelN;
import com.hosv3.control.HosControl;
import com.hosv3.gui.component.CellEditorPanelRoom;
import com.hosv3.gui.component.PanelRoom;
import com.hosv3.object.Room;
//import com.hosv3.utility.CellRendererHotChartCheckBox;
//import com.hosv3.utility.HCheckBoxHotChartEditor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author LionHeartV
 */
public class PanelAllRoom extends javax.swing.JPanel {
    private HosControl theHC;
    private JDialog theJD;
    private Vector theRoomV;
    private int theRowSize;
    private String[][] room_name;
    private Room[][] theRoom;

    /**
     * Creates new form PanelAllRoom
     */
    public PanelAllRoom() {
        initComponents();
        jTable1.setRowHeight(72);
        jTable1.getTableHeader().setVisible(false);
        jTable1.getTableHeader().setPreferredSize(new Dimension(-1, 0));
    }
    public void setRoomV(Vector roomV,int row_size)
    {
        theRoomV = roomV;
        if(theRoomV == null)
            theRoomV = new Vector();
        try
        {
            theRowSize = row_size;
            int column = 1;
            if(theRoomV!=null && theRoomV.size() > 0)
            {
                column = theRoomV.size() / row_size;
                if(theRoomV.size() % row_size > 0)
                    column+=1;
            }
            int col_c = -1;
            int row_c = 0;
            if(column < 1)
                column = 1;
            String[] col = new String[column];
            room_name = new String[row_size][column];
            theRoom = new Room[row_size][column];
            for(int i=0;i<theRoomV.size();i++)
            {
                Room item = (Room)theRoomV.get(i);
                if(row_c%row_size==0)
                {
                    col_c+=1;
                    row_c=0;
                }
                room_name[row_c][col_c] = item.visit_room_number;
                theRoom[row_c][col_c] = item;
                row_c++;
            }


            TaBleModelN tm = new TaBleModelN(col,row_size);
            for(int row=0;row<tm.getRowCount();row++)
            {
                for(int c=0;c<tm.getColumnCount();c++)
                {
                    tm.setValueAt(room_name[row][c], row, c);
                }
            }
            jTable1.setModel(tm);
            PanelRoom[] panelRoom = new PanelRoom[jTable1.getColumnCount()];
            CellEditorPanelRoom[] cellEditorPanelRoom = new CellEditorPanelRoom[jTable1.getColumnCount()];
            JTextField[] jTextField = new JTextField[jTable1.getColumnCount()];
//            panelRoom.setRoomName("test");
//            panelRoom.setRoomSize(2);
//            CellRendererHotChartCheckBox theCellRendererCheckBox = new CellRendererHotChartCheckBox();
//            theCellRendererCheckBox.setOrderName(room_name);
//            HCheckBoxHotChartEditor order_home = new HCheckBoxHotChartEditor(new JCheckBox());
//            order_home.setOrderName(room_name);
//            order_home.setHosControl(theHC);
//            order_home.setItem(theRoom);
//            setModel(tm);
            if(jTable1.getColumnCount() > 0)
            {
                for(int i=0;i<jTable1.getColumnCount();i++)
                {
//                    getColumnModel().getColumn(i).setCellEditor(order_home);
                    panelRoom[i] = new PanelRoom();
                    jTextField[i] = new JTextField();
                    cellEditorPanelRoom[i] = new CellEditorPanelRoom(jTextField[i]);
                    cellEditorPanelRoom[i].setObject(panelRoom[i]);
                    panelRoom[i].setRoomSize(2);
                    panelRoom[i].setRoomName(room_name);
                    jTable1.getColumnModel().getColumn(i).setCellRenderer(panelRoom[i]);
                    jTable1.getColumnModel().getColumn(i).setCellEditor(cellEditorPanelRoom[i]);
                    jTable1.getColumnModel().getColumn(i).setPreferredWidth(90);
                }
                jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void setHosControl(HosControl hc)
    {
        theHC = hc;
    }
    public void showDialog()
    {
        if(theJD==null)
            theJD = new JDialog(theHC.theUS.getJFrame());
        theJD.add(this);
        theJD.setSize(1000,600);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        theJD.setLocation((screenSize.width - theJD.getSize().width) / 2, (screenSize.height - theJD.getSize().height) / 2);
//        theJD.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                
//            }
//            });
        theJD.setTitle("Room");
        theJD.setModal(true);
        theJD.setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane1, gridBagConstraints);

        jButton1.setText("jButton1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        add(jButton1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
