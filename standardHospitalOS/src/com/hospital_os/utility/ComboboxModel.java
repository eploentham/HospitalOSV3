/*
 * ComboboxModel.java
 *
 * Created on 1 ตุลาคม 2546, 23:57 น.
 */

package com.hospital_os.utility;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import com.hospital_os.usecase.connection.*;
/**
 *
 * @author  Administrator
 */
public class ComboboxModel {
    
    /** Creates a new instance of ComboboxModel */
    static String data;
    public ComboboxModel() {
    }

     
     
     
    public static boolean initComboBox(JComboBox jc,Vector xfix)
    {
        if(xfix==null || jc==null) return false;
        /*
        if(jc!=null && jc.getItemCount()>0)
            jc.removeAllItems();
        for(int i=0;i<xfix.size();i++)
        {
            jc.addItem(xfix.get(i));
        }*/
            DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
            for(int i=0;i<xfix.size();i++)
                dcbm.addElement(xfix.get(i));
            jc.setModel(dcbm);
        
        return true;
    }   
    
    public static String getStringConboBox(JComboBox jc)
    {
        try{
            ComboFix hos = (ComboFix)jc.getSelectedItem();
            if(hos==null) return "";
            return hos.name;
        }
        catch(Exception e)
        {   CommonInf hos;
            try{
                hos = (CommonInf)jc.getSelectedItem();
            }
            catch(Exception ex)
            {   hos = null;
            }
            if(hos==null) return "";
            return hos.getName();
        }
    }
    
    
    
    public static String getCodeComboBox(JComboBox jc)
    {  
        try{
            ComboFix hos = (ComboFix)jc.getSelectedItem();
            if(hos==null) return "";
            return hos.code;
        }
        catch(Exception e)
        {   CommonInf hos;
            try{
                hos = (CommonInf)jc.getSelectedItem();
            }
            catch(Exception ex)
            {   hos = null;
            }
            if(hos==null) return "";
            return hos.getCode();
        }
    }
    /**
     *@not deprecated henbe unused มันใช้ได้กับ combofix เท่านั้นจึงจำเป็นต้องให้มัน dep
     */
    public static String getDescriptionComboBox(JComboBox jc ,String id)
    {   
        if(jc==null) return null;
        if(jc.getItemCount()>0) 
        {   int count = jc.getItemCount();
            for(int i=0;i<count;i++)
            {
                ComboFix hos = (ComboFix)jc.getItemAt(i);
                if(hos.code.equals(id))
                {   return hos.name;
                }
               
            }
            return null;
        }
        return null;
    }
    
    public static String getOtherComboBox(JComboBox jc )
    {   data = "";
        try{
            ComboFix hos = (ComboFix)jc.getSelectedItem();
            if(hos!=null) 
            {
                data = hos.other;
            }
            hos = null;
//            Constant.println("----------: " + data);
            //return hos.code;
        }
        catch(Exception e)
        {   CommonInf hos;
            try{
                hos = (CommonInf)jc.getSelectedItem();
            }
            catch(Exception ex)
            {   hos = null;
            }
            if(hos==null) 
            {
                data = "";
            }
            
           // return hos.getCode();
        }
        return data;
    }
    
    /*
     *@author henbe
     *@name new Pattern of setCodeComboBox
     */
    
    public static boolean setCodeComboBox(JComboBox jc,String code)
    {
        //Constant.println("setCodeComboBox(JComboBox jc,String code)" + code);
         CommonInf hos = null;
         Color background = jc.getBackground();
         int i;
         for(i=0;i<jc.getItemCount();i++)
         {
            CommonInf hos_loop = (CommonInf)jc.getItemAt(i);
            if(code!=null && hos_loop!=null && code.equals(hos_loop.getCode()))
            {
                hos = hos_loop;
                break;
            }
         }
         //พบข้อมูลตามปกติ
         if(hos!=null)
         {
            jc.setToolTipText(null);
            if(jc.getItemCount()>0)
            {
                jc.setSelectedIndex(i);
            }
            jc.setBackground(UIManager.getColor("ComboBox.popupSelectedBackground"));
            return true;
         }
         //ไม่พบข้อมูลและค่าที่ส่งมาเป็นค่าว่าง
         else
         {
             if(code!=null )
                 code = " ";
            jc.setToolTipText(code);
            if(jc.getItemCount()>0)
                jc.setSelectedIndex(0);
            jc.setBackground(Color.YELLOW);
            return false;
         }
    }               
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    }
    
}
