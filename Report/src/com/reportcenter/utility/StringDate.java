/*
 * StringDate.java
 *
 * Created on 7 กันยายน 2548, 16:21 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportcenter.utility;
import java.util.*;
/**
 *
 * @author tong(Padungrat)
 */
public class StringDate {
    
    /** Creates a new instance of StringDate */
    public StringDate() {
    }
       /**
     *  แปลงข้อมูล จากข้อความ เป็น วันที่
     *  รูปแบบ ของข้อมูลเข้า เป็น   yyyy-mm-dd,hh:nn:ss
     */
    public static Date StringDateToDate(String text)
    {
            Date date = null;
            Calendar c=Calendar.getInstance(Locale.US);
         //  Calendar c=Calendar.getInstance();
           if(text==null || text.length()<8) return null;
           try{
                int yyyy = Integer.parseInt(text.substring(0,4));
                if(yyyy>2300)
                    yyyy = yyyy -  543;

                if(text.length()==8){
                    int mm = Integer.parseInt(text.substring(4,6))-1;
                    int dd = Integer.parseInt(text.substring(6,8));
                    c.set(yyyy,mm,dd);
                }
                else{
                    int mm = Integer.parseInt(text.substring(5,7))-1;
                    int dd = Integer.parseInt(text.substring(8,10));
                    c.set(yyyy,mm,dd);
                    if(text.length()>10){
                        int hh = Integer.parseInt(text.substring(11,13));
                        int nn = Integer.parseInt(text.substring(14,16));
                        int ss = Integer.parseInt(text.substring(17));
                        c.set(yyyy,mm,dd,hh,nn,ss);
                    }
                }
                // System.out.println("Year : " + yyyy);
                return c.getTime();
           }
           catch(Exception e) {
               System.out.println("date" + text);
               e.printStackTrace();
               return null;
           }
   }
    
    public static void main(String[] argv)
    {
        Date d = StringDate.StringDateToDate("2551-02-29");
        System.out.println(d.toString());
        d = StringDate.StringDateToDate("2007-09-07");
        System.out.println(d.toString());
        d = StringDate.StringDateToDate("20070907");
        System.out.println(d.toString());
    }
}
