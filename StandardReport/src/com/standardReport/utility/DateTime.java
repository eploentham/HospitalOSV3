/*
 * DateTime.java
 *
 * Created on 16 มกราคม 2548, 12:52 น.
 */
package com.standardReport.utility;
import java.util.*;
import java.sql.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.ConnectionDBMgr;
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
    public DateTime() {
         filename = Gutil.getTextBundleConfig(filename);
         readFileConnection();
         loadServer();
    }
    /**load server*/
    public void loadServer()
    {
         //= config.sendURL();
        if(settings != null)
       {//  String url = "jdbc:postgresql://"+setting.getProperty("SERVER") + ":" + setting.getProperty("PORT") + "/" + setting.getProperty("DATABASE");
             String username =  settings.getProperty("USERNAME");
             String password =settings.getProperty("PASSWORD");
             String url = new String();
              String driver = new String();
              CheckDataBaseType  theCheckDataBaseType = new CheckDataBaseType();
             int typedata = theCheckDataBaseType.getDataBaseType(settings.getProperty("DATABASETYPE"));
              String typeDatabase = String.valueOf(typedata);
              switch(typedata)
              {     case 0 : url = "jdbc:postgresql://"+settings.getProperty("SERVER") + ":" + settings.getProperty("PORT") + "/" + settings.getProperty("DATABASE");
                             driver = "org.postgresql.Driver";
                             break;   
                    case 1 : url = "jdbc:microsoft:sqlserver://"+settings.getProperty("SERVER") + ":" + settings.getProperty("PORT") + ";User=sa;Password=tong123;DatabaseName=" + settings.getProperty("DATABASE");
                             driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
                             break;
                    case 2 :   url = "jdbc:mysql://"+settings.getProperty("SERVER")+ ":" + settings.getProperty("PORT") + "/" +settings.getProperty("DATABASE");
                             driver = "org.gjt.mm.mysql.Driver";
                             break;
              }
             
              theConnectionInf = new ConnectionDBMgr(url, username,password,typeDatabase);
        }
    }
    
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
        //    System.out.println("Remark------------------" +valueRemark);
            if(valueRemark.equals("1"))
                var = true;
            else
                var = false;
            //  System.out.println("VARDFDSDFSDFDSF------------------" + var);
            valueRemark = null;  
        }
        catch(Exception e) 
        {
            System.out.println("ไม่พบแฟ้ม " + filename);
            settings = null;
            var = false;
        }
        return var;
    
   }
   
    
     public String getDate() throws Exception
    {
        sql = "select CURRENT_DATE";
        data = eQuery(sql);
        data = convertThaiDate(data);
        return data;
    }
    
     public String convertThaiDate(String date)
     { 
         if(date.length()!=10)
            return null;
         
         String year = date.substring(0,4);
         String month = date.substring(5,7);
         String day = date.substring(8,10);
         /*
         System.out.println("year : " +year);
         System.out.println("month : " +month);
         System.out.println("day : " +day);
          */
         int y = Integer.parseInt(year);
       
        String yyyy ="0000" + String.valueOf(y+543);
        yyyy=yyyy.substring(yyyy.length()-4,yyyy.length());
        month=month.substring(month.length()-2,month.length());
        day=day.substring(day.length()-2,day.length());
        //return yyyy + "/" +  mm + "/" + dd;
        return yyyy + "-" +  month + "-" + day;
      
     }
     
    public String getTime() throws Exception
    {
        sql = "select CURRENT_TIME";
        data = eQuery(sql);
        data = convertThaiTime(data);
        return data;
    }   
    
    public String convertThaiTime(String time)
    {
        
        if(time.length()<8)
            return null;
        String hour = time.substring(0,2);
        String minute = time.substring(3,5);
        String second = time.substring(6,8);
        /*
        System.out.println("hour : " +hour);
        System.out.println("minute : " +minute);
        System.out.println("second : " +second);
        */
        return hour + ":" + minute + ":" + second ;
       
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
    
    public static void main(String[] argv)
    {
        DateTime dt = new DateTime();
        try{
            System.out.println("Time: " + dt.getTime());
            System.out.println("Time: " + dt.getDate());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
