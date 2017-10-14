package com.hosv3.gui.panel.transaction;
/*
 * Main.java
 *
 * Created on 29 �ѹ��¹ 2546, 9:31 �.
 */
import com.hosv3.utility.Constant;
import com.hospital_os.gui.font.*;
import com.hosv3.gui.dialog.*;
import com.hosv3.object.*;
import com.hosv3.utility.connection.*;
import com.hosv3.control.*;
import javax.swing.*;
import java.awt.Component;

/**
 *
 * @author  Surachai Thowong
 */
public class HosPanel extends javax.swing.JPanel
{     
    static final long serialVersionUID = 0;
    public PanelListVisitByTransfer aPanelListVisitByTransfer;
    public PanelListVisitByWard aPanelListVisitByWard;
    public JPanel aPanelSocialData;
    public PanelVisit aPanelVisit;
    public PanelVitalSign aPanelVitalSign;
    public PanelOrder aPanelOrder;
    public PanelDiagICD10 aPanelDiagICD10;
    public PanelDiagICD9 aPanelDiagICD9;
    public PanelDiagnosis aPanelDiagIcd;
    public PanelBilling aPanelBilling;
    public PanelLab aPanelLab;
    public PanelXray aPanelXray;
    public JTabbedPane aPanelListVisit;
    public JPanel thePanelCurrentVisit;
    HosDialog theHD;
    /** ੾���������  henbe_just
    PanelHome aPanelHome;
    PanelFpWoman aPanelFpWoman;
    PanelBeforeMch aPanelBeforeMch;
    PanelAfterMch aPanelAfterMch;
    PanelEpi aPanelEpi;    
    */
    DefaultFont defaultFont1 = new DefaultFont();
    HosControl theHC;
    HosObject theHO;
    UpdateStatus theUS;
    public JTabbedPane theJTabbedPane;

    private GHospitalSuit theGHS;
    /** Creates a new instance of Main */
    public HosPanel(HosDialog hd,HosControl hosc,UpdateStatus us)
    {
        theHC = hosc;
        theHD = hd;
        theHO = hosc.theHO;
        theUS = us;
        theGHS = new GHospitalSuit(hosc);
        theJTabbedPane = new JTabbedPane();
        theJTabbedPane.setFont(defaultFont1);
        //theJTabbedPane.setUI(new HosTabbedPaneUI());
        theJTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        //initUserAuthen();
    }
    
    public void setClearAllPanel()
    {
         aPanelListVisitByTransfer = null;
         aPanelListVisitByWard = null;
         aPanelSocialData = null;
         aPanelVisit = null;
         aPanelVitalSign = null;
         aPanelOrder = null;
         aPanelDiagICD10 = null;
         aPanelDiagICD9 = null;
         aPanelDiagIcd = null;
         aPanelBilling = null;
         aPanelLab = null;
         aPanelXray = null;
         aPanelListVisit = null;
         thePanelCurrentVisit = null;
    }
    
    public void refreshLookup()
    {
        if(aPanelListVisitByTransfer!=null)
            aPanelListVisitByTransfer.initComboBox();
        if(aPanelListVisitByWard!=null)
            aPanelListVisitByWard.initComboBox();
        //aPanelVisit.initComboBox();  //����� combobox ����� by henbe
        if(aPanelVitalSign!=null)
            aPanelVitalSign.initComboBox();
        if(aPanelOrder!=null)
            aPanelOrder.initComboBox();
        if(aPanelDiagICD10!=null)
            aPanelDiagICD10.initComboBox();
        if(aPanelDiagICD9!=null)
            aPanelDiagICD9.initComboBox();
        //aPanelBilling.initComboBox(); //����� combobox ����� by henbe
        //aPanelLab.initComboBox(); //����� combobox ����� by henbe
        if(aPanelXray!=null)
            aPanelXray.initComboBox();
        if(thePanelCurrentVisit!=null){
            try{
                ((PanelCurrentVisit)thePanelCurrentVisit).initComboBox();
            }catch(Exception e){
                Constant.println(e.getMessage());
            }
        }
    }
    
    public void initPanelSOpd(boolean read,boolean write){ 
        if(!read) return;   
        aPanelListVisitByTransfer = new PanelListVisitByTransfer();
        aPanelListVisitByTransfer.setControl(theHC,theUS);
        aPanelListVisitByTransfer.setDialog(theHD);
        aPanelListVisitByTransfer.setEnabled(write);
        if(aPanelListVisit==null) {
             aPanelListVisit = new JTabbedPane();
             aPanelListVisit.setFont(defaultFont1);
             theJTabbedPane.addTab(Constant.getTextBundle("��ª��ͼ�����"), aPanelListVisit);
        }
         aPanelListVisit.addTab(Constant.getTextBundle("��ª���㹨ش��ԡ��"), aPanelListVisitByTransfer);
    }
    public void initPanelSIpd(boolean read,boolean write){
        if(!read) return;
         aPanelListVisitByWard = new PanelListVisitByWard();
         aPanelListVisitByWard.setControl(theHC,theUS);
         if(aPanelListVisit==null) {
             aPanelListVisit = new JTabbedPane();
             aPanelListVisit.setFont(defaultFont1);
             theJTabbedPane.addTab(Constant.getTextBundle("��ª��ͼ�����"), aPanelListVisit);
        }
         aPanelListVisit.addTab(Constant.getTextBundle("��ª��������"), aPanelListVisitByWard);
    }
    public void initPanelDPatient(boolean read,boolean write){ 
        if(!read) return;
        this.aPanelSocialData = new JPanel();
         theJTabbedPane.addTab(Constant.getTextBundle("1.�����ż�����"), aPanelSocialData);
    }
    
    public int getTabIndexComponent(Component c)
    {
        return getTabIndexComponent(theJTabbedPane,c);
    }
    public int getTabIndexComponent(JTabbedPane jt,Component c)
    {
        if(c==null)
            return -1;
        for(int i=0;i<jt.getTabCount();i++)
        {
            Component comp = jt.getComponentAt(i);
            if(comp.equals(c))
                return i;
        }
        return -1;
    }
    
    public boolean replacePanel(String index,JPanel jp,String name)
    {
        try{
            int ind = Integer.parseInt(index);
            int ind_check=-1;
            if(index.equals("0")){
                thePanelCurrentVisit = jp;           
                return true;
            }
            if(ind==1){
                ind_check = getTabIndexComponent(aPanelSocialData);
                if(ind_check==-1)  return false;
                aPanelSocialData = jp;// Somprasong Fix bug 090810 ��������������ͷѺ˹�ҹ��� ������͡ᶺ�ҡ����ᶺ���������ö���¡ᶺ������ʴ���
                theJTabbedPane.remove(ind_check);
                theJTabbedPane.insertTab( Constant.getTextBundle(name),null, jp,"",ind_check);
                return true;
            }
            else if(ind==2){
                ind_check = getTabIndexComponent(aPanelVisit);
                if(ind_check==-1)  return false;
                aPanelVisit.gc();
                theJTabbedPane.remove(ind_check);
                theJTabbedPane.insertTab( Constant.getTextBundle(name),null, jp,"",ind_check);
                return true;
            }
            else if(ind==3){
                ind_check = getTabIndexComponent(aPanelVitalSign);
                if(ind_check==-1)  return false;
                aPanelVitalSign = null;
                theJTabbedPane.remove(ind_check);
                theJTabbedPane.insertTab( Constant.getTextBundle(name),null, jp,"",ind_check);
                return true;
            }
            else if(ind==4){
                ind_check = getTabIndexComponent(aPanelOrder);
//                //����������ѭ�ҷ���Դ�ҡ Module Xray ��� PanelOrder �Ҥ�ͺ���Ǿ���������
                if(ind_check==-1)  return false;
//                aPanelOrder = null;
                theJTabbedPane.remove(ind_check);
                theJTabbedPane.insertTab( Constant.getTextBundle(name),null, jp,"",ind_check);
//                aPanelOrder = new PanelOrder();
                return true;
            }
            else if(ind==5){
                ind_check = getTabIndexComponent(aPanelDiagIcd);
                if(ind_check==-1)  return false;
                aPanelDiagIcd = null;
                theJTabbedPane.remove(ind_check);
                theJTabbedPane.insertTab( Constant.getTextBundle(name),null, jp,"",ind_check);
                return true;
            }
            else if(ind==6){
                ind_check = getTabIndexComponent(aPanelBilling);
                if(ind_check==-1)  return false;
                aPanelBilling = null;
                theJTabbedPane.remove(ind_check);
                theJTabbedPane.insertTab( Constant.getTextBundle(name),null, jp,"",ind_check);
                return true;
            }
            else if(ind==11){
                ind_check = getTabIndexComponent(aPanelListVisit,aPanelListVisitByTransfer);
                if(ind_check==-1)  return false;
                aPanelBilling = null;
                aPanelListVisit.remove(ind_check);
                aPanelListVisit.insertTab( Constant.getTextBundle(name),null, jp,"",ind_check);
                return true;
            }
            else if(ind==12){
                ind_check = getTabIndexComponent(aPanelListVisit,aPanelListVisitByWard);
                if(ind_check==-1)  return false;
                aPanelBilling = null;
                aPanelListVisit.remove(ind_check);
                aPanelListVisit.insertTab( Constant.getTextBundle(name),null, jp,"",ind_check);
                return true;
            }
            else{
                theJTabbedPane.addTab(Constant.getTextBundle(name),jp);
                return true;
            }   
        }
        catch(Exception e){
            e.printStackTrace(Constant.getPrintStream());
            return false;
        }
        
    }
    
    public void initPanelCVisit(boolean read,boolean write){
        thePanelCurrentVisit = new PanelCurrentVisit();
        ((PanelCurrentVisit)thePanelCurrentVisit).setControl(theHC,theUS);
        ((PanelCurrentVisit)thePanelCurrentVisit).setDialog(theHD);
    }
    public void initPanelDVisit(boolean read,boolean write){
        if(!read) return;
         aPanelVisit = new PanelVisit();
         aPanelVisit.setControl(theHC,theUS);
         aPanelVisit.setDialog(theHD);
         theJTabbedPane.addTab(Constant.getTextBundle("2.����Ѻ��ԡ��"), aPanelVisit);
    }
    public void initPanelDOrder(boolean read,boolean write){
        if(!read) return;
         aPanelOrder = new PanelOrder();
         aPanelOrder.setControl(theHC,theUS);
         aPanelOrder.setDialog(theHD);
         theJTabbedPane.addTab(            Constant.getTextBundle("4.��õ�Ǩ/�ѡ��"), aPanelOrder);  
   }
    public void initPanelDVital(boolean read,boolean write){  
        if(!read) return; 
         aPanelVitalSign = new PanelVitalSign();
         aPanelVitalSign.setControl(theHC,theHC.theGPS,theGHS,theUS);
         aPanelVitalSign.setDialog(theHD);
         theJTabbedPane.addTab(            Constant.getTextBundle("3.�ҡ���纻���"), aPanelVitalSign);
   }
    public void initPanelDDiagIcd(boolean read,boolean write){
        if(!read) return;
        aPanelDiagIcd = new PanelDiagnosis();
        aPanelDiagICD10 = new PanelDiagICD10();
        aPanelDiagICD9 = new PanelDiagICD9();
        aPanelDiagICD10.setControl(theHC,theHC.theGPS,theGHS,theUS);
        aPanelDiagICD9.setControl(theHC,theUS);
        aPanelDiagICD10.setDialog(theHD);
        aPanelDiagICD9.setDialog(theHD);
        aPanelDiagIcd = new PanelDiagnosis();
        theJTabbedPane.addTab(            Constant.getTextBundle("5.����ԹԨ���"), aPanelDiagIcd);
        aPanelDiagIcd.addPanel(            Constant.getTextBundle("���ŧ���� ICD-10"), aPanelDiagICD10);
        aPanelDiagIcd.addPanel(             Constant.getTextBundle("���ŧ���� ICD-9"), aPanelDiagICD9); 
    }
    public void initPanelDBill(boolean read,boolean write){
        if(!read) return;
         aPanelBilling = new PanelBilling();
         aPanelBilling.setControl(theHC,theUS);
         aPanelBilling.setDialog(theHD);
         theJTabbedPane.addTab(            Constant.getTextBundle("6.����Թ"), aPanelBilling);
    }
    public void initPanelDXray(boolean read,boolean write){
        if(!read) return;
         aPanelXray = new PanelXray();
         aPanelXray.setControl(theHC,theUS);
         aPanelXray.setDialog(theHD);
         theJTabbedPane.addTab(             Constant.getTextBundle("��硫����"), aPanelXray);
   }
   /*  ���ʴ� �����·������㹨ش��ԡ�õ�ҧ� ����֧�ش����� ward ����
    *  �ҡ �ش����������ʴ� ward ����ʴ�੾�Шش��ԡ�����ҧ����
    * 
    */
    public void initPanelDLab(boolean read,boolean write){
        if(!read) return;
         aPanelLab = new PanelLab();
         aPanelLab.setWrite(write);
         aPanelLab.setControl(theHC,theUS);
         aPanelLab.setDialog(theHD);

               theJTabbedPane.addTab(          Constant.getTextBundle("�Ż"), aPanelLab);
         aPanelLab.setLanguage("");
        
    }
}
