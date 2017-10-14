/*
 * GUISubject.java
 *
 * Created on 5 �ѹ��¹ 2548, 16:29 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportquery.manage.subject;
import java.util.Vector;
import com.reportquery.usecase.gui.GUIManage;


/**
 *
 * @author tong(Padungrat)
 */
public class GUISubject implements GUIManage 
{
    Vector vGUIManage;
  
    public GUISubject() {
        vGUIManage = new Vector();
    }
    
    /**
     * ��㹡��ŧ����¹ �����Ѻ Notify �ͧ��èѴ�������ǡѺ GUI
     * ��觨е�ͧ �ա�� inplements GUIManage �ҡ�͹
     * @ guimanage �ͧ Interface ������ GUIManage
     */
    public void registerGUIManage(GUIManage guimanage)
    {   System.out.println("Register");
        vGUIManage.add(guimanage);
    }
    /**
     * ��㹡���觤�ҷ���ͧ��á�Ѻ��ѧ form ����ա���Ѻ Notify
     * �ͧ��� refresh GUI
     */
    public void notifyRefreshGUI() {
        for(int i =0 ; i<vGUIManage.size();i++)
        { 
            ((GUIManage)vGUIManage.get(i)).notifyRefreshGUI();
        }
    }
    
    /**
     * ��㹡���觤�ҷ���ͧ��á�Ѻ��ѧ form ����ա���Ѻ Notify
     * �ͧ��� �ʴ���
     */
    public void notifyCallShowSetupSQLTemplate() {
        for(int i =0 ; i<vGUIManage.size();i++)
        { 
            ((GUIManage)vGUIManage.get(i)).notifyCallShowSetupSQLTemplate();
        }
    }
    
}
