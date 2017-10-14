/*
 * showTime.java
 *
 * Created on 28 เมษายน 2548, 17:52 น.
 */

package com.hospital_os.utility;
import java.io.*;
import java.util.*;
/**
 *
 * @author  Administrator
 */
public class ShowTime {
    
    /** Creates a new instance of showTime */
    final String filenamesql = "testsql1.txt";
    final String filenametime = "testtime.txt";
    final String pattern = "dd-MM-yyyy hh:mm:ss";
    
    private static boolean save = false;
    private String message = "";
    private String sql = "";
    private long start ,stop;
    private double temp;
    private String data = "";
    
    FileOutputStream fos =null;
    Writer out = null;
    Calendar c =null;
    java.util.Date today;
    public ShowTime(int choose) {
        init(choose);
        
        
        
    }
    public ShowTime()
    {
    }
    
    public void setText(String text)
    {
        message = text;
    }
    
    public void init(int choose)
    {
        try{
            if(choose == 1)
            {   fos = new FileOutputStream(filenamesql);
            }
            else
            {    fos = new FileOutputStream(filenametime);
            }
            if(save)
            {
                out = new OutputStreamWriter(fos, "UTF8");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
    }
    
    
    public void start(String SQL)
    {
        
        start = System.currentTimeMillis();
        sql = SQL;
    }
    public void start()
    {
        start = System.currentTimeMillis();
    }
    
    
    public void print()
    {
        data = speace();
        data = data + "time \t: "+ getDateTime() + " \n";
        data = data + message + " \t: Use Time : " + temp + " sec." + " \n";
        if(sql != null && sql.length() >0)
        {
            data = data + "SQL \t: " + sql + "\n";
        }
        data = data + speace();
        Constant.print(data);
    }
    
    public String  getDateTime()
    {
        
        String dateString = new String();
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
    
    private double totalTime()
    {
        return (stop - start)/1000.0;
    }
    
    public String speace()
    {
        return "****************************************" + " \n";
    }
    
    
   public void stop()
   {
       stop = System.currentTimeMillis();
       temp = totalTime();
     /*  if( temp > 0.1 )*/
     /*  {*/
           this.print();
           
            writeOutput(data);
      /* }*/
       
       message = null;
       sql =null;
       
   }
    
   
   
   public void writeOutput(String str) 
   {
       /*try 
       {
           Constant.println("save to file");
           if(save)
            out.write(str);
           out.close();
       } 
       catch (IOException e) 
       {
           e.printStackTrace(Constant.getPrintStream());
       }*/
   }
   
   public void closefile()
   { try{
         if(save)
            out.close();
     }
     catch(Exception ex){
         
     }
     
   }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* TODO code application logic here*/
        ShowTime st = new ShowTime(2);
      /*  st.start("select * from t_patient;");*/
        st.start();
       /* st.setText("test");*/
        st.stop();
        st.closefile();
    }
    
}
