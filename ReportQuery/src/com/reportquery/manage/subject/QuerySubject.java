
/*
 * QuerySubject.java
 *
 * Created on 6 �ѹ��¹ 2548, 13:12 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportquery.manage.subject;
import java.util.Vector;
import com.reportquery.usecase.gui.QueryManage;
/**
 *
 * @author tong(Padungrat)
 */
public class QuerySubject implements QueryManage
{
    Vector vQueryManage;
    /** Creates a new instance of QuerySubject */
    public QuerySubject() {
         vQueryManage = new Vector();
    }

    /**
     * ��㹡��ŧ����¹ �����Ѻ Notify �ͧ��èѴ�������ǡѺ GUI
     * ��觨е�ͧ �ա�� inplements GUIManage �ҡ�͹
     * @ guimanage �ͧ Interface ������ GUIManage
     */
    public void registerGUIManage(QueryManage queryManage)
    {   System.out.println("Register");
        vQueryManage.add(queryManage);
    }
    
    /**��㹡���觤�ҡ�Ѻ��ѧ panel ������ա�� Query ������Ẻ���ѹ���
     */
    public void notifyQueryDataDate(String[] columnname, Vector vString, int exception, String exceptions) 
    {
        for(int i =0 ; i<vQueryManage.size();i++)
        { 
            ((QueryManage)vQueryManage.get(i)).notifyQueryDataDate(columnname,vString,exception,exceptions);
        }
    }
    
    /**��㹡���觤�ҡ�Ѻ��ѧ panel ������ա�� Query ������Ẻ������ѹ���
     */
    public void notifyQueryDataNoDate(String[] columnname, Vector vString, String exceptions) 
    {
        for(int i =0 ; i<vQueryManage.size();i++)
        { 
            ((QueryManage)vQueryManage.get(i)).notifyQueryDataNoDate(columnname,vString,exceptions);
        }
    }
    /**
     * ��㹡�� �� ��ҡ�Ѻ��ѧ object �����ŧ����¹����
     */
    public void onShowPicture(String pic, boolean isVisible) {
        for(int i =0; i < vQueryManage.size();i++)
        {
            ((QueryManage)vQueryManage.elementAt(i)).onShowPicture(pic, isVisible);
        }
    }

    public void onShowStatus(String str, java.awt.Color color) {
        for(int i =0; i < vQueryManage.size();i++)
        {
            ((QueryManage)vQueryManage.elementAt(i)).onShowStatus(str, color);
        }
    }

    public void onShowStatus(String str, java.awt.Color color, boolean isFinish) {
        for(int i =0; i < vQueryManage.size();i++)
        {
            ((QueryManage)vQueryManage.elementAt(i)).onShowStatus(str, color,isFinish);
        }
    }
    /**��㹡�á�˹���� object ����Ѻ notify �ء��� ����ö�ӧҹ���������*/
    public void setEnabled(boolean setEnabled) {
        for(int i =0; i < vQueryManage.size();i++)
        {
            ((QueryManage)vQueryManage.elementAt(i)).setEnabled(setEnabled);
        }
    }

  
}
