/*
 * Language.java
 *
 * Created on 7 กุมภาพันธ์ 2549, 15:24 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.utility;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.JTabbedPane;
/**
 *
 * @author pu
 */
public class Language
{
    
    /** Creates a new instance of Language */
    public Language()
    {
    }
    
    public static String getTextBundle(String str)
    {
        if(str.trim().equals(""))
        {
            return "";
        }
        try
        {
            return java.util.ResourceBundle.getBundle("com/reportnan/property/thai").getString(str);
        }
        catch(Exception e)
        {
            return str;
        }
    }
    
    public static void JPanelLabler(JPanel pane)
    {
        try
        {
            ((TitledBorder) pane.getBorder()).setTitle(getTextBundle(((TitledBorder) pane.getBorder()).getTitle()));
        }
        catch(Exception e)
        {
            
        }
    }
    
    public static void getTextBundleTab(int index,JTabbedPane pane)
    {
        pane.setTitleAt(index,getTextBundle(pane.getTitleAt(index)));
    }
}
