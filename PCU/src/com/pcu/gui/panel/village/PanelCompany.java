/*
 * PanelCompany.java
 *
 * Created on 11 เมษายน 2550, 13:57 น.
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
import com.pcu.object.Company;
import com.pcu.object.CompanyHistory;
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
public class PanelCompany extends javax.swing.JPanel implements VillageObserv{
    
    protected Company theCompany;
    protected CompanyHistory theCompanyHistory;
    protected Vector vCompany = new Vector();
    protected Vector vCompanyHistory = new Vector();
    protected String standardTypeCompany = "";
    protected int rowCompany;
    protected boolean flagCompany = true;

    protected AllComboBoxControl theAllComboBoxControl;

    protected SystemControl theSystemControl;

    protected String employeeId;

    protected VillageControl theVillageControl;

    protected ComboFix cfv;

    protected HosManage theHM;

    private UpdateStatus theUS;
    /** Creates new form PanelCompany */
    public PanelCompany() {
        initComponents();
        setLanguage();
    }
    /**
     *  implements VillageObserv
     */
    public boolean refreshVillage() {
        ComboboxModel.initComboBox(this.jComboBoxVillageCompany,theAllComboBoxControl.listVillage());
        ComboboxModel.initComboBox(this.jComboBoxSearchVillageCompany,theAllComboBoxControl.listVillage());  
        return true;
    }
    public void setControl(HosManage hm,UpdateStatus us) 
    {
        theHM = hm;
//        theUS = hm.theUS;
        theUS = us;
        theSystemControl = hm.theHosControl.theSystemControl;
        theAllComboBoxControl = hm.theHosControl.theAllComboBoxControl;
        theVillageControl = hm.theHosControl.theVillageControl;

        Vector vComboBoxStandardType = theAllComboBoxControl.listCommunityStandardType();
        Vector vComboBoxStandard = theAllComboBoxControl.listCommunityStandard();
        this.panelCatAddress1.setControl(hm.theHosInf.getLookupControl(),null);
        ComboboxModel.initComboBox(jComboBoxCompanyType,theAllComboBoxControl.listCompanyType());
        ComboboxModel.initComboBox(jComboBoxStandardTypeCompany,vComboBoxStandardType);
        ComboboxModel.initComboBox(jComboBoxStandardCompany,vComboBoxStandard);     
        ComboboxModel.initComboBox(this.jComboBoxVillageCompany,theAllComboBoxControl.listVillage());
        ComboboxModel.initComboBox(this.jComboBoxSearchVillageCompany,theAllComboBoxControl.listVillage());
        
        /***การมีที่ตั้งอยู่ในตลาด**/
        ComboFix cfmk = new ComboFix();
        cfmk.code = "0";
        cfmk.name = GutilPCU.getTextBundle("Not_in_market");
        Vector vComboboxMarket = theAllComboBoxControl.listMarket();
        vComboboxMarket.add(cfmk);
        ComboboxModel.initComboBox(jComboBoxInMarketCompany,vComboboxMarket);
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
        SystemControl a = theSystemControl;
        Site theSite = theSystemControl.theHospital_Site;
        if(theSite != null)
        {
            this.panelCatAddress1.setAddress(theSite.changwat,theSite.amphor,theSite.tambon);
        }
    }
    
    /*****Company*****/
    protected void clearGuiCompany()
    {
        setDefaultHospital();
        jTextFieldCompanyCode.setText("");
        jTextFieldCompanyName.setText("");
        jTextFieldCompanyOwner.setText("");
        jTextFieldHomeNumberCompany.setText("");
        integerTextFieldMooCompany.setText("");
        jTextFieldRoadCompany.setText("");
        jTextFieldPhoneCompany.setText("");        
        integerTextFieldAmountMan.setText("");
        integerTextFieldAmountWoman.setText("");
        integerTextFieldTimeOfPassCompany.setText("");
        jTextFieldPeriodOfTimeCompany.setText("");
        jCheckBoxCloseCompany.setSelected(false);
        dateComboBoxCompanyCoDate.initCurrentDate(); 
        dateComboBoxDateOfPassCompany.initCurrentDate();
        dateComboBoxCloseCompany.initCurrentDate();
        jRadioButtonCompanyRegister.setSelected(true);
        Gutil.setGuiData(jComboBoxVillageCompany,Gutil.getGuiData(jComboBoxSearchVillageCompany));
    }
    
    protected void setEnableGuiCompany(boolean flag)
    {
        jTextFieldCompanyCode.setEnabled(flag);
        jTextFieldCompanyName.setEnabled(flag);
        jRadioButtonCompanyNoRegister.setEnabled(flag);
        jRadioButtonCompanyRegister.setEnabled(flag);
        jComboBoxCompanyType.setEnabled(flag);
        jTextFieldCompanyOwner.setEnabled(flag);
        jTextFieldHomeNumberCompany.setEnabled(flag);
        integerTextFieldMooCompany.setEnabled(flag);
        jTextFieldRoadCompany.setEnabled(flag);
        jTextFieldPhoneCompany.setEnabled(flag);
        
        this.panelCatAddress1.setEnabled(flag);     
        integerTextFieldAmountMan.setEnabled(flag);
        integerTextFieldAmountWoman.setEnabled(flag);
        jRadioButtonCompanyCo.setEnabled(flag);
        jRadioButtonCompanyNotCo.setEnabled(flag);
        jComboBoxStandardTypeCompany.setEnabled(flag);
        jComboBoxStandardCompany.setEnabled(flag);
        integerTextFieldTimeOfPassCompany.setEnabled(flag);
        dateComboBoxDateOfPassCompany.setEnabled(flag);
        jTextFieldPeriodOfTimeCompany.setEnabled(flag);
        jComboBoxInMarketCompany.setEnabled(flag);
        jCheckBoxCloseCompany.setEnabled(flag);
        jComboBoxVillageCompany.setEnabled(flag);
        jButtonDeleteCompany.setEnabled(flag);
        jButtonSaveCompany.setEnabled(flag);
    }
    
    protected void addCompany()
    {
        clearGuiCompany();
        theCompany = new Company();
        theCompanyHistory = new CompanyHistory();
        standardTypeCompany = "";
        jTextAreaCauseCloseCompany.setText("");
        setEnableGuiCompany(true);
        /*ยกเว้นปุ่มลบและปุ่มบวก*/        
        jButtonDeleteCompany.setEnabled(false);
        /*และวันที่เลิกกิจกรรม*/
        jTextFieldStandardTypeCompanyDescription.setVisible(false);
        //jPanel42.setVisible(false);
        jRadioButtonCompanyCo.setSelected(true);
        jTableCompany.clearSelection();
        vCompanyHistory = null;
        setTableCompanyHistory();
    }
    
    protected void saveCompany()
    {           
        theCompany.company_number = jTextFieldCompanyCode.getText();
        theCompany.company_name = jTextFieldCompanyName.getText();
            if(jCheckBoxCloseCompany.isSelected())
            {
                theCompany.company_close = "1";                
                theCompany.company_close_date_time = dateComboBoxCloseCompany.getText();
                theCompany.company_close_note = jTextAreaCauseCloseCompany.getText();
            }
            else
            {
                theCompany.company_close = "0";
                theCompany.company_close_date_time = "";
                theCompany.company_close_note = "";
            }
            theCompany.company_active = "1";
            theCompany.village_id = Gutil.getGuiData(jComboBoxVillageCompany);

            /***CompanyHistory***/
            if(jRadioButtonCompanyRegister.isSelected())
                theCompanyHistory.register_id = PcuAnswer.One();
            else
                theCompanyHistory.register_id = PcuAnswer.Zero();
         
            theCompanyHistory.company_type_id = Gutil.getGuiData(jComboBoxCompanyType);
            theCompanyHistory.owner_name = jTextFieldCompanyOwner.getText();
            theCompanyHistory.home_number = jTextFieldHomeNumberCompany.getText();
            theCompanyHistory.moo = integerTextFieldMooCompany.getText();
            theCompanyHistory.road = jTextFieldRoadCompany.getText();
            theCompanyHistory.phone = jTextFieldPhoneCompany.getText();            
            theCompanyHistory.changwat = this.panelCatAddress1.getChangwat();
            theCompanyHistory.amphur = panelCatAddress1.getAmpur();
            theCompanyHistory.tambol = panelCatAddress1.getTambon();         
            theCompanyHistory.amount_man = integerTextFieldAmountMan.getText(); 
            theCompanyHistory.amount_woman = integerTextFieldAmountWoman.getText(); 
            if(jRadioButtonCompanyNotCo.isSelected())
                theCompanyHistory.company_co = PcuAnswer.Zero();
            else
                theCompanyHistory.company_co = PcuAnswer.One();

            if(theCompanyHistory.company_co.equals("0"))
                theCompanyHistory.company_co_date_time = "";
            else
                theCompanyHistory.company_co_date_time = dateComboBoxCompanyCoDate.getText();
            
            theCompanyHistory.standard_type_id = Gutil.getGuiData(jComboBoxStandardTypeCompany);
            theCompanyHistory.standard_type_description = jTextFieldStandardTypeCompanyDescription.getText();
            theCompanyHistory.standard_id = Gutil.getGuiData(jComboBoxStandardCompany);
            theCompanyHistory.time_of_pass = integerTextFieldTimeOfPassCompany.getText();
            theCompanyHistory.date_of_pass = dateComboBoxDateOfPassCompany.getText();
            theCompanyHistory.period_of_pass = jTextFieldPeriodOfTimeCompany.getText(); 
            theCompanyHistory.in_market = Gutil.getGuiData(jComboBoxInMarketCompany);            
            boolean ret = theVillageControl.saveCompanyAndHistory(theCompany,theCompanyHistory);
            if(!ret)
                return;
            
            int row = this.jTableCompany.getSelectedRow();
            this.searchCompany();
            if(row==-1)
                row = jTableCompany.getRowCount()-1;
            jTableCompany.setRowSelectionInterval(row,row);
            this.jTableCompanyMouseReleased(null);
    }
    
    protected void updateCompany()
    {
        /***Company***/
        theCompany.company_number = jTextFieldCompanyCode.getText();
        theCompany.company_name = jTextFieldCompanyName.getText();
        if(jCheckBoxCloseCompany.isSelected())
        {
            theCompany.company_close = "1";
            theCompany.company_close_date_time = dateComboBoxCloseCompany.getText();
            theCompany.company_close_note = jTextAreaCauseCloseCompany.getText();
        }
        else
        {
            theCompany.company_close = "0";
            theCompany.company_close_date_time = "";
            theCompany.company_close_note = "";
        }
        theCompany.village_id = Gutil.getGuiData(jComboBoxVillageCompany);

        /***CompanyHistory***/
        if(jRadioButtonCompanyRegister.isSelected())
            theCompanyHistory.register_id = PcuAnswer.One();
        else
            theCompanyHistory.register_id = PcuAnswer.Zero();

        theCompanyHistory.company_type_id = Gutil.getGuiData(jComboBoxCompanyType);
        theCompanyHistory.owner_name = jTextFieldCompanyOwner.getText();
        theCompanyHistory.home_number = jTextFieldHomeNumberCompany.getText();
        theCompanyHistory.moo = integerTextFieldMooCompany.getText();
        theCompanyHistory.road = jTextFieldRoadCompany.getText();
        theCompanyHistory.phone = jTextFieldPhoneCompany.getText();            
        theCompanyHistory.changwat = panelCatAddress1.getChangwat();
        theCompanyHistory.amphur = panelCatAddress1.getAmpur();
        theCompanyHistory.tambol = panelCatAddress1.getTambon();       
        theCompanyHistory.amount_man = integerTextFieldAmountMan.getText(); 
        theCompanyHistory.amount_woman = integerTextFieldAmountWoman.getText();
        if(jRadioButtonCompanyNotCo.isSelected())
            theCompanyHistory.company_co = PcuAnswer.Zero();
        else
            theCompanyHistory.company_co = PcuAnswer.One();

        if(theCompanyHistory.company_co.equals("0"))
            theCompanyHistory.company_co_date_time = "";
        else
            theCompanyHistory.company_co_date_time = dateComboBoxCompanyCoDate.getText();
        theCompanyHistory.standard_type_id = Gutil.getGuiData(jComboBoxStandardTypeCompany);
        theCompanyHistory.standard_type_description = jTextFieldStandardTypeCompanyDescription.getText();
        theCompanyHistory.standard_id = Gutil.getGuiData(jComboBoxStandardCompany);
        theCompanyHistory.time_of_pass = integerTextFieldTimeOfPassCompany.getText();
        theCompanyHistory.date_of_pass = dateComboBoxDateOfPassCompany.getText();
        theCompanyHistory.period_of_pass = jTextFieldPeriodOfTimeCompany.getText(); 
        theCompanyHistory.in_market = Gutil.getGuiData(jComboBoxInMarketCompany);    

        theVillageControl.updateCompanyAndHistory(theCompany,theCompanyHistory);

        int row = this.jTableCompany.getSelectedRow();
        this.searchCompany();
        if(row==-1)
            row = jTableCompany.getRowCount()-1;
        jTableCompany.setRowSelectionInterval(row,row);
        this.jTableCompanyMouseReleased(null);
    }
    
    protected void searchCompany()
    {   
        search("");
    }
    protected void search(String village_id)
    {   
        if(village_id!=null && !village_id.equals(""))
        {
            Gutil.setGuiData(jComboBoxSearchVillageCompany,village_id);
            Gutil.setGuiData(jComboBoxVillageCompany,village_id);
        }
        /***ค้นหาสถานประกอบการ***/
        String search = jTextFieldSearchCompany.getText();      
        String village = "0";
        if(jCheckBoxSearchMoo.isSelected())
            village = Gutil.getGuiData(jComboBoxSearchVillageCompany);
        vCompany =  theVillageControl.listCompanyByNameOrNumber(search,village);
        setTableCompany();
        vCompanyHistory =  null;
        setTableCompanyHistory();
        setEnableGuiCompany(false);
        dateComboBoxCompanyCoDate.setEnabled(false);
    }
    
    protected void setTableCompany()
    {
        String[] col = {GutilPCU.getTextBundle("Code"),
                    GutilPCU.getTextBundle("CompanyName"),
                    GutilPCU.getTextBundle("VillageName")};
                    
        Company companyTemp = new Company();
        TaBleModel tm ;
        
        if(vCompany != null)
        {   
            tm = new TaBleModel(col,vCompany.size());
            for(int i=0, size=vCompany.size(); i<size; i++)
            {  
                companyTemp = (Company)vCompany.get(i);
                tm.setValueAt(companyTemp.company_number,i,0); 
                tm.setValueAt(companyTemp.company_name,i,1);
                tm.setValueAt(ComboboxModel.getDescriptionComboBox(jComboBoxVillageCompany,companyTemp.village_id),i,2); 
            }
        }
        else
        {   
            tm= new TaBleModel(col,0);
        }         
        companyTemp = null;
        jTableCompany.setModel(tm);  
        
        /***SetTableDefault***/
        jTableCompany.getColumnModel().getColumn(0).setPreferredWidth(150);     /*รหัสสถานประกอบการ*/
        jTableCompany.getColumnModel().getColumn(0).setCellRenderer(ColumnTableRenderer.getRendererCenter());
        jTableCompany.getColumnModel().getColumn(1).setPreferredWidth(230);     /*ชื่อสถานประกอบการ*/
        jTableCompany.getColumnModel().getColumn(2).setPreferredWidth(200);     /*ชื่อหมู่บ้าน*/
    }
    
    protected void selectCompany(int row)
    {
        if(row==-2)
        {/*user เป็นผู้เลือกเอง*/
            rowCompany = jTableCompany.getSelectedRow();           
        }
        else
        {/*user บันทึก จะ select record ที่เพิ่งเพิ่ม*/
            rowCompany = row;            
        }
        
        theCompany = (Company)vCompany.get(rowCompany);
        
        setEnableGuiCompany(true);
        jTextFieldCompanyCode.setText(theCompany.company_number);
        jTextFieldCompanyName.setText(theCompany.company_name);        
        if(theCompany.company_close.equals("1"))
        {
            jCheckBoxCloseCompany.setSelected(true);
            jCheckBoxCloseCompanyActionPerformed(null);
            //jPanel42.setVisible(true);            
            dateComboBoxCloseCompany.setText(Gutil.convertFieldDate(theCompany.company_close_date_time));
            jTextAreaCauseCloseCompany.setText(theCompany.company_close_note);
            dateComboBoxCloseCompany.setEnabled(true);
            jTextAreaCauseCloseCompany.setEnabled(true);
        }
        else
        {
            jCheckBoxCloseCompany.setSelected(false);
            jCheckBoxCloseCompanyActionPerformed(null);
            //jPanel42.setVisible(false);                  
        }        
        ComboboxModel.setCodeComboBox(jComboBoxVillageCompany,theCompany.village_id);
        
        searchCompanyHistory();
        if(theCompanyHistory.register_id.equals(PcuAnswer.Zero()))
            jRadioButtonCompanyNoRegister.setSelected(true);
        else
            jRadioButtonCompanyRegister.setSelected(true);

        ComboboxModel.setCodeComboBox(jComboBoxCompanyType,theCompanyHistory.company_type_id); 
        jTextFieldCompanyOwner.setText(theCompanyHistory.owner_name);
        jTextFieldHomeNumberCompany.setText(theCompanyHistory.home_number);
        integerTextFieldMooCompany.setText(theCompanyHistory.moo);
        jTextFieldRoadCompany.setText(theCompanyHistory.road);
        jTextFieldPhoneCompany.setText(theCompanyHistory.phone);
        this.panelCatAddress1.setAddress(theCompanyHistory.changwat
                ,theCompanyHistory.amphur,theCompanyHistory.tambol);
        integerTextFieldAmountMan.setText(theCompanyHistory.amount_man);
        integerTextFieldAmountWoman.setText(theCompanyHistory.amount_woman);
        if(theCompanyHistory.company_co.equals(PcuAnswer.Zero()))
            jRadioButtonCompanyNotCo.setSelected(true);
        else
            jRadioButtonCompanyCo.setSelected(true);
        
        if(theCompanyHistory.company_co.equals("0")){
            dateComboBoxCompanyCoDate.setEnabled(false);
            dateComboBoxCompanyCoDate.setText("");
        }
        else{
            dateComboBoxCompanyCoDate.setEnabled(true);
            dateComboBoxCompanyCoDate.setText(Gutil.convertFieldDate(theCompanyHistory.company_co_date_time));
        }
        ComboboxModel.setCodeComboBox(jComboBoxStandardTypeCompany,theCompanyHistory.standard_type_id);
        if(theCompanyHistory.standard_type_id.equals("90")){
            jTextFieldStandardTypeCompanyDescription.setVisible(true);
            jTextFieldStandardTypeCompanyDescription.setText(theCompanyHistory.standard_type_description);
        }
        else
            jTextFieldStandardTypeCompanyDescription.setVisible(false);
        ComboboxModel.setCodeComboBox(jComboBoxStandardCompany,theCompanyHistory.standard_id);
        jComboBoxStandardCompanyActionPerformed(null);
        integerTextFieldTimeOfPassCompany.setText(theCompanyHistory.time_of_pass);
        dateComboBoxDateOfPassCompany.setText(Gutil.convertFieldDate(theCompanyHistory.date_of_pass));
        jTextFieldPeriodOfTimeCompany.setText(theCompanyHistory.period_of_pass);
        ComboboxModel.setCodeComboBox(jComboBoxInMarketCompany,theCompanyHistory.in_market);
    }
    
    protected void selectCompanyHistory()
    {
        CompanyHistory companyHistoryTemp = (CompanyHistory)vCompanyHistory.get(jTableHistoryCompany.getSelectedRow()); 
        if(theCompanyHistory.register_id.equals(PcuAnswer.Zero()))
        {
            jRadioButtonCompanyNoRegister.setSelected(true);
        }
        else
        {
            jRadioButtonCompanyRegister.setSelected(true);
        }
        ComboboxModel.setCodeComboBox(jComboBoxCompanyType,companyHistoryTemp.company_type_id); 
        jTextFieldCompanyOwner.setText(companyHistoryTemp.owner_name);
        jTextFieldHomeNumberCompany.setText(companyHistoryTemp.home_number);
        integerTextFieldMooCompany.setText(companyHistoryTemp.moo);
        jTextFieldRoadCompany.setText(companyHistoryTemp.road);
        jTextFieldPhoneCompany.setText(companyHistoryTemp.phone);
        this.panelCatAddress1.setAddress(companyHistoryTemp.changwat
                ,companyHistoryTemp.amphur,companyHistoryTemp.tambol);
        integerTextFieldAmountMan.setText(companyHistoryTemp.amount_man);
        integerTextFieldAmountWoman.setText(companyHistoryTemp.amount_woman);
        if(companyHistoryTemp.company_co.equals(PcuAnswer.Zero()))
        {
            jRadioButtonCompanyNotCo.setSelected(true);
        }
        else
        {
            jRadioButtonCompanyCo.setSelected(true);
        }
        if(companyHistoryTemp.company_co.equals("0"))
        {
            dateComboBoxCompanyCoDate.setText("");
        }
        else
        {
            dateComboBoxCompanyCoDate.setText(Gutil.convertFieldDate(companyHistoryTemp.company_co_date_time));
        }
        ComboboxModel.setCodeComboBox(jComboBoxStandardTypeCompany,companyHistoryTemp.standard_type_id);
        jTextFieldStandardTypeCompanyDescription.setText(companyHistoryTemp.standard_type_description);
        ComboboxModel.setCodeComboBox(jComboBoxStandardCompany,companyHistoryTemp.standard_id);
        jComboBoxStandardCompanyActionPerformed(null);
        integerTextFieldTimeOfPassCompany.setText(companyHistoryTemp.time_of_pass);
        dateComboBoxDateOfPassCompany.setText(Gutil.convertFieldDate(companyHistoryTemp.date_of_pass));
        jTextFieldPeriodOfTimeCompany.setText(companyHistoryTemp.period_of_pass);
        ComboboxModel.setCodeComboBox(jComboBoxInMarketCompany,companyHistoryTemp.in_market);
        setEnableGuiCompany(false);
        dateComboBoxCloseCompany.setEnabled(false);
        jTextAreaCauseCloseCompany.setEnabled(false);
        dateComboBoxCompanyCoDate.setEnabled(false);        
        jTextFieldStandardTypeCompanyDescription.setEnabled(false);    
    }
    
    
    protected void searchCompanyHistory()
    {
        /***ค้นหาประวัติสถานประกอบการ***/
        vCompanyHistory =  theVillageControl.listCompanyHistoryByCompanyId(theCompany.getObjectId());
        setTableCompanyHistory();
    }
    
    protected void setTableCompanyHistory()
    { 
        String[] col = {GutilPCU.getTextBundle("DateRecord")};
                    
        CompanyHistory companyHistoryTemp = new CompanyHistory();
        TaBleModel tm ;
        
        if(vCompanyHistory != null)
        {   
            int size = vCompanyHistory.size()-1;    
            tm = new TaBleModel(col,size);
            for(int i=0; i<size; i++)
            {  
                companyHistoryTemp = (CompanyHistory)vCompanyHistory.get(i);
                if(i==0)
                    theCompanyHistory = companyHistoryTemp;
                if(!("").equals(companyHistoryTemp.company_history_record_date_time))
                    tm.setValueAt(GutilPCU.changDateToString(companyHistoryTemp.company_history_record_date_time,true),i,0); 
                else
                    tm.setValueAt(GutilPCU.changDateToString(theCompany.company_modify_date_time,true),i,0); 
            }
        }
        else
        {   
            tm= new TaBleModel(col,0);
        } 
        jTableHistoryCompany.setModel(tm);  
        
    }
    
    protected void deleteCompany()
    {
        boolean ret = theVillageControl.deleteCompany(theCompany);
        if(!ret)
            return;
        searchCompany();
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel73 = new javax.swing.JPanel();
        jPanelSearchCompany = new javax.swing.JPanel();
        jTextFieldSearchCompany = new javax.swing.JTextField();
        jButtonSearchCompany = new javax.swing.JButton();
        jComboBoxSearchVillageCompany = new javax.swing.JComboBox();
        jCheckBoxSearchMoo = new javax.swing.JCheckBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableCompany = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTableHistoryCompany = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jButtonAddCompany = new javax.swing.JButton();
        jButtonDeleteCompany = new javax.swing.JButton();
        jButtonSaveCompany = new javax.swing.JButton();
        jCheckBoxSaveHistory = new javax.swing.JCheckBox();
        jPanelData1 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldCompanyCode = new com.pcu.utility.IntegerTextField();
        jPanel55 = new javax.swing.JPanel();
        jRadioButtonCompanyNoRegister = new javax.swing.JRadioButton();
        jRadioButtonCompanyRegister = new javax.swing.JRadioButton();
        jLabel23 = new javax.swing.JLabel();
        jTextFieldCompanyOwner = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        integerTextFieldAmountMan = new com.pcu.utility.IntegerTextField();
        jPanel20 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jTextFieldRoadCompany = new javax.swing.JTextField();
        panelCatAddress1 = new com.hosv3.gui.component.PanelCatAddress();
        jLabel34 = new javax.swing.JLabel();
        jComboBoxVillageCompany = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jTextFieldHomeNumberCompany = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        integerTextFieldMooCompany = new com.pcu.utility.IntegerTextField();
        jLabel46 = new javax.swing.JLabel();
        jTextFieldPhoneCompany = new javax.swing.JTextField();
        integerTextFieldAmountWoman = new com.pcu.utility.IntegerTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextFieldCompanyName = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jComboBoxCompanyType = new javax.swing.JComboBox();
        jPanelData2 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jComboBoxStandardCompany = new javax.swing.JComboBox();
        jPanel37 = new javax.swing.JPanel();
        jTextFieldStandardTypeCompanyDescription = new javax.swing.JTextField();
        jComboBoxStandardTypeCompany = new javax.swing.JComboBox();
        jLabel95 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        jTextFieldPeriodOfTimeCompany = new javax.swing.JTextField();
        jLabel102 = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        dateComboBoxCloseCompany = new com.pcu.utility.DateComboBox();
        jCheckBoxCloseCompany = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaCauseCloseCompany = new javax.swing.JTextArea();
        jPanel76 = new javax.swing.JPanel();
        jRadioButtonCompanyNotCo = new javax.swing.JRadioButton();
        jRadioButtonCompanyCo = new javax.swing.JRadioButton();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        dateComboBoxCompanyCoDate = new com.pcu.utility.DateComboBox();
        jPanel1 = new javax.swing.JPanel();
        integerTextFieldTimeOfPassCompany = new com.pcu.utility.IntegerTextField();
        dateComboBoxDateOfPassCompany = new com.pcu.utility.DateComboBox();
        jLabel103 = new javax.swing.JLabel();
        jComboBoxInMarketCompany = new javax.swing.JComboBox();
        jLabel101 = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        jPanel73.setLayout(new java.awt.GridBagLayout());

        jPanelSearchCompany.setLayout(new java.awt.GridBagLayout());

        jPanelSearchCompany.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SearchData", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, defaultFont1));
        jTextFieldSearchCompany.setFont(defaultFont1);
        jTextFieldSearchCompany.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldSearchCompany.setPreferredSize(new java.awt.Dimension(100, 21));
        jTextFieldSearchCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchCompanyActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanelSearchCompany.add(jTextFieldSearchCompany, gridBagConstraints);

        jButtonSearchCompany.setFont(defaultFont1);
        jButtonSearchCompany.setText("Search");
        jButtonSearchCompany.setMargin(new java.awt.Insets(2, 8, 2, 8));
        jButtonSearchCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchCompanyActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelSearchCompany.add(jButtonSearchCompany, gridBagConstraints);

        jComboBoxSearchVillageCompany.setFont(defaultFont1);
        jComboBoxSearchVillageCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSearchVillageCompanyActionPerformed(evt);
            }
        });
        jComboBoxSearchVillageCompany.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBoxSearchVillageCompanyFocusGained(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanelSearchCompany.add(jComboBoxSearchVillageCompany, gridBagConstraints);

        jCheckBoxSearchMoo.setSelected(true);
        jCheckBoxSearchMoo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBoxSearchMoo.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBoxSearchMoo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSearchMooActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelSearchCompany.add(jCheckBoxSearchMoo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel73.add(jPanelSearchCompany, gridBagConstraints);

        jScrollPane4.setBorder(null);
        jScrollPane4.setFont(defaultFont1);
        jTableCompany.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableCompany.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableCompanyMouseReleased(evt);
            }
        });

        jScrollPane4.setViewportView(jTableCompany);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel73.add(jScrollPane4, gridBagConstraints);

        jScrollPane12.setFont(defaultFont1);
        jTableHistoryCompany.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableHistoryCompany.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableHistoryCompanyMouseReleased(evt);
            }
        });

        jScrollPane12.setViewportView(jTableHistoryCompany);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel73.add(jScrollPane12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        add(jPanel73, gridBagConstraints);

        jPanel13.setLayout(new java.awt.GridBagLayout());

        jButtonAddCompany.setFont(defaultFont1);
        jButtonAddCompany.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif")));
        jButtonAddCompany.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonAddCompany.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonAddCompany.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonAddCompany.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonAddCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddCompanyActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel13.add(jButtonAddCompany, gridBagConstraints);

        jButtonDeleteCompany.setFont(defaultFont1);
        jButtonDeleteCompany.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif")));
        jButtonDeleteCompany.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonDeleteCompany.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonDeleteCompany.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonDeleteCompany.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonDeleteCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteCompanyActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel13.add(jButtonDeleteCompany, gridBagConstraints);

        jButtonSaveCompany.setFont(defaultFont1);
        jButtonSaveCompany.setText("Save");
        jButtonSaveCompany.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonSaveCompany.setMaximumSize(new java.awt.Dimension(67, 24));
        jButtonSaveCompany.setMinimumSize(new java.awt.Dimension(67, 24));
        jButtonSaveCompany.setPreferredSize(new java.awt.Dimension(67, 24));
        jButtonSaveCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveCompanyActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel13.add(jButtonSaveCompany, gridBagConstraints);

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
        jPanel13.add(jCheckBoxSaveHistory, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jPanel13, gridBagConstraints);

        jPanelData1.setLayout(new java.awt.GridBagLayout());

        jPanelData1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jLabel20.setFont(defaultFont1);
        jLabel20.setText("Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 0, 0);
        jPanelData1.add(jLabel20, gridBagConstraints);

        jTextFieldCompanyCode.setColumns(3);
        jTextFieldCompanyCode.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldCompanyCode.setFont(defaultFont1);
        jTextFieldCompanyCode.setMinimumSize(new java.awt.Dimension(37, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 11);
        jPanelData1.add(jTextFieldCompanyCode, gridBagConstraints);

        jPanel55.setLayout(new java.awt.GridBagLayout());

        buttonGroup1.add(jRadioButtonCompanyNoRegister);
        jRadioButtonCompanyNoRegister.setFont(defaultFont1);
        jRadioButtonCompanyNoRegister.setText("NoRegister");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel55.add(jRadioButtonCompanyNoRegister, gridBagConstraints);

        buttonGroup1.add(jRadioButtonCompanyRegister);
        jRadioButtonCompanyRegister.setFont(defaultFont1);
        jRadioButtonCompanyRegister.setSelected(true);
        jRadioButtonCompanyRegister.setText("RegisterBy");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel55.add(jRadioButtonCompanyRegister, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 11, 0, 11);
        jPanelData1.add(jPanel55, gridBagConstraints);

        jLabel23.setFont(defaultFont1);
        jLabel23.setText("OwnerName");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 11, 0, 0);
        jPanelData1.add(jLabel23, gridBagConstraints);

        jTextFieldCompanyOwner.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 5, 0, 11);
        jPanelData1.add(jTextFieldCompanyOwner, gridBagConstraints);

        jLabel25.setFont(defaultFont1);
        jLabel25.setText("AmountMan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 11, 0, 0);
        jPanelData1.add(jLabel25, gridBagConstraints);

        integerTextFieldAmountMan.setColumns(3);
        integerTextFieldAmountMan.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldAmountMan.setFont(defaultFont1);
        integerTextFieldAmountMan.setMinimumSize(new java.awt.Dimension(30, 20));
        integerTextFieldAmountMan.setPreferredSize(new java.awt.Dimension(30, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 5, 0, 0);
        jPanelData1.add(integerTextFieldAmountMan, gridBagConstraints);

        jPanel20.setLayout(new java.awt.GridBagLayout());

        jLabel45.setFont(defaultFont1);
        jLabel45.setText("Road");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel20.add(jLabel45, gridBagConstraints);

        jTextFieldRoadCompany.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel20.add(jTextFieldRoadCompany, gridBagConstraints);

        panelCatAddress1.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel20.add(panelCatAddress1, gridBagConstraints);

        jLabel34.setFont(defaultFont1);
        jLabel34.setText("VillageName");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 1, 0, 0);
        jPanel20.add(jLabel34, gridBagConstraints);

        jComboBoxVillageCompany.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 11);
        jPanel20.add(jComboBoxVillageCompany, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel43.setFont(defaultFont1);
        jLabel43.setText("HomeNumber");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jLabel43, gridBagConstraints);

        jTextFieldHomeNumberCompany.setFont(defaultFont1);
        jTextFieldHomeNumberCompany.setMinimumSize(new java.awt.Dimension(40, 21));
        jTextFieldHomeNumberCompany.setPreferredSize(new java.awt.Dimension(40, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel2.add(jTextFieldHomeNumberCompany, gridBagConstraints);

        jLabel44.setFont(defaultFont1);
        jLabel44.setText("Moo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel2.add(jLabel44, gridBagConstraints);

        integerTextFieldMooCompany.setColumns(3);
        integerTextFieldMooCompany.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldMooCompany.setFont(defaultFont1);
        integerTextFieldMooCompany.setMinimumSize(new java.awt.Dimension(30, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel2.add(integerTextFieldMooCompany, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel20.add(jPanel2, gridBagConstraints);

        jLabel46.setFont(defaultFont1);
        jLabel46.setText("PPTel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel20.add(jLabel46, gridBagConstraints);

        jTextFieldPhoneCompany.setFont(defaultFont1);
        jTextFieldPhoneCompany.setMinimumSize(new java.awt.Dimension(80, 21));
        jTextFieldPhoneCompany.setPreferredSize(new java.awt.Dimension(80, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel20.add(jTextFieldPhoneCompany, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 11, 0, 11);
        jPanelData1.add(jPanel20, gridBagConstraints);

        integerTextFieldAmountWoman.setColumns(3);
        integerTextFieldAmountWoman.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldAmountWoman.setFont(defaultFont1);
        integerTextFieldAmountWoman.setMinimumSize(new java.awt.Dimension(30, 20));
        integerTextFieldAmountWoman.setPreferredSize(new java.awt.Dimension(30, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 5, 0, 11);
        jPanelData1.add(integerTextFieldAmountWoman, gridBagConstraints);

        jLabel26.setFont(defaultFont1);
        jLabel26.setText("AmountWoman");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 5, 0, 0);
        jPanelData1.add(jLabel26, gridBagConstraints);

        jLabel21.setFont(defaultFont1);
        jLabel21.setText("CompanyName");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 0, 0);
        jPanelData1.add(jLabel21, gridBagConstraints);

        jTextFieldCompanyName.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 22, 0, 11);
        jPanelData1.add(jTextFieldCompanyName, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel22.setFont(defaultFont1);
        jLabel22.setText("Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
        jPanel3.add(jLabel22, gridBagConstraints);

        jComboBoxCompanyType.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 0, 0);
        jPanel3.add(jComboBoxCompanyType, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 11, 0, 11);
        jPanelData1.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 5, 0, 0);
        add(jPanelData1, gridBagConstraints);

        jPanelData2.setLayout(new java.awt.GridBagLayout());

        jPanelData2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jLabel76.setFont(defaultFont1);
        jLabel76.setText("CommunityStandardType");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelData2.add(jLabel76, gridBagConstraints);

        jLabel93.setFont(defaultFont1);
        jLabel93.setText("CommunityStandard");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelData2.add(jLabel93, gridBagConstraints);

        jComboBoxStandardCompany.setFont(defaultFont1);
        jComboBoxStandardCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxStandardCompanyActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 22, 0, 11);
        jPanelData2.add(jComboBoxStandardCompany, gridBagConstraints);

        jPanel37.setLayout(new java.awt.GridBagLayout());

        jTextFieldStandardTypeCompanyDescription.setFont(defaultFont1);
        jTextFieldStandardTypeCompanyDescription.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldStandardTypeCompanyDescriptionFocusLost(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel37.add(jTextFieldStandardTypeCompanyDescription, gridBagConstraints);

        jComboBoxStandardTypeCompany.setFont(defaultFont1);
        jComboBoxStandardTypeCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxStandardTypeCompanyActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel37.add(jComboBoxStandardTypeCompany, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 22, 0, 11);
        jPanelData2.add(jPanel37, gridBagConstraints);

        jLabel95.setFont(defaultFont1);
        jLabel95.setText("TimeOfPass");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 0, 0);
        jPanelData2.add(jLabel95, gridBagConstraints);

        jPanel41.setLayout(new java.awt.GridBagLayout());

        jTextFieldPeriodOfTimeCompany.setColumns(2);
        jTextFieldPeriodOfTimeCompany.setFont(defaultFont1);
        jTextFieldPeriodOfTimeCompany.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldPeriodOfTimeCompany.setMinimumSize(new java.awt.Dimension(26, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel41.add(jTextFieldPeriodOfTimeCompany, gridBagConstraints);

        jLabel102.setFont(defaultFont1);
        jLabel102.setText("Month");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel41.add(jLabel102, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 22, 0, 0);
        jPanelData2.add(jPanel41, gridBagConstraints);

        jPanel42.setLayout(new java.awt.GridBagLayout());

        jLabel104.setFont(defaultFont1);
        jLabel104.setText("DateCloseActivity");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel42.add(jLabel104, gridBagConstraints);

        jLabel105.setFont(defaultFont1);
        jLabel105.setText("CauseOfClose");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel42.add(jLabel105, gridBagConstraints);

        dateComboBoxCloseCompany.setFont(defaultFont1);
        dateComboBoxCloseCompany.setMinimumSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel42.add(dateComboBoxCloseCompany, gridBagConstraints);

        jCheckBoxCloseCompany.setFont(defaultFont1);
        jCheckBoxCloseCompany.setText("CloseActivity");
        jCheckBoxCloseCompany.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jCheckBoxCloseCompanyMouseReleased(evt);
            }
        });
        jCheckBoxCloseCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxCloseCompanyActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel42.add(jCheckBoxCloseCompany, gridBagConstraints);

        jTextAreaCauseCloseCompany.setFont(defaultFont1);
        jTextAreaCauseCloseCompany.setLineWrap(true);
        jTextAreaCauseCloseCompany.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextAreaCauseCloseCompany);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel42.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 9, 0, 11);
        jPanelData2.add(jPanel42, gridBagConstraints);

        jPanel76.setLayout(new java.awt.GridBagLayout());

        buttonGroup2.add(jRadioButtonCompanyNotCo);
        jRadioButtonCompanyNotCo.setFont(defaultFont1);
        jRadioButtonCompanyNotCo.setText("NotCo");
        jRadioButtonCompanyNotCo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCompanyNotCoActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel76.add(jRadioButtonCompanyNotCo, gridBagConstraints);

        buttonGroup2.add(jRadioButtonCompanyCo);
        jRadioButtonCompanyCo.setFont(defaultFont1);
        jRadioButtonCompanyCo.setSelected(true);
        jRadioButtonCompanyCo.setText("Co");
        jRadioButtonCompanyCo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCompanyCoActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel76.add(jRadioButtonCompanyCo, gridBagConstraints);

        jLabel74.setFont(defaultFont1);
        jLabel74.setText("\u0e42\u0e04\u0e23\u0e07\u0e01\u0e32\u0e23\u0e17\u0e35\u0e48\u0e17\u0e33\u0e07\u0e32\u0e19\u0e19\u0e48\u0e32\u0e2d\u0e22\u0e39\u0e48");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel76.add(jLabel74, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 11);
        jPanelData2.add(jPanel76, gridBagConstraints);

        jLabel75.setFont(defaultFont1);
        jLabel75.setText("\u0e27\u0e31\u0e19\u0e17\u0e35\u0e48\u0e40\u0e02\u0e49\u0e32\u0e42\u0e04\u0e23\u0e07\u0e01\u0e32\u0e23");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 11, 0, 0);
        jPanelData2.add(jLabel75, gridBagConstraints);

        dateComboBoxCompanyCoDate.setFont(defaultFont1);
        dateComboBoxCompanyCoDate.setMinimumSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 0, 11);
        jPanelData2.add(dateComboBoxCompanyCoDate, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        integerTextFieldTimeOfPassCompany.setColumns(3);
        integerTextFieldTimeOfPassCompany.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldTimeOfPassCompany.setFont(defaultFont1);
        integerTextFieldTimeOfPassCompany.setMinimumSize(new java.awt.Dimension(30, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(integerTextFieldTimeOfPassCompany, gridBagConstraints);

        dateComboBoxDateOfPassCompany.setFont(defaultFont1);
        dateComboBoxDateOfPassCompany.setMinimumSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(dateComboBoxDateOfPassCompany, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 11);
        jPanelData2.add(jPanel1, gridBagConstraints);

        jLabel103.setFont(defaultFont1);
        jLabel103.setText("InMarket");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelData2.add(jLabel103, gridBagConstraints);

        jComboBoxInMarketCompany.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 11);
        jPanelData2.add(jComboBoxInMarketCompany, gridBagConstraints);

        jLabel101.setFont(defaultFont1);
        jLabel101.setText("PeriodOfTime");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 0, 0);
        jPanelData2.add(jLabel101, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 5, 0, 5);
        add(jPanelData2, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxSearchMooActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSearchMooActionPerformed
        this.jComboBoxSearchVillageCompany.setEnabled(jCheckBoxSearchMoo.isSelected());
    }//GEN-LAST:event_jCheckBoxSearchMooActionPerformed

    private void jCheckBoxSaveHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSaveHistoryActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxSaveHistoryActionPerformed

    private void jCheckBoxCloseCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxCloseCompanyActionPerformed
        dateComboBoxCloseCompany.setEnabled(jCheckBoxCloseCompany.isSelected());
        jTextAreaCauseCloseCompany.setEnabled(jCheckBoxCloseCompany.isSelected());
    }//GEN-LAST:event_jCheckBoxCloseCompanyActionPerformed

    private void jComboBoxStandardCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxStandardCompanyActionPerformed
        boolean have_gain = jComboBoxStandardCompany.getSelectedIndex()!=0;
        integerTextFieldTimeOfPassCompany.setEnabled(have_gain);
        dateComboBoxDateOfPassCompany.setEnabled(have_gain);
        jTextFieldPeriodOfTimeCompany.setEnabled(have_gain);
    }//GEN-LAST:event_jComboBoxStandardCompanyActionPerformed

    private void jComboBoxSearchVillageCompanyFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBoxSearchVillageCompanyFocusGained
// TODO add your handling code here:
//        ComboboxModel.initComboBox(this.jComboBoxSearchVillageCompany,theAllComboBoxControl.listVillage2());
    }//GEN-LAST:event_jComboBoxSearchVillageCompanyFocusGained

    private void jCheckBoxCloseCompanyMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxCloseCompanyMouseReleased
        if(jCheckBoxCloseCompany.isSelected()) {
            //jPanel42.setVisible(true);
            dateComboBoxCloseCompany.setEnabled(true);
            jTextAreaCauseCloseCompany.setEnabled(true);
        } else {
            //jPanel42.setVisible(false);
        }
    }//GEN-LAST:event_jCheckBoxCloseCompanyMouseReleased

    private void jTextFieldStandardTypeCompanyDescriptionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldStandardTypeCompanyDescriptionFocusLost
        standardTypeCompany = jTextFieldStandardTypeCompanyDescription.getText();
    }//GEN-LAST:event_jTextFieldStandardTypeCompanyDescriptionFocusLost

    private void jComboBoxStandardTypeCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxStandardTypeCompanyActionPerformed
        /*90 = อื่นๆ ระบุ*/
        if(ComboboxModel.getCodeComboBox(jComboBoxStandardTypeCompany).equals("90")) {
            jTextFieldStandardTypeCompanyDescription.setVisible(true);
            jTextFieldStandardTypeCompanyDescription.setEnabled(true);
            jTextFieldStandardTypeCompanyDescription.setText(standardTypeCompany);
            standardTypeCompany=null;
        } else {
            jTextFieldStandardTypeCompanyDescription.setVisible(false);
            jTextFieldStandardTypeCompanyDescription.setText("");
        }
    }//GEN-LAST:event_jComboBoxStandardTypeCompanyActionPerformed

    private void jRadioButtonCompanyCoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCompanyCoActionPerformed
        dateComboBoxCompanyCoDate.setEnabled(true);
    }//GEN-LAST:event_jRadioButtonCompanyCoActionPerformed

    private void jRadioButtonCompanyNotCoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCompanyNotCoActionPerformed
        dateComboBoxCompanyCoDate.setEnabled(false);
    }//GEN-LAST:event_jRadioButtonCompanyNotCoActionPerformed

    private void jTableHistoryCompanyMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableHistoryCompanyMouseReleased
        selectCompanyHistory();
    }//GEN-LAST:event_jTableHistoryCompanyMouseReleased

    private void jTableCompanyMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCompanyMouseReleased
        selectCompany(-2);
    }//GEN-LAST:event_jTableCompanyMouseReleased

    private void jComboBoxSearchVillageCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSearchVillageCompanyActionPerformed
        searchCompany();
    }//GEN-LAST:event_jComboBoxSearchVillageCompanyActionPerformed

    private void jButtonSearchCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchCompanyActionPerformed
        searchCompany();
    }//GEN-LAST:event_jButtonSearchCompanyActionPerformed

    private void jTextFieldSearchCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchCompanyActionPerformed
        searchCompany();
    }//GEN-LAST:event_jTextFieldSearchCompanyActionPerformed

    private void jButtonSaveCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveCompanyActionPerformed
        
        if(this.jCheckBoxSaveHistory.isSelected() || this.theCompany.getObjectId()==null)
            saveCompany();
        else
            updateCompany();
    }//GEN-LAST:event_jButtonSaveCompanyActionPerformed

    private void jButtonDeleteCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteCompanyActionPerformed
        deleteCompany();
    }//GEN-LAST:event_jButtonDeleteCompanyActionPerformed

    private void jButtonAddCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddCompanyActionPerformed
        addCompany();
    }//GEN-LAST:event_jButtonAddCompanyActionPerformed

    
    private void setLanguage()
    {  
        /*jLabel*/           
        jLabel20.setText(GutilPCU.getTextBundle(jLabel20.getText()));  
        jLabel21.setText(GutilPCU.getTextBundle(jLabel21.getText()));        
        jLabel22.setText(GutilPCU.getTextBundle(jLabel22.getText()));        
        jLabel23.setText(GutilPCU.getTextBundle(jLabel23.getText()));        
        jLabel25.setText(GutilPCU.getTextBundle(jLabel25.getText())); 
        jLabel26.setText(GutilPCU.getTextBundle(jLabel26.getText()));
        jLabel34.setText(GutilPCU.getTextBundle(jLabel34.getText()));
        jLabel43.setText(GutilPCU.getTextBundle(jLabel43.getText()));
        jLabel44.setText(GutilPCU.getTextBundle(jLabel44.getText()));
        jLabel45.setText(GutilPCU.getTextBundle(jLabel45.getText()));
        jLabel46.setText(GutilPCU.getTextBundle(jLabel46.getText()));
        jLabel74.setText(GutilPCU.getTextBundle(jLabel74.getText()));
        jLabel75.setText(GutilPCU.getTextBundle(jLabel75.getText()));
        jLabel76.setText(GutilPCU.getTextBundle(jLabel76.getText()));
        jLabel93.setText(GutilPCU.getTextBundle(jLabel93.getText()));
        jLabel95.setText(GutilPCU.getTextBundle(jLabel95.getText()));
        jLabel101.setText(GutilPCU.getTextBundle(jLabel101.getText()));
        jLabel102.setText(GutilPCU.getTextBundle(jLabel102.getText()));
        jLabel103.setText(GutilPCU.getTextBundle(jLabel103.getText()));
        jLabel104.setText(GutilPCU.getTextBundle(jLabel104.getText()));
        jLabel105.setText(GutilPCU.getTextBundle(jLabel105.getText()));
        jButtonSearchCompany.setText(GutilPCU.getTextBundle(jButtonSearchCompany.getText()));
        jButtonAddCompany.setText(GutilPCU.getTextBundle(jButtonAddCompany.getText()));
        jButtonDeleteCompany.setText(GutilPCU.getTextBundle(jButtonDeleteCompany.getText()));
        jButtonSaveCompany.setText(GutilPCU.getTextBundle(jButtonSaveCompany.getText()));    
        
        /*TitledBorder*/        
        GutilPCU.JPanelLabler(jPanelSearchCompany);
        jCheckBoxCloseCompany.setText(GutilPCU.getTextBundle(jCheckBoxCloseCompany.getText())); 
        
        /*Radio*/
        jRadioButtonCompanyNoRegister.setText(GutilPCU.getTextBundle(jRadioButtonCompanyNoRegister.getText()));
        jRadioButtonCompanyRegister.setText(GutilPCU.getTextBundle(jRadioButtonCompanyRegister.getText()));
        jRadioButtonCompanyCo.setText(GutilPCU.getTextBundle(jRadioButtonCompanyCo.getText()));
        jRadioButtonCompanyNotCo.setText(GutilPCU.getTextBundle(jRadioButtonCompanyNotCo.getText()));
        jCheckBoxSaveHistory.setText(GutilPCU.getTextBundle(jCheckBoxSaveHistory.getText()));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.pcu.utility.DateComboBox dateComboBoxCloseCompany;
    private com.pcu.utility.DateComboBox dateComboBoxCompanyCoDate;
    private com.pcu.utility.DateComboBox dateComboBoxDateOfPassCompany;
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private com.pcu.utility.IntegerTextField integerTextFieldAmountMan;
    private com.pcu.utility.IntegerTextField integerTextFieldAmountWoman;
    private com.pcu.utility.IntegerTextField integerTextFieldMooCompany;
    private com.pcu.utility.IntegerTextField integerTextFieldTimeOfPassCompany;
    private javax.swing.JButton jButtonAddCompany;
    private javax.swing.JButton jButtonDeleteCompany;
    private javax.swing.JButton jButtonSaveCompany;
    private javax.swing.JButton jButtonSearchCompany;
    private javax.swing.JCheckBox jCheckBoxCloseCompany;
    private javax.swing.JCheckBox jCheckBoxSaveHistory;
    private javax.swing.JCheckBox jCheckBoxSearchMoo;
    private javax.swing.JComboBox jComboBoxCompanyType;
    private javax.swing.JComboBox jComboBoxInMarketCompany;
    private javax.swing.JComboBox jComboBoxSearchVillageCompany;
    private javax.swing.JComboBox jComboBoxStandardCompany;
    private javax.swing.JComboBox jComboBoxStandardTypeCompany;
    private javax.swing.JComboBox jComboBoxVillageCompany;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JPanel jPanelData1;
    private javax.swing.JPanel jPanelData2;
    private javax.swing.JPanel jPanelSearchCompany;
    private javax.swing.JRadioButton jRadioButtonCompanyCo;
    private javax.swing.JRadioButton jRadioButtonCompanyNoRegister;
    private javax.swing.JRadioButton jRadioButtonCompanyNotCo;
    private javax.swing.JRadioButton jRadioButtonCompanyRegister;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableCompany;
    private javax.swing.JTable jTableHistoryCompany;
    private javax.swing.JTextArea jTextAreaCauseCloseCompany;
    private com.pcu.utility.IntegerTextField jTextFieldCompanyCode;
    private javax.swing.JTextField jTextFieldCompanyName;
    private javax.swing.JTextField jTextFieldCompanyOwner;
    private javax.swing.JTextField jTextFieldHomeNumberCompany;
    private javax.swing.JTextField jTextFieldPeriodOfTimeCompany;
    private javax.swing.JTextField jTextFieldPhoneCompany;
    private javax.swing.JTextField jTextFieldRoadCompany;
    private javax.swing.JTextField jTextFieldSearchCompany;
    private javax.swing.JTextField jTextFieldStandardTypeCompanyDescription;
    private com.hosv3.gui.component.PanelCatAddress panelCatAddress1;
    // End of variables declaration//GEN-END:variables


    protected JLabel jPaneData2;
    
}
