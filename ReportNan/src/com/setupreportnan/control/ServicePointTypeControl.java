/*
 * ServicePointTypeControl.java
 *
 * Created on 14 มิถุนายน 2549, 10:46 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreportnan.control;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.setupreportnan.control.ManageDB;
import com.reportnan.object.RPNanServicePointType;
import com.reportnan.object.RPNanServicePointTypeMap;
import java.util.Vector;
/**
 *
 * @author pu
 */
public class ServicePointTypeControl
{
    ConnectionInf theConnectionInf;
    ManageDB theManageDB;
    int iresult =0;
    Vector vData;
    RPNanServicePointType theRPNanServicePointType;

    /**
     * Creates a new instance of ServicePointTypeControl 
     */
    public ServicePointTypeControl(ManageDB mdb)
    {
        theManageDB = mdb;
        theConnectionInf = this.theManageDB.theConnectionInf;        
    }
    
    /**
     * สำหรับค้นหาประเภทจุดบริการ
     * @Param keyword เป็นตัวแปรที่เก็บค่าคำค้นที่ผู้ใช้กรอก
     * @Return Vector ของ ServicePointType
     * @Author Pu
     * @Date 14/06/2006
     **/
    public Vector selectServicePointTypeByKeyword(String keyword)
    {
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = (Vector)theManageDB.theServicePointTypeDB.selectBySearch(keyword);
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
     * สำหรับบันทึกประเภทจุดบริการ
     * @Param obj เป็น Object ของประเภทจุดบริการ
     * @Return int เพื่อบอกสถานะการบันทึกรายการ
     * @Author Pu
     * @Date 14/06/2006
     **/
    public int saveServicePointType(RPNanServicePointType obj)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theManageDB.theServicePointTypeDB.insertData(obj);
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
     * สำหรับลบประเภทจุดบริการ
     * @Param obj เป็น Object ของประเภทจุดบริการ
     * @Return int เพื่อบอกสถานะการลบรายการ
     * @Author Pu
     * @Date 14/06/2006
     **/
    public int deleteServicePointTypeByKeyId(RPNanServicePointType obj)
    {
        iresult = 0;
        try
        {
            theConnectionInf.open();
            iresult = theManageDB.theServicePointTypeDB.deleteByKeyID(obj.getObjectId());
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
