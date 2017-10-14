/*
 * ScreeningDiseaseControl.java
 *
 * Created on 14 มีนาคม 2549, 18:07 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.manage.control;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.setupreport.manage.control.HosDB;
import com.setupreport.object.*;
import java.util.Vector;
import com.hosv3.utility.connection.UpdateStatus;
/**
 *
 * @author pu
 */
public class ScreeningDiseaseControl
{
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    private int iresult;
    Vector vData;
    Vector vItem;
    Rp115Group4Disease theRp115Group4Disease;
    Rp115Group4Item theRp115Group4Item;
    UpdateStatus theUS;
    /** Creates a new instance of ScreeningDiseaseControl */
    public ScreeningDiseaseControl(HosDB hdb, UpdateStatus us)
    {
        theUS = us;
        theHosDB = hdb;
        theConnectionInf = theHosDB.theConnectionInf;
    }
    
    /**
     * สำหรับค้นหารายการคัดกรองที่อยู่ในรายงาน 11รง5 ส่วนที่ 4
     * @Param keyword เป็นตัวแปรที่เก็บค่าคำค้นที่ผู้ใช้กรอก
     * @Return Vector ของ Rp115Group4Disease
     * @Author pu
     * @Date 16/03/2549
     **/
    public Vector searchScreeningDiseaseByKeyword(String keyword)
    {
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = (Vector)theHosDB.theRp115Group4DiseaseDB.selectByKeyword(keyword);
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
     * สำหรับค้นหารายการตรวจรักษาที่อยู่ในรายงาน 11รง5 ส่วนที่ 4
     * @Param disease_id เป็น key id ของรายการคัดกรองที่จะนำไปค้นหารายการตรวจรักษา
     * @Return Vector ของ Rp115Group4Item
     * @Author pu
     * @Date 16/03/2549
     **/
    public Vector searchRp115ItemByDiseaseID(String disease_id)
    {
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            vData = (Vector)theHosDB.theRp115Group4ItemDB.selectByDiseaseID(disease_id);
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
     *บันทึกรายการตรวจรักษาของกลุ่มคัดกรองลงฐานข้อมูล
     *@param vcItem เป็น Vector ที่เก็บรายการตรวจรักษา
     *@Author pu
     *@date 16/03/2006
     */
    public void saveRp115Item(Vector vcItem)
    {
        try
        {
            theConnectionInf.open();
            if(vcItem != null)
            {
                for(int i=0;i<vcItem.size();i++)
                {
                    this.theHosDB.theRp115Group4ItemDB.insertData((Rp115Group4Item)vcItem.get(i));
                }
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
    }
    
    /**
     *ลบรายการตรวจรักษาของกลุ่มคัดกรอง
     *@param vcItem เป็น Vector ของรายการตรวจรักษาที่ต้องการลบ
     *@param a เป็น Array ของ Integer ที่เก็บ Index ของแถวของรายการตรวจรักษาที่อยู่ในตาราง
     *@Author pu
     *@date 16/03/2006
     */
    public void deleteRp115Item(Vector vcItem,int[] a)
    {
        if(a.length==0)
        {
            return;
        }
        try
        {
            theConnectionInf.open();
            for(int i=a.length-1;i>=0;i--)
            {
                Rp115Group4Item oi = (Rp115Group4Item)vcItem.get(a[i]);
                this.theHosDB.theRp115Group4ItemDB.deleteByKeyID(oi.getObjectId());
                vcItem.remove(a[i]);
                oi = null;               
            }
            theUS.setStatus("การลบรายการตรวจรักษาของการตรวจคัดกรองเสร็จสิ้น", UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        this.vItem = vcItem;
    }

    /**
     *ค่าที่เหลือจากการลบ
     *@Author pu
     *@date 16/03/2006
     */
    public Vector getVectorDeleted()
    {
        return this.vItem;
    }
}
