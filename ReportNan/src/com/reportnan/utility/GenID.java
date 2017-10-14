/*
 * GenID.java
 *
 * Created on 22 ตุลาคม 2548, 16:20 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.utility;
import java.io.*;
import java.util.*;
import java.text.DecimalFormat;
/**
 *
 * @author tong(Padungrat)
 */
public class GenID {
    
    /** Creates a new instance of GenID */
    private String id;
    private String genid;
    String site = null;
    /**GenID for Hospital*/
    public GenID(String hospital_site) {
        id = new String();
        site =hospital_site;
    }
    /** GenID for Standard*/
    public GenID()
    {
        id = new String();
    }
    public String getGenID(String id)
    {   this.id = id;
        double start = System.currentTimeMillis();
        double end ;
        String timem;
        
       
        int i;
        int j;
        String tempid = new String();
        java.security.SecureRandom  rd = new java.security.SecureRandom();
      /*  
        for(i = 0 ; i<= 100000; i++ )
        {   
        }
        do
        { i = rd.nextInt();
        } while( i< 0);
        i = i /100000;
        for(j = 0 ; j < i/1000 ; j++)
        {
        }
        do
        {   j = rd.nextInt();
        }while(j<0);
            j = j /1000000;
        end = System.currentTimeMillis();
        timem = Integer.toString(Double.toString(end - start).length());
        tempid = i + timem + j;
       */
  
        tempid = String.valueOf( (double)(Math.random()*10000000000.0));
        tempid = setTemp(tempid);
        if(site!=null)
            id = id + site + tempid; 
        else
            id = id + "00000" + tempid; 
        return id;
    }
    
    public String getGenStandardID(String id)
    {   this.id = id;
        double start = System.currentTimeMillis();
        double end ;
        String timem;
        
       
        int i;
        int j;
        String tempid = new String();
        java.security.SecureRandom  rd = new java.security.SecureRandom();
      /*  
        for(i = 0 ; i<= 100000; i++ )
        {   
        }
        do
        { i = rd.nextInt();
        } while( i< 0);
        i = i /100000;
        for(j = 0 ; j < i/1000 ; j++)
        {
        }
        do
        {   j = rd.nextInt();
        }while(j<0);
            j = j /1000000;
        end = System.currentTimeMillis();
        timem = Integer.toString(Double.toString(end - start).length());
        tempid = i + timem + j;
       */
  
        tempid = String.valueOf( (double)(Math.random()*10000000000.0));
        tempid = setTemp(tempid);
       
            id = id + tempid; 
       
        return id;
    }
    
    private String setTemp(String temp)
    {   
       DecimalFormat df = new DecimalFormat();
        df.applyPattern("0000000000"); 
        temp = df.format(Double.parseDouble(temp));       
      /*  
        switch(temp.length())
        {   case 0 : temp = "0000000000" + temp ; break; 
            case 1 : temp = "000000000" + temp ; break;
            case 2 : temp = "00000000" + temp ; break;
            case 3 : temp = "0000000" + temp ; break;
            case 4 : temp = "000000" + temp ; break;
            case 5 : temp = "00000" + temp ; break;
            case 6 : temp = "0000" + temp ; break;
            case 7 : temp = "000" + temp ; break;
            case 8 : temp = "00" + temp ; break;
            case 9 : temp = "0" + temp ; break;
            default: ;
        }
        */
        return temp;
    }
   /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GenID g;
       int r =0;
        for(int i=0 ;i <=100 ;i++)
        {    g = new GenID("12345");
            System.out.println("Gen : " + g.getGenID("332"));
        }
     
    }
    
}
