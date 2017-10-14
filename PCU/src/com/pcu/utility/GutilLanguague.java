/*
 * GutilLanguague.java
 *
 * Created on 8 กุมภาพันธ์ 2549, 11:05 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.utility;
import java.util.Properties;
import com.language.utility.language.Language;
/**
 *
 * @author Administrator
 */
public class GutilLanguague {
    private static Properties data = Language.getProperties("pcu");
    /** Creates a new instance of GutilLanguague */
    public GutilLanguague() {
    }
    public static String getTextBundle(String str)
    {
        if(str.trim().equals(""))
        {
            return "";
        }
        try
        {
            return data.getProperty(str); 
        }
        catch(Exception e)
        {
            return "";
        }
    }
}
