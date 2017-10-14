/*
 * ReadModule.java
 *
 * Created on 27 �ѹ��¹ 2548, 9:32 �.
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
                //�ӡ�õ�Ǩ�ͺ���������չ��ʡ���� xml ������� �����Ҫ��͹�鹨��繵��������͵���˭�
                //filename.toLowerCase().
                      
                if(filename.toLowerCase().endsWith(".xml"))
                {
                    System.out.println("Load File name : " + filename);
                    
                    loadModule(filemodule[i]);
                    /*
                    //��Ǩ�ͺ ����� ����ش�����������
                    if(i==(size-1))
                    {   //�ӡ�� load ��� ����к�
                        module = module+loadModule(filemodule[i]);
                    }
                    else
                    {   //�ӡ�� load ��� ����к�
                        module = module+loadModule(filemodule[i]) + END_MODULE;
                    }
                     */
                }
            }
            listModule();
        //    System.out.println(module);
        }
    }
    
    /**�ӡ�� listmodule ��ѧ�ҡ�����ҹ��� config �ҷ���������
     */
    private void listModule()
    {
        if(hashModule !=null)
        {   sortIndex();
            int size = row.length;
            for(int i=0 ; i < size ; i++)
            {
                
                if(i==(size-1))
                {   //�ӡ�� load ��� ����к�
                    module = module+hashModule.get(String.valueOf(row[i]));
                }
                else
                {   //�ӡ�� load ��� ����к�
                    module = module+hashModule.get(String.valueOf(row[i])) + END_MODULE;
                }
              //  System.out.println(module);
                System.out.println("Index:  " + row[i] + " : " + hashModule.get(String.valueOf(row[i])));
            }
            
        }
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
        
     //   System.out.println(index);
        return index;
    }
    
    /**
     *  �ӡ�� loadModule ����ҵ�� �����Ţͧ��� Config �����ҹ��
     *  @param filemodule �� object File �� path ��� ������� ����ͧ��è������ҹ��Ң�����
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
     *  ��㹡�õ�Ǩ�ͺ ����� Directory ��������������
     *  �������ըзӡ�����ҧ Directory ������
     *@return boolean true ��Directory �����������, false ����� Directory ���� ���� ����դ��(null)
     */
    private boolean checkFile()
    {   boolean result = true;
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
        System.out.println("File " + f.toString()+ "is Exists : " + f.exists());
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
