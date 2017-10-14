/*
 * IOStream.java
 *
 * Created on 30 กรกฎาคม 2548, 14:12 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportcenter.utility;

/**
 *
 * @author Noom
 */
import java.io.*;
import java.util.*;
import java.sql.*;
public class IOStream 
{
   public static void writeOutput(String str, String filename) 
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
           e.printStackTrace();
       }
   }
   public static String readInput(String filename) 
   {
      StringBuffer buffer = new StringBuffer();
      try 
      {
          FileInputStream fis = new FileInputStream(filename);
          InputStreamReader isr = new InputStreamReader(fis, "UTF8");
          Reader in = new BufferedReader(isr);
          int ch;
          while ((ch = in.read()) > -1) 
          { //System.out.println(ch);
             buffer.append((char)ch);
          }
          in.close();
          return buffer.toString();
      }
      catch (IOException e)
      {
          e.printStackTrace();
      }
      
      return null;
   }
   /*
    * Default encoding write
    */
   public static void writeOutputDefault(String str, String filename) 
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
           e.printStackTrace();
       }
   }
   /*
    * Default encoding read
    * cut sql comment
    */
   public static String readInputDefaultOld(String filename)
   {
       System.out.println("filename readInputDefault " + filename);
      StringBuffer buffer = new StringBuffer();
      try 
      {
          FileInputStream fis = new FileInputStream(filename);
          InputStreamReader isr = new InputStreamReader(fis);
          //System.out.println(isr.getEncoding());
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
          e.printStackTrace();
      }
      return null;
   }
   /*
    * Default encoding read
    * cut sql comment
    */
   public static String readInputDefault(String filename)
   {
       System.out.println("filename readInputDefault " + filename);
      StringBuffer buffer = new StringBuffer();
      try 
      {
          FileInputStream fis = new FileInputStream(filename);
          InputStreamReader isr = new InputStreamReader(fis);
          //System.out.println(isr.getEncoding());
          BufferedReader in = new BufferedReader(isr);
          String ch = "";
          while((ch=in.readLine())!= null){
              if (ch.length() <= 0) {
                  continue;
              }
//                System.err.println("str = " + ch);
              int i_mob = (int) ch.charAt(0);
              if (i_mob == 65279) {
//                    System.out.println("str = " + ch);
                  ch = ch.substring(1);
//                    System.out.println("str2 = " + ch);
              }
              if (!ch.startsWith("--")) {
                  buffer.append("\n ");
                  buffer.append(ch);
              }
//              ch = in.readLine();
          }
          in.close();
//          System.out.println("buffer ++++"+ buffer.toString());
          return buffer.toString();
      }
      catch (IOException e) 
      {
          e.printStackTrace();
      }
      return null;
   }
   public static void Convert2Ascii(String in, String out)
   {
       writeOutputDefault(readInput(in), out);
   }
   public static void Convert2Unicode(String in, String out)
   {
       writeOutput(readInputDefault(in), out);
   }
   
   public static String Unicode2ASCII(String unicode) { 
        StringBuffer ascii = new StringBuffer(unicode); 
        int code; 
        for(int i = 0; i < unicode.length(); i++) { 
            code = (int)unicode.charAt(i); 
            if ((0xE01<=code) && (code <= 0xE5B )) 
            {
                ascii.setCharAt( i, (char)(code - 0xD60));
            } 
        } 
        return ascii.toString(); 
   } 
   
    public static String ASCII2Unicode(String ascii) { 
        StringBuffer unicode = new StringBuffer(ascii); 
        int code; 
        for(int i = 0; i < ascii.length(); i++) { 
            code = (int)ascii.charAt(i); 
            if ((0xA1 <= code) && (code <= 0xFB)) 
            {
                unicode.setCharAt( i, (char)(code + 0xD60));
            } 
        } 
        return unicode.toString(); 
    } 
   
   public static void main(String args[])
   {
       IOStream.Convert2Unicode("C:\\in\\as.html","C:\\in\\uni.html");
   }
}

