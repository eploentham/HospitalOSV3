/*
 * ReadModule.java
 *
 * Created on 27 �ѹ��¹ 2548, 9:32 �.
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
 * @author tong(Padungrat) henbe modify ���ͺ������
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
        //������ argrument �ҡ��� run �����
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
     * ��㹡�� sort Index ��� array �ͧ row ������ ������仵�� Index ������ config ������
     */
    private void sortIndex()
    {   int temp =0;
        
        // �ӡ��ǹ�ٻ ����ӹǹ �ͧ array �ͧ row
        for(int i =0; i < row.length;i++)
        {
            temp = row[i];
            //��Ǩ�ͺ��� ��ͧ����繵���ش����
            if(i != row.length-1)
            {
                //�ӡ������º���º��Ңͧ array row ������ ��ҹ��¡��� ���ӡ�� swap ��ҷ�鹷��
               if(temp> row[i+1])
               {
                   row[i] = row[i+1];
                   row[i+1] = temp;
               }
            }
        }
    }
    
    /**
     *  �ӡ�õ�Ǩ�ͺ ��� Index ���������������ա�ë�ӡѹ�Ѻ Index ����������������
     *  ����ի�ӡѹ �зӡ��������Ң��价���� 1 ��зӡ�õ�Ǩ�ͺ�ա��� �ի�ӡѹ�������
     *  �������� ��� return ��ҷ��١��ͧ�͡��
     *  @param newIndex �� int �繤�� Index ������Ǩ�ͺ��ҫ�ӡѹ�������
     *  @return int �� Index ���� ��� function �ӡ�õ�Ǩ�ͺ ����觤�������
     */
    private int checkIndex(int newIndex)
    {  int index = newIndex;
       //�ӡ��ǹ�ٺ��� �ӹǹ array �ͧ row ���������
        for(int i = 0;i < row.length ; i++)
        {
           //��Ǩ�ͺ ��ҷ���Ѻ�������ҫ�ӡѹ������� 
            if(newIndex == row[i])
            {   //��ӡѹ�ӡ����������ա 1 ��зӡ�õ�Ǩ�ͺ ��ҫ�ӡѹ�Ѻ����������������
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
     *  ��㹡�õ�Ǩ�ͺ ����� Directory ��������������
     *  �������ըзӡ�����ҧ Directory ������
     *@return boolean true ��Directory �����������, false ����� Directory ���� ���� ����դ��(null)
     */
    private boolean checkFile(String dir)
    {   
        file = new File(directory);
        boolean result = true;
        if(file!=null)
        {   // ������������
            if(!file.exists())
            {   //���ҧ Direcroty ���
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
     *  ��㹡�����ҧ Directory ������������� Directory 
     */
    private void createFile()
    {  
        java.io.File f = file;//new java.io.File(file.getParent());
        Constant.println("File " + f.toString()+ "is Exists : " + f.exists());
        java.io.File fi = new java.io.File(f.getParent());
        
        try{
     
        if(!f.exists())
        {
            /**��ͧ ���ҧ Folder gui ���ͧ��Ǩ�ͺ��͹����� Folder ��͹˹�ҹ������*/
        
            if(!f.exists())
            {
                /**��ͧ ���ҧ Folder config */
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
//                {   //�ӡ�� load ��� ����к�
//                    module = module+hashModule.get(String.valueOf(row[i]));
//                }
//                else
//                {   //�ӡ�� load ��� ����к�
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
