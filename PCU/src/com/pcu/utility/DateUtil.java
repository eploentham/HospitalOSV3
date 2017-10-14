/*
 * DateUtil.java
 *
 * Created on 20 �Զع�¹ 2548, 11:06 �.
 */

package com.pcu.utility;
import java.util.*;
import com.hospital_os.utility.*;
import com.hospital_os.usecase.connection.*;
import java.text.SimpleDateFormat;
/**
 *
 * @author noom
 *
 */
public class DateUtil extends com.hosv3.utility.DateUtil{
//    public String year;
//    public String month;
//    public String date;
//    public String hour;
//    public String minute;
    /** Creates a new instance of DateUtil */
    public DateUtil() {
    }
    public static String getDateFromPattern(String pattern)
    {       String dateString = new String();
           java.util.Date today = new java.util.Date();
            java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(pattern);
        
            try {
                dateString = formatter.format(today);
                
            
            } catch (IllegalArgumentException iae) {
                dateString = null;
            }
            formatter = null;
            return dateString;
    }
    
   
    
    // �ʴ����ب�ԧ�������͡㹪�ͧ�����˹�� socialdata ��ʴ�㹪�ͧ���آͧ currentvisit
    /**
     *@deprecated henbe unused ����� calculateAge ᷹
     **/
    public static String calculateAgeShort(String year,String curr_year)
    {
        int age = Integer.parseInt(curr_year) - Integer.parseInt(year);
        return String.valueOf(age);
    }    


    public static String calPregnant(String date)
    {
        if(date.length() >=8)
        {
         //   Constant.println(date);
            Date d = getDateFromText(date);
            int dd =0;
            int mm =0;
            int yyyy=0;
            Calendar c=Calendar.getInstance();
            Calendar c2=Calendar.getInstance();
            c.setTimeInMillis(d.getTime());
            dd = c.get(c.DATE)+280;
            mm = c.get(c.MONTH);
            yyyy = c.get(c.YEAR);
            c.set(yyyy,mm,dd);
            String yyyy1 ="0000" + String.valueOf(c.get(c.YEAR));
            String mm1 = "00" + String.valueOf(c.get(c.MONTH) + 1);
            String dd1 = "00" + String.valueOf(c.get(c.DATE));
            yyyy1=yyyy1.substring(yyyy1.length()-4,yyyy1.length());
            mm1=mm1.substring(mm1.length()-2,mm1.length());
            dd1=dd1.substring(dd1.length()-2,dd1.length());
     //       Constant.println(dd1+"/" + mm1 + "/" +yyyy1);
            return dd1+"/" + mm1 + "/" +yyyy1;
        }
        else
            return "";
    }
//henbe comment 100253 kong ��������������͹�ѹ�Ѻ pregnant �������ҷ���
    //�����ҿѧ�ѹ����Դ���������ͧ������١
    public static String calLmp(String date)
    {
        if(date.length() >=8)
        { 
         //   Constant.println(date);
            Date d = getDateFromText(date);
            int dd =0;
            int mm =0;
            int yyyy=0;
            
            
            Calendar c=Calendar.getInstance();
            Calendar c2=Calendar.getInstance();
            
            
            
            c.setTimeInMillis(d.getTime());
            dd = c.get(c.DATE)-280;
            mm = c.get(c.MONTH);
            yyyy = c.get(c.YEAR);
            c.set(yyyy,mm,dd);
            
            
            String yyyy1 ="0000" + String.valueOf(c.get(c.YEAR));
            String mm1 = "00" + String.valueOf(c.get(c.MONTH) + 1);
            String dd1 = "00" + String.valueOf(c.get(c.DATE));
            yyyy1=yyyy1.substring(yyyy1.length()-4,yyyy1.length());
            mm1=mm1.substring(mm1.length()-2,mm1.length());
            dd1=dd1.substring(dd1.length()-2,dd1.length());
            
     //       Constant.println(dd1+"/" + mm1 + "/" +yyyy1); 
            return dd1+"/" + mm1 + "/" +yyyy1;
        }
        else
            return "";
    }
    
    /**
     *@deprecated henbe unused
     */
    public static String calculateAge(String date,ConnectionInf cinf){
        return calculateAge(date,cinf,false);
    }
    /**
     *@deprecated henbe unused
     */
    public static String calculateAge(String date,ConnectionInf cinf,boolean long_mode) {
        return "";
    }
    public static String calculateAgeLong(String date_in,String cur_date){       
        return calculateAge(date_in,cur_date);
    }
    
    /**
     *  �ŧ������ �ҡ��ͤ��� �� �ѹ���
     *  �ٻẺ �ͧ��������� ��   yyyy-mm-dd,hh:nn:ss
     */
    public static Date getDateFromText(String text)  {
       Calendar c=Calendar.getInstance();
       if(text==null || text.length()<10) return null;
       try{
        int yyyy = Integer.parseInt(text.substring(0,4));
        int mm = Integer.parseInt(text.substring(5,7))-1;
        int dd = Integer.parseInt(text.substring(8,10));
        c.set(yyyy,mm,dd);
        if(text.length()>=17){
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
     *  �ŧ������ �ҡ��ͤ��� �� �ѹ���
     *  �ٻẺ �ͧ��������� ��   yyyy-mm-dd,hh:nn:ss
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
            Constant.println("Year : " + yyyy);
           return c.getTime();
           }catch(Exception e) {
               e.printStackTrace();
               return null;
           }
        
    }
    
    /**
     * �ŧ��������Ҩҡ �ѹ��� �ٻẺ dd/mm/yyyy
     * ��� yyyy-mm-dd
     */
    public static String getGuiBDate(String date)
    {
        if(date==null) return "";
       if(date.length() < 9 ) return "";
       try{
          String temp = new String();
            //    Constant.println("Real Date : " + text);
            //    Constant.println(text.substring(0,(text.indexOf("/"))) );
                String dd = date.substring(0,date.indexOf("/"));
                int ddd = Integer.parseInt(dd);
                String d = "00" + String.valueOf(ddd);
                d=d.substring(d.length()-2,d.length());
           //     Constant.println("DATE : " + dd);
                temp  = date.substring(dd.length()+1);
          //      Constant.println(temp);
                int m = Integer.parseInt(temp.substring(0,temp.indexOf("/")));
                String mm = "00" + String.valueOf(m);
                mm=mm.substring(mm.length()-2,mm.length());
             //   Constant.println("MONHT : " + mm);
                temp = temp.substring(temp.indexOf("/")+1);
                // Constant.println(temp);
                 String yyyy = temp.substring(0,4);
            //     Constant.println(yyyy);
            return yyyy+ "-" + mm + "-"+d; 
       }catch(Exception e) {
           e.printStackTrace();
           return null;
       }
    }
    
    /**
     *  ��㹡�õ�Ǩ�ͺ���� ��������ҡ�͹ ������ѧ ����º�Ѻ���һѨ�غѹ
     *  ������� �Ѩ�غѹ ���¡������ҷ����º ���觤�� �� 0 
     *  ��������� �� hh:mm:ss
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
   
//        Constant.println("time " + ctime.getTime().toString());
     //   Constant.println("today " + ctoday.getTime().toString());
        //Constant.println(ctime.before(ctoday));
       // Constant.println(ctoday.before(ctime));
    
        
        return ctoday.before(ctime);
    }
    
    /**
     *  ��㹡�õ�Ǩ�ͺ���� ��������ҡ�͹ ������ѧ ����º�Ѻ���һѨ�غѹ
     *  ������� �Ѩ�غѹ �ҡ���ҡ������ҷ����º ���觤�� �� 0 
     *  ��������� �� hh:mm:ss
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
          
        
        
        
        
//        Constant.println("time " + ctime.getTime().toString());
     //   Constant.println("today " + ctoday.getTime().toString());
        //Constant.println(ctime.before(ctoday));
    //    Constant.println(ctoday.after(ctime));
         return ctoday.after(ctime);
    }
    
    /**
     *  �ŧ������ �ѹ��� ������� Ẻ yyyy-mm-dd : MM:ss
     *  ����ѹ��� �ٻẺ��ͤ���(Ẻ���) ��Ш�����ʴ� ����������� 
     *  ��� showtime �� true ��� �ʴ����Ҵ���
     *              �� false ��� ����ʴ�����
     *@deprecated old version has bug
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
                  // Constant.println(date);
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
     *  �ŧ������ �ѹ��� ������� Ẻ yyyy-mm-dd : MM:ss
     *  ����ѹ��� �ٻẺ��ͤ���(Ẻ����Ţ������) ��Ш�����ʴ� ����������� 
     *  ��� showtime �� true ��� �ʴ����Ҵ���
     *              �� false ��� ����ʴ�����
     */
    public static String getDateToStringDigit(Date date,boolean showtime)
    {   String pattern = "dd MM yyyy HH:mm";
         String dateString = new String();
            java.text.SimpleDateFormat formatter =null;
        
            try {
                if(showtime)
                {   formatter = new java.text.SimpleDateFormat(pattern); 
                    dateString = formatter.format(date);
                }
                else
                {
                    pattern = "dd MM yyyy";
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
     *  �ŧ������ �ѹ��� ������� Ẻ yyyy-mm-dd : MM:ss
     *  ����ѹ��� �ٻẺ��ͤ���(Ẻ���) ��Ш�����ʴ� ����������� 
     *  ��� showtime �� true ��� �ʴ����Ҵ���
     *              �� false ��� ����ʴ�����
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
     *  �ŧ������ �ѹ��� ������� Ẻ yyyy-mm-dd : MM:ss
     *  ����ѹ��� �ٻẺ��ͤ���(Ẻ���) ��Ш�����ʴ� ����������� 
     *  ��� showtime �� true ��� �ʴ����Ҵ���
     *              �� false ��� ����ʴ�����
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
    //GUtil.convertFieldDate
    public static String convertFieldDate(String text){
        return getGuiDateTime(text);
    }
    //GUtil.convertFieldDate
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
     *  �ӡ�����º��º�ѹ��� �� 
     *  datestart ��͹ enddate ������� true
     */
    public static boolean beforeDate(Date startDate, Date endDate)
    {
            return startDate.before(endDate);
    }
    
    
    /**
     *  ��Ǩ�ͺ��� �ѹ�����������ѹ����
     *  ��������� �� Object Date
     *  �������͡ �� 
     *  1  �ѹ�ҷԵ��
     *  2  �ѹ�ѹ���
     *  3  �ѹ�ѧ���
     *  4  �ѹ�ظ
     *  5  �ѹ����ʺ��
     *  6  �ѹ�ء��
     *  7  �ѹ�����
                              
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
         Constant.println(today.toString());
         return true;
    }
    
    
    /**
     *  �ӡ�����º��º�ѹ��� �� 
     *  datestart ��͹ enddate ������� true
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
                //Constant.println(numday + " " + nummonth + " " + numyear);
                today = null;
                c = null;
                
           if(numday == 0 && nummonth ==0 && numyear == 0)
               return true;
           else
               return false;
    }
    /**
    * ��㹡�äӹǳ �� �ӹǹ������� ��
    * ��������� �� String �ٻẺ yyyy-mm-dd,hh:mm:ss
    * �������͡ �繨ӹǹ �������
    */
     public static int countHourServer(String date)
     {  int numhour = 0;
        if(date != null || date.length() >0)
        {
            Date d = getDateFromTextServer(date);
        
            Date today = new Date();
            Calendar c=Calendar.getInstance(Locale.US);
            int numday = 0;
           
//            Constant.println("today : " + today.toString());
//            Constant.println("visit : " + d.toString());
        
            
            
            if(today.after(d))
            {    c.setTimeInMillis(today.getTime() - d.getTime());
                 numday =c.get(Calendar.DATE) -1 ;
                 numhour = c.get(Calendar.HOUR) -7;
                 Constant.println("Mumber day : " + numday);
                 Constant.println("Mumber hour : " + numhour);
                 if(numday >0)
                     numhour = numhour + numday*24;
            }
            else
            {   Constant.println("Error date : Data must lest today");
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
    * ��㹡�äӹǳ �� �ӹǹ������� ��
    * ��������� �� String �ٻẺ yyyy-mm-dd,hh:mm:ss
    * �������͡ �繨ӹǹ �������
    */
     /*
     public static int countHour(String date,ConnectionInf cc)
     {  int numhour = 0;
        if(date != null || date.length() >0)
        {
            Date d = getDateFromText(date);
            DateTime dt = new DateTime(cc);
            Date today;
            String dated;
            try{
            dated = dt.getDate();
            }
            catch(Exception ex)
            {
                dated = null;
            }
            if(dated !=null)
                today = getDateFromText(dated);
            else
                today = new Date();
            Calendar c=Calendar.getInstance();
            int numday = 0;
            Constant.println(today.toString());
            Constant.println(d.toString());
            if(today.after(d))
            {    c.setTimeInMillis(today.getTime() - d.getTime());
                 numday =c.get(Calendar.DATE) -1;
                 numhour = c.get(Calendar.HOUR) -7;
                 if(numday >0)
                     numhour = numhour + numday*24;
            }
            else
            {   Constant.println("Error date : �ѹ��� ��ͧ���¡����ѹ�Ѩ�غѹ");
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
      */
     
     /**
    * ��㹡�äӹǳ �� �ӹǹ�ѻ���� ��
    * ��������� �� String �ٻẺ yyyy-mm-dd,hh:mm:ss
    * �������͡ �繨ӹǹ �ѻ���� 
    *  @deprecated henbe unused
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
                 
                 //    Constant.println(numweek);
            }
            else
            {   //Constant.println("Error date : �ѹ��� ��ͧ���¡����ѹ�Ѩ�غѹ");
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
    * ��㹡�äӹǳ �� �ӹǹ�ѹ ��
    * ��������� �� String �ٻẺ yyyy-mm-dd,hh:mm:ss ���� yyyy-mm-dd
    * �������͡ �繨ӹǹ ��������繤�� ź�����ӡѴ
    */
     public static int countDateDiff(String date,ConnectionInf cinf)
     {
        int numday = 0;
        if(date != null || date.length() >0) 
        {    
            Date d = getDateFromText(date);
            if(d == null){
                return 0;
            }
            Date today = DateUtil.getDateFromText(DateTime.getTextCurrentDate(cinf));
            Calendar c=Calendar.getInstance();
            if(today.after(d))
            {
                c.setTimeInMillis(today.getTime() - d.getTime());
                numday =c.get(Calendar.DATE) -1;
                numday = 0 - numday;
            }
            else
            {
                c.setTimeInMillis(d.getTime() - today.getTime());
                numday =c.get(Calendar.DATE) -1;
            }
            d = null;
            today = null;
            c = null;
        }
        return numday;
     }   
     
     /**
    * ��㹡�äӹǳ �� �ӹǹ�ѹ ��
    * ��������� �� String �ٻẺ yyyy-mm-dd,hh:mm:ss ���� yyyy-mm-dd
    * �������͡ �繨ӹǹ �������
    */
     public static int countDay(String date,ConnectionInf cinf)
     {  int numday = 0;
        if(date != null || date.length() >0) 
        {    
            Date d = getDateFromText(date);
            Date today = DateUtil.getDateFromText(DateTime.getTextCurrentDate(
            cinf));
            Calendar c=Calendar.getInstance();
            
            if(today.after(d))
            {    c.setTimeInMillis(today.getTime() - d.getTime());
                 numday =c.get(Calendar.DATE) -1;
            }
            else
            {   //Constant.println("Error date : �ѹ��� ��ͧ���¡����ѹ�Ѩ�غѹ");
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
     *  �ӡ�����º��º�ѹ��� �� 
     *  datestart ��͹ enddate ������� true
     */
    public static boolean isToday(Date date,ConnectionInf cinf)
    {       int numday =0;
            int nummonth = 0;
            int numyear = 0;
            Date today = DateUtil.getDateFromText(DateTime.getTextCurrentDate(
            cinf));
            Calendar c=Calendar.getInstance();
//            Constant.println("Today : " + today.toString() );
//            Constant.println("Date : " + date.toString() );
                c.setTimeInMillis(today.getTime() - date.getTime());
                numday =c.get(Calendar.DAY_OF_YEAR) -1;
                nummonth = c.get(Calendar.MONTH);
                numyear =  c.get(Calendar.YEAR) - 2513 ;// -543 -1970;
                Constant.println(numday + " " + nummonth + " " + numyear);
                today = null;
                c = null;
                
           if(numday == 0 && nummonth ==0 && numyear == 0)
               return true;
           else
               return false;
    }
    /**
    * ��㹡�äӹǳ �� �ӹǹ�ѹ ��
    * ��������� �� String �ٻẺ yyyy-mm-dd,hh:mm:ss ���� yyyy-mm-dd
    * �������͡ �繨ӹǹ �������
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
            {   //Constant.println("Error date : �ѹ��� ��ͧ���¡����ѹ�Ѩ�غѹ");
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
    * ��㹡�äӹǳ �� �ӹǹ�ѹ ��
    * ��������� �� String �ٻẺ yyyy-mm-dd,hh:mm:ss ���� yyyy-mm-dd �ӹǹ 2 �ش 
    * ��� �ѹ���������� ����ѹ���������ش
    * �������͡ �繨ӹǹ�ѹ
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
            {   Constant.println("Error date : �ѹ��� Admit ��ͧ���¡����ѹ��� Discharge");
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
     
       
    public static long initDay(String startDate,String endDate) 
    {
        try{
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd",new Locale("en","US")); //PanelBeforeMch ��ͧ���� Format yy-MM-dd          
            Calendar calStart = Calendar.getInstance();
            Calendar calEnd = Calendar.getInstance();
            calStart.setTime(sdFormat.parse(startDate)); 
            calEnd.setTime(sdFormat.parse(endDate)); 
            long a = calEnd.getTimeInMillis() - calStart.getTimeInMillis();  
            return (a/(86400000L));
        }catch(Exception e){
            return 0;
        }
    }
    
    public static long initHour(String startDate,String endDate) throws Exception
    {
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss",new Locale("en","US")); 
    	Calendar calStart = Calendar.getInstance();
    	Calendar calEnd = Calendar.getInstance();
    	calStart.setTime(sdFormat.parse(startDate));
        calEnd.setTime(sdFormat.parse(endDate));
        long a = calEnd.getTimeInMillis() - calStart.getTimeInMillis(); 
	return ((a%(86400000L))/3600000L);
    }
     
    

    public static void main(String[] argc){
        System.out.println(DateUtil.countWeek("2552-02-01","2552-02-07"));
        System.out.println(DateUtil.countWeekS("2552-02-01","2552-02-08"));
        System.out.println(DateUtil.countWeek("2552-02-01","2552-02-09"));
        System.out.println(DateUtil.countWeek("2552-02-01","2552-07-01"));
    }

    public static int calculateAgeYearInt(String date, String datetime) {
        
        Date d = getDateFromText(date);
        if(d==null) 
            return -1;
        Date dt = DateUtil.getDateFromText(datetime);
        if(dt==null)
            return -1;
        Calendar c=Calendar.getInstance();
        Calendar c2=Calendar.getInstance();
        c.setTimeInMillis(d.getTime());
        c2.setTimeInMillis(dt.getTime());
        return c2.get(Calendar.YEAR) - c.get(Calendar.YEAR);
    }


    
}
