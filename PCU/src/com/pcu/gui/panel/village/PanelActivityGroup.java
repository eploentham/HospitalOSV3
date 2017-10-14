/*
 * PanelActivityGroup.java
 *
 * Created on 11 เมษายน 2550, 14:07 น.
 */

package com.pcu.gui.panel.village;

import com.hospital_os.object.Site;
import com.hospital_os.utility.ComboFix;
import com.hospital_os.utility.ComboboxModel;
import com.hospital_os.utility.Gutil;
import com.hospital_os.utility.TaBleModel;
import com.hosv3.utility.connection.UpdateStatus;
import com.pcu.control.AllComboBoxControl;
import com.pcu.control.HosManage;
import com.pcu.control.PCUObject;
import com.pcu.control.SystemControl;
import com.pcu.control.VillageControl;
import com.pcu.control.VillageObserv;
import com.pcu.object.AGR;
import com.pcu.object.AGRHistory;
import com.pcu.object.PcuAnswer;
import com.pcu.utility.ColumnTableRenderer;
import com.pcu.utility.GutilPCU;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author  Administrator
 */
public class PanelActivityGroup extends javax.swing.JPanel implements VillageObserv{
    
    protected AGR theAGR;
    protected AGRHistory theAGRHistory;
    protected Vector vAGR = new Vector();
    protected Vector vAGRHistory = new Vector();
    protected String standardTypeAGR = "";
    protected boolean flagAGR = true;
    protected int rowAGR;

    protected AllComboBoxControl theAllComboBoxControl;

    protected String employeeId;

    protected SystemControl theSystemControl;

    protected ComboFix cfv;

    protected VillageControl theVillageControl;

    private UpdateStatus theUS;
    /** Creates new form PanelActivityGroup */
    public PanelActivityGroup() {
        initComponents();
        setLanguage();
    }
    /**
     *  implements VillageObserv
     */
    public boolean refreshVillage() {
        ComboboxModel.initComboBox(this.jComboBoxVillageAGR,theAllComboBoxControl.listVillage());
        ComboboxModel.initComboBox(this.jComboBoxSearchVillageAGR,theAllComboBoxControl.listVillage());
        return true;
    }
    public void setControl(HosManage hm,UpdateStatus us) 
    {
        theUS = us;
        theSystemControl = hm.theHosControl.theSystemControl;
        theAllComboBoxControl = hm.theHosControl.theAllComboBoxControl;
        theVillageControl = hm.theHosControl.theVillageControl;

        AllComboBoxControl a = theAllComboBoxControl;
        Vector vComboBoxStandardType = theAllComboBoxControl.listCommunityStandardType();
        Vector vComboBoxStandard = theAllComboBoxControl.listCommunityStandard();
        Vector vComboboxChangwat = theAllComboBoxControl.listAddressCGW();
        this.panelCatAddress1.setControl(hm.theHosInf.getLookupControl(),null);
        ComboboxModel.initComboBox(jComboBoxStandardTypeAGR,vComboBoxStandardType);
        ComboboxModel.initComboBox(jComboBoxStandardAGR,vComboBoxStandard);
        ComboboxModel.initComboBox(jComboBoxAGRCode,theAllComboBoxControl.listAGRCode());
        ComboboxModel.initComboBox(jComboBoxAGRType,theAllComboBoxControl.listAGRType());      
        ComboboxModel.initComboBox(this.jComboBoxVillageAGR,theAllComboBoxControl.listVillage());
        ComboboxModel.initComboBox(this.jComboBoxSearchVillageAGR,theAllComboBoxControl.listVillage());
    }
    public void setObject(PCUObject pcuobject)
    {  
        if(pcuobject != null)
        {
            if(pcuobject.getEmployee() != null)
            {
                employeeId = pcuobject.getEmployee().getObjectId();
            }
        }
    }
    
    protected void setDefaultHospital()
    {
        SystemControl s = theSystemControl;
        Site theSite = theSystemControl.theHospital_Site;
        if(theSite != null)
        {
            this.panelCatAddress1.setAddress(theSite.changwat,theSite.amphor,theSite.tambon);
        }
    }

    /*****AGR*****/
    protected void clearGuiAGR()
    {
        jTextFieldAGRCode.setText("");
        jTextFieldAGRName.setText("");        
        integerTextFieldStartYearAGRGroup.setText("");        
        jTextFieldHomeNumberAGR.setText("");
        integerTextFieldMooAGR.setText("");
        jTextFieldRoadAGR.setText("");
        jTextFieldPhoneAGR.setText("");         
        jTextFieldChairmanNameAGR.setText("");        
        integerTextFieldManMemberAGR.setText("");        
        integerTextFieldWomanMemberAGR.setText("");        
        integerTextFieldCapitalAGR.setText("");
        integerTextFieldTimeOfPassAGR.setText("");
        dateComboBoxDateOfPassAGR.initCurrentDate();
        jTextFieldPeriodOfTimeAGR.setText("");       
        jCheckBoxCloseAGR.setSelected(false);        
        jCheckBoxCloseAGRMouseReleased(null);
        dateComboBoxCloseAGR.initCurrentDate();  
        Gutil.setGuiData(jComboBoxVillageAGR,Gutil.getGuiData(jComboBoxSearchVillageAGR));     
        setDefaultHospital();
    }
    
    protected void setEnableGuiAGR(boolean flag)
    {
        jTextFieldAGRCode.setEnabled(flag);        
        jTextFieldAGRName.setEnabled(flag); 
        jRadioButtonActivityGroupNoRegister.setEnabled(flag);
        jRadioButtonActivityGroupRegister.setEnabled(flag);
        jComboBoxAGRCode.setEnabled(flag);        
        jComboBoxAGRType.setEnabled(flag);
        integerTextFieldStartYearAGRGroup.setEnabled(flag);        
        jTextFieldHomeNumberAGR.setEnabled(flag);
        integerTextFieldMooAGR.setEnabled(flag);
        jTextFieldRoadAGR.setEnabled(flag);
        jTextFieldPhoneAGR.setEnabled(flag);
        this.panelCatAddress1.setEnabled(flag);
        jTextFieldChairmanNameAGR.setEnabled(flag);        
        integerTextFieldManMemberAGR.setEnabled(flag);
        integerTextFieldWomanMemberAGR.setEnabled(flag);
        integerTextFieldCapitalAGR.setEnabled(flag);        
        jComboBoxStandardTypeAGR.setEnabled(flag);        
        jComboBoxStandardAGR.setEnabled(flag);        
        integerTextFieldTimeOfPassAGR.setEnabled(flag);        
        dateComboBoxDateOfPassAGR.setEnabled(flag);        
        jTextFieldPeriodOfTimeAGR.setEnabled(flag);        
        jCheckBoxCloseAGR.setEnabled(flag);        
        jComboBoxVillageAGR.setEnabled(flag);        
        jButtonDeleteAGR.setEnabled(flag);
        jButtonSaveAGR.setEnabled(flag);
    }
    
    protected void addAGR()
    {
        clearGuiAGR();
        theAGR = new AGR();
        theAGRHistory = new AGRHistory();
        standardTypeAGR = "";
        jTextAreaCauseCloseAGR.setText("");
        setEnableGuiAGR(true);
        /*ยกเว้นปุ่มลบและปุ่มบวก*/        
        jButtonDeleteAGR.setEnabled(false);
        /*และวันที่เลิกกิจกรรม*/
        jTextFieldStandardTypeAGRDescription.setVisible(false);
        jRadioButtonActivityGroupRegister.setSelected(true);
        jTableAGR.clearSelection();
        vAGRHistory = null;
        setTableAGRHistory();
    }
    
    protected void saveAGR()
    {    
        String code = jTextFieldAGRCode.getText();
        String name = jTextFieldAGRName.getText();
        if(code.equals("") && !name.equals(""))
        {
            theUS.setStatus(GutilPCU.getTextBundle("FillNumberAndName"),UpdateStatus.WARNING);
            return;
        }
            /***AGR***/
            theAGR.agr_number = code;
            theAGR.agr_name = name;
            if(jCheckBoxCloseAGR.isSelected())
            {
                theAGR.agr_close = "1";                
                theAGR.agr_close_date_time = dateComboBoxCloseAGR.getText();
                theAGR.agr_close_note = jTextAreaCauseCloseAGR.getText();
            }
            else
            {
                theAGR.agr_close = "0";
                theAGR.agr_close_date_time = "";
                theAGR.agr_close_note = "";
            }
            theAGR.agr_active = "1";
            theAGR.village_id = Gutil.getGuiData(jComboBoxVillageAGR);

            /***AGRHistory***/
            if(jRadioButtonActivityGroupNoRegister.isSelected())
            {
                theAGRHistory.register_id = PcuAnswer.Zero();
            }
            else
            {
                theAGRHistory.register_id = PcuAnswer.One();
            }
            theAGRHistory.agr_history_group = Gutil.getGuiData(jComboBoxAGRCode);            
            theAGRHistory.agr_history_type = Gutil.getGuiData(jComboBoxAGRType);            
            theAGRHistory.agr_history_start_year = integerTextFieldStartYearAGRGroup.getText();
            theAGRHistory.agr_history_home_number = jTextFieldHomeNumberAGR.getText();
            theAGRHistory.agr_history_moo = integerTextFieldMooAGR.getText();
            theAGRHistory.agr_history_road = jTextFieldRoadAGR.getText();
            theAGRHistory.agr_history_phone = jTextFieldPhoneAGR.getText();            
            theAGRHistory.agr_history_changwat = this.panelCatAddress1.getChangwat();
            theAGRHistory.agr_history_amphur = panelCatAddress1.getAmpur();
            theAGRHistory.agr_history_tambol = panelCatAddress1.getTambon();
            theAGRHistory.agr_history_chairman_name = jTextFieldChairmanNameAGR.getText();             
            theAGRHistory.agr_history_man_member = integerTextFieldManMemberAGR.getText();          
            theAGRHistory.agr_history_woman_member = integerTextFieldWomanMemberAGR.getText();          
            theAGRHistory.agr_history_capital = integerTextFieldCapitalAGR.getText(); 
            theAGRHistory.standard_type_id = Gutil.getGuiData(jComboBoxStandardTypeAGR);
            theAGRHistory.standard_type_description = jTextFieldStandardTypeAGRDescription.getText();            
            theAGRHistory.standard_id = Gutil.getGuiData(jComboBoxStandardAGR);            
            theAGRHistory.time_of_pass = integerTextFieldTimeOfPassAGR.getText();
            theAGRHistory.date_of_pass = dateComboBoxDateOfPassAGR.getText();
            theAGRHistory.period_of_pass = jTextFieldPeriodOfTimeAGR.getText(); 
            boolean ret = theVillageControl.saveAGRAndHistory(theAGR,theAGRHistory,theUS);
            
        if(!ret)
            return;

        int row = this.jTableAGR.getSelectedRow();
        this.searchAGR();
        if(row==-1)
            row = jTableAGR.getRowCount()-1;
        jTableAGR.setRowSelectionInterval(row,row);
        this.jTableAGRMouseReleased(null);
    }
    
    protected void updateAGR()
    {
        String code = jTextFieldAGRCode.getText();
        String name = jTextFieldAGRName.getText();
        if(code.equals("") && !name.equals(""))
        {
            theUS.setStatus(GutilPCU.getTextBundle("FillNumberAndName"),UpdateStatus.WARNING);
            return;
        }
            /***AGR***/
            theAGR.agr_number = code;
            theAGR.agr_name = name;
            if(jCheckBoxCloseAGR.isSelected())
            {
                theAGR.agr_close = "1";
                theAGR.agr_close_date_time = dateComboBoxCloseAGR.getText();
                theAGR.agr_close_note = jTextAreaCauseCloseAGR.getText();
            }
            else
            {
                theAGR.agr_close = "0";
                theAGR.agr_close_date_time = "";
                theAGR.agr_close_note = "";
            }
            theAGR.village_id = Gutil.getGuiData(jComboBoxVillageAGR);
                        
            /***AGRHistory***/
            if(jRadioButtonActivityGroupNoRegister.isSelected())
            {
                theAGRHistory.register_id = PcuAnswer.Zero();
            }
            else{
                theAGRHistory.register_id = PcuAnswer.One();
            }
            theAGRHistory.agr_history_group = Gutil.getGuiData(jComboBoxAGRCode);            
            theAGRHistory.agr_history_type = Gutil.getGuiData(jComboBoxAGRType);            
            theAGRHistory.agr_history_start_year = integerTextFieldStartYearAGRGroup.getText();
            theAGRHistory.agr_history_home_number = jTextFieldHomeNumberAGR.getText();
            theAGRHistory.agr_history_moo = integerTextFieldMooAGR.getText();
            theAGRHistory.agr_history_road = jTextFieldRoadAGR.getText();
            theAGRHistory.agr_history_phone = jTextFieldPhoneAGR.getText(); 
            theAGRHistory.agr_history_changwat = panelCatAddress1.getChangwat();
            theAGRHistory.agr_history_amphur = panelCatAddress1.getAmpur();
            theAGRHistory.agr_history_tambol = panelCatAddress1.getTambon();
            theAGRHistory.agr_history_chairman_name = jTextFieldChairmanNameAGR.getText();             
            theAGRHistory.agr_history_man_member = integerTextFieldManMemberAGR.getText();          
            theAGRHistory.agr_history_woman_member = integerTextFieldWomanMemberAGR.getText();          
            theAGRHistory.agr_history_capital = integerTextFieldCapitalAGR.getText(); 
            theAGRHistory.standard_type_id = Gutil.getGuiData(jComboBoxStandardTypeAGR);
            theAGRHistory.standard_type_description = jTextFieldStandardTypeAGRDescription.getText();            
            theAGRHistory.standard_id = Gutil.getGuiData(jComboBoxStandardAGR);            
            theAGRHistory.time_of_pass = integerTextFieldTimeOfPassAGR.getText();
            theAGRHistory.date_of_pass = dateComboBoxDateOfPassAGR.getText();
            theAGRHistory.period_of_pass = jTextFieldPeriodOfTimeAGR.getText(); 
        boolean ret = theVillageControl.updateAGRAndHistory(theAGR,theAGRHistory);
        if(!ret)
            return;

        int row = this.jTableAGR.getSelectedRow();
        this.searchAGR();
        if(row==-1)
            row = jTableAGR.getRowCount()-1;
        jTableAGR.setRowSelectionInterval(row,row);
        this.jTableAGRMouseReleased(null);
    }
    
    protected void searchAGR()
    {   
        search("");
    }
    protected void search(String village_id)
    {   
        if(village_id!=null && !village_id.equals(""))
        {
            Gutil.setGuiData(jComboBoxSearchVillageAGR,village_id);
            Gutil.setGuiData(jComboBoxVillageAGR,village_id);
        }
        
        /***ค้นหากลุ่มกิจกรรม***/
        String search = jTextFieldSearchAGR.getText();      
        String village = "0";
        if(this.jCheckBoxVillage.isSelected())
            village = Gutil.getGuiData(jComboBoxSearchVillageAGR);
        vAGR =  theVillageControl.listAGRByNameOrNumber(search,village);
        setTableAGR();
        vAGRHistory =  null;
        setTableAGRHistory();
        setEnableGuiAGR(false);
    }
    
    protected void setTableAGR()
    {
        String[] col = {GutilPCU.getTextBundle("Code"),
                    GutilPCU.getTextBundle("AGRName"),
                    GutilPCU.getTextBundle("VillageName")};
                    
        AGR aGRTemp = new AGR();
        TaBleModel tm ;
        
        if(vAGR != null)
        {   
            tm = new TaBleModel(col,vAGR.size());
            for(int i=0, size=vAGR.size(); i<size; i++)
            {  
                aGRTemp = (AGR)vAGR.get(i);
                tm.setValueAt(aGRTemp.agr_number,i,0); 
                tm.setValueAt(aGRTemp.agr_name,i,1);
                tm.setValueAt(ComboboxModel.getDescriptionComboBox(jComboBoxVillageAGR,aGRTemp.village_id),i,2); 
            }
        }
        else
        {   
            tm= new TaBleModel(col,0);
        }         
        aGRTemp = null;
        jTableAGR.setModel(tm);  
        
        /***SetTableDefault***/
        jTableAGR.getColumnModel().getColumn(0).setPreferredWidth(150);     /*รหัสสถานประกอบการ*/
        jTableAGR.getColumnModel().getColumn(0).setCellRenderer(ColumnTableRenderer.getRendererCenter());
        jTableAGR.getColumnModel().getColumn(1).setPreferredWidth(200);     /*ชื่อสถานประกอบการ*/
        jTableAGR.getColumnModel().getColumn(2).setPreferredWidth(200);     /*ชื่อหมู่บ้าน*/
    }
    
    protected void selectAGR(int row)
    {
        if(row==-2)
        {/*user เป็นผู้เลือกเอง*/
            rowAGR = jTableAGR.getSelectedRow();            
        }
        else
        {/*user บันทึก จะ select record ที่เพิ่งเพิ่ม*/
            rowAGR = row;            
        }        
        theAGR = (AGR)vAGR.get(rowAGR);   
        
        setEnableGuiAGR(true);
        jTextFieldAGRCode.setText(theAGR.agr_number);
        jTextFieldAGRName.setText(theAGR.agr_name);        
        if(theAGR.agr_close.equals("1"))
        {
            jCheckBoxCloseAGR.setSelected(true);
            jCheckBoxCloseAGRMouseReleased(null);
            dateComboBoxCloseAGR.setText(Gutil.convertFieldDate(theAGR.agr_close_date_time));
            jTextAreaCauseCloseAGR.setText(theAGR.agr_close_note);
            dateComboBoxCloseAGR.setEnabled(true);
            jTextAreaCauseCloseAGR.setEnabled(true);
        }
        else
        {
            jCheckBoxCloseAGR.setSelected(false);
            jCheckBoxCloseAGRMouseReleased(null);
        }        
        ComboboxModel.setCodeComboBox(jComboBoxVillageAGR,theAGR.village_id);
        
        vAGRHistory =  theVillageControl.listAGRHistoryByAGRId(theAGR.getObjectId());
        setTableAGRHistory();
        
        if(theAGRHistory.register_id.equals(PcuAnswer.Zero()))
        {
            jRadioButtonActivityGroupNoRegister.setSelected(true);
        }
        else
        {
            jRadioButtonActivityGroupRegister.setSelected(true);
        }
        ComboboxModel.setCodeComboBox(jComboBoxAGRCode,theAGRHistory.agr_history_group); 
        ComboboxModel.setCodeComboBox(jComboBoxAGRType,theAGRHistory.agr_history_type); 
        integerTextFieldStartYearAGRGroup.setText(theAGRHistory.agr_history_start_year);
        jTextFieldHomeNumberAGR.setText(theAGRHistory.agr_history_home_number);     
        integerTextFieldMooAGR.setText(theAGRHistory.agr_history_moo);        
        jTextFieldRoadAGR.setText(theAGRHistory.agr_history_road);       
        jTextFieldPhoneAGR.setText(theAGRHistory.agr_history_phone);       
        this.panelCatAddress1.setAddress(
            theAGRHistory.agr_history_changwat,theAGRHistory.agr_history_amphur
            ,theAGRHistory.agr_history_tambol);
        jTextFieldChairmanNameAGR.setText(theAGRHistory.agr_history_chairman_name);        
        integerTextFieldManMemberAGR.setText(theAGRHistory.agr_history_man_member);
        integerTextFieldWomanMemberAGR.setText(theAGRHistory.agr_history_woman_member);
        integerTextFieldCapitalAGR.setText(theAGRHistory.agr_history_capital);        
        ComboboxModel.setCodeComboBox(jComboBoxStandardTypeAGR,theAGRHistory.standard_type_id);
        if(theAGRHistory.standard_type_id.equals("90"))
        {
            jTextFieldStandardTypeAGRDescription.setVisible(true);
            jTextFieldStandardTypeAGRDescription.setText(theAGRHistory.standard_type_description);
        }
        else
        {
            jTextFieldStandardTypeAGRDescription.setVisible(false);
        }   
        ComboboxModel.setCodeComboBox(jComboBoxStandardAGR,theAGRHistory.standard_id);        
        jComboBoxStandardAGRActionPerformed(null);
        integerTextFieldTimeOfPassAGR.setText(theAGRHistory.time_of_pass);
        dateComboBoxDateOfPassAGR.setText(Gutil.convertFieldDate(theAGRHistory.date_of_pass));
        jTextFieldPeriodOfTimeAGR.setText(theAGRHistory.period_of_pass);
                
    }
    
    protected void selectAGRHistory()
    {
        AGRHistory aGRHistoryTemp = (AGRHistory)vAGRHistory.get(jTableHistoryAGR.getSelectedRow()); 
        if(aGRHistoryTemp.register_id.equals(PcuAnswer.Zero()))
        {
            jRadioButtonActivityGroupNoRegister.setSelected(true);
        }
        else
        {
            jRadioButtonActivityGroupRegister.setSelected(true);
        }
        ComboboxModel.setCodeComboBox(jComboBoxAGRCode,aGRHistoryTemp.agr_history_group); 
        ComboboxModel.setCodeComboBox(jComboBoxAGRType,aGRHistoryTemp.agr_history_type);        
        integerTextFieldStartYearAGRGroup.setText(aGRHistoryTemp.agr_history_start_year);
        jTextFieldHomeNumberAGR.setText(aGRHistoryTemp.agr_history_home_number);     
        integerTextFieldMooAGR.setText(aGRHistoryTemp.agr_history_moo);        
        jTextFieldRoadAGR.setText(aGRHistoryTemp.agr_history_road);       
        jTextFieldPhoneAGR.setText(aGRHistoryTemp.agr_history_phone);    
        this.panelCatAddress1.setAddress(aGRHistoryTemp.agr_history_changwat
                ,aGRHistoryTemp.agr_history_amphur,aGRHistoryTemp.agr_history_tambol);
        jTextFieldChairmanNameAGR.setText(aGRHistoryTemp.agr_history_chairman_name);        
        integerTextFieldManMemberAGR.setText(aGRHistoryTemp.agr_history_man_member);
        integerTextFieldWomanMemberAGR.setText(aGRHistoryTemp.agr_history_woman_member);
        integerTextFieldCapitalAGR.setText(aGRHistoryTemp.agr_history_capital);        
        ComboboxModel.setCodeComboBox(jComboBoxStandardTypeAGR,aGRHistoryTemp.standard_type_id);
        jTextFieldStandardTypeAGRDescription.setText(aGRHistoryTemp.standard_type_description);
        ComboboxModel.setCodeComboBox(jComboBoxStandardAGR,aGRHistoryTemp.standard_id);        
        jComboBoxStandardAGRActionPerformed(null);
        integerTextFieldTimeOfPassAGR.setText(aGRHistoryTemp.time_of_pass);
        dateComboBoxDateOfPassAGR.setText(Gutil.convertFieldDate(aGRHistoryTemp.date_of_pass));
        jTextFieldPeriodOfTimeAGR.setText(aGRHistoryTemp.period_of_pass);
        
        setEnableGuiAGR(false);
        dateComboBoxCloseAGR.setEnabled(false);
        jTextAreaCauseCloseAGR.setEnabled(false);
        jTextFieldStandardTypeAGRDescription.setEnabled(false);    
    }
    
    
    protected void setTableAGRHistory()
    { 
        String[] col = {GutilPCU.getTextBundle("DateRecord")};
                    
        TaBleModel tm ;
        
        if(vAGRHistory != null)
        {   
            int size = vAGRHistory.size()-1;    
            tm = new TaBleModel(col,size);
            for(int i=0; i<size; i++)
            {  
                AGRHistory aGRHistoryTemp = (AGRHistory)vAGRHistory.get(i);
                if(i==0)
                    theAGRHistory = aGRHistoryTemp;
                
                if(!("").equals(aGRHistoryTemp.agr_history_record_date_time))
                    tm.setValueAt(GutilPCU.changDateToString(aGRHistoryTemp.agr_history_record_date_time,true),i,0); 
                else
                    tm.setValueAt(GutilPCU.changDateToString(theAGR.agr_modify_date_time,true),i,0); 
            }
        }
        else
        {   
            tm= new TaBleModel(col,0);
        } 
        jTableHistoryAGR.setModel(tm);  
        
    }
    
    protected void deleteAGR()
    {
            boolean ret = theVillageControl.deleteAGR(theAGR,theUS);
            if(!ret)
                return;
            this.searchAGR();
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel75 = new javax.swing.JPanel();
        jPanelSearchAGR = new javax.swing.JPanel();
        jTextFieldSearchAGR = new javax.swing.JTextField();
        jButtonSearchAGR = new javax.swing.JButton();
        jComboBoxSearchVillageAGR = new javax.swing.JComboBox();
        jCheckBoxVillage = new javax.swing.JCheckBox();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTableAGR = new javax.swing.JTable();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTableHistoryAGR = new javax.swing.JTable();
        jPanel64 = new javax.swing.JPanel();
        jButtonAddAGR = new javax.swing.JButton();
        jButtonDeleteAGR = new javax.swing.JButton();
        jButtonSaveAGR = new javax.swing.JButton();
        jCheckBoxSaveHistory = new javax.swing.JCheckBox();
        jPanelData1 = new javax.swing.JPanel();
        jLabel121 = new javax.swing.JLabel();
        jComboBoxAGRType = new javax.swing.JComboBox();
        jComboBoxAGRCode = new javax.swing.JComboBox();
        jLabel132 = new javax.swing.JLabel();
        jPanel57 = new javax.swing.JPanel();
        jPanel58 = new javax.swing.JPanel();
        jLabel134 = new javax.swing.JLabel();
        jTextFieldHomeNumberAGR = new javax.swing.JTextField();
        jLabel135 = new javax.swing.JLabel();
        integerTextFieldMooAGR = new com.pcu.utility.IntegerTextField();
        jLabel136 = new javax.swing.JLabel();
        jTextFieldRoadAGR = new javax.swing.JTextField();
        jLabel137 = new javax.swing.JLabel();
        jTextFieldPhoneAGR = new javax.swing.JTextField();
        panelCatAddress1 = new com.hosv3.gui.component.PanelCatAddress();
        jLabel144 = new javax.swing.JLabel();
        jComboBoxVillageAGR = new javax.swing.JComboBox();
        jLabel154 = new javax.swing.JLabel();
        integerTextFieldStartYearAGRGroup = new com.pcu.utility.IntegerTextField();
        jPanel60 = new javax.swing.JPanel();
        jRadioButtonActivityGroupNoRegister = new javax.swing.JRadioButton();
        jRadioButtonActivityGroupRegister = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel130 = new javax.swing.JLabel();
        jTextFieldAGRName = new javax.swing.JTextField();
        jTextFieldAGRCode = new com.pcu.utility.IntegerTextField();
        jLabel122 = new javax.swing.JLabel();
        jPanelData2 = new javax.swing.JPanel();
        jLabel155 = new javax.swing.JLabel();
        integerTextFieldCapitalAGR = new com.pcu.utility.IntegerTextField();
        jLabel145 = new javax.swing.JLabel();
        jPanel61 = new javax.swing.JPanel();
        jTextFieldStandardTypeAGRDescription = new javax.swing.JTextField();
        jComboBoxStandardTypeAGR = new javax.swing.JComboBox();
        jLabel146 = new javax.swing.JLabel();
        jComboBoxStandardAGR = new javax.swing.JComboBox();
        jLabel149 = new javax.swing.JLabel();
        jPanel62 = new javax.swing.JPanel();
        jTextFieldPeriodOfTimeAGR = new javax.swing.JTextField();
        jLabel150 = new javax.swing.JLabel();
        jPanel63 = new javax.swing.JPanel();
        jLabel152 = new javax.swing.JLabel();
        dateComboBoxCloseAGR = new com.pcu.utility.DateComboBox();
        jLabel153 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaCauseCloseAGR = new javax.swing.JTextArea();
        jCheckBoxCloseAGR = new javax.swing.JCheckBox();
        jLabel141 = new javax.swing.JLabel();
        jTextFieldChairmanNameAGR = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel142 = new javax.swing.JLabel();
        integerTextFieldManMemberAGR = new com.pcu.utility.IntegerTextField();
        integerTextFieldWomanMemberAGR = new com.pcu.utility.IntegerTextField();
        jLabel143 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel147 = new javax.swing.JLabel();
        integerTextFieldTimeOfPassAGR = new com.pcu.utility.IntegerTextField();
        dateComboBoxDateOfPassAGR = new com.pcu.utility.DateComboBox();

        setLayout(new java.awt.GridBagLayout());

        jPanel75.setLayout(new java.awt.GridBagLayout());

        jPanelSearchAGR.setLayout(new java.awt.GridBagLayout());

        jPanelSearchAGR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SearchData", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, defaultFont1));
        jTextFieldSearchAGR.setFont(defaultFont1);
        jTextFieldSearchAGR.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldSearchAGR.setPreferredSize(new java.awt.Dimension(100, 21));
        jTextFieldSearchAGR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchAGRActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanelSearchAGR.add(jTextFieldSearchAGR, gridBagConstraints);

        jButtonSearchAGR.setFont(defaultFont1);
        jButtonSearchAGR.setText("Search");
        jButtonSearchAGR.setMargin(new java.awt.Insets(2, 8, 2, 8));
        jButtonSearchAGR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchAGRActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelSearchAGR.add(jButtonSearchAGR, gridBagConstraints);

        jComboBoxSearchVillageAGR.setFont(defaultFont1);
        jComboBoxSearchVillageAGR.setMinimumSize(new java.awt.Dimension(100, 21));
        jComboBoxSearchVillageAGR.setPreferredSize(new java.awt.Dimension(100, 21));
        jComboBoxSearchVillageAGR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSearchVillageAGRActionPerformed(evt);
            }
        });
        jComboBoxSearchVillageAGR.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBoxSearchVillageAGRFocusGained(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanelSearchAGR.add(jComboBoxSearchVillageAGR, gridBagConstraints);

        jCheckBoxVillage.setSelected(true);
        jCheckBoxVillage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxVillageActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanelSearchAGR.add(jCheckBoxVillage, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel75.add(jPanelSearchAGR, gridBagConstraints);

        jScrollPane19.setBorder(null);
        jScrollPane19.setFont(defaultFont1);
        jTableAGR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableAGR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableAGRMouseReleased(evt);
            }
        });

        jScrollPane19.setViewportView(jTableAGR);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel75.add(jScrollPane19, gridBagConstraints);

        jScrollPane20.setFont(defaultFont1);
        jTableHistoryAGR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableHistoryAGR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableHistoryAGRMouseReleased(evt);
            }
        });

        jScrollPane20.setViewportView(jTableHistoryAGR);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel75.add(jScrollPane20, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        add(jPanel75, gridBagConstraints);

        jPanel64.setLayout(new java.awt.GridBagLayout());

        jButtonAddAGR.setFont(defaultFont1);
        jButtonAddAGR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif")));
        jButtonAddAGR.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonAddAGR.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonAddAGR.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonAddAGR.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonAddAGR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddAGRActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel64.add(jButtonAddAGR, gridBagConstraints);

        jButtonDeleteAGR.setFont(defaultFont1);
        jButtonDeleteAGR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif")));
        jButtonDeleteAGR.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonDeleteAGR.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonDeleteAGR.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonDeleteAGR.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonDeleteAGR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteAGRActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel64.add(jButtonDeleteAGR, gridBagConstraints);

        jButtonSaveAGR.setFont(defaultFont1);
        jButtonSaveAGR.setText("Save");
        jButtonSaveAGR.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonSaveAGR.setMaximumSize(new java.awt.Dimension(67, 24));
        jButtonSaveAGR.setMinimumSize(new java.awt.Dimension(67, 24));
        jButtonSaveAGR.setPreferredSize(new java.awt.Dimension(67, 24));
        jButtonSaveAGR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveAGRActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel64.add(jButtonSaveAGR, gridBagConstraints);

        jCheckBoxSaveHistory.setFont(defaultFont1);
        jCheckBoxSaveHistory.setText("\u0e40\u0e01\u0e47\u0e1a\u0e1b\u0e23\u0e30\u0e27\u0e31\u0e15\u0e34");
        jCheckBoxSaveHistory.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBoxSaveHistory.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBoxSaveHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSaveHistoryActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel64.add(jCheckBoxSaveHistory, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jPanel64, gridBagConstraints);

        jPanelData1.setLayout(new java.awt.GridBagLayout());

        jPanelData1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jLabel121.setFont(defaultFont1);
        jLabel121.setText("AGRType");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 11, 0, 0);
        jPanelData1.add(jLabel121, gridBagConstraints);

        jComboBoxAGRType.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 22, 0, 11);
        jPanelData1.add(jComboBoxAGRType, gridBagConstraints);

        jComboBoxAGRCode.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 22, 0, 11);
        jPanelData1.add(jComboBoxAGRCode, gridBagConstraints);

        jLabel132.setFont(defaultFont1);
        jLabel132.setText("AGRCode");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 11, 0, 0);
        jPanelData1.add(jLabel132, gridBagConstraints);

        jPanel57.setLayout(new java.awt.GridBagLayout());

        jPanel58.setLayout(new java.awt.GridBagLayout());

        jLabel134.setFont(defaultFont1);
        jLabel134.setText("HomeNumber");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel58.add(jLabel134, gridBagConstraints);

        jTextFieldHomeNumberAGR.setColumns(5);
        jTextFieldHomeNumberAGR.setFont(defaultFont1);
        jTextFieldHomeNumberAGR.setMinimumSize(new java.awt.Dimension(59, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel58.add(jTextFieldHomeNumberAGR, gridBagConstraints);

        jLabel135.setFont(defaultFont1);
        jLabel135.setText("Moo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanel58.add(jLabel135, gridBagConstraints);

        integerTextFieldMooAGR.setColumns(3);
        integerTextFieldMooAGR.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldMooAGR.setFont(defaultFont1);
        integerTextFieldMooAGR.setMinimumSize(new java.awt.Dimension(30, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel58.add(integerTextFieldMooAGR, gridBagConstraints);

        jLabel136.setFont(defaultFont1);
        jLabel136.setText("Road");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel58.add(jLabel136, gridBagConstraints);

        jTextFieldRoadAGR.setFont(defaultFont1);
        jTextFieldRoadAGR.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldRoadAGR.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel58.add(jTextFieldRoadAGR, gridBagConstraints);

        jLabel137.setFont(defaultFont1);
        jLabel137.setText("PPTel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel58.add(jLabel137, gridBagConstraints);

        jTextFieldPhoneAGR.setFont(defaultFont1);
        jTextFieldPhoneAGR.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldPhoneAGR.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel58.add(jTextFieldPhoneAGR, gridBagConstraints);

        panelCatAddress1.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel58.add(panelCatAddress1, gridBagConstraints);

        jLabel144.setFont(defaultFont1);
        jLabel144.setText("VillageName");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel58.add(jLabel144, gridBagConstraints);

        jComboBoxVillageAGR.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel58.add(jComboBoxVillageAGR, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel57.add(jPanel58, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 5, 0, 11);
        jPanelData1.add(jPanel57, gridBagConstraints);

        jLabel154.setFont(defaultFont1);
        jLabel154.setText("StartYear");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 11, 0, 0);
        jPanelData1.add(jLabel154, gridBagConstraints);

        integerTextFieldStartYearAGRGroup.setColumns(4);
        integerTextFieldStartYearAGRGroup.setFont(defaultFont1);
        integerTextFieldStartYearAGRGroup.setMinimumSize(new java.awt.Dimension(38, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 5, 0, 11);
        jPanelData1.add(integerTextFieldStartYearAGRGroup, gridBagConstraints);

        jPanel60.setLayout(new java.awt.GridBagLayout());

        buttonGroup1.add(jRadioButtonActivityGroupNoRegister);
        jRadioButtonActivityGroupNoRegister.setFont(defaultFont1);
        jRadioButtonActivityGroupNoRegister.setText("NoRegister");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel60.add(jRadioButtonActivityGroupNoRegister, gridBagConstraints);

        buttonGroup1.add(jRadioButtonActivityGroupRegister);
        jRadioButtonActivityGroupRegister.setFont(defaultFont1);
        jRadioButtonActivityGroupRegister.setSelected(true);
        jRadioButtonActivityGroupRegister.setText("RegisterBy");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel60.add(jRadioButtonActivityGroupRegister, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 11, 0, 11);
        jPanelData1.add(jPanel60, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel130.setFont(defaultFont1);
        jLabel130.setText("AGRName");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 11, 0, 0);
        jPanel3.add(jLabel130, gridBagConstraints);

        jTextFieldAGRName.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 0, 0);
        jPanel3.add(jTextFieldAGRName, gridBagConstraints);

        jTextFieldAGRCode.setColumns(2);
        jTextFieldAGRCode.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldAGRCode.setFont(defaultFont1);
        jTextFieldAGRCode.setMinimumSize(new java.awt.Dimension(26, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 0, 0);
        jPanel3.add(jTextFieldAGRCode, gridBagConstraints);

        jLabel122.setFont(defaultFont1);
        jLabel122.setText("Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel3.add(jLabel122, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 11);
        jPanelData1.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        add(jPanelData1, gridBagConstraints);

        jPanelData2.setLayout(new java.awt.GridBagLayout());

        jPanelData2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jLabel155.setFont(defaultFont1);
        jLabel155.setText("Capital");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 0, 0);
        jPanelData2.add(jLabel155, gridBagConstraints);

        integerTextFieldCapitalAGR.setColumns(8);
        integerTextFieldCapitalAGR.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldCapitalAGR.setMinimumSize(new java.awt.Dimension(70, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 5, 0, 11);
        jPanelData2.add(integerTextFieldCapitalAGR, gridBagConstraints);

        jLabel145.setFont(defaultFont1);
        jLabel145.setText("CommunityStandardType");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelData2.add(jLabel145, gridBagConstraints);

        jPanel61.setLayout(new java.awt.GridBagLayout());

        jTextFieldStandardTypeAGRDescription.setFont(defaultFont1);
        jTextFieldStandardTypeAGRDescription.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldStandardTypeAGRDescriptionFocusLost(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel61.add(jTextFieldStandardTypeAGRDescription, gridBagConstraints);

        jComboBoxStandardTypeAGR.setFont(defaultFont1);
        jComboBoxStandardTypeAGR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxStandardTypeAGRActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel61.add(jComboBoxStandardTypeAGR, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 22, 0, 11);
        jPanelData2.add(jPanel61, gridBagConstraints);

        jLabel146.setFont(defaultFont1);
        jLabel146.setText("CommunityStandard");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelData2.add(jLabel146, gridBagConstraints);

        jComboBoxStandardAGR.setFont(defaultFont1);
        jComboBoxStandardAGR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxStandardAGRActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 22, 0, 11);
        jPanelData2.add(jComboBoxStandardAGR, gridBagConstraints);

        jLabel149.setFont(defaultFont1);
        jLabel149.setText("PeriodOfTime");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelData2.add(jLabel149, gridBagConstraints);

        jPanel62.setLayout(new java.awt.GridBagLayout());

        jTextFieldPeriodOfTimeAGR.setColumns(3);
        jTextFieldPeriodOfTimeAGR.setFont(defaultFont1);
        jTextFieldPeriodOfTimeAGR.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldPeriodOfTimeAGR.setMinimumSize(new java.awt.Dimension(37, 21));
        jPanel62.add(jTextFieldPeriodOfTimeAGR, new java.awt.GridBagConstraints());

        jLabel150.setFont(defaultFont1);
        jLabel150.setText("Month");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel62.add(jLabel150, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 22, 0, 11);
        jPanelData2.add(jPanel62, gridBagConstraints);

        jPanel63.setLayout(new java.awt.GridBagLayout());

        jLabel152.setFont(defaultFont1);
        jLabel152.setText("DateCloseActivity");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel63.add(jLabel152, gridBagConstraints);

        dateComboBoxCloseAGR.setFont(defaultFont1);
        dateComboBoxCloseAGR.setMinimumSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanel63.add(dateComboBoxCloseAGR, gridBagConstraints);

        jLabel153.setFont(defaultFont1);
        jLabel153.setText("CauseOfClose");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel63.add(jLabel153, gridBagConstraints);

        jTextAreaCauseCloseAGR.setFont(defaultFont1);
        jTextAreaCauseCloseAGR.setLineWrap(true);
        jTextAreaCauseCloseAGR.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextAreaCauseCloseAGR);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanel63.add(jScrollPane1, gridBagConstraints);

        jCheckBoxCloseAGR.setFont(defaultFont1);
        jCheckBoxCloseAGR.setText("CloseActivity");
        jCheckBoxCloseAGR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jCheckBoxCloseAGRMouseReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel63.add(jCheckBoxCloseAGR, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelData2.add(jPanel63, gridBagConstraints);

        jLabel141.setFont(defaultFont1);
        jLabel141.setText("ChairmanName");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelData2.add(jLabel141, gridBagConstraints);

        jTextFieldChairmanNameAGR.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 11);
        jPanelData2.add(jTextFieldChairmanNameAGR, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel142.setFont(defaultFont1);
        jLabel142.setText("ManMember");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jLabel142, gridBagConstraints);

        integerTextFieldManMemberAGR.setColumns(3);
        integerTextFieldManMemberAGR.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldManMemberAGR.setFont(defaultFont1);
        integerTextFieldManMemberAGR.setMinimumSize(new java.awt.Dimension(37, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel2.add(integerTextFieldManMemberAGR, gridBagConstraints);

        integerTextFieldWomanMemberAGR.setColumns(3);
        integerTextFieldWomanMemberAGR.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldWomanMemberAGR.setFont(defaultFont1);
        integerTextFieldWomanMemberAGR.setMinimumSize(new java.awt.Dimension(37, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel2.add(integerTextFieldWomanMemberAGR, gridBagConstraints);

        jLabel143.setFont(defaultFont1);
        jLabel143.setText("WomanMember");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel2.add(jLabel143, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelData2.add(jPanel2, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel147.setFont(defaultFont1);
        jLabel147.setText("TimeOfPass");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel4.add(jLabel147, gridBagConstraints);

        integerTextFieldTimeOfPassAGR.setColumns(3);
        integerTextFieldTimeOfPassAGR.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldTimeOfPassAGR.setFont(defaultFont1);
        integerTextFieldTimeOfPassAGR.setMinimumSize(new java.awt.Dimension(39, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel4.add(integerTextFieldTimeOfPassAGR, gridBagConstraints);

        dateComboBoxDateOfPassAGR.setFont(defaultFont1);
        dateComboBoxDateOfPassAGR.setMinimumSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel4.add(dateComboBoxDateOfPassAGR, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelData2.add(jPanel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(jPanelData2, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxSaveHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSaveHistoryActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxSaveHistoryActionPerformed

    private void jCheckBoxVillageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxVillageActionPerformed
        this.jComboBoxSearchVillageAGR.setEnabled(jCheckBoxVillage.isSelected());
    }//GEN-LAST:event_jCheckBoxVillageActionPerformed

    private void jComboBoxStandardAGRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxStandardAGRActionPerformed
        boolean have_gain = jComboBoxStandardAGR.getSelectedIndex()!=0;
        integerTextFieldTimeOfPassAGR.setEnabled(have_gain);
        dateComboBoxDateOfPassAGR.setEnabled(have_gain);
        jTextFieldPeriodOfTimeAGR.setEnabled(have_gain);
    }//GEN-LAST:event_jComboBoxStandardAGRActionPerformed

    private void jComboBoxSearchVillageAGRFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBoxSearchVillageAGRFocusGained
// TODO add your handling code here:
       // ComboboxModel.initComboBox(this.jComboBoxSearchVillageAGR,theAllComboBoxControl.listVillage2());
    }//GEN-LAST:event_jComboBoxSearchVillageAGRFocusGained

    private void jCheckBoxCloseAGRMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxCloseAGRMouseReleased
            dateComboBoxCloseAGR.setEnabled(jCheckBoxCloseAGR.isSelected());
            jTextAreaCauseCloseAGR.setEnabled(jCheckBoxCloseAGR.isSelected());
    }//GEN-LAST:event_jCheckBoxCloseAGRMouseReleased

    private void jTextFieldStandardTypeAGRDescriptionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldStandardTypeAGRDescriptionFocusLost
        standardTypeAGR = jTextFieldStandardTypeAGRDescription.getText();
    }//GEN-LAST:event_jTextFieldStandardTypeAGRDescriptionFocusLost

    private void jComboBoxStandardTypeAGRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxStandardTypeAGRActionPerformed
        /*90 = อื่นๆ ระบุ*/
        if(ComboboxModel.getCodeComboBox(jComboBoxStandardTypeAGR).equals("90")) {
            jTextFieldStandardTypeAGRDescription.setVisible(true);
            jTextFieldStandardTypeAGRDescription.setEnabled(true);
            jTextFieldStandardTypeAGRDescription.setText(standardTypeAGR);
            standardTypeAGR=null;
        } else {
            jTextFieldStandardTypeAGRDescription.setVisible(false);
            jTextFieldStandardTypeAGRDescription.setText("");
        }
    }//GEN-LAST:event_jComboBoxStandardTypeAGRActionPerformed

    private void jTableHistoryAGRMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableHistoryAGRMouseReleased
        selectAGRHistory();
    }//GEN-LAST:event_jTableHistoryAGRMouseReleased

    private void jTableAGRMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAGRMouseReleased
        selectAGR(-2);
    }//GEN-LAST:event_jTableAGRMouseReleased

    private void jComboBoxSearchVillageAGRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSearchVillageAGRActionPerformed
        searchAGR();
    }//GEN-LAST:event_jComboBoxSearchVillageAGRActionPerformed

    private void jButtonSearchAGRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchAGRActionPerformed
        searchAGR();
    }//GEN-LAST:event_jButtonSearchAGRActionPerformed

    private void jTextFieldSearchAGRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchAGRActionPerformed
        searchAGR();
    }//GEN-LAST:event_jTextFieldSearchAGRActionPerformed

    private void jButtonSaveAGRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveAGRActionPerformed
        if(this.jCheckBoxSaveHistory.isSelected() || this.theAGR.getObjectId()==null)
            saveAGR();
        else
            this.updateAGR();
    }//GEN-LAST:event_jButtonSaveAGRActionPerformed

    private void jButtonDeleteAGRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteAGRActionPerformed
        deleteAGR();
    }//GEN-LAST:event_jButtonDeleteAGRActionPerformed

    private void jButtonAddAGRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddAGRActionPerformed
        addAGR();
    }//GEN-LAST:event_jButtonAddAGRActionPerformed

   
        private void setLanguage()
    {  
        /*jLabel*/        
        jLabel121.setText(GutilPCU.getTextBundle(jLabel121.getText()));
        jLabel122.setText(GutilPCU.getTextBundle(jLabel122.getText()));
        jLabel130.setText(GutilPCU.getTextBundle(jLabel130.getText()));
        jLabel132.setText(GutilPCU.getTextBundle(jLabel132.getText()));
        jLabel134.setText(GutilPCU.getTextBundle(jLabel134.getText()));
        jLabel135.setText(GutilPCU.getTextBundle(jLabel135.getText()));
        jLabel136.setText(GutilPCU.getTextBundle(jLabel136.getText()));
        jLabel137.setText(GutilPCU.getTextBundle(jLabel137.getText()));
        jLabel141.setText(GutilPCU.getTextBundle(jLabel141.getText()));
        jLabel142.setText(GutilPCU.getTextBundle(jLabel142.getText()));
        jLabel143.setText(GutilPCU.getTextBundle(jLabel143.getText()));
        jLabel144.setText(GutilPCU.getTextBundle(jLabel144.getText()));
        jLabel145.setText(GutilPCU.getTextBundle(jLabel145.getText()));
        jLabel146.setText(GutilPCU.getTextBundle(jLabel146.getText()));
        jLabel147.setText(GutilPCU.getTextBundle(jLabel147.getText()));
        jLabel149.setText(GutilPCU.getTextBundle(jLabel149.getText()));
        jLabel150.setText(GutilPCU.getTextBundle(jLabel150.getText()));
        jLabel152.setText(GutilPCU.getTextBundle(jLabel152.getText()));
        jLabel153.setText(GutilPCU.getTextBundle(jLabel153.getText()));
        jLabel154.setText(GutilPCU.getTextBundle(jLabel154.getText()));
        jLabel155.setText(GutilPCU.getTextBundle(jLabel155.getText()));
        //add by neung
        jButtonAddAGR.setText(GutilPCU.getTextBundle(jButtonAddAGR.getText())); 
        jButtonDeleteAGR.setText(GutilPCU.getTextBundle(jButtonDeleteAGR.getText())); 
        jButtonSaveAGR.setText(GutilPCU.getTextBundle(jButtonSaveAGR.getText()));  
        jButtonSearchAGR.setText(GutilPCU.getTextBundle(jButtonSearchAGR.getText())); 
        
        /*TitledBorder*/        
        //GutilPCU.JPanelLabler(jPanelSchoolDetail);
        GutilPCU.JPanelLabler(jPanelSearchAGR);
        
        
        
        /*CheckBox*/
        jCheckBoxCloseAGR.setText(GutilPCU.getTextBundle(jCheckBoxCloseAGR.getText()));
        
        /*Radio*/
        jRadioButtonActivityGroupNoRegister.setText(GutilPCU.getTextBundle(jRadioButtonActivityGroupNoRegister.getText()));
        jRadioButtonActivityGroupRegister.setText(GutilPCU.getTextBundle(jRadioButtonActivityGroupRegister.getText()));
        jCheckBoxSaveHistory.setText(GutilPCU.getTextBundle(jCheckBoxSaveHistory.getText()));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.pcu.utility.DateComboBox dateComboBoxCloseAGR;
    private com.pcu.utility.DateComboBox dateComboBoxDateOfPassAGR;
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private com.pcu.utility.IntegerTextField integerTextFieldCapitalAGR;
    private com.pcu.utility.IntegerTextField integerTextFieldManMemberAGR;
    private com.pcu.utility.IntegerTextField integerTextFieldMooAGR;
    private com.pcu.utility.IntegerTextField integerTextFieldStartYearAGRGroup;
    private com.pcu.utility.IntegerTextField integerTextFieldTimeOfPassAGR;
    private com.pcu.utility.IntegerTextField integerTextFieldWomanMemberAGR;
    private javax.swing.JButton jButtonAddAGR;
    private javax.swing.JButton jButtonDeleteAGR;
    private javax.swing.JButton jButtonSaveAGR;
    private javax.swing.JButton jButtonSearchAGR;
    private javax.swing.JCheckBox jCheckBoxCloseAGR;
    private javax.swing.JCheckBox jCheckBoxSaveHistory;
    private javax.swing.JCheckBox jCheckBoxVillage;
    private javax.swing.JComboBox jComboBoxAGRCode;
    private javax.swing.JComboBox jComboBoxAGRType;
    private javax.swing.JComboBox jComboBoxSearchVillageAGR;
    private javax.swing.JComboBox jComboBoxStandardAGR;
    private javax.swing.JComboBox jComboBoxStandardTypeAGR;
    private javax.swing.JComboBox jComboBoxVillageAGR;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel75;
    private javax.swing.JPanel jPanelData1;
    private javax.swing.JPanel jPanelData2;
    private javax.swing.JPanel jPanelSearchAGR;
    private javax.swing.JRadioButton jRadioButtonActivityGroupNoRegister;
    private javax.swing.JRadioButton jRadioButtonActivityGroupRegister;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JTable jTableAGR;
    private javax.swing.JTable jTableHistoryAGR;
    private javax.swing.JTextArea jTextAreaCauseCloseAGR;
    private com.pcu.utility.IntegerTextField jTextFieldAGRCode;
    private javax.swing.JTextField jTextFieldAGRName;
    private javax.swing.JTextField jTextFieldChairmanNameAGR;
    private javax.swing.JTextField jTextFieldHomeNumberAGR;
    private javax.swing.JTextField jTextFieldPeriodOfTimeAGR;
    private javax.swing.JTextField jTextFieldPhoneAGR;
    private javax.swing.JTextField jTextFieldRoadAGR;
    private javax.swing.JTextField jTextFieldSearchAGR;
    private javax.swing.JTextField jTextFieldStandardTypeAGRDescription;
    private com.hosv3.gui.component.PanelCatAddress panelCatAddress1;
    // End of variables declaration//GEN-END:variables


    protected JLabel jPaneData2;
    
}
