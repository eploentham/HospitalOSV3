/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelBornHistory.java
 *
 * Created on 27 ม.ค. 2554, 16:57:15
 */

package com.pcu.gui.panelpcu;

import com.hospital_os.utility.Gutil;
import com.hospital_os.utility.TaBleModel;
import com.hosv3.control.HosControl;
import com.hosv3.gui.dialog.HosDialog;
import com.hosv3.utility.connection.UpdateStatus;
import com.pcu.control.HosManage;
import com.pcu.control.PCUObject;
import com.pcu.gui.dialog.PanelChooseMCHMother;
import com.pcu.object.BornMch;
import com.pcu.object.Family;
import com.pcu.object.PP;
import com.pcu.object.Pregnancy;
import com.pcu.utility.GutilPCU;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author LionHeart
 */
public class PanelBornHistory extends javax.swing.JPanel {

    HosControl theHC;
    UpdateStatus theUS;
    HosDialog theHD;
    private Vector theBornMchV;
    String[] cols = {"ครรภ์ที่","วันที่คลอด","บุตรคลอด(คน)"};
    HosManage theHM;
    BornMch theBornMch;
    PanelChooseMCHMother thePCMM;
    Vector thePregnancyV;
    PanelBornMchMother thePanelBornMchMother;
    public PanelBornHistory() {
        initComponents();
    }
    public void setControl(HosManage hm,HosDialog hd,UpdateStatus us)
    {
        theHC = hm.theHC;
        theUS = us;
        theHD = hd;
        theHM = hm;
        jTextArea1.setContentType("text/html");
    }
    public void setObject(PCUObject pcuo )
    {
        Vector vBornMother = theHM.theHosControl.theAfterMCHMotherControl
                .selectBornMchMotherShowGUITableByFamilyID(pcuo.theHO.theFamily.getObjectId());
        setBornMchrV(vBornMother);
        thePregnancyV = theHM.theHosControl.theHealthServiceControl.listPregnancyByFamilyID();
    }
    public void setBornMch(BornMch bornMch)
    {
        if(jComboBox1.getSelectedItem()==null)
            return;
        theBornMch = bornMch;
        if(theBornMch == null)
        {
            theBornMch = new BornMch();
        }
        jComboBox1.removeAllItems();
        int pp_number = Integer.parseInt(theBornMch.lborn);
        for(int i=1;i<=pp_number;i++)
        {
            jComboBox1.addItem(String.valueOf(i));
        }
        
        PP pp = theHM.theHosControl.theAfterMCHMotherControl.readPPByPidAndGravidaAndPPNumber
                (this.theHM.theHO.thePatient.getObjectId(),this.theBornMch.gravida,jComboBox1.getSelectedItem().toString());
        if(pp!=null)
        {
            Family family = this.theHM.theHC.thePatientControl.readFamilyByFamilyIdRet(pp.family_id);
            String sex = "";
            String born = "";
            String born_method = "";
            String skin_color = "";
            String initial_condition = "";
            if(family.f_sex_id.equals("1"))
                sex = "ชาย";
            else if(family.f_sex_id.equals("2"))
                sex = "หญิง";
            else if(family.f_sex_id.equals("3"))
                sex = "ไม่ระบุ";
            if(theBornMch.bplace.equals("1"))
                born = "โรงพยาบาล";
            else if(theBornMch.bplace.equals("2"))
                born = "สถานีอนามัย";
            else if(theBornMch.bplace.equals("3"))
                born = "บ้าน";
            else if(theBornMch.bplace.equals("4"))
                born = "กลางทาง";
            else if(theBornMch.bplace.equals("5"))
                born = "อื่นๆ";
            if(theBornMch.birthmethod.equals("1"))
                born_method = "NORMAL";
            else if(theBornMch.birthmethod.equals("2"))
                born_method = "CESAREAN";
            else if(theBornMch.birthmethod.equals("3"))
                born_method = "VACUUM";
            else if(theBornMch.birthmethod.equals("4"))
                born_method = "FORCEPS";
            else if(theBornMch.birthmethod.equals("5"))
                born_method = "BREECH ท่าก้น";
            else if(theBornMch.birthmethod.equals("6"))
                born_method = "ABORTION";
            else if(theBornMch.birthmethod.equals("7"))
                born_method = "คลอดแฝดหลายวิธี";
            else if(theBornMch.birthmethod.equals("0"))
                born_method = "ไม่ระบุ";
            if(pp.pp_color.equals("1"))
                skin_color = "Pink";
            else if(pp.pp_color.equals("0"))
                skin_color = "Blue";
            if(pp.pp_initial_condition.equals("1"))
                initial_condition = "Good";
            else if(pp.pp_initial_condition.equals("0"))
                initial_condition = "Poor";
            String txt = "<html><body>บันทึกข้อมูลการคลอด<br>ชื่อ<u>" + theHC.theHO.thePatient.patient_name + " "+theHC.theHO.thePatient.patient_last_name
                    + "</u> เลขบัตรประชาชน <u>" + theHC.theHO.thePatient.pid + "</u><br>คลอดบุตรเมื่อวันที่ <u>" + Gutil.convertFieldDate(theBornMch.bdate)
                    + "</u> ชื่อ<u>"+family.patient_name + " " + family.patient_last_name + "</u> เพศ <u>" + sex
                    +"</u><br>คลอดที่ <u>" + born + "</u>น้ำหนักแรกเกิด <u>" + pp.pp_weight + "</u> กรัม คลอดแบบ <u>" + born_method
                    +"</u><br>ลักษณะสีผิว <u>" + skin_color + "</u> ภาวะสุขภาพแรกเกิด <u>" + initial_condition
                    +"</u><br>ภาวะแทรกซ้อน <u>" + theBornMch.abnormalpreg + "</u></body></html>";
            jTextArea1.setText(txt);
        }
        
    }
    public void setPanelBornMchMother(PanelBornMchMother panelBornMchMother)
    {
        thePanelBornMchMother = panelBornMchMother;
    }
    public void setBornMchrV(Vector bornMotherV)
    {
        TaBleModel tm;
        theBornMchV = bornMotherV;
        if(theBornMchV == null)
            theBornMchV = new Vector();
        tm = new TaBleModel(cols,theBornMchV.size());
        for(int i=0;i<theBornMchV.size();i++)
        {
            BornMch tmp = (BornMch) theBornMchV.get(i);
            tm.setValueAt(tmp.gravida, i,0);
            tm.setValueAt(GutilPCU.changDateToString(tmp.bdate), i,1);
            tm.setValueAt(tmp.lborn, i,2);
        }
        jTableBornMch.setModel(tm);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableBornMch = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JEditorPane();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("ประวัติการคลอด"));
        jPanel1.setMinimumSize(new java.awt.Dimension(23, 100));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(300, 100));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 100));

        jTableBornMch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableBornMch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableBornMchMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableBornMch);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("รายละเอียดการคลอดข้อมูลเด็กทารก"));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("ทารกคนที่");
        jPanel3.add(jLabel1, new java.awt.GridBagConstraints());

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel3.add(jComboBox1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel3, gridBagConstraints);

        jTextArea1.setEditable(false);
        jScrollPane2.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel2.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanel2, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("คลิ้กเพื่อลงข้อมูล");
        jButton1.setMargin(new java.awt.Insets(2, 7, 2, 7));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jButton1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("*ต้องการแก้ไขข้อมูล สามารถคลิ้กบนพื่นที่รายละเอียดการคลอด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel4.add(jLabel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(jPanel4, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        JOptionPane.showConfirmDialog(null, "Form Title", "ผู้ป่วยรับบริการฝากครรภ์ที่นี่หรือไม่ ?", JOptionPane.YES_NO_OPTION);
        if(theBornMchV!=null && theBornMchV.size()>0)
        {
            int select = this.jTableBornMch.getSelectedRow();
            if(select<0)
            {
                theUS.setStatus("กรุณาเลือกครรภ์ที่", UpdateStatus.WARNING);
                return;
            }
            int size = 0;
            int born_size = theBornMchV.size();
            BornMch bornMch = (BornMch) theBornMchV.get(select);
            for(int i =0;i<thePregnancyV.size();i++)
            {
                Pregnancy pregnancy = (Pregnancy) thePregnancyV.get(i);
                if(pregnancy.pregnancy_gravida_number.equals(bornMch.gravida))
                {
                    Vector anc = theHM.theHosControl.theHealthServiceControl.listAncByPregnantID(pregnancy.getObjectId());
                    size = anc.size();
                    if(thePCMM == null)
                    {
                        thePCMM = new PanelChooseMCHMother();
                        thePCMM.setControl(theHM);
                        thePCMM.setPanelBornMchMother(thePanelBornMchMother);
                    }
                    thePCMM.setAncTime(size,born_size);
                    thePCMM.showDialog();
                    break;
                }
            }
        }
        else
        {
            int size = 0;
            int born_size = theBornMchV.size();
            if(born_size == 0)
                born_size = 1;
            //---
            if(thePregnancyV!=null&&!thePregnancyV.isEmpty())
            {
                Pregnancy pregnancy = (Pregnancy) thePregnancyV.get(thePregnancyV.size()-1);
                Vector anc = theHM.theHosControl.theHealthServiceControl.listAncByPregnantID(pregnancy.getObjectId());
                size = anc.size();
                if(thePCMM == null)
                {
                    thePCMM = new PanelChooseMCHMother();
                    thePCMM.setControl(theHM);
                    thePCMM.setPanelBornMchMother(thePanelBornMchMother);
                }
                thePCMM.setAncTime(size,born_size);
                    thePCMM.showDialog();
            }
//            for(int i =0;i<thePregnancyV.size();i++)
//            {
//                Pregnancy pregnancy = (Pregnancy) thePregnancyV.get(i);
//                if(pregnancy.pregnancy_gravida_number.equals("1"))
//                {
//                    Vector anc = theHM.theHosControl.theHealthServiceControl.listAncByPregnantID(pregnancy.getObjectId());
//                    size = anc.size();
//                    if(thePCMM == null)
//                    {
//                        thePCMM = new PanelChooseMCHMother();
//                        thePCMM.setControl(theHM);
//                        thePCMM.setPanelBornMchMother(thePanelBornMchMother);
//                    }
//                    thePCMM.setAncTime(size,born_size);
//                    thePCMM.showDialog();
//                    break;
//                }
//            }
        }
        thePanelBornMchMother.hideHaveLife(true);
        thePanelBornMchMother.showDialog(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTableBornMchMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableBornMchMouseReleased
        int select = jTableBornMch.getSelectedRow();
        setBornMch((BornMch)theBornMchV.get(select));
    }//GEN-LAST:event_jTableBornMchMouseReleased

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        setBornMch(theBornMch);
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableBornMch;
    private javax.swing.JEditorPane jTextArea1;
    // End of variables declaration//GEN-END:variables

}
