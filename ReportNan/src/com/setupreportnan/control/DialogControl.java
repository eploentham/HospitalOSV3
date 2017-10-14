/*
 * DialogControl.java
 *
 * Created on 13 มิถุนายน 2549, 16:02 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreportnan.control;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.setupreportnan.control.ManageDB;
import java.util.Vector;
/**
 *
 * @author pu
 */
public class DialogControl
{
    private ConnectionInf theConnectionInf;
    private ManageDB theManageDB;
    private int iresult;
    private Vector vData;
    private Vector vDataQuery;

    /**
     * Creates a new instance of DialogControl 
     */
    public DialogControl(ManageDB mdb)
    {
        theManageDB = mdb;
        theConnectionInf = theManageDB.theConnectionInf;
    }
    
    /**
     * สำหรับค้นหารายชื่อจุดบริการ
     * @Param keyword เป็นตัวแปรที่เก็บค่าคำค้นที่ผู้ใช้กรอก
     * @Return Vector ของ ServicePoint
     * @Author pu
     * @Date 13/06/2006
     **/
    public Vector selectServicePointByKeyword(String keyword,String active)
    {
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = (Vector)theManageDB.theServicePointNanDB.selectBySearch(keyword,active);
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
     * สำหรับตรวจสอบว่าจะค้นหาข้อมูลด้วย Function ไหน
     * @Param keyword คือ ค่าที่ผู้ใช้กรอกเพื่อค้นหารายการ
     *        offsetPage คือ 8jk เริ่มต้นของการค้นหา
     * @Author ojika
     * @Date 20/06/2549
     */
    public Vector selectItemByKeyword(String keyword,int offsetPage)
    {
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            if(!keyword.equalsIgnoreCase(""))
            {
                // เมื่อมีค่าใน keyword ที่ผู้ใช้กรอก
                vData = (Vector)theManageDB.theItemNanDB.selectBySearch(keyword,offsetPage); 
            }
            else
            {
                // เมื่อไม่มี keyword ที่ผู้ใช้กรอก
                vData = (Vector)theManageDB.theItemNanDB.selectAll(offsetPage);
            }
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
     * สำหรับค้นหา Office
     * @Param keyword คือ ค่าที่ผู้ใช้กรอกเพื่อค้นหารายการ
     *        offsetPage คือ 8jk เริ่มต้นของการค้นหา
     * @Author ojika
     * @Date 21/06/2549
     */
    public Vector selectOfficeByKeyword(String keyword,int offsetPage)
    {
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            // เมื่อมีค่าใน keyword ที่ผู้ใช้กรอก
            vData = (Vector)theManageDB.theOfficeNanDB.selectBySearch(keyword,offsetPage);             
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
}
