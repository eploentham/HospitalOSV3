/*
 * SystemControl.java
 *
 * Created on 12 ตุลาคม 2548, 13:23 น.
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
     *select จุดบริการ จากฐานข้อมูล สำหรับเซ็ตค่าให้กับ ComboBox
     *@return Vector ที่เก็บรายชื่อจุดบริการทั้งหมด
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
     *select ชื่อแพทย์ จากฐานข้อมูล สำหรับเซ็ตค่าให้กับ ComboBox
     *@return Vector ที่เก็บชื่อแพทย์
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
     *select รายชื่อแพทย์จากจุดตรวจ สำหรับเซ็ตค่าให้กับ ComboBox
     *@return Vector ที่เก็บชื่อแพทย์
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
     *เซ็ตค่าเริ่มต้นให้กับ ComboBox Doctor ให้เป็น ไม่ระบุชื่อแพทย์    
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
