/*
 * ConfigSQLServer.java
 *
 * Created on 6 มกราคม 2547, 22:04 น.
 */

package com.hospital_os.utility;
import java.io.*;

/**
 *
 * @author  tong
 */
public class ConfigSQLServer {
    
    /** Creates a new instance of ConfigSQLServer */
    
    
    public ConfigSQLServer() {
        
    }
    
     public  void writeOutput(String str, String filename) 
   {
       try 
       {
           FileOutputStream fos = new FileOutputStream(filename);
           Writer out = new OutputStreamWriter(fos, "UTF8");
           out.write(str);
           out.close();
       } 
       catch (IOException e) 
       {
           e.printStackTrace(Constant.getPrintStream());
       }
   }

    
    public  String readInput(String filename) 
   {
      StringBuffer buffer = new StringBuffer();
      try 
      {
          FileInputStream fis = new FileInputStream(filename);
          InputStreamReader isr = new InputStreamReader(fis, "UTF8");
          Reader in = new BufferedReader(isr);
          int ch;
          while ((ch = in.read()) > -1) 
          {
             buffer.append((char)ch);
          }
          in.close();
          return buffer.toString();
      }
      catch (IOException e)
      {
          e.printStackTrace(Constant.getPrintStream());
      }
      return null;
   }
    
   
   /*
    * Default encoding write
    */
   public  void writeOutputDefault(String str, String filename) 
   {
       try 
       {
           FileOutputStream fos = new FileOutputStream(filename);
           Writer out = new OutputStreamWriter(fos);
           out.write(str);
           out.close();
       } 
       catch (IOException e) 
       {
           e.printStackTrace(Constant.getPrintStream());
       }
   }

   /*
    * Default encoding read
    */
   public  String readInputDefault(String filename)
   {
      StringBuffer buffer = new StringBuffer();
      try 
      {
          FileInputStream fis = new FileInputStream(filename);
          InputStreamReader isr = new InputStreamReader(fis);
          /*Constant.println(isr.getEncoding());
*/
          Reader in = new BufferedReader(isr);
          int ch;
          while ((ch = in.read()) > -1) 
          {
             buffer.append((char)ch);
          }
          in.close();
          return buffer.toString();
      } 
      catch (IOException e) 
      {
          /*e.printStackTrace(Constant.getPrintStream());
*/
      }
      return null;
   }
    
    
    public static void main(String[] argv)
    {   ConfigSQLServer cfg = new ConfigSQLServer();
        String data = "tong\ntest";
       /* cfg.writeOutput(data, "test.cfg");
*/
       /* cfg.writeOutputDefault(data, "test.cfg");
*/
       
        Constant.println("Data : " + cfg.readInputDefault("test.cfg") );
        
        
    }
    
    
   
    
}
