/*
 * ServicePointTypeControl.java
 *
 * Created on 14 �Զع�¹ 2549, 10:46 �.
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
     * ����Ѻ���һ������ش��ԡ��
     * @Param keyword �繵���÷���纤�ҤӤ鹷�������͡
     * @Return Vector �ͧ ServicePointType
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
     * ����Ѻ�ѹ�֡�������ش��ԡ��
     * @Param obj �� Object �ͧ�������ش��ԡ��
     * @Return int ���ͺ͡ʶҹС�úѹ�֡��¡��
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
     * ����Ѻź�������ش��ԡ��
     * @Param obj �� Object �ͧ�������ش��ԡ��
     * @Return int ���ͺ͡ʶҹС��ź��¡��
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
