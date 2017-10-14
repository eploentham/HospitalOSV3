/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelMapPlan.java
 *
 * Created on 5 ม.ค. 2554, 9:29:28
 */

package com.hosv3.gui.dialog;

import com.hospital_os.object.Payment;
import com.hospital_os.object.Plan;
import com.hospital_os.utility.Gutil;
import com.hospital_os.utility.TaBleModel;
import com.hosv3.control.HosControl;
import com.hosv3.object.MapNhsoPlan;
import com.hosv3.object.NhsoRight;
import com.hosv3.utility.connection.UpdateStatus;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.JDialog;
import th.go.nhso.rightsearch.RightData;

/**
 *
 * @author LionHeart
 */
public class PanelMapPlan2 extends javax.swing.JPanel {

    /** Creates new form PanelMapPlan */
    public Vector thePlan;
    HosControl theHC;
    UpdateStatus theUS;
    private JDialog theJD;
    Payment thePaymentNow;
    static String[] column_jTablePlan = {"สิทธิการรักษา"};
    RightData theRightData;
    Vector theMapNhsoPlanV;
    public int res=0;
    public PanelMapPlan2() {
        initComponents();
        jPanel3.setVisible(false);
    }
    public void setControl(HosControl hc, UpdateStatus us)
    {
        theHC = hc;
        theUS = us;
    }
    public void setPaymentNow(Payment payment)
    {
        thePaymentNow = payment;
    }
    public void showDialog()
    {
        NhsoRight nhsoRight = theHC.theSystemControl.selectNhsoRightByDes(theRightData.getMaininsclName());
        if(nhsoRight!=null)
        {
            jPanel3.setVisible(false);
            thePlan = null;
            Vector mapNhsoPlanV = theHC.theSystemControl.listMapNhsoPlanByNhsoRightID(nhsoRight.getObjectId());
            setMapNhsoPlanV(mapNhsoPlanV);
            if(mapNhsoPlanV == null)
            {
                theMapNhsoPlanV = null;
                thePlan = theHC.theLookupControl.listPlan();
                setPlanV(thePlan);
                jPanel3.setVisible(true);
            }
        }
        else
        {
            theMapNhsoPlanV = null;
            thePlan = theHC.theLookupControl.listPlan();
            setPlanV(thePlan);
            jPanel3.setVisible(true);
        }
        if(theJD==null)
            theJD = new JDialog(theUS.getJFrame());
        theJD.add(this);
        theJD.setSize(450,500);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        theJD.setLocation((screenSize.width - theJD.getSize().width) / 2, (screenSize.height - theJD.getSize().height) / 2);
        theJD.setTitle("จับคู่สิทธิ์ สปสช");
        theJD.setModal(true);
        theJD.setVisible(true);
    }
    private void setMapNhsoPlanV(Vector v)
    {
        theMapNhsoPlanV = v;
        if(theMapNhsoPlanV == null)
            theMapNhsoPlanV = new Vector();

        TaBleModel tm  ;
        jTableVisitPayment.getSelectionModel().clearSelection();
        if(theMapNhsoPlanV == null)
        {
            theMapNhsoPlanV = new Vector();
            tm = new TaBleModel(column_jTablePlan,0);
            jTableVisitPayment.setModel(tm);
            return;
        }
       tm = new TaBleModel(column_jTablePlan,theMapNhsoPlanV.size());
       for(int i=0,size = theMapNhsoPlanV.size(); i < size; i++)
       {
           MapNhsoPlan plan = (MapNhsoPlan) theMapNhsoPlanV.get(i);
           tm.setValueAt(plan.contract_plans_des,i,0);
       }
       jTableVisitPayment.setModel(tm);
       jTableVisitPayment.getColumnModel().getColumn(0).setPreferredWidth(300);
       if(theMapNhsoPlanV.size()>0)
            jTableVisitPayment.setRowSelectionInterval(0,0);
    }
    private void setPlanV(Vector pv)
    {
        this.thePlan = pv;
        TaBleModel tm  ;
        jTableVisitPayment.getSelectionModel().clearSelection();
        if(thePlan == null)
        {
            thePlan = new Vector();
            tm = new TaBleModel(column_jTablePlan,0);
            jTableVisitPayment.setModel(tm);
            return;
        }
       tm = new TaBleModel(column_jTablePlan,thePlan.size());
       for(int i=0,size = thePlan.size(); i < size; i++)
       {
           Plan plan = (Plan) thePlan.get(i);
           tm.setValueAt(plan.description,i,0);
       }
       jTableVisitPayment.setModel(tm);
       jTableVisitPayment.getColumnModel().getColumn(0).setPreferredWidth(300);
       if(thePlan.size()>0)
            jTableVisitPayment.setRowSelectionInterval(0,0);
     }
    public void setNhsoPlan(RightData rightData)
    {
        theRightData = rightData;
        jTextFieldRight.setText(rightData.getMaininsclName());
        jTextFieldCardID.setText(rightData.getCardId());
        String start_date = "";
        if(rightData.getStartdate() != null 
                && !rightData.getStartdate().equals("")
                && rightData.getStartdate().length()>=8)
        {
            start_date = rightData.getStartdate().substring(0,4)
                    + "-" + rightData.getStartdate().substring(4,6) + "-" + rightData.getStartdate().substring(6,8);
            start_date = Gutil.convertFieldDate(start_date);
        }
        dateComboBoxStart.setText(start_date);
        String exp_date = "";
        if(rightData.getExpdate()!=null && !rightData.getExpdate().equals("")
                && !rightData.getExpdate().equals("NoExp") && rightData.getExpdate().length()>=8)
        {
            exp_date = rightData.getExpdate().substring(0,4)
                    +"-"+rightData.getExpdate().substring(4,6)+"-"+rightData.getExpdate().substring(6,8);
            exp_date = Gutil.convertFieldDate(exp_date);
        }
        dateComboBoxExp.setText(exp_date);
        jLabelMainHos.setText(rightData.getHmainName());
        jLabelSubHos.setText(rightData.getHsubName());
    }
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
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldCardID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        dateComboBoxStart = new com.hospital_os.utility.DateComboBox();
        dateComboBoxExp = new com.hospital_os.utility.DateComboBox();
        jLabelSubHos = new javax.swing.JLabel();
        jLabelMainHos = new javax.swing.JLabel();
        jTextFieldRight = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTableVisitPayment = new com.hosv3.gui.component.HJTableSort();
        jPanel3 = new javax.swing.JPanel();
        jButtonSearchAllPlan = new javax.swing.JButton();
        jTextFieldSearchPlan = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(200, 445));
        setPreferredSize(new java.awt.Dimension(200, 375));
        setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("สิทธิ์จากเว็บเซอร์วิส"));
        jPanel1.setMinimumSize(new java.awt.Dimension(934, 70));
        jPanel1.setPreferredSize(new java.awt.Dimension(465, 70));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("สิทธิ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel3.setText("เลขที่บัตร");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        jTextFieldCardID.setEditable(false);
        jTextFieldCardID.setMinimumSize(new java.awt.Dimension(185, 21));
        jTextFieldCardID.setPreferredSize(new java.awt.Dimension(185, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel1.add(jTextFieldCardID, gridBagConstraints);

        jLabel4.setText("วันที่ออกบัตร");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setText("วันที่หมดอายุ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabel5, gridBagConstraints);

        jLabel6.setText("สถานพยาบาลหลัก");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabel6, gridBagConstraints);

        jLabel7.setText("สถานพยาบาลรอง");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabel7, gridBagConstraints);

        dateComboBoxStart.setEnabled(false);
        dateComboBoxStart.setMinimumSize(new java.awt.Dimension(90, 23));
        dateComboBoxStart.setPreferredSize(new java.awt.Dimension(90, 23));
        dateComboBoxStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxStartActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel1.add(dateComboBoxStart, gridBagConstraints);

        dateComboBoxExp.setEnabled(false);
        dateComboBoxExp.setMinimumSize(new java.awt.Dimension(90, 23));
        dateComboBoxExp.setPreferredSize(new java.awt.Dimension(90, 23));
        dateComboBoxExp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxExpActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel1.add(dateComboBoxExp, gridBagConstraints);

        jLabelSubHos.setForeground(new java.awt.Color(0, 0, 255));
        jLabelSubHos.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel1.add(jLabelSubHos, gridBagConstraints);

        jLabelMainHos.setForeground(new java.awt.Color(0, 0, 255));
        jLabelMainHos.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel1.add(jLabelMainHos, gridBagConstraints);

        jTextFieldRight.setEditable(false);
        jTextFieldRight.setMinimumSize(new java.awt.Dimension(185, 21));
        jTextFieldRight.setPreferredSize(new java.awt.Dimension(185, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel1.add(jTextFieldRight, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 3);
        add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("จับคู่สิทธิ์ สปสช กับสิทธิ์ของโรงพยาบาล"));
        jPanel2.setMinimumSize(new java.awt.Dimension(200, 300));
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 300));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jScrollPane11.setMaximumSize(new java.awt.Dimension(300, 300));

        jTableVisitPayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableVisitPaymentMouseReleased(evt);
            }
        });
        jTableVisitPayment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableVisitPaymentKeyReleased(evt);
            }
        });
        jScrollPane11.setViewportView(jTableVisitPayment);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 327;
        gridBagConstraints.ipady = 142;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane11, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jButtonSearchAllPlan.setText("ค้นหา");
        jButtonSearchAllPlan.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonSearchAllPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchAllPlanActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        jPanel3.add(jButtonSearchAllPlan, gridBagConstraints);

        jTextFieldSearchPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchPlanActionPerformed(evt);
            }
        });
        jTextFieldSearchPlan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchPlanKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(jTextFieldSearchPlan, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        add(jPanel2, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("Visit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel4.add(jButton1, gridBagConstraints);

        jButton2.setText("ยกเลิก");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel4.add(jButton2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        add(jPanel4, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableVisitPaymentMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableVisitPaymentMouseReleased
//        this.jTablePatientPayment.clearSelection();
//        //this.jTablePlan.clearSelection();
//        this.jTableVisitPayment.clearSelection();
//        Plan p = (Plan)thePlan.get(jTablePlan.getSelectedRow());
//        setPlan(p);
        if(evt.getClickCount()==2)
        {
            jButton1ActionPerformed(null);
        }
}//GEN-LAST:event_jTableVisitPaymentMouseReleased

    private void jTableVisitPaymentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableVisitPaymentKeyReleased
//        if(evt.getKeyCode()==KeyEvent.VK_UP ||evt.getKeyCode()==KeyEvent.VK_DOWN){
//            jTablePlanMouseReleased(null);
//        }
//        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
//            this.jButtonSaveVisitPaymentActionPerformed(null);
//        }
}//GEN-LAST:event_jTableVisitPaymentKeyReleased

    private void dateComboBoxStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxStartActionPerformed
        //        dateComboBoxTo.setText(
        //            DateUtil.convertFieldDate(dateComboBoxFrom.getText()));
}//GEN-LAST:event_dateComboBoxStartActionPerformed

    private void dateComboBoxExpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxExpActionPerformed
        //        dateComboBoxTo.setText(
        //            DateUtil.convertFieldDate(dateComboBoxFrom.getText()));
}//GEN-LAST:event_dateComboBoxExpActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        res = 1;
        int select = jTableVisitPayment.getSelectedRow();
        if(select<0)
        {
            theUS.setStatus("กรุณาเลือกสิทธิ์ที่ต้องการจับคู่", UpdateStatus.WARNING);
            return;
        }
        if(theMapNhsoPlanV != null)
        {
            MapNhsoPlan p = (MapNhsoPlan) this.theMapNhsoPlanV.get(select);
            thePaymentNow.plan_kid = p.b_contract_plans_id;
            thePaymentNow.contract_kid = p.b_contract_id;
            theHC.thePatientControl.savePatientPayment2(theHC.theHO.vPatientPayment,thePaymentNow);
        }
        if(thePlan!=null)
        {
            Plan p = (Plan) thePlan.get(select);
            thePaymentNow.plan_kid = p.getObjectId();
            thePaymentNow.contract_kid = p.contract_id;
            theHC.thePatientControl.savePatientPayment2(theHC.theHO.vPatientPayment,thePaymentNow);
        }
        

        theJD.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonSearchAllPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchAllPlanActionPerformed
        String strplan = jTextFieldSearchPlan.getText();
        if(strplan.equals("")) {
            thePlan = theHC.theLookupControl.listPlan();
            setPlanV(thePlan);
        } else {
            thePlan = theHC.theLookupControl.listPlan(strplan);
            setPlanV(thePlan);
        }
}//GEN-LAST:event_jButtonSearchAllPlanActionPerformed

    private void jTextFieldSearchPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchPlanActionPerformed
        jButtonSearchAllPlanActionPerformed(null);
}//GEN-LAST:event_jTextFieldSearchPlanActionPerformed

    private void jTextFieldSearchPlanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchPlanKeyReleased

}//GEN-LAST:event_jTextFieldSearchPlanKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        res = 2;
        thePaymentNow = null;
        theRightData = null;
        theJD.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.utility.DateComboBox dateComboBoxExp;
    private com.hospital_os.utility.DateComboBox dateComboBoxStart;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonSearchAllPlan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelMainHos;
    private javax.swing.JLabel jLabelSubHos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane11;
    private com.hosv3.gui.component.HJTableSort jTableVisitPayment;
    private javax.swing.JTextField jTextFieldCardID;
    private javax.swing.JTextField jTextFieldRight;
    private javax.swing.JTextField jTextFieldSearchPlan;
    // End of variables declaration//GEN-END:variables

}
