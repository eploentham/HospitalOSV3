/*
 * DateTime.java
 *
 * Created on 16 มกราคม 2548, 12:52 น.
 */
package com.pcu.utility;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import java.util.*;
import java.sql.ResultSet;
/**
 *
 * @author  tong
 */
public class DateTime {
    
    /** Creates a new instance of DateTime */
    public ConnectionInf theConnectionInf;
    String sql ="";
    String data = "";
    ResultSet rs = null;
    String filename = "CONNECTION_FILE";
    Properties settings;
    public DateTime(ConnectionInf c) {
         //filename = getTextBundleConfig(filename);
         theConnectionInf = c;
         //readFileConnection();
         //loadServer(c);
    }
    public static String getTextBundleConfig(String str)
    {
        if(str.trim().equals(""))return "";
        try{
            return java.util.ResourceBundle.getBundle("com/hospital_os/property/Config").getString(str);
        }catch(Exception e){ 
           // Constant.println(str + ":Not Found ");
            return str;
        }
    }   
    
    
    /*
    public boolean readFileConnection()
   {
       boolean var= false;
       String valueRemark = new String();
       String config = IOStream.readInputDefault(filename);
       if( settings == null)
            settings= new Properties();
        try
        { 
            //ถอดรหัสตรงนี้
            String conf = Secure.decode(config);
            String tmp = new String();
            String value = new String();
            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n")+1);
            value = tmp.substring(tmp.indexOf("=")+1);
            settings.put("DONT_REMIND", value.trim());
            valueRemark = value.trim();
            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n")+1);
            value = tmp.substring(tmp.indexOf("=")+1);
            settings.put("SERVER", value.trim());
            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n")+1);
            value = tmp.substring(tmp.indexOf("=")+1);
            settings.put("DATABASE", value.trim());
            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n")+1);
            value = tmp.substring(tmp.indexOf("=")+1);
            settings.put("PORT", value.trim());
           tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n")+1);
            value = tmp.substring(tmp.indexOf("=")+1);
            settings.put("USERNAME", value.trim());
            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n")+1);
            value = tmp.substring(tmp.indexOf("=")+1);
            settings.put("PASSWORD", value.trim());
            tmp = conf.substring(0, conf.indexOf("\n"));
            conf = conf.substring(conf.indexOf("\n")+1);
            value = tmp.substring(tmp.indexOf("=")+1);
            settings.put("DATABASETYPE", value.trim());
        //    Constant.println("Remark------------------" +valueRemark);
            if(valueRemark.equals("1"))
                var = true;
            else
                var = false;
            //  Constant.println("VARDFDSDFSDFDSF------------------" + var);
            valueRemark = null;  
        }
        catch(Exception e) 
        {
            Constant.println("ไม่พบแฟ้ม " + filename);
            settings = null;
            var = false;
        }
        return var;
    
   }*/
   
    /**
     *  ใช้ในการแปลงให้อยู่ในรูป ตาม format ที่กำหนดไว้
     *  dd-mm-yyyy, dd/mm/yyyy
     */
    public String getDate(String format) throws Exception
    {
        sql = "select CURRENT_DATE";
        data = eQuery(sql);
        data = convertThaiDate(data);
        String[] d = data.split("-");
        
        data= d[2] + "/" + d[1] + "/" + d[0];
        return data;
    }
    
    public String getDate() throws Exception
    {
        sql = "select CURRENT_DATE";
        data = eQuery(sql);
        data = convertThaiDate(data);
        return data;
    }
    
    public String getTime() throws Exception
    {
        sql = "select CURRENT_TIME";
        data = eQuery(sql);
        data = convertThaiTime(data);
        return data;
    }   
    
    
    private String eQuery(String sql) throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        while(rs.next()) 
	{
            data = rs.getString(1);
        }
        rs = null;
        return data;
    } 
    

	/**
     * จะให้ค่าเป็น วันที่ และเวลา
     * โดยรูปแบบ จะเป็น yyyy-MM-dd
     */
    public static String getTextCurrentDate(ConnectionInf cc)
   {
       cc.open();
       try{
            DateTime dt = new DateTime(cc);
            String date = dt.getDate();
            dt = null;
            return date;
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            Calendar c = Calendar.getInstance(Locale.US);
      
      
            c.setTimeInMillis(System.currentTimeMillis());
            String yyyy ="0000" + String.valueOf(c.get(c.YEAR)+543);
            String mm = "00" + String.valueOf(c.get(c.MONTH) + 1);
            String dd = "00" + String.valueOf(c.get(c.DATE));
            yyyy=yyyy.substring(yyyy.length()-4,yyyy.length());
            mm=mm.substring(mm.length()-2,mm.length());
            dd=dd.substring(dd.length()-2,dd.length());
               return yyyy + "-" +  mm + "-" + dd;
        }
       finally{
           cc.close();
       }
    }
    
    /**
     * จะให้ค่าเป็น วันที่ และเวลา
     * โดยรูปแบบ จะเป็น yyyy-MM-dd
     */
    public static String getTextDate(ConnectionInf cc)
   {
       
       try{
            DateTime dt = new DateTime(cc);
            String date = dt.getDate("/");
            dt = null;
            return date;
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            Calendar c = Calendar.getInstance(Locale.US);
      
      
            c.setTimeInMillis(System.currentTimeMillis());
            String yyyy ="0000" + String.valueOf(c.get(c.YEAR)+543);
            String mm = "00" + String.valueOf(c.get(c.MONTH) + 1);
            String dd = "00" + String.valueOf(c.get(c.DATE));
            yyyy=yyyy.substring(yyyy.length()-4,yyyy.length());
            mm=mm.substring(mm.length()-2,mm.length());
            dd=dd.substring(dd.length()-2,dd.length());
               return dd + "/" + mm + "/" + yyyy;
        }
    }
    
    
    /**
     * จะให้ค่าเป็น วันที่ และเวลา
     * โดยรูปแบบ จะเป็น yyyy-MM-dd
     
    public static String getTextCurrentDateS(ConnectionInf cinf)
   {
       
        Calendar c = Calendar.getInstance(Locale.US);
        c.setTimeInMillis(System.currentTimeMillis());
        String yyyy ="0000" + String.valueOf(c.get(c.YEAR)+543);
        
        
        String mm = "00" + String.valueOf(c.get(c.MONTH) + 1);
        String dd = "00" + String.valueOf(c.get(c.DATE));
        yyyy=yyyy.substring(yyyy.length()-4,yyyy.length());
        mm=mm.substring(mm.length()-2,mm.length());
        dd=dd.substring(dd.length()-2,dd.length());
        //return yyyy + "/" +  mm + "/" + dd;
        return yyyy + "-" +  mm + "-" + dd;
    }
    
    /**
     * จะให้ค่าเป็น วันที่ และเวลา
     * โดยรูปแบบ จะเป็น hh:mm:ss
     */
    public static String getTextCurrentTime(ConnectionInf cc)
    {
        try{
            DateTime dt = new DateTime(cc);
            String date = dt.getTime();
            dt = null;
            return date;
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            String hh = "00" +  String.valueOf(c.get(c.HOUR_OF_DAY));
            String mm = "00" +  String.valueOf(c.get(c.MINUTE));
            java.text.DecimalFormat d= new java.text.DecimalFormat();
            d.applyPattern("00");
            String ss =    d.format(c.get(c.SECOND)); //String.valueOf(c.get(c.SECOND));//aaaaaa
            String am_pm = new String();
            if(c.get(c.AM_PM) ==0)
                 am_pm = "AM";
            else
               am_pm = "PM";
            mm=mm.substring(mm.length()-2,mm.length());
            hh=hh.substring(hh.length()-2,hh.length());
            return hh + ":" + mm + ":" + ss ;
        }
    }

    /**จะให้ค่าเป็น วันที่ และเวลา
     * โดยรูปแบบ จะเป็น yyyy-MM-dd,hh:mm:ss a
     *
     */
   public static String getTextCurrentDateTime(ConnectionInf cc)
    {
       cc.open();
        try{
            DateTime dt = new DateTime(cc);
            String date = dt.getDate() + "," + dt.getTime();
            dt = null;
            return date;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            String hh = "00" +  String.valueOf(c.get(c.HOUR_OF_DAY));
            String mm = "00" +  String.valueOf(c.get(c.MINUTE));
            java.text.DecimalFormat d= new java.text.DecimalFormat();
            d.applyPattern("00");
            String ss =    d.format(c.get(c.SECOND)); //String.valueOf(c.get(c.SECOND));//aaaaaa
            String am_pm = new String();
            if(c.get(c.AM_PM) ==0)
                 am_pm = "AM";
            else
               am_pm = "PM";
            mm=mm.substring(mm.length()-2,mm.length());
            hh=hh.substring(hh.length()-2,hh.length());
            return hh + ":" + mm + ":" + ss ;
        }
       finally{
           cc.close();
       }
    }
   
   
    public String convertThaiDate(String date) {
        if(date.length()!=10)
            return null;
        
        String year = date.substring(0,4);
        String month = date.substring(5,7);
        String day = date.substring(8,10);
         /*
         Constant.println("year : " +year);
         Constant.println("month : " +month);
         Constant.println("day : " +day);
          */
        int y = Integer.parseInt(year);
        
        String yyyy ="0000" + String.valueOf(y+543);
        yyyy=yyyy.substring(yyyy.length()-4,yyyy.length());
        month=month.substring(month.length()-2,month.length());
        day=day.substring(day.length()-2,day.length());
        //return yyyy + "/" +  mm + "/" + dd;
        return yyyy + "-" +  month + "-" + day;
        
    }
    
    public String convertThaiTime(String time) {
        
        if(time.length()<8)
            return null;
        String hour = time.substring(0,2);
        String minute = time.substring(3,5);
        String second = time.substring(6,8);
        /*
        Constant.println("hour : " +hour);
        Constant.println("minute : " +minute);
        Constant.println("second : " +second);
         */
        return hour + ":" + minute + ":" + second ;
        
    }
    
}
