/*
 * Util.java
 *
 * Created on 10 กันยายน 2548, 21:43 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportcenter.utility;

/**
 *
 * @author tong(Padungrat)
 */
public class Util {
    
   // private static String headfile;
    public Util() {
    }
    
    /**ใช้ในการแปลงวันที่เพื่อตัดข้อมูลให้เหลือเป็นรูปแบบ mmyy
     *@param startDate เป็น String เป็นวันที่ ในรูปบบ yyyy-mm-dd เท่านั้น
     *@return เป็น String ที่ได้จากการตัดแล้วเป็น yymm
     */
    public static String getheadFile(String startDate)
    {
        String headfile = "";
        try{
           if(startDate.trim().length() == 10)
           {
               headfile = startDate.trim();
               headfile = headfile.substring(2, 7);
               headfile = headfile.replaceAll("-", "");

           }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return headfile;
    }
    
    public static String getHeadFile(String startDate,String curdate,String hid
            ,String filename,int mode,String typeFile)
    {
           if(curdate.trim().length() >= 10)
               curdate = curdate.substring(0,10);
           
            String ret_file = filename;
            if(mode!=0 && mode!=3 && mode!=4){   
                if(filename.length()>3)
                    filename = filename.substring(0,3);
                
                ret_file = filename + getHeadFile(startDate,curdate,hid);       
            }
            if(Constant.DBF_INDEX.equals(typeFile)){
                ret_file += "." +Constant.DBF_FILE;
            }
            else if(Constant.XLS_INDEX.equals(typeFile)){
                ret_file += "."+Constant.XLS_FILE;
            }
            else if(Constant.CSV_INDEX.equals(typeFile)){
                ret_file += "."+Constant.CSV_FILE;
            }
            else if(Constant.TXT_HEAD_INDEX.equals(typeFile)){
                ret_file += "."+Constant.TXT_FILE;
            }
            else{
                ret_file += "." + Constant.TXT_FILE;
            }
         return ret_file;
    }
    
    public static String getHeadFile(String startDate,String curdate,String hid)
    {
        
           if(curdate.trim().length() >= 10)
               curdate = curdate.substring(0,10);
           
        String headfile = "";
        try{
            //2550-11-11
           if(startDate.trim().length() >= 10)
           {
               headfile = startDate.trim();
               int year = Integer.parseInt(headfile.substring(0,4));
               if(year > 2500)
                   year = year-543;
               headfile = year + headfile.substring(5,7);
               headfile = headfile.replaceAll("-", "");
           }
           if(curdate.trim().length() >= 10)
           {
               curdate = curdate.substring(0,10);
               curdate = curdate.trim();
               int year = Integer.parseInt(curdate.substring(0,4));
               if(year > 2500)
                   year = year-543;
               curdate = year + curdate.substring(5);
               curdate = curdate.replaceAll("-", "");
           }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        headfile = "_"+hid+"_"+headfile+"_"+curdate;
        return headfile;
    }
    
    public static boolean isBetweenDate(String startDate,String endDate){
        
        /**ตรวจสอบวันที่เริ่มต้นเป็นวันเดียวกันกับวันที่สิ้นสุดหรือไม่*/
        if(startDate.compareTo(endDate) <= 0){
            return true;
        }else{
            return false;
        }
    }
    /**
     *  สำหรับเปลี่ยนข้อมูลที่เป็น พศ. ให้เป็น คศ. เฉพาะการ write file แบบ txt และ xls
     **/    
    public static String convertYearString(String dateToConvert)
    {
        
        String convertDate = "";
        int intDateToConvert = 0;
        try{
            if(dateToConvert != null && dateToConvert.length() >= 10)
            {
                    // ทำการเปลี่ยนเป็น คศ.               
                intDateToConvert = Integer.parseInt(dateToConvert.substring(0, 4)) - 543;
                convertDate = String.valueOf(intDateToConvert);                
                convertDate +=  dateToConvert.substring(5,7) + dateToConvert.substring(8,10);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return convertDate;
    }

    public static void main(String[] argv)
    {
        System.out.println(Util.convertYearString("2548-08-01 "));
    }
}
