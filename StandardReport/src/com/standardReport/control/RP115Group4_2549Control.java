/*
 * RP115Group4_2549Control.java
 *
 * Created on 23 �չҤ� 2549, 12:09 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.control;

import java.util.Vector;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.control.HosControl;
import com.hosv3.object.UseCase;
import com.standardReport.object.RP115Group4AbortPregnant_2549;
import com.standardReport.object.RP115Group4Assort_2549;
import com.standardReport.object.RP115Group4CheckConfirm_2549;
import com.standardReport.object.RP115Group4Efficiency_2549;
import com.standardReport.object.RP115Group4PersonAmount_2549;
import com.standardReport.object.RP115Group4TreatInfant_2549;
import com.standardReport.object.RP115Group4_2549;
import com.standardReport.object.Rp115_4;
import com.standardReport.utility.*;

import java.util.Vector;
import java.io.File;
import javax.swing.JFrame;
/**
 *
 * @author pu
 */
public class RP115Group4_2549Control
{
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private Vector vc = null;
    private FileWriter fileWriter;
    private String startCheck;
    private String endCheck;
    private boolean isJan;
    
    private RP115Group4_2549 theRP115Group4_2549;
    private   HosControl theHC;
    
    /** Creates a new instance of RP115Group4_2549Control */
    public RP115Group4_2549Control(com.hosv3.control.HosControl hc,ConnectionInf con,HosDB hdb)
    {
        theHC = hc;
        theConnectionInf = con;
        theHosDB = hdb;
        fileWriter = new FileWriter();
    }

    
    /**
     *��¡�á�õ�Ǩ�Ѵ��ͧ
     *@param startDate �� String �ͧ�ѹ����������㹡�ô֧������
     *@param endDate �� String �ͧ�ѹ�������ش㹡�ô֧������
     *@return Vector �ͧ�����š�õ�Ǩ�Ѵ��ͧ
     *@Author pu
     *@date 23/03/2006
     */
    public Vector selectGroup4Assort(String startDate,String endDate,boolean isDbBackup)
    {
        vc = new Vector();
        if(isDbBackup){
            theConnectionInf = com.hosv3.utility.Config.getConnectionInfDBBackupFromFile(new JFrame());
        }else{
            theConnectionInf = theHosDB.theConnectionInf;
        }
        theConnectionInf.open();
        try
        {            
            setCheckDate(startDate,endDate);
            theHosDB.theRP115Group4_2549DB.theConnectionInf = theConnectionInf;
            vc = theHosDB.theRP115Group4_2549DB.selectGroup4AssortByDate(startDate,endDate, this.startCheck, this.endCheck,this.isJan);
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part4_assort.sql",
                    "�ҹ������ : "+
                    theConnectionInf.getURL()+
                    " ��ǧ���� "+
                    startDate+"-"+
                    endDate,
                    theHC.theUS.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.saveLog(UseCase.UCID_StandardReportModule, "standard_1105_part4_assort.sql", ex, theHC.theUS.ERROR);
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    /**
     *��¡�á�õ�Ǩ�׹�ѹ������ä
     *@param startDate �� String �ͧ�ѹ����������㹡�ô֧������
     *@param endDate �� String �ͧ�ѹ�������ش㹡�ô֧������
     *@return Vector �ͧ�����š�õ�Ǩ�׹�ѹ������ä
     *@Author pu
     *@date 23/03/2006
     */
    public Vector selectGroup4CheckConfirm(String startDate,String endDate)
    {
        vc = new Vector();
        theConnectionInf.open();
        try
        {
            setCheckDate(startDate,endDate);
            vc = theHosDB.theRP115Group4_2549DB.selectGroup4CheckConfirmByDate(startDate,endDate, this.startCheck, this.endCheck,this.isJan);            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }

    /**
     *��¡�á������ش��õ�駤���� ���ͧ�ҡ��á㹤������ Thalassemia
     *@param startDate �� String �ͧ�ѹ����������㹡�ô֧������
     *@param endDate �� String �ͧ�ѹ�������ش㹡�ô֧������
     *@return Vector �ͧ�����š������ش��õ�駤����
     *@Author pu
     *@date 23/03/2006
     */
    public Vector selectGroup4AbortPregnant(String startDate,String endDate)
    {
        vc = new Vector();
        theConnectionInf.open();
        try
        {
            //setCheckDate(startDate,endDate);
            vc = theHosDB.theRP115Group4_2549DB.selectGroup4AbortPregnantByDate(startDate,endDate);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }

    /**
     *��¡�á���ѡ�� �� 0-1 ��͹��躡���ͧ���ʹչ������´�
     *@param startDate �� String �ͧ�ѹ����������㹡�ô֧������
     *@param endDate �� String �ͧ�ѹ�������ش㹡�ô֧������
     *@return Vector �ͧ�����š���ѡ�� �� 0-1 ��͹��躡���ͧ���ʹչ������´�
     *@Author pu
     *@date 23/03/2006
     */
    public Vector selectGroup4TreatInfant(String startDate,String endDate)
    {
        vc = new Vector();
        theConnectionInf.open();
        try
        {
            setCheckDate(startDate,endDate);
            vc = theHosDB.theRP115Group4_2549DB.selectGroup4TreatInfantByDate(startDate,endDate, this.startCheck, this.endCheck, this.isJan);           
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    /**
     *��¡�èӹǹ����㹡������ԡ�ÿ�鹿����ö�Ҿ
     *@param startDate �� String �ͧ�ѹ����������㹡�ô֧������
     *@param endDate �� String �ͧ�ѹ�������ش㹡�ô֧������
     *@return Vector �ͧ�����š������ԡ�ÿ�鹿����ö�Ҿ
     *@Author pu
     *@date 23/03/2006
     */
      public Vector selectGroup4Efficiency(String startDate,String endDate)
    {
        vc = new Vector();
        theConnectionInf.open();
        try
        {
            vc = theHosDB.theRP115Group4_2549DB.selectGroup4EfficiencyByDate(startDate,endDate);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    /**
     *��¡�èӹǹ����Ѻ��ԡ�á����������ҹ
     *@param startDate �� String �ͧ�ѹ����������㹡�ô֧������
     *@param endDate �� String �ͧ�ѹ�������ش㹡�ô֧������
     *@return Vector �ͧ�����š����������ҹ
     *@Author pu
     *@date 23/03/2006
     */
    public Vector selectGroup4PersonAmount(String startDate,String endDate)
    {
        vc = new Vector();
        theConnectionInf.open();
        try
        {
            vc = theHosDB.theRP115Group4_2549DB.selectGroup4PersonAmountByDate(startDate,endDate);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    /**
     *��ô֧��§ҹ�ӹǹ�������͹��� ��ҡ������������ҹ
     *@param startDate �� String �ͧ�ѹ����������㹡�ô֧������
     *@param endDate �� String �ͧ�ѹ�������ش㹡�ô֧������
     *@return Vector �ͧ�����Ũӹǹ�������͹��� ��ҡ������������ҹ
     *@Author pu
     *@date 23/03/2006
     */
    public Vector selectGroup4HomeAmount(String startDate,String endDate)
    {
        vc = new Vector();
        theConnectionInf.open();
        try
        {
            vc = theHosDB.theRP115Group4_2549DB.selectGroup4HomeAmountByDate(startDate,endDate);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    } 
    
    
    /**
     *��ô֧��§ҹ�ӹǹ����㹡���͡˹���͹�����ç���¹
     *@param startDate �� String �ͧ�ѹ����������㹡�ô֧������
     *@param endDate �� String �ͧ�ѹ�������ش㹡�ô֧������
     *@return Vector �ͧ�����Ũӹǹ����㹡���͡˹���͹�����ç���¹
     *@Author pu
     *@date 23/03/2006
     */
    public Vector selectGroup4SchoolOutSite(String startDate,String endDate)
    {
        vc = new Vector();
        theConnectionInf.open();
        try
        {
            vc = theHosDB.theRP115Group4_2549DB.selectGroup4SchoolOutSiteByDate(startDate,endDate);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }    
    
    /**
     *Substring ��͹��л� �͡�����͹�仵�Ǩ�ͺ��ǧ����㹡�ô֧������
     *@param startDate �� String �ͧ�ѹ����������㹡�ô֧������
     *@param endDate �� String �ͧ�ѹ�������ش㹡�ô֧������
     *@Author pu
     *@date 23/03/2006
     */
    private void setCheckDate(String startDate ,String endDate)
    {
        this.startCheck = "";
        this.endCheck = "";
        this.isJan = false;
        
        if(startDate.length() >= 10)
        {
            if(("01").equalsIgnoreCase(startDate.substring(5, 7)))
            {
                this.isJan = true;
            }
            else
            {
                this.startCheck = startDate.substring(0, 4) + "-01";
                this.endCheck = startDate.substring(0, 7);
            } 
        }        
        /*this.startCheck = startDate.substring(0, 7);
        this.endCheck = endDate.substring(0, 7);
        
        System.out.println("-----------this.startCheck-----------------" + this.startCheck);
        System.out.println("------------- this.endCheck---------------" + this.endCheck);*/
    }
    
    /**
     * �������������� Vector ���ǡѹ
     * @Author pu
     * @Date 23/03/2549
     **/
    public Vector setVectorAllGroup4(Vector vAssort, Vector vCheckConfirm, Vector vAbortPregnant, Vector vTreatInfant, Vector vEfficiency,Vector vPersonAmount)
    {
        vc = new Vector();
        theRP115Group4_2549 = new RP115Group4_2549();
        
        RP115Group4Assort_2549 theRP115Group4Assort_2549;
        RP115Group4CheckConfirm_2549 theRP115Group4CheckConfirm_2549;
        RP115Group4AbortPregnant_2549 theRP115Group4AbortPregnant_2549;
        RP115Group4TreatInfant_2549 theRP115Group4TreatInfant_2549;        
        RP115Group4Efficiency_2549 theRP115Group4Efficiency_2549;
        RP115Group4PersonAmount_2549 theRP115Group4PersonAmount_2549;
        
        if((vAssort != null && vAssort.size() > 0) 
            && (vCheckConfirm != null && vCheckConfirm.size() > 0) 
            && (vAbortPregnant != null && vAbortPregnant.size() > 0) 
            && (vTreatInfant != null && vTreatInfant.size() > 0)
            && (vEfficiency != null && vEfficiency.size() > 0)
            && (vPersonAmount != null && vPersonAmount.size() > 0))
        {
            // ��� Vector
            System.out.println("********************vAssort.size()** "+ vAssort.size());
            for(int i=0,sizeReport=vAssort.size();i<sizeReport;i++)
            {
                // �緤��������鹢ͧ Object ��������͹㹷ء�ͺ
                theRP115Group4_2549 = new RP115Group4_2549();
                theRP115Group4_2549.setInitData(); 
                
                // ��ǹ������ ��äѴ��ͧ
                theRP115Group4Assort_2549 = new RP115Group4Assort_2549();
                theRP115Group4Assort_2549 = (RP115Group4Assort_2549)vAssort.get(i);
                if(theRP115Group4Assort_2549 != null)
                {
                    theRP115Group4_2549.plan_type = theRP115Group4Assort_2549.plan_type;
                    theRP115Group4_2549.womb_cancer = theRP115Group4Assort_2549.womb_cancer;
                    theRP115Group4_2549.breast_cancer = theRP115Group4Assort_2549.breast_cancer;
                    theRP115Group4_2549.thalassemia = theRP115Group4Assort_2549.thalassemia;
                    theRP115Group4_2549.thalassemia_mom = theRP115Group4Assort_2549.thalassemia_mom;
                    theRP115Group4_2549.iodine_thiroid = theRP115Group4Assort_2549.iodine_thiroid;
                }
                theRP115Group4Assort_2549 = null;
                
                // ��ǹ������ ��õ�Ǩ�׹�ѹ
                theRP115Group4CheckConfirm_2549 = new RP115Group4CheckConfirm_2549();
                theRP115Group4CheckConfirm_2549 = (RP115Group4CheckConfirm_2549)vCheckConfirm.get(i);
                if(theRP115Group4CheckConfirm_2549 != null)
                {
                    theRP115Group4_2549.thalassemia_infant = theRP115Group4CheckConfirm_2549.thalassemia_infant;
                    theRP115Group4_2549.thiroid = theRP115Group4CheckConfirm_2549.thiroid;
                }
                theRP115Group4CheckConfirm_2549 = null;       
              
                // ��ǹ������ �������ش��õ�駤���� ���ͧ�ҡ��á㹤������ Thalassemia
                theRP115Group4AbortPregnant_2549 = new RP115Group4AbortPregnant_2549();
                theRP115Group4AbortPregnant_2549 = (RP115Group4AbortPregnant_2549)vAbortPregnant.get(i);
                if(theRP115Group4AbortPregnant_2549 != null)
                {
                    theRP115Group4_2549.terminated_pregnance = theRP115Group4AbortPregnant_2549.terminated_pregnance;
                }
                theRP115Group4AbortPregnant_2549 = null;
                
                // ��ǹ������ ������ 0-1 ��͹���Ѻ����ѡ������к����ͧ���ʹչ������´�
                theRP115Group4TreatInfant_2549 = new RP115Group4TreatInfant_2549();
                theRP115Group4TreatInfant_2549 = (RP115Group4TreatInfant_2549)vTreatInfant.get(i);
                if(theRP115Group4TreatInfant_2549 != null)
                {
                    theRP115Group4_2549.treat_thiroid = theRP115Group4TreatInfant_2549.treat_thiroid;
                }
                theRP115Group4TreatInfant_2549 = null;
                
                // ��ǹ������ �ӹǹ�������ԡ�ÿ�鹿����ö�Ҿ
                theRP115Group4Efficiency_2549 = new RP115Group4Efficiency_2549();
                theRP115Group4Efficiency_2549 = (RP115Group4Efficiency_2549)vEfficiency.get(i);
                if(theRP115Group4Efficiency_2549 != null)
                {
                    theRP115Group4_2549.efficiency = theRP115Group4Efficiency_2549.efficiency;
                }
                theRP115Group4Efficiency_2549 = null;
                
                 // ��ǹ������ �ӹǹ����Ѻ��ԡ�á����������ҹ
                theRP115Group4PersonAmount_2549 = new RP115Group4PersonAmount_2549();
                theRP115Group4PersonAmount_2549 = (RP115Group4PersonAmount_2549)vPersonAmount.get(i);
                if(theRP115Group4PersonAmount_2549 != null)
                {
                    theRP115Group4_2549.person = theRP115Group4PersonAmount_2549.person;
                }
                theRP115Group4PersonAmount_2549 = null;
                
                vc.add(theRP115Group4_2549);                
                theRP115Group4_2549 = null;                  
            }
        }       
        return vc;
    }
    
}
