/*
 * PanelMaimPCU.java
 *
 * Created on 27 มีนาคม 2549, 11:46 น.
 */

package com.generalpcu.gui.panel;
import com.generalpcu.usecase.*;
import com.generalpcu.control.ManageControlSubject;
import com.generalpcu.gui.panel.DialogShowStatus;
import com.generalpcu.utility.ComboFix;
import com.generalpcu.utility.ComboboxModel;
import com.generalpcu.utility.Constant;
import com.generalpcu.utility.Report;
import com.generalpcu.utility.Language;
import com.generalpcu.utility.TableModelGUI;
import java.util.Vector;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JOptionPane;

/**
 *
 * @author  pu
 */
public class PanelMaimPCU extends javax.swing.JPanel implements 
AllPanelResp,GUIResp,CardNameControl,Runnable
{
    ManageControlSubject theMSC;
    DialogShowStatus theDialogShowStatus;
    ComboboxModel theComboboxModel;
    DefaultTableCellRenderer rendererCenter;
    DefaultTableCellRenderer rendererRight;
    TableModelGUI theTableModelGUI;
    Vector vVillage;
    Vector vMaimType;
    Vector vTreat;
    Vector vRegistry;
    Vector vcData;
    Vector vcDataTemp;
    private Vector vcDataQuery;
    private String village;
    private String maimtype;
    private String treatstr;
    private String registrystr;
    private String cardName;
    private String startDate;
    private String endDate;
    private Thread theThread;
    private String[] headColumn;
    private boolean isDbBackup;

    
    /** Creates new form PanelMaimPCU */
    public PanelMaimPCU(ManageControlSubject msc)
    {
        theMSC = msc;
        theMSC.theManageSubject.theAllPanelSubject.registerAllPanelManage(this);
        theMSC.theManageSubject.theGUISubject.registerGUIManage(this);
        initComponents();
        cardName = ((Report)Constant.Report.get("6")).ENG_NAME;
        theDialogShowStatus = new DialogShowStatus(theMSC.theUS.getJFrame(),false,theMSC);
        theComboboxModel = new ComboboxModel();
        setStatusGUI();
        setLanguage();
        initComboBoxVillage();
        initComboBoxMaimType();
        initComboBoxTreatStatus();
        initComboBoxRegistryStatus();
    }    
    
    /**
     *ตรวจสอบเงื่อนไขการดึงข้อมูล
     *@Author pu
     *@date 28/03/2006
     */
    private void setStatusGUI()
    {
        if((jCheckBoxTreatStr.isSelected() && jCheckBoxRegistryStr.isSelected()) || (!jCheckBoxTreatStr.isSelected() && !jCheckBoxRegistryStr.isSelected()))
        {
            setEnableStatusGUI(false);
            //this.strTreat = 1;
        }
        else if(jCheckBoxTreatStr.isSelected())
        {
            this.jComboBoxTreat.setEnabled(false);
            this.jComboBoxRegistry.setEnabled(false);
            //this.strTreat = 0;
        }        
        else if(jCheckBoxRegistryStr.isSelected())
        {
            this.jComboBoxTreat.setEnabled(true);
            this.jComboBoxRegistry.setEnabled(true);
        }
    }
    
    /**
     *กำหนดสถานะให้กับ GUI ตามที่ผู้ใช้เลือก
     *@Author pu
     *@date 28/03/2006
     */
    private void setEnableStatusGUI(boolean str)
    {
        this.jComboBoxTreat.setEnabled(str);
        this.jComboBoxRegistry.setEnabled(str);
    }
    /**
     *เซ็ตค่าให้กับ ComboBox หมู่บ้าน
     *@Author pu
     *@date 27/03/2006
     */
    private void initComboBoxVillage()
    {
        this.vVillage = this.theMSC.theManageControl.theComboBoxControl.listVillage();
        theComboboxModel.initComboBox(this.jComboBoxVillage, this.vVillage);
        theComboboxModel.setCodeComboBox(this.jComboBoxVillage, "0");
    }
    
    /**
     *เซ็ตค่าให้กับ ComboBox หมู่บ้าน
     *@Author pu
     *@date 27/03/2006
     */
    private void initComboBoxMaimType()
    {
        this.vMaimType = this.theMSC.theManageControl.theComboBoxControl.listMaimType();
        theComboboxModel.initComboBox(this.jComboBoxMaimType, this.vMaimType);
        theComboboxModel.setCodeComboBox(this.jComboBoxMaimType, "0");
    }
    
    /**
     *เซ็ตค่าให้กับ ComboBox สถานะการบำบัด
     *@Author pu
     *@date 28/03/2006
     */
    private void initComboBoxTreatStatus()
    {
        this.vTreat = new Vector();
        ComboFix cTreat = new ComboFix();
        cTreat.code = "1"; 
        cTreat.name = Language.getTextBundle("treated");
        this.vTreat.add(cTreat);
        cTreat = null;
        
        ComboFix cTreat1 = new ComboFix();
        cTreat1.code = "0";
        cTreat1.name = Language.getTextBundle("non-treat");
        this.vTreat.add(cTreat1);
        cTreat1 = null;
        
        ComboFix cTreat2 = new ComboFix();
        cTreat2.code = "2";
        cTreat2.name = Language.getTextBundle("TOTAL");
        this.vTreat.add(cTreat2);
        cTreat2 = null;
        
        theComboboxModel.initComboBox(this.jComboBoxTreat, this.vTreat);
        theComboboxModel.setCodeComboBox(this.jComboBoxTreat, "2");
        this.treatstr ="2";
    }
    
    /**
     *เซ็ตค่าให้กับ ComboBox สถานะการบำบัด
     *@Author pu
     *@date 28/03/2006
     */
    private void initComboBoxRegistryStatus()
    {
        this.vRegistry = new Vector();
        ComboFix cRegistry = new ComboFix();
        cRegistry.code = "1";
        cRegistry.name = Language.getTextBundle("registered");
        this.vRegistry.add(cRegistry);
        cRegistry = null;
        
        ComboFix cRegistry1 = new ComboFix();
        cRegistry1.code = "0";
        cRegistry1.name = Language.getTextBundle("non-register");
        this.vRegistry.add(cRegistry1);
        cRegistry = null;
        
        ComboFix cRegistry2 = new ComboFix();
        cRegistry2.code = "2";
        cRegistry2.name = Language.getTextBundle("TOTAL");
        this.vRegistry.add(cRegistry2);
        cRegistry2 = null;
        
        
        theComboboxModel.initComboBox(this.jComboBoxRegistry, this.vRegistry);
        theComboboxModel.setCodeComboBox(this.jComboBoxRegistry, "2");
        this.registrystr = "2";
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabelVillage = new javax.swing.JLabel();
        jComboBoxVillage = new javax.swing.JComboBox();
        jLabelMaimType = new javax.swing.JLabel();
        jComboBoxMaimType = new javax.swing.JComboBox();
        jPanelMain_Type = new javax.swing.JPanel();
        jCheckBoxTreatStr = new javax.swing.JCheckBox();
        jCheckBoxRegistryStr = new javax.swing.JCheckBox();
        jComboBoxRegistry = new javax.swing.JComboBox();
        jComboBoxTreat = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        fixedColumnScrollPane1 = new com.hospital_os.utility.FixedColumnScrollPane();
        jTableMaim = new javax.swing.JTable();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabelVillage.setText("Village");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel3.add(jLabelVillage, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel3.add(jComboBoxVillage, gridBagConstraints);

        jLabelMaimType.setText("MaimType");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel3.add(jLabelMaimType, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel3.add(jComboBoxMaimType, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel1.add(jPanel3, gridBagConstraints);

        jPanelMain_Type.setLayout(new java.awt.GridBagLayout());

        jPanelMain_Type.setBorder(new javax.swing.border.TitledBorder("SortByStatus"));
        jCheckBoxTreatStr.setText("TreatStr");
        jCheckBoxTreatStr.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jCheckBoxTreatStrActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelMain_Type.add(jCheckBoxTreatStr, gridBagConstraints);

        jCheckBoxRegistryStr.setText("RegistryStr");
        jCheckBoxRegistryStr.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jCheckBoxRegistryStrActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanelMain_Type.add(jCheckBoxRegistryStr, gridBagConstraints);

        jComboBoxRegistry.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jComboBoxRegistryActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelMain_Type.add(jComboBoxRegistry, gridBagConstraints);

        jComboBoxTreat.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jComboBoxTreatActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanelMain_Type.add(jComboBoxTreat, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jPanelMain_Type, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jTableMaim.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {

            }
        ));
        jTableMaim.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        fixedColumnScrollPane1.setViewportView(jTableMaim);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(fixedColumnScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 3, 3);
        add(jPanel2, gridBagConstraints);

    }
    // </editor-fold>//GEN-END:initComponents

    private void jCheckBoxRegistryStrActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jCheckBoxRegistryStrActionPerformed
    {//GEN-HEADEREND:event_jCheckBoxRegistryStrActionPerformed
        //ขึ้นทะเบียน
        if(jCheckBoxRegistryStr.isSelected())
        {
            this.jComboBoxRegistry.setEnabled(true);
        }
        //ไม่ขึ้นทะเบียน
        else
        {
            theComboboxModel.setCodeComboBox(this.jComboBoxRegistry, "2");
            this.jComboBoxRegistry.setEnabled(false);
            this.registrystr = "2";
        }        
    }//GEN-LAST:event_jCheckBoxRegistryStrActionPerformed
    
    private void jCheckBoxTreatStrActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jCheckBoxTreatStrActionPerformed
    {//GEN-HEADEREND:event_jCheckBoxTreatStrActionPerformed
        //บำบัด
        if(jCheckBoxTreatStr.isSelected())
        {
            this.jComboBoxTreat.setEnabled(true);
        }
        //ไม่บำบัด
        else
        {    
            theComboboxModel.setCodeComboBox(this.jComboBoxTreat, "2");
            this.jComboBoxTreat.setEnabled(false);            
            this.treatstr = "2";
        }
    }//GEN-LAST:event_jCheckBoxTreatStrActionPerformed

    private void jComboBoxRegistryActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBoxRegistryActionPerformed
    {//GEN-HEADEREND:event_jComboBoxRegistryActionPerformed
       this.registrystr = this.theComboboxModel.getCodeComboBox(this.jComboBoxRegistry); 
    }//GEN-LAST:event_jComboBoxRegistryActionPerformed

    private void jComboBoxTreatActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBoxTreatActionPerformed
    {//GEN-HEADEREND:event_jComboBoxTreatActionPerformed
        this.treatstr = this.theComboboxModel.getCodeComboBox(this.jComboBoxTreat);
    }//GEN-LAST:event_jComboBoxTreatActionPerformed
   /**
    *  ใช้ในการรับข้อมูลจาก การค้นหา และส่งค่าเข้ามา panel นี้
    *  เพื่อให้ทำการ Query และค้นหารายการตามข้อกำหนดของ panel
    *  @param startDate เป็น String เป็นวันที่เริ่มต้น อยู่ในรูปแบบ yyyy-mm-dd
    *  @param endDate เป็น String เป็นวันที่สิ้นสุด อยู่ในรูปแบบ yyyy-mm-dd
    **/
    public void setQueryReport(String startDate, String endDate, boolean dbBackup)
    {
        this.startDate = startDate;
        this.endDate = endDate;
        isDbBackup = dbBackup;
        startQuery();
    }
    private void startQuery()
    {
        theThread = new Thread(this);
        theThread.start();
    }
    
    public Vector getPersontMaimPCU()
    {
        Vector vTemp = new Vector();
        vTemp = convertToThaiData(this.vcData);
        return vTemp;
    }
    
    /**
     *แปลงข้อมูลเป็นภาษาไทย ก่อนนำไปเขียนลงไฟล์
     *@Author pu
     *@date 28/03/2006
     */
    private Vector convertToThaiData(Vector vc)
    {
        Vector vdata = new Vector();        
        if(vc != null)
        {
            headColumn = (String[])vc.get(0);
            vcDataQuery = (Vector)vc.get(1);
        }
        int size = 0;
        size = vcDataQuery.size();
        vcDataTemp = new Vector();
        String[] colData = null;
        for(int i=0 ;i<size; i++)
        {    //วนลูปตของ column
            String[] rowdata = (String[])vcDataQuery.get(i);   
            colData  = new String[rowdata.length];
            for(int j = 0 ; j < rowdata.length ;j++)
            {
                colData[j] = Language.getTextBundle(rowdata[j]);
            }
            rowdata = null;
            vcDataTemp.add(i, colData); 
        }
        vdata.add(0, headColumn);
        vdata.add(1, vcDataTemp);        
        return vdata;
    }
    public String getCardName()
    {
        return cardName;
    }
    
    public void notifySetInitAllGUI()
    {
        clearDataGUI();
    }
    /**ใช้ในการ Clear ข้อมูลที่อยู่บนตาราง*/
    private void clearDataGUI()
    {
        System.out.println("Clear Data in GUI");
        vcData = null;
        showDataInTable(null,null);
    }
    public void notifyStopProcess()
    {
        try
        {
            if(theThread != null)
            {
                theThread.stop();
            }
            theThread = null;
            System.out.println("In stop in PanelMaimPCU");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void run()
    {
        this.village = this.theComboboxModel.getCodeComboBox(jComboBoxVillage);
        this.maimtype = this.theComboboxModel.getCodeComboBox(jComboBoxMaimType);
        queryPersonMaimByDate();
    }
    /**
     *ค้นหาข้อมูลผู้พิการ แยกตามกลุ่มความพิการ และหมู่บ้าน
     *@Author pu
     *@Date 27/03/2006
     */
    private void queryPersonMaimByDate()
    {
        if(this.theMSC.theManageControl.theGeneralPCUControl.setDateForQuery(this.startDate, this.endDate))
        {
            theDialogShowStatus.setVisible(true);
            theDialogShowStatus.showDialog(Language.getTextBundle("PleaseWait"),false);
            
            this.vcData = this.theMSC.theManageControl.theGeneralPCUControl.queryPersonMaim(this.village
                    , this.maimtype
                    ,this.treatstr
                    ,this.registrystr
                    ,isDbBackup);
            
            headColumn = new String[] {""};
            vcDataQuery = null;
            if(vcData != null)
            {
                headColumn = (String[])vcData.get(0);
                vcDataQuery = (Vector)vcData.get(1);
            }
            
            showDataInTable(headColumn, vcDataQuery);
            theDialogShowStatus.setVisible(false);
        }
        else
        {
            showMessageStartYearOver();
        }
    }
    
    private void showDataInTable(String[] columnname,Vector vc)
    {
        String[] col = columnname;        
        int size = 0;
        if(vc != null)
        {   theTableModelGUI= new TableModelGUI(col,vc.size());
            size = vc.size();
            //วนลูป ต่อ 1 แถว
            for(int i=0 ;i<size; i++)
            {    //วนลูปตของ column
                String[] rowdata = (String[])vc.get(i);
                for(int j = 0 ; j < rowdata.length ;j++)
                {
                   theTableModelGUI.setValueAt(Language.getTextBundle(rowdata[j]),i,j);                   
                }
                theTableModelGUI.setEditingCol(rowdata.length+1);
                rowdata = null;
            }
        }
        else
        {   
            theTableModelGUI= new TableModelGUI(col,0);
        }
        this.jTableMaim.setModel(theTableModelGUI);
        if(col!= null && col.length!= 0)
        {
            fixedColumnScrollPane1.setFixedColumnScrollPane(jTableMaim, 1, 60);
            setTableListReportPattern(col);
        }
        else
        {
            fixedColumnScrollPane1.setFixedColumnScrollPane(jTableMaim, 0, 100);
            setTableListReportPattern(new String[0]);
        }
        sendDataToMainReport(size);
    }
    /**ใช้ในการแสดงความกว้างของคอลัมน์
     *@param col เป็น Array ของ String ที่เก็บหัว column สำหรับนำมานับจำนวน Column ที่ต้องสร้างในตาราง
     */
    private void setTableListReportPattern(String [] col)
    {
        if(rendererCenter == null )
        {
            rendererCenter = new DefaultTableCellRenderer();
        }
        if(rendererRight == null)
        {
            rendererRight = new DefaultTableCellRenderer();
        }
        rendererCenter.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        rendererRight.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        
        String[] col_number = col;
        int size = col_number.length;
        for(int i=0;i<size-1;i++)
        {
            if(i == 0 && i==1)
            {
                jTableMaim.getColumnModel().getColumn(i).setPreferredWidth(120);
            }
            else if(i == 4 && i==5)
            {
                jTableMaim.getColumnModel().getColumn(i).setCellRenderer(rendererRight);
                jTableMaim.getColumnModel().getColumn(i).setPreferredWidth(80);
            }
            else
            {
                jTableMaim.getColumnModel().getColumn(i).setPreferredWidth(100);
            }
        }
    }
    /**
     *  ใช้ในการส่งสถานะให้แสดง ปุ่มบันทึกหรือไม่ โดยจะตรวจสอบจาก size ของตาราง
     */
    private void sendDataToMainReport(int size)
    {
        theMSC.theManageSubject.theMainReportSubject.notifyShowSaveToFile(false);
        if(size >0)
        {
            theMSC.theManageSubject.theMainReportSubject.notifyShowSaveToFile(true);
        }
    }
    /**แสดงข้อความ วันที่เริ่ม และวันที่สิ้นสุด ต้อง มีปี เดียวกัน*/
    private void showMessageStartYearOver()
    {
        JOptionPane.showMessageDialog(this, Language.getTextBundle("StartYearNotSameEndYear"),Language.getTextBundle("Warning"),JOptionPane.OK_OPTION);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.utility.FixedColumnScrollPane fixedColumnScrollPane1;
    private javax.swing.JCheckBox jCheckBoxRegistryStr;
    private javax.swing.JCheckBox jCheckBoxTreatStr;
    private javax.swing.JComboBox jComboBoxMaimType;
    private javax.swing.JComboBox jComboBoxRegistry;
    private javax.swing.JComboBox jComboBoxTreat;
    private javax.swing.JComboBox jComboBoxVillage;
    private javax.swing.JLabel jLabelMaimType;
    private javax.swing.JLabel jLabelVillage;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelMain_Type;
    private javax.swing.JTable jTableMaim;
    // End of variables declaration//GEN-END:variables
    
    private void setLanguage()
    {
        jLabelVillage.setText(Language.getTextBundle(jLabelVillage.getText()));
        jLabelMaimType.setText(Language.getTextBundle(jLabelMaimType.getText()));
        jCheckBoxTreatStr.setText(Language.getTextBundle(jCheckBoxTreatStr.getText()));
        jCheckBoxRegistryStr.setText(Language.getTextBundle(jCheckBoxRegistryStr.getText()));
        
        Language.JPanelLabler(jPanelMain_Type);
    }
}
