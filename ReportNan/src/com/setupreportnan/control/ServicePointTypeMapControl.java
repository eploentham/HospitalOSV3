/*
 * ServicePointTypeMapControl.java
 *
 * Created on 14 มิถุนายน 2549, 11:56 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreportnan.control;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.setupreportnan.control.ManageDB;
import com.reportnan.object.RPNanServicePointTypeMap;
import java.util.Vector;
/**
 *
 * @author pu
 */
public class ServicePointTypeMapControl
{
    ConnectionInf theConnectionInf;
    ManageDB theManageDB;
    int iresult =0;
    Vector vData;
    RPNanServicePointTypeMap theRPNanServicePointTypeMap;
    /** Creates a new instance of ServicePointTypeMapControl */
    public ServicePointTypeMapControl(ManageDB mdb)
    {
        theManageDB = mdb;
        theConnectionInf = this.theManageDB.theConnectionInf;    
    }
    
    /**
     * สำหรับค้นหาประเภทจุดบริการที่ map แล้ว
     * @Param keyword เป็นตัวแปรที่เก็บค่าคำค้นที่ผู้ใช้กรอก
     * @Return Vector ของ ServicePointTypeMap
     * @Author Pu
     * @Date 14/06/2006
     **/
    public Vector selectServicePointTypeMapByServicePointTypeId(String servicePointTypeId)
    {
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = (Vector)theManageDB.theServicePointTypeMapDB.selectByServicePointTypeId(servicePointTypeId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        
        return vData;
    }
    /**
     * สำหรับบันทึกประเภทจุดบริการที่ map แล้ว
     * @Param obj เป็น Object ของประเภทจุดบริการที่ map แล้ว
     * @Return int เพื่อบอกสถานะการบันทึกรายการ
     * @Author Pu
     * @Date 14/06/2006
     **/
    public int saveServicePointTypeMap(RPNanServicePointTypeMap obj)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theManageDB.theServicePointTypeMapDB.insertData(obj);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        
        return iresult;
    }
    /**
     * สำหรับลบประเภทจุดบริการที่ map
     * @Param obj เป็น Object ของประเภทจุดบริการที่ map
     * @Return int เพื่อบอกสถานะการลบรายการ
     * @Author Pu
     * @Date 14/06/2006
     **/
    public int deleteServicePointTypeMapByKeyId(RPNanServicePointTypeMap obj)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theManageDB.theServicePointTypeMapDB.deleteByKeyID(obj.getObjectId());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }        
        return iresult;
    }
}
