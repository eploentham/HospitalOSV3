/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewJPanel1.java
 *
 * Created on 30 พ.ย. 2552, 17:01:34
 */

package com.report12file.gui;

import com.hosv3.control.LookupControl;
import com.hosv3.control.MapCon;
import com.hosv3.gui.component.TTableCellRenderer;
import com.hosv3.object.HosObject;
import com.hosv3.utility.DialogConfig;
import com.hosv3.utility.connection.UpdateStatus;
import com.report12file.control.MapControl;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author henbe
 */
public class PanelMapADPCode extends javax.swing.JPanel {

    private MapControl theMC;
    private long firstClickTime;
    String[] colTable = new String[]{"ข้อมูลตั้งต้น","ข้อมูลจับคู่"};
    String[] colLookup = new String[]{"ข้อมูลจับคู่"};
    private Vector vLookup;
    private Vector vMap;
    TTableCellRenderer tt = new TTableCellRenderer();
    private UpdateStatus theUS;
    private HosObject theHO;
    /** Creates new form NewJPanel1 */
    public PanelMapADPCode() {
        initComponents();
    }
    public void setTitle(String str){
        this.jLabel1.setText(str);
    }
    public void setControl(MapControl mapCon,UpdateStatus us,HosObject ho,LookupControl lc){
        theMC = mapCon;
        theUS = us;
        theHO = ho;
        MapCon mc1 = new MapCon(MapCon.LOOK_ITEMCATEGORY,theUS,lc.theConnectionInf);
        this.jComboBoxGroup.setControl(mc1,true);
        jCheckBoxCompany.setVisible(false);
        jComboBoxCompany.setVisible(false);
//        MapControl mc2 = new MapControl(Report12FileData.LOOK_COMPANY,theUS,lc.theConnectionInf);
//        this.jComboBoxCompany.setControl(mc2,true);
    }

    private JLabel initJLabel2(String[] data) {
        JLabel jl = new JLabel();
        jl.setOpaque(true);
        if(data[4]==null)
            return jl;
        jl.setText(data[4]);
        String[] datas = data[5].split(",");
        StringBuffer datass = new StringBuffer();
        datass.append("<html><body>");
        for(int j=0;j<datas.length;j++){
            if(j!=0)
                datass.append("<br>");
            datass.append(datas[j]);
        }
        datass.append("</body></html>");
        jl.setToolTipText(datass.toString());
        return jl;
    }
    private JLabel initJLabel(String[] data) {
        JLabel jl = new JLabel();
            jl.setText(data[1]);
            jl.setOpaque(true);
            String[] datas = data[2].split(",");
            StringBuffer datass = new StringBuffer();
            datass.append("<html><body>");
            for(int j=0;j<datas.length;j++){
                if(j!=0)
                    datass.append("<br>");
                datass.append(datas[j]);
            }
            datass.append("</body></html>");
            jl.setToolTipText(datass.toString());
            return jl;
    }

    public void setMapV(Vector listData) {
        vMap = listData;
        DefaultTableModel dlm = new DefaultTableModel(colTable,listData.size());
        for(int i=0;i<listData.size();i++){
            String[] data = (String[])listData.get(i);
//            theUS.setStatus(data[4]);
            JLabel jl = initJLabel(data);
            JLabel jl2 = initJLabel2(data);
            dlm.setValueAt(jl, i, 0);
            dlm.setValueAt(jl2, i, 1);
        }
        this.jTableMap.setModel(dlm);
//         TableRowSorter sorter = new TableRowSorter(dlm);
//         jTableMap.setRowSorter(sorter);
        this.jTableMap.getColumnModel().getColumn(0).setCellRenderer(tt);
        this.jTableMap.getColumnModel().getColumn(1).setCellRenderer(tt);
        this.jTableMap.getColumnModel().getColumn(0).setCellEditor(tt);
        this.jTableMap.getColumnModel().getColumn(1).setCellEditor(tt);
    }

    private void dbclick() {
        long clickTime = System.currentTimeMillis();
        long clickInterval = clickTime - firstClickTime;
        if(clickInterval < 400)
        {
            theMC.mapData(getMapV(),getLookup());
            String str = jTextFieldMap.getText();
            if(this.jCheckBoxGroup.isSelected())
                str = jTextFieldMap.getText()+" "+this.jComboBoxGroup.getDetail();
            this.setMapV(theMC.listMap(str,jCheckBox1.isSelected()));
            firstClickTime = 0;
        }
        else
            firstClickTime = clickTime;
    }
    private void dbclickMap() {
        long clickTime = System.currentTimeMillis();
        long clickInterval = clickTime - firstClickTime;
        if(clickInterval < 400)
        {
            this.jTextFieldLookup1.setText("%"+getMap()[1]);
            this.jTextFieldLookup1.requestFocus();
            this.jTextFieldLookup1ActionPerformed(null);
            firstClickTime = 0;
        }
        else
            firstClickTime = clickTime;
    }
    public String[] getLookup(){
        int index = this.jTableLookup.getSelectedRow();
        if(index==-1)
            return null;
        String[] data = (String[])vLookup.get(index);
        return data;
    }
    public String[] getMap(){
        int index = this.jTableMap.getSelectedRow();
        if(index==-1)
            return null;
        String[] data = (String[])vMap.get(index);
        return data;
    }
    public Vector getMapV(){
        int[] index = this.jTableMap.getSelectedRows();
        if(index.length <= 0)
            return null;
        Vector v = new Vector();
        for (int i = 0; i < index.length; i++) {
            String[] data = (String[])vMap.get(index[i]);
            v.add(data);
        }
        return v;
    }
    private void setLookupV(Vector listData) {
        vLookup = listData;
        DefaultTableModel dlm = new DefaultTableModel(colLookup,vLookup.size());
        for(int i=0;i<listData.size();i++){
            String[] data = (String[])listData.get(i);
            JLabel jl = this.initJLabel(data);
            dlm.setValueAt(jl,i,0);
        }
        this.jTableLookup.setModel(dlm);
//         TableRowSorter sorter = new TableRowSorter(dlm);
//         jTableLookup.setRowSorter(sorter);
        this.jTableLookup.getColumnModel().getColumn(0).setCellRenderer(tt);
        this.jTableLookup.getColumnModel().getColumn(0).setCellEditor(tt);

    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        fontFormatTitle1 = new com.hospital_os.gui.font.FontFormatTitle();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableMap = new javax.swing.JTable();
        jTextFieldMap = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextFieldLookup1 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableLookup = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButtonImportMap = new javax.swing.JButton();
        jCheckBoxCompany = new javax.swing.JCheckBox();
        jCheckBoxGroup = new javax.swing.JCheckBox();
        jComboBoxGroup = new com.hosv3.gui.component.HosComboBox();
        jComboBoxCompany = new com.hosv3.gui.component.HosComboBox();

        setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setMinimumSize(new java.awt.Dimension(452, 402));

        jTableMap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableMapMouseReleased(evt);
            }
        });
        jTableMap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableMapKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTableMap);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(jScrollPane2, gridBagConstraints);

        jTextFieldMap.setColumns(30);
        jTextFieldMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMapActionPerformed(evt);
            }
        });
        jTextFieldMap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldMapKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 3);
        add(jTextFieldMap, gridBagConstraints);

        jCheckBox1.setText("ยังไม่จับคู่");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(jCheckBox1, gridBagConstraints);

        jTextFieldLookup1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLookup1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 3);
        add(jTextFieldLookup1, gridBagConstraints);

        jTableLookup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableLookupMouseReleased(evt);
            }
        });
        jTableLookup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableLookupKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTableLookup);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(jScrollPane3, gridBagConstraints);

        jLabel1.setFont(fontFormatTitle1);
        jLabel1.setText("รายการจับคู่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jLabel1, gridBagConstraints);

        jButtonImportMap.setText("นำเข้าข้อมูลจับคู่");
        jButtonImportMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImportMapActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        add(jButtonImportMap, gridBagConstraints);

        jCheckBoxCompany.setText("บริษัท");
        jCheckBoxCompany.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxCompanyMouseClicked(evt);
            }
        });
        jCheckBoxCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxCompanyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        add(jCheckBoxCompany, gridBagConstraints);

        jCheckBoxGroup.setText("กลุ่ม");
        jCheckBoxGroup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxGroupMouseClicked(evt);
            }
        });
        jCheckBoxGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxGroupActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        add(jCheckBoxGroup, gridBagConstraints);

        jComboBoxGroup.setDoubleBuffered(true);
        jComboBoxGroup.setEnabled(false);
        jComboBoxGroup.setMinimumSize(new java.awt.Dimension(137, 21));
        jComboBoxGroup.setPreferredSize(new java.awt.Dimension(137, 21));
        jComboBoxGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxGroupActionPerformed(evt);
            }
        });
        jComboBoxGroup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBoxGroupKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 3);
        add(jComboBoxGroup, gridBagConstraints);

        jComboBoxCompany.setDoubleBuffered(true);
        jComboBoxCompany.setEnabled(false);
        jComboBoxCompany.setMinimumSize(new java.awt.Dimension(137, 21));
        jComboBoxCompany.setPreferredSize(new java.awt.Dimension(137, 21));
        jComboBoxCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCompanyActionPerformed(evt);
            }
        });
        jComboBoxCompany.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBoxCompanyKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 3);
        add(jComboBoxCompany, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMapActionPerformed
        String str = jTextFieldMap.getText();
        if(this.jCheckBoxGroup.isSelected())
            str = jTextFieldMap.getText()+" "+this.jComboBoxGroup.getDetail();
        this.setMapV(theMC.listMap(str,jCheckBox1.isSelected()));
}//GEN-LAST:event_jTextFieldMapActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        String str = jTextFieldMap.getText();
        if(this.jCheckBoxGroup.isSelected())
            str = jTextFieldMap.getText()+" "+this.jComboBoxGroup.getDetail();
        this.setMapV(theMC.listMap(str,jCheckBox1.isSelected()));
}//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jTextFieldMapKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldMapKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT)
            this.jTextFieldMap.requestFocus();
        if(evt.getKeyCode()==KeyEvent.VK_DOWN)
            this.jTableMap.requestFocus();
}//GEN-LAST:event_jTextFieldMapKeyReleased

    private void jTableMapKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableMapKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_TAB)
            this.jTextFieldMap.requestFocus();
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT)
            this.jTextFieldMap.requestFocus();
        if(evt.getKeyCode()==KeyEvent.VK_DELETE){
            this.theMC.deleteMap(getMap());
            String str = jTextFieldMap.getText();
            if(this.jCheckBoxGroup.isSelected())
                str = jTextFieldMap.getText()+" "+this.jComboBoxGroup.getDetail();
            this.setMapV(theMC.listMap(str,jCheckBox1.isSelected()));
        }
}//GEN-LAST:event_jTableMapKeyReleased

    private void jTableMapMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMapMouseReleased
        this.dbclickMap();
}//GEN-LAST:event_jTableMapMouseReleased

    private void jTextFieldLookup1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLookup1ActionPerformed
        String str = this.jTextFieldLookup1.getText();
        if(this.jCheckBoxCompany.isSelected())
            str = this.jTextFieldLookup1.getText()+" "+this.jComboBoxCompany.getDetail();
        setLookupV(theMC.listLookup(str));
}//GEN-LAST:event_jTextFieldLookup1ActionPerformed

    private void jTableLookupMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLookupMouseReleased
        this.dbclick();
}//GEN-LAST:event_jTableLookupMouseReleased

    private void jTableLookupKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableLookupKeyReleased
        // TODO add your handling code here:
}//GEN-LAST:event_jTableLookupKeyReleased

    private void jButtonImportMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImportMapActionPerformed
        if(theHO.target_db==null){
            theHO.target_db = DialogConfig.readDbFile();
        }
        String local_url = new String(theHO.local_db[0]+theHO.local_db[1]);
        DialogConfig.showDialog(theUS.getJFrame(), "เลือกฐานข้อมูลจับคู่", theHO.target_db, false);
        if(local_url.equals(theHO.target_db[0]+theHO.target_db[1])){
            theUS.setStatus("กรุณาเลือกฐานข้อมูลอื่นที่ไม่ใช่ฐานข้อมูลปัจจุบัน",UpdateStatus.WARNING);
            return;
        }
        theMC.importMap(theHO.target_db);
    }//GEN-LAST:event_jButtonImportMapActionPerformed

    private void jCheckBoxCompanyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxCompanyMouseClicked

}//GEN-LAST:event_jCheckBoxCompanyMouseClicked

    private void jCheckBoxCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxCompanyActionPerformed
        this.jComboBoxCompany.setEnabled(this.jCheckBoxCompany.isSelected());
        String str = this.jTextFieldLookup1.getText();
        if(this.jCheckBoxCompany.isSelected())
            str = this.jTextFieldLookup1.getText()+" "+this.jComboBoxCompany.getDetail();
        setLookupV(theMC.listLookup(str));
}//GEN-LAST:event_jCheckBoxCompanyActionPerformed

    private void jCheckBoxGroupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxGroupMouseClicked
        // TODO add your handling code here:
}//GEN-LAST:event_jCheckBoxGroupMouseClicked

    private void jCheckBoxGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxGroupActionPerformed
        this.jComboBoxGroup.setEnabled(this.jCheckBoxGroup.isSelected());
        String str = jTextFieldMap.getText();
        if(this.jCheckBoxGroup.isSelected())
            str = jTextFieldMap.getText()+" "+this.jComboBoxGroup.getDetail();
        this.setMapV(theMC.listMap(str,jCheckBox1.isSelected()));
}//GEN-LAST:event_jCheckBoxGroupActionPerformed

    private void jComboBoxGroupKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxGroupKeyReleased

    }//GEN-LAST:event_jComboBoxGroupKeyReleased

    private void jComboBoxCompanyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxCompanyKeyReleased

    }//GEN-LAST:event_jComboBoxCompanyKeyReleased

    private void jComboBoxGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxGroupActionPerformed
        String str = jTextFieldMap.getText();
        if(this.jCheckBoxGroup.isSelected())
            str = jTextFieldMap.getText()+" "+this.jComboBoxGroup.getDetail();
        this.setMapV(theMC.listMap(str,jCheckBox1.isSelected()));
    }//GEN-LAST:event_jComboBoxGroupActionPerformed

    private void jComboBoxCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCompanyActionPerformed
        String str = this.jTextFieldLookup1.getText();
        if(this.jCheckBoxCompany.isSelected())
            str = this.jTextFieldLookup1.getText()+" "+this.jComboBoxCompany.getDetail();

        setLookupV(theMC.listLookup(str));
    }//GEN-LAST:event_jComboBoxCompanyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.gui.font.FontFormatTitle fontFormatTitle1;
    private javax.swing.JButton jButtonImportMap;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBoxCompany;
    private javax.swing.JCheckBox jCheckBoxGroup;
    private com.hosv3.gui.component.HosComboBox jComboBoxCompany;
    private com.hosv3.gui.component.HosComboBox jComboBoxGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableLookup;
    private javax.swing.JTable jTableMap;
    private javax.swing.JTextField jTextFieldLookup1;
    private javax.swing.JTextField jTextFieldMap;
    // End of variables declaration//GEN-END:variables

}
