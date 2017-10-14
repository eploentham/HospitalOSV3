/*
 * DialogControl.java
 *
 * Created on 13 �Զع�¹ 2549, 16:02 �.
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
     * ����Ѻ������ª��ͨش��ԡ��
     * @Param keyword �繵���÷���纤�ҤӤ鹷�������͡
     * @Return Vector �ͧ ServicePoint
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
     * ����Ѻ��Ǩ�ͺ��ҨФ��Ң����Ŵ��� Function �˹
     * @Param keyword ��� ��ҷ�������͡���ͤ�����¡��
     *        offsetPage ��� 8jk ������鹢ͧ��ä���
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
                // ������դ��� keyword ��������͡
                vData = (Vector)theManageDB.theItemNanDB.selectBySearch(keyword,offsetPage); 
            }
            else
            {
                // ���������� keyword ��������͡
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
     * ����Ѻ���� Office
     * @Param keyword ��� ��ҷ�������͡���ͤ�����¡��
     *        offsetPage ��� 8jk ������鹢ͧ��ä���
     * @Author ojika
     * @Date 21/06/2549
     */
    public Vector selectOfficeByKeyword(String keyword,int offsetPage)
    {
        vData = new Vector();
        try
        {
            theConnectionInf.open();
            // ������դ��� keyword ��������͡
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
