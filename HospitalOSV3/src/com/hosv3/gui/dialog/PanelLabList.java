/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LabList.java
 *
 * Created on 6 พ.ค. 2553, 10:00:48
 */

package com.hosv3.gui.dialog;

import com.hospital_os.usecase.connection.UpdateStatus;
import com.hospital_os.utility.TaBleModel;
import com.hosv3.control.HosControl;
import com.hosv3.control.LabControl;
import com.hosv3.object.LabList;
import com.hosv3.usecase.transaction.ManageOrderResp;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author LionHearth
 */
public class PanelLabList extends javax.swing.JPanel implements ManageOrderResp {

    /** Creates new form LabList */
    JDialog theJD;
    LabControl theLabControl;
    String list_col[] = {"รายการแลป","สิ่งส่งตรวจ"};
    String list_cols[] = {"LN","รายการ","สิ่งส่งตรวจ","เวลาดำเนินการ","สถานะ"};
    private JFrame theJF;
    HosControl theHosControl; 
    Vector<LabList> theLabListV;
    Vector<LabList> theLabListV_tmp;
    String type = "";
    public PanelLabList() {
        initComponents();
    }
    public void setControl(HosControl hosControl,LabControl labControl)
    {
        theHosControl = hosControl;
        theLabControl = labControl;
        theJF = theHosControl.theUS.getJFrame();
        jCheckBoxPreview.setVisible(false);
        theHosControl.theHS.theOrderSubject.attachManageOrder(this);
//        File f = new File("ln.txt");
//        if(f.exists())
//        {
//            BufferedReader in;
//            try {
//                in = new BufferedReader(new FileReader("ln.txt"));
//                String st1;
//                String st2 = new String();
//                while ((st1 = in.readLine()) != null) {
//                    st2 += st1;
//                }
//                in.close();
//                if(st2.equals("1ln"))
//                {
//                    jCheckBoxLN.setSelected(true);
//                }
//                else if(st2.equals("mln"))
//                {
//                    jCheckBoxLN.setSelected(false);
//                }
//            } catch (Exception ex) {
//                Logger.getLogger(PanelLabList.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }
    }
    public void setCheck(boolean b)
    {
        jCheckBoxSpecimenView.setSelected(b);
    }
    public void showDialog(Vector<LabList> v,String t)
    {
        if(theJD==null)
            theJD = new JDialog(theJF);
        theJD.add(this);
        theJD.setSize(700,300);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        theJD.setLocation((screenSize.width - theJD.getSize().width) / 2, (screenSize.height - theJD.getSize().height) / 2);
        if(jCheckBoxSpecimenView.isSelected())
        {
            Vector<LabList> tmpV = this.theLabControl.convertToSpecimenView(theLabListV);
            this.setLabListV(tmpV);
        }
        else
        {
            this.setLabListV(v);
        }
        theJD.setTitle("รายการแลป");
        jTableList.selectAll();
        theJD.setModal(true);
        type = t;
        theJD.setVisible(true);
    }
    public void setLabListV(Vector<LabList> v)
    {
        theLabListV = v;
        TaBleModel tm = null ;
        if(theLabListV==null)
        {
            tm = new TaBleModel(list_cols,0);
            jTableList.setModel(tm);
            return;
        }
        boolean type_new = false;
        for(int i=0;i<theLabListV.size();i++)
        {//{"LN","รายการ","Specimen","สถานะ"};

            LabList tmp = theLabListV.get(i);
            if(i==0)
            {
                if(tmp.type.equals(LabList.TYPE_NEW))
                    tm = new TaBleModel(list_cols,theLabListV.size());
                else
                    tm = new TaBleModel(list_col,theLabListV.size());
            }
//
            if(tmp.type.equals(LabList.TYPE_NEW))
            {
                tm.setValueAt(tmp.ln, i, 0);
                tm.setValueAt(tmp.order_name, i, 1);
                tm.setValueAt(tmp.item_nick_name, i, 2);
                if(tmp.status.equals("0"))
                    tm.setValueAt("<html><body><font color=green>ยกเลิก</font></body></html>", i, 4);
                if(tmp.status.equals("1"))
                    tm.setValueAt("<html><body><font color=green>ยืนยัน</font></body></html>", i, 4);
                if(tmp.status.equals("2"))
                    tm.setValueAt("<html><body><font color=green>ส่งเข้า LIS แล้ว</font></body></html>", i, 4);
                tm.setValueAt(tmp.order_execute_date.substring(0,16), i, 3);
                type_new = true;
            }
            else
            {
                tm.setValueAt(tmp.order_name, i, 0);
                tm.setValueAt(tmp.item_nick_name, i, 1);
            }
            
        }
        System.err.println("DEBUG");
        System.err.println("jTableList == null " + jTableList==null);
        System.err.println("tm == null " + tm==null);
        jTableList.setModel(tm);
        if(type_new)
        {
            jTableList.getColumnModel().getColumn(0).setPreferredWidth(78);
            jTableList.getColumnModel().getColumn(1).setPreferredWidth(190);
            jTableList.getColumnModel().getColumn(2).setPreferredWidth(140);
            jTableList.getColumnModel().getColumn(3).setPreferredWidth(110);
        }
    }
//    public void setLabListV2(Vector<LabList> v)
//    {
//        theLabListV = v;
//        TaBleModel tm ;
//        if(theLabListV==null)
//        {
//            tm = new TaBleModel(list_cols,0);
//            jTableList.setModel(tm);
//            return;
//        }
//        tm = new TaBleModel(list_cols,theLabListV.size());
//        for(int i=0;i<theLabListV.size();i++)
//        {
//            LabList tmp = theLabListV.get(i);
//            tm.setValueAt(tmp.order_name, i, 0);
//            tm.setValueAt(tmp.item_nick_name, i, 1);
//        }
//        jTableList.setModel(tm);
//    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jCheckBoxPreview = new javax.swing.JCheckBox();
        jCheckBoxSpecimenView = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableList = new com.hosv3.gui.component.HJTableSort();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("พิมพ์ Sticker ติด Tube");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jButton1, gridBagConstraints);

        jCheckBoxPreview.setText("ภาพก่อนพิมพ์");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel1.add(jCheckBoxPreview, gridBagConstraints);

        jCheckBoxSpecimenView.setText("มุมมอง Specimen (เฉพาะรายการที่ไม่ซ้ำ)");
        jCheckBoxSpecimenView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSpecimenViewActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jCheckBoxSpecimenView, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        add(jPanel1, gridBagConstraints);

        jTableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTableList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(jScrollPane2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jCheckBoxPreview.isSelected())
            theLabControl.printStickerTube(theLabListV,jTableList.getSelectedRows(),1);
        else
            theLabControl.printStickerTube(theLabListV,jTableList.getSelectedRows(),0);
        jCheckBoxSpecimenView.setSelected(false);
        theJD.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBoxSpecimenViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSpecimenViewActionPerformed
        if(theHosControl.theHO.theVisit == null)
        {
            theHosControl.theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ", UpdateStatus.WARNING);
            setLabListV(null);
            return;
        }
        if(type.equals(LabList.FROM_MENU))
        {
            theLabListV = theLabControl.listLabOrderByVn(theHosControl.theHO.theVisit.getObjectId());
        }
        else
        {
            theLabListV = theLabListV_tmp;
        }
        if(theLabListV == null )
        {
            setLabListV(null);
            return;
        }
        if(theLabListV.size() == 0)
        {
            theHosControl.theUS.setStatus("ผู้ป่วยไม่มีรายการแลป", UpdateStatus.WARNING);
            setLabListV(null);
            return;
        }
        if(jCheckBoxSpecimenView.isSelected())
        {
            Vector<LabList> v = this.theLabControl.convertToSpecimenView(theLabListV);
            this.setLabListV(v);
        }
        else
        {
            this.setLabListV(theLabListV);
        }
        jTableList.selectAll();
    }//GEN-LAST:event_jCheckBoxSpecimenViewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBoxPreview;
    private javax.swing.JCheckBox jCheckBoxSpecimenView;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.hosv3.gui.component.HJTableSort jTableList;
    // End of variables declaration//GEN-END:variables

    @Override
    public void notifySaveOrderItemInLab(String str, int status) {

    }

    @Override
    public void notifyCheckAutoOrder(String str, int status) {

    }

    @Override
    public void notifyDoctorOffDrug(String DoctorId, int status) {

    }

    @Override
    public void notifyCancelOrderItem(String str, int status) {

    }

    @Override
    public void notifyDispenseOrderItem(String str, int status) {

    }

    @Override
    public void notifyExecuteOrderItem(String str, int status) {
//        if(str.indexOf("has_lab") > 0)
//        {
//            Vector v = this.theLabControl.listLabOrderByVn2(this.theHosControl.theHO.theVisit.getObjectId());
//            theLabListV_tmp = v;
//            this.showDialog(v,LabList.FROM_EXECUTE);
//            theLabListV_tmp = null;
//        }
    }

    @Override
    public void notifyContinueOrderItem(String str, int status) {

    }

    @Override
    public void notifySaveOrderItem(String str, int status) {

    }

    @Override
    public void notifySaveOrderRequest(String str, int status) {

    }

    @Override
    public void notifyVerifyOrderItem(String str, int status) {

    }

    @Override
    public void notifyReferOutLab(String msg, int status) {

    }

    @Override
    public void notifyReceiveReturnDrug(String str, int status) {

    }

    @Override
    public void notifySaveReturnDrug(String str, int status) {

    }


}
