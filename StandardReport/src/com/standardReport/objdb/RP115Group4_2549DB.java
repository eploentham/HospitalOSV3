/*
 * RP115Group4_2549DB.java
 *
 * Created on 22 �չҤ� 2549, 17:49 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.objdb;
import com.standardReport.object.RP115Group4AbortPregnant_2549;
import com.standardReport.object.RP115Group4Assort_2549;
import com.standardReport.object.RP115Group4CheckConfirm_2549;
import com.standardReport.object.RP115Group4Efficiency_2549;
import com.standardReport.object.RP115Group4HomeAmount_2549;
import com.standardReport.object.RP115Group4PersonAmount_2549;
import com.standardReport.object.RP115Group4SchoolOutSite_2549;
import com.standardReport.object.RP115Group4TreatInfant_2549;
import com.standardReport.object.RP115Group4_2549;
import java.util.*;
import java.sql.*;
import java.io.*;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.standardReport.subject.StandardDB;
import com.standardReport.utility.*;
/**
 *
 * @author pu
 */
public class RP115Group4_2549DB
{
    public ConnectionInf theConnectionInf;
    RP115Group4Assort_2549 theRP115Group4Assort_2549;
    RP115Group4CheckConfirm_2549 theRP115Group4CheckConfirm_2549;
    RP115Group4AbortPregnant_2549 theRP115Group4AbortPregnant_2549;
    RP115Group4TreatInfant_2549 theRP115Group4TreatInfant_2549;        
    RP115Group4Efficiency_2549 theRP115Group4Efficiency_2549;
    RP115Group4PersonAmount_2549 theRP115Group4PersonAmount_2549;
    RP115Group4_2549 theRP115Group4_2549;

    RP115Group4HomeAmount_2549 theRP115Group4HomeAmount_2549;
    RP115Group4SchoolOutSite_2549 theRP115Group4SchoolOutSite_2549;
    
    ResultSet rs = null;
    String SQL = "";
    Vector vReport;
    /** Creates a new instance of RP115Group4_2549DB */
    public RP115Group4_2549DB(ConnectionInf conf)
    {
        theConnectionInf = conf;
        theRP115Group4Assort_2549 = new RP115Group4Assort_2549();
        theRP115Group4CheckConfirm_2549 = new RP115Group4CheckConfirm_2549();
        theRP115Group4AbortPregnant_2549 = new RP115Group4AbortPregnant_2549();
        theRP115Group4TreatInfant_2549 = new RP115Group4TreatInfant_2549();
        theRP115Group4Efficiency_2549 = new RP115Group4Efficiency_2549();
        theRP115Group4PersonAmount_2549 = new RP115Group4PersonAmount_2549();
        theRP115Group4_2549 = new RP115Group4_2549();
        
        theRP115Group4HomeAmount_2549 = new RP115Group4HomeAmount_2549();
        theRP115Group4SchoolOutSite_2549 = new RP115Group4SchoolOutSite_2549();
        
    }
    
    /**
     *��¡�á�õ�Ǩ�Ѵ��ͧ
     *@param startdate �� String �ͧ�ѹ����������㹡�ô֧������
     *@param enddate �� String �ͧ�ѹ�������ش㹡�ô֧������
     *@param startcheck �� String �ͧ�������͹��ǧ������� ���͹�仵�Ǩ�ͺ��ǧ����㹡�ô֧������
     *@param endcheck �� String �ͧ�������͹��ǧ����ش ���͹�仵�Ǩ�ͺ��ǧ����㹡�ô֧������
	 *@param isJan �� boolean ����Ѻ��Ǩ�ͺ�������͹ ���Ҥ� ������
     *@return Vector �ͧ�����š�õ�Ǩ�Ѵ��ͧ
     *@Author pu
     *@date 22/03/2006
     */
    public Vector selectGroup4AssortByDate(String startdate,String enddate,String startcheck,String endcheck, boolean isJan)throws Exception
    {
          String sql = IOStream.readInputDefault("config/rp_standard/standard_1105_part4_assort.sql");
            PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
            pm.setString(1,startdate);
            pm.setString(2,enddate);
            eAssortQuery(pm.executeQuery());

          sql = null;     
          if(this.vReport.size()==0)
          {
              return null;
          }
          else
          {
              return this.vReport;
          }
    }
    
    /**
     *��¡�á�õ�Ǩ�׹�ѹ������ä
     *@param startdate �� String �ͧ�ѹ����������㹡�ô֧������
     *@param enddate �� String �ͧ�ѹ�������ش㹡�ô֧������
	 *@param startcheck �� String �ͧ�ѹ����������㹡�õ�Ǩ�ͺ�����Ť�����㹻�
     *@param endcheck �� String �ͧ�ѹ�������ش㹡�õ�Ǩ�ͺ�����Ť�����㹻�
     *@param isJan �� boolean ����Ѻ��Ǩ�ͺ�������͹ ���Ҥ� �������
     *@return Vector �ͧ�����š�õ�Ǩ�׹�ѹ������ä
     *@Author pu
     *@date 23/03/2006
     */
    public Vector selectGroup4CheckConfirmByDate(String startdate,String enddate,String startcheck,String endcheck,boolean isJan)throws Exception
    {
       
        String sql = IOStream.readInputDefault("config/rp_standard/standard_1105_part4_confirm.sql");
            PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
            pm.setString(1,startdate);
            pm.setString(2,startdate);
            pm.setString(3,startdate);
            pm.setString(4,startdate);
            pm.setString(5,startdate);
            pm.setString(6,enddate);
//            System.out.println(pm.toString());
            echeckConfirmQuery(pm.executeQuery());

          sql = null;   
        if(this.vReport.size()==0)
        {
            return null;
        }
        else
        {
            return this.vReport;
        }
    }
    
    /**
     *��¡�á������ش��õ�駤���� ���ͧ�ҡ��á㹤������ Thalassemia
     *@param startdate �� String �ͧ�ѹ����������㹡�ô֧������
     *@param enddate �� String �ͧ�ѹ�������ش㹡�ô֧������
     *@return Vector �ͧ�����š������ش��õ�駤����
     *@Author pu
     *@date 23/03/2006
     */
    public Vector selectGroup4AbortPregnantByDate(String startdate,String enddate)throws Exception
    {        
        
        String sql = IOStream.readInputDefault("config/rp_standard/standard_1105_part4_pregnancy.sql");
            PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
            pm.setString(1,startdate);
            pm.setString(2,enddate);
            pm.setString(3,startdate);
            pm.setString(4,enddate);
            eAbortPregnant(pm.executeQuery());

          sql = null;   
        if(this.vReport.size()==0)
        {
            return null;
        }
        else
        {
            return this.vReport;
        }
    }
    
    /**
     *��¡�á���ѡ�� �� 0-1 ��͹��躡���ͧ���ʹչ������´�
     *@param startdate �� String �ͧ�ѹ����������㹡�ô֧������
     *@param enddate �� String �ͧ�ѹ�������ش㹡�ô֧������
	 *@param isJan �� boolean ����Ѻ��Ǩ�ͺ�������͹ ���Ҥ� ������
     *@return Vector �ͧ�����š���ѡ�� �� 0-1 ��͹��躡���ͧ���ʹչ������´�
     *@Author pu
     *@date 23/03/2006
     */
    public Vector selectGroup4TreatInfantByDate(String startdate,String enddate,String startcheck,String endcheck,boolean isJan)throws Exception
    {        
        
        String sql = IOStream.readInputDefault("config/rp_standard/standard_1105_part4_TreatInfant.sql");
            PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
            pm.setString(1,startdate);
            pm.setString(2,enddate);
            eTreatInfant(pm.executeQuery());

          sql = null; 
        if(this.vReport.size()==0)
        {
            return null;
        }
        else
        {
            return this.vReport;
        }
    }
    /**
     *��¡�èӹǹ����㹡������ԡ�ÿ�鹿����ö�Ҿ
     *@param startdate �� String �ͧ�ѹ����������㹡�ô֧������
     *@param enddate �� String �ͧ�ѹ�������ش㹡�ô֧������
     *@return Vector �ͧ�����š������ԡ�ÿ�鹿����ö�Ҿ
     *@Author pu
     *@date 23/03/2006
     */
    public Vector selectGroup4EfficiencyByDate(String startdate,String enddate) throws Exception
    {
        
        String sql = IOStream.readInputDefault("config/rp_standard/standard_1105_part4_Efficiency.sql");
            PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
            pm.setString(1,startdate);
            pm.setString(2,enddate);
            pm.setString(3,startdate);
            pm.setString(4,enddate);
            eEfficiency(pm.executeQuery()); 

          sql = null;
        if(this.vReport.size()==0)
        {
            return null;
        }
        else
        {
            return this.vReport;
        }
    }
    
    
    /**
     *��¡�èӹǹ����Ѻ��ԡ�á����������ҹ
     *@param startdate �� String �ͧ�ѹ����������㹡�ô֧������
     *@param enddate �� String �ͧ�ѹ�������ش㹡�ô֧������
     *@return Vector �ͧ�����š����������ҹ
     *@Author pu
     *@date 23/03/2006
     */
    public Vector selectGroup4PersonAmountByDate(String startdate,String enddate)throws Exception
    {        
       
        String sql = IOStream.readInputDefault("config/rp_standard/standard_1105_part4_PersonAmount.sql");
            PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
            pm.setString(1,startdate);
            pm.setString(2,enddate);
            pm.setString(3,startdate);
            pm.setString(4,enddate);
            ePersonAmount(pm.executeQuery()); 

          sql = null;
        if(this.vReport.size()==0)
        {
            return null;
        }
        else
        {
            return this.vReport;
        }
    }
    
    /**
     *��ô֧��§ҹ�ӹǹ�������͹��� ��ҡ������������ҹ
     *@param startdate �� String �ͧ�ѹ����������㹡�ô֧������
     *@param enddate �� String �ͧ�ѹ�������ش㹡�ô֧������
     *@return Vector �ͧ�����Ũӹǹ�������͹��� ��ҡ������������ҹ
     *@Author pu
     *@date 23/03/2006
     */
    public Vector selectGroup4HomeAmountByDate(String startdate,String enddate) throws Exception
    {
        
        String sql = IOStream.readInputDefault("config/rp_standard/standard_1105_part4_HomeAmount.sql");
            PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
            pm.setString(1,startdate);
            pm.setString(2,enddate);
            eHomeAmount(pm.executeQuery()); 

          sql = null;
        if(this.vReport.size()==0)
        {
            return null;
        }
        else
        {
            return this.vReport;
        }
    }
    
    /**
     *��ô֧��§ҹ�ӹǹ����㹡���͡˹���͹�����ç���¹
     *@param startdate �� String �ͧ�ѹ����������㹡�ô֧������
     *@param enddate �� String �ͧ�ѹ�������ش㹡�ô֧������
     *@return Vector �ͧ�����Ũӹǹ����㹡���͡˹���͹�����ç���¹
     *@Author pu
     *@date 23/03/2006
     */
    public Vector selectGroup4SchoolOutSiteByDate(String startdate,String enddate) throws Exception
    {
      
        String sql = IOStream.readInputDefault("config/rp_standard/standard_1105_part4_SchoolOutSite.sql");
            PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
            pm.setString(1,startdate);
            pm.setString(2,enddate);
            eSchoolOutSite(pm.executeQuery()); 

          sql = null;
        if(this.vReport.size()==0)
        {
            return null;
        }
        else
        {
            return this.vReport;
        }
    }
       
    
    /**
     *��ô֧��§ҹ��õ�Ǩ�Ѵ��ͧ
     *@param SQL �� String ����纤���� sql ����Ѻ�֧������
     *@Author pu
     *@date 22/03/2006
     */
    private void eAssortQuery(ResultSet rs)
    {
    
        try{
        //rs = theConnectionInf.eQuery(sql);
        this.vReport= new Vector();
        theRP115Group4Assort_2549 = new RP115Group4Assort_2549();
            while(rs.next()) 
            {
                theRP115Group4Assort_2549 = new RP115Group4Assort_2549();
                
                theRP115Group4Assort_2549.plan_type = rs.getString(1);
                theRP115Group4Assort_2549.womb_cancer = rs.getString(2);                
                theRP115Group4Assort_2549.breast_cancer = rs.getString(3); 
                theRP115Group4Assort_2549.thalassemia = rs.getString(4);        
                theRP115Group4Assort_2549.thalassemia_mom = rs.getString(5);
                theRP115Group4Assort_2549.iodine_thiroid = rs.getString(6);
                  
                this.vReport.add(theRP115Group4Assort_2549);
                theRP115Group4Assort_2549 = null;
            }            
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }        
    }
    
    /**
     *��ô֧��§ҹ��õ�Ǩ�׹�ѹ������ä
     *@param SQL �� String ����纤���� sql ����Ѻ�֧������
     *@Author pu
     *@date 23/03/2006
     */
    private void echeckConfirmQuery(ResultSet rs)
    {
    
        try{
        //rs = theConnectionInf.eQuery(sql);
        this.vReport= new Vector();
        theRP115Group4CheckConfirm_2549 = new RP115Group4CheckConfirm_2549();
            while(rs.next()) 
            {
                theRP115Group4CheckConfirm_2549 = new RP115Group4CheckConfirm_2549();
                
                theRP115Group4CheckConfirm_2549.plan_type = rs.getString(1);
                theRP115Group4CheckConfirm_2549.thalassemia_infant = rs.getString(2);                
                theRP115Group4CheckConfirm_2549.thiroid = rs.getString(3); 
                 
                this.vReport.add(theRP115Group4CheckConfirm_2549);
                theRP115Group4CheckConfirm_2549 = null;
            }            
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }        
    }
    
    /**
     *��ô֧��§ҹ�������ش��õ�駤�������ͧ�ҡ��á㹤������ Thalassemia 
     *@param SQL �� String ����纤���� sql ����Ѻ�֧������
     *@Author pu
     *@date 23/03/2006
     */
    private void eAbortPregnant(ResultSet rs)
    {
    
        try{
        //rs = theConnectionInf.eQuery(sql);
        this.vReport= new Vector();
        theRP115Group4AbortPregnant_2549 = new RP115Group4AbortPregnant_2549();
            while(rs.next()) 
            {
                theRP115Group4AbortPregnant_2549 = new RP115Group4AbortPregnant_2549();
                
                theRP115Group4AbortPregnant_2549.plan_type = rs.getString(1);
                theRP115Group4AbortPregnant_2549.terminated_pregnance = rs.getString(2);                
                
                this.vReport.add(theRP115Group4AbortPregnant_2549);
                theRP115Group4AbortPregnant_2549 = null;
            }            
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }        
    }
    
    /**
     *��ô֧��§ҹ�� 0-1 ��͹���Ѻ����ѡ�����к����ͧ���ʹչ������´�
     *@param SQL �� String ����纤���� sql ����Ѻ�֧������
     *@Author pu
     *@date 23/03/2006
     */
    private void eTreatInfant(ResultSet rs)
    {
        
        try
        {
        //rs = theConnectionInf.eQuery(sql);
        this.vReport= new Vector();
        theRP115Group4TreatInfant_2549 = new RP115Group4TreatInfant_2549();
            while(rs.next())
            {
                theRP115Group4TreatInfant_2549 = new RP115Group4TreatInfant_2549();
                
                theRP115Group4TreatInfant_2549.plan_type = rs.getString(1);
                theRP115Group4TreatInfant_2549.treat_thiroid = rs.getString(2);
                
                this.vReport.add(theRP115Group4TreatInfant_2549);
                theRP115Group4TreatInfant_2549 = null;
            }
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        } 
    }
    
    /**
     *��ô֧��§ҹ�ӹǹ���駷������ԡ�ÿ�鹿����ö�Ҿ
     *@param SQL �� String ����纤���� sql ����Ѻ�֧������
     *@Author pu
     *@date 23/03/2006
     */
    private void eEfficiency(ResultSet rs)
    {
        
        try
        {
       // rs = theConnectionInf.eQuery(sql);
        this.vReport= new Vector();
        theRP115Group4Efficiency_2549 = new RP115Group4Efficiency_2549();
            while(rs.next())
            {
                theRP115Group4Efficiency_2549 = new RP115Group4Efficiency_2549();
                
                theRP115Group4Efficiency_2549.plan_type = rs.getString(1);
                theRP115Group4Efficiency_2549.efficiency = rs.getString(2);
                
                this.vReport.add(theRP115Group4Efficiency_2549);
                theRP115Group4Efficiency_2549 = null;
            }
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    /**
     *��ô֧��§ҹ�ӹǹ����Ѻ��ԡ�á����������ҹ
     *@param SQL �� String ����纤���� sql ����Ѻ�֧������
     *@Author pu
     *@date 23/03/2006
     */
    private void ePersonAmount(ResultSet rs)
    {
        
        try
        {
        //rs = theConnectionInf.eQuery(sql);
        this.vReport= new Vector();
        theRP115Group4PersonAmount_2549 = new RP115Group4PersonAmount_2549();
            while(rs.next())
            {
                theRP115Group4PersonAmount_2549 = new RP115Group4PersonAmount_2549();
                
                theRP115Group4PersonAmount_2549.plan_type = rs.getString(1);
                theRP115Group4PersonAmount_2549.person = rs.getString(2);
                
                this.vReport.add(theRP115Group4PersonAmount_2549);
                theRP115Group4PersonAmount_2549 = null;
            }
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     *��ô֧��§ҹ�ӹǹ�������͹��� ��ҡ������������ҹ
     *@param SQL �� String ����纤���� sql ����Ѻ�֧������
     *@Author pu
     *@date 23/03/2006
     */
    private void eHomeAmount(ResultSet rs)
    {
        
        try
        {
        //rs = theConnectionInf.eQuery(sql);
        this.vReport= new Vector();
        theRP115Group4HomeAmount_2549 = new RP115Group4HomeAmount_2549();
            while(rs.next())
            {
                theRP115Group4HomeAmount_2549 = new RP115Group4HomeAmount_2549();
                
                theRP115Group4HomeAmount_2549.homeAmount = rs.getString(1);
            
                this.vReport.add(theRP115Group4HomeAmount_2549);
                theRP115Group4HomeAmount_2549 = null;
            }
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     *��ô֧��§ҹ�ӹǹ����㹡���͡˹���͹�����ç���¹
     *@param SQL �� String ����纤���� sql ����Ѻ�֧������
     *@Author pu
     *@date 23/03/2006
     */
    private void eSchoolOutSite(ResultSet rs)
    {
        
        try
        {
     //   rs = theConnectionInf.eQuery(sql);
        this.vReport= new Vector();
        theRP115Group4SchoolOutSite_2549 = new RP115Group4SchoolOutSite_2549();
            while(rs.next())
            {
                theRP115Group4SchoolOutSite_2549 = new RP115Group4SchoolOutSite_2549();
                
                theRP115Group4SchoolOutSite_2549.visit_school = rs.getString(1);
            
                this.vReport.add(theRP115Group4SchoolOutSite_2549);
                theRP115Group4SchoolOutSite_2549 = null;
            }
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    /**
     *  ��㹡�� Convert Object �� String �·���Ѻ ��� true ���� false �������ҧ��Ǥ�������������
     *  ����� true ������ʴ���Ǥ������ ����� Tab �繵�Ǥ��
     *  ����� false ������ʴ������Ǥ������ ����� Pipe �繵�Ǥ��
     *  @param vObject �� Vector �ͧ Obeject ��� RP115Group4_2549
     *  @param isGetColumnName �� Boolean ����� true ������ʴ���Ǥ������ ����� false ������ʴ������Ǥ������
     *  @param obj �� String ��������͵�Ǩ�ͺ��� Object � ���١������� Vector
     *  @return �� String �ͧ Sql ����ա���ŧ������º��������
     */
    public String convertToString(Vector vObject,boolean isGetColumnName)
    {
        StringBuffer sql = new StringBuffer();
        String separator = Constant.PIPE;
        System.out.println("------------- SIZE DATA ------------ " + vObject.size());
        if(vObject != null)
        {
                RP115Group4_2549 p = null;
                if(isGetColumnName)
                {
                    separator = Constant.TAB;
                    sql.append(Gutil.getTextBundle("TypePlan")+separator
                            +Gutil.getTextBundle("Womb_cancer")+separator
                            +Gutil.getTextBundle("Breast_cancer")+separator
                            +Gutil.getTextBundle("Thalassemia")+separator
                            +Gutil.getTextBundle("Thalassemia_infant")+separator
                            +Gutil.getTextBundle("Thalassemia_mom")+separator
                            +Gutil.getTextBundle("Terminated_pregnance")+separator
                            +Gutil.getTextBundle("Iodine_Thiroid")+separator
                            +Gutil.getTextBundle("Thiroid")+separator
                            +Gutil.getTextBundle("Treat_Thiroid")+separator
                            +Gutil.getTextBundle("Efficiency")+separator
                            +Gutil.getTextBundle("Person")
                            +Constant.NEWLINE);
                }
                for(int i=0;i<vObject.size();i++)
                {                    
                    p = (RP115Group4_2549)vObject.elementAt(i);
                    sql.append(p.plan_type + separator      
                            + p.womb_cancer + separator
                            + p.breast_cancer + separator
                            + p.thalassemia + separator
                            + p.thalassemia_infant + separator
                            + p.thalassemia_mom + separator
                            + p.terminated_pregnance + separator
                            + p.iodine_thiroid + separator
                            + p.thiroid + separator
                            + p.treat_thiroid + separator
                            + p.efficiency + separator
                            + p.person
                            + Constant.NEWLINE);
                    System.out.println("------------- DATA ------------ " + p.plan_type);
                }           
        }
        return sql.toString();
    }
}
