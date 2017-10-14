/*
 * Language.java
 *
 * Created on 20 �Զع�¹ 2548, 13:20 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.language.utility.language;
import com.hospital_os.utility.Constant;
import java.util.Properties;
/**
 *
 * @author tong(Padungrat)
 */
public class Language {

    private static String text= "";
    private static String language = "";
    private static Properties data = null;
    /** Creates a new instance of Language */
    public Language() {
    
    }
    
    /**��㹤����á����ͧ��� get ���ҷ���ͧ�����
     *@return Properties �� Object �ͧ Properties 
     *�������դ�������Դ�Դ��Ҵ�� ������� null
     * @deprecated please useg getProperties(String module_name,String lng_file,String lng_path)
     */
    public static Properties getProperties(String module_name)
    {
        DefaultReadLanguage df = new DefaultReadLanguage(module_name);
        data = new Properties();
        boolean result = false;
        if(df.checkXMLFile())
        {   
            df.readXML();
            data =  df.getProperties();
        }
        return df.getProperties();
    }
    public static Properties getProperties(String module_name,String lng_file,String lng_path)
    {
        DefaultReadLanguage df = new DefaultReadLanguage(module_name,lng_file,lng_path);
        data = new Properties();
        boolean result = false;
        if(df.checkXMLFile())
        {   
            df.readXML();
            data =  df.getProperties();
        }
        return df.getProperties();
    }
   
    /**
     * ����ҷ��������������������Ţͧ������е�ͧ�������ŧ�������������ѧ���
     * @return �����ŷ��١�ŧ�͡� ��������ŷ���Ѻ
     * @param str �����ŷ���ͧ��è��ŧ�͡��
     */
    public static String getTextBundle(String str)
    {   language = "";
        if(str.trim().equals(""))
        {
            language = "";
        }
        else
        {
            if(data !=null)
            {
                try
                {
                    language =  data.getProperty(str);
                }
                catch(Exception e)
                {  e.printStackTrace(Constant.getPrintStream());
                    language = str;
                }
                finally
                {

                }
            }
            
            if(language==null || language.trim().length() == 0)
            {
                language = str;
            }
            
        }
        return language;
    }
    
   
    public static void main(String[] argv)
    {
        Constant.println(Language.getTextBundle("Edit"));
    }

    public static Object getTextBundle(String string, int language) {
        return null;
    }
}
