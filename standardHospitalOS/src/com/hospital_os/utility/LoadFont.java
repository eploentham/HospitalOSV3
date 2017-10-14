/*
 * LoadFont.java
 *
 * Created on 26 �ѹ��¹ 2548, 19:37 �.
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
     *  ��㹡�� load Font ��� printing ��ͧ��� ��������зӡ����ҹ��� config ������ 
     *  pathfont.xml ����� ���繷���� Directory �ͧ font �� Default ��� font
     *  ���������� config �������� �á���зӡ�� ���ҧ���������ͧ�ѵ��ѵ�
     *  �������� config ��������������� load Font ��� path ����˹�� config ���
     *  
     *  ���ǹ���� ��ͧ load ��͹��þ���� �з���§ ����������ҹ��
     *  @deprecated must send parameter
     */
    public static boolean readPathFont()
    {
        DefaultFileFont dff= new DefaultFileFont();
        boolean result = false;
        //��Ǩ�ͺ ��� config ������������� ����������ҧ ���
        dff.checkXMLFile();
        //�ӡ�� ��ҹ ��� config 
        dff.readXML();
        //�ӡ�� load ���� Directory ��� �ӡ�� ������ load ���
        result = loadFileFont(dff.getPathFont());
        
        
        dff = null;
        
        return result;
    }
    
    public static boolean readPathFont(String path_font_file)
    {
        DefaultFileFont dff= new DefaultFileFont(path_font_file);
        boolean result = false;
        //��Ǩ�ͺ ��� config ������������� ����������ҧ ���
        dff.checkXMLFile();
        //�ӡ�� ��ҹ ��� config 
        dff.readXML();
        //�ӡ�� load ���� Directory ��� �ӡ�� ������ load ���
        result = loadFileFont(dff.getPathFont());
        
        
        dff = null;
        
        return result;
    }
    /**
     *  ��㹡�� load ��� �������� Directory ������
     *  ��Шзӡ�õ�Ǩ�ͺ��� ��� ����� font ������� (�¨��� ���ʡ���� ttf 
     *  �������� ����� load �����
     *  @param pathfile �� String ����� ���� Directory �е�ͧ����� path ��� ��
     *  c:/font
     *  @return boolean ����� true ���¤������ load ��� font �� ������������ file font ����
     *  ����� false ���¤����������� Directory ������� ��Шзӡ�� create Directory ���
     * 
     */
    public static boolean loadFileFont(String pathfile)
    {
        boolean result = true;
        Constant.println("String name: " + pathfile);
        File file = new File(pathfile);
        Constant.println("Path is Directory : " + file.getName());
        //��Ǩ�ͺ��� ����� Directory �������
        if(file.isDirectory())
        {
           // ����� list File �������� Directory
            File[] listFile = file.listFiles();
            int size = listFile.length;
            String filename="";
            //�ӡ��ǹ�ٻ
            for(int i = 0; i < size ; i++)
            {   //����Ң����ŷ���繪���
                filename = listFile[i].getAbsolutePath();
                //�ӡ�õ�Ǩ�ͺ���������չ��ʡ���� ttf ������� �����Ҫ��͹�鹨��繵��������͵���˭�
                if(filename.toLowerCase().indexOf("ttf")>0)
                {
                    Constant.println("Load File name : " + filename);
                    //�ӡ�� load ��� ����к�
                    loadFont(filename);
                }
            }
        }
        else
        {
            Constant.println("Path is not Directory : " + file.getAbsolutePath());
            //�ӡ�����ҧ Directory ���
            //file.mkdir();
            result = false;
        }
        file = null;
        
        return result;
    }
    
    /**
     *  �зӡ�� load ��� ����к���� file ����˹����
     *  @param file �� ���� ����кض֧ path ���
     *  @return Font ����ö�����ҹ��
     */
    public static java.awt.Font loadFont(String file)
    {
        java.awt.Font f = null;
        try {
            //�ӡ�� load �������к�
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
