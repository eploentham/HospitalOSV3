/*
 * ServicePointTypeMapControl.java
 *
 * Created on 14 �Զع�¹ 2549, 11:56 �.
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
     * ����Ѻ���һ������ش��ԡ�÷�� map ����
     * @Param keyword �繵���÷���纤�ҤӤ鹷�������͡
     * @Return Vector �ͧ ServicePointTypeMap
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
     * ����Ѻ�ѹ�֡�������ش��ԡ�÷�� map ����
     * @Param obj �� Object �ͧ�������ش��ԡ�÷�� map ����
     * @Return int ���ͺ͡ʶҹС�úѹ�֡��¡��
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
     * ����Ѻź�������ش��ԡ�÷�� map
     * @Param obj �� Object �ͧ�������ش��ԡ�÷�� map
     * @Return int ���ͺ͡ʶҹС��ź��¡��
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
