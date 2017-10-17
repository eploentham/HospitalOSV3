/*
 * ReadModule.java
 *
 * Created on 27 กันยายน 2548, 9:32 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.utility;
import com.hospital_os.usecase.connection.ModuleInfTool;
import java.io.File;
import java.util.HashMap;
import java.util.Vector;
/**
 * 
 * @author tong(Padungrat) henbe modify ทดสอบภาษาไทย
 */
public class ReadModule {
    
    String directory = Config.MODULE_PATH;
    
    static final String END_MODULE = ";";
    File file;
    DefaultReadModule theDefaultReadModule;
    String module;
    int[] row;
    int count ;
    HashMap hashModule;
 
    public ReadModule(String m_path) {
        directory = m_path;
        file = new File(directory);
        module = "-module=";
        count = 0; 
        hashModule = new HashMap();

    }
 
    public ReadModule() {
        file = new File(directory);
        module = "-module=";
        count = 0; 
        hashModule = new HashMap();

    }   
     
    public static Vector loadModule(String[] argc,String dir,String dir_rp)
    {
        System.out.println("dir_rp "+dir_rp);
        Vector theModuleV = new Vector();
        ReadModule rm = new ReadModule(dir);
        String modules = "";
        for(int i=0;i<argc.length;i++){
            if(argc[i].equals("-module_xml")){
                modules = rm.listModule(rm.directory,dir_rp);
            }
            if(argc[i].startsWith("-module="))
                modules = argc[i];
        }
        //ใช้การเช็ค argrument จากการ run โปรแกรม
        Constant.println("_________________________module "+dir);
        Constant.println(modules);
        // -module=com.XModule;com.YModule;com.ZModule 
        int index = modules.indexOf('=');
        if(index==-1) return theModuleV;
        modules = modules.substring(index + 1) + ";";
        //com.XModule;com.YModule;com.ZModule;
        //           |
        index = modules.indexOf(';');
        while(index != -1){
            String mod = modules.substring(0,index);
            modules = modules.substring(index+1);
            index = modules.indexOf(';');
            
            try{
                Constant.println("'" + mod + "'");
                if(mod.trim().length()==0)
                    continue;
                ModuleInfTool mi = (ModuleInfTool)Class.forName(mod).newInstance();
                theModuleV.add(mi);
            }
            catch(Exception e){
                e.printStackTrace(Constant.getPrintStream());
                Constant.println("Cannot found external Module :" + mod);
            }
        }
        return theModuleV;
    }
    private String listModule(String dir,String dir_rp)
    {
        StringBuffer sb = new StringBuffer("-module=");
        File file = new File(dir);
        File[] filemodule = file.listFiles();
        for(int i =0;i<filemodule.length;i++)
        {
            String filename = filemodule[i].getAbsolutePath();
            if(filename.toLowerCase().endsWith(".xml"))
            {
                theDefaultReadModule = new DefaultReadModule(filemodule[i]);
                theDefaultReadModule.checkXMLFile();
                theDefaultReadModule.readXML();
                sb.append(theDefaultReadModule.getModule()).append(";");
                theDefaultReadModule.getIndex();
            }
        }
        if(dir_rp!=null) {
        file = new File(dir_rp);
        filemodule = file.listFiles();
        for(int i =0;i<filemodule.length;i++)
        {
            String filename = filemodule[i].getAbsolutePath();
            if(filename.toLowerCase().endsWith(".xml"))
            {
                theDefaultReadModule = new DefaultReadModule(filemodule[i]);
                theDefaultReadModule.checkXMLFile();
                theDefaultReadModule.readXML();
                sb.append(theDefaultReadModule.getModule()).append(";");
                theDefaultReadModule.getIndex();
            }
        }
        }
        return sb.toString();
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
        
     //   Constant.println(index);
        return index;
    }
    
    private void loadModule(File filemodule,HashMap hashModule)
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
    private boolean checkFile(String dir)
    {   
        file = new File(directory);
        boolean result = true;
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
        Constant.println("File " + f.toString()+ "is Exists : " + f.exists());
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
            ex.printStackTrace(Constant.getPrintStream());

        }
        f = null;
        fi = null;
    }

    private String genModuleArgc(HashMap hashModule) 
    {
            sortIndex();
            module = "-module";
            for(int i=0 ; i < row.length ; i++)
            {
                if(hashModule.get(String.valueOf(row[i]))==null)
                    continue;
                
//                if(i==(row.length-1))
//                {   //ทำการ load ไฟล์ เข้าระบบ
//                    module = module+hashModule.get(String.valueOf(row[i]));
//                }
//                else
//                {   //ทำการ load ไฟล์ เข้าระบบ
//                    module = module+hashModule.get(String.valueOf(row[i])) + END_MODULE;
//                }
              // Constant.println(module);
                 Constant.println("Index:  " + row[i] + " : " + hashModule.get(String.valueOf(row[i])));
                 if(i==0)
                     module = module + "=" + String.valueOf(hashModule.get(String.valueOf(row[i])));
                 else
                    module = module + ";" + String.valueOf(hashModule.get(String.valueOf(row[i])));
            }
            Constant.println("_________________________argc henbe");
            Constant.println(module);
            return module;
    }
    
 
}
