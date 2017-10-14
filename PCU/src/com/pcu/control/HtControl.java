/*
 * HtControl.java
 *
 * Created on 7 เมษายน 2549, 16:09 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.control;

import com.healthy.objdb.*; 
import com.hosv3.utility.DateUtil;
import java.util.*;
import com.hospital_os.utility.ConnectionDBMgr;
import com.healthy.object.*;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.utility.connection.UpdateStatus;
import com.hosv3.control.HosControl;
import com.hosv3.utility.Constant;


/**
 *
 * @author hospitalos5
 */
public class HtControl
{
    
    HtDB theHtDB;
    ConnectionDBMgr con;
    ConnectionInf theConnectionInf;
    public UpdateStatus theUS;
    HosControl theHosControl;
    /** Creates a new instance of HtControl */
    public HtControl()
    {
        con = new ConnectionDBMgr("jdbc:postgresql://localhost/hospital_osv3"
                ,"postgres"
                ,"postgres"
                , "0");
        theConnectionInf = con;
        theHtDB = new HtDB(theConnectionInf);
        Constant.println(con.toString());
        
    }
    public HtControl(ConnectionInf con,HosControl hc)
    {
        theConnectionInf = con;
        theHtDB = new HtDB(theConnectionInf);
        Constant.println(con.toString());
        theHosControl = hc;
    }
    public void setUpdateStatus(UpdateStatus us)
    {
        theUS = us;
    }
//////////////////////////////////////////////////////////////////////////
    
    public Vector listAgr()
    {
        theHtDB.theConnectionInf.open();
        try
        {
            return theHtDB.theAGRTypeDB.selectAll();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new Vector();
        }
        finally
        {
            theHtDB.theConnectionInf.close();
        }
    }
//////////////////////////////////////////////////////////////////////////
    public Vector listSex(String str)
    {
        theHtDB.theConnectionInf.open();
        try
        {
            return theHtDB.theSexDB.selectAll();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new Vector();
        }
        finally
        {
            theHtDB.theConnectionInf.close();
        }
    }
//////////////////////////////////////////////////////////////////////////
    public Vector listBehaviorHx()
    {
        theConnectionInf.open();
        try
        {
            return theHtDB.theBehaviorHxTypeDB.selectAll();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new Vector();
        }
        finally
        {
            theHtDB.theConnectionInf.close();
        }
    }
//////////////////////////////////////////////////////////////////////////
    public Vector listReason()
    {
        theHtDB.theConnectionInf.open();
        try
        {
            return theHtDB.theReasonTypeDB.selectAll();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new Vector();
        }
        finally
        {
            theHtDB.theConnectionInf.close();
        }
    }
//////////////////////////////////////////////////////////////////////////
    public Vector listCigaBehavior()
    {
        theHtDB.theConnectionInf.open();
        try
        {
            return theHtDB.theCigaDB.selectAll();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new Vector();
        }
        finally
        {
            theHtDB.theConnectionInf.close();
        }
    }
    //////////////////////////////////////
    public Vector listAlcoholBehavior()
    {
        theHtDB.theConnectionInf.open();
        try
        {
            return theHtDB.theAlcoholDB.selectAll();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new Vector();
        }
        finally
        {
            theHtDB.theConnectionInf.close();
        }
    }
//////////////////////////////////////////////////////////////////////////
    public Vector listSexBehavior()
    {
        theHtDB.theConnectionInf.open();
        try
        {
            return theHtDB.theSexBehaviorDB.selectAll();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new Vector();
        }
        finally
        {
            theHtDB.theConnectionInf.close();
        }
    }
//////////////////////////////////////////////////////////////////////////
    public Vector listMoodBehavior()
    {
        theHtDB.theConnectionInf.open();
        try
        {
            return theHtDB.theMoodBehaviorDB.selectAll();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new Vector();
        }
        finally
        {
            theHtDB.theConnectionInf.close();
        }
    }
//////////////////////////////////////////////////////////////////////////
    public Vector listAccidentProtect()
    {
        theHtDB.theConnectionInf.open();
        try
        {
            return theHtDB.theAccidentProtectTypeDB.selectAll();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new Vector();
        }
        finally
        {
            theHtDB.theConnectionInf.close();
        }
    }
//////////////////////////////////////////////////////////////////////////
    public Vector listChronic()
    {
        theHtDB.theConnectionInf.open();
        try
        {
            return theHtDB.theICD10ChronicDB.selectChronicComboFix();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new Vector();
        }
        finally
        {
            theHtDB.theConnectionInf.close();
        }
    }
//////////////////////////////////////////////////////////////////////////
    public Vector listSchool()
    {
        theHtDB.theConnectionInf.open();
        try
        {
            return theHtDB.theSchoolDB.selectAll();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new Vector();
        }
        finally
        {
            theHtDB.theConnectionInf.close();
        }
    }
//save ,delet ของ ElderAgr
    public void saveElderAgr(ElderAgr elderAgr)
    {
        if (elderAgr.idy.equals(""))
        {
            theUS.setStatus(Constant.getTextBundle("กรุณากรอกปีงบประมาณ"),UpdateStatus.WARNING);
            return;
        }
        
        theConnectionInf.open();
        try
        {
            if(elderAgr.getObjectId()==null)
            {
                //appointment.appointmenter = theHO.theEmployee.getObjectId();
                elderAgr.record_time = DateUtil.getTextDB(new Date(), true);
                elderAgr.modify_time = DateUtil.getTextDB(new Date(), true);
                theHtDB.theElderAgrDB.insert(elderAgr);
                theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลเสร็จสิ้น"),UpdateStatus.COMPLETE);
            }
            else
            {
                elderAgr.modify_time = DateUtil.getTextDB(new Date(), true);
                theHtDB.theElderAgrDB.update(elderAgr);
                theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลเสร็จสิ้น"),UpdateStatus.COMPLETE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลคัดกรองผิดพลาด"),UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public void deleteElderAgr(ElderAgr elderAgr)
    {
        theConnectionInf.open();
        try
        {
            theHtDB.theElderAgrDB.delete(elderAgr);
            theUS.setStatus(Constant.getTextBundle("การลบข้อมูลคัดกรองเสร็จสิ้น"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus(Constant.getTextBundle("การลบข้อมูลคัดกรองผิดพลาด"),UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    // save, delete ของ Behavior
    public void saveBehavior(Behavior behavior)
    {
        if (behavior.idy.equals(""))
        {
            theUS.setStatus(Constant.getTextBundle("กรุณากรอกปีงบประมาณ"),UpdateStatus.WARNING);
            return;
        }
        
        theConnectionInf.open();
        try
        {
            if(behavior.getObjectId()==null)
            {
                //appointment.appointmenter = theHO.theEmployee.getObjectId();
                behavior.record_time = DateUtil.getTextDB(new Date(), true);
                behavior.modify_time = DateUtil.getTextDB(new Date(), true);
                theHtDB.theBehaviorDB.insert(behavior);
                theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลเสร็จสิ้น"),UpdateStatus.COMPLETE);
            }
            else
            {
                behavior.modify_time = DateUtil.getTextDB(new Date(), true);
                theHtDB.theBehaviorDB.update(behavior);
                theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลเสร็จสิ้น"),UpdateStatus.COMPLETE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลคัดกรองผิดพลาด"),UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public void deleteBehavior(Behavior behavior)
    {
        theConnectionInf.open();
        try
        {
            theHtDB.theBehaviorDB.delete(behavior);
            theUS.setStatus(Constant.getTextBundle("การลบข้อมูลคัดกรองเสร็จสิ้น"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลคัดกรองผิดพลาด"),UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    /**
     *@author: sumo
     *@date: 30/08/2549
     *@see : บันทึก-แก้ไขข้อมูล To_be_number_one
     *@param : Object ToBeOne
     *@return void
     */
    public void saveToBeOne(ToBeOne toBeOne)
    {
        theConnectionInf.open();
        try
        {
            if(toBeOne.getObjectId()==null)
            {
                //appointment.appointmenter = theHO.theEmployee.getObjectId();
                toBeOne.record_time = DateUtil.getTextDB(new Date(), true);
                toBeOne.modify_time = DateUtil.getTextDB(new Date(), true);
                // เพิ่มการเก็บข้อมูลผู้บันทึก sumo 30/08/2549
                toBeOne.staff_record = theHosControl.theHO.theEmployee.getObjectId();
                theHtDB.theToBeOneDB.insert(toBeOne);
            }
            else
            {
                toBeOne.modify_time = DateUtil.getTextDB(new Date(), true);
                // เพิ่มการเก็บข้อมูลผู้แก้ไข sumo 30/08/2549
                toBeOne.staff_modify = theHosControl.theHO.theEmployee.getObjectId();
                theHtDB.theToBeOneDB.update(toBeOne);
            }
            theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลเสร็จสิ้น"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลคัดกรองผิดพลาด"),UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    /**deprecate ไม่ได้ใช้แล้ว sumo 30/08/2549 */
    public void deleteToBeOne(ToBeOne toBeOne)
    {
        theConnectionInf.open();
        try
        {
            theHtDB.theToBeOneDB.delete(toBeOne);
            theUS.setStatus(Constant.getTextBundle("การลบข้อมูลคัดกรองเสร็จสิ้น"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus(Constant.getTextBundle("การลบข้อมูลคัดกรองผิดพลาด"),UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    // save, delete ของ Diabetes
    public void saveDiabetes(Diabetes diabetes)
    {
        if (diabetes.idy.equals(""))
        {
            
            theUS.setStatus(Constant.getTextBundle("กรุณากรอกปีงบประมาณ"),UpdateStatus.WARNING);
            return;
        }
        
        theConnectionInf.open();
        try
        {
            if(diabetes.getObjectId()==null)
            {
                //appointment.appointmenter = theHO.theEmployee.getObjectId();
                diabetes.record_time = DateUtil.getTextDB(new Date(), true);
                diabetes.modify_time = DateUtil.getTextDB(new Date(), true);
                theHtDB.theDiabetesDB.insert(diabetes);
                theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลเสร็จสิ้น"),UpdateStatus.COMPLETE);
            }
            else
            {
                diabetes.modify_time = DateUtil.getTextDB(new Date(), true);
                theHtDB.theDiabetesDB.update(diabetes);
                theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลเสร็จสิ้น"),UpdateStatus.COMPLETE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลคัดกรองผิดพลาด"),UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public void deleteDiabetes(Diabetes diabetes)
    {
        theConnectionInf.open();
        try
        {
            theHtDB.theDiabetesDB.delete(diabetes);
            theUS.setStatus(Constant.getTextBundle("การลบข้อมูลคัดกรองเสร็จสิ้น"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus(Constant.getTextBundle("การลบข้อมูลคัดกรองผิดพลาด"),UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    //save, delete ของ Exercise
    public void saveExercise(Exercise exercise)
    {
        if (exercise.idy.equals(""))
        {
            theUS.setStatus(Constant.getTextBundle("กรุณากรอกปีงบประมาณ"),UpdateStatus.WARNING);
            return;
        }
        
        theConnectionInf.open();
        try
        {
            if(exercise.getObjectId()==null)
            {
                //appointment.appointmenter = theHO.theEmployee.getObjectId();
                exercise.record_time = DateUtil.getTextDB(new Date(), true);
                exercise.modify_time = DateUtil.getTextDB(new Date(), true);
                theHtDB.theExerciseDB.insert(exercise);
                theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลเสร็จสิ้น"),UpdateStatus.COMPLETE);
            }
            else
            {
                exercise.modify_time = DateUtil.getTextDB(new Date(), true);
                theHtDB.theExerciseDB.update(exercise);
                theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลเสร็จสิ้น"),UpdateStatus.COMPLETE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลคัดกรองผิดพลาด"),UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public void deleteExercise(Exercise exercise)
    {
        
        theConnectionInf.open();
        try
        {
            theHtDB.theExerciseDB.delete(exercise);
            theUS.setStatus(Constant.getTextBundle("การลบข้อมูลคัดกรองเสร็จสิ้น"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus(Constant.getTextBundle("การลบข้อมูลคัดกรองผิดพลาด"),UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
// save, delete ของ Pressure
    public void savePressure(Pressure pressure)
    {
        if (pressure.idy.equals(""))
        {
            theUS.setStatus(Constant.getTextBundle("กรุณากรอกปีงบประมาณ"),UpdateStatus.WARNING);
            return;
        }
        
        theConnectionInf.open();
        try
        {
            if(pressure.getObjectId()==null)
            {
                //appointment.appointmenter = theHO.theEmployee.getObjectId();
                pressure.record_time = DateUtil.getTextDB(new Date(), true);
                pressure.modify_time = DateUtil.getTextDB(new Date(), true);
                theHtDB.thePressureDB.insert(pressure);
                theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลเสร็จสิ้น"),UpdateStatus.COMPLETE);
            }
            else
            {
                pressure.modify_time = DateUtil.getTextDB(new Date(), true);
                theHtDB.thePressureDB.update(pressure);
                theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลเสร็จสิ้น"),UpdateStatus.COMPLETE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลคัดกรองผิดพลาด"),UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public void deletePressure(Pressure pressure)
    {
        theConnectionInf.open();
        try
        {
            theHtDB.thePressureDB.delete(pressure);
            theUS.setStatus(Constant.getTextBundle("การลบข้อมูลคัดกรองเสร็จสิ้น"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theUS.setStatus(Constant.getTextBundle("การลบข้อมูลคัดกรองผิดพลาด"),UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public Vector listToBeOneByFid(String family_id)
    {
        if (family_id == null)
        {
            //theUS.setStatus(Constant.getTextBundle("กรุณากรอกปีงบประมาณ"),UpdateStatus.WARNING);
            return null;
        }
        
        theConnectionInf.open();
        try
        {
            
            return theHtDB.theToBeOneDB.selectByFamily(family_id);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public Vector listBehaviorByFid(String family_id)
    {
        if (family_id == null)
        {
            //theUS.setStatus(Constant.getTextBundle("กรุณากรอกปีงบประมาณ"),UpdateStatus.WARNING);
            return null;
        }
        
        theConnectionInf.open();
        try
        {
            
            return theHtDB.theBehaviorDB.selectByFamily(family_id);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public Vector listDiabetesByFid(String family_id)
    {
        if (family_id == null)
        {
            //theUS.setStatus(Constant.getTextBundle("กรุณากรอกปีงบประมาณ"),UpdateStatus.WARNING);
            return null;
        }
        
        theConnectionInf.open();
        try
        {
            
            return theHtDB.theDiabetesDB.selectByFamily(family_id);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public Vector listElderAgrByFid(String family_id)
    {
        if (family_id == null)
        {
            //theUS.setStatus(Constant.getTextBundle("กรุณากรอกปีงบประมาณ"),UpdateStatus.WARNING);
            return null;
        }
        
        theConnectionInf.open();
        try
        {
            
            return theHtDB.theElderAgrDB.selectByFamily(family_id);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public Vector listExerciseByFid(String family_id)
    {
        if (family_id == null)
        {
            //theUS.setStatus(Constant.getTextBundle("กรุณากรอกปีงบประมาณ"),UpdateStatus.WARNING);
            return null;
        }
        
        theConnectionInf.open();
        try
        {
            
            return theHtDB.theExerciseDB.selectByFamily(family_id);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public Vector listPressureByFid(String family_id)
    {
        if (family_id == null)
        {
            //theUS.setStatus(Constant.getTextBundle("กรุณากรอกปีงบประมาณ"),UpdateStatus.WARNING);
            return null;
        }
        
        theConnectionInf.open();
        try
        {
            
            return theHtDB.thePressureDB.selectByFamily(family_id);
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public String getYear()
    {
        return com.hospital_os.utility.DateUtil.getTextCurrentDate(theConnectionInf).substring(0, 4);
    }
}
