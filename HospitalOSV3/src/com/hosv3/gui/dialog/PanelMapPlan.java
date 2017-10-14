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

import com.hospital_os.object.PatientPayment;
import com.hospital_os.object.Payment;
import com.hospital_os.object.Plan;
import com.hospital_os.utility.ComboFix;
import com.hospital_os.utility.Gutil;
import com.hospital_os.utility.TaBleModel;
import com.hosv3.control.HosControl;
import com.hosv3.control.lookup.R53CardInsTypeLookup;
import com.hosv3.object.MapNhsoMainInscl;
import com.hosv3.object.MapNhsoPlan;
import com.hosv3.object.MapNhsoSubInscl;
import com.hosv3.object.NhsoRight;
import com.hosv3.object.OfficeInCup;
import com.hosv3.utility.connection.UpdateStatus;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JDialog;
import th.go.nhso.rightsearch.RightData;

/**
 *
 * @author LionHeart
 */
public class PanelMapPlan extends javax.swing.JPanel {

    /** Creates new form PanelMapPlan */
    public Vector thePlan;
    HosControl theHC;
    UpdateStatus theUS;
    private JDialog theJD;
    Payment thePaymentNow;
    static String[] column_jTablePlan = {"สิทธิการรักษา","เขต"};
    static String[] pttype_col = {"PTTYPE"};
    RightData theRightData;
    Vector theMapNhsoPlanV;
    R53CardInsTypeLookup theR53CardInsTypeLookup;
    public int res=-1;
    private Vector thePttypeV;
    Vector theMapNhsoSubInsclV;
    Vector theMapNhsoMainInsclV;
    public PanelMapPlan() {
        initComponents();
        jPanel3.setVisible(false);
        jScrollPane1.setVisible(false);
    }
    public void setControl(HosControl hc, UpdateStatus us)
    {
        theHC = hc;
        theUS = us;
        theR53CardInsTypeLookup = new R53CardInsTypeLookup(theHC.theLookupControl.theConnectionInf);
    }
    public void setPaymentNow(Payment payment)
    {
        thePaymentNow = payment;
    }
    public void showDialog()
    {
//        NhsoRight nhsoRight = theHC.theSystemControl.selectNhsoRightByDes(theRightData.getMaininsclName());
//        if(nhsoRight!=null)
//        {
//            jPanel3.setVisible(false);
            jPanel3.setVisible(true);
            thePlan = null;
            theMapNhsoSubInsclV = null;
            theMapNhsoMainInsclV = null;
            boolean has_main_in_cup;
            boolean has_sub_incup;
            OfficeInCup hmain = this.theHC.theLookupControl.readOfficeInCupByCode(theRightData.getHmain());
            OfficeInCup hsub = this.theHC.theLookupControl.readOfficeInCupByCode(theRightData.getHsub());
            has_main_in_cup = hmain!=null;
            has_sub_incup = hsub!=null;
            //ในกรณีมีสิทธิ์ย่อย
            if(theRightData!=null && theRightData.getSubinscl()!=null
                    && !theRightData.getSubinscl().equals("") && !theRightData.getSubinscl().equals("null"))
            {
                String type="";
                if(has_main_in_cup||has_sub_incup)
                    type = "1";//ในเขต
                else
                    type = "2";//นอกเขต
                Vector mapNhsoSubInsclV = theHC.theSystemControl.listMapNhsoSubInsclByNhsoRightIDAndType(theRightData.getSubinscl(),type);
                this.setMapNhsoSubInscl(mapNhsoSubInsclV);
            }
            else
            {
                String type="";
                if(has_main_in_cup||has_sub_incup)
                    type = "1";//ในเขต
                else
                    type = "2";//นอกเขต
                Vector mapNhsoMainInsclV = theHC.theSystemControl.listMapNhsoMainInsclByNhsoRightIDAndType(theRightData.getMaininscl(),type);
                this.setMapNhsoMainInscl(mapNhsoMainInsclV);
            }
//            Vector mapNhsoPlanV = theHC.theSystemControl.listMapNhsoPlanByNhsoRightID(nhsoRight.getObjectId());
//            setMapNhsoPlanV(mapNhsoPlanV);
//            this.setPttype(theR53CardInsTypeLookup.listData2("%"));
//            if(theRightData!=null && theRightData.getSubinscl()!=null
//                    && !theRightData.getSubinscl().equals("null")
//                    && !theRightData.getSubinscl().equals(""))
//            {
//                int pttype_select = getPttypeIndex(theRightData.getSubinscl());
//                if(pttype_select>=0)
//                    jTable1.changeSelection(pttype_select,0, false, false);
//                else
//                    setPttype(null);
//            }
//            else
//            {
//                setPttype(null);
//            }
//            if(mapNhsoPlanV == null)
//            {
//                theMapNhsoPlanV = null;
//                thePlan = theHC.theLookupControl.listPlan();
//                setPlanV(thePlan);
//                jPanel3.setVisible(true);
//            }
//        }
//        else
//        {
//            theMapNhsoPlanV = null;
//            thePlan = theHC.theLookupControl.listPlan();
//            setPlanV(thePlan);
//            jPanel3.setVisible(true);
//        }
        if(theJD==null)
            theJD = new JDialog(theUS.getJFrame());
        theJD.add(this);
        theJD.setSize(650,600);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        theJD.setLocation((screenSize.width - theJD.getSize().width) / 2, (screenSize.height - theJD.getSize().height) / 2);
        theJD.setTitle("ผลการตรวจสอบสิทธิด้วย webservice");
        
        theJD.setModal(true);
        theJD.setVisible(true);
    }
    public void setPttype(Vector pttypeV)
    {
        thePttypeV = pttypeV;
        if(thePttypeV == null)
            thePttypeV = new Vector();
        TaBleModel tm  ;
        tm = new TaBleModel(pttype_col,thePttypeV.size());
        for(int i=0;i<thePttypeV.size();i++)
        {
            ComboFix cf = (ComboFix) thePttypeV.get(i);
            tm.setValueAt(cf.name,i,0);
        }
        jTable1.setModel(tm);
    }
    public void setMapNhsoSubInscl(Vector v)
    {
        theMapNhsoSubInsclV = v;
        if(theMapNhsoSubInsclV == null)
            theMapNhsoSubInsclV = new Vector();
        TaBleModel tm;
        jTableVisitPayment.getSelectionModel().clearSelection();
        tm = new TaBleModel(column_jTablePlan,theMapNhsoSubInsclV.size());
        for(int i=0;i<theMapNhsoSubInsclV.size();i++)
        {
            MapNhsoSubInscl mnsi = (MapNhsoSubInscl) theMapNhsoSubInsclV.get(i);
            tm.setValueAt(mnsi.contract_plans_des,i,0);
            if(mnsi.map_nhso_sub_inscl_type.equals("1"))
                tm.setValueAt("ในเขต",i,1);
            else if(mnsi.map_nhso_sub_inscl_type.equals("2"))
                tm.setValueAt("นอกเขต",i,1);
        }
        jTableVisitPayment.setModel(tm);
        jTableVisitPayment.getColumnModel().getColumn(0).setPreferredWidth(300);
        if(theMapNhsoSubInsclV.size()>0)
            jTableVisitPayment.setRowSelectionInterval(0,0);
    }
    public void setMapNhsoMainInscl(Vector v)
    {
        theMapNhsoMainInsclV = v;
        if(theMapNhsoMainInsclV == null)
            theMapNhsoMainInsclV = new Vector();
        TaBleModel tm;
        jTableVisitPayment.getSelectionModel().clearSelection();
        tm = new TaBleModel(column_jTablePlan,theMapNhsoMainInsclV.size());
        for(int i=0;i<theMapNhsoMainInsclV.size();i++)
        {
            MapNhsoMainInscl mnsi = (MapNhsoMainInscl) theMapNhsoMainInsclV.get(i);
            tm.setValueAt(mnsi.contract_plans_des,i,0);
            if(mnsi.map_nhso_main_inscl_type.equals("1"))
                tm.setValueAt("ในเขต",i,1);
            else if(mnsi.map_nhso_main_inscl_type.equals("2"))
                tm.setValueAt("นอกเขต",i,1);
        }
        jTableVisitPayment.setModel(tm);
        jTableVisitPayment.getColumnModel().getColumn(0).setPreferredWidth(300);
        if(theMapNhsoMainInsclV.size()>0)
            jTableVisitPayment.setRowSelectionInterval(0,0);
    }
    public void setMapNhsoPlanV(Vector v)
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
    public int getPttypeIndex(String index)
    {
        Vector v = theR53CardInsTypeLookup.listData2("%");
//        this.setPttype(v);
        if(v==null)
            v = new Vector();
        for(int i=0;i<v.size();i+=1)
        {
            ComboFix cf = (ComboFix) v.get(i);
            if(cf.code.equals(index))
                return i;
        }
        return -1;
    }
    public void setNhsoPlan(RightData rightData)
    {
        HashMap hm = new HashMap();
        hm.put("01","มกราคม");
        hm.put("02","กุมภาพันธ์");
        hm.put("03","มีนาคม");
        hm.put("04","เมษายน");
        hm.put("05","พฤษภาคม");
        hm.put("06","มิถุนายน");
        hm.put("07","กรกฏาคม");
        hm.put("08","สิงหาคม");
        hm.put("09","กันยายน");
        hm.put("10","ตุลาคม");
        hm.put("11","พฤศจิกายน");
        hm.put("12","ธันวาคม");
        String[] date_arr = theHC.theHO.date_time.substring(0,10).split("\\-");
        theRightData = rightData;
        jTextFieldPersonID.setText(theRightData.getPersonId());
        jTextFieldFname.setText(theRightData.getFname());
        jTextFieldLname.setText(theRightData.getLname());
        if(theRightData.getSex().equals("1"))
            jTextFieldSex.setText("ชาย");
        else if(theRightData.getSex().equals("2"))
            jTextFieldSex.setText("หญิง");
        String birth_date = "";
        if(theRightData.getBirthdate() != null
                && !theRightData.getBirthdate().equals("")
                && theRightData.getBirthdate().length()>=8)
        {
            birth_date = theRightData.getBirthdate().substring(0,4)
                    + "-" + theRightData.getBirthdate().substring(4,6) + "-" + theRightData.getBirthdate() .substring(6,8);
            birth_date = Gutil.convertFieldDate(birth_date);
        }
        jTextFieldBirth.setText(birth_date);
//        jTextFieldStatus.setText(theRightData.get);
        jTextFieldCurrentDate.setText(date_arr[2] + " " + hm.get(date_arr[1]) + " " + date_arr[0]);
        jTextFieldPurchaseProvince.setText(theRightData.getPurchaseprovinceName());
        jTextFieldSubRight.setText(theRightData.getSubinsclName());
        jTextFieldRight.setText(theRightData.getMaininsclName());
        jTextFieldModel.setText(theRightData.getPaidModel());
        jTextFieldCount.setText(String.valueOf(theRightData.getCountSelect()));
        jLabelSubHos1.setText(theRightData.getHmain() + " " + theRightData.getHmainName());
        if(theRightData.getCardId() != null && !theRightData.getCardId().equals(""))
            jTextFieldCardID.setText(theRightData.getCardId());
        else
            jTextFieldCardID.setText(theRightData.getPersonId());
        String start_date = "";
        
        if(theRightData.getStartdate() != null
                && !theRightData.getStartdate().equals("")
                && theRightData.getStartdate().length()>=8)
        {
            start_date = theRightData.getStartdate().substring(0,4)
                    + "-" + theRightData.getStartdate().substring(4,6) + "-" + theRightData.getStartdate().substring(6,8);
            start_date = Gutil.convertFieldDate(start_date);
        }
        dateComboBoxStart.setText(start_date);
        String exp_date = "";
        if(theRightData.getExpdate()!=null && !theRightData.getExpdate().equals("")
                && !theRightData.getExpdate().equals("NoExp") && theRightData.getExpdate().length()>=8)
        {
            exp_date = theRightData.getExpdate().substring(0,4)
                    +"-"+theRightData.getExpdate().substring(4,6)+"-"+theRightData.getExpdate().substring(6,8);
            exp_date = Gutil.convertFieldDate(exp_date);
        }
        dateComboBoxExp.setText(exp_date);
        jLabelMainHos.setText(theRightData.getHsub() + " " + theRightData.getHsubName());
        jLabelSubHos.setText(theRightData.getHmain() + " " + theRightData.getHmainName());
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
        jTextFieldRight = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldPurchaseProvince = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldSubRight = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldModel = new javax.swing.JTextField();
        jTextFieldCount = new javax.swing.JTextField();
        jLabelMainHos = new javax.swing.JTextField();
        jLabelSubHos = new javax.swing.JTextField();
        jLabelSubHos1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTableVisitPayment = new com.hosv3.gui.component.HJTableSort();
        jPanel3 = new javax.swing.JPanel();
        jButtonSearchAllPlan = new javax.swing.JButton();
        jTextFieldSearchPlan = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldPersonID = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldFname = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldLname = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldSex = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldBirth = new com.hospital_os.utility.DateComboBox();
        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldStatus = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldCurrentDate = new javax.swing.JTextField();

        setMinimumSize(new java.awt.Dimension(200, 445));
        setPreferredSize(new java.awt.Dimension(200, 375));
        setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("ข้อมูลสิทธิ"));
        jPanel1.setMinimumSize(new java.awt.Dimension(934, 190));
        jPanel1.setPreferredSize(new java.awt.Dimension(465, 190));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("สิทธิหลักในการรับบริการ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel3.setText("เลขที่บัตร");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        jTextFieldCardID.setEditable(false);
        jTextFieldCardID.setMinimumSize(new java.awt.Dimension(185, 21));
        jTextFieldCardID.setPreferredSize(new java.awt.Dimension(185, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel1.add(jTextFieldCardID, gridBagConstraints);

        jLabel4.setText("วันที่เริ่มใช้สิทธิ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setText("วันหมดสิทธิย่อย");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabel5, gridBagConstraints);

        jLabel6.setText("หน่วนบริการปฐมภูมิ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabel6, gridBagConstraints);

        jLabel7.setText("หน่วยบริการที่รับการส่งต่อ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
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
        gridBagConstraints.gridy = 4;
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
        gridBagConstraints.gridy = 11;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel1.add(dateComboBoxExp, gridBagConstraints);

        jTextFieldRight.setEditable(false);
        jTextFieldRight.setMinimumSize(new java.awt.Dimension(185, 21));
        jTextFieldRight.setPreferredSize(new java.awt.Dimension(185, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel1.add(jTextFieldRight, gridBagConstraints);

        jLabel14.setText("จังหวัดที่ลงทะเบียนรักษา");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel14, gridBagConstraints);

        jTextFieldPurchaseProvince.setEditable(false);
        jTextFieldPurchaseProvince.setMinimumSize(new java.awt.Dimension(185, 21));
        jTextFieldPurchaseProvince.setPreferredSize(new java.awt.Dimension(185, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jTextFieldPurchaseProvince, gridBagConstraints);

        jLabel15.setText("ประเภทสิทธิย่อย");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel15, gridBagConstraints);

        jTextFieldSubRight.setEditable(false);
        jTextFieldSubRight.setMinimumSize(new java.awt.Dimension(185, 21));
        jTextFieldSubRight.setPreferredSize(new java.awt.Dimension(185, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel1.add(jTextFieldSubRight, gridBagConstraints);

        jLabel16.setText("model");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabel16, gridBagConstraints);

        jLabel17.setText("จำนวนครั้งที่เปลี่ยนหน่วยบริการประจำ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabel17, gridBagConstraints);

        jLabel18.setText("หน่วยบริการประจำ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel1.add(jLabel18, gridBagConstraints);

        jTextFieldModel.setEditable(false);
        jTextFieldModel.setMinimumSize(new java.awt.Dimension(70, 21));
        jTextFieldModel.setPreferredSize(new java.awt.Dimension(70, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel1.add(jTextFieldModel, gridBagConstraints);

        jTextFieldCount.setEditable(false);
        jTextFieldCount.setMinimumSize(new java.awt.Dimension(70, 21));
        jTextFieldCount.setPreferredSize(new java.awt.Dimension(70, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel1.add(jTextFieldCount, gridBagConstraints);

        jLabelMainHos.setEditable(false);
        jLabelMainHos.setBorder(null);
        jLabelMainHos.setMinimumSize(new java.awt.Dimension(185, 21));
        jLabelMainHos.setPreferredSize(new java.awt.Dimension(185, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jLabelMainHos, gridBagConstraints);

        jLabelSubHos.setEditable(false);
        jLabelSubHos.setBorder(null);
        jLabelSubHos.setMinimumSize(new java.awt.Dimension(185, 21));
        jLabelSubHos.setPreferredSize(new java.awt.Dimension(185, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jLabelSubHos, gridBagConstraints);

        jLabelSubHos1.setEditable(false);
        jLabelSubHos1.setBorder(null);
        jLabelSubHos1.setMinimumSize(new java.awt.Dimension(185, 21));
        jLabelSubHos1.setPreferredSize(new java.awt.Dimension(185, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jLabelSubHos1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 5);
        add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("จับคู่สิทธิ์ สปสช กับสิทธิ์ของโรงพยาบาล"));
        jPanel2.setMinimumSize(new java.awt.Dimension(200, 300));
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 300));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jScrollPane11.setMaximumSize(new java.awt.Dimension(300, 300));
        jScrollPane11.setPreferredSize(new java.awt.Dimension(50, 50));

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
        gridBagConstraints.gridy = 1;
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

        jScrollPane1.setMinimumSize(new java.awt.Dimension(300, 23));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 402));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        add(jPanel2, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("Visit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
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
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        jPanel4.add(jButton2, gridBagConstraints);

        jButton3.setText("บันทึกสิทธิ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel4.add(jButton3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 5);
        add(jPanel4, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("ข้อมูลประชากร (สปสช.)"));
        jPanel5.setPreferredSize(new java.awt.Dimension(465, 150));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("รหัสประจำตัวประชาชน 13 หลัก");
        jPanel6.add(jLabel2, new java.awt.GridBagConstraints());

        jTextFieldPersonID.setEditable(false);
        jTextFieldPersonID.setBorder(null);
        jTextFieldPersonID.setMinimumSize(new java.awt.Dimension(185, 21));
        jTextFieldPersonID.setPreferredSize(new java.awt.Dimension(185, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel6.add(jTextFieldPersonID, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        jPanel5.add(jPanel6, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jLabel8.setText("ชื่อ");
        jPanel7.add(jLabel8, new java.awt.GridBagConstraints());

        jTextFieldFname.setEditable(false);
        jTextFieldFname.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldFname.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(jTextFieldFname, gridBagConstraints);

        jLabel13.setText("นามสกุล");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(jLabel13, gridBagConstraints);

        jTextFieldLname.setEditable(false);
        jTextFieldLname.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldLname.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(jTextFieldLname, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel5.add(jPanel7, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel9.setText("เพศ");
        jPanel8.add(jLabel9, new java.awt.GridBagConstraints());

        jTextFieldSex.setEditable(false);
        jTextFieldSex.setMinimumSize(new java.awt.Dimension(70, 21));
        jTextFieldSex.setPreferredSize(new java.awt.Dimension(70, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel8.add(jTextFieldSex, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel5.add(jPanel8, gridBagConstraints);

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jLabel10.setText("เกิด");
        jPanel9.add(jLabel10, new java.awt.GridBagConstraints());

        jTextFieldBirth.setEnabled(false);
        jTextFieldBirth.setMinimumSize(new java.awt.Dimension(100, 23));
        jTextFieldBirth.setPreferredSize(new java.awt.Dimension(100, 23));
        jTextFieldBirth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBirthActionPerformed(evt);
            }
        });
        jPanel9.add(jTextFieldBirth, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel5.add(jPanel9, gridBagConstraints);

        jPanel10.setLayout(new java.awt.GridBagLayout());

        jLabel11.setText("สถานะบุคคล");
        jPanel10.add(jLabel11, new java.awt.GridBagConstraints());

        jTextFieldStatus.setEditable(false);
        jTextFieldStatus.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldStatus.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel10.add(jTextFieldStatus, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel5.add(jPanel10, gridBagConstraints);

        jPanel11.setLayout(new java.awt.GridBagLayout());

        jLabel12.setText("ข้อมูล ณ วันที่");
        jPanel11.add(jLabel12, new java.awt.GridBagConstraints());

        jTextFieldCurrentDate.setEditable(false);
        jTextFieldCurrentDate.setMinimumSize(new java.awt.Dimension(150, 21));
        jTextFieldCurrentDate.setPreferredSize(new java.awt.Dimension(150, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel11.add(jTextFieldCurrentDate, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel5.add(jPanel11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        add(jPanel5, gridBagConstraints);
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
        if(this.theHC.theHO.theVisit!=null) {
            theUS.setStatus("ผู้ป่วยเข้าสู่กระบวนการแล้ว", UpdateStatus.WARNING);
            return;
        }
        res = 1;
        int select = jTableVisitPayment.getSelectedRow();
        if(select<0)
        {
            theUS.setStatus("กรุณาเลือกสิทธิ์ที่ต้องการจับคู่", UpdateStatus.WARNING);
            return;
        }
//        if(theMapNhsoPlanV != null)
//        {
//            MapNhsoPlan p = (MapNhsoPlan) this.theMapNhsoPlanV.get(select);
//            thePaymentNow.plan_kid = p.b_contract_plans_id;
//            thePaymentNow.contract_kid = p.b_contract_id;
//            System.err.println("thePaymentNow.plan_kid = " + thePaymentNow.plan_kid);
//            System.err.println("thePaymentNow.contract_kid = "+thePaymentNow.contract_kid);
//            theHC.thePatientControl.savePatientPayment2(theHC.theHO.vPatientPayment,thePaymentNow);
//        }
//        if(thePlan!=null)
//        {
//            Plan p = (Plan) thePlan.get(select);
//            thePaymentNow.plan_kid = p.getObjectId();
//            thePaymentNow.contract_kid = p.contract_id;
//            System.err.println("thePaymentNow.plan_kid = " + thePaymentNow.plan_kid);
//            System.err.println("thePaymentNow.contract_kid = "+thePaymentNow.contract_kid);
//            theHC.thePatientControl.savePatientPayment2(theHC.theHO.vPatientPayment,thePaymentNow);
//        }
        if(theMapNhsoSubInsclV != null)
        {
            MapNhsoSubInscl p = (MapNhsoSubInscl) this.theMapNhsoSubInsclV.get(select);
            thePaymentNow.plan_kid = p.b_contract_plans_id;
            thePaymentNow.contract_kid = p.b_contract_id;
            theHC.thePatientControl.savePatientPayment2(theHC.theHO.vPatientPayment,thePaymentNow);
        }
        else if(theMapNhsoMainInsclV != null)
        {
            MapNhsoMainInscl p = (MapNhsoMainInscl) this.theMapNhsoMainInsclV.get(select);
            thePaymentNow.plan_kid = p.b_contract_plans_id;
            thePaymentNow.contract_kid = p.b_contract_id;
            theHC.thePatientControl.savePatientPayment2(theHC.theHO.vPatientPayment,thePaymentNow);
        }
        else if(thePlan != null)
        {
            Plan p = (Plan) thePlan.get(select);
            thePaymentNow.plan_kid = p.getObjectId();
            thePaymentNow.contract_kid = p.contract_id;
            theHC.thePatientControl.savePatientPayment2(theHC.theHO.vPatientPayment,thePaymentNow);
        }
        if(theHC.theHP.aPanelVisit.vPatientPayment!=null && !theHC.theHP.aPanelVisit.vPatientPayment.isEmpty() && res == 1) {
            PatientPayment payment = (PatientPayment)theHC.theHP.aPanelVisit.vPatientPayment.get(theHC.theHP.aPanelVisit.vPatientPayment.size()-1);
            payment.setObjectId(null);
            theHC.theHP.aPanelVisit.saveVisitPayment(payment);

            int select2 = theHC.theHP.aPanelVisit.vVisitPayment.size()-1;
            for(int i=select2;i>0;i--) {
                theHC.theHP.aPanelVisit.jTableVisitPayment.setRowSelectionInterval(i, i);
                theHC.theHP.aPanelVisit.jButtonUpActionPerformed();
            }
            //              jTableVisitPayment.setRowSelectionInterval(vVisitPayment.size()-1, vVisitPayment.size()-1);
            //              jButtonUpActionPerformed(null);
            theHC.theHP.aPanelVisit.jButtonVisitActionPerformed();
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

    private void jTextFieldBirthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBirthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBirthActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int select = jTableVisitPayment.getSelectedRow();
//        if(select<0)
//        {
//            theUS.setStatus("กรุณาเลือกสิทธิ์ที่ต้องการจับคู่", UpdateStatus.WARNING);
//            return;
//        }
//        if(theMapNhsoPlanV != null)
//        {
//            MapNhsoPlan p = (MapNhsoPlan) this.theMapNhsoPlanV.get(select);
//            thePaymentNow.plan_kid = p.b_contract_plans_id;
//            thePaymentNow.contract_kid = p.b_contract_id;
//            theHC.thePatientControl.savePatientPayment2(theHC.theHO.vPatientPayment,thePaymentNow);
//        }
//        if(thePlan!=null)
//        {
//            Plan p = (Plan) thePlan.get(select);
//            thePaymentNow.plan_kid = p.getObjectId();
//            thePaymentNow.contract_kid = p.contract_id;
//            theHC.thePatientControl.savePatientPayment2(theHC.theHO.vPatientPayment,thePaymentNow);
//        }
        if(select<0)
        {
            theUS.setStatus("กรุณาเลือกสิทธิ์ที่ต้องการจับคู่", UpdateStatus.WARNING);
            return;
        }
        if(thePlan != null)
        {
            Plan p = (Plan) thePlan.get(select);
            thePaymentNow.plan_kid = p.getObjectId();
            thePaymentNow.contract_kid = p.contract_id;
            theHC.thePatientControl.savePatientPayment2(theHC.theHO.vPatientPayment,thePaymentNow);
        }
        else if(theMapNhsoSubInsclV != null)
        {
            MapNhsoSubInscl p = (MapNhsoSubInscl) this.theMapNhsoSubInsclV.get(select);
            thePaymentNow.plan_kid = p.b_contract_plans_id;
            thePaymentNow.contract_kid = p.b_contract_id;
            theHC.thePatientControl.savePatientPayment2(theHC.theHO.vPatientPayment,thePaymentNow);
        }
        else if(theMapNhsoMainInsclV != null)
        {
            MapNhsoMainInscl p = (MapNhsoMainInscl) this.theMapNhsoMainInsclV.get(select);
            thePaymentNow.plan_kid = p.b_contract_plans_id;
            thePaymentNow.contract_kid = p.b_contract_id;
            theHC.thePatientControl.savePatientPayment2(theHC.theHO.vPatientPayment,thePaymentNow);
        }
        
        theJD.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.utility.DateComboBox dateComboBoxExp;
    private com.hospital_os.utility.DateComboBox dateComboBoxStart;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonSearchAllPlan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jLabelMainHos;
    private javax.swing.JTextField jLabelSubHos;
    private javax.swing.JTextField jLabelSubHos1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JTable jTable1;
    private com.hosv3.gui.component.HJTableSort jTableVisitPayment;
    private com.hospital_os.utility.DateComboBox jTextFieldBirth;
    private javax.swing.JTextField jTextFieldCardID;
    private javax.swing.JTextField jTextFieldCount;
    private javax.swing.JTextField jTextFieldCurrentDate;
    private javax.swing.JTextField jTextFieldFname;
    private javax.swing.JTextField jTextFieldLname;
    private javax.swing.JTextField jTextFieldModel;
    private javax.swing.JTextField jTextFieldPersonID;
    private javax.swing.JTextField jTextFieldPurchaseProvince;
    private javax.swing.JTextField jTextFieldRight;
    private javax.swing.JTextField jTextFieldSearchPlan;
    private javax.swing.JTextField jTextFieldSex;
    private javax.swing.JTextField jTextFieldStatus;
    private javax.swing.JTextField jTextFieldSubRight;
    // End of variables declaration//GEN-END:variables

}
