/*
 * objUtil.java
 *
 * Created on 5 กันยายน 2548, 18:34 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportquery.utility;

/**
 *
 * @author tong(Padungrat)
 */
public class objUtil {
    
    private static String result;
    private static boolean bresult;
    public objUtil() {
    }
    /**ใช้ในการแปลง ค่าจาก boolean ไปเป็น String เช่น true เป็น 1 false เป็น 0
     *@param b เป็น boolean
     *@return เป็น String คือ 1(true),0(false)
     */
    public static String BooleanToString(boolean b)
    {   result = "0";
        if(b)
        {
            result = "1";
        }
        
        return result;
    }
    /**ใช้ในการแปลง ค่าจาก String ไปเป็น boolean เช่น 1 เป็น true, 0 เป็น false
     *@param b เป็น String
     *@return เป็น boolean คือ true(1),false(0)
     */
    public static boolean StringToBoolean(String b)
    {   bresult = false;
        try
        {
            if(b != null || ("").equals(b))
            {
                if(("1").equalsIgnoreCase(b))
                {
                    bresult = true;
                }
            }
        }
        catch(Exception ex)
        {
        }
        return bresult;
    }
    
    /**ใช้ในการตรวจสอบคำต้องห้ามในการพิมพ์ SQL
     *@param sql เป็น String ของ sql ที่เข้ามาตรวจสอบ
     *@return เป็น boolean ถ้าเป็น true ไม่มีคำต้องห้าม false มีคำต้องห้าม
     */
    public static boolean checkSyntaxSQL(String sql)
    {   boolean result = true;
        if(sql != null && sql.length() >0)
        {
            int iresult = 0;
          sql = sql.toUpperCase().trim();
          
          if(sql.startsWith(StyleError.insert.toUpperCase()))
          {result = false;}
          if(sql.startsWith(StyleError.alter.toUpperCase()))
          {result = false;}
          if(sql.startsWith(StyleError.delete.toUpperCase()))
          {result = false;}
          if(sql.startsWith(StyleError.drop.toUpperCase()))
          {result = false;}
          if(sql.startsWith(StyleError.doubleq.toUpperCase()))
          {result = false;}
          if(sql.startsWith(StyleError.update.toUpperCase()) )
          {result = false;}
         
        }
        return result;
    }
    
    public static void main(String[] argv)
    {
        System.out.println(objUtil.checkSyntaxSQL("insert"));    }
    
    
}
