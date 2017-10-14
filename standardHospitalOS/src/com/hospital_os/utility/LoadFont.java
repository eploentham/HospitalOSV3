/*
 * LoadFont.java
 *
 * Created on 26 กันยายน 2548, 19:37 น.
 */

package com.hospital_os.utility;
import java.io.File;

/**
 *
 * @author  tong(Padungrat)
 */
public class LoadFont {
    
    /** Creates a new instance of LoadFont */
    public LoadFont() {
      
       
    }
    
    
    /**
     *  ใช้ในการ load Font ที่ printing ต้องการ โดยโปรแกรมจะทำการอ่านไฟล์ config ที่ชื่อ 
     *  pathfont.xml ขึ้นมา จะเป็นที่เก็บ Directory ของ font จะ Default ที่ font
     *  ถ้าโปรแกรมหา config นี่ไม่เจอ โปรกรมจะทำการ สร้างขึ้นมาให้เองอัตโนมัติ
     *  ถ้ามีไฟล์ config อยู่แล้วโปรแกรมจะ load Font ตาม path ที่กำหนดใน config นั้น
     *  
     *  ในส่วนนี้จะ ต้อง load ก่อนการพิมพ์ จะทำเพียง ครั้งเดียวเท่านั้น
     *  @deprecated must send parameter
     */
    public static boolean readPathFont()
    {
        DefaultFileFont dff= new DefaultFileFont();
        boolean result = false;
        //ตรวจสอบ ไฟล์ config มีอยู่หรือไม่ ถ้าไม่จะสร้าง ให้
        dff.checkXMLFile();
        //ทำการ อ่าน ไฟล์ config 
        dff.readXML();
        //ทำการ load ชื่อ Directory และ ทำการ สั่งให้ load ไฟล์
        result = loadFileFont(dff.getPathFont());
        
        
        dff = null;
        
        return result;
    }
    
    public static boolean readPathFont(String path_font_file)
    {
        DefaultFileFont dff= new DefaultFileFont(path_font_file);
        boolean result = false;
        //ตรวจสอบ ไฟล์ config มีอยู่หรือไม่ ถ้าไม่จะสร้าง ให้
        dff.checkXMLFile();
        //ทำการ อ่าน ไฟล์ config 
        dff.readXML();
        //ทำการ load ชื่อ Directory และ ทำการ สั่งให้ load ไฟล์
        result = loadFileFont(dff.getPathFont());
        
        
        dff = null;
        
        return result;
    }
    /**
     *  ใช้ในการ load ไฟล์ ที่อยู่ใน Directory ทั้งหมด
     *  และจะทำการตรวจสอบไฟล์ ว่า เป็นไฟล์ font หรือไม่ (โดยจะมี นามสกุลเป็น ttf 
     *  ถ้าไม่ใช้ จะไม่ load เข้ามา
     *  @param pathfile เป็น String ที่เป็น ชื่อ Directory จะต้องพิมพ์ path เต็ม เช่น
     *  c:/font
     *  @return boolean ถ้าเป็น true หมายความว่า load ไฟล์ font ได้ แต่จะมีหรือไม่ file font ก็ได้
     *  ถ้าเป็น false หมายความว่าไม่มี Directory นั้นอยู่ และจะทำการ create Directory ให้
     * 
     */
    public static boolean loadFileFont(String pathfile)
    {
        boolean result = true;
        Constant.println("String name: " + pathfile);
        File file = new File(pathfile);
        Constant.println("Path is Directory : " + file.getName());
        //ตรวจสอบค่า ว่าเป็น Directory หรือไม่
        if(file.isDirectory())
        {
           // ให้ค่า list File ที่อยู่ใน Directory
            File[] listFile = file.listFiles();
            int size = listFile.length;
            String filename="";
            //ทำการวนลูป
            for(int i = 0; i < size ; i++)
            {   //ให้ค่าข้อมูลที่เป็นชื่อ
                filename = listFile[i].getAbsolutePath();
                //ทำการตรวจสอบว่าไฟล์นั้นมีนามสกุลเป็น ttf หรือไม่ ไม่ว่าชื่อนั้นจะเป็นตัวเล็กหรือตัวใหญ่
                if(filename.toLowerCase().indexOf("ttf")>0)
                {
                    Constant.println("Load File name : " + filename);
                    //ทำการ load ไฟล์ เข้าระบบ
                    loadFont(filename);
                }
            }
        }
        else
        {
            Constant.println("Path is not Directory : " + file.getAbsolutePath());
            //ทำการสร้าง Directory ให้
            //file.mkdir();
            result = false;
        }
        file = null;
        
        return result;
    }
    
    /**
     *  จะทำการ load ไฟล์ เข้าระบบตาม file ที่กำหนดให้
     *  @param file เป็น ชื่อ ที่ระบุถึง path ไฟล์
     *  @return Font สามารถนำไปใช้งานได้
     */
    public static java.awt.Font loadFont(String file)
    {
        java.awt.Font f = null;
        try {
            //ทำการ load ไฟล์เข้าระบบ
            f = java.awt.Font.createFont(f.TRUETYPE_FONT,  new java.io.FileInputStream(file));
           
        }
        catch (IllegalArgumentException ett)
        {
            Constant.println(ett.getMessage() +" No TrueType font");
        }
        catch (java.awt.FontFormatException ef)
        {
            Constant.println(ef.getMessage() +" FontFormatException");
        }
         catch (java.io.IOException ioex)
        {
            Constant.println(ioex.getMessage() +" IOException");
        }
        catch (Exception ex)
        {
            Constant.println(ex.getMessage() +" General Exception");
        }

        return f;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
       LoadFont.readPathFont();
    }
    
}
