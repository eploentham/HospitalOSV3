/*
 * ReadUseRort.java
 *
 * Created on 4 ��Ȩԡ�¹ 2548, 11:22 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportcenter.utility;

/**
 *
 * @author tong(Padungrat)
 */
public class ReadUseRort {
    
    static final String Config = "com/reportcenter/property/ForUseReport";
  
    private static String text= "";
    private static String language = "";
    public ReadUseRort() {
    }
    /**
     * ����ҷ��������������������Ţͧ������е�ͧ�������ŧ�������������ѧ���
     * @return �����ŷ��١�ŧ�͡� ��������ŷ���Ѻ
     * @param str �����ŷ���ͧ��è��ŧ�͡��
     * @param lang �� ����Ţ����ͧ��è�����ŧ�͡������������
     * 1 ����͡����������
     * 2 ����͡���������ѧ���
     */
    public static String getTextBundle(String str)
    {   text = str;
     
      text = getTextThaiBundle(str);
          

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
            language = java.util.ResourceBundle.getBundle(Config).getString(str);
        }catch(Exception e){ 
           // e.printStackTrace();
           
            language = str;
            
        }
         
         return language;
    }
    
}
