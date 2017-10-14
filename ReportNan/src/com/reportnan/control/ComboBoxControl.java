/*
 * ComboBoxControl.java
 *
 * Created on 10 กุมภาพันธ์ 2549, 16:56 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.control;

import java.util.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.objdb.SiteDB;
import com.reportnan.objdb.*;
import com.reportnan.utility.ComboFix;
import com.reportnan.utility.Language;
/**
 *
 * @author nu_ojika
 */
public class ComboBoxControl
{
    private ConnectionInf theConnectionInf;
    private ManageDB theManageDB;
    Vector vTambon;
    Vector vYear;
    ComboFix theComboFixMonth;
    Vector vMonth;
    Vector vServicePoint;
    String site_id;
    String site_amphur;
    String SQL ="";
    
     /** Creates a new instance of ComboBoxControl */
    public ComboBoxControl(ManageDB manageDB)
    {
        theManageDB = manageDB;
        theConnectionInf = theManageDB.theConnectionInf;       
        theConnectionInf.open();       
        try
        {  //เพิ่มตำบลทั้งหมด ใน ComboBox            
            site_id = SiteDB.getSiteID(theConnectionInf);
            this.site_amphur = theManageDB.thSiteDB.getSiteAmphur(site_id);
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            theConnectionInf.close();            
        }
    }
    
    /**
     *ลิสต์ตำบลทั้งหมดในเขตรับผิดชอบ
     *@return Vector ของตำบลในเขตรับผิดชอบ
     *@Author pu
     *@Date 06/06/2006
     */    
    public Vector listTambon() 
    {
        ComboFix theComboFixTambon = new ComboFix();
        theComboFixTambon.code = "0";
        theComboFixTambon.name= Language.getTextBundle("AllTambon");
        if((vTambon != null))
        {
            return vTambon;
        }
        theConnectionInf.open();
        try
        {
            vTambon = new Vector();
            vTambon = theManageDB.theTambonDB.selectAll(this.site_amphur);
            vTambon.add(theComboFixTambon);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
            theComboFixTambon = null;
        }
        System.out.println("Side vTambon" + vTambon.size());
        return vTambon;
    }
    
    
     /**
     *ลิสต์ปีของเข้ารับบริการว่ามีปีใดบ้าง
     *@return Vector ของปีทั้งหมดที่มี
     *@Author pu
     *@Date 12/06/2006
     */   
    public Vector listYear()
    {
        if((vYear != null))
        {
            return vYear;
        }
        theConnectionInf.open();
        try
        {
            vYear = new Vector();
            vYear = theManageDB.theGeneralDB.AllYear();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vYear;
    }
    /**
     *ลิสต์จุดบริการทั้งหมดมาแสดง
     *@return Vector ของจุดบริการ
     *@Author pu
     *@Date 14/06/2006
     */
    public Vector listServicePoint()
    {
        ComboFix theComboFixServicePoint = new ComboFix();
        theComboFixServicePoint.code = "0";
        theComboFixServicePoint.name= Language.getTextBundle("AllServicePoint");
        if((vServicePoint != null))
        {
            return vServicePoint;
        }
        theConnectionInf.open();
        try
        {
            vServicePoint = new Vector();
            vServicePoint = theManageDB.theServicePointNanDB.selectServicePointAll();
            vServicePoint.add(theComboFixServicePoint);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
            theComboFixServicePoint = null;
        }
        return vServicePoint;
    }
    
    /**
     *ลิสต์เดือน
     *@return Vector ของเดือน
     *@Author pu
     *@Date 15/06/2006
     */
    public Vector listMonth()
    {
        this.vMonth = new Vector();        
        theComboFixMonth = new ComboFix();        
        theComboFixMonth.code = "01";
        theComboFixMonth.name= Language.getTextBundle("January");
        this.vMonth.addElement(theComboFixMonth);
        theComboFixMonth = null;
        
        theComboFixMonth = new ComboFix();        
        theComboFixMonth.code = "02";
        theComboFixMonth.name= Language.getTextBundle("February");
        this.vMonth.addElement(theComboFixMonth);
        theComboFixMonth = null;
        
        theComboFixMonth = new ComboFix();        
        theComboFixMonth.code = "03";
        theComboFixMonth.name= Language.getTextBundle("March");
        this.vMonth.addElement(theComboFixMonth);
        theComboFixMonth = null;
        
        theComboFixMonth = new ComboFix();        
        theComboFixMonth.code = "04";
        theComboFixMonth.name= Language.getTextBundle("April");
        this.vMonth.addElement(theComboFixMonth);
        theComboFixMonth = null;
        
        theComboFixMonth = new ComboFix();        
        theComboFixMonth.code = "05";
        theComboFixMonth.name= Language.getTextBundle("May");
        this.vMonth.addElement(theComboFixMonth);
        theComboFixMonth = null;
        
        theComboFixMonth = new ComboFix();        
        theComboFixMonth.code = "06";
        theComboFixMonth.name= Language.getTextBundle("June");
        this.vMonth.addElement(theComboFixMonth);
        theComboFixMonth = null;
        
        theComboFixMonth = new ComboFix();        
        theComboFixMonth.code = "07";
        theComboFixMonth.name= Language.getTextBundle("July");
        this.vMonth.addElement(theComboFixMonth);
        theComboFixMonth = null;
        
        theComboFixMonth = new ComboFix();        
        theComboFixMonth.code = "08";
        theComboFixMonth.name= Language.getTextBundle("August");
        this.vMonth.addElement(theComboFixMonth);
        theComboFixMonth = null;
        
        theComboFixMonth = new ComboFix();        
        theComboFixMonth.code = "09";
        theComboFixMonth.name= Language.getTextBundle("September");
        this.vMonth.addElement(theComboFixMonth);
        theComboFixMonth = null;
        
        theComboFixMonth = new ComboFix();        
        theComboFixMonth.code = "10";
        theComboFixMonth.name= Language.getTextBundle("October");
        this.vMonth.addElement(theComboFixMonth);
        theComboFixMonth = null;
        
        theComboFixMonth = new ComboFix();        
        theComboFixMonth.code = "11";
        theComboFixMonth.name= Language.getTextBundle("November");
        this.vMonth.addElement(theComboFixMonth);
        theComboFixMonth = null;
        
        theComboFixMonth = new ComboFix();        
        theComboFixMonth.code = "12";
        theComboFixMonth.name= Language.getTextBundle("December");
        this.vMonth.addElement(theComboFixMonth);
        theComboFixMonth = null;
        
        return this.vMonth;
    }
}
