/*
 * DateUtil.java
 *
 * Created on 26 ตุลาคม 2546, 10:20 น.
 */
package com.generalreport.utility;
import java.util.*;
/**
 *
 * @author  tong
 */
public class DateUtil {
    public String year;
    public String month;
    public String date;
    public String hour;
    public String minute;
    /** Creates a new instance of DateUtil */
    public DateUtil() {
    }
    public static String calculateAge(String date)
    {
        String year;
        String month;
        
        String hour;
        String minute;
        Date d = getDateFromText(date);
        if(d==null) 
            return "";
        Calendar c=Calendar.getInstance();
        Calendar c2=Calendar.getInstance();
        c.setTimeInMillis(d.getTime());
        c2.setTimeInMillis(System.currentTimeMillis());
//System.out.println(c.toString());
//System.out.println(c2.toString());
        year = String.valueOf(c2.get(c2.YEAR) - c.get(c.YEAR) );//- 543);
        month = String.valueOf(c.get(c.MONTH));
        date = String.valueOf(c.get(c.DAY_OF_MONTH));
        minute = String.valueOf(c.get(c.MINUTE));
        hour = String.valueOf(c.get(c.HOUR_OF_DAY));
        if(!year.equals(""))
        {
            if(year.substring(0,1).equals("-"))
                return "0";
            else
                return year;// +" ปี "+ month +" เดือน "+ date +" วัน ";
        }
        else
            return "0";
    }
    
    public static String calPregnant(String date)
    {
        if(date.length() >=8)
        { 
         //   System.out.println(date);
            Date d = getDateFromText(date);
            int dd =0;
            int mm =0;
            int yyyy=0;
            
            
            Calendar c=Calendar.getInstance();
            Calendar c2=Calendar.getInstance();
            
            
            
            c.setTimeInMillis(d.getTime());
            dd = c.get(c.DATE)+284;
            mm = c.get(c.MONTH);
            yyyy = c.get(c.YEAR);
            c.set(yyyy,mm,dd);
            
            
            String yyyy1 ="0000" + String.valueOf(c.get(c.YEAR));
            String mm1 = "00" + String.valueOf(c.get(c.MONTH) + 1);
            String dd1 = "00" + String.valueOf(c.get(c.DATE));
            yyyy1=yyyy1.substring(yyyy1.length()-4,yyyy1.length());
            mm1=mm1.substring(mm1.length()-2,mm1.length());
            dd1=dd1.substring(dd1.length()-2,dd1.length());
            
     //       System.out.println(dd1+"/" + mm1 + "/" +yyyy1);
            
            
        
            return dd1+"/" + mm1 + "/" +yyyy1;
        }
        else
            return "";
    }
    
    
    public String calculateAgeLong(String date)
    {       
        Date d = getDateFromText(date);
        Date today = new Date();
        if(d==null) 
            return "";
        Calendar c=Calendar.getInstance();
        Calendar c2=Calendar.getInstance();
        Calendar c3=Calendar.getInstance();
        c.setTimeInMillis(d.getTime());
        c2.setTimeInMillis(System.currentTimeMillis());
        c3.setTimeInMillis(today.getTime() - d.getTime());
                
        year = String.valueOf(c3.get(Calendar.YEAR)-543 -1970);
        month = String.valueOf(c3.get(Calendar.MONTH));
        date = String.valueOf(c3.get(Calendar.DATE));
        minute = String.valueOf(c3.get(c.MINUTE));
        hour = String.valueOf(c3.get(c.HOUR_OF_DAY));
        
        if(year.substring(0,1).equals("-")) 
            return "0";
            
        //amp
        if(year.equalsIgnoreCase("0"))
        {
            if(month.equalsIgnoreCase("0"))
            {
                if(date.equalsIgnoreCase("0"))
                {
                    return "";
                }
                else
                {
                    return date +" วัน ";
                }
            }
            else
            {
                if(date.equalsIgnoreCase("0"))
                {
                    return month +" เดือน ";
                }
                else
                {
                    return month +" เดือน "+ date +" วัน ";
                }
            }            
        }
        else
        {
            if(month.equalsIgnoreCase("0"))
            {
                if(date.equalsIgnoreCase("0"))
                {
                    return year +" ปี ";
                }
                else
                {
                    return year +" ปี "+ date +" วัน ";
                }
            }
            else
            {
                if(date.equalsIgnoreCase("0"))
                {
                    return year +" ปี "+ month +" เดือน ";
                }
                else
                {
                    return year +" ปี "+ month +" เดือน "+ date +" วัน ";
                }
            }            
        }
        
    }
    
    /**
     *  แปลงข้อมูล จากข้อความ เป็น วันที่
     *  รูปแบบ ของข้อมูลเข้า เป็น   yyyy-mm-dd,hh:nn:ss
     */
    public static Date getDateFromText(String text)  {
       Calendar c=Calendar.getInstance();
       if(text==null || text.length()<10) return null;
       try{
        int yyyy = Integer.parseInt(text.substring(0,4));
        int mm = Integer.parseInt(text.substring(5,7))-1;
        int dd = Integer.parseInt(text.substring(8,10));
        c.set(yyyy,mm,dd);
        if(text.length()>10){
            int hh = Integer.parseInt(text.substring(11,13));
            int nn = Integer.parseInt(text.substring(14,16));
            int ss = Integer.parseInt(text.substring(17));
            c.set(yyyy,mm,dd,hh,nn,ss);
       }
       return c.getTime();
       }catch(Exception e) {
           e.printStackTrace();
           return null;
       }
    }
    
    /**
     *  แปลงข้อมูล จากข้อความ เป็น วันที่
     *  รูปแบบ ของข้อมูลเข้า เป็น   yyyy-mm-dd,hh:nn:ss
     */
    public static Date getDateFromTextServer(String text)  {
       
         Calendar c=Calendar.getInstance(Locale.US);
         //  Calendar c=Calendar.getInstance();
           if(text==null || text.length()<10) return null;
           try{
            int yyyy = Integer.parseInt(text.substring(0,4));
            yyyy = yyyy -  543;
            int mm = Integer.parseInt(text.substring(5,7))-1;
            int dd = Integer.parseInt(text.substring(8,10));
            c.set(yyyy,mm,dd);
            if(text.length()>10){
                int hh = Integer.parseInt(text.substring(11,13));
                int nn = Integer.parseInt(text.substring(14,16));
                int ss = Integer.parseInt(text.substring(17));
                c.set(yyyy,mm,dd,hh,nn,ss);
           }
            System.out.println("Year : " + yyyy);
           return c.getTime();
           }catch(Exception e) {
               e.printStackTrace();
               return null;
           }
        
    }
    
    /**
     * แปลงข้อมูลเข้าจาก วันที่ รูปแบบ dd/mm/yyyy
     * ไปเป็น yyyy-mm-dd
     */
    public static String getGuiBDate(String date)
    {
        if(date==null) return "";
       if(date.length() < 9 ) return "";
       try{
          String temp = new String();
            //    System.out.println("Real Date : " + text);
            //    System.out.println(text.substring(0,(text.indexOf("/"))) );
                String dd = date.substring(0,date.indexOf("/"));
                int ddd = Integer.parseInt(dd);
                String d = "00" + String.valueOf(ddd);
                d=d.substring(d.length()-2,d.length());
           //     System.out.println("DATE : " + dd);
                temp  = date.substring(dd.length()+1);
          //      System.out.println(temp);
                int m = Integer.parseInt(temp.substring(0,temp.indexOf("/")));
                String mm = "00" + String.valueOf(m);
                mm=mm.substring(mm.length()-2,mm.length());
             //   System.out.println("MONHT : " + mm);
                temp = temp.substring(temp.indexOf("/")+1);
                // System.out.println(temp);
                 String yyyy = temp.substring(0,4);
            //     System.out.println(yyyy);
            return yyyy+ "-" + mm + "-"+d; 
       }catch(Exception e) {
           e.printStackTrace();
           return null;
       }
    }
    
    /**
     *  ใบ้ในการตรวจสอบเวลา ถ้าเป็นเวลาก่อน หรือหลัง โดยเทียบกับเวลาปัจจุบัน
     *  ถ้าเวลา ปัจจุบัน น้อยกว่าเวลาที่เทียบ จะส่งค่า เป็น 0 
     *  ข้อมูลเข้า เป็น hh:mm:ss
     */
    public static boolean checkBeforeTime(String time)
    {   if(time == null || time.length() <= 4 || time.length() >8)
            return false;
        
        Calendar ctoday = Calendar.getInstance();
        Calendar ctime =Calendar.getInstance();
        Date today = new Date();
        ctoday.setTimeInMillis(today.getTime());
        if(time.length() == 5)
        {
            int hh = Integer.parseInt(time.substring(0,2));
            int nn = Integer.parseInt(time.substring(3,5));
            ctime.set(ctoday.get(Calendar.YEAR),ctoday.get(Calendar.MONTH),ctoday.get(Calendar.DATE),hh,nn);
        
        }
        else
        {   
            int hh = Integer.parseInt(time.substring(0,2));
            int nn = Integer.parseInt(time.substring(3,5));
            int ss = Integer.parseInt(time.substring(6));
            
            ctime.set(ctoday.get(Calendar.YEAR),ctoday.get(Calendar.MONTH),ctoday.get(Calendar.DATE),hh,nn,ss);
        
        }
   
        System.out.println("time " + ctime.getTime().toString());
     //   System.out.println("today " + ctoday.getTime().toString());
        //System.out.println(ctime.before(ctoday));
       // System.out.println(ctoday.before(ctime));
    
        
        return ctoday.before(ctime);
    }
    
    /**
     *  ใบ้ในการตรวจสอบเวลา ถ้าเป็นเวลาก่อน หรือหลัง โดยเทียบกับเวลาปัจจุบัน
     *  ถ้าเวลา ปัจจุบัน มากกว่ากว่าเวลาที่เทียบ จะส่งค่า เป็น 0 
     *  ข้อมูลเข้า เป็น hh:mm:ss
     */
    public static boolean checkAfterTime(String time)
    {   if(time == null || time.length() <= 4 || time.length() >8)
            return false;
        
        Calendar ctoday = Calendar.getInstance();
        Calendar ctime =Calendar.getInstance();
        Date today = new Date();
        ctoday.setTimeInMillis(today.getTime());
        if(time.length() == 5)
        {
            int hh = Integer.parseInt(time.substring(0,2));
            int nn = Integer.parseInt(time.substring(3,5));
            ctime.set(ctoday.get(Calendar.YEAR),ctoday.get(Calendar.MONTH),ctoday.get(Calendar.DATE),hh,nn);
        
        }
        else
        {   
            int hh = Integer.parseInt(time.substring(0,2));
            int nn = Integer.parseInt(time.substring(3,5));
            int ss = Integer.parseInt(time.substring(6));
            ctime.set(ctoday.get(Calendar.YEAR),ctoday.get(Calendar.MONTH),ctoday.get(Calendar.DATE),hh,nn,ss);
        
        }
          
        
        
        
        
        System.out.println("time " + ctime.getTime().toString());
     //   System.out.println("today " + ctoday.getTime().toString());
        //System.out.println(ctime.before(ctoday));
    //    System.out.println(ctoday.after(ctime));
         return ctoday.after(ctime);
    }
    
    /**
     *  แปลงข้อมูล วันที่ และเวลา แบบ yyyy-mm-dd : MM:ss
     *  ไปเป็นวันที่ รูปแบบข้อความ(แบบเต็ม) และจะให้แสดง เวลาหรือไม่ 
     *  ถ้า showtime เป็น true คือ แสดงเวลาด้าย
     *              เป็น false คือ ไม่แสดงเวลา
     */ 
    public static String getDateToString(Date date,boolean showtime)
    {   String pattern = "dd MMMMM yyyy HH:mm";
         String dateString = new String();
           java.util.Date today = new java.util.Date();
            java.text.SimpleDateFormat formatter =null;
        
            try {
                if(showtime)
                {   formatter = new java.text.SimpleDateFormat(pattern); 
                    if(date !=null)
                        dateString = formatter.format(date);
                    else
                        dateString = "";
                }
                else
                {
                    pattern = "dd MMMMM yyyy";
                    formatter = new java.text.SimpleDateFormat(pattern);
                  // System.out.println(date);
                    if(date != null)
                        dateString = formatter.format(date);
                    else
                        dateString = null;
                }
            
            } catch (IllegalArgumentException iae) {
                dateString = null;
            }
            formatter = null;
            pattern = null;
            return dateString;
        
    }
    
    /**
     *  แปลงข้อมูล วันที่ และเวลา แบบ yyyy-mm-dd : MM:ss
     *  ไปเป็นวันที่ รูปแบบข้อความ(แบบเต็ม) และจะให้แสดง เวลาหรือไม่ 
     *  ถ้า showtime เป็น true คือ แสดงเวลาด้าย
     *              เป็น false คือ ไม่แสดงเวลา
     */
    public static String getDateShotToString(Date date,boolean showtime)
    {   String pattern = "dd MMM yyyy HH:mm";
         String dateString = new String();
           java.util.Date today = new java.util.Date();
            java.text.SimpleDateFormat formatter =null;
        
            try {
                if(showtime)
                {   formatter = new java.text.SimpleDateFormat(pattern); 
                    dateString = formatter.format(date);
                }
                else
                {
                    pattern = "dd MMM yyyy";
                    formatter = new java.text.SimpleDateFormat(pattern);
                    dateString = formatter.format(date);
                }
            
            } catch (IllegalArgumentException iae) {
                dateString = null;
            }
            formatter = null;
            pattern = null;
            return dateString;
        
    }
    
    
    
    /**
     *  แปลงข้อมูล วันที่ และเวลา แบบ yyyy-mm-dd : MM:ss
     *  ไปเป็นวันที่ รูปแบบข้อความ(แบบย่อ) และจะให้แสดง เวลาหรือไม่ 
     *  ถ้า showtime เป็น true คือ แสดงเวลาด้าย
     *              เป็น false คือ ไม่แสดงเวลา
     */
    public static String getDateToStringShort(Date date,boolean showtime)
    {   String pattern = "dd MMM yy HH:mm";
         String dateString = new String();
           java.util.Date today = new java.util.Date();
            java.text.SimpleDateFormat formatter =null;
        
            try {
                if(showtime)
                {   formatter = new java.text.SimpleDateFormat(pattern); 
                    dateString = formatter.format(date);
                }
                else
                {
                    pattern = "dd MMM yy";
                    formatter = new java.text.SimpleDateFormat(pattern);
                    dateString = formatter.format(date);
                }
            
            } catch (IllegalArgumentException iae) {
                dateString = null;
            }
            formatter = null;
            pattern = null;
            return dateString;
        
    }
    
    public static String getGuiDateTime(String text)
    {
        if(text==null) return "";
        if(text.length() < 10 ) return "";
       try{
        String yyyy = text.substring(0,4);
        int m = Integer.parseInt(text.substring(5,7));
        String mm = "00" + String.valueOf(m);
        mm=mm.substring(mm.length()-2,mm.length());
        String dd = text.substring(8,10);
        if(text.length()>10){
            String hh = text.substring(11,13);
            String nn = text.substring(14,16);
            return dd+"/"+ mm + "/" + yyyy +","+ hh+ ":"+nn;
      //      return hh+":"+nn+","+dd+"/"+mm+"/"+"/"+yyyy; 
        }
        return dd + "/" + mm + "/" + yyyy; 
       }catch(Exception e) {
           e.printStackTrace();
           return null;
       }
    }
    
    /**
     *  ทำการเปรียบเทียบวันที่ โดย 
     *  datestart ก่อน enddate จะให้ค่า true
     */
    public static boolean beforDate(Date startDate, Date endDate)
    {
            return startDate.before(endDate);
    }
    
    
    /**
     *  ตรวจสอบว่า วันที่เข้ามาเป็นวันอะไร
     *  ข้อมูลเข้า เป็น Object Date
     *  ข้อมูลออก เป็น 
     *  1  วันอาทิตย์
     *  2  วันจันทร์
     *  3  วันอังคาร
     *  4  วันพุธ
     *  5  วันพฤหัสบดี
     *  6  วันศุกร์
     *  7  วันเสาร์
                              
     */
    public static String isDay(Date date)
    {       
            String day = new String();
            Calendar c=Calendar.getInstance();
        
                c.setTimeInMillis(date.getTime());
                
           /*     numday =c.get(Calendar.DAY_OF_YEAR) -1;
                nummonth = c.get(Calendar.MONTH);
                numyear =  c.get(Calendar.YEAR) -543 -1970;
            */
                
                day = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
                c = null;
           
               return day;
    }
    
    public static boolean checkTimeLocalThai()
    {
         Date today = new Date();
         Calendar c=Calendar.getInstance();
         c.setTimeInMillis(today.getTime());
         System.out.println(today.toString());
         return true;
    }
    
    
    /**
     *  ทำการเปรียบเทียบวันที่ โดย 
     *  datestart ก่อน enddate จะให้ค่า true
     */
    public static boolean isToday(Date date)
    {       int numday =0;
            int nummonth = 0;
            int numyear = 0;
            Date today = new Date();
            Calendar c=Calendar.getInstance();
        
                c.setTimeInMillis(today.getTime() - date.getTime());
                numday =c.get(Calendar.DAY_OF_YEAR) -1;
                nummonth = c.get(Calendar.MONTH);
                numyear =  c.get(Calendar.YEAR) -543 -1970;
                //System.out.println(numday + " " + nummonth + " " + numyear);
                today = null;
                c = null;
                
           if(numday == 0 && nummonth ==0 && numyear == 0)
               return true;
           else
               return false;
    }
    /**
    * ใช้ในการคำนวณ หา จำนวนชั่วโมง โดย
    * ข้อมูลเข้า เป็น String รูปแบบ yyyy-mm-dd,hh:mm:ss
    * ข้อมูลออก เป็นจำนวน ชั่วโมง
    */
     public static int countHourServer(String date)
     {  int numhour = 0;
        if(date != null || date.length() >0)
        {
            Date d = getDateFromTextServer(date);
        
            Date today = new Date();
            Calendar c=Calendar.getInstance(Locale.US);
            int numday = 0;
           
            System.out.println("today : " + today.toString());
            System.out.println("visit : " + d.toString());
        
            
            
            if(today.after(d))
            {    c.setTimeInMillis(today.getTime() - d.getTime());
                 numday =c.get(Calendar.DATE) -1 ;
                 numhour = c.get(Calendar.HOUR) -7;
                 System.out.println("Mumber day : " + numday);
                 System.out.println("Mumber hour : " + numhour);
                 if(numday >0)
                     numhour = numhour + numday*24;
            }
            else
            {   System.out.println("Error date : Data must lest today");
                numhour = -1;
            }
            d = null;
            today = null;
            c = null;
        }
        else
            numhour = -1;
        return numhour;
        
     }
  
     /**
    * ใช้ในการคำนวณ หา จำนวนสัปดาห์ โดย
    * ข้อมูลเข้า เป็น String รูปแบบ yyyy-mm-dd,hh:mm:ss
    * ข้อมูลออก เป็นจำนวน สัปดาห์ 
    */
     public static int countWeek(String date)
     {  int numweek = 0;
        if(date != null || date.length() >0)
        {
            Date d = getDateFromText(date);
        
            Date today = new Date();
            Calendar c=Calendar.getInstance();
                      
            if(today.after(d))
            {    c.setTimeInMillis(today.getTime() - d.getTime());
                 numweek =c.get(c.WEEK_OF_YEAR) -1;
                 
                 //    System.out.println(numweek);
            }
            else
            {   System.out.println("Error date : วันที่ ต้องน้อยกว่าวันปัจจุบัน");
                numweek = -1;
            }
            d = null;
            today = null;
            c = null;
        }
        else
            numweek = -1;
        return numweek;
        
     }
     
    /**
    * ใช้ในการคำนวณ หา จำนวนวัน โดย
    * ข้อมูลเข้า เป็น String รูปแบบ yyyy-mm-dd,hh:mm:ss หรือ yyyy-mm-dd
    * ข้อมูลออก เป็นจำนวน ชั่วโมง
    */
     public static int countDay(String date)
     {  int numday = 0;
        if(date != null || date.length() >0) 
        {    
            Date d = getDateFromText(date);
            Date today = new Date();
            Calendar c=Calendar.getInstance();
            
            if(today.after(d))
            {    c.setTimeInMillis(today.getTime() - d.getTime());
                 numday =c.get(Calendar.DATE) -1;
            }
            else
            {   System.out.println("Error date : วันที่ ต้องน้อยกว่าวันปัจจุบัน");
                numday = -1;
            }
            d = null;
            today = null;
            c = null;
        }
        else
            numday = -1;
        
        return numday;
        
     }
     
     /**
    * ใช้ในการคำนวณ หา จำนวนวัน โดย
    * ข้อมูลเข้า เป็น String รูปแบบ yyyy-mm-dd,hh:mm:ss หรือ yyyy-mm-dd จำนวน 2 ชุด 
    * คือ วันที่เริ่มต้น และวันที่ที่สิ้นสุด
    * ข้อมูลออก เป็นจำนวนวัน
    */
     public static int countDayByTwoDate(String begin_date, String end_date)
     {  int numday = 0;
        if(begin_date != null || begin_date.length() >0) 
        {    
            Date begin = getDateFromText(begin_date);
            Date end = getDateFromText(end_date);
            Calendar c = Calendar.getInstance();
            
            if(end.after(begin))
            {    
                c.setTimeInMillis(end.getTime() - begin.getTime());
                 numday = c.get(Calendar.DATE) -1;
            }
            else
            {   System.out.println("Error date : วันที่ Admit ต้องน้อยกว่าวันที่ Discharge");
                numday = -1;
            }
            begin = null;
            end = null;
            c = null;
        }
        else
            numday = -1;
        
        return numday;
        
     }
     
    public static void main(String[] argv)
    {
     //   System.out.println(DateUtil.getGuiDateTime("2547-04-21"));
     //   System.out.println(DateUtil.countDate("2546-12-31,12:12:12"));
     
     //   System.out.println("Hour : " + DateUtil.countHour("2547-04-21,13:40:12"));
    //    System.out.println("Day : " + DateUtil.countDay("2547-04-21,13:40:12"));
    //    System.out.println("---------------------------------------------------");
  //       System.out.println("DATE : " + DateUtil.getGuiBDate("25/01/2546"));
  //      System.out.println("---------------------------------------------------");
 //        System.out.println("DATE : " + DateUtil.getGuiBDate("5/1/2546"));
   //    DateUtil du = new DateUtil();
 //        System.out.println(DateUtil.calPregnant("2547-03-01"));
        // System.out.println(DateUtil.isToday(DateUtil.getDateFromText("2547-05-01")));
        // System.out.println(DateUtil.isDay(new Date()));
    //    System.out.println(DateUtil.checkAfterTime("07:30")); 
    //    System.out.println(DateUtil.checkBeforeTime("07:30"));
     //   System.out.println(DateUtil.getDateToStringShort(new Date(),true));
       //  System.out.println(DateUtil.countHourServer("2547-09-23,20:08:00"));
     //    System.out.println(DateUtil.beforDate(DateUtil.getDateFromText("2547-07-15"),new Date()));
       // System.out.println("2547-04-01  :" + du.calculateAgeLong("2547-04-01,12:12:12"));
         
         System.out.println(DateUtil.checkTimeLocalThai());
    }
}
