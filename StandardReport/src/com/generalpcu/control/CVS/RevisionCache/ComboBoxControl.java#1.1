/*
 * ComboBoxControl.java
 *
 * Created on 10 กุมภาพันธ์ 2549, 16:56 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalpcu.control;

import java.util.*;
import com.hospital_os.usecase.connection.*;
import com.generalpcu.objdb.*;
import com.generalpcu.utility.ComboFix;
import com.generalpcu.utility.Language;
/**
 *
 * @author nu_ojika
 */
public class ComboBoxControl
{
    private ConnectionInf theConnectionInf;
    private ManageDB theManageDB;
    
    /** Vector ของหมู่บ้าน **/
    private Vector vVillage;
    /** Vector ของรายชื่อโรค */
    private Vector vDisease;
    /** Vector ของรายงานประชากร*/
    private Vector vResidentReport;
    private ComboFix theComboFixResidentReport;
    
    /** Vector ของช่วงอายุประชากร*/
    private Vector vAgePortion;
    
    /** Vector ของประเภทความพิการ*/
    private Vector vMaimType;
    private ComboFix theComboFixMaimType;
    
    /** Vector ของช่วงอายุการรรับวัคซีน*/
    private Vector vEpiAgeGroup;
    
    /** Creates a new instance of ComboBoxControl */
    public ComboBoxControl(ManageDB manageDB)
    {
        theManageDB = manageDB;
        theConnectionInf = theManageDB.theConnectionInf;       
        theConnectionInf.open();
        ComboFix theComboFixDisease = new ComboFix();
        ComboFix theComboFixVillage = new ComboFix();
        ComboFix theComboFixAgePortion = new ComboFix();
        ComboFix theComboFixMaimType = new ComboFix();
        try
        {  //เพิ่มหมู่บ้านทั้งหมด ใน ComboBox
            
            theComboFixVillage.code = "0";
            theComboFixVillage.name= Language.getTextBundle("AllVillage");        
            vVillage = theManageDB.theVillageDB.selectAll();
            if(vVillage == null)
            {
                vVillage = new Vector();
            }
            vVillage.add(theComboFixVillage);

            theComboFixDisease.code = "0";
            theComboFixDisease.name = Language.getTextBundle("AllDisease");
            vDisease = theManageDB.theDiseaseDB.selectAll();   
            if(vDisease == null)
            {
                vDisease = new Vector();
            }
            vDisease.add(theComboFixDisease);
            
            theComboFixAgePortion.code = "0";
            theComboFixAgePortion.name = Language.getTextBundle("AllAgePortion");
            vAgePortion = theManageDB.theResidentAgeGroupDB.selectAllComboFix();            
            if(vAgePortion == null)
            {
                vAgePortion = new Vector();
            }            
            vAgePortion.add(theComboFixAgePortion);
            
            theComboFixMaimType.code = "0";
            theComboFixMaimType.name = Language.getTextBundle("AllMaimType");
            vMaimType = theManageDB.theMaimTypeDB.selectAll();
            if(vMaimType == null)
            {
                vMaimType = new Vector();
            }
            vMaimType.add(theComboFixMaimType);
            
            vEpiAgeGroup = theManageDB.theEpiAgeGroupDB.selectAll();
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            theConnectionInf.close();
            theComboFixVillage = null;
            theComboFixDisease =null;
            theComboFixAgePortion = null;
            theComboFixMaimType = null;
        }
    }
    
    public Vector listVillage() 
    {
        ComboFix theComboFixVillage = new ComboFix();
        theComboFixVillage.code = "0";
        theComboFixVillage.name= Language.getTextBundle("AllVillage");
        if((vVillage != null))
        {
            return vVillage;
        }
        theConnectionInf.open();
        try
        {
            vVillage = new Vector();
            vVillage = theManageDB.theVillageDB.selectAll();
            vVillage.add(theComboFixVillage);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
            theComboFixVillage = null;
        }
        return vVillage;
    }
    
    /**
     *ดึงข้อมูลรายชื่อโรคจากฐานข้อมูล 
     *@return Vector ที่เก็บรายชื่อโรค
     *@Date 23/02/2006
     *@Author pu
     */
    public Vector listDisease()
    {
        ComboFix theComboFixVillage = new ComboFix();
        theComboFixVillage.code = "0";
        theComboFixVillage.name = Language.getTextBundle("AllDisease");
        if(vDisease != null)
        {
            return vDisease;
        }
        theConnectionInf.open();
        try
        {
            vDisease = new Vector();
            vDisease = theManageDB.theDiseaseDB.selectAll();
            vDisease.add(theComboFixVillage);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
            theComboFixVillage = null;
        }
        return vDisease;
    }
    
    /**
     *กำหนดรายงานย่อยใน Vector สำหรับการ set ComboBox 
     *@return Vector ของรายงานประชากร
     *@Date 01/03/2006
     *@Author pu
     */
    public Vector listResidenrReport()
    {        
        vResidentReport = new Vector();
        
        theComboFixResidentReport = new ComboFix();
        theComboFixResidentReport.code = "0";
        theComboFixResidentReport.name = Language.getTextBundle("Resident_AgePortion");
        vResidentReport.add(theComboFixResidentReport);
        theComboFixResidentReport = null;
        
        theComboFixResidentReport = new ComboFix();
        theComboFixResidentReport.code = "1";
        theComboFixResidentReport.name = Language.getTextBundle("Resident_Child0_5");
        vResidentReport.add(theComboFixResidentReport);
        theComboFixResidentReport = null;
        
        theComboFixResidentReport = new ComboFix();
        theComboFixResidentReport.code = "2";
        theComboFixResidentReport.name = Language.getTextBundle("Resident_Women_15Up");
        vResidentReport.add(theComboFixResidentReport);
        theComboFixResidentReport = null;
        
        theComboFixResidentReport = new ComboFix();
        theComboFixResidentReport.code = "3";
        theComboFixResidentReport.name = Language.getTextBundle("Resident_Death");
        vResidentReport.add(theComboFixResidentReport);
        theComboFixResidentReport = null;
        
        return vResidentReport;
    }
    
    /**
     *ดึงช่วงอายุจากฐานข้อมูล สำหรับออกรายงานประชากร ตามช่วงอายุ
     *@return Vector ของรายงานประชากร
     *@Date 03/03/2006
     *@Author pu
     */
    public Vector listAgePortion()
    {     
        ComboFix theComboFixAgePortion = new ComboFix();
        theComboFixAgePortion.code = "0";
        theComboFixAgePortion.name= Language.getTextBundle("AllAgePortion");
        if((vAgePortion != null))
        {
            return vAgePortion;
        }
        theConnectionInf.open();
        try
        {
            vAgePortion = new Vector();
            vAgePortion = theManageDB.theResidentAgeGroupDB.selectAllComboFix();
            vAgePortion.add(theComboFixAgePortion);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
            theComboFixAgePortion = null;
        }
        return vAgePortion;
    }
    
    /**
     *ดึงประเภทความพิการจากฐานข้อมูล สำหรับออกรายการผู้พิการ
     *@return Vector ของประเภทความพิการ
     *@Date 27/03/2006
     *@Author pu
     */
    public Vector listMaimType()
    {
        ComboFix theComboFixMaimType = new ComboFix();
        theComboFixMaimType.code = "0";
        theComboFixMaimType.name= Language.getTextBundle("AllMaimType");
        if(vMaimType != null)
        {
            return vMaimType;
        }
        theConnectionInf.open();
        try
        {
            vMaimType = new Vector();
            vMaimType = theManageDB.theResidentAgeGroupDB.selectAllComboFix();
            vMaimType.add(theComboFixMaimType);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
            theComboFixMaimType = null;
        }
        return vMaimType;
    }
    /**
     *อายุเริ่มต้น และอายุที่มากที่สุดของช่วงอายุทั้งหมด
     *@Author pu
     *@date 03/04/2006
     */
    public Vector getStartEndAge()
    {
        Vector vc = new Vector();
        String start[] = {""};
        String end[] = {""};
        if(this.vAgePortion != null && this.vAgePortion.size() > 1)
        {
            int size = this.vAgePortion.size();
            start = ((ComboFix)this.vAgePortion.get(0)).name.split("-");
            end = ((ComboFix)this.vAgePortion.get(size-2)).name.split("-");
            vc.add(0,start[0]);
            vc.add(1,end[1]);   
            return vc;
        }        
        else
            return null;
    }
    
    /**
     * ดึงข้อมูลรายชื่อโรคจากฐานข้อมูล 
     * @return Vector ที่เก็บช่วงอายุการรับวัคซีน
     * @Date 31/03/2006
     * @Author Ojika
     */
    public Vector listEpiAgeGroup()
    {
        if(vEpiAgeGroup != null)
        {
            return vEpiAgeGroup;
        }
        theConnectionInf.open();
        try
        {
            vEpiAgeGroup = new Vector();
            vEpiAgeGroup = theManageDB.theEpiAgeGroupDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vDisease;
    }
}
