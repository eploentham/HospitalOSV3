/*
 * SystemControl.java
 *
 * Created on 12 ���Ҥ� 2548, 13:23 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.control;
import com.hospital_os.usecase.connection.ConnectionInf;
import java.util.*;
import com.generalreport.objdb.HosDB;
import com.generalreport.object.*;
import com.generalreport.utility.Constant;
import com.generalreport.utility.ComboFix;
import com.generalreport.utility.Language;

/**
 *
 * @author americus
 */
public class SystemControl
{
    ConnectionInf theConnectionInf;
    HosDB theHosDB;
    Vector vc;
    /** Creates a new instance of SystemControl */
    public SystemControl(ConnectionInf conf,HosDB hdb)
    {
        theConnectionInf = conf;
        theHosDB = hdb;
    }
    /**
     *select �ش��ԡ�� �ҡ�ҹ������ ����Ѻ�絤�����Ѻ ComboBox
     *@return Vector �������ª��ͨش��ԡ�÷�����
     */    
    public Vector selectServicePointForComboBox()
    {
        vc = new Vector();
        theConnectionInf.open();
        try
        {
            vc = theHosDB.theSystemDB.selectServicePointForComboBox();
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
     *select ����ᾷ�� �ҡ�ҹ������ ����Ѻ�絤�����Ѻ ComboBox
     *@return Vector ����纪���ᾷ��
     */
    public Vector selectDoctorByServicePointID(String Pid)
    {
        vc = new Vector();
        theConnectionInf.open();
        try
        {
            vc = theHosDB.theSystemDB.selectDoctorByServicePointID(Pid);
            System.out.println("---- "+vc);
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
     *select ��ª���ᾷ��ҡ�ش��Ǩ ����Ѻ�絤�����Ѻ ComboBox
     *@return Vector ����纪���ᾷ��
     */
  /*  public Vector selectServicePointDoctor()
    {
        vc = new Vector();
        theConnectionInf.open();
        try
        {
            vc = theHosDB.theSystemDB.selectServicePointDoctor();
            System.out.println("---- "+vc);
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
        
   */
    /**
     *�絤������������Ѻ ComboBox Doctor ����� ����кت���ᾷ��    
     */
    public Vector getNonDoctor()
    {
        Vector vc = new Vector();
        
        ComboFix theComboFix = new ComboFix();
        theComboFix.code = "0000000000000";
        theComboFix.name = Language.getTextBundle("UnIdentify", 1);
        vc.add(theComboFix);
        
        return  vc;
    }
    
}
