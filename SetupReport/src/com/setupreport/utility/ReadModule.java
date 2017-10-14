/*
 * ReadModule.java
 *
 * Created on 27 กันยายน 2548, 9:32 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.utility;
import java.io.File;
import java.util.HashMap;
/**
 *
 * @author tong(Padungrat)
 */
public class ReadModule {
    
    final String directory = "config/report/othersetup";
    
    final String END_MODULE = ";";
    File file;
    DefaultReadModule theDefaultReadModule; 
    String module;
    int[] row;
    int count ;
    HashMap hashModule;
 
 
    public ReadModule() {
        file = new File(directory);
        module = "-module=";
        count = 0; 
        hashModule = new HashMap();
      
        
        readDirectory();
    }
    
    public String[] getData()
    {
        return new String[] {module};
    }
    
    private void readDirectory()
    {
        if(checkFile())
        {
            loadFile();
        }
        else
        {
            System.out.println("Make Directory ");
        }
    }
    
    private void loadFile()
    {
        if(file != null && file.isDirectory())
        {
            
           
            //new SimpleFileFilte
            File[] filemodule = file.listFiles();
            int size = filemodule.length;
            row = new int[size];
            String filename ="";
            for(int i =0;i<size;i++)
            {
                filename = filemodule[i].getAbsolutePath();
                //ทำการตรวจสอบว่าไฟล์นั้นมีนามสกุลเป็น xml หรือไม่ ไม่ว่าชื่อนั้นจะเป็นตัวเล็กหรือตัวใหญ่
                //filename.toLowerCase().
                      
                if(filename.toLowerCase().endsWith(".xml"))
                {
                    System.out.println("Load File name : " + filename);
                    
                    loadModule(filemodule[i]);
                    /*
                    //ตรวจสอบ ว่าเป็น ตัวสุดท้ายหรือไม่
                    if(i==(size-1))
                    {   //ทำการ load ไฟล์ เข้าระบบ
                        module = module+loadModule(filemodule[i]);
                    }
                    else
                    {   //ทำการ load ไฟล์ เข้าระบบ
                        module = module+loadModule(filemodule[i]) + END_MODULE;
                    }
                     */
                }
            }
            listModule();
        //    System.out.println(module);
        }
    }
    
    /**ทำการ listmodule หลังจากการอ่านไฟล์ config มาทั้งหมดแล้ว
     */
    private void listModule()
    {
        if(hashModule !=null)
        {   sortIndex();
            int size = row.length;
            for(int i=0 ; i < size ; i++)
            {
                
                if(i==(size-1))
                {   //ทำการ load ไฟล์ เข้าระบบ
                    module = module+hashModule.get(String.valueOf(row[i]));
                }
                else
                {   //ทำการ load ไฟล์ เข้าระบบ
                    module = module+hashModule.get(String.valueOf(row[i])) + END_MODULE;
                }
              //  System.out.println(module);
                System.out.println("Index:  " + row[i] + " : " + hashModule.get(String.valueOf(row[i])));
            }
            
        }
    }
    
    /**
     * ใช้ในการ sort Index ที่ array ของ row เก็บอยู่ เพื่อเป็นไปตาม Index ที่ไฟล์ config เก็บอยู่
     */
    private void sortIndex()
    {   int temp =0;
        
        // ทำการวนลูป ตามจำนวน ของ array ของ row
        for(int i =0; i < row.length;i++)
        {
            temp = row[i];
            //ตรวจสอบว่า ต้องไม่เป็นตัวสุดท้าย
            if(i != row.length-1)
            {
                //ทำการเปลี่ยบเที่ยบค่าของ array row เป็นอยู่ ถ้าน้อยกว่า ให้ทำการ swap ค่าทั้นที่
               if(temp> row[i+1])
               {
                   row[i] = row[i+1];
                   row[i+1] = temp;
               }
            }
        }
    }
    
    /**
     *  ทำการตรวจสอบ ค่า Index ที่เข้ามาให้ว่ามีการซ้ำกันกับ Index ที่มีอยู่หรือไม่
     *  ถ้ามีซ้ำกัน จะทำการเพิ่มค่าขึ้นไปที่ละ 1 และทำการตรวจสอบอีกว่า มีซ้ำกันหรือไม่
     *  ถ้าไม่ซ้ำ ให้ return ค่าที่ถูกต้องออกมา
     *  @param newIndex เป็น int เป็นค่า Index ที่ใช้ตรวจสอบว่าซ้ำกันหรือไม่
     *  @return int เป็น Index ใหม่ ที่ function ทำการตรวจสอบ และส่งค่ามาให้
     */
    private int checkIndex(int newIndex)
    {  int index = newIndex;
       //ทำการวนลูบตาม จำนวน array ของ row ที่เก็บอยู่
        for(int i = 0;i < row.length ; i++)
        {
           //ตรวจสอบ ค่าที่รับเข้ามาว่าซ้ำกันหรือไม่ 
            if(newIndex == row[i])
            {   //ซ้ำกันทำการเพิ่มค่าอีก 1 และทำการตรวจสอบ ว่าซ้ำกันกับที่มีอยู่หรือไม่
                newIndex = newIndex+1;
                index = checkIndex(newIndex);
            }
            
        }
        
     //   System.out.println(index);
        return index;
    }
    
    /**
     *  ทำการ loadModule เข้ามาตาม ข้อมูลของไฟล์ Config ที่อ่านได้
     *  @param filemodule เป็น object File เป็น path และ ชื่อไฟล์ ที่ต้องการจะให้อ่านค่าข้อมูล
     *  
     */
    private void loadModule(File filemodule)
    {
        theDefaultReadModule = new DefaultReadModule(filemodule);
        theDefaultReadModule.checkXMLFile();
        theDefaultReadModule.readXML();
        int index = checkIndex(Integer.parseInt(theDefaultReadModule.getIndex()));
        row[count] = index;
        hashModule.put(String.valueOf(index),theDefaultReadModule.getModule());
        count++;
        
        
    }
    
    /**
     *  ใช้ในการตรวจสอบ ว่ามี Directory นั้นอยู่หรือไม่
     *  ถ้าไม่มีจะทำการสร้าง Directory นั้นให้
     *@return boolean true มีDirectory นั้นอยู่แล้ว, false ไม่มี Directory อยู่ หรือ ไม่มีค่า(null)
     */
    private boolean checkFile()
    {   boolean result = true;
        if(file!=null)
        {   // ถ้าไม่มีอยู่
            if(!file.exists())
            {   //สร้าง Direcroty ให้
                createFile();
                result = false;
            }
        }
        else
        {
            result = false;
        }
        return result;
    }
    
    /**
     *  ใช้ในการสร้าง Directory ให้เมื่อไม่มี Directory 
     */
    private void createFile()
    {  
        java.io.File f = file;//new java.io.File(file.getParent());
        System.out.println("File " + f.toString()+ "is Exists : " + f.exists());
        java.io.File fi = new java.io.File(f.getParent());
        
        try{
     
        if(!f.exists())
        {
            /**ต้อง สร้าง Folder gui แต่ต้องตรวจสอบก่อนว่ามี Folder ก่อนหน้านี้เปล่า*/
        
            if(!f.exists())
            {
                /**ต้อง สร้าง Folder config */
                fi.mkdir();
            }
            f.mkdir();
            
        }
          
     
        }
        catch(Exception ex)
        {
            ex.printStackTrace();

        }
        f = null;
        fi = null;
    }
    
 
    public static void main(String[] argv)
    {
        ReadModule readmodule = new ReadModule();
      // readmodule.readDirectory();
        
    }
}
