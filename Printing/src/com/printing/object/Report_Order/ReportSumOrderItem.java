/*
 * ReportOrder.java
 *
 * Created on 21 ����Ҥ� 2547, 12:53 �.
 */

package com.printing.object.Report_Order;
import java.util.*;
import com.printing.usecase.*;
/**
 *
 * @author  tong
 *  �� Object ˹�觢ͧ Report_Order �¡���Ѻ�����Ẻ set
 *  �������͡��� getData ������Ѻ Print
 */

public class ReportSumOrderItem implements ObjectInf
{
    
    /** Creates a new instance of ReportOrder */
        String hospital = "Hospital";
        String namep = "Name";
        String date = "Date";
        String hn = "HN";
        String payment = "Payment";
        String vn_an = "VN_AN";
        String payment_id = "Payment_ID";
        String age = "Age";
        String sum = "Sum";
        String paddress = "Address";
        String patientType = "patientType";
        Map parameters = new HashMap();
    
    public ReportSumOrderItem() {
    }
    /**�����*/
     public void setSum(String name)
    {
        setMap(sum,name);
    }
     /**����*/
     public void setAge(String name)
    {
        setMap(age,name);
    }
    /**�����Ţ�Է�Ժѵ�*/
    public void setPaymentID(String name)
    {
        setMap(payment_id,name);
    }
    /**An VN*/
    public void setVNAN(String name)
    {
        setMap(vn_an,name);
    }
    /**�Է�ԡ���ѡ��*/
    public void setPayment(String name)
    {
        setMap(payment,name);
    }
    /**HN*/
    public void setHN(String name)
    {
        setMap(hn,name);
    }
    /**�ѹ���*/
    public void setDate(String name)
    {
        setMap(date,name);
    }
    /**�ç��Һ��*/
    public void setHospital(String name)
    {
        setMap(hospital,name);
    }
    /**����*/
    public void setname(String name)
    {
        setMap(namep,name);
    }
    
    /**�������*/
    public void setAddress(String name)
    {
        setMap(paddress,name);
    }
    /**ʶҹм������ - �͡*/
    public void setPatientType(String name)
    {
        setMap(patientType,name);
    }
    
    public Map getData() {
         return parameters;
    }
    
    public void setMap(String Param, String Data) {
         parameters.put(Param,Data);
    }
    
}