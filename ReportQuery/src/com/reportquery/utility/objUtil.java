/*
 * objUtil.java
 *
 * Created on 5 �ѹ��¹ 2548, 18:34 �.
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
    /**��㹡���ŧ ��Ҩҡ boolean ��� String �� true �� 1 false �� 0
     *@param b �� boolean
     *@return �� String ��� 1(true),0(false)
     */
    public static String BooleanToString(boolean b)
    {   result = "0";
        if(b)
        {
            result = "1";
        }
        
        return result;
    }
    /**��㹡���ŧ ��Ҩҡ String ��� boolean �� 1 �� true, 0 �� false
     *@param b �� String
     *@return �� boolean ��� true(1),false(0)
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
    
    /**��㹡�õ�Ǩ�ͺ�ӵ�ͧ����㹡�þ���� SQL
     *@param sql �� String �ͧ sql �������ҵ�Ǩ�ͺ
     *@return �� boolean ����� true ����դӵ�ͧ���� false �դӵ�ͧ����
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
