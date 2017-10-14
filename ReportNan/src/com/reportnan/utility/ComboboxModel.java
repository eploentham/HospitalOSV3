/*
 * ComboboxModel.java
 *
 * Created on 2 สิงหาคม 2548, 11:22 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.utility;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import com.reportnan.utility.ComboFix;

import com.hospital_os.usecase.connection.*;

/**
 *
 * @author Noom
 */
public class ComboboxModel {
    
    /** Creates a new instance of ComboboxModel */
    public ComboboxModel() {
    }
    
      public static boolean initComboBox(JComboBox jc,Vector xfix)
    {
        if(xfix==null || jc==null) return false;
        if(jc.getItemCount()>0) jc.removeAllItems();
        for(int i=0;i<xfix.size();i++)
        {
            jc.addItem(xfix.get(i)); }
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
    
    public static boolean setCodeComboBox(JComboBox jc,String code)
    {
        int i;
        if(code==null || code.equals(""))
            i = jc.getItemCount();
        else
        {
            for(i=0;i<jc.getItemCount();i++)
            {
                try
                {
                    ComboFix hos = (ComboFix)jc.getItemAt(i);
                    if(code.equals(hos.code))    break;
                    
                }
                catch(ClassCastException e)
                {
                    CommonInf hos = (CommonInf)jc.getItemAt(i);
                    if(code.equals(hos.getCode()))    break;
                }
            }
        }
        if(i<jc.getItemCount())
        {  jc.setSelectedIndex(i);
           jc.setBackground(new Color(204,204,204));
           return true;
        }
        else
        { // jc.setBackground(Color.red);
            return false;
        }
    }
}
