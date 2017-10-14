/*
 * Language.java
 *
 * Created on 20 �Զع�¹ 2548, 13:20 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportcenter.utility;

/**
 *
 * @author Administrator
 */
public class Language {
    static final String thai = "com/reportcenter/property/Thai";
    static final String eng = "com/reportcenter/property/eng";
    private static String text= "";
    private static String language = "";
    /**
     * Creates a new instance of Language1 
     */
    public Language() {
    }
    
   
    /**
     * ����ҷ��������������������Ţͧ������е�ͧ�������ŧ�������������ѧ���
     * @return �����ŷ��١�ŧ�͡� ��������ŷ���Ѻ
     * @param str �����ŷ���ͧ��è��ŧ�͡��
     * @param lang �� ����Ţ����ͧ��è�����ŧ�͡������������
     * 1 ����͡����������
     * 2 ����͡���������ѧ���
     */
    public static String getTextBundle(String str,int lang)
    {   text = str;
     
        switch(lang)
        {   
            case  1 :   text = getTextThaiBundle(str);
                       
                        break;
            case  2 :   text = getTextEngBundle(str);
                        break;
            default : text = str;
                        break;
        }

        return text;
    }
    
    
    
    /**
     * �繡���Ѻ����������ŧ�͡���������� ���繵�ͧ�� property ����͹
     * @param str �繪������ҷ���ͧ��è�����ŧ�͡����������
     * @return ���������͡���� String �����������
     */
    public static String getTextThaiBundle(String str)
    {    language = "";
         if(str.trim().equals(""))
         {
                language = "";
         }
        try{
            language = java.util.ResourceBundle.getBundle(thai).getString(str);
        }catch(Exception e){ 
           // e.printStackTrace();
           
            language = str;
            
        }
         
         return language;
    }
    /**
     * �繡���Ѻ����������ŧ�͡���������ѧ��� ���繵�ͧ�� property ����͹
     * @param str �繪������ҷ���ͧ��è�����ŧ�͡���������ѧ���
     * @return ���������͡���� String ����������ѧ���
     */
    public static String getTextEngBundle(String str)
    {   String data ="";
         if(str.trim().equals("")){
             data = "";
         }
        try{
            data = java.util.ResourceBundle.getBundle(eng).getString(str);
        }catch(Exception e){ 
           // System.out.println(str + ":Not Found ");
            data = str;
        }
        return data;
    }
    
    public static void main(String[] argv)
    {
        System.out.println(Language.getTextThaiBundle("Edit"));
    }
}
