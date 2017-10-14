/*
 * Constant.java
 *
 * Created on 10 สิงหาคม 2548, 15:30 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report18file.utility;

/**
 *
 * @author Noom
 */
public class Constant extends com.reportcenter.utility.Constant{

    public static String removeDot(String icd_code) 
    {
        if(icd_code==null)
            return "";
        int dot_index = icd_code.indexOf(".");
        if(dot_index!=-1)
            return icd_code.substring(0,dot_index) + icd_code.substring(dot_index+1);
        return icd_code;
    }

    
    public final static String[] REPORT_NAME = {"รายงาน 18 แฟ้ม"};
}
