/*
 * NumberUtil.java
 *
 * Created on 12 ตุลาคม 2548, 19:22 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.utility;

import java.text.*;

/**
 *
 * @author surachai
 */
public class NumberUtil
{
    private static NumberFormat nf2 = NumberFormat.getInstance();
    
    static 
    {
        nf2.setMaximumFractionDigits(2);
        nf2.setMinimumFractionDigits(2);        
    }
    
    public static String formatCurrency(double number)
    {
        return nf2.format(number);        
    }
    
    public static String formatCurrency(String number)
    {
        return formatCurrency(Double.parseDouble(number));
    }
    
}
