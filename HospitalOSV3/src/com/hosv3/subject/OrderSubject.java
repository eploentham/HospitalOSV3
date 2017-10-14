/*
 * OrderSubject.java
 *
 * Created on 26 ตุลาคม 2546, 22:52 น.
 */
package com.hosv3.subject;
import com.hosv3.usecase.transaction.*;
import java.util.*;
/**
 *
 * @author  henbe
 */
public class OrderSubject implements ManageOrderResp{    
    
    private Vector vManageOrder;
    
    public OrderSubject() 
    {
        vManageOrder = new Vector();
    } 
    public void removeAttach()
    {
        vManageOrder.removeAllElements();
        
    }
    /*attach*/  
    public void attachManageOrder(ManageOrderResp o)
    {   
        vManageOrder.add(o);
    }
    
    /*method*/
    public void notifyReceiveReturnDrug(String msg,int status) 
    {
        for(int i=0,size=vManageOrder.size();i<size;i++)
            ((ManageOrderResp)vManageOrder.get(i)).notifyReceiveReturnDrug(msg,status); 
    }  
    
    public void notifySaveOrderItemInLab(String msg,int status) 
    { 
        for(int i=0,size=vManageOrder.size();i<size;i++)
            ((ManageOrderResp)vManageOrder.get(i)).notifySaveOrderItemInLab(msg,status); 
    }
    /**
     *@param msg
     *@param status
     *@author padungrat(tong)
     *@date 13/03/49,10:45
    
    public void notifySaveOrderItemInXRay(String msg,int status) 
    { 
        for(int i=0,size=vManageOrder.size();i<size;i++)
            ((ManageOrderResp)vManageOrder.get(i)).notifySaveOrderItemInXRay(msg,status); 
    }
     * */
    public void notifyCheckAutoOrder(String msg,int status) 
    { 
        for(int i=0,size=vManageOrder.size();i<size;i++)
            ((ManageOrderResp)vManageOrder.get(i)).notifyCheckAutoOrder(msg,status); 
    } 
    
    public void notifyDoctorOffDrug(String msg,int status) 
    { 
        for(int i=0,size=vManageOrder.size();i<size;i++)
            ((ManageOrderResp)vManageOrder.get(i)).notifyDoctorOffDrug(msg,status); 
    } 
    public void notifySaveOrderItem(String msg, int status)
    {
        for(int i=0,size=vManageOrder.size();i<size;i++)
            ((ManageOrderResp)vManageOrder.get(i)).notifySaveOrderItem(msg,status); 
    }
    public void notifyVerifyOrderItem(String msg, int status)
    {
        for(int i=0,size=vManageOrder.size();i<size;i++)
            ((ManageOrderResp)vManageOrder.get(i)).notifyVerifyOrderItem(msg,status); 
    }
    
    public void notifyCancelOrderItem(String str, int status) {
        for(int i=0,size=vManageOrder.size();i<size;i++)
            ((ManageOrderResp)vManageOrder.get(i)).notifyCancelOrderItem(str,status);
    }
    
    public void notifyDispenseOrderItem(String str, int status) {
        for(int i=0,size=vManageOrder.size();i<size;i++)
            ((ManageOrderResp)vManageOrder.get(i)).notifyDispenseOrderItem(str,status);
    }

    public void notifyExecuteOrderItem(String str, int status) {
        for(int i=0,size=vManageOrder.size();i<size;i++)
            ((ManageOrderResp)vManageOrder.get(i)).notifyExecuteOrderItem(str,status);
    }

    public void notifyContinueOrderItem(String str, int status) {
        for(int i=0,size=vManageOrder.size();i<size;i++)
            ((ManageOrderResp)vManageOrder.get(i)).notifyContinueOrderItem(str,status);
    }
    public void notifyReferOutLab(String msg,int status){
        for(int i=0,size=vManageOrder.size();i<size;i++)
            ((ManageOrderResp)vManageOrder.get(i)).notifyReferOutLab(msg,status);
    }
    
    public void notifySaveReturnDrug(String str, int status) {
        for(int i=0,size=vManageOrder.size();i<size;i++)
            ((ManageOrderResp)vManageOrder.get(i)).notifySaveReturnDrug(str,status);
    }
    
    public void notifySaveOrderRequest(String str, int status) {
        for(int i=0,size=vManageOrder.size();i<size;i++)
            ((ManageOrderResp)vManageOrder.get(i)).notifySaveOrderRequest(str,status);
    }
    
}
